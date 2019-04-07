package Model;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import Exceptions.AlreayExistingException;

public class Bank implements BankProc, Serializable {
	
	private static final long serialVersionUID = 1L; 
	private HashMap<Person, ArrayList<Account>> bankHashMap = new HashMap<Person, ArrayList<Account>>();
	
	public void addPerson(Person person) throws AlreayExistingException {
		assert person != null;
		
		boolean checkIfPersonExists = false;
		
		for (Entry<Person, ArrayList<Account>> entry : bankHashMap.entrySet()) {
			if(entry.getKey().getCnp()==person.getCnp()) {
				checkIfPersonExists = true;
				break;
			}
		}
		if(checkIfPersonExists==false) 
			bankHashMap.put(person, null);
		else 
			throw new AlreayExistingException("This person already exits in the Bank!");
		
		assert bankHashMap.get(person) == null;
	}
	
	public void addAccount(Person person, Account accountToAdd) throws AlreayExistingException {
		assert person != null && accountToAdd != null;
		
		boolean checkIfAccountExists = false; 
		for (Entry<Person, ArrayList<Account>> entry : bankHashMap.entrySet()) {
			ArrayList<Account> listOfAccounts = entry.getValue();
			if(listOfAccounts != null) {
				for(Iterator<Account> i = listOfAccounts.iterator(); i.hasNext();) {
					Account accountToCheck = i.next();
					if(accountToCheck.equals(accountToAdd)) {
						checkIfAccountExists = true;
						break;
					}
				}
			}
		}
		if(checkIfAccountExists==false) {
			accountToAdd.setOwner(person.getCnp());
			ArrayList<Account> list = bankHashMap.get(person);
			if(list!=null) { 
				list.add(accountToAdd);
				bankHashMap.put(person, list);
			} else {
				ArrayList<Account> newList = new ArrayList<Account>();
				newList.add(accountToAdd);
				bankHashMap.put(person, newList);
			}
			accountToAdd.register((Observer)person);
		} else
			throw new AlreayExistingException("This account already exist in the bank!");
		
		assert bankHashMap.get(person) != null;
	}
	
	public void removePerson(Person person) {
		assert person != null;
		
		bankHashMap.remove(person);
		
		assert bankHashMap.get(person) == null;
	}

	public void removeAccount(Account accountToDelete) {
		assert accountToDelete != null;
		
		Person person = this.getPersonByCnp(accountToDelete.getOwner());
		ArrayList<Account> list = bankHashMap.get(person);
		int sizeBeforeRemoval = list.size();
		for(int i=0; i<list.size();i++)
			if(list.get(i).equals(accountToDelete)) 
				list.remove(list.get(i));
		
		assert bankHashMap.get(person).size() == (sizeBeforeRemoval-1);
	}
	
	public void editPerson(Person oldPerson, Person newPerson) throws AlreayExistingException {
		assert oldPerson != null && newPerson != null;
		
		int sizeBeforeRemoval = bankHashMap.size();
		ArrayList<Account> listOfAccounts = bankHashMap.get(oldPerson);
		this.removePerson(oldPerson);
		this.addPerson(newPerson);
		if(listOfAccounts!= null)
			for(Iterator<Account> i = listOfAccounts.iterator();i.hasNext();) {
				Account account = i.next();
				int index = listOfAccounts.indexOf(account);
				account.setOwner(newPerson.getCnp());
				listOfAccounts.set(index, account);
			}
		bankHashMap.put(newPerson, listOfAccounts);
		
		assert bankHashMap.size() == sizeBeforeRemoval;
	}
	
	public void editAccount(Account oldAccount, Account newAccount) throws AlreayExistingException {
		assert oldAccount != null && newAccount != null;
		
		Person oldOwner = this.getPersonByCnp(oldAccount.getOwner());
		Person newOwner = this.getPersonByCnp(newAccount.getOwner());
		
		ArrayList<Account> listOfAccountsOldOwner = bankHashMap.get(oldOwner);
		ArrayList<Account> listOfAccountsNewOwner = bankHashMap.get(newOwner);
		int listSizeBeforeRemovalOldOwner = listOfAccountsOldOwner.size();
		int listSizeBeforeRemovalNewOwner = listOfAccountsNewOwner.size();
		
		if(newOwner.getCnp()!=oldOwner.getCnp()) {
			oldAccount.unregister((Observer)oldOwner);
			this.removeAccount(oldAccount);
			this.addAccount(newOwner, newAccount);
			newAccount.register((Observer)newOwner);
		} else {
			oldAccount.unregister((Observer)oldOwner);
			int indexOfAccount = listOfAccountsNewOwner.indexOf(newAccount);
			bankHashMap.get(oldOwner).set(indexOfAccount, newAccount);
			newAccount.register((Observer)oldOwner);	
		}
		
		assert bankHashMap.get(oldOwner).size() == listSizeBeforeRemovalOldOwner || bankHashMap.get(newOwner).size() == listSizeBeforeRemovalNewOwner+1;
	}

	public void addObservers() {
		for(ArrayList<Account> listOfAccounts : bankHashMap.values())
			if(listOfAccounts != null)
				for(Account account : listOfAccounts) 
					account.register((Observer)(this.getPersonByCnp(account.getOwner())));
	}
	
	@SuppressWarnings("resource")
	public Bank readBankData() {
		Bank result = null;
		try {
			FileInputStream file = new FileInputStream("file.ser");
			ObjectInputStream in = new ObjectInputStream(file);
			result = (Bank)in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}		
		return result;
	}
	
	
	public void writeBankData() {
		try {
			FileOutputStream file = new FileOutputStream("file.ser");
	        ObjectOutputStream out = new ObjectOutputStream(file);     
	        out.writeObject(this);
	        out.close();
	        file.close();
	        System.out.println("Object has been serialized");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public Account getAccountById(int idAccount) {
		for (Entry<Person, ArrayList<Account>> entry : bankHashMap.entrySet()) {
			ArrayList<Account> listOfAccounts = entry.getValue();
			if(listOfAccounts != null) {
				for(Iterator<Account> i = listOfAccounts.iterator(); i.hasNext();) {
					Account accountToCheck = i.next();
					if(accountToCheck.getIdAccount()==idAccount)
						return accountToCheck;
				}
			}
		}
		return null;
	}
	
	
	public Person getPersonByCnp(int cnp) {
		for (Entry<Person, ArrayList<Account>> entry : bankHashMap.entrySet()) {
			if(entry.getKey().getCnp()==cnp)
				return entry.getKey();
		}
		return null;
	}

	
	public ArrayList<Person> getPersons(){
		ArrayList<Person> persons = new ArrayList<Person>();
		for (Entry<Person, ArrayList<Account>> entry : bankHashMap.entrySet()) {
		    Person key = entry.getKey();
		    persons.add(key);
		}
		return persons;
	}
	
	
	public ArrayList<Account> getAccounts(){
		ArrayList<Account> accounts = new ArrayList<Account>();
		for (Entry<Person, ArrayList<Account>> entry : bankHashMap.entrySet()) {
		    List<Account> value = entry.getValue();
		    if(value!=null)
		    	accounts.addAll(value);
		}
		return accounts;
	}
	

}
