package src;
import java.util.Random;
public class producerSales {
    Random rand = new Random();
    int storeID;
    public producerSales(int storeID){
        this.storeID = storeID;
    }
    public SalesRecord creatSalesRecord(){
        int dD = rand.nextInt((31) - 1) + 1;
        int mM = rand.nextInt((13) - 1) + 1;
        int yY = 16;
        int registerNumber = rand.nextInt(7 - 1) + 1;
        double saleAmount = rand.nextDouble((999.99 + 1.0) - 0.50) + 0.50;
        float saleAmountFloat = (float) saleAmount;
        SalesRecord newSale = new SalesRecord(dD, mM, yY, registerNumber, storeID, saleAmountFloat);
        return newSale;
    }

}
