package hongikhospital.reservation.service;

import hongikhospital.reservation.domain.Department;
import hongikhospital.reservation.domain.Hospital;
import hongikhospital.reservation.repository.DepartmentRepository;
import hongikhospital.reservation.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final HospitalRepository hospitalRepository;

    public void saveDepartment(Hospital hospital, Department department) {
        department.setHospital(hospital);
        departmentRepository.save(department);
    }

    public Department findOne(Long hospitalId, String departmentName) {
        return departmentRepository.findOne(hospitalId, departmentName);
    }

    public List<Department> findDepartments(Long hospitalId) {
        return departmentRepository.findAll(hospitalId);
    }

}
