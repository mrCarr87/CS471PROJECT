package CPUSCHED.src;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.io.IOException;


public class cpuScheduler {
    /**
     * First-Come, First-Served (FCFS) Scheduling Algorithm
     * @param data
     */
    public static void FCFS(ArrayList<int[]> data){
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
                waitingTimes[counter] = simulatedTime;
                turnaroundTimes[counter] = burstTimes[counter] + waitingTimes[counter];
                responseTimes[counter] = waitingTimes[counter] - arrivalTimes[counter];
                System.out.printf("Counter: %s\n",counter);
                System.out.printf("Simulated Time: %s\n",simulatedTime);
                System.out.printf("Current Burst Time Sum: %s\n",Arrays.stream(burstTimes).sum());
                System.out.printf("Number of Processes: %s \n",numberOfProcesses);
                System.out.printf("Current Process: %s\n",currentProcess);
                System.out.printf("Arrival Time: %s\n", arrivalTimes[counter]);
                System.out.printf("Waiting Time: %s\n",waitingTimes[counter]);
                System.out.printf("Turnaround Time: %s\n",turnaroundTimes[counter]);
                System.out.printf("Response Time: %s\n",responseTimes[counter]);
                simulatedTime += burstTimes[counter];
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
        try(BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("../CPUSCHED/FCFS_Output.txt"))){
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


    private static int getAverageTime(int numberOfProcesses, int[] waitingTimes) {
        return (Arrays.stream(waitingTimes).sum())/numberOfProcesses;
    }


    private static int getThroughput(int numberOfProcesses, int totalBurstTime) {
        int throughput = totalBurstTime/numberOfProcesses;
        return throughput;
    }

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
        
        ArrayList<int[]> currentProcesses = new ArrayList<>();
        int[] waitingProcesses;
        int[] currentProcess = null;
        int[] waitingTimes = new int[500]; 
        int[] arrivalTimes = new int[500];
        int[] burstTimes = new int[500];
        int[] turnaroundTimes = new int[500];
        int[] responseTimes = new int[500];
        while(numberOfProcesses < 500 && counter != data.size()){
            if(simulatedTime == data.get(counter)[0]){
                currentProcess = data.get(counter);
                currentProcesses.add(currentProcess);
                if(currentProcesses.size() == 1){
                    
                }
                else{
                    for(int i = 0; i < currentProcesses.size(); i++){
                        for(int j = 0; j< currentProcesses.size(); j++){
                            System.out.println("null");
                        }
                    }
                }
                
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
            FCFS(processes);
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
