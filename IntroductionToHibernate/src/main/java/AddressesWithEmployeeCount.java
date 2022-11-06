import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.createQuery("from Address a order by a.employees.size desc", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
