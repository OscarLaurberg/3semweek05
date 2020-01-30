/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author oscar
 */
public class CustomerFacade {
     private EntityManagerFactory emf;


    public CustomerFacade(EntityManagerFactory emf) {
    this.emf=emf;
    }
  /*  
Customer findByID(int id);
List<Customer> findByLastName(String name);
int getNumberOfCustomers();
List<Customer> allCustomers();
Customer addCustomer(String fName, String lName);
*/
    
    public Customer addCustomer(String firstName, String lastName){
        Customer customer = new Customer(firstName, lastName);
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        }finally {
            em.close();
        }
    }
    
    public Customer findCustomer(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer customer = em.find(Customer.class,id);
            return customer;
        }finally {
            em.close();
        }
    }
    public List<Customer> getAllCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select c from Customer c",Customer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    public List<Customer> findByLastName (String lastName){
        EntityManager em = emf.createEntityManager();
        try{
          TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.lastName like :lastName",Customer.class);
          query.setParameter("lastName", lastName);
          return query.getResultList();
 
        }finally{
            em.close();
        }
    }
        
       public int getNumberOfCustomers(){
           EntityManager em = emf.createEntityManager();
           try{
               Query query = em.createQuery("SELECT count(c) FROM Customer c");
               return Integer.parseInt(query.getSingleResult().toString());
           }finally{
               em.close();
           }
            
        }
       
    }


