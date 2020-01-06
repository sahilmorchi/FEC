package com.hhsfbla.fec.view;

import com.hhsfbla.fec.model.Customer;
import com.hhsfbla.fec.model.Employee;
import com.hhsfbla.fec.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * 
 * Dialog to edit the details of a customer
 *
 */
public class CustomerEditDialogController {
	
	 	@FXML
	    private TextField firstNameField;
	    @FXML
	    private TextField lastNameField;
	    
	   // @FXML
	   // private TextField birthdayField;
	    
	    @FXML
	    private TextField genderField;
	    
	    @FXML
	    private TextField ageField;
	    
	   @FXML
	   private TextField customerIDField;
	   
	   @FXML
	   private TextField emailField;
	   
	   @FXML
	   private TextField timeField;
	   
	 //  @FXML
	  // private TextField dateField;
	   
	   @FXML
	   private DatePicker birthdayPicker;
	   
	   @FXML 
	   private DatePicker datePicker;
	  


	    private Stage dialogStage;
	    private Customer customer;
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
	     * @param dialogStage stage of this dialog
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	    /**
	     * Sets the person to be edited in the dialog.
	     * 
	     * @param customer person to be edited in the dialog
	     */
	    public void setPerson(Customer customer) {
	        this.customer = customer;
	        customerIDField.setText(Integer.toString(customer.getCustID()));
	        firstNameField.setText(customer.getFirstName());
	        lastNameField.setText(customer.getLastName());
	        genderField.setText(customer.getGender());
	        ageField.setText(Integer.toString(customer.getAge()));
	       // birthdayField.setText(DateUtil.format(customer.getBirthday()));
	       // birthdayField.setPromptText("dd.mm.yyyy");
	        timeField.setText(customer.getTime());
	       // dateField.setText(DateUtil.format(customer.getDate()));
	       // dateField.setPromptText("dd.mm.yyyy");
	        datePicker.setValue(customer.getDate());
	        
	        
	        birthdayPicker.setValue(customer.getBirthday());
	        
	        emailField.setText(customer.getEmail());
	    }

	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     * 
	     * @return true if the user clicked OK, false otherwise
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
	           customer.setFirstName(firstNameField.getText());
	            customer.setLastName(lastNameField.getText());
	           customer.setGender(genderField.getText());
	           customer.setAge(Integer.parseInt(ageField.getText()));
	            //customer.setBirthday(DateUtil.parse(birthdayField.getText()));
	           
	            customer.setCustID(Integer.parseInt(customerIDField.getText()));
	            customer.setEmail(emailField.getText());
	            
	            customer.setTime(timeField.getText());
	            //customer.setDate(DateUtil.parse(dateField.getText()));
	            customer.setBirthday((birthdayPicker.getValue()));
	            customer.setDate(datePicker.getValue());
	            

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
	      		if(customerIDField == null || customerIDField.getText().length() != 6)
	      			errorMessage += "No valid customer number! Must be 6 numbers long!\n";
	      		else if(!customer.checkID()){
	      				errorMessage += "Customer ID already exists!";
	      			}
	      		else{
	      			try{
	      				Integer.parseInt(customerIDField.getText()); //tests if field is integer
	      			}
	      			catch (NumberFormatException e){
	      				errorMessage += "No valid customer number (must be an integer)!\n";
	      			}
	      		}

	        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
	            errorMessage += "No valid first name!\n"; 
	        }
	        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
	            errorMessage += "No valid last name!\n"; 
	        }
	        
	        if (genderField.getText() == null || genderField.getText().length() == 0) {
	            errorMessage += "No valid gender! Use M, F, Male, Female, Other\n"; 
	        }
	        
	        if (timeField.getText() == null || timeField.getText().length() == 0 ) {
	            errorMessage += "No valid time!\n"; 
	        }
	        
	        
	        
	        if(ageField == null || ageField.getText().length() == 0)
      			errorMessage += "No valid age!\n";
      		else{
      			try{
      				Integer.parseInt(ageField.getText()); //tests if field is integer
      			}
      			catch (NumberFormatException e){
      				errorMessage += "No valid age(must be an integer)!\n";
      			}
      		}
	       

	       
	       
	        
	        if (birthdayPicker.getValue() == null) {
	            errorMessage += "No valid birthday!\n";
	        } 
	        
	        if(datePicker.getValue() == null){
	        	errorMessage += "Not a valid date!\n";
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
