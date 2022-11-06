import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("select e.department.name, max(e.salary) from Employee e group by e.department.name having max(e.salary) not between 30000 and 70000", Object[].class)
                        .getResultList().
                        forEach(o -> System.out.println(o[0] + " " +o[1]));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
