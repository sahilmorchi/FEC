package com.hhsfbla.fec.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.hhsfbla.fec.util.LocalDateAdapter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * 
 */
public class Employee {

    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final SimpleStringProperty workShift;
    private final SimpleStringProperty workShiftEnd;
    private final SimpleStringProperty workDay;
    private final ObjectProperty<LocalDate> birthday;
    private final IntegerProperty empID;
    private final StringProperty email;
    
    

    /**
     * Default constructor.
     */
    public Employee() {
        this(0,"", "");
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Employee(int employeeID, String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.empID = new SimpleIntegerProperty(employeeID);

        // Some initial dummy data, just for convenient testing.
        this.email = new SimpleStringProperty(firstName.toLowerCase() + lastName.toLowerCase() + "@gmail.com");
        this.street = new SimpleStringProperty("Homestead");
        this.postalCode = new SimpleIntegerProperty(94087);
        this.city = new SimpleStringProperty("Sunnyvale");
        this.workShift = new SimpleStringProperty("8 am");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 5, 18));
        this.workShiftEnd = new SimpleStringProperty("9 am");
        this.workDay = new SimpleStringProperty("Thursday");
        
        
        
    }

   
    
    
    
    
    
	/**
	 * 
	 * @return the start of the work shift of the employee
	 */
    public String getWorkShift() {
		return workShift.get();
	}
    /**
     * sets the start of the work shift of the employee
     * @param workShift
     */
    public void setWorkShift(String workShift){
    	this.workShift.set(workShift);
    }
    
    /**
	 * 
	 * @return the end of the work shift of the employee
	 */
    public String getWorkShiftEnd() {
		return workShiftEnd.get();
	}
    
    
    /**
     * 
     * @return the property of the start of the work shift
     */
    public StringProperty getShiftStartProperty() {
        return workShift ;
    }
    
    /**
     * 
     * @return the property of the end of the work shift
     */
    public StringProperty getShiftEndProperty() {
        return workShiftEnd;
    }
    /**
     * sets the end of the work shift
     * @param workShiftEnd
     */
    public void setWorkShiftEnd(String workShiftEnd){
    	this.workShiftEnd.set(workShiftEnd);
    }
    
    /**
     *
     * @return work day of the employee
     */
    public String getWorkDay() {
		return workDay.get();
	}
    
    /**
     * Sets the work day of the employee
     * @param workDay
     */
    public void setWorkDay(String workDay){
    	this.workDay.set(workDay);
    }
    
    /**
     * 
     * @return the property of the workDay
     */
    public StringProperty getDayProperty(){
    	return workDay;
    }

    /**
     * 
     * @return the first name of the employee
     */
	public String getFirstName() {
        return firstName.get();
    }

	/**
	 * Sets the first name of the employee
	 * @param firstName
	 */
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    /**
     * 
     * @return the property of the first name
     */
    public StringProperty firstNameProperty() {
        return firstName;
    }

    /**
     *  
     * @return the last name of the employee
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Sets the last name of the employee
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    /**
     * 
     * @return the property of the last name
     */
    public StringProperty lastNameProperty() {
        return lastName;
    }
    
    /**
     * 
     * @return the street name
     */
    public String getStreet() {
        return street.get();
    }

    /**
     * Sets the street name
     * @param street
     */
    public void setStreet(String street) {
        this.street.set(street);
    }

    /**
     * 
     * @return the property of the street
     */
    public StringProperty streetProperty() {
        return street;
    }

    /**
     * 
     * @return the postal code
     */
    public int getPostalCode() {
        return postalCode.get();
    }
    
    /**
     * Sets the postal code
     * @param postalCode
     */
    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    /**
     * 
     * @return the property of the postal code
     */
    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }

    /**
     * 
     * @return the city
     */
    public String getCity() {
        return city.get();
    }

    /**
     * Sets the city
     * @param city
     */
    public void setCity(String city) {
        this.city.set(city);
    }

    /**
     * 
     * @return the property of the city
     */
    public StringProperty cityProperty() {
        return city;
    }

    /**
     * 
     * @return the birthday of the employee
     */
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }
    
    /**
     * Sets the birthday of the employee
     * @param birthday
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    /**
     * 
     * @return the property of the birthday
     */
    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    /**
     * 
     * @return the employee ID
     */
	public int getEmpID() {
		return empID.get();
	}
	
	/**
	 * Sets the employee ID
	 * @param employeeID
	 */
	public void setEmpID(int employeeID){
	empID.set(employeeID);
	}
	
	/**
	 * Sets the member's email
	 * @param email
	 */
	public void setEmail(String email){
		this.email.set(email);
	}

	/**
	 * 
	 * @return the email of the member
	 */
	public String getEmail(){
		return email.get();
	}
	
	/**
	 * 
	 * @return the string property of the customer number
	 */
	public StringProperty getEmployeeNumberPropertyString(){
		return new SimpleStringProperty(Integer.toString(empID.get()));
	}
	
	
}
