import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<String> departments = Arrays.asList("Engineering", "Tool Design", "Marketing", "Information Services");

        entityManager.
                createQuery("select e from Employee e where e.department.name in ( :departments ) ", Employee.class).
                setParameter("departments", departments).
                getResultList().
                forEach(e -> {
                    e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    entityManager.persist(e);
                });
        entityManager.
                createQuery("select e from Employee e where e.department.name in ( :departments )", Employee.class).
                setParameter("departments", departments).
                getResultList().
                forEach(e -> System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
