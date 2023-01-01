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

    public void saveDoctor(Department department, Doctor doctor) {
        doctor.setDepartment(department);
        doctorRepository.save(doctor);
    }

    public Doctor findOne(Long hospitalId, String departmentName, Long doctorId) {
        return doctorRepository.findOne(hospitalId, departmentName, doctorId);
    }

    public List<Doctor> findDoctors(Long hospitalId, String departmentName) {
        return doctorRepository.findAll(hospitalId, departmentName);
    }

}
