package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
/**
 * Consumer class for statistics
 */
public class consumerStatistics {
    SalesRecord saleRecord;
    /**
     * Basic Constructor with no inputted salesRecord
     */
    public consumerStatistics(){
        this.saleRecord = null;
    }
    /**
     * Constructor with salesRecord set
     * @param salesRecord
     */
    public consumerStatistics(SalesRecord salesRecord){
        this.saleRecord = salesRecord;
    }
    /**
     * Sets sales record
     * @param saleRecord
     */
    public void setSalesRecord(SalesRecord saleRecord){
        this.saleRecord = saleRecord;
    }
    /**
     * Obtains sales record
     * @return saleRecord
     */
    public SalesRecord getSalesRecord(){
        return saleRecord;
    }
    /**
     * Updates Global Statistcs for Run
     * @param storeIncome
     * @param monthlyIncome
     * @param producers
     * @param consumers
     */
    public void updateOverallStatistics(ArrayList<Float> storeIncome, float[] monthlyIncome, int producers, int consumers){
        SalesRecord record = getSalesRecord();
        float currentStoreIncome = storeIncome.get(record.getStoreID() - 1) +record.saleAmount;
        storeIncome.set(record.getStoreID() - 1, currentStoreIncome);
        monthlyIncome[record.mM - 1] += record.saleAmount;
        try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(String.format(".../PRODUCER-CONSUMER/Producer%sConsumer%dOutput.txt",producers,consumers)))){
            bufferWriter.write("\nStatistics for the Run\n");
            bufferWriter.write(String.format("Date: %s/%d/%f \n",record.getMM(),record.getDD(),record.getYY()));
            bufferWriter.write(String.format("Store ID: %s \n", record.getStoreID()));
            bufferWriter.write(String.format("Register Number: %s \n", record.getRegisterNumber()));
            bufferWriter.write(String.format("Sale Amount: %s \n", record.getSaleAmount()));
            
        }
        catch(IOException e){
            System.out.println("File not found!");
        }
    }
    
}
