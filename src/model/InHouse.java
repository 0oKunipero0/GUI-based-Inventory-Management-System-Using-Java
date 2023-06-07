package model;

/**
 * @Author:     Kun Xie
 * @StudentID:  001332645
 * @Class:      Software I - C482
 * @School:     WGU
 */

/**This a subclass contains parts that are marked as InHouse. */
public class InHouse extends Part {

    /** the Machine ID of a part when the In-House option is toggled. 
     */
    private int machineId;

    /** * This is the InHouse Constructor.It creates an InHouse object. 
     * @param id id of a Part object. Inherited from the Part superclass. 
     * @param name name of a Part object. Inherited from the Part superclass. 
     * @param price price of a Part object. Inherited from the Part superclass. 
     * @param stock stock of a Part object. Inherited from the Part superclass. 
     * @param min min of a Part object. Inherited from the Part superclass. 
     * @param max max of a Part object. Inherited from the Part superclass. 
     * @param machineId machineID of an InHouse part. */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /** returns the machineID of an InHouse part. 
     * @return the machineID of an InHouse part. 
     */
    public int getMachineId() {
        return machineId;
    }

    /** Sets machineId for an InHouse part. 
    * @param machineId the machine ID. */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
