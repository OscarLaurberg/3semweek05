/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author oscar
 */
public class MakeTestData {
    
      static EntityManagerFactory emf;

//    private String firstName;
//    private String lastName;
//    private String accountNumber;
//    private double balance;
//    private int customerRanking;
//    private String internalInfo;
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        BankCustomer bc1 = new BankCustomer("Alexander","Løve","1",1000,5,"Noob");
        BankCustomer bc2 = new BankCustomer("Mads","Mus","2",500,4,"Noob");

        try {
            em.getTransaction().begin();
            em.persist(bc1);
            em.persist(bc2);
            em.getTransaction().commit();
        } finally {
            em.close();

        }
    }

}
