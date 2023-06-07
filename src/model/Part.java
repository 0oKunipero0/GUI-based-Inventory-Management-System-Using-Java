package model;

/**
 * @Author:     Kun Xie
 */

/**This is a public abstract class Part*/
public abstract class Part {

    /**
     * id of a part object. 
     */
    private int id;
    /**
     * name of a part object. 
     */
    private String name;
    /**
     * price of a part object. 
     */
    private double price;
    /**
     * stock of a part object. 
     */
    private int stock;
    /**
     * minimum value of stock of a part object. 
     */
    private int min;
    /**
     * maximum value of stock of a part object. 
     */
    private int max;

    /** This is the Part Constructor.It is used to create Part objects. 
     * @param id id of a Part object.
     * @param name name of a Part object.
     * @param price price of a Part object.
     * @param stock stock of a Part object.
     * @param min minimum value of a Part object.
     * @param max maximum value of a Part object. */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**returns return the id of Part class. 
     * @return return the id of Part class. */
    public int getId() {
        return id;
    }

    /**to set the id of Part class. 
     * @param id to set the id of Part class. */
    public void setId(int id) {
        this.id = id;
    }

    /**returns the name of Part class. 
     * @return return the name of Part class. */
    public String getName() {
        return name;
    }

    /**to set the name of Part class. 
     * @param name to set the name of Part class. */
    public void setName(String name) {
        this.name = name;
    }

    /**returns the price of Part class. 
     * @return return the price of Part class. */
    public double getPrice() {
        return price;
    }

    /**to set the price of Part class. 
     * @param price to set the price of Part class. */
    public void setPrice(double price) {
        this.price = price;
    }

    /**returns the inventory stock of Part class. 
     * @return return the stock of Part class. */
    public int getStock() {
        return stock;
    }

    /** to set the stock of Part class. 
     * @param stock to set the stock of Part class. */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** returns the min. value of Part class. 
     * @return return the min. value of Part class. */
    public int getMin() {
        return min;
    }

    /** to set the minimum value of Part class. 
     * @param min to set the minimum value of Part class. */
    public void setMin(int min) {
        this.min = min;
    }

    /** returns the max. value of Part class. 
     * @return return the max. value of Part class. */
    public int getMax() {
        return max;
    }

    /**to set the maximum value of Part class. 
     * @param max to set the maximum value of Part class. */
    public void setMax(int max) {
        this.max = max;
    }



}


