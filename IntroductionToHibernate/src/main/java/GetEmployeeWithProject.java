import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmployeeWithProject {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scan = new Scanner(System.in);
        int id = Integer.parseInt(scan.nextLine());

        Employee employee = entityManager.find(Employee.class, id);

        System.out.println(employee);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
