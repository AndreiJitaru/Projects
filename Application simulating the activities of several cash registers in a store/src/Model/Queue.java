package Model;

import java.util.concurrent.*;
import javax.swing.JTextArea;

public class Queue implements Runnable {
	
	private BlockingQueue<Client> clients;
	@SuppressWarnings("unused")
	private int queueId;
	private int totalWaitingTime;
	private int totalServiceTime;
	private int emptyQueueTime;
	private int noOfClients;
	private int currentTime;
	private int simulationInterval;
	private int lastServiceEndTime;
	private int speed;
	private JTextArea logger;

	public Queue(int queueId, int simulationInterval, int speed, JTextArea logger) {
		clients = new LinkedBlockingQueue<Client>();
		this.queueId = queueId;
		this.simulationInterval = simulationInterval;
		this.speed = speed;
		totalWaitingTime = 0;
		totalServiceTime = 0;
		emptyQueueTime = 0;
		noOfClients = 0;
		currentTime = 1;
		lastServiceEndTime = 1;
		this.logger = logger;
	}
	
	@Override
	public void run() {
		while(currentTime <= simulationInterval) {
			if(clients.isEmpty()==false) {
				Client client = clients.peek();
				if((client.getServiceTime() + currentTime <= simulationInterval)) {
					noOfClients++;
					totalServiceTime += client.getServiceTime();
					try {
						Thread.sleep(client.getServiceTime()*speed);
					} catch (InterruptedException e) { }
					clients.poll();
					logger.append(" \nClient " + client.getId() + " left at time " + currentTime);
				}
			} else {		
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				lastServiceEndTime = currentTime;	
				emptyQueueTime++;
			}
		}
	}

	public void addClient(Client newClient) {
		clients.add(newClient);
		lastServiceEndTime += newClient.getServiceTime();
		totalWaitingTime += (lastServiceEndTime - currentTime);
	}
	
	public String displayClients() {
		String s = "";
		if(!clients.isEmpty())
			for(Client client : clients)
				s += "Client" + client.getId() + "\n";
		return s;
	}

	public double getAverageServiceTime() {
		if(noOfClients!=0)
			return (double)totalServiceTime/noOfClients;
		return 0;
	}
	
	public double getAverageEmptyQueueTime() {
		if(noOfClients!=0)
			return (double)emptyQueueTime/noOfClients;
		return 0;
	}
	
	public double getAverageWaitingTime() {
		if(noOfClients!=0)
			return (double)totalWaitingTime/noOfClients;
		return 0;
	}

	public int getNoOfClients() {
		return noOfClients;
	}

	public int getCurrentNoOfClients() {
		if(!clients.isEmpty())
			return clients.size();
		return 0;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
