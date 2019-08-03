package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Process_Handler {
	private int arrival_time;
	private int execution_time;
	private int remaining_execution_time;
	private int execution_start_time;
	private int completion_time;
	private int priority;
	private int wait_time;
	private Random random;
	private String name;

	private static int MAX_WAIT_TIME = 5;
	private static int max_priority = 1;

	public Process_Handler(int seed) {
		this.name = "";
		random = new Random(seed);
		arrival_time = generateIntTime(99);
		execution_time = generateIntTime(10) + 1;
		remaining_execution_time = execution_time;
		priority = random.nextInt(4) + 1;
		wait_time = 0;

		completion_time = -1;
		execution_start_time = -1;
	}

	private int generateIntTime(int range) {
		return random.nextInt(range);
	}

	// Turnaround time – time from arrival to completion.
	// Response time – time from arrival to the first instance of output.
	// Execution time – time from start of execution
	// Throughput is the number of jobs per hour that the system completes

	public Process_Handler(String name, int seed) {
		this.name = name;
		random = new Random(seed);
		arrival_time = generateIntTime(99);
		execution_time = generateIntTime(10) + 1;
		remaining_execution_time = execution_time;
		priority = random.nextInt(4) + 1;

		completion_time = -1;
		execution_start_time = -1;
	}
	public static void arrange_ListBy_ArrivalTime(ArrayList<Process_Handler> list) {
		Comparator<Process_Handler> comparator = new Comparator<Process_Handler>() {
			public int compare(Process_Handler process1, Process_Handler process2) {
				if (process1.arrival_time < process2.arrival_time) {
					return -1;
				} else if (process1.arrival_time > process2.arrival_time) {
					return 1;
				} else {
					return 0;
				}
			}
		};
		Collections.sort(list, comparator);
	}

	public static void arrange_ListBy_ExecutionTime(ArrayList<Process_Handler> list) {
		Comparator<Process_Handler> comparator = new Comparator<Process_Handler>() {
			public int compare(Process_Handler process1, Process_Handler process2) {
				if (process1.execution_time < process2.execution_time) {
					return -1;
				} else if (process1.execution_time > process2.execution_time) {
					return 1;
				} else {
					return 0;
				}
			}
		};
		Collections.sort(list, comparator);
	}

	public void setArrivalTime(int arrival_time) {
		this.arrival_time = arrival_time;
	}

	public void setGivenExecutionTime(int execution_time) {
		this.execution_time = execution_time;
	}

	public void setRemainingExecutionTime(int remaining_execution_time) {
		this.remaining_execution_time = remaining_execution_time;
	}

	public void setExecutionStartTime(int execution_start_time) {
		this.execution_start_time = execution_start_time;
	}

	public void setCompletionTime(int completion_time) {
		this.completion_time = completion_time;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getArrivalTime() {
		return arrival_time;
	}

	public int getGivenExecutionTime() {
		return execution_time;
	}

	public int getRemainingExecutionTime() {
		return remaining_execution_time;
	}

	public int getExecutionStartTime() {
		return execution_start_time;
	}

	public int getCompletionTime() {
		return completion_time;
	}

	public int getPriority() {
		return priority;
	}

	public String getName() {
		return name;
	}

	public void decrementRemainingExecutionTime() {
		remaining_execution_time--;
	}

	public void incrementWaitTime() {
		wait_time++;
		if (wait_time >= MAX_WAIT_TIME) {
			increasePriority();
		}
	}

	public int wait_time() {
		return wait_time;
	}

	public void increasePriority() {
		if (priority > max_priority) {
			priority--;
		}
	}

	public int calculateTurnaroundTime() {
		return Math.max(completion_time - arrival_time, 0);
	}

	public int calculateWaitTime() {
		return Math.max(calculateTurnaroundTime() - execution_time, 0);
	}

	public int calculateResponseTime() {
		return Math.max(execution_start_time - arrival_time, 0);
	}

	/*
	 * public Process clone() { Process process = new Process();
	 * process.setArrivalTime(arrival_time);
	 * process.setGivenExecutionTime(execution_time); process.setName(name);
	 * process.setPriority(priority); return process; }
	 */
}

