package Model;

import Exceptions.AlreayExistingException;

public interface BankProc {
	
	/**
	 *  @throws AlreayExistingException 
	 * @pre person != null
	 *  @post bankHashMap.get(person) == null
	 */
	public void addPerson(Person person) throws AlreayExistingException;
	
	/**
	 * @pre person != null
	 * @post bankHashMap.get(person) == null 
	 */
	public void removePerson(Person person);
	
	/**
	 * @throws AlreayExistingException 
	 * @pre person != null && accountToAdd != null
	 * @post bankHashMap.get(person) != null
	 */
	public void addAccount(Person person, Account accountToAdd) throws AlreayExistingException;
	
	/**
	 * @pre accountToDelete != null
	 * @post bankHashMap.get(person).size() == (sizeBeforeRemoval-1)
	 */
	public void removeAccount(Account account);
	
	/**
	 * @throws AlreayExistingException 
	 * @pre oldPerson != null && newPerson != null
	 * @post bankHashMap.size() == sizeBeforeRemoval
	 */
	public void editPerson(Person oldPerson, Person newPerson) throws AlreayExistingException;
	
	/**
	 * @throws AlreayExistingException 
	 * @pre oldAccount != null && newAccount != null
	 * @post bankHashMap.get(oldOwner).size() == listSizeBeforeRemovalOldOwner || bankHashMap.get(newOwner).size() == listSizeBeforeRemovalNewOwner+1
	 */
	public void editAccount(Account oldAccount, Account newAccount) throws AlreayExistingException;
	
	public Bank readBankData();
	
	public void writeBankData();
	
}
