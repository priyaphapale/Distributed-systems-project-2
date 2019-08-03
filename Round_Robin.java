package test;
import java.util.*;

public class Round_Robin {
	private ArrayList<Process_Handler> processes;
	private ArrayList<Process_Handler> wait_queue;
	private ArrayList<Process_Handler> completed;
	private Process_Handler current;
	private static int max_quanta = 99;
	private static int PROCESSES_TO_MAKE = 30;

	
	private int quanta;
	private double total_TurnaroundTime;
	private double total_WaitTime;
	private double total_ResponseTime;
	private double processes_Finished;
	private String out;
	
	public Round_Robin(ArrayList<Process_Handler> processes) {
		this.processes = processes;
		this.wait_queue = new ArrayList<Process_Handler>();
		this.completed = new ArrayList<Process_Handler>();
		out="";
		execute();
	}

	public static void main(String[] args) {
		ArrayList<Process_Handler> processes = new ArrayList<Process_Handler>();
		for (int i = 1; i <= PROCESSES_TO_MAKE; i++) {
			Process_Handler process = new Process_Handler("P" + i, i);
			processes.add(process);
		}
		Process_Handler.arrange_ListBy_ArrivalTime(processes);

		Round_Robin rr = new Round_Robin(processes);

		String table = "";
		for (Process_Handler process : rr.getProcesses()) {
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

	public void execute() {
		quanta = 0;
		total_TurnaroundTime = 0;
		total_WaitTime = 0;
		total_ResponseTime = 0;
		processes_Finished = 0;

		while (quanta < max_quanta || !wait_queue.isEmpty()) {
			if (quanta < max_quanta) {
				// Check the current time quanta, and add processes that arrive at the current time to the queue.
				for (Process_Handler process : processes) {
					if (process.getArrivalTime() <= quanta) {
						wait_queue.add(process);
						// processes.remove(process);
					}
				}
				processes.removeAll(wait_queue);
			}
			
			// Preemptive code block.
			if (!wait_queue.isEmpty()) {
				// Get the process in the queue with the lowest remaining execution time.
				current = wait_queue.remove(0);
				// If the current shortest process has not started yet, start it.
				if (current.getExecutionStartTime() < 0 && quanta < max_quanta) {
					current.setExecutionStartTime(quanta);
				}
				
				// If the process has started
				if (current.getExecutionStartTime() > -1) {
					// Represent it in the timeline
					out =out + ("[" + current.getName() + "]");
					// Decrement the execution time remaining
					current.decrementRemainingExecutionTime();
					// Move the time splice
					quanta++;

					// If the shortest process is done (execution time remaining == 0)
					if (current.getRemainingExecutionTime() <= 0) {
						// Set its end time at the current quanta
						current.setCompletionTime(quanta);
						// Add the finished process to the completed list.
						completed.add(current);

						processes_Finished++;
						total_TurnaroundTime += current.calculateTurnaroundTime();
						total_WaitTime += current.calculateWaitTime();
						total_ResponseTime += current.calculateResponseTime();
					} else {
						// The process is not done, add it back into the queue.
						wait_queue.add(current);
					}
				}
			} else {
				out = out + ("[*]");
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

