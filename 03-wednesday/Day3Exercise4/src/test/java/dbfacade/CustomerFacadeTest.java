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
import org.eclipse.persistence.sessions.DatabaseSession;
import org.eclipse.persistence.tools.schemaframework.SchemaManager;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author oscar
 */
public class CustomerFacadeTest {

    private static final EntityManagerFactory EMF
            = Persistence.createEntityManagerFactory("pu");
    private static final EntityManager EM = EMF.createEntityManager();
    private static final CustomerFacade FACADE = new CustomerFacade(EMF);

    private static final SchemaManager SM = new SchemaManager(EM.unwrap(DatabaseSession.class));

    public CustomerFacadeTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        //Setup employees
        int numberOfCustomers = 10;
        String firstName = "name";
        String lastName = "lastName";

        while (numberOfCustomers > 0) {
            FACADE.addCustomer(firstName, lastName);
            numberOfCustomers--;
        }

    }

    @AfterClass
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        SM.dropTable("customer");
        EM.close();
        EMF.close();
    }

    @Test
    public void testGetAllCustomers() {
        int expected = 10;
        int result = FACADE.getAllCustomers().size();
        assertEquals(expected, result);
    }

    @Test
    public void testGetNumberOfCustomers() {
        int expected = 10;
        int result = FACADE.getNumberOfCustomers();
        assertEquals(expected, result);
    }


    @Test
    public void testFindCustomer() {
        Customer result = FACADE.findCustomer(1);
        String firstNameResult = result.getFirstName();
        String lastNameResult = result.getLastName();
        String firstNameExpected = "name";
        String lastNameExpected = "lastName";
        assertEquals(firstNameExpected, firstNameResult);
        assertEquals(lastNameExpected, lastNameResult);
    }

    @Test
    public void testFindByLastName() {
        String lastName = "lastName";
        String expected = "name";
        String result = FACADE.findByLastName(lastName).get(0).getFirstName();
        assertEquals(expected, result);

    }

}
