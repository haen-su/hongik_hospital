package hongikhospital.reservation.service;

import hongikhospital.reservation.domain.Patient;
import hongikhospital.reservation.repository.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.fail;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    PatientService patientService;

    @Test
    public void 회원가입() {
        // given
        Patient patient = new Patient();
        patient.setName("p1");

        //when
        Long patientId = patientService.join(patient);

        // then
        Patient findPatient = patientService.findOne(patientId);
        assertThat(patient).isEqualTo(findPatient);
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원가입() throws Exception {
        // given
        Patient patient1 = new Patient();
        patient1.setName("p1");
        Patient patient2 = new Patient();
        patient2.setName("p1");

        //when
        patientService.join(patient1);
        patientService.join(patient2);

        // then
        fail("예외 발생해야함");
    }
}

