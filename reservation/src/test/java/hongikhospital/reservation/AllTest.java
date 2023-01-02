package hongikhospital.reservation;

import hongikhospital.reservation.domain.*;
import hongikhospital.reservation.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AllTest {

    @Autowired
    PatientService patientService;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    TreatmentService treatmentService;

    @Test
    @Rollback(value = false)
    public void ALL_TEST() {
        Hospital hospital1 = createHospital("세브란스병원", "서울시", "서대문구", "1-1");
        Hospital hospital2 = createHospital("서울아산병원", "서울시", "송파구", "2-1");
        hospitalService.saveHospital(hospital1);
        hospitalService.saveHospital(hospital2);

        Department department1 = createDepartment("성형외과", hospital1, "02-0000-0001");
        Department department2 = createDepartment("정신과", hospital1, "02-0000-0002");

        Department department3 = createDepartment("내과", hospital2, "02-1000-0000");
        Department department4 = createDepartment("정형외과", hospital2, "02-2000-0001");

        departmentService.saveDepartment(department1);
        departmentService.saveDepartment(department2);
        departmentService.saveDepartment(department3);
        departmentService.saveDepartment(department4);

        Doctor doctor1 = createDoctor("김현수", department1);
        Doctor doctor2 = createDoctor("김현민", department1);
        Doctor doctor3 = createDoctor("임채리", department2);
        Doctor doctor4 = createDoctor("이찬희", department2);
        Doctor doctor5 = createDoctor("김슬기", department3);
        Doctor doctor6 = createDoctor("김재승", department3);
        Doctor doctor7 = createDoctor("김정원", department4);
        Doctor doctor8 = createDoctor("강혜민", department4);

        doctorService.saveDoctor(doctor1);
        doctorService.saveDoctor(doctor2);
        doctorService.saveDoctor(doctor3);
        doctorService.saveDoctor(doctor4);
        doctorService.saveDoctor(doctor5);
        doctorService.saveDoctor(doctor6);
        doctorService.saveDoctor(doctor7);
        doctorService.saveDoctor(doctor8);

        Patient patient1 = createPatient("권은혜", 24, Gender.Female);
        Patient patient2 = createPatient("임가현", 24, Gender.Female);
        Patient patient3 = createPatient("권은", 24, Gender.Female);
        Patient patient4 = createPatient("박시영", 25, Gender.Female);

        patientService.join(patient1);
        patientService.join(patient2);
        patientService.join(patient3);
        patientService.join(patient4);

        Reservation reservation1 = createReservation(patient1, doctor1, "2023-01-02 12:00");
        Reservation reservation2 = createReservation(patient2, doctor2, "2023-01-02 14:00");
        Reservation reservation3 = createReservation(patient3, doctor4, "2023-01-03 11:00");
        Reservation reservation4 = createReservation(patient4, doctor3, "2023-01-03 10:00");

        reservationService.reserve(reservation1);
        reservationService.reserve(reservation2);
        reservationService.reserve(reservation3);
        reservationService.reserve(reservation4);

        // 예약 취소
        reservationService.cancelReservation(reservation3.getId());

        Treatment treatment1 = createTreatment(patient1, doctor3, LocalDateTime.now(), 10000);
        Treatment treatment2 = createTreatment(patient1, doctor4, LocalDateTime.now(), 5000);
        Treatment treatment3 = createTreatment(patient2, doctor1, LocalDateTime.now(), 8700);
        Treatment treatment4 = createTreatment(patient3, doctor3, LocalDateTime.now(), 12000);
        Treatment treatment5 = createTreatment(patient3, doctor2, LocalDateTime.now(), 6700);
        Treatment treatment6 = createTreatment(patient4, doctor1, LocalDateTime.now(), 23000);

        treatmentService.saveTreatment(treatment1);
        treatmentService.saveTreatment(treatment2);
        treatmentService.saveTreatment(treatment3);
        treatmentService.saveTreatment(treatment4);
        treatmentService.saveTreatment(treatment5);
        treatmentService.saveTreatment(treatment6);
        
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
