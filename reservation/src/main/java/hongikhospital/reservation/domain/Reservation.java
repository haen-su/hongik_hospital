package hongikhospital.reservation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String reserveDate;

    private ReservationStatus status;

    //==생성 메서드==//
    public static Reservation createReservation(Patient patient, Doctor doctor, String date) {
        Reservation reservation = new Reservation();
        reservation.setPatient(patient);
        reservation.setDoctor(doctor);
        reservation.setReserveDate(date);
        reservation.setStatus(ReservationStatus.RESERVE);

        return reservation;
    }

    //==비즈니스 로직==//
    // 예약 취소
    public void cancel() {
        this.setStatus(ReservationStatus.CANCEL);
        this.setReserveDate(null);
    }

}
