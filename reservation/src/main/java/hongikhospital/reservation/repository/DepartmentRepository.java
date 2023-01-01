package hongikhospital.reservation.repository;

import hongikhospital.reservation.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DepartmentRepository {

    private final EntityManager em;

    public void save(Department department) {
        em.persist(department);
    }

    public List<Department> findAll(Long hospitalId) {
        return em.createQuery("select d from Department d join d.hospital h where h.id = :hospitalId", Department.class)
                .setParameter("hospitalId", hospitalId)
                .getResultList();
    }

    public Department findOne(Long hospitalId, String departmentName) {
        String query = "select d from Department d join d.hospital h where h.id = :hospitalId and d.name = : departmentName";

        return em.createQuery(query, Department.class)
                .setParameter("hospitalId", hospitalId)
                .setParameter("departmentName", departmentName)
                .getSingleResult();
    }

}


