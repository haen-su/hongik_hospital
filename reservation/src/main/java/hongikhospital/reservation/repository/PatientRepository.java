package hongikhospital.reservation.repository;

import hongikhospital.reservation.domain.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatientRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Patient patient) {
        em.persist(patient);
    }

    public Patient findOne(Long id) {
        return em.find(Patient.class, id);
    }

    public List<Patient> findAll() {
        return em.createQuery("select p from Patient p", Patient.class)
                .getResultList();
    }

    public List<Patient> findByName(String name) {
        return em.createQuery("select p from Patient p where p.name= :name", Patient.class)
                .setParameter("name", name)
                .getResultList();
    }

}
