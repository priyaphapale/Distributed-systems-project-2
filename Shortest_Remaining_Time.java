package test;

import java.util.*;

public class Shortest_Remaining_Time {
	private ArrayList<Process_Handler> processes;
	private ArrayList<Process_Handler> wait_queue;
	private Process_Handler shortest;
	private ArrayList<Process_Handler> completed;
	private static int max_quanta = 99;
	private static int PROCESSES_TO_MAKE = 30;
	
	private int quanta;
	private double total_TurnaroundTime;
	private double total_WaitTime;
	private double total_ResponseTime;
	private double processes_Finished;
	private String out;

	public static void main(String[] args) {
		ArrayList<Process_Handler> processes = new ArrayList<Process_Handler>();
		for (int i = 1; i <= PROCESSES_TO_MAKE; i++) {
			Process_Handler process = new Process_Handler("P" + i, i);
			processes.add(process);
		}
		Process_Handler.arrange_ListBy_ArrivalTime(processes);

		Shortest_Remaining_Time srt = new Shortest_Remaining_Time(processes);
		String table = "";
		for (Process_Handler process : srt.getProcesses()) {
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

	public Shortest_Remaining_Time(ArrayList<Process_Handler> processes) {
		this.processes = processes;
		this.wait_queue = new ArrayList<Process_Handler>();
		this.shortest = null;
		this.completed = new ArrayList<Process_Handler>();
		
		run();
		
	}

	public ArrayList<Process_Handler> getProcesses() {
		return this.completed;
	}

	private void run() {
		 quanta = 0;
		 total_TurnaroundTime = 0;
		 total_WaitTime = 0;
		 total_ResponseTime = 0;
		 processes_Finished = 0;
		 out = "";
		 
		 
		while (quanta < max_quanta || !wait_queue.isEmpty()) {
			// Check the current time quanta, and add processes that arrive at the current time to the wait_queue.
			for (Process_Handler process : processes) {
				if (process.getArrivalTime() <= quanta) {
					wait_queue.add(process);
					// processes.remove(process);
				}
			}
			processes.removeAll(wait_queue);

			// Preemptive code block.
			if (!wait_queue.isEmpty()) {
				// Get the process in the queue with the lowest remaining execution time.
				shortest = wait_queue.get(0);
				for (Process_Handler process : wait_queue) {
					if (process.getRemainingExecutionTime() < shortest.getRemainingExecutionTime()) {
						shortest = process;
					}
				}
				wait_queue.remove(shortest);
				
				// If the current shortest process has not started yet, start it.
				if (shortest.getExecutionStartTime() < 0 && quanta < max_quanta) {
					shortest.setExecutionStartTime(quanta);
				}
				
				// If the process has started
				if (shortest.getExecutionStartTime() > -1) {
					// Represent it in the timeline
					out = out+ "[" + shortest.getName() + "]";
					// Decrement the execution time remaining
					shortest.decrementRemainingExecutionTime();
					// Move the time splice
					quanta++;

					// If the shortest process is done (execution time remaining == 0)
					if (shortest.getRemainingExecutionTime() <= 0) {
						// Set its end time at the current quanta
						shortest.setCompletionTime(quanta);
						// Add the finished process to the completed list.
						completed.add(shortest);

						processes_Finished++;
						total_TurnaroundTime += shortest.calculateTurnaroundTime();
						total_WaitTime += shortest.calculateWaitTime();
						total_ResponseTime += shortest.calculateResponseTime();
					} else {
						// The process is not done, add it back into the queue.
						wait_queue.add(shortest);
					}
				}
			} else {
				out = out+"[*]";
				quanta++;
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
		System.out.println("Average turnaround time: " + total_TurnaroundTime / processes_Finished);
		System.out.println("Average wait time: " + total_WaitTime / processes_Finished);
		System.out.println("Average response time: " + total_ResponseTime / processes_Finished);
		System.out.println("Throughput: " + processes_Finished / quanta + " processes completed per quanta.");
		System.out.println();*/
		return  averages;
	}
	
	public String getOut()
	{
		out = out +"\n";
		return out;
	}
}