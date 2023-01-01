package hongikhospital.reservation.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@IdClass(PushLogPK.class)
public class Department {

    @Id
    private String name;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;


    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Doctor> doctorList = new ArrayList<>();

    private String tel;

    //== 연관관계 메서드 ==//
    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
        hospital.getDepartmentList().add(this);
    }

}
