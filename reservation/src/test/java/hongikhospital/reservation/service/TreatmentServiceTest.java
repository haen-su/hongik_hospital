package hongikhospital.reservation.service;

import hongikhospital.reservation.domain.*;
import hongikhospital.reservation.repository.DoctorRepository;
import hongikhospital.reservation.repository.PatientRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TreatmentServiceTest {

    @Autowired
    TreatmentService treatmentService;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 예약_생성() {
        // given
        Hospital hospital = createHospital("서울아산병원", null, null, null);
        em.persist(hospital);

        Department department = createDepartment("외과", hospital, null);
        em.persist(department);

        Patient patient = createPatient("김현수", 23, null);
        patientRepository.save(patient);

        Doctor doctor = new Doctor();
        doctor.setDepartment(department);
        doctor.setName("김현민");
        doctorRepository.save(doctor);


        //when
        Treatment treatment = createTreatment(patient, doctor, LocalDateTime.now(), 10000);
        Long treatmentId = treatmentService.saveTreatment(treatment);
        Treatment findTreatment = treatmentService.findOne(treatmentId);

        // then
        Assertions.assertThat(findTreatment.getId()).isEqualTo(treatmentId);
        Assertions.assertThat(findTreatment.getPatient()).isEqualTo(patient);
        Assertions.assertThat(findTreatment.getDoctor()).isEqualTo(doctor);

        System.out.println(findTreatment.getDoctor().getDepartment().getHospital().getName() + ", " +
                findTreatment.getDoctor().getDepartment().getName() + ", " + findTreatment.getDoctor().getName()
                + ", " + findTreatment.getPatient().getName() + ", " + findTreatment.getTreatDate());

    }

    @Test
    public void 예약_목록_조회_for_환자() {
        // given
        Hospital hospital = createHospital("서울아산병원", null, null, null);
        em.persist(hospital);

        Department department1 = createDepartment("외과", hospital, null);
        em.persist(department1);
        Department department2 = createDepartment("내과", hospital, null);
        em.persist(department2);

        Patient patient1 = createPatient("김현수", 24, null);
        patientRepository.save(patient1);
        Patient patient2 = createPatient("김현민", 22, null);
        patientRepository.save(patient2);


        Doctor doctor = createDoctor("김씨", department1);
        doctorRepository.save(doctor);

        //when
        Treatment treatment1 = createTreatment(patient1, doctor, LocalDateTime.now(), 10000);
        Treatment treatment2 = createTreatment(patient2, doctor, LocalDateTime.now(), 10000);
        treatmentService.saveTreatment(treatment1);
        treatmentService.saveTreatment(treatment2);

        List<Treatment> treatments = treatmentService.findTreatmentsForPatient(patient1.getId());

        // then
        Assertions.assertThat(treatments.size()).isEqualTo(1);

        for(Treatment treatment : treatments)
            System.out.println(treatment.getDoctor().getDepartment().getHospital().getName() + ", " +
                    treatment.getDoctor().getDepartment().getName() + ", " + treatment.getDoctor().getName()
                    + ", " + treatment.getPatient().getName() + ", " + treatment.getTreatDate());
    }

    @Test
    public void 예약_목록_조회_for_의사() {
        // given
        Hospital hospital = createHospital("서울아산병원", null, null, null);
        em.persist(hospital);

        Department department1 = createDepartment("외과", hospital, null);
        em.persist(department1);
        Department department2 = createDepartment("내과", hospital, null);
        em.persist(department2);

        Patient patient1 = createPatient("김현수", 24, null);
        patientRepository.save(patient1);
        Patient patient2 = createPatient("김현민", 22, null);
        patientRepository.save(patient2);


        Doctor doctor = createDoctor("김씨", department1);
        doctorRepository.save(doctor);

        //when
        Treatment treatment1 = createTreatment(patient1, doctor, LocalDateTime.now(), 10000);
        Treatment treatment2 = createTreatment(patient2, doctor, LocalDateTime.now(), 10000);
        treatmentService.saveTreatment(treatment1);
        treatmentService.saveTreatment(treatment2);

        List<Treatment> treatments = treatmentService.findTreatmentsForDoctor(doctor.getId());

        // then
        Assertions.assertThat(treatments.size()).isEqualTo(2);

        for(Treatment treatment : treatments)
            System.out.println(treatment.getDoctor().getDepartment().getHospital().getName() + ", " +
                    treatment.getDoctor().getDepartment().getName() + ", " + treatment.getDoctor().getName()
                    + ", " + treatment.getPatient().getName() + ", " + treatment.getTreatDate());
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

    public Doctor createDoctor(String name, Department department) {
        Doctor doctor = new Doctor();
        doctor.setDepartment(department);
        doctor.setName(name);

        return doctor;
    }

    public Patient createPatient(String name, int age, Gender gender) {
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);

        return patient;
    }

    public Reservation createReservation(Patient patient, Doctor doctor, String date) {
        Reservation reservation = new Reservation();
        reservation.setPatient(patient);
        reservation.setDoctor(doctor);
        reservation.setReserveDate(date);
        reservation.setStatus(ReservationStatus.RESERVE);

        return reservation;
    }

    public Treatment createTreatment(Patient patient, Doctor doctor, LocalDateTime date, int fee) {
        Treatment treatment = new Treatment();
        treatment.setPatient(patient);
        treatment.setDoctor(doctor);
        treatment.setTreatDate(date);
        treatment.setFee(fee);

        return treatment;
    }
}
