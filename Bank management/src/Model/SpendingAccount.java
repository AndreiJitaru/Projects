package Model;

import Exceptions.InvalidWithdrawalException;

@SuppressWarnings("serial")
public class SpendingAccount extends Account{
	
	public SpendingAccount(int idAccount) { // create a "full" SpendingAccount
		super(idAccount, "Spending", 0, 0);
	}
	
	public SpendingAccount(int idAccount, int owner, double balance, String type, double interest, int noOfMonths, int noOfActions) {
		super(idAccount, owner, balance, type, interest, noOfMonths, noOfActions);
	}
	
	public SpendingAccount() {}
	
	public void deposit(int amount){
		this.setBalance(this.getBalance() + amount);
		this.notifyAllObservers();
	}
	
	public void withdraw(int amount) throws InvalidWithdrawalException {
		if(this.getBalance() > amount) {
			this.setBalance(this.getBalance() - amount);
			this.notifyAllObservers();
		}
		else 
			throw new InvalidWithdrawalException("Insuficient balance!");
	}
	
}
