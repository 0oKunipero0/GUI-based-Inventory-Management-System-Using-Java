package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @Author:     Kun Xie
 */

/**This class is used to implement controller for the AddProduct form. */
public class AddProductController implements Initializable {

    /**
     * ObservableList of all associated parts of a product. 
     */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();


    /**
     * text field to enter info to search parts. 
     */
    @FXML
    private TextField searchTxt;
    /**
     * text field to enter id of a product object. 
     */
    @FXML
    private TextField idOfProTxt;
    /**
     * text field to enter name of a product object. 
     */
    @FXML
    private TextField nameOfProTxt;
    /**
     * text field to enter inventory of a product object. 
     */
    @FXML
    private TextField invOfProTxt;
    /**
     * text field to enter price of a product object. 
     */
    @FXML
    private TextField priceOfProTxt;
    /**
     * text field to enter maximum value of stock of a product object. 
     */
    @FXML
    private TextField maxStockOfProTxt;
    /**
     * text field to enter minimum value of stock of a product object. 
     */
    @FXML
    private TextField minStockOfProTxt;
    /**
     * Parts Table View in the AddProduct form. 
     */
    @FXML
    private TableView<Part> prtTbl;
    /**
     * Parts Table column of part id. 
     */
    @FXML
    private TableColumn<Part, Integer> idOfPartCol;
    /**
     * Parts Table column of part name. 
     */
    @FXML
    private TableColumn<Part, String> nameOfPartCol;
    /**
     * Parts Table column of part inventory. 
     */
    @FXML
    private TableColumn<Part, Integer> invOfPartCol;
    /**
     * Parts Table column of part price. 
     */
    @FXML
    private TableColumn<Part, Double> priceOfPartCol;
    /**
     * Associated Parts Table View in the AddProduct form. 
     */
    @FXML
    private TableView<Part> assocPrtTbl;
    /**
     * Associated Parts Table column of associated part id. 
     */
    @FXML
    private TableColumn<Part, Integer> assocIdCol;
    /**
     * Associated Parts Table column of associated part name. 
     */
    @FXML
    private TableColumn<Part, String> assocNameCol;
    /**
     * Associated Parts Table column of associated part inventory. 
     */
    @FXML
    private TableColumn<Part, Integer> assocInvCol;
    /**
     * Associated Parts Table column of associated part price. 
     */
    @FXML
    private TableColumn<Part, Double> assocPriceCol;
    
    
    /**This method is used to add an existing part from record to the associated parts list of the product. */
    @FXML
    void addAction(ActionEvent event) {

        Part selectedPart = prtTbl.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please Select a Part.");
            alert.showAndWait();
        } else {
            assocParts.add(selectedPart);
            assocPrtTbl.setItems(assocParts);
        }
    }

    /**This method is used to exit the AddProduct form and return to the Main form. It will erase existing field texts if it's confirmed. 
     */
    @FXML
    void cancelAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Please confirm you want to return to the mainscreen. This will remove all changes you have entered. ");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            mainMenu(event);
        }
    }
    
    /**This method is used to remove part(s) associated with the product. 
     */
    @FXML
    void removeAction(ActionEvent event) {

        Part selectedPart = assocPrtTbl.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please Select a Part.");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Please confirm entry deletion. This will remove the associated part from the product. ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                assocParts.remove(selectedPart);
                assocPrtTbl.setItems(assocParts);
            }
        }
    }
    
    /**This method is used to create a new Product object by saving field text entries and associated parts. 
     */
    @FXML
    void saveAction(ActionEvent event) throws IOException {

        try {
            int id = 0;
            String name = nameOfProTxt.getText();
            Double price = Double.parseDouble(priceOfProTxt.getText());
            int inv = Integer.parseInt(invOfProTxt.getText());
            int min = Integer.parseInt(minStockOfProTxt.getText());
            int max = Integer.parseInt(maxStockOfProTxt.getText());

            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Name");
                alert.setContentText("Please enter a valid name into the text field.");
                alert.showAndWait();
            } else {
                if (validMin(min, max) && validInv(min, max, inv)) {

                    Product newProduct = new Product(id, name, price, inv, min, max);

                    for (Part part : assocParts) {
                        newProduct.addAssociatedPart(part);
                    }

                    newProduct.setId(Inventory.autoProductId());
                    Inventory.addProduct(newProduct);
                    mainMenu(event);
                }
            }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error - Failed to Add Product");
            alert.setContentText("Please enter valid values and check for blank spaces.");
            alert.showAndWait();
        }
    }
    
    /**This method is used to search existing part from record. 
     */
    @FXML
    void searchAction(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = searchTxt.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        prtTbl.setItems(partsFound);

        if (partsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Can't find the part you entered. ");
            alert.showAndWait();
        }
    }

    /**This method is used to display all parts from record if the search field is left empty. 
     */
    @FXML
    void partSearchKeyPressed(KeyEvent event) {

        if (searchTxt.getText().isEmpty()) {
            prtTbl.setItems(Inventory.getAllParts());
        }
    }
    
    /**This method is used to create action event so other methods can return to the Main form. */
    private void mainMenu(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**This method is used to create alert error if the minimum value entered is not valid. */
    private boolean validMin(int min, int max) {

        boolean valid = true;

        if (min <= 0 || min >= max) {
            valid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Entered Minimum Value is Invalid");
            alert.setContentText("Minimum value should be a number greater than 0 and less than the maximum value entered.");
            alert.showAndWait();
        }

        return valid;
    }

    /**This method is used to create alert error if the inventory value entered is not valid. */
    private boolean validInv(int min, int max, int inv) {

        boolean valid = true;

        if (inv < min || inv > max) {
            valid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Entered Inventory Value is Invalid");
            alert.setContentText("Inventory value should be a number equal to, greater than the minimum or less than the maximum value.");
            alert.showAndWait();
        }

        return valid;
    }

    /**This is used to display both the part and associate part tables in the AddProduct form, and to set new product entries.
     * This is used to display both the part and associate part tables in the AddProduct form,  and to set new product entries. 
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idOfPartCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfPartCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invOfPartCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceOfPartCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        prtTbl.setItems(Inventory.getAllParts());

        assocIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
