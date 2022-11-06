import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Scanner scan = new Scanner(System.in);
        String startsWith = scan.nextLine();

        entityManager.
                createQuery("from Employee e where e.firstName like :startsWith", Employee.class).
                setParameter("startsWith", startsWith + "%").
                getResultList().
                forEach(e -> System.out.println(e.salaryInfo()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
