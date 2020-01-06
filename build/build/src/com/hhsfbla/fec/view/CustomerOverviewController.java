package com.hhsfbla.fec.view;

import java.util.Optional;

import com.hhsfbla.fec.MainApp;
import com.hhsfbla.fec.model.Customer;
import com.hhsfbla.fec.model.Employee;
import com.hhsfbla.fec.util.DateUtil;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * 
 * Displays the basic customer information
 *
 */
public class CustomerOverviewController {
	
	 	@FXML
	    private TableView<Customer> personTable;
	    @FXML
	    private TableColumn<Customer, String> firstNameColumn;
	    @FXML
	    private TableColumn<Customer, String> lastNameColumn;

	    @FXML
	    private Label firstNameLabel;
	    @FXML
	    private Label lastNameLabel;
	    @FXML
	    private Label genderLabel;
	   
	    @FXML
	    private Label ageLabel;
	    
	    @FXML
	    private Label birthdayLabel;
	    
	    @FXML
	    private Label numLabel;
	    
	    
	    @FXML
	    private Label emailLabel;
	    
	    @FXML
	    private Label timeLabel;
	    
	    @FXML
	    private Label dateLabel;
	    
	    
	    
	    private ObservableList<Customer> customers;
	    
	    @FXML 
	    private TextField filterField;
	    

	    // Reference to the main application.
	    private MainApp mainApp;

	    /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	    public CustomerOverviewController() {
	    }

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	        // Initialize the person table with the two columns.
	        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
	        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	        
	     // Clear person details.
	        showPersonDetails(null);

	        // Listen for selection changes and show the person details when changed.
	        personTable.getSelectionModel().selectedItemProperty().addListener(
	                (observable, oldValue, newValue) -> showPersonDetails(newValue));
	        
	    }
	    
	    

	    /**
	     * Is called by the main application to give a reference back to itself.
	     * 
	     * @param mainApp
	     */
	    public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // Add observable list data to the table
	        personTable.setItems(mainApp.getCustomerData());
	    }
	    
	    /**
		 * Activates filter, when user types in textfield, automatically filters.
		 */
		public void activateFilter() {
			customers = mainApp.getCustomerData();
			//Wraps the ObservableList in a FilteredList
			FilteredList<Customer> filteredData = new FilteredList<>(customers, p -> true);

			//Sets the filter Predicate whenever the filter changes
			filterField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(person -> {
					//If filter text is empty, display all members
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					//Compare first name and last name of every person with filter text
					String lowerCaseFilter = newValue.toLowerCase();

					if (person.getFirstName().toLowerCase().contains(lowerCaseFilter)) {
						return true; //Filter matches first name
					} else if (person.getLastName().toLowerCase().contains(lowerCaseFilter)) {
						return true; //Filter matches last name
					}
					return false; //Does not match
				});
			});

			//Wraps the FilteredList in a SortedList
			SortedList<Customer> sortedData = new SortedList<>(filteredData);

			//Binds the SortedList comparator to the TableView comparator
			sortedData.comparatorProperty().bind(personTable.comparatorProperty());

			//Add sorted (and filtered) data to the table
			personTable.setItems(sortedData);
		}
	    
	    /**
	     * Fills all text fields to show details about the person.
	     * If the specified person is null, all text fields are cleared.
	     * 
	     * @param person the person or null
	     */
	    private void showPersonDetails(Customer customer) {
	        if (customer!= null) {
	            // Fill the labels with info from the person object.
	            firstNameLabel.setText(customer.getFirstName());
	            lastNameLabel.setText(customer.getLastName());
	            genderLabel.setText(customer.getGender());
	            ageLabel.setText(Integer.toString(customer.getAge()));
	            numLabel.setText(Integer.toString(customer.getCustID()));
	            emailLabel.setText(customer.getEmail());
	            birthdayLabel.setText(DateUtil.format(customer.getBirthday()));
	            timeLabel.setText(customer.getTime());
	            dateLabel.setText(DateUtil.format(customer.getDate()));
	            
	        } else {
	            // Person is null, remove all the text.
	            firstNameLabel.setText("");
	            lastNameLabel.setText("");
	            genderLabel.setText("");
	            ageLabel.setText("");
	            numLabel.setText("");
	            emailLabel.setText("");
	            birthdayLabel.setText("");
	            timeLabel.setText("");
	            dateLabel.setText("");
	        }
	    }
	    
	    /**
	     * Called when the user clicks on the delete button.
	     */
	    @FXML
	    private void handleDeletePerson() {
	        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
	        if (selectedIndex >= 0) {
	        	 Alert alert = new Alert(AlertType.WARNING, "Are you sure you want to permanently delete this person?", ButtonType.YES, ButtonType.NO);
	         	alert.showAndWait();

	         	if (alert.getResult() == ButtonType.YES) {
	         	    customers.remove(selectedIndex);
	         	
	        	
	        	 } else {
	        		 alert.close();
	        	 }
	        	
	            
	        } else {
	            // Nothing selected.
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("No Selection");
	            alert.setHeaderText("No Person Selected");
	            alert.setContentText("Please select a person in the table.");

	            alert.showAndWait();
	        }
	    }
	    
	    /**
	     * Called when the user clicks the new button. Opens a dialog to edit
	     * details for a new person.
	     */
	    @FXML
	    private void handleNewPerson() {
	        Customer tempPerson = new Customer();
	        boolean okClicked = mainApp.showCustomerEditDialog(tempPerson);
	        if (okClicked) {
	            mainApp.getCustomerData().add(tempPerson);
	        }
	    }

	    /**
	     * Called when the user clicks the edit button. Opens a dialog to edit
	     * details for the selected person.
	     */
	    @FXML
	    private void handleEditPerson() {
	        Customer selectedPerson = personTable.getSelectionModel().getSelectedItem();
	        if (selectedPerson != null) {
	            boolean okClicked = mainApp.showCustomerEditDialog(selectedPerson);
	            if (okClicked) {
	                showPersonDetails(selectedPerson);
	            }

	        } else {
	            // Nothing selected.
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.initOwner(mainApp.getPrimaryStage());
	            alert.setTitle("No Selection");
	            alert.setHeaderText("No Person Selected");
	            alert.setContentText("Please select a person in the table.");

	            alert.showAndWait();
	        }
	    }
	    
	    @FXML
	    private void handleAttendance(){
	    	mainApp.showCustomerReport();
	    }
	    
	    @FXML
	    private void handleStatistics(){
	    	mainApp.showBirthdayStatistics();
	    }
	}


