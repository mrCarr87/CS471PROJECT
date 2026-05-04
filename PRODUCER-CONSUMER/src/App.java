package src;
import java.nio.Buffer;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.time.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
class newThread extends Thread{
    public void Run(){
        System.out.println("Thread is running!");
    }
}

public class App {
    
    static int[] producerConsumerSetNumbers = {2,5,10};
    static Random rand = new Random();
    static int sleepTimer = rand.nextInt((40 + 1) - 5) + 5;
    

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
    

    /**
     * Primary function to execute program
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        
        for(int producers: producerConsumerSetNumbers){
            
            for(int consumers: producerConsumerSetNumbers){
                newThread pcThread = new newThread();
                pcThread.start();
                int bufferSize = producers;
                Buffer[] buffer = new Buffer[bufferSize];
                int itemLimit = 1000;
                int itemCount = 0;
                Instant startTime = Instant.now();
                producerSales[] producerList = new producerSales[producers];
                for(int i = 0; i < producers; i++){
                    producerList[i] = new producerSales(i  + 1);
                }
                float[] monthlyTotalSales = {0,0,0,0,0,0,0,0,0,0,0,0};
                ArrayList<Float> stores = new ArrayList<>();
                int storeID = rand.nextInt((producers + 1)- 1) + 1;
                
                for(int i = 0; i < producers; i++){
                    stores.add((float) 0);
                }
                while(itemCount < itemLimit){
                    int counter = 0;
                    while(true){
                        
                        while(counter == bufferSize){

                        }

                        counter++;
                    }
                    
                    

                    
                }
                Instant endTime = Instant.now();
                long duration = Duration.between(startTime, endTime).toMillis();
                float totalAggregateSum = 0;
                for(int i = 0; i < monthlyTotalSales.length; i++){
                    totalAggregateSum += monthlyTotalSales[i];
                }
                try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(String.format(".../PRODUCER-CONSUMER/Producer%sConsumer%dOutput.txt",producers,consumers)))){
                    bufferWriter.write("Statistics for the Run\n");
                    for(int i = 0; i < stores.size(); i++){
                        bufferWriter.write(String.format("\nStore %s Total Sales: %d \n", i + 1, stores.get(i)));
                    }
                    bufferWriter.write(String.format("Monthly Total Sales: \n January: %a \n February: %b \n March: %c \n April: %d \n May: %e \n June: %f \n", 
                    monthlyTotalSales[0],monthlyTotalSales[1], monthlyTotalSales[2], monthlyTotalSales[3], monthlyTotalSales[4],monthlyTotalSales[5]));
                    bufferWriter.write(String.format("July: %a \n August: %b \n September: %c \n October: %d \n November: %e \n December: %f \n", 
                    monthlyTotalSales[6],monthlyTotalSales[7], monthlyTotalSales[8], monthlyTotalSales[9], monthlyTotalSales[10],monthlyTotalSales[11]));
                    bufferWriter.write(String.format("Aggregate Sales: %s \n", totalAggregateSum));
                    bufferWriter.write(String.format("Total Simulation Time: %s \n", duration));
                    
                }
                catch(IOException e){
                    System.out.println("File not found!");
                }
                

            }
            

        }
        
        
    }
}
