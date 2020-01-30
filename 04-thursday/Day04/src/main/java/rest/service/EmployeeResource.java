package rest.service;

import com.google.gson.Gson;
import dbfacade.EmployeeFacade;
import dto.EmployeeDTO;
import entity.Employee;
import facades.FacadeExample;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("employee")
public class EmployeeResource {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EmployeeFacade facade = new EmployeeFacade(emf);
    Gson gson = new Gson();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployees() {
        List<EmployeeDTO> allEmployees = facade.getAllEmployees();
        return gson.toJson(allEmployees);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeFromId(@PathParam("id") Long id) {
        EmployeeDTO employeeDTOFromId = facade.getEmployeeByID(id);
        return gson.toJson(employeeDTOFromId);
    }

    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String findEmloyeeByName(@PathParam("name") String name) {
        List<EmployeeDTO> employeesFromName = facade.getEmployeesByName(name);
        return gson.toJson(employeesFromName);
    }

    @GET
    @Path("highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public String findHighestPaidEmployee() {
        List<EmployeeDTO> highestPaidEmployees = facade.getEmployeesWithHigestSalary();
        return gson.toJson(highestPaidEmployees);
    }

}
