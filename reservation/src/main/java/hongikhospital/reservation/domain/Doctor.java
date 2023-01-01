package hongikhospital.reservation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumns ({
            @JoinColumn(name = "department"),
            @JoinColumn(name = "hospital_id")
    })
    private Department department;

    private String name;

    //== 연관관계 메서드==//
    public void setDepartment(Department department) {
        this.department = department;
        department.getDoctorList().add(this);
    }
}
