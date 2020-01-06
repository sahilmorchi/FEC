package com.hhsfbla.fec.view;

import com.hhsfbla.fec.MainApp;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Home screen
 */
public class HomeController {
	
	private MainApp mainApp;
	private Stage dialogStage;
	
	
	
	
	
	
	public HomeController(){
		
	}
	
	
	/**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        
    }
    
  
    
    /**
     * Called when the Employee Button is clicked.
     * Opens Employee overview page. 
     */
    @FXML
    private void handleEmployeePage(){
    	mainApp.showEmployeeOverview();
    }
    
    
    /**
     * Called when the Customer Button is clicked.
     * Opens Customer overview page.
     */
    @FXML
    private void handleCustomerPage(){
    	mainApp.showCustomerOverview();
    }
    
    
    
    

}
