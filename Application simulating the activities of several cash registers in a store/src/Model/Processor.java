package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JTextArea;

public class Processor implements Runnable {
	
	private List<Queue> queues; 
	private List<Thread> threads;
	private int minArrivalTime;
	private int maxArrivalTime;
	private int minServiceTime;
	private int maxServiceTime;
	private int noOfQueues;
	private int speed;
	private static int simulationInterval;
	private static int currentTime; 
	private static int clientId;
	private int peakHour;
	private JTextArea logger;
	
	public Processor(int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, int noOfQueues, int simulationInterval, int speed, JTextArea logger) {
		queues = Collections.synchronizedList(new ArrayList<Queue>());
		threads = new ArrayList<Thread>();
		this.minArrivalTime = minArrivalTime;
		this.maxArrivalTime = maxArrivalTime;
		this.minServiceTime = minServiceTime;
		this.maxServiceTime = maxServiceTime;
		this.noOfQueues = noOfQueues;
		Processor.simulationInterval = simulationInterval;
		Processor.clientId = 0;
		this.speed = speed;
		this.logger = logger;
	}
	
	public Processor() {}
	
	@Override
	public void run() {
		for(int i=0; i<noOfQueues; i++) {
			queues.add(new Queue(i, simulationInterval, speed, logger));
			threads.add(new Thread(queues.get(i)));
			threads.get(i).start();
		}
		
		Client client = this.generateRandomClient();
		int enqueueTime  = client.getArrivalTime();
		int maxNoOfClients = 0;
		
		for(currentTime=1; currentTime<=simulationInterval; currentTime++) {
			try {
				Thread.sleep(speed);
				logger.append("\nTime: " + currentTime);
				for(Queue queue: queues) {
					queue.setCurrentTime(currentTime);
				}
				if(enqueueTime==currentTime) {
					if(currentTime+client.getServiceTime() <= simulationInterval) {
						int queueId = this.chooseNextQueueByCurrentNoOfClients(); 
						queues.get(queueId).addClient(client);
						logger.append(" \nClient " + client.getId() + " has arrived in queue number: " + (queueId + 1)  + " at time: " + enqueueTime + ".  (Arrival time: " + client.getArrivalTime() + " Service time: " + client.getServiceTime() + ")");
						client = this.generateRandomClient();
						enqueueTime = currentTime + client.getArrivalTime();
					} 
				} 
				if(this.getTotalCurrentNumberOfClients() > maxNoOfClients) {
					maxNoOfClients = this.getTotalCurrentNumberOfClients();
					peakHour = currentTime;
				}				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
			
		for(int i=0; i<noOfQueues; i++) 
			threads.get(i).interrupt();
		
		logger.append("\nSimulation is over!\n"); 
		logger.append("Average of empty queue time: " + this.getTotalAverageEmptyQueueTime() + "\n");
		logger.append("Average of service time: " + this.getTotalAverageServiceTime() + "\n");
		logger.append("Average of waiting time: " + this.getTotalAverageWaitingTime() + "\n");
		logger.append("Peak hour: " + peakHour + "\n");
		
	}

	public Client generateRandomClient() {
		int arrivalTime = ThreadLocalRandom.current().nextInt(minArrivalTime, maxArrivalTime + 1);
		int serviceTime = ThreadLocalRandom.current().nextInt(minServiceTime, maxServiceTime + 1);
		return new Client(++clientId, arrivalTime, serviceTime); 
	}
	
	public double getTotalAverageWaitingTime() {
		double sum = 0;
		for(Queue queue : queues) {
			sum += queue.getAverageWaitingTime();
		}
		return sum/noOfQueues;
	}
	
	public double getTotalAverageServiceTime() {
		double sum = 0;
		for(Queue queue : queues) {
			sum += queue.getAverageServiceTime();
		}
		return sum/noOfQueues;
	}
	
	public double getTotalAverageEmptyQueueTime() {
		double sum = 0;
		for(Queue queue : queues) {
			sum += queue.getAverageEmptyQueueTime();
		}
		return sum/noOfQueues;
	}
	
	public int getTotalCurrentNumberOfClients() {
		int sum = 0;
		for(Queue queue : queues) {
			sum += queue.getCurrentNoOfClients();
		}
		return sum;
	}
	
	public int chooseNextQueueByCurrentNoOfClients() {
		int minNoOfClients = queues.get(0).getCurrentNoOfClients(), indexOfQueueWithMinNoOfClients = 0;
		for(int i=1; i<noOfQueues; i++)
			if(queues.get(i).getCurrentNoOfClients() < minNoOfClients) {
				indexOfQueueWithMinNoOfClients = i;
				minNoOfClients = queues.get(i).getCurrentNoOfClients();
			}
		return indexOfQueueWithMinNoOfClients;
	}
	
	public List<Queue> getQueues() {
		return queues;
	}
	
	public int getNoOfQueues() {
		return noOfQueues;
	}

	public static int getSimulationInterval() {
		return simulationInterval;
	}
	
	public static int getCurrentTime() {
		return currentTime;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
