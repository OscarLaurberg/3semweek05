/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dbfacade.CustomerFacade;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author oscar
 */
public class EntityTested {

    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        CustomerFacade facade = new CustomerFacade(emf);

        try {
            em.getTransaction().begin();
            Customer customer1 = new Customer("Alexander", "Løve");
            Customer customer2 = new Customer("Mads", "Mus");
            em.persist(customer1);
            em.persist(customer2);
            em.getTransaction().commit();
            System.out.println("Customer: " + facade.findByLastName("Mus"));
            System.out.println(facade.getNumberOfCustomers());
            System.out.println(facade.getAllCustomers().size());

        } finally {
            em.close();
        }

    }
}
