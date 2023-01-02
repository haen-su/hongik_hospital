package hongikhospital.reservation.service;

import hongikhospital.reservation.domain.Doctor;
import hongikhospital.reservation.domain.Patient;
import hongikhospital.reservation.domain.Treatment;
import hongikhospital.reservation.repository.DoctorRepository;
import hongikhospital.reservation.repository.PatientRepository;
import hongikhospital.reservation.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public Long saveTreatment(Treatment treatment) {
        treatmentRepository.save(treatment);

        return treatment.getId();
    }

    public Treatment findOne(Long treatmentId) {
        return treatmentRepository.findOne(treatmentId);
    }

    //==진료 목록 조회 for 환자==//
    public List<Treatment> findTreatmentsForPatient(Long patientId) {
        List<Treatment> treatments = treatmentRepository.findAll();
        List<Treatment> findTreatments = new ArrayList<>();

        for(Treatment treatment : treatments) {
            if(treatment.getPatient().getId() == patientId)
                findTreatments.add(treatment);
        }

        return findTreatments;
    }

    //==진료 목록 조회 for 의사==//
    public List<Treatment> findTreatmentsForDoctor(Long doctorId) {
        List<Treatment> treatments = treatmentRepository.findAll();
        List<Treatment> findTreatments = new ArrayList<>();

        for(Treatment treatment : treatments) {
            if(treatment.getDoctor().getId() == doctorId)
                findTreatments.add(treatment);
        }

        return findTreatments;
    }

}
