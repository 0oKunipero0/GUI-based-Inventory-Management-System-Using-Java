package model;

/**
 * @Author:     Kun Xie
 */

/**This a subclass contains parts that are marked as OutSourced. */
public class Outsourced extends Part {

    /** the Company Name of a part when the Outsourced option is toggled. 
     */
    private String companyName;

    /** * This is the InHouse Constructor.It creates an InHouse object. 
     * @param id id of a Part object. Inherited from the Part superclass. 
     * @param name name of a Part object. Inherited from the Part superclass. 
     * @param price price of a Part object. Inherited from the Part superclass. 
     * @param stock stock of a Part object. Inherited from the Part superclass. 
     * @param min min of a Part object. Inherited from the Part superclass. 
     * @param max max of a Part object. Inherited from the Part superclass. 
     * @param companyName name of a company of an OutSourced part. */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**returns the company name. 
     * @return the company name. */
    public String getCompanyName() {
        return companyName;
    }

    /**It sets the company name of an OutSourced part. 
     * @param companyName the name of a company. */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
