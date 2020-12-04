import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =  new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em  = emf.createEntityManager();
        em.getTransaction().begin();
        /**
         * Какие товары купил клиент
         */

        List<Product> products = em.createQuery("from Product p", Product.class).getResultList();
        products.forEach(System.out::println);
        em.close();

    }
}
