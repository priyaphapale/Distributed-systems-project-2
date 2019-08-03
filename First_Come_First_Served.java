package test;

import java.util.*;

public class First_Come_First_Served {
	private ArrayList<Process_Handler> processes;
	private ArrayList<Process_Handler> completed;
	private static int max_quanta = 100;
	private static int PROCESSES_TO_MAKE = 30;
	
	private int quanta;
	private double total_TurnaroundTime;
	private double total_WaitTime;
	private double total_ResponseTime;
	private double processes_Finished;
	private String out;

	public First_Come_First_Served(ArrayList<Process_Handler> processes) {
		this.processes = processes;
		this.completed = new ArrayList<Process_Handler>();
		out="";
		execute();
	}

	public ArrayList<Process_Handler> getProcesses() {
		return this.completed;
	}

	public static void main(String[] args) {
		ArrayList<Process_Handler> processes = new ArrayList<Process_Handler>();
		for (int i = 1; i <= PROCESSES_TO_MAKE; i++) {
			Process_Handler process = new Process_Handler("P" + i, i);
			processes.add(process);
		}
		Process_Handler.arrange_ListBy_ArrivalTime(processes);

		First_Come_First_Served fcfs = new First_Come_First_Served(processes);

		String table = "";
		for (Process_Handler process : fcfs.getProcesses()) {
			table += "[Process: " + String.format("%3s", process.getName()) + ", Arrival time: "
					+ String.format("%3d", process.getArrivalTime()) + ", Run time: "
					+ String.format("%3d", process.getGivenExecutionTime()) + ", Priority: " + process.getPriority()
					+ ", End time: " + String.format("%3d", process.getCompletionTime()) + ", Time started: "
					+ String.format("%3d", process.getExecutionStartTime()) + ", Turnaround time: "
					+ String.format("%3d", process.calculateTurnaroundTime()) + ", Wait time: "
					+ String.format("%3d", process.calculateWaitTime()) + ", Response time: "
					+ String.format("%3d", process.calculateResponseTime()) + "]\n";

		}
		//System.out.println(table);
	}

	// Wait time = startExecutionTime - arrivalTime

	private void execute() {
		quanta = 0;
		total_TurnaroundTime = 0;
		total_WaitTime = 0;
		total_ResponseTime = 0;
		processes_Finished = 0;

		for (Process_Handler process : processes) {
			if (quanta < max_quanta) {
				while (process.getRemainingExecutionTime() > 0) {
					if (process.getArrivalTime() <= quanta) {
						if (process.getRemainingExecutionTime() == process.getGivenExecutionTime()) {
							process.setExecutionStartTime(quanta);
						}
						process.decrementRemainingExecutionTime();
						quanta++;
						out = out+("[" + process.getName() + "]");
					} else {
						quanta++;
						out = out+("[*]");
					}
				}
				
				completed.add(process);
				processes_Finished++;
				process.setCompletionTime(quanta);
				total_TurnaroundTime += process.calculateTurnaroundTime();
				total_WaitTime += process.calculateWaitTime();
				total_ResponseTime += process.calculateResponseTime();
			} else {
				break;
			}
		}

		
	}
	
	public String getAverages()
	{
		String averages = "\n" +"Averages turnaround time: " + total_TurnaroundTime / processes_Finished +"\n"
						+ "Average wait time: " + total_WaitTime / processes_Finished +"\n" +
						"Average response time: " + total_ResponseTime / processes_Finished +"\n"
						+ "Throughput: " + processes_Finished / quanta + " processes completed per quanta." +"\n";
		/*System.out.println("\n");
		System.out.println("Average turnaround time: " + totalTurnaroundTime / processesFinished);
		System.out.println("Average wait time: " + totalWaitTime / processesFinished);
		System.out.println("Average response time: " + totalResponseTime / processesFinished);
		System.out.println("Throughput: " + processesFinished / quanta + " processes completed per quanta.");
		System.out.println();*/
		return  averages;
	}
	
	public String getOut()
	{
		out = out +"\n";
		return out;
	}
}
