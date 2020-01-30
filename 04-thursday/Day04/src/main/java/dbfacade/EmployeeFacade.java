/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import dto.EmployeeDTO;
import entity.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author oscar
 */
public class EmployeeFacade {
    private EntityManagerFactory emf;

    public EmployeeFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Employee createEmployee(String name, String address, int salary) {
        Employee employee = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return employee;
        } finally {
            em.close();
        }
    }

    public EmployeeDTO getEmployeeByID(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            EmployeeDTO empDTOFromID = new EmployeeDTO(employee);
            return empDTOFromID;
        } finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = 
                    em.createQuery("SELECT e FROM Employee e", Employee.class);
            List<EmployeeDTO> allEmpDTOs = new ArrayList();
            List<Employee> allEmployees = query.getResultList();
            for (Employee employee : allEmployees) {
                allEmpDTOs.add(new EmployeeDTO(employee));
            }
            return allEmpDTOs;
        } finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getEmployeesByName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.name like :name", Employee.class);
            query.setParameter("name", name);
            List<EmployeeDTO> empDTOsFromName = new ArrayList();
            List<Employee> employeesFromName = query.getResultList();
            for (Employee employee : employeesFromName) {
                empDTOsFromName.add(new EmployeeDTO(employee));
            }
            return empDTOsFromName;
        } finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getEmployeesWithHigestSalary() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)");
            List<EmployeeDTO> empDTO = new ArrayList();
            List<Employee> highestPaidEmp = query.getResultList();
            highestPaidEmp.forEach(employee -> empDTO.add(new EmployeeDTO(employee)));
            return empDTO;
            
        } finally {
            em.close();
        }

    }
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EmployeeFacade facade = new EmployeeFacade(emf);
        System.out.println(facade.getAllEmployees());
        System.out.println(facade.getEmployeesWithHigestSalary().get(0));
        facade.createEmployee("John Johnson", "Gade", 0);  
        System.out.println(facade.getEmployeeByID(29L));
        
       
        
    }
}
//    Create an Employee facade class that can:
//getEmployeeById
//getEmployeesByName
//getAllEmployees
//getEmployeesWithHighestSalary
//createEmployee

