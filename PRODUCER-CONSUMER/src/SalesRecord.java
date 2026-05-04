package src;

public class SalesRecord {
    int dD;
    int mM;
    int yY;
    int registerNumber;
    int storeID;
    float saleAmount;
    public SalesRecord(int DD, int MM, int YY, int registerNumber, int storeID,float saleAmount){
        this.dD = DD;
        this.mM = MM;
        this.yY = YY;
        this.registerNumber = registerNumber;
        this.storeID = storeID;
        this.saleAmount = saleAmount;
    }

    public int getDD(){
        return dD;
    }
    public int getMM(){
        return mM;
    }
    public int getYY(){
        return yY;
    }
    public int getRegisterNumber(){
        return registerNumber;
    }
    public int getStoreID(){
        return storeID;
    }
    public float getSaleAmount(){
        return saleAmount;
    }
}
