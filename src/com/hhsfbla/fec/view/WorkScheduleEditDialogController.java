package com.hhsfbla.fec.view;

import com.hhsfbla.fec.model.Employee;
import com.hhsfbla.fec.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * 
 * Dialog to edit the work schedule of an employee
 *
 */
public class WorkScheduleEditDialogController {
	 @FXML
	    private TextField firstNameField;
	    @FXML
	    private TextField lastNameField;
	   
	    @FXML
	    private TextField workShiftField;
	    
	    @FXML
	    private TextField workShiftEndField;
	    
	    @FXML
	    private TextField workDayField;
	    
	  


	    private Stage dialogStage;
	    private Employee person;
	    private boolean okClicked = false;

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    }

	    /**
	     * Sets the stage of this dialog.
	     * 
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	    /**
	     * Sets the person to be edited in the dialog.
	     * 
	     * @param person
	     */
	    public void setPerson(Employee person) {
	        this.person = person;
	       
	        firstNameField.setText(person.getFirstName());
	        lastNameField.setText(person.getLastName());
	        workShiftField.setText(person.getWorkShift());
	        workShiftEndField.setText(person.getWorkShiftEnd());
	        workDayField.setText(person.getWorkDay());
	        
	    }

	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     * 
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Called when the user clicks ok.
	     */
	    @FXML
	    private void handleOk() {
	        if (isInputValid()) {
	            person.setFirstName(firstNameField.getText());
	            person.setLastName(lastNameField.getText());
	            person.setWorkShift(workShiftField.getText());
	            person.setWorkShiftEnd(workShiftEndField.getText());
	            person.setWorkDay(workDayField.getText());

	            okClicked = true;
	            dialogStage.close();
	        }
	    }

	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

	    /**
	     * Validates the user input in the text fields.
	     * 
	     * @return true if the input is valid
	     */
	    private boolean isInputValid() {
	        String errorMessage = "";
	        
	      

	        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
	            errorMessage += "No valid first name!\n"; 
	        }
	        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
	            errorMessage += "No valid last name!\n"; 
	        }
	        
	        
	        if (workShiftField.getText() == null || workShiftField.getText().length() == 0) {
	            errorMessage += "No valid work shift!\n"; 
	        }
	        
	        if (workShiftEndField.getText() == null || workShiftEndField.getText().length() == 0) {
	            errorMessage += "No valid work shift end!\n"; 
	        }
	        
	        if (workDayField.getText() == null || workDayField.getText().length() == 0) {
	            errorMessage += "No valid work day!\n"; 
	        }
	        
	        

	        

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Invalid Fields");
	            alert.setHeaderText("Please correct invalid fields");
	            alert.setContentText(errorMessage);

	            alert.showAndWait();

	            return false;
	        }
	    }
}
