package ru.geekbrains;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();
        // Из фабрики создаем EntityManager
        EntityManager em = factory.createEntityManager();

        /**
         * Какие товары купил клиент c id = 2
         */

        Customer customer = em.find(Customer.class, 2L);
        List<Product> productsOfCustomer = customer.getProducts();
        productsOfCustomer.forEach(prod ->{
            System.out.println(prod.getName());
        });


        /**
         * Кто купил Apple Ipad (id = 3)
         */

        Product appleIpad = em.find(Product.class, 3L);
        List<Customer> customerOfIpad = appleIpad.getCustomers();
        customerOfIpad.forEach(custom->{
            System.out.println(custom.getName());
        });

        /**
         * Удалим товар с id = 11 и покупателя с id = 7
         */

        em.getTransaction().begin();
        int result =  em.createQuery("delete Product where id = :id")
            .setParameter("id", 11L)
            .executeUpdate();
        em.getTransaction().commit();
        em.getTransaction().begin();
        em.createQuery("delete Customer where id = :ID")
            .setParameter("ID", 7L)
            .executeUpdate();
        em.getTransaction().commit();


        em.close();
    }
}
