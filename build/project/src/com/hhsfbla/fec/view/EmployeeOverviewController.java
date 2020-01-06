package com.hhsfbla.fec.view;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Optional;

import com.hhsfbla.fec.MainApp;
import com.hhsfbla.fec.model.Employee;
import com.hhsfbla.fec.util.DateUtil;



/**
 * 
 * Displays the basic employee information
 *
 */
public class EmployeeOverviewController {
	
    @FXML
    private TableView<Employee> personTable;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    
    

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    
    @FXML
    private Label workShiftLabel;
    
    @FXML
    private Label numLabel;
    
    @FXML
    private Label emailLabel;
    
    private ObservableList<Employee> employees;
    
    @FXML 
    private TextField filterField;
    
    
    

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public EmployeeOverviewController() {
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
        personTable.setItems(mainApp.getPersonData());
    }
    
    /**
	 * Activates filter, when user types in textfield, automatically filters.
	 */
	public void activateFilter() {
		employees = mainApp.getPersonData();
		//Wraps the ObservableList in a FilteredList
		FilteredList<Employee> filteredData = new FilteredList<>(employees, p -> true);

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
		SortedList<Employee> sortedData = new SortedList<>(filteredData);

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
    private void showPersonDetails(Employee person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
            workShiftLabel.setText(person.getWorkShift());
            numLabel.setText(Integer.toString(person.getEmpID()));
            emailLabel.setText(person.getEmail());
            
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
            workShiftLabel.setText("");
            numLabel.setText("");
            emailLabel.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
    	
    	
    	
    	
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	/*
        	Alert alert = new Alert(AlertType.WARNING, ButtonType.OK, ButtonType.CANCEL);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Are you sure?");
            alert.setContentText("Person will be permanently deleted");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                employees.remove(selectedIndex);
            }
            */
            
            Alert alert = new Alert(AlertType.WARNING, "Are you sure you want to permanently delete this person?", ButtonType.YES, ButtonType.NO);
        	alert.showAndWait();

        	if (alert.getResult() == ButtonType.YES) {
        	    employees.remove(selectedIndex);
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
        Employee tempPerson = new Employee();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Employee selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
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
    
    /**
     * Opens schedule information.
     */
    @FXML
    private void handleSchedule(){
    	mainApp.showScheduleOverview();
    }
    
   
    
    
}