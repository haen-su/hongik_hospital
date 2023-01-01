package hongikhospital.reservation.service;

import hongikhospital.reservation.domain.Hospital;
import hongikhospital.reservation.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    @Transactional
    public Long saveHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
        return hospital.getId();
    }

    public Hospital findOne(Long hospitalId) {
        return hospitalRepository.findOne(hospitalId);
    }

    public List<Hospital> findHospitals() {
        return hospitalRepository.findAll();
    }

}
