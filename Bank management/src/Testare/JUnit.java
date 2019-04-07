package Testare;

import org.junit.jupiter.api.Test;

import Exceptions.AlreayExistingException;
import Model.Account;
import Model.Bank;
import Model.Person;
import Model.SavingAccount;
import Model.SpendingAccount;

class JUnit {
	
	Bank theBank = new Bank();	

	@Test
	void testAddPerson() {
		Person person = new Person(1, "Andrei", "Jitaru", "andrei_jitaru10@yahoo.com");
		Person person1 = new Person(2, "Vasile", "Jitaru", "vasile_jitaru10@yahoo.com");
		Person person2 = new Person(3, "Andrei", "Moldovan", "andrei_moldovan@yahoo.com");
		try {
			theBank.addPerson(person);
			theBank.addPerson(person1);
			theBank.addPerson(person2);
		} catch (AlreayExistingException e) {
			e.printStackTrace();
		}
	}

	void testAddAccount() {
		Person person1 = theBank.getPersonByCnp(2);
		Person person2 = theBank.getPersonByCnp(3);
		SpendingAccount account = new SpendingAccount(1);
		SavingAccount account1 = new SavingAccount(2, 2, 4);
		SpendingAccount account2 = new SpendingAccount(3);
		try {
			theBank.addAccount(person1, account);
			theBank.addAccount(person1, account1);
			theBank.addAccount(person2, account2);
		} catch (AlreayExistingException e) {
			e.printStackTrace();
		}
		
	}
	
	void testRemovePerson() {
		Person person = theBank.getPersonByCnp(1);
		theBank.removePerson(person);
	}
	
	void testRemoveAccount() {
		Account account1 = theBank.getAccountById(1);
		Account account2 = theBank.getAccountById(3);
		theBank.removeAccount(account1);
		theBank.removeAccount(account2);
	}
	
	void testEditPerson() {
		Person oldPerson = theBank.getPersonByCnp(3);
		Person newPerson = new Person(oldPerson.getCnp(), "Marian", oldPerson.getLastName(), "marian_moldovan@gmail.com");
		try {
			theBank.editPerson(oldPerson, newPerson);
		} catch (AlreayExistingException e) {
			e.printStackTrace();
		}
	}
	
	void testEditAccount() {
		Account oldAccount = theBank.getAccountById(2);
		Account newAccount = new Account(oldAccount.getIdAccount(), 3, 0, oldAccount.getType(), 1, 2, oldAccount.getNoOfActions());
		try {
			theBank.editAccount(oldAccount, newAccount);
		} catch (AlreayExistingException e) {
			e.printStackTrace();
		}
	}
}
