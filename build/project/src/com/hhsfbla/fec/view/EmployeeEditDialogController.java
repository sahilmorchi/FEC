package com.hhsfbla.fec.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.hhsfbla.fec.model.Employee;
import com.hhsfbla.fec.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * 
 */
public class EmployeeEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField cityField;
    
    
    @FXML
    private DatePicker birthdayPicker;
    
    @FXML
    private TextField workShiftField;
    
   @FXML
   private TextField employeeIDField;
   
   @FXML
   private TextField emailField;


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
        employeeIDField.setText(Integer.toString(person.getEmpID()));
        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayPicker.setValue(person.getBirthday());
        workShiftField.setText(person.getWorkShift());
        emailField.setText(person.getEmail());
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
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setWorkShift(workShiftField.getText());
            person.setEmpID(Integer.parseInt(employeeIDField.getText()));
            person.setEmail(emailField.getText());
            person.setBirthday(birthdayPicker.getValue());

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
        
      //if membership number is not inputted or is not an integer, creates error
      		if(employeeIDField == null || employeeIDField.getText().length() != 6)
      			errorMessage += "No valid employee number! Must be 6 numbers long!\n";
      		else{
      			try{
      				Integer.parseInt(employeeIDField.getText()); //tests if field is integer
      			}
      			catch (NumberFormatException e){
      				errorMessage += "No valid employee number (must be an integer)!\n";
      			}
      		}

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n"; 
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n"; 
        }
        if (streetField.getText() == null || streetField.getText().length() == 0) {
            errorMessage += "No valid street!\n"; 
        }
        
        if (workShiftField.getText() == null || workShiftField.getText().length() == 0) {
            errorMessage += "No valid work shift!\n"; 
        }

        if (postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
            errorMessage += "No valid postal code!\n"; 
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n"; 
            }
        }

        if (cityField.getText() == null || cityField.getText().length() == 0) {
            errorMessage += "No valid city!\n"; 
        }

        
        
        if(birthdayPicker == null){
        	errorMessage += "No valid birthday!\n";
        }
        
        if(emailField.getText() == null || emailField.getText().length() == 0)
			errorMessage += "No valid email!\n";

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