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
import java.util.concurrent.*;


public class App {
    
    
    static int[] producerConsumerSetNumbers = {2,5,10};
    static Random rand = new Random();
    static int sleepTimer = rand.nextInt((40 + 1) - 5) + 5;
    

    
    

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
                SalesRecord[] buffer = new SalesRecord[bufferSize];
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
                

                int m = 1;
                int empty = bufferSize;
                int full = 0;


                int in = 0;
                int out = 0;
                for(int i = 0; i < producers; i++){
                    stores.add((float) 0);
                }
                while(itemCount < itemLimit){
                    int counter = 0;
                    
                        while(counter != bufferSize){
                            SalesRecord newlyCreatedRecord = producerList[storeID - 1].creatSalesRecord();
                            System.out.println(counter);
                            
                            buffer[in] = newlyCreatedRecord;
                            in = (in + 1) % bufferSize;
                            counter++;
                            
                        }
                        
                        while(counter != 0){
                            
                            System.out.println(counter);
                            consumerStatistics consumer = new consumerStatistics(buffer[out]);
                            
                            consumer.updateOverallStatistics(stores, monthlyTotalSales,producers,consumers);
                            out = (out + 1) % bufferSize;
                            counter--;
                            itemCount++;
                            
                        }     

                    
                }
                Instant endTime = Instant.now();
                long duration = Duration.between(startTime, endTime).toMillis();
                float totalAggregateSum = 0;
                for(int i = 0; i < monthlyTotalSales.length; i++){
                    totalAggregateSum += monthlyTotalSales[i];
                }
                try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(String.format("../Producer%dConsumer%dOutput.txt",producers,consumers)))){
                    bufferWriter.write("Statistics for the Run\n");
                    for(int i = 0; i < stores.size(); i++){
                        bufferWriter.write(String.format("\nStore %s Total Sales: %f \n", i + 1, stores.get(i)));
                    }
                    bufferWriter.write(String.format("Monthly Total Sales: \n January: %f \n February: %f \n March: %f \n April: %f \n May: %f \n June: %f \n", 
                    monthlyTotalSales[0],monthlyTotalSales[1], monthlyTotalSales[2], monthlyTotalSales[3], monthlyTotalSales[4],monthlyTotalSales[5]));
                    bufferWriter.write(String.format("July: %f \n August: %f \n September: %f \n October: %f \n November: %f \n December: %f \n", 
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

