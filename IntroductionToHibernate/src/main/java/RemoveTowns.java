import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scan = new Scanner(System.in);
        String town = scan.nextLine();

        List<Address> addresses = entityManager.createQuery("select a from Address a where a.town.name = :town", Address.class).
                setParameter("town", town).
                getResultList();

        int addressesCount = addresses.size();

        if(addressesCount == 0){
            System.out.println("No such town");
            entityManager.close();
            return;
        }

        addresses.forEach(a -> {
            a.getEmployees().forEach(e -> e.setAddress(null));
            entityManager.remove(a);
        });

        Town getTown = entityManager.
                createQuery("select t from Town t where t.name = :town", Town.class)
                .setParameter("town", town)
                .getSingleResult();

        entityManager.remove(getTown);
        String address = addressesCount == 1 ? "address" : "addresses";
        System.out.println(addressesCount + " " + address + " in " + town + " deleted");

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
