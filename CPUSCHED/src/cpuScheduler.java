package CPUSCHED.src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;

/**
 * Class for CPU
 */
public class cpuScheduler {
    /**
     * First-In, First-Out (FIFO) Scheduling Algorithm
     * @param data
     */
    public static void FIFO(ArrayList<int[]> data){
        int counter = 0;
        int simulatedTime = 0;
        int numberOfProcesses = 0;
        int[] currentProcess = null;
        int[] waitingTimes = new int[500];
        int[] arrivalTimes = new int[500]; 
        int[] burstTimes = new int[500];
        int[] turnaroundTimes = new int[500];
        int[] responseTimes = new int[500];
        System.out.printf("Data size: %s\n",data.size());
        while(numberOfProcesses <= 500 && counter != data.size()){
            if(((simulatedTime >= data.get(counter)[0])) && (currentProcess == null)){
                currentProcess = data.get(counter);
                arrivalTimes[counter] = data.get(counter)[0];
                burstTimes[counter] = data.get(counter)[1];
                waitingTimes[counter] = simulatedTime - arrivalTimes[counter];
                simulatedTime += burstTimes[counter];
                turnaroundTimes[counter] = simulatedTime - arrivalTimes[counter];
                responseTimes[counter] = waitingTimes[counter] - arrivalTimes[counter];
                
                numberOfProcesses += 1;
                currentProcess = null;
                counter += 1;
                
                
            }
            else{
                simulatedTime += 1;
            }
            if(numberOfProcesses == 500){
                break;
            }

            

            
        }
        int totalBurstTime = Arrays.stream(burstTimes).sum();
        int averageWaitingTime = getAverageTime(numberOfProcesses, waitingTimes);
        int throughput = getThroughput(numberOfProcesses, totalBurstTime);
        double cpuUtilization = (double)((totalBurstTime / simulatedTime) * 100);
        int averageTurnaroundTime = getAverageTime(numberOfProcesses, turnaroundTimes);
        int averageResponseTime = getAverageTime(numberOfProcesses, responseTimes);
        extractedAndPrintFCFSOutput(simulatedTime, averageWaitingTime, throughput, cpuUtilization, averageTurnaroundTime,
                averageResponseTime);
        
    }


