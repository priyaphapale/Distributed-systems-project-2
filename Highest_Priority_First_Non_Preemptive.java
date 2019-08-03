package test;

import java.util.*;

public class Highest_Priority_First_Non_Preemptive {
	private ArrayList<Process_Handler> processes;
	private ArrayList<Process_Handler> completed;
	private Queue<Process_Handler> q1;
	private Queue<Process_Handler> q2;
	private Queue<Process_Handler> q3;
	private Queue<Process_Handler> q4;
	private Process_Handler current;
	private static int max_quanta = 100;
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
		Highest_Priority_First_Non_Preemptive hpfnp = new Highest_Priority_First_Non_Preemptive(processes);

		String table = "";
		for (Process_Handler process : hpfnp.getProcesses()) {
			table += "[Process: " + String.format("%3s", process.getName()) + ", Arrival time: "
					+ String.format("%3d", process.getArrivalTime()) + ", Run time: "
					+ String.format("%3d", process.getGivenExecutionTime()) + ", Priority: " + process.getPriority()
					+ ", End time: " + String.format("%3d", process.getCompletionTime()) + ", Time started: "
					+ String.format("%3d", process.getExecutionStartTime()) + ", Turnaround time: "
					+ String.format("%3d", process.calculateTurnaroundTime()) + ", Wait time: "
					+ String.format("%3d", process.calculateWaitTime()) + ", Response time: "
					+ String.format("%3d", process.calculateResponseTime()) + "]\n";

		}
		System.out.println(table);
	}

	public ArrayList<Process_Handler> getProcesses() {
		return this.completed;
	}

	public Highest_Priority_First_Non_Preemptive(ArrayList<Process_Handler> processes) {
		this.processes = processes;
		this.completed = new ArrayList<Process_Handler>();
		q1 = new LinkedList<Process_Handler>();
		q2 = new LinkedList<Process_Handler>();
		q3 = new LinkedList<Process_Handler>();
		q4 = new LinkedList<Process_Handler>();
		out ="";
		execute();
	}

	private void execute() {
		quanta = 0;
		total_TurnaroundTime = 0;
		total_ResponseTime = 0;
		processes_Finished = 0;
		total_WaitTime = 0;

		// Check the current time quanta, and add processes based on priority that arrive at the current time to the q's
		while (quanta < max_quanta) {
			for (Process_Handler process : processes) {
				if (process.getArrivalTime() <= quanta) {
					if (process.getPriority() == 1) {
						q1.add(process);
					} else if (process.getPriority() == 2) {
						q2.add(process);
					} else if (process.getPriority() == 3) {
						q3.add(process);
					} else if (process.getPriority() == 4) {
						q4.add(process);
					} else {
						System.exit(1); // getting a non-existant priority.
					}
				}
			}
			processes.removeAll(q1);
			processes.removeAll(q2);
			processes.removeAll(q3);
			processes.removeAll(q4);

			//non preemptive block of code
			// Get a process from the q's from priority 1 to 4
			if (!q1.isEmpty() || !q2.isEmpty() || !q3.isEmpty() || !q4.isEmpty()) {
				if (!q1.isEmpty()) {
					current = q1.remove();
				} else if (!q2.isEmpty()) {
					current = q2.remove();
				} else if (!q3.isEmpty()) {
					current = q3.remove();
				} else if (!q4.isEmpty()) {
					current = q4.remove();
				} else {
					System.exit(0);
				}
				// If the current shortest process has not started yet, start it.
				current.setExecutionStartTime(quanta);
				//once 1 quanta is passed, decrements the quanta of that process and adds to time line 
				while (current.getRemainingExecutionTime() > 0) {
					current.decrementRemainingExecutionTime();
					quanta++;
					out = out +("[" + current.getName() + "]");
				}
				current.setCompletionTime(quanta);
				//adds unfinished but decremented process back to its priority queue
				completed.add(current);

				processes_Finished++;
				total_TurnaroundTime += current.calculateTurnaroundTime();
				total_WaitTime += current.calculateWaitTime();
				total_ResponseTime += current.calculateResponseTime();
			} else {
				out =out +("[*]");
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
