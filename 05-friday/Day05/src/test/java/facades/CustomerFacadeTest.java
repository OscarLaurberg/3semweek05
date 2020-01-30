/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
////import org.junit.jupiter.api.Test;
//import static org.junit.Assert.*;

/**
 *
 * @author thomas
 */
public class CustomerFacadeTest {
    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("pu");
    private static final CustomerFacade FE = CustomerFacade.getFacadeExample(ENF);
    public CustomerFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
//        Add code to setup entities for test before running any test methods
    }
    
    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }
    
    @BeforeEach
    public void setUp() {
//        Put the test database in a proper state before each test is run
    }
    
    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    /**
     * Test a method here.
     */
    
    
    @Test
    public void testGetAllBankCustomers(){
       boolean isNotEmpty = !FE.getAllBankCustomers().isEmpty();
       assertEquals(isNotEmpty,true);        
    }
    
    @Test
    public void testGetCustomerByName(){
        String firstName = "Mads";
        String expected = "Mads Mus";
        List<CustomerDTO> custDTOFromName = FE.getCustomerByName(firstName);
        assertEquals(expected, custDTOFromName.get(0).getFullName());
    }
    
    @Test
    public void testAddCustomer(){
        BankCustomer cust = new BankCustomer("Larsi", "Lone", "100", 10, 3, "Klammert");
        int expected = FE.getAllBankCustomers().size()+1;
        FE.addCustomer(cust);
        int result = FE.getAllBankCustomers().size();
        
        assertEquals(expected,result);
    }
    
    @Test
    public void testGetCustomerByID(){
        String expected = "Mads Mus";
        String result = FE.getCustomerByID(1).getFullName();
        assertEquals(expected, result);
    }
    
}
