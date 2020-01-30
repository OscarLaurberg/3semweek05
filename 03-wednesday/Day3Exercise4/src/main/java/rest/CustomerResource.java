/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dbfacade.CustomerFacade;
import entity.Customer;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author oscar
 */
@Path("customer")
public class CustomerResource {

    @Context
    private UriInfo context;

    EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();
    CustomerFacade facade = new CustomerFacade(emf);
    Gson gson = new Gson();

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    /**
     * Retrieves representation of an instance of rest.CustomerResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCustomers() {
        List<Customer> allCustomers = facade.getAllCustomers();
        return gson.toJson(allCustomers);
    }

    @GET
    @Path("random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandomCostumer() {
        List<Customer> allCustomers = facade.getAllCustomers();
        int noOfCustomers = facade.getNumberOfCustomers();
        Random rand = new Random();
        int randomInt = rand.nextInt(noOfCustomers);
        Customer randomCustomer = allCustomers.get(randomInt);
        return gson.toJson(randomCustomer);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerFromId(@PathParam("id") int id) {
        Customer customerFromID = facade.findCustomer(id);
        return gson.toJson(customerFromID);
    }

    /**
     * PUT method for updating or creating an instance of CustomerResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
