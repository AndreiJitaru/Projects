package Model;

public class Client {
	
	private int idClient;
	private int arrivalTime;
	private int serviceTime;

	public Client(int idClient,int arrivalTime, int serviceTime){
		this.idClient = idClient;
		this.arrivalTime = arrivalTime;
		this.serviceTime = serviceTime;
	}
	
	public Client() {}

	public int getId() {
		return idClient;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}
}
