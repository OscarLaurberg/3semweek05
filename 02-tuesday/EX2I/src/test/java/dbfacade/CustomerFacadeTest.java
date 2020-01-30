/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author oscar
 */
public class CustomerFacadeTest {

    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();
    CustomerFacade facade = new CustomerFacade(emf);

    @Before
    public void init() {

        try {
            em.getTransaction().begin();
            Customer customer1 = new Customer("Alexander", "Løve");
            Customer customer2 = new Customer("Mads", "Mus");
            em.persist(customer1);
            em.persist(customer2);
            em.getTransaction().commit();

        } finally {
            em.close();
        }

    }
    
    @Test
    public void testGetAllCustomers(){
        int expected = 2;
        int result = facade.getAllCustomers().size();
        assertEquals(expected, result);
    }

    @Test
    public void testGetNumberOfCustomers(){
        int expected = 2;
        int result = facade.getNumberOfCustomers();
        assertEquals(expected, result);
    }
    
    @Test
    public void testAddCustomer() {
        int expected = 3;
        Customer customer3 = new Customer("John", "Johnson");
        int result = facade.getNumberOfCustomers();
        assertEquals(expected, result);
    }

    @Test
    public void testFindCustomer(){
        Customer result = facade.findCustomer(1);
        String firstNameResult = result.getFirstName();
        String lastNameResult = result.getLastName();
        String firstNameExpected = "Mads";
        String lastNameExpected = "Mus";
        assertEquals(firstNameExpected, firstNameResult);
        assertEquals(lastNameExpected, lastNameResult);
    }
    
    @Test
    public void testFindByLastName(){
        String lastName = "Mus";
        String expected = "Mads";
        String result = facade.findByLastName(lastName).get(0).getFirstName();
        assertEquals(expected,result);
        
    }
}
