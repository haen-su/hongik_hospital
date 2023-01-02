package hongikhospital.reservation.service;

import hongikhospital.reservation.domain.Department;
import hongikhospital.reservation.domain.Doctor;
import hongikhospital.reservation.repository.DepartmentRepository;
import hongikhospital.reservation.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DepartmentRepository departmentRepository;

    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor findOne(Long doctorId) {
        return doctorRepository.findOne(doctorId);
    }

    public List<Doctor> findDoctors(Long hospitalId, String departmentName) {
        return doctorRepository.findAll(hospitalId, departmentName);
    }

}
