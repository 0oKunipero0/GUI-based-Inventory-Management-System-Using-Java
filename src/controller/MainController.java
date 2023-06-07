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


/** This class is used to implement controller for the main page. */
public class MainController implements Initializable {

    /**
     * text field to enter info to search parts in Main form. 
     */
    @FXML
    private TextField searchPartTxt;
    /**
     * Part Table View in Main form. 
     */
    @FXML
    private TableView<Part> partTable;
    /**
     * Part Table view's id column of parts in Main form. 
     */
    @FXML
    private TableColumn<Part, Integer> idOfPartCol;
    /**
     * Part Table view's name column of parts in Main form. 
     */
    @FXML
    private TableColumn<Part, String> nameOfPartCol;
    /**
     * Part Table view's inventory level column of parts in Main form. 
     */
    @FXML
    private TableColumn<Part, Integer> invOfPartCol;
    /**
     * Part Table view's price/cost column of parts in Main form. 
     */
    @FXML
    private TableColumn<Part, Double> priceOfPartCol;

    /**
     * text field to enter info to search products in Main form. 
     */
    @FXML
    private TextField searchProductTxt;
    /**
     * Product Table View in Main form. 
     */
    @FXML
    private TableView<Product> productTable;
    /**
     * Product Table view's id column of products in Main form. 
     */
    @FXML
    private TableColumn<Product, Integer> idOfProCol;
    /**
     * Product Table view's name column of products in Main form. 
     */
    @FXML
    private TableColumn<Product, String> nameOfProCol;
    /**
     * Product Table view's inventory level column of products in Main form. 
     */
    @FXML
    private TableColumn<Product, Integer> invOfProductCol;
    /**
     * Product Table view's price/cost column of products in Main form. 
     */
    @FXML
    private TableColumn<Product, Double> priceOfProCol;
    
    /**
     * parts for modification. 
     */
    private static Part partModification;
    /**
     * products for modification. 
     */
    private static Product productModification;

    /**returns parts for modification. 
     *
     * @return parts for modification
     */
    public static Part getPartToModify() {
        return partModification;
    }

    /**returns products for modification. 
     *
     * @return products for modification
     */
    public static Product getProductToModify() {
        return productModification;
    }

    /**This method is used to exit the application. */
    @FXML
    void exitAction(ActionEvent event) {

        System.exit(0);
    }

    /**This method is used to add new Part object. It takes the user to the AddPart form. */
    @FXML
    void addPart(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddPart.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**This method is used to remove existing Part object. 
     */
    @FXML
    void deletePart(ActionEvent event) {

        Part selectedPart = partTable.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a valid part to proceed. ");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Please confirm that you wish to remove the part that is being selected. ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    /**This method is used to modify existing Part object. It takes the user to the ModifyPart form. 
     * RUNTIME ERROR:
     * I had to resolve was when making the ModifyPartController, I tested
     * the project by clicking the Modify button on the Main form. However, I got a
     * series of runtime error code (in bright red)and the ModifyPart form wasn't coming up. 
     * I kept going back and forth to check, make sure the path and link is correct. 
     * After hours of research and testing, I realized there was no record to select to get to the ModifyPart
     * form. I fixed this issue by creating alert in the MainController and preventing null 
     * from being passed to the ModifyPartController. It prevented me from making 
     * to same mistake when testing the ModifyProduct form.
     */
    @FXML
    void modifyPart(ActionEvent event) throws IOException {

        partModification = partTable.getSelectionModel().getSelectedItem();

        if (partModification == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a valid part to proceed. ");
            alert.showAndWait();
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyPart.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**This method is used to search existing Part object from the ObservableList.  */
    @FXML
    void searchPart(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = searchPartTxt.getText();

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        partTable.setItems(partsFound);

        if (partsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("No record was found. ");
            alert.showAndWait();
        }
    }

    /**This method is used to display all records when the search field is empty when the button is pressed. */
    @FXML
    void partSearchTextKeyPressed(KeyEvent event) {

        if (searchPartTxt.getText().isEmpty()) {
            partTable.setItems(Inventory.getAllParts());
        }

    }

    /**This method is used to add new Product object. It takes the user to the AddProduct form. */
    @FXML
    void addProduct(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(getClass().getResource("../view/AddProduct.fxml"));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**This method is used to delete existing Product object. */
    @FXML
    void deleteProduct(ActionEvent event) {

        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a valid product to proceed. ");
            alert.showAndWait();
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Please confirm that you wish to remove the product that is being selected. ");
            
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<Part> assocParts = selectedProduct.getAllAssociatedParts();

                if (assocParts.size() >= 1) {
                    
                alert.setTitle("Error");
                alert.setHeaderText("Parts Associated");
                alert.setContentText("Please delete all associated part before deletion. ");
                alert.showAndWait();
                } else {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    }

    /**This method is used to modify existing Product object. It takes the user to the ModifyProduct form. */
    @FXML
    void modifyProduct(ActionEvent event) throws IOException {

        productModification = productTable.getSelectionModel().getSelectedItem();

        if (productModification == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a valid product to proceed. ");
            alert.showAndWait();
        } else {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/ModifyProduct.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**This method is used to search existing Product object from the ObservableList. */
    @FXML
    void searchProduct(ActionEvent event) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String searchString = searchProductTxt.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) ||
                    product.getName().contains(searchString)) {
                productsFound.add(product);
            }
        }

        productTable.setItems(productsFound);

        if (productsFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("No record was found. ");
            alert.showAndWait();
        }
    }

    /**This method is used to display all records when the search field is empty when the button is pressed. 
     */
    @FXML
    void productSearchTextKeyPressed(KeyEvent event) {

        if (searchProductTxt.getText().isEmpty()) {
            productTable.setItems(Inventory.getAllProducts());
        }
    }

    /**This method is used to populate both the Part and Product table on the Main form.
     * @param location 
     * @param resources */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        partTable.setItems(Inventory.getAllParts());
        idOfPartCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfPartCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invOfPartCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceOfPartCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productTable.setItems(Inventory.getAllProducts());
        idOfProCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameOfProCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invOfProductCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceOfProCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
