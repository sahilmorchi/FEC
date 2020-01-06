package com.hhsfbla.fec.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;

import com.hhsfbla.fec.MainApp;
import com.hhsfbla.fec.model.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * 
 * Report for the attendance of customers
 *
 */
public class AttendanceReportController {
	
	//Table of the report
		@FXML private TableView<Customer> table;
		
		@FXML private TableColumn<Customer, String> numberColumn;
		@FXML private TableColumn<Customer, String> lNameColumn;
		@FXML private TableColumn<Customer, String> fNameColumn;
		@FXML private TableColumn<Customer, String> timeColumn;
		@FXML private TableColumn<Customer, LocalDate>	dateColumn;
		
		
		
		private MainApp main; //the main
		
		private int numCustomers;
		
		private ObservableList<Customer> customers = FXCollections.observableArrayList(); //members list
		private Stage dialogStage; //the main stage
		private boolean cancelClicked; //if cancel is clicked

		/**
		 * Creates a new AttendanceReportController. Initializes everything to 0 and cancelClicked to false.
		 */
		public AttendanceReportController(){
			
			cancelClicked = false;
		}

		/**
		 * Initializes the table.
		 */
		@FXML private void initialize(){
			//Initializes columns so that it displays correct information.
			
			numberColumn.setCellValueFactory(cellData -> cellData.getValue().getCustomerNumberPropertyString());
			fNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
			lNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
			timeColumn.setCellValueFactory(cellData -> cellData.getValue().getTimeProperty());
			dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
			
		}

		/**
		 * Sets main to the main, deletes members not owing a balance
		 * @param main
		 */
		public void setMain(MainApp main){
			this.main = main; //sets main to the main
			
			numCustomers = main.getCustomerData().size(); //calculates size of the data
			for(int i = 0; i < numCustomers; i++){ //copies data to member to avoid passing by reference so members can be deleted
				customers.add(main.getCustomerData().get(i)); //without affecting the database
			}
			
			
			table.setItems(customers); //sets table to these members
		}

		/**
		 * Sets the stage
		 * @param dialogStage
		 */
		public void setDialog(Stage dialogStage){
			this.dialogStage = dialogStage;
			//Does not allow resizing
			this.dialogStage.setResizable(false);
			//this.dialogStage.getIcons().add(new Image("file:resources/img/report-icon.png"));
		}

		

		/**
		 * Cancels program, closes
		 */
		@FXML private void handleCancel(){
			dialogStage.close(); //closes program
		}

		/**
		 * 
		 * @return if cancel is clicked
		 */
		public boolean isCancelClicked(){
			return cancelClicked;
		}

		/**
		 * Handles printing for report
		 */
		@FXML private void handlePrint(){
			//Takes the report page anchorpane from main and prints it
			AnchorPane page = MainApp.page;
			print(page);
		}

		/**
		 * Prints a given node.
		 * @param node the inputted node
		 */
		private void print(Node node) {
			/*
			System.out.println("Creating a printer job...");

			//Creates new printerjob, makes sure it isn't null. Then prints.
			PrinterJob job = PrinterJob.createPrinterJob();
			if (job != null) {
				System.out.println(job.jobStatusProperty().asString());
				
				boolean printed = job.printPage(node);
				if (printed)job.endJob(); //Tests for printing success/fail
				else System.out.println("Printing failed.");
			}
			else System.out.println("Could not create a printer job."); //Printerjob is null.
			*/
			PrinterJob job = PrinterJob.createPrinterJob();
			if (job != null && job.showPrintDialog(node.getScene().getWindow())){
			    boolean success = job.printPage(node);
			    if (success) {
			        job.endJob();
			    }
			}
			
		}
		
		/**
		 * Handles exporting the table information as an excel .xls file. Export path is the desktop.
		 * @throws Exception
		 */
		@FXML public void handleExport() throws Exception {
			Writer writer = null;
			try {
				//Creates new file at path Desktop
					File file = new File(System.getProperty("user.home") + "/Desktop/", "report_output.xls");
					if(!file.exists()){
						file.createNewFile(); //If file does not exist, creates new file
					}
					writer = new BufferedWriter(new FileWriter(file)); //Writes file to desktop
					
					String text = "Number, Last, First, Time, Date\n"; //Adds first line title
					writer.write(text);
					
					//Adds customers to the xls file
					for (Customer customer : customers) {
						text = customer.getCustID() + "," +  customer.getLastName() + "," + customer.getFirstName() + "," + customer.getTime() + "," +  customer.getDate() + "\n";
						writer.write(text);
					}
					
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			finally {
				Alert alert = new Alert(AlertType.INFORMATION); //Creates success alert
				alert.setTitle("Success!");
				alert.setHeaderText("Export successful");
				alert.setContentText("Saved xls file to user.home/Desktop/\n Named \"report_output.xls\"");
				alert.showAndWait();
				
				writer.flush(); //Closes BufferedWriter
				writer.close();
			} 
		}

}
