package com.hhsfbla.fec.view;

import com.hhsfbla.fec.MainApp;
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
import javafx.scene.control.ComboBox;

/**
 * 
 * View information on an employee's work schedule
 *
 */
public class WorkScheduleController {
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
	    private Label workShiftLabel;
	    
	    @FXML
	    private Label workShiftEndLabel;
	    
	    @FXML
	    private Label workDayLabel;
	    
	    
	    
	    private ObservableList<Employee> employees;
	    
	    @FXML 
	    private TextField filterField;
	    

	    // Reference to the main application.
	    private MainApp mainApp;

	    /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	    public WorkScheduleController() {
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
	            workShiftLabel.setText(person.getWorkShift());
	            workShiftEndLabel.setText(person.getWorkShiftEnd());
	            workDayLabel.setText(person.getWorkDay());
	            
	            
	           
	            
	        } else {
	            // Person is null, remove all the text.
	            firstNameLabel.setText("");
	            lastNameLabel.setText("");
	            workShiftLabel.setText("");
	            workShiftEndLabel.setText("");
	            workDayLabel.setText("");
	           
	        }
	    }
	    
	    
	    
	   

	    /**
	     * Called when the user clicks the edit button. Opens a dialog to edit
	     * details for the selected person.
	     */
	    @FXML
	    private void handleEditSchedule() {
	        Employee selectedPerson = personTable.getSelectionModel().getSelectedItem();
	        if (selectedPerson != null) {
	            boolean okClicked = mainApp.showScheduleEditDialog(selectedPerson);
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
	     * Opens work schedule report.
	     */
	    @FXML
	    private void handleGenerateScheduleReport(){
	    	mainApp.showScheduleReport();
	    }
}
