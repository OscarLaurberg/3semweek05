/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Employee;

/**
 *
 * @author oscar
 */
public class EmployeeDTO {
    
//    
//    Create only id, name and address fields and create a constructor that takes an 
//            Employee object as argument and sets data for its own fields.
    
    private Long id;
    private String name;
    private String address;

    public EmployeeDTO(Employee employee) {
        this.id=employee.getId();
        this.name=employee.getName();
        this.address=employee.getAddress();
    }
    
    
}
