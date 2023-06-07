package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @Author:     Kun Xie
 */

/**This class uses both of the OberservableLists allParts and allProducts. */
public class Inventory {


    /** ObservableList of all parts. 
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    
    /** ObservableList of all products. 
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    

    
    /**This method adds a new part to the allParts list. 
     * @param newPart the new part that's being added. 
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }
    
    /**This method adds a new product to the allProducts list. 
     * @param newProduct the new product that's being added. 
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }
    
    /** The unique id of a part. Initial value is set to 0. 
     */
    private static int idOfPart = 0;
    
    /** The unique id of a product. Initial value is set to 0. 
     */
    private static int idOfPro = 0;
    
    /**This method looks up part based on the id of a part. 
     * @param idOfPart unique id of a part. 
     * @return return part if found, or null is not found. 
     */
    public static Part lookUpPart(int idOfPart) {
        for (Part part : allParts) {
            if (part.getId() == idOfPart) {
                return part;
            }
        }
        return null;
    }
    
    /**This method looks up product based on the id of a product. 
     * @param idOfPro unique id of a product. 
     * @return return product if found, or null is not found. 
     */
    public static Product lookupProduct(int idOfPro) {
        Product productFound = null;

        for (Product product : allProducts) {
            if (product.getId() == idOfPro) {
                return product;
            }
        }
        return null;
    }

    /**Search ObservableList part. 
     * @param partName name of a part. 
     * @return it returns search result. */
    public static ObservableList<Part> lookupPart(String partName) {
        
        ObservableList<Part> found = FXCollections.observableArrayList();

        for (Part part : allParts) {
            if (part.getName().equals(partName)) {
                found.add(part);
            }
        }
        return found;
    }
    
    /**Search ObservableList product. 
     * @param productName name of a product. 
     * @return it returns search result. */
    public static ObservableList<Product> lookupProduct(String productName) {
        
        ObservableList<Product> found = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if (product.getName().equals(productName)) {
                found.add(product);
            }
        }
        return found;
    }
    
     /** This method modifies and updates parts. 
     @param index is the selected parts index from the list. 
     @param selectedPart  the part that's selected to be modified. */
    public static void updatePart (int index, Part selectedPart) {

         if (index == 1) {
            InHouse newPart = new InHouse(selectedPart.getId(),
                    selectedPart.getName(),
                    selectedPart.getPrice(),
                    selectedPart.getStock(),
                    selectedPart.getMin(),
                    selectedPart.getMax(),
                    ((InHouse)selectedPart).getMachineId());
            addPart(newPart);
        }
        if (index == 2) {
            Outsourced newPart = new Outsourced(selectedPart.getId(),
                    selectedPart.getName(),
                    selectedPart.getPrice(),
                    selectedPart.getStock(),
                    selectedPart.getMin(),
                    selectedPart.getMax(),
                    ((Outsourced)selectedPart).getCompanyName());
            addPart(newPart);
        }
    }
    
    /** This method modifies and updates products. 
     @param index is the selected products index from the list. 
     @param newProduct  is the new product after modification. */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }
    
    /** This method deletes a part record from list.
     * @param selectedPart the part that's being selected for deletion. 
     * @return  returns true if the part is successfully selected and deleted. It returns false if unsuccessful. */
    public static boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }
    /** This method deletes a part record from list.
     * @param selectedProduct the product that's being selected for deletion. 
     * @return  returns true if the product is successfully selected and deleted. It returns false if unsuccessful. */
    public static boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }
    
    /**returns all records from the allParts list. 
     * @return returns all records from the allParts list. */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    /**returns all records from the allProducts list. 
     * @return returns all records from the allProducts list. */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    

    /** returns auto generated new part id.  
     * @return auto generated new part id. */
    public static int autoPartId() {
        return ++idOfPart;
    }
    
    /**returns auto generated new product id. 
     * @return auto generated new product id. */
    public static int autoProductId() {
        return ++idOfPro;
    }








    
}
