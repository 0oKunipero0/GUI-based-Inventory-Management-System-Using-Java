package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @Author:     Kun Xie
 * @StudentID:  001332645
 * @Class:      Software I - C482
 * @School:     WGU
 */

/**This is the Product class, it includes the ObservableList of associated parts.  */
public class Product {

    /**
     * id of a product object. 
     */
    private int id;
    /**
     * name of a product object. 
     */
    private String name;
    /**
     * price of a product object. 
     */
    private double price;
    /**
     * stock of a product object. 
     */
    private int stock;
    /**
     * minimum value of stock of a product object. 
     */
    private int min;
    /**
     * maximum value of stock of a product object. 
     */
    private int max;
    /**
     * list of associated parts of a product object. 
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /** This is the Product Constructor.It is used to create Product objects.
     * @param id id of a Product object.
     * @param name name of a Product object.
     * @param price price of a Product object.
     * @param stock stock of a Product object.
     * @param min minimum value of a Product object.
     * @param max maximum value of a Product object. */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**returns the id of Product class. 
     * @return return the id of Product class. */
    public int getId() {
        return id;
    }

    /** to set the id of Product class. 
     * @param id to set the id of Product class. */
    public void setId(int id) {
        this.id = id;
    }

    /** returns the name of Product class. 
     * @return return the name of Product class. */
    public String getName() {
        return name;
    }

    /** to set the name of Product class. 
     * @param name to set the name of Product class. */
    public void setName(String name) {
        this.name = name;
    }

    /** returns the price of Product class. 
     * @return return the price of Product class. */
    public double getPrice() {
        return price;
    }

    /** to set the price of Product class. 
     * @param price to set the price of Product class. */
    public void setPrice(double price) {
        this.price = price;
    }

    /** returns the stock of Product class. 
     * @return return the stock of Product class. */
    public int getStock() {
        return stock;
    }

    /** to set the stock of Product class. 
     * @param stock to set the stock of Product class. */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** returns the minimum value of Product class. 
     * @return return the minimum value of Product class. */
    public int getMin() {
        return min;
    }

    /** to set the minimum value of Product class. 
     * @param min to set the minimum value of Product class. */
    public void setMin(int min) {
        this.min = min;
    }

    /** returns the maximum value of Product class. 
     * @return return the maximum value of Product class. */
    public int getMax() {
        return max;
    }

    /** to set the maximum value of Product class. 
     * @param max to set the maximum value of Product class. */
    public void setMax(int max) {
        this.max = max;
    }

    /**It is used to add part to a Product object's associated parts list. 
     * @param part a part that's being added*/
    public void  addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**It is used to remove an associated part from a Product object.
     * @param selectedAssociatedPart used to select an associated part from the list.
     * @return true if a part is successfully selected and removed. It returns false if a part is not successfully removed. 
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else
            return false;
    }
    /**Displays all the associated parts record from the list.
     * @return it returns all associated parts. 
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }
}
