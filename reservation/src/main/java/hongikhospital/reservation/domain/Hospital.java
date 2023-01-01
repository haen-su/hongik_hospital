package hongikhospital.reservation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hospital_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
    private List<Department> departmentList = new ArrayList<>();

    @Embedded
    private Address address;

}
