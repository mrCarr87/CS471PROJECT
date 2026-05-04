package src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

public class consumerStatistics {
    SalesRecord saleRecord;
    public consumerStatistics(){
        this.saleRecord = null;
    }
    public consumerStatistics(SalesRecord salesRecord){
        this.saleRecord = salesRecord;
    }
    public void setSalesRecord(SalesRecord saleRecord){
        this.saleRecord = saleRecord;
    }
    public SalesRecord getSalesRecord(){
        return saleRecord;
    }
    public void updateOverallStatistics(Buffer buffer, ArrayList<Float> storeIncome, float[] monthlyIncome){
        SalesRecord record = getSalesRecord();
        float currentStoreIncome = storeIncome.get(record.getStoreID() - 1) +record.saleAmount;
        storeIncome.set(record.getStoreID() - 1, currentStoreIncome);
        monthlyIncome[record.mM - 1] += record.saleAmount;
        try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("../PRODUCER-CONSUMER/Producer_Consumer_Output.txt"))){
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
