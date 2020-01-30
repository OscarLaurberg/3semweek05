/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import entities.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author oscar
 */
@Path("animal")
public class AnimalResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AnimalResource
     */
    public AnimalResource() {
    }

    public ArrayList animals() {
        ArrayList<Animal> animals = new ArrayList();
        Animal dog = new Animal("Dog", 2019, "bark");
        animals.add(dog);
        Animal cow = new Animal("Cow", 2015, "Muuh");
        animals.add(cow);
        Animal crow = new Animal("Crow", 2017, "Krakraaa");
        animals.add(crow);
        Animal pig = new Animal("Pig", 2014, "Oink");
        animals.add(pig);
        
        return animals;
        
    }

    /**
     * Retrieves representation of an instance of rest.AnimalResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "Hello mothafuckas";

    }

    /**
     * PUT method for updating or creating an instance of AnimalResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String newEndPoint() {
        ArrayList<Animal> animals = animals();
        Random rand = new Random();
        int randomInt = rand.nextInt(4);
        Animal randomAnimal = animals.get(randomInt);
        
        return new Gson().toJson(randomAnimal);

    }
}
