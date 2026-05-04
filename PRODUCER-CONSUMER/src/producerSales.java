package src;
import java.util.Random;
/**
 * Producer Class for sales
 */
public class producerSales {
    Random rand = new Random();
    int storeID;
    /**
     * Constructor for producerSales
     * @param storeID
     */
    public producerSales(int storeID){
        this.storeID = storeID;
    }
    /**
     * Produces a new sales record for the specific that will be consumed by a consumer
     * @return SalesRecord newSale
     */
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
    public int[] createSalesArray(){
        int dD = rand.nextInt((31) - 1) + 1;
        int mM = rand.nextInt((13) - 1) + 1;
        int yY = 16;
        int registerNumber = rand.nextInt(7 - 1) + 1;
        double saleAmount = rand.nextDouble((999.99 + 1.0) - 0.50) + 0.50;
        float saleAmountFloat = (float) saleAmount;
        int[] salesArray = {dD, mM, yY, registerNumber, storeID, (int) saleAmountFloat};
        return salesArray;
    }

}