    /**
     * Extracts and prints output for FCFS scheduling algorithm to a text file
     * @param simulatedTime
     * @param averageWaitingTime
     * @param throughput
     * @param cpuUtilization
     * @param averageTurnaroundTime
     * @param averageResponseTime
     */
    private static void extractedAndPrintFCFSOutput(int simulatedTime, int averageWaitingTime, int throughput, double cpuUtilization,
            int averageTurnaroundTime, int averageResponseTime) {
        try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("../CPUSCHED/FIFO_Output.txt"))){
            bufferWriter.write("Statistics for the Run\n");
            bufferWriter.write(String.format("Total Elapsed Time: %s Simulated Time\n", simulatedTime));
            bufferWriter.write(String.format("Throughput: %s\n", throughput));
            bufferWriter.write(String.format("CPU Utilization: %s Percent\n", cpuUtilization));
            bufferWriter.write(String.format("Average Waiting Time: %s Simulated Time\n", averageWaitingTime));
            bufferWriter.write(String.format("Average Turnaround Time: %s Simulated Time\n", averageTurnaroundTime));
            bufferWriter.write(String.format("Average Response Time: %s Simulated Time\n", averageResponseTime));
            
        }
        catch(IOException e){
            System.out.println("File not found!");
        }
    }

    /**
     * Calcuates average times
     * @param numberOfProcesses
     * @param waitingTimes
     * @return
     */
    private static int getAverageTime(int numberOfProcesses, int[] waitingTimes) {
        return (Arrays.stream(waitingTimes).sum())/numberOfProcesses;
    }

    /**
     * Gets throughput
     * @param numberOfProcesses
     * @param totalBurstTime
     * @return
     */
    private static int getThroughput(int numberOfProcesses, int totalBurstTime) {
        int throughput = totalBurstTime/numberOfProcesses;
        return throughput;
    }
    /**
     * Helps with swapping processes in the waiting queue for SJF scheduling algorithm
     * @param waitingProcesses
     */
    private static void SJF_Swap(ArrayList<int[]> waitingProcesses){
        for(int i = 0; i < waitingProcesses.size(); i++){
            for(int j = 0; j < waitingProcesses.size(); j++){
                if(waitingProcesses.get(i)[1] > waitingProcesses.get(j)[1]){
                    int[] temporary = waitingProcesses.get(i);
                    waitingProcesses.set(i,waitingProcesses.get(j));
                    waitingProcesses.set(j, temporary);
                }
            }
        }
    }

   
    /**
     * Shortest Job First (SJF) Scheduling Algorithm
     * @param data
     */
    public static void SJF(ArrayList<int[]> data){
        int counter = 0;
        int simulatedTime = 0;
        int numberOfProcesses = 0;
        
        
        ArrayList<int[]> waitingProcesses = new ArrayList<>();
        int[] currentProcess = null;
        int[] waitingTimes = new int[500]; 
        int[] arrivalTimes = new int[500];
        int[] burstTimes = new int[500];
        int[] turnaroundTimes = new int[500];
        int[] responseTimes = new int[500];
        while(numberOfProcesses < 500 && counter != data.size()){
            if(numberOfProcesses == 500 || counter == data.size()){
                break;
            }
            if(waitingProcesses.size() == 0){
                currentProcess = data.get(counter);
                simulatedTime = currentProcess[0];
            }
            if(data.get(counter)[0] <= simulatedTime){
                waitingProcesses.add(data.get(counter));
                counter += 1;
            }
            else{
                simulatedTime += 1;

            }

            if(currentProcess != null){
                if(counter >= 500 || numberOfProcesses >= 500){
                    break;
                }
                arrivalTimes[counter] = currentProcess[0];
                waitingTimes[counter] = simulatedTime - arrivalTimes[counter];
                burstTimes[counter] = currentProcess[1];
                simulatedTime += burstTimes[counter];
                turnaroundTimes[counter] = simulatedTime - arrivalTimes[counter];
                responseTimes[counter] = waitingTimes[counter] - arrivalTimes[counter];
                
                
                numberOfProcesses += 1;
                counter++;
                currentProcess = null;
                
                
                if(waitingProcesses.size() > 1){
                    SJF_Swap(waitingProcesses);
                    currentProcess = waitingProcesses.get(0);
                    waitingProcesses.remove(0);
                }
                else{
                    currentProcess = waitingProcesses.get(0);
                    waitingProcesses.remove(0);
                }
               
            }
            
        }
        
        
        
        int totalBurstTime = Arrays.stream(burstTimes).sum();
        int averageWaitingTime = getAverageTime(numberOfProcesses, waitingTimes);
        int throughput = getThroughput(numberOfProcesses, totalBurstTime);
        double cpuUtilization = (double)((totalBurstTime / simulatedTime) * 100);
        int averageTurnaroundTime = getAverageTime(numberOfProcesses, turnaroundTimes);
        int averageResponseTime = getAverageTime(numberOfProcesses, responseTimes);
        extractAndPrintSJFTextOutput(simulatedTime, averageWaitingTime, throughput, cpuUtilization, averageTurnaroundTime,
                averageResponseTime);

    }

    /**
     * Extracts and prints output for SJF scheduling algorithm to a text file
     * @param simulatedTime
     * @param averageWaitingTime
     * @param throughput
     * @param cpuUtilization
     * @param averageTurnaroundTime
     * @param averageResponseTime
     */
    private static void extractAndPrintSJFTextOutput(int simulatedTime, int averageWaitingTime, int throughput, double cpuUtilization,
            int averageTurnaroundTime, int averageResponseTime) {
        try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("../CPUSCHED/SJF_Output.txt"))){
            bufferWriter.write("Statistics for the Run\n");
            bufferWriter.write(String.format("Total Elapsed Time: %s Simulated Time\n", simulatedTime));
            bufferWriter.write(String.format("Throughput: %s\n", throughput));
            bufferWriter.write(String.format("CPU Utilization: %s Percent\n", cpuUtilization));
            bufferWriter.write(String.format("Average Waiting Time: %s Simulated Time\n", averageWaitingTime));
            bufferWriter.write(String.format("Average Turnaround Time: %s Simulated Time\n", averageTurnaroundTime));
            bufferWriter.write(String.format("Average Response Time: %s Simulated Time\n", averageResponseTime));
            
        }
        catch(IOException e){
            System.out.println("File not found!");
        }
    }
    
    public static void main(String[] args) throws Exception {
        Path filePath = Paths.get("../CPUSCHED/src/datafile-txt.txt");
        List<String> times = Files.readAllLines(filePath);
        ArrayList<int[]> processes = new ArrayList<>();
        for(String time: times){
            if(time.equals("ArrivalTime\tCPUBurstlength")){
                continue;
            }
            else{
                String[] parts = time.split("\\s+");
                int arrival = Integer.parseInt(parts[0]);
                int burst = Integer.parseInt(parts[1]);
                int[] process = {arrival, burst};
                processes.add(process);
            }
            
        }
        
        Scanner schedulingInputs = new Scanner(System.in);
        System.out.println("Select A Scheduling Style You Want to Simulate:  (FIFO/SJF)");
        String schedulingSelection = schedulingInputs.nextLine();
        System.out.println(schedulingSelection);
        if(schedulingSelection.equals("FIFO")){
            FIFO(processes);
        }
        else if (schedulingSelection.equals("SJF")){
            SJF(processes);
        }
        else{
            System.out.println("Invalid Input!");
        }
        schedulingInputs.close();

        

    }
}
