package test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Tester {
	private static int NUMBER_OF_PROCESSES_TO_MAKE = 30;
	private static PrintWriter writer;

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		writer = new PrintWriter("output.txt", "UTF-8");
		testFirst_Come_First_Served();
		testShortest_Job_First();
		testShortest_Remaining_Time();
		testRound_Robin();
		testHighest_Priority_First_Preemptive();
		testHighest_Priority_First_Non_Preemptive();
		writer.close();
	}

	public static void testFirst_Come_First_Served() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing FCFS.");
		First_Come_First_Served fcfs1 = new First_Come_First_Served(generateList(0));
		writer.write(fcfs1.getAverages() + "\n" + fcfs1.getOut());

		printTable(fcfs1.getProcesses());
		First_Come_First_Served fcfs2 = new First_Come_First_Served(generateList(30));
		writer.write(fcfs2.getAverages() + "\n" + fcfs2.getOut());

		printTable(fcfs2.getProcesses());
		First_Come_First_Served fcfs3 = new First_Come_First_Served(generateList(60));
		writer.write(fcfs3.getAverages() + "\n" + fcfs3.getOut());

		printTable(fcfs3.getProcesses());
		First_Come_First_Served fcfs4 = new First_Come_First_Served(generateList(90));
		writer.write(fcfs4.getAverages() + "\n" + fcfs4.getOut());

		printTable(fcfs4.getProcesses());
		First_Come_First_Served fcfs5 = new First_Come_First_Served(generateList(120));
		writer.write(fcfs5.getAverages() + "\n" + fcfs5.getOut());

		printTable(fcfs5.getProcesses());
	}

	public static void testShortest_Job_First() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing SJF.");
		Shortest_Job_First sjf1 = new Shortest_Job_First(generateList(0));
		writer.write(sjf1.getAverages() + "\n" + sjf1.getOut());

		printTable(sjf1.getProcesses());
		Shortest_Job_First sjf2 = new Shortest_Job_First(generateList(30));
		writer.write(sjf2.getAverages() + "\n" + sjf2.getOut());

		printTable(sjf2.getProcesses());
		Shortest_Job_First sjf3 = new Shortest_Job_First(generateList(60));
		writer.write(sjf3.getAverages() + "\n" + sjf3.getOut());

		printTable(sjf3.getProcesses());
		Shortest_Job_First sjf4 = new Shortest_Job_First(generateList(90));
		writer.write(sjf4.getAverages() + "\n" + sjf4.getOut());

		printTable(sjf4.getProcesses());
		Shortest_Job_First sjf5 = new Shortest_Job_First(generateList(120));
		writer.write(sjf5.getAverages() + "\n" + sjf5.getOut());

		printTable(sjf5.getProcesses());
	}


	public static void testShortest_Remaining_Time() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing SRT.");
		Shortest_Remaining_Time srt = new Shortest_Remaining_Time(generateList(0));
		writer.write(srt.getAverages() + "\n" + srt.getOut());

		printTable(srt.getProcesses());
		srt = new Shortest_Remaining_Time(generateList(30));
		writer.write(srt.getAverages() + "\n" + srt.getOut());

		printTable(srt.getProcesses());
		srt = new Shortest_Remaining_Time(generateList(60));
		writer.write(srt.getAverages() + "\n" + srt.getOut());

		printTable(srt.getProcesses());
		srt = new Shortest_Remaining_Time(generateList(90));
		writer.write(srt.getAverages() + "\n" + srt.getOut());

		printTable(srt.getProcesses());
		srt = new Shortest_Remaining_Time(generateList(120));
		writer.write(srt.getAverages() + "\n" + srt.getOut());

		printTable(srt.getProcesses());
	}

	public static void testRound_Robin() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing RR.");
		Round_Robin rr = new Round_Robin(generateList(0));
		writer.write(rr.getAverages() + "\n" + rr.getOut());

		printTable(rr.getProcesses());
		rr = new Round_Robin(generateList(30));
		writer.write(rr.getAverages() + "\n" + rr.getOut());

		printTable(rr.getProcesses());
		rr = new Round_Robin(generateList(60));
		writer.write(rr.getAverages() + "\n" + rr.getOut());

		printTable(rr.getProcesses());
		rr = new Round_Robin(generateList(90));
		writer.write(rr.getAverages() + "\n" + rr.getOut());

		printTable(rr.getProcesses());
		rr = new Round_Robin(generateList(120));
		writer.write(rr.getAverages() + "\n" + rr.getOut());

		printTable(rr.getProcesses());
	}

	public static void testHighest_Priority_First_Non_Preemptive() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing HPFNP.");
		Highest_Priority_First_Non_Preemptive hpfnp = new Highest_Priority_First_Non_Preemptive(generateList(0));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new Highest_Priority_First_Non_Preemptive(generateList(30));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new Highest_Priority_First_Non_Preemptive(generateList(60));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new Highest_Priority_First_Non_Preemptive(generateList(90));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
		hpfnp = new Highest_Priority_First_Non_Preemptive(generateList(120));
		writer.write(hpfnp.getAverages() + "\n" + hpfnp.getOut());

		printTable(hpfnp.getProcesses());
	}

	public static void testHighest_Priority_First_Preemptive() throws FileNotFoundException, UnsupportedEncodingException {
		writer.write("//- Testing HPFP.");
		Highest_Priority_First_Preemptive hpfp = new Highest_Priority_First_Preemptive(generateList(0));
		writer.write(hpfp.getAverages() + "\n" + hpfp.getOut());

		printTable(hpfp.getProcesses());
		hpfp = new Highest_Priority_First_Preemptive(generateList(30));
		writer.write(hpfp.getAverages() + "\n" + hpfp.getOut());

		printTable(hpfp.getProcesses());
		hpfp = new Highest_Priority_First_Preemptive(generateList(60));
		writer.write(hpfp.getAverages() + "\n" + hpfp.getOut());

		printTable(hpfp.getProcesses());
		hpfp = new Highest_Priority_First_Preemptive(generateList(90));
		writer.write(hpfp.getAverages() + "\n" + hpfp.getOut());

		printTable(hpfp.getProcesses());
		hpfp = new Highest_Priority_First_Preemptive(generateList(120));
		writer.write(hpfp.getAverages() + "\n" + hpfp.getOut());

		printTable(hpfp.getProcesses());
	}

	public static void printTable(List<Process_Handler> processes) throws FileNotFoundException, UnsupportedEncodingException {
		String table = "";
		for (Process_Handler process : processes) {
			table += "[Process: " + String.format("%3s", process.getName()) + ", Arrival time: "
					+ String.format("%3d", process.getArrivalTime()) + ", Run time: "
					+ String.format("%3d", process.getGivenExecutionTime()) + ", Priority: " + process.getPriority()
					+ ", End time: " + String.format("%3d", process.getCompletionTime()) + ", Time started: "
					+ String.format("%3d", process.getExecutionStartTime()) + ", Turnaround time: "
					+ String.format("%3d", process.calculateTurnaroundTime()) + ", Wait time: "
					+ String.format("%3d", process.calculateWaitTime()) + ", Response time: "
					+ String.format("%3d", process.calculateResponseTime()) + "]\n";
		}
		writer.write(table);
		writer.write("\n");
		// System.out.println(table);
	}

	public static double getAverageTurnaroundTime(List<Process_Handler> processes1, List<Process_Handler> processes2,
												  List<Process_Handler> processes3, List<Process_Handler> processes4, List<Process_Handler> processes5) {
		double average = 0;
		double averageP = 0;
		for (Process_Handler p : processes1) {
			averageP += p.calculateTurnaroundTime();
		}
		average += averageP / processes1.size();
		for (Process_Handler p : processes2) {
			averageP += p.calculateTurnaroundTime();
		}
		average += averageP / processes2.size();
		for (Process_Handler p : processes3) {
			averageP += p.calculateTurnaroundTime();
		}
		average += averageP / processes3.size();
		for (Process_Handler p : processes4) {
			averageP += p.calculateTurnaroundTime();
		}
		average += averageP / processes4.size();
		for (Process_Handler p : processes5) {
			averageP += p.calculateTurnaroundTime();
		}
		average += averageP / processes5.size();
		return average / 5;
	}

	public static double getAverageWaitTime(List<Process_Handler> processes1, List<Process_Handler> processes2,
											List<Process_Handler> processes3, List<Process_Handler> processes4, List<Process_Handler> processes5) {
		double average = 0;
		double averageP = 0;
		for (Process_Handler p : processes1) {
			averageP += p.calculateWaitTime();
		}
		average += averageP / processes1.size();
		for (Process_Handler p : processes2) {
			averageP += p.calculateWaitTime();
		}
		average += averageP / processes2.size();
		for (Process_Handler p : processes3) {
			averageP += p.calculateWaitTime();
		}
		average += averageP / processes3.size();
		for (Process_Handler p : processes4) {
			averageP += p.calculateWaitTime();
		}
		average += averageP / processes4.size();
		for (Process_Handler p : processes5) {
			averageP += p.calculateWaitTime();
		}
		average += averageP / processes5.size();
		return average;
	}

	public static double getAverageResponseTime(List<Process_Handler> processes1, List<Process_Handler> processes2,
												List<Process_Handler> processes3, List<Process_Handler> processes4, List<Process_Handler> processes5) {
		double average = 0;
		double averageP = 0;
		for (Process_Handler p : processes1) {
			averageP += p.calculateResponseTime();
		}
		average += averageP / processes1.size();
		for (Process_Handler p : processes2) {
			averageP += p.calculateResponseTime();
		}
		average += averageP / processes2.size();
		for (Process_Handler p : processes3) {
			averageP += p.calculateResponseTime();
		}
		average += averageP / processes3.size();
		for (Process_Handler p : processes4) {
			averageP += p.calculateResponseTime();
		}
		average += averageP / processes4.size();
		for (Process_Handler p : processes5) {
			averageP += p.calculateResponseTime();
		}
		average += averageP / processes5.size();
		return average;
	}

	public static double getAverageThroughput(List<Process_Handler> processes1, List<Process_Handler> processes2,
											  List<Process_Handler> processes3, List<Process_Handler> processes4, List<Process_Handler> processes5) {
		double p1 = processes1.get(processes1.size() - 1).getCompletionTime() / processes1.size();
		double p2 = processes2.get(processes2.size() - 1).getCompletionTime() / processes2.size();
		double p3 = processes3.get(processes3.size() - 1).getCompletionTime() / processes3.size();
		double p4 = processes4.get(processes4.size() - 1).getCompletionTime() / processes4.size();
		double p5 = processes5.get(processes5.size() - 1).getCompletionTime() / processes5.size();
		return (p1 + p2 + p3 + p4 + p5) / 5;
	}

	public static ArrayList<Process_Handler> generateList(int seed) {
		ArrayList<Process_Handler> processes = new ArrayList<Process_Handler>();
		for (int i = 1; i <= NUMBER_OF_PROCESSES_TO_MAKE; i++) {
			Process_Handler process = new Process_Handler("P" + i, i + seed);
			processes.add(process);
		}
		Process_Handler.arrange_ListBy_ArrivalTime(processes);
		return processes;
	}
}