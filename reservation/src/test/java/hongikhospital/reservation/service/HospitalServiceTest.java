package hongikhospital.reservation.service;

import hongikhospital.reservation.domain.Address;
import hongikhospital.reservation.domain.Department;
import hongikhospital.reservation.domain.Hospital;
import hongikhospital.reservation.repository.HospitalRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class HospitalServiceTest {

    @Autowired
    HospitalService hospitalService;
    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    public void 병원등록() {
        // given
        Hospital hospital = createHospital("세브란스병원", "서울시", "서대문구", "1-1");

        //when
        Long id = hospitalService.saveHospital(hospital);
        Hospital findHospital = hospitalService.findOne(id);

        // then
        assertThat(hospital).isEqualTo(findHospital);
    }

    @Test
    @Rollback(value = false)
    public void 병원_목록_조회() {
        // given
        Hospital hospital1 = createHospital("세브란스병원", "서울시", "서대문구", "1-1");
        Hospital hospital2 = createHospital("서울아산병원", "서울시", "송파구", "2-1");
        hospitalService.saveHospital(hospital1);
        hospitalService.saveHospital(hospital2);

        //when
        List<Hospital> hospitals = hospitalService.findHospitals();

        // then
        assertThat(hospitals.size()).isEqualTo(2);
        for(Hospital hospital : hospitals)
            System.out.println(hospital.getName());
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
