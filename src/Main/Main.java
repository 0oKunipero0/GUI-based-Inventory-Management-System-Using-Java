/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * @author: Kun Xie
 * @StudentID:  001332645
 * @Class:      Software I - C482
 * @School:     WGU
 * 
 * JAVADOC LOCATION: WGU - Software 1 C482/dist/javadoc/index.html
 */


/** This class creates the inventory management application. 
 * FUTURE ENHANCEMENT: 
 * If I am able to continue this project in the future, one functional 
 * enhancement I would like to add is a table view of parts and the products
 * they are associated with. Right now, we can view products and their
 * associated parts, but we can't view it from the other direction.
 */
public class Main extends Application {

    /**
     * This is used to set up the primary window of the GUI application. 
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
       Scene scene = new Scene (root);
       stage.setScene(scene);
       stage.setTitle("Inventory Management System Application");
       stage.show();
    }
    
    /** * This method launches the application.It's the first method getting called upon launching the program. 
     * @param args args. */
    public static void main(String[] args) {
        
        launch(args);
        
    }
    
}
