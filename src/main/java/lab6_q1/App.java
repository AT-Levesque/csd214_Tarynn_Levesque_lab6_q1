package lab6_q1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public App() {
        // Initialize the EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void run() {
        try {
            // create the database if it doesn't exist
            createDatabase();

            dropTable();

            createTable();

            populateTable();

            viewTable();

        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close the EntityManager and EntityManagerFactory
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    private void createDatabase() {
        // No need to explicitly create the database in JPA
    }

    private void viewTable() {
        try {
            List<CD> cds = entityManager.createQuery("SELECT c FROM CD c", CD.class).getResultList();
            for (CD cd : cds) {
                System.out.println(cd.getCdName() + "(" + cd.getId() + "): " + cd.getCdName() + cd.getBandName());
            }
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void dropTable() {
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE FROM CD").executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            entityManager.getTransaction().rollback();
        }
    }

    private void populateTable() {
        try {
            entityManager.getTransaction().begin();

            entityManager.persist(new CD("5150", "Van Halen"));
            entityManager.persist(new CD("1984", "Van Halen"));
            entityManager.persist(new CD("Back in Black", "AC/DC"));
            entityManager.persist(new CD("Full Bluntal Nugity", "Ted Nugent"));

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            entityManager.getTransaction().rollback();
        }
    }

    private void createTable() {
        // JPA will automatically generate the table based on the CD entity
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
