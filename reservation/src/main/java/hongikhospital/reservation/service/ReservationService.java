package hongikhospital.reservation.service;

import hongikhospital.reservation.domain.Doctor;
import hongikhospital.reservation.domain.Patient;
import hongikhospital.reservation.domain.Reservation;
import hongikhospital.reservation.repository.DoctorRepository;
import hongikhospital.reservation.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Long reserve(Long patientId, Long hospitalId, String departmentName, Long doctorId, String date) {
        Patient patient = patientRepository.findOne(patientId);
        Doctor doctor = doctorRepository.findOne(hospitalId, departmentName, doctorId);

        Reservation reservation = Reservation.createReservation(patient, doctor, date);

        reservationRepository.save(reservation);
        return reservation.getId();
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findOne(reservationId);
        reservation.cancel();
    }

    //==예약 목록 조회 for 환자==//
    public List<Reservation> findReservationsForPatient(Long patientId) {
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> findReservations = new ArrayList<>();

        for(Reservation reservation : reservations) {
            if(reservation.getPatient().getId() == patientId)
                findReservations.add(reservation);
        }

        return findReservations;
    }

    //==예약 목록 조회 for 의사==//
    public List<Reservation> findReservationsForDoctor(Long doctorId) {
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> findReservations = new ArrayList<>();

        for(Reservation reservation : reservations) {
            if(reservation.getDoctor().getId() == doctorId)
                findReservations.add(reservation);
        }

        return findReservations;
    }


}

