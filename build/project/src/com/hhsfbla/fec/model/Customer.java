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

public class Customer {
	
	private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty gender;
    private final IntegerProperty age;
    private final ObjectProperty<LocalDate> birthday;
    private final IntegerProperty customerID;
    private final StringProperty email;
    private final StringProperty time;
    private final ObjectProperty<LocalDate> date;
    

    /**
     * Default constructor.
     */
    public Customer() {
        this(0,"", "", "", 0);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Customer(int customerID, String firstName, String lastName, String gender, int age) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.customerID = new SimpleIntegerProperty(customerID);
        this.gender = new SimpleStringProperty(gender);
        this.age = new SimpleIntegerProperty(age);
        

        // Some initial dummy data, just for convenient testing.
        this.email = new SimpleStringProperty(firstName.toLowerCase() + lastName.toLowerCase() + "@gmail.com");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        this.time = new SimpleStringProperty("8 AM");
        this.date = new SimpleObjectProperty<LocalDate>(LocalDate.of(2017, 2, 21));
         
        
        
        
    }

    /**
     * 
     * @return the first name of the customer
     */
	public String getFirstName() {
        return firstName.get();
    }

	/**
	 * Sets the first name of the customer
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
     * @return the last name of the customer
     */
    public String getLastName() {
        return lastName.get();
    }

    /**
     * Sets the last name of the customer
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
     * @return the gender of the customer
     */
    public String getGender(){
    	return gender.get();
    }
    
    /**
     * Sets the gender of the customer
     * @param gender
     */
    public void setGender(String gender){
    	this.gender.set(gender);
    }
    
    /**
     * 
     * @return age of the customer
     */
    public int getAge(){
    	return age.get();
    }
    
    /**
     * Sets the age of the customer
     * @param age
     */
    public void setAge(int age){
    	this.age.set(age);
    }

   
    
    /**
     * 
     * @return the birthday of the customer
     */
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }
    
    /**
     * Sets the birthday of the customer
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
     * @return the date of attendance
     */
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getDate() {
        return date.get();
    }
    
    /**
     * Sets the date of the attendance
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date.set(date);
    }

    /**
     * 
     * @return the property of the date of the attendance
     */
    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    
    /**
     * 
     * @return the customer ID
     */
	public int getCustID() {
		return customerID.get();
	}
	
	/**
	 * Sets the customer ID
	 * @param custID
	 */
	public void setCustID(int custID){
	customerID.set(custID);
	}
	
	
	/**
	 * 
	 * @return the string property of the customer number
	 */
	public StringProperty getCustomerNumberPropertyString(){
		return new SimpleStringProperty(Integer.toString(customerID.get()));
	}
	
	/**
	 * Sets the customer email
	 * @param email
	 */
	public void setEmail(String email){
		this.email.set(email);
	}

	/**
	 * 
	 * @return the email of the customer
	 */
	public String getEmail(){
		return email.get();
	}
	
	/**
	 * Sets the time of attendance
	 * @param time
	 */
	public void setTime(String time){
		this.time.set(time);
	}
	
	/**
	 * 
	 * @return time of attendance
	 */
	public String getTime(){
		return time.get();
	}
	
	/**
	 * 
	 * @return the property of time
	 */
	public StringProperty getTimeProperty(){
		return time;
	}
	
	
}


