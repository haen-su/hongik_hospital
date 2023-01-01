package hongikhospital.reservation.service;

import hongikhospital.reservation.domain.Department;
import hongikhospital.reservation.domain.Doctor;
import hongikhospital.reservation.domain.Hospital;
import hongikhospital.reservation.repository.DoctorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class DoctorServiceTest {

    @Autowired DoctorService doctorService;
    @Autowired
    EntityManager em;

    @Test
    public void 의사_등록() {
        // given
        Hospital hospital = new Hospital();
        hospital.setName("서울아산병원");
        em.persist(hospital);
        Long hospitalId = hospital.getId();

        Department department = new Department();
        department.setName("외과");
        department.setHospital(hospital);
        em.persist(department);

        Doctor doctor = new Doctor();
        doctor.setName("김현수");
        doctorService.saveDoctor(department, doctor);

        //when
        Doctor findDoctor = doctorService.findOne(hospitalId, department.getName(), doctor.getId());

        // then
        assertThat(doctor).isEqualTo(findDoctor);
        assertThat(findDoctor.getName()).isEqualTo("김현수");

        System.out.println(findDoctor.getDepartment().getHospital().getName() + ", "
                + findDoctor.getDepartment().getName() + ", " + findDoctor.getName());
    }
}
