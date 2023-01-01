package hongikhospital.reservation.service;

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
        Hospital hospital = new Hospital();
        hospital.setName("서울아산병원");

        //when
        Long id = hospitalService.saveHospital(hospital);
        Hospital findHospital = hospitalService.findOne(id);

        // then
        assertThat(hospital).isEqualTo(findHospital);
    }

    @Test
    public void 병원_목록_조회() {
        // given
        Hospital hospital1 = new Hospital();
        hospital1.setName("서울아산병원");
        Hospital hospital2 = new Hospital();
        hospital2.setName("연세세브란스병원");
        hospitalService.saveHospital(hospital1);
        hospitalService.saveHospital(hospital2);

        //when
        List<Hospital> hospitals = hospitalService.findHospitals();

        // then
        assertThat(hospitals.size()).isEqualTo(2);
        for(Hospital hospital : hospitals)
            System.out.println(hospital.getName());
    }
}
