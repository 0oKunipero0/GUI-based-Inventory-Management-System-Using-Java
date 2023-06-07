package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @Author:     Kun Xie
 * @StudentID:  001332645
 * @Class:      Software I - C482
 * @School:     WGU
 */

/**This class is used to implement controller for the ModifyPart form. */
public class ModifyPartController implements Initializable {

    /**
     * the part object that's being selected for modification. 
     */
    private Part selectedPart;

    /**
     * radio button that toggles the Outsourced option. 
     */
    @FXML
    private ToggleGroup tgPartType;
    /**
     * radio button that toggles the In-House option. 
     */
    @FXML
    private RadioButton inHRBtn;
    /**
     * radio button that toggles the Outsourced option. 
     */
    @FXML
    private RadioButton outSRBtn;
    /**
     * text field to enter part id. 
     */
    @FXML
    private TextField idOfPartTxt;
    /**
     * text field to enter part name. 
     */
    @FXML
    private TextField nameOfPartTxt;
    /**
     * text field to enter inventory value. 
     */
    @FXML
    private TextField invOfPartTxt;
    /**
     * text field to enter price. 
     */
    @FXML
    private TextField priceOfPartTxt;
    /**
     * text field to enter maximum stock value.  
     */
    @FXML
    private TextField maxStockPartTxt;
    /**
     * text field to enter minimum stock value. 
     */
    @FXML
    private TextField minStockPartTxt;
    /**
     * label of the Machine ID. 
     */
    @FXML
    private Label mchIDorCoNmLbl;
    /**
     * text field to enter Machine ID when In-House option is toggled. 
     */
    @FXML
    private TextField mchIDorCoNm;

    /**This method is used to exit the ModifyPart form and return to the Main form. It will erase existing field texts if it's confirmed. */
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

    /**This method is used to modify the Machine ID when the InHouse radio button is toggled. */
    @FXML
    void inHToggled(ActionEvent event) {

        mchIDorCoNmLbl.setText("Machine ID");
    }

    /**This method is used to modify the Company Name when the OutSourced radio button is toggled. */
    @FXML
    void outSToggled(ActionEvent event) {

        mchIDorCoNmLbl.setText("Company Name");
    }

    /**This method is used to save field text entries and update an existing Part object. */
    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {

        try {
            int id = selectedPart.getId();
            String name = nameOfPartTxt.getText();
            Double price = Double.parseDouble(priceOfPartTxt.getText());
            int stock = Integer.parseInt(invOfPartTxt.getText());
            int min = Integer.parseInt(minStockPartTxt.getText());
            int max = Integer.parseInt(maxStockPartTxt.getText());
            int machineId;
            String companyName;
            boolean partAddSuccessful = false;
            
            if (name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Name");
                alert.setContentText("Please enter a valid name into the text field. ");
                alert.showAndWait();
            }

            else { if (validMin(min, max) && validInv(min, max, stock)) {

                if (inHRBtn.isSelected()) {
                    try {
                        machineId = Integer.parseInt(mchIDorCoNm.getText());
                        InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                        Inventory.addPart(newInHousePart);
                        partAddSuccessful = true;
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Entered Machine ID is Invalid");
                        alert.setContentText("Please enter numbers ONLY. ");
                        alert.showAndWait();
                    }
                }

                if (outSRBtn.isSelected()) {
                    companyName = mchIDorCoNm.getText();
                    Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max,
                            companyName);
                    Inventory.addPart(newOutsourcedPart);
                    partAddSuccessful = true;
                }

                if (partAddSuccessful) {
                    Inventory.deletePart(selectedPart);
                    mainMenu(event);
                }
            }
            }
        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error - Failed to Modify Part");
            alert.setContentText("Please enter valid values and check for blank spaces.");
            alert.showAndWait();
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
    private boolean validInv(int min, int max, int stock) {

        boolean valid = true;

        if (stock < min || stock > max) {
            valid = false;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Entered Inventory Value is Invalid");
            alert.setContentText("Inventory value should be a number equal to, greater than the minimum or less than the maximum value.");
            alert.showAndWait();
        }

        return valid;
    }

    /**This is used to modify existing parts using the new entries entered. 
     *This is used to modify existing parts using the new entries entered. 
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        selectedPart = MainController.getPartToModify();

        if (selectedPart instanceof InHouse) {
            inHRBtn.setSelected(true);
            mchIDorCoNmLbl.setText("Machine ID");
            mchIDorCoNm.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }

        if (selectedPart instanceof Outsourced){
            outSRBtn.setSelected(true);
            mchIDorCoNmLbl.setText("Company Name");
            mchIDorCoNm.setText(((Outsourced) selectedPart).getCompanyName());
        }

        idOfPartTxt.setText(String.valueOf(selectedPart.getId()));
        nameOfPartTxt.setText(selectedPart.getName());
        invOfPartTxt.setText(String.valueOf(selectedPart.getStock()));
        priceOfPartTxt.setText(String.valueOf(selectedPart.getPrice()));
        maxStockPartTxt.setText(String.valueOf(selectedPart.getMax()));
        minStockPartTxt.setText(String.valueOf(selectedPart.getMin()));
    }
}
