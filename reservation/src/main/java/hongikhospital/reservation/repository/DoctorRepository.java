package hongikhospital.reservation.repository;

import hongikhospital.reservation.domain.Department;
import hongikhospital.reservation.domain.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DoctorRepository {

    private final EntityManager em;

    public void save(Doctor doctor) {
        em.persist(doctor);
    }


    public List<Doctor> findAll(Long hospitalId, String departmentName) {
        String query = "select dr from Doctor dr join dr.department de where de.hospital.id = :hospitalId and dr.department = :departmentName";

        return em.createQuery(query, Doctor.class)
                .setParameter("hospitalId", hospitalId)
                .setParameter("departmentName", departmentName)
                .getResultList();
    }

    public Doctor findOne(Long hospitalId, String departmentName, Long doctorId) {
        String query = "select dr from Doctor dr join dr.department de where de.hospital.id = :hospitalId and de.name = :departmentName " +
                "and dr.id = :doctorId";

        return em.createQuery(query, Doctor.class)
                .setParameter("hospitalId", hospitalId)
                .setParameter("departmentName", departmentName)
                .setParameter("doctorId", doctorId)
                .getSingleResult();
    }

    public List<Doctor> findByName(Long hospitalId, String departmentName, String doctorName) {
        String query = "select dr from Doctor dr join dr.department de where de.hospital.id = :hospitalId and de.name = :departmentName " +
                "and dr.name = :doctorName";

        return em.createQuery(query, Doctor.class)
                .setParameter("hospitalId", hospitalId)
                .setParameter("departmentName", departmentName)
                .setParameter("doctorName", doctorName)
                .getResultList();
    }

}
