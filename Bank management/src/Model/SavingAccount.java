package Model;

import Exceptions.InvalidDepositException;
import Exceptions.InvalidWithdrawalException;

@SuppressWarnings("serial")
public class SavingAccount extends Account {
	
	public SavingAccount(int idAccount, double interest, int noOfMonths) { 
		super(idAccount, "Saving", interest, noOfMonths);
	}
	
	public SavingAccount(int idAccount, int owner, double balance, String type, double interest, int noOfMonths, int noOfActions) {
		super(idAccount, owner, balance, type, interest, noOfMonths, noOfActions);
	}
	
	public SavingAccount(int id) {
		super(id);
	}
	
	public SavingAccount() {}
	
	public void deposit(int amount) throws InvalidDepositException {
		if(this.getNoOfActions() < 1) {
			if(amount>=1000 ) {
				this.setBalance(amount + ((this.getInterest()*amount)/100)*this.getNoOfMonths());
				this.setNoOfActions(this.getNoOfActions() + 1);
				this.notifyAllObservers();
			} else 	
				throw new InvalidDepositException("You need to deposit a larger sum!");
		} else 
			throw new InvalidDepositException("You can only deposit a single time!");
	}
	
	public void withdraw(int amount) throws InvalidWithdrawalException {
		if(this.getNoOfActions()< 2) {
			if(amount==this.getBalance() && amount != 0) {
				this.setBalance(0);
				this.setNoOfActions(this.getNoOfActions() + 1);
				this.notifyAllObservers();
			} else 
				throw new InvalidWithdrawalException("You can only withdraw the whole sum from the bank!");
		} else
			throw new InvalidWithdrawalException("You can only withdraw a single time!");
	}
}
