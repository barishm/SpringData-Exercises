import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class FindLatest10Projects {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("from Project p order by p.startDate desc", Project.class)
                .setMaxResults(10)
                .getResultList().stream().sorted(Comparator.comparing(Project::getName))
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
