package com.hhsfbla.fec;

import java.io.File;

import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.hhsfbla.fec.model.Customer;
import com.hhsfbla.fec.model.Employee;
import com.hhsfbla.fec.model.PersonListWrapper;
import com.hhsfbla.fec.view.AttendanceReportController;
import com.hhsfbla.fec.view.BirthdayStatisticsController;
import com.hhsfbla.fec.view.CustomerEditDialogController;
import com.hhsfbla.fec.view.CustomerOverviewController;
import com.hhsfbla.fec.view.EmployeeEditDialogController;
import com.hhsfbla.fec.view.EmployeeOverviewController;
import com.hhsfbla.fec.view.HomeController;
import com.hhsfbla.fec.view.RootLayoutController;
import com.hhsfbla.fec.view.ScheduleReportController;
import com.hhsfbla.fec.view.WorkScheduleController;
import com.hhsfbla.fec.view.WorkScheduleEditDialogController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * 
 * @author Sahil Morchi
 * @version 1.0.0
 *
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    public static AnchorPane page;
    
    
    
    
    // ... AFTER THE OTHER VARIABLES ...

   
    private ObservableList<Employee> personData = FXCollections.observableArrayList();
    
    private ObservableList<Customer> customerData = FXCollections.observableArrayList();
    
    

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        personData.add(new Employee(123541,"John", "Anderson"));
        personData.add(new Employee(124323,"Billy", "Joel"));
        personData.add(new Employee(127765,"Joanna", "Marcus"));
        personData.add(new Employee(125454,"Mary", "Meier"));
        personData.add(new Employee(132322,"Jack", "Pearson"));
        personData.add(new Employee(135322,"Lydia", "Hunter"));
        personData.add(new Employee(129943,"Angela", "Ofstad"));
        personData.add(new Employee(144234,"Jacob", "Meier"));
        personData.add(new Employee(122232,"Alex", "Hirsch"));
        personData.add(new Employee(133232,"Shelly", "Patton"));
        personData.add(new Employee(122732,"Melissa", "Knight"));
        personData.add(new Employee(137321,"Jim", "Andrews"));
        personData.add(new Employee(153233,"Brent", "Gibson"));
     
        
       
        
        customerData.add(new Customer(173332, "Sahil", "Morchi", "M", 16));
        customerData.add(new Customer(184641, "Joe", "Benson", "M", 18));
        customerData.add(new Customer(145651, "Jill", "Mayer", "F", 14));
        customerData.add(new Customer(185235, "Justin", "West", "M", 21));
        customerData.add(new Customer(190654, "Saul", "Munoz", "M", 11));
        customerData.add(new Customer(134321, "Leon", "Sullivan", "M", 8));
        customerData.add(new Customer(164243, "Ella", "Vasquez", "F", 14));
        customerData.add(new Customer(176587, "Sonia", "Parks", "F", 11));
        customerData.add(new Customer(143223, "Linda", "Jackson", "F", 22));
        customerData.add(new Customer(165568, "Doug", "Reid", "M", 28));
        customerData.add(new Customer(168952, "Marcella", "Fowler", "F", 19));
        customerData.add(new Customer(147542, "Jordan", "Becker", "M", 15));
        customerData.add(new Customer(145963, "Amber", "Norton", "F", 17));
        customerData.add(new Customer(169338, "Martin", "Barber", "M", 41));
        customerData.add(new Customer(137944, "Regina", "Ellis", "F", 23));
        customerData.add(new Customer(146832, "Brad", "Fox", "M", 14));
        customerData.add(new Customer(146722, "Cecilia", "Marsh", "F", 35));
    }

    /**
     * Returns the data as an observable list of Employees. 
     * @return returns the data as an observable list of Employees.
     */
    public ObservableList<Employee> getPersonData() {
        return personData;
    }
    
    
    /**
     * Returns the data as an observable list of Customers. 
     * @return returns the data as an observable list of Customers. 
     */
    public ObservableList<Customer> getCustomerData() {
        return customerData;
    }
    
    


    @Override
    public void start(Stage primaryStage) {
    	
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("FECApp");
        
     // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
        

        initRootLayout();

        showHomeOverview();
       
        
        
    }

    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
            primaryStage.setMinHeight(500);
            primaryStage.setMinWidth(700);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
        }
    }
    
   
    
    

    /**
     * Shows the employee overview inside the root layout.
     */
    public void showEmployeeOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EmployeeOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
            //Give controller access to the main app
            EmployeeOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.activateFilter();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Shows the schedule overview inside the root layout.
     */
    public void showScheduleOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/WorkSchedule.fxml"));
            AnchorPane scheduleOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(scheduleOverview);
            
            //Give controller access to the main app
            WorkScheduleController controller = loader.getController();
            controller.setMainApp(this);
            controller.activateFilter();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
     * Shows the customer overview inside the root layout.
     */
    public void showCustomerOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CustomerOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
            
            //Give controller access to the main app
            CustomerOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.activateFilter();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * Shows the home screen inside the root layout.
     */
    public void showHomeOverview(){
    	try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Home.fxml"));
            AnchorPane home = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(home);
            
            HomeController controller = loader.getController();
            controller.setMainApp(this);
           
          
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    /**
	 * Generates a customer attendance report, showing all customer attendance information.
	 */
	public void showCustomerReport(){
		try{
			// Load the fxml file and create a new stage for the members report
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/AttendanceReport.fxml"));
			page = (AnchorPane) loader.load();

			//Sets the stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Attendance Report"); //Sets title to arg
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);
			

			// Loads controller
			AttendanceReportController controller = loader.getController();

			//Sets stage and main to controller class
			controller.setDialog(dialogStage);
			controller.setMain(this);

			
			dialogStage.showAndWait();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Generates a work schedule report, showing all work schedule information.
	 */
	public void showScheduleReport(){
		try{
			// Load the fxml file and create a new stage for the members report
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/ScheduleReport.fxml"));
			page = (AnchorPane) loader.load();

			//Sets the stage
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Work Schedule"); //Sets title to arg
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Loads controller
			ScheduleReportController controller = loader.getController();

			//Sets stage and main to controller class
			controller.setDialog(dialogStage);
			controller.setMain(this);

			
			dialogStage.showAndWait();
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
    
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Employee person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EmployeeEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EmployeeEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showCustomerEditDialog(Customer customer) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CustomerEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            CustomerEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(customer);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showScheduleEditDialog(Employee employee) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/WorkScheduleEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            WorkScheduleEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(employee);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return person file preference
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("FECApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("FECApp");
        }
    }
    
    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     * 
     * @param file
     */
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());
            
            customerData.clear();
            customerData.addAll(wrapper.getCustomers());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);
            wrapper.setCustomers(customerData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    
    
    
    /**
     * Opens a dialog to show birthday statistics.
     */
    
    public void showBirthdayStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(customerData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    /**
     * Returns the main stage.
     * @return main stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
        
    }
}
