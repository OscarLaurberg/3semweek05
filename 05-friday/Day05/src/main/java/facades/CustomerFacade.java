package facades;

import dto.CustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class CustomerFacade {

    private static CustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private CustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CustomerFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    
//    CustomerDTO getCustomerByID(int id)
//List<CustomerDTO> getCustomerByName(String name)
//BankCustomer addCustomer(BankCustomer cust)
//List<BankCustomer> getAllBankCustomers()
    
    public List<BankCustomer> getAllBankCustomers(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<BankCustomer> query = em.createQuery("SELECT b FROM BankCustomer b", BankCustomer.class);
            return query.getResultList();
        }finally{
            em.close();
        }
    }
    
    public BankCustomer addCustomer(BankCustomer cust){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        }finally{
            em.close();
        }
    }
    
    public CustomerDTO getCustomerByID(int id){
        EntityManager em = emf.createEntityManager();
        try {
            BankCustomer bankCustomer = em.find(BankCustomer.class,(long)id);
            CustomerDTO customerDTO = new CustomerDTO(bankCustomer);
            return customerDTO;
        }finally{
            em.close();
        }
    }
    
    public List<CustomerDTO> getCustomerByName(String name){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<BankCustomer> query = em.createQuery("SELECT b FROM BankCustomer b WHERE b.firstName like :name", BankCustomer.class);
            query.setParameter("name", name);
            List <CustomerDTO> custDTOFromName = new ArrayList();
            List<BankCustomer> bankCustomerFromName = query.getResultList();
            for (BankCustomer bankCustomer : bankCustomerFromName) {
                custDTOFromName.add(new CustomerDTO(bankCustomer));
            }
            return custDTOFromName;
            
        }finally{
            em.close();
        }
    }
    

}
