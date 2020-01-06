package com.hhsfbla.fec.view;

import java.io.File;
import java.util.Optional;

import com.hhsfbla.fec.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * 
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * When Home is clicked returns to home page.
     */
    @FXML private void handleHome(){
 	   mainApp.showHomeOverview();
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("FECApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Sahil Morchi HHS FBLA\n\n"
        		+ "Database program managing the operations of a family entertainment center.");

        alert.showAndWait();
    }
    
    /**
     * Opens a help dialog
     */
    @FXML
    private void handleHelp(){
    	Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("FECApp");
        alert.setHeaderText("How to Use");
        alert.setContentText("Navigate between employees and customers and the home page. \n\n Click the New button to add a new employee/customer to the database. \n\n "
        		+ "Click the edit button to edit an existing employee/customer. \n\n Click the delete button to delete a employee/customer from the database. \n\n"
        		+ "Attendance reports can be created for customers, and a weekly work schedule can be created for employees. Both reports can be printed and/or exported as an Excel document. ");

        alert.showAndWait();
    }
    
    /**
     * 
     * Switches from Employee Page to Customer Page
     */
   @FXML private void handleSwitch(){
    	mainApp.showCustomerOverview();
    }
   
   @FXML private void handleSwitchEmployee(){
	   mainApp.showEmployeeOverview();
   }
   
   

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
    	Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to exit?", ButtonType.YES, ButtonType.NO);
    	alert.showAndWait();

    	if (alert.getResult() == ButtonType.YES) {
    	    System.exit(0);
    	}
    }
    
   
}
