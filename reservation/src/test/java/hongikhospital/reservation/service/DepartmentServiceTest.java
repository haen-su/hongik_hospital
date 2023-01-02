package hongikhospital.reservation.service;

import hongikhospital.reservation.domain.Address;
import hongikhospital.reservation.domain.Department;
import hongikhospital.reservation.domain.Hospital;
import hongikhospital.reservation.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DepartmentServiceTest {

    @Autowired EntityManager em;
    @Autowired DepartmentService departmentService;
    @Autowired DepartmentRepository departmentRepository;

    @Test
    public void 진료과_등록() {
        // given
        Hospital hospital = new Hospital();
        hospital.setName("서울아산병원");
        em.persist(hospital);
        Long hospitalId = hospital.getId();

        Department department = createDepartment("외과", hospital, null);
        departmentService.saveDepartment(department);

        //when
        Department findDepartment = departmentService.findOne(hospitalId, "외과");

        // then
        assertThat(department).isEqualTo(findDepartment);
        assertThat(findDepartment.getName()).isEqualTo("외과");

    }

    @Test
    public void 진료과_목록_조회() {
        // given
        Hospital hospital = new Hospital();
        hospital.setName("서울아산병원");
        em.persist(hospital);
        Long hospitalId = hospital.getId();

        Department department1 = createDepartment("외과", hospital, null);
        departmentService.saveDepartment(department1);

        Department department2 = createDepartment("정신과", hospital, null);
        departmentService.saveDepartment(department2);

        //when
        List<Department> departments = departmentService.findDepartments(hospitalId);

        // then
        assertThat(departments.size()).isEqualTo(2);
        for(Department department : departments)
            System.out.println(department.getHospital().getName() + ", " + department.getName());
    }

    public Hospital createHospital(String name, String city, String street, String zipcode) {
        Hospital hospital = new Hospital();
        hospital.setName(name);
        hospital.setAddress(new Address(city, street, zipcode));

        return hospital;
    }

    public Department createDepartment(String name, Hospital hospital, String tel) {
        Department department = new Department();
        department.setName(name);
        department.setHospital(hospital);
        department.setTel(tel);

        return department;
    }
}
