/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author oscar
 */
public class EmployeeFacadeTest {
    
    private static final EntityManagerFactory EMF
            = Persistence.createEntityManagerFactory("pu");
    private static final EntityManager EM = EMF.createEntityManager();
    private static final EmployeeFacade FACADE = new EmployeeFacade(EMF);
    

//    
//    @Test
//    public void testGetEmployeesWithHighestSalary(){
//        List<Employee>highestPaidEmployees = FACADE.getEmployeesWithHigestSalary();
//        int expected = 2000;
//        int result = highestPaidEmployees.get(0).getSalary();
//        Assertions.assertEquals(expected,result);
//    }
    
}
