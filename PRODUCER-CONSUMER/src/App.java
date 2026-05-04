package src;
import java.nio.Buffer;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.time.*;
class newThread extends Thread{
    public void Run(){
        System.out.println("Thread is running!");
    }
}

public class App {
    static int bufferSize = 5;
    static int[] producerConsumerSetNumbers = {2,5,10};
    
    static Random rand = new Random();
    
    

    public static SalesRecord producerSales(int storeID){
        int dD = rand.nextInt((31) - 1) + 1;
        int mM = rand.nextInt((13) - 1) + 1;
        int yY = 16;
        int registerNumber = rand.nextInt(7 - 1) + 1;
        double saleAmount = rand.nextDouble((999.99 + 1.0) - 0.50) + 0.50;
        float saleAmountFloat = (float) saleAmount;
        SalesRecord newSale = new SalesRecord(dD, mM, yY, registerNumber, storeID, saleAmountFloat);
        return newSale;

    }
    public static void consumerStatistics(SalesRecord record, Buffer buffer){
        System.out.printf("Date: %s/%d/%f",record.mM,record.dD,record.yY);
        System.out.printf("Store ID: %s", record.storeID);
        System.out.printf("Register Number: %s", record.registerNumber);
        System.out.printf("Sale Amount: %s", record.saleAmount);
    }


    public static void main(String[] args) throws Exception {
        
        for(int producers: producerConsumerSetNumbers){
            
            for(int consumers: producerConsumerSetNumbers){
                Buffer[] buffer = new Buffer[bufferSize];
                int itemLimit = 1000;
                int itemCount = 0;
                Instant startTime = Instant.now();
                ArrayList<Integer> stores = new ArrayList<>();
                
                for(int i = 0; i < producers; i++){
                    stores.add(0);
                }
                while(itemCount < itemLimit){
                    while(true){

                    }

                    
                }
                Instant endTime = Instant.now();
                long duration = Duration.between(startTime, endTime).toMillis();
                System.out.printf("Total Simulation Time: %s", duration);

            }
            

        }
        
        
    }
}
