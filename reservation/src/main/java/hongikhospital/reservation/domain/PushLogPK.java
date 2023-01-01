package hongikhospital.reservation.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class PushLogPK implements Serializable {
    private String name;
    private Long hospital;

    protected PushLogPK() { }

    public PushLogPK(String name, Long hospital) {
        this.name = name;
        this.hospital = hospital;
    }
}

