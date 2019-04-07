package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class Account extends Observable implements Serializable{
	private int idAccount;
	private int owner;
	private double balance;
	private String type;
	private double interest;
	private int noOfMonths;
	private int noOfActions;
	
	ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public Account(int idAccount, String type, double interest, int noOfMonths) {
		this.idAccount = idAccount;
		this.balance = 0;
		this.type = type;
		this.interest = interest;
		this.noOfMonths = noOfMonths;
		this.noOfActions = 0;
	}
	
	public Account(int idAccount, int owner, double balance, String type, double interest, int noOfMonths,
			int noOfActions) {
		this.idAccount = idAccount;
		this.owner = owner;
		this.balance = balance;
		this.type = type;
		this.interest = interest;
		this.noOfMonths = noOfMonths;
		this.noOfActions = noOfActions;
	}

	public Account(int id) {
		this.idAccount = id;
	}
	
	public Account() { }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAccount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Account other = (Account) obj;
		if (idAccount != other.idAccount)
			return false;
		return true;
	}

	public void register(Observer observer) {
		boolean ok = true;
		for(Observer observerToCheck : observers)
			if(observerToCheck.equals(observer))
				ok = false;
		if(ok==true)
			observers.add(observer);
	}
	
	public void unregister(Observer observer) {
		observers.remove(observer);
	}
	
	public void notifyAllObservers() {
		for(Observer observer : observers) 
			observer.update(this, observer);
	}
	
	public int getNoOfActions() {
		return noOfActions;
	}

	public void setNoOfActions(int noOfActions) {
		this.noOfActions = noOfActions;
	}

	public int getIdAccount() {
		return idAccount;
	}
	
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getInterest() {
		return interest;
	}

	public int getNoOfMonths() {
		return noOfMonths;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}
}
