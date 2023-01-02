package hongikhospital.reservation.repository;

import hongikhospital.reservation.domain.Reservation;
import hongikhospital.reservation.domain.Treatment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TreatmentRepository {

    private final EntityManager em;

    public void save(Treatment treatment) {
        em.persist(treatment);
    }

    public Treatment findOne(Long id) {
        return em.find(Treatment.class, id);
    }

    public List<Treatment> findAll() {
        return em.createQuery("select t from Treatment t", Treatment.class)
                .getResultList();
    }

}
