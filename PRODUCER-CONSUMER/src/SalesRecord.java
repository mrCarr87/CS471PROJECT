package src;
/**
 * Class and operations for salesRecords
 */
public class SalesRecord {
    int dD;
    int mM;
    int yY;
    int registerNumber;
    int storeID;
    float saleAmount;
    /**
     * Primary constructor for SalesRecord
     * @param DD
     * @param MM
     * @param YY
     * @param registerNumber
     * @param storeID
     * @param saleAmount
     */
    public SalesRecord(int DD, int MM, int YY, int registerNumber, int storeID,float saleAmount){
        this.dD = DD;
        this.mM = MM;
        this.yY = YY;
        this.registerNumber = registerNumber;
        this.storeID = storeID;
        this.saleAmount = saleAmount;
    }
    /**
     * Gets day
     * @return dD
     */
    public int getDD(){
        return dD;
    }
    /**
     * Gets month
     * @return mM
     */
    public int getMM(){
        return mM;
    }
    /**
     * Gets year
     * @return yY
     */
    public int getYY(){
        return yY;
    }
    /**
     * Gets Register Number
     * @return
     */
    public int getRegisterNumber(){
        return registerNumber;
    }
    /**
     * Gets Store ID
     * @return storeID
     */
    public int getStoreID(){
        return storeID;
    }
    /**
     * Gets Sale Amount
     * @return saleAmount
     */
    public float getSaleAmount(){
        return saleAmount;
    }
}
