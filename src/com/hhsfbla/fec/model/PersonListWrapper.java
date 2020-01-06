package com.hhsfbla.fec.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * 
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Employee> persons;
    private List<Customer> customers;
    
    /**
     * 
     * @return the list of employees
     */
    @XmlElement(name = "person")
    public List<Employee> getPersons() {
        return persons;
    }
   
    /**
     * Sets the list of employees
     * @param persons
     */
    public void setPersons(List<Employee> persons) {
        this.persons = persons;
    }
    
    /**
     * 
     * @return the list of customers
     */
    @XmlElement(name = "customer")
    public List<Customer> getCustomers(){
    	return customers;
    }
    
    /**
     * Sets the list of customers
     * @param customers
     */
    public void setCustomers(List<Customer> customers){
    	this.customers = customers;
    }
}
