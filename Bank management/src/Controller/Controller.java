package Controller;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Exceptions.EmptyFieldException;
import Exceptions.InvalidDepositException;
import Exceptions.InvalidWithdrawalException;
import Exceptions.NoElementSelectedInTableException;
import Exceptions.AlreayExistingException;
import Model.Account;
import Model.Bank;
import Model.Person;
import Model.SavingAccount;
import Model.SpendingAccount;
import View.View;

public class Controller {
	private View theView;
	private Bank theBank;
	
	public Controller(View theView, Bank theBank) {
		this.theView = theView;
		this.theBank = theBank;
		
		this.theView.viewAddPersonPanel(new openAddPersonPanelListener());
		this.theView.viewEditPersonPanel(new openEditPersonPanelListener());
		this.theView.viewPersonsTablePanel(new openViewPersonsPanelListener());
		this.theView.viewAddAccountPanel(new openAddAccountPanelListener());
		this.theView.viewEditAccountPanel(new openEditAccountPanelListener());
		this.theView.viewAccountsTablePanel(new openViewAccountsPanelListener());
		this.theView.viewWithdrawPanel(new openWithdrawPanelListener());
		this.theView.viewDepositPanel(new openDepositPanelListener());
		this.theView.saveDataWhenClosingActionListener(new saveDataWhenClosingListener());
		this.theView.loadDataWhenClosingActionListener(new loadDataWhenClosingListener());
		
		this.theView.addPersonFromPanel(new addPersonListener());
		this.theView.addAccountFromPanel(new addAccountListener());
		this.theView.removePersonButtonActionListener(new removePersonListener());
		this.theView.removeAccountButtonActionListener(new removeAccountListener());
		this.theView.editPersonFromPanel(new editPersonListener());
		this.theView.editAccountFromPanel(new editAccountListener());
		this.theView.depositFromPanelActionListener(new depositListener());
		this.theView.withdrawFromPanelActionListener(new withdrawListener());
		
		//cand intram in aplicatie si voiam sa editez o persoana sau cont fara sa ma uit prima data in tabela
		//nu imi arunca exceptia de selectare a unei persoane/cont
		this.theView.setPersonsTable(Controller.createPersonsTable(theBank.getPersons()));
		this.theView.setAccountsTable(Controller.createAccountsTable(theBank.getAccounts()));
		this.theView.setEmptyBackgroundPanelVisible();
	}
	
	public static JTable createAccountsTable(ArrayList<Account> accounts) {
		String col[] = {"Id", "Owner", "Balance", "Type", "Interest", "Age"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);       
		for(Account account : accounts) {
			int id = account.getIdAccount();
			int owner = account.getOwner();
			double balance = account.getBalance();
			String type = account.getType();
			String interest = String.valueOf(account.getInterest());
			if(account instanceof SpendingAccount) 
				interest = "-";
			String age = String.valueOf(account.getNoOfMonths());
			if(account instanceof SpendingAccount) 
				age = "-";
			Object[] data = {id, owner, balance, type, interest, age}; 
				tableModel.addRow(data);
		}
		return new JTable(tableModel);
	}
	
	public static JTable createPersonsTable(ArrayList<Person> persons) {
		String col[] = {"CNP","First Name","Second Name", "Emial"};
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);       
		for(Person person : persons) {
			int cnp = person.getCnp();
			String firstName = person.getFirstName();
			String lastName = person.getLastName();
			String email = person.getEmail();
			Object[] data = {cnp, firstName, lastName, email}; 
			tableModel.addRow(data);
		}
		return new JTable(tableModel);
	}
	
	
	public class openAddPersonPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theView.setAddPersonPanelVisible();
		}
	}
	
	public class openEditPersonPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theView.setEditPersonPanelVisible();
		}
	}
	
	public class openWithdrawPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theView.setWithdrawPanelVisible();
		}
	}
	
	public class openDepositPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theView.setDepositPanelVisible();
		}
	}
	
	public class openViewPersonsPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theView.setPersonsTable(Controller.createPersonsTable(theBank.getPersons()));
			theView.setViewPersonsPanelVisible();
		}
	}
	
	public class openAddAccountPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theView.setAddAccountPanelVisible();
		}
	}
	
	public class openEditAccountPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theView.setEditAccountPanelVisible();
		}
	}
	
	public class openViewAccountsPanelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			theView.setAccountsTable(Controller.createAccountsTable(theBank.getAccounts()));
			theView.setViewAccountsPanelVisible();
		}
	}
	
	public class removePersonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JTable table = theView.getPersonTable();
			int[] selectedRows = table.getSelectedRows();
			try {
				if(selectedRows.length==0) 
					theBank.removePerson(null);
				else {
					for(int i:selectedRows) 
						theBank.removePerson(new Person((int) table.getValueAt(i, 0)));
					theView.setPersonsTable(Controller.createPersonsTable(theBank.getPersons()));
					theView.setViewPersonsPanelVisible();
				}
			} catch(AssertionError e) {
				theView.displayErrorMessage("No person selected from <Persons> table for removal!");
				e.printStackTrace();
			}
		}
	}
	
	public class removeAccountListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JTable table = theView.getAccountsTable();
			int[] selectedRows = table.getSelectedRows();
			try {
				if(selectedRows.length==0)
					theBank.removeAccount(null);
				else {
					for(int i : selectedRows) 
						if(table.getValueAt(i, 3).toString().equals("Spending")) 
							theBank.removeAccount(theBank.getAccountById((int) table.getValueAt(i, 0)));
						else 
							theBank.removeAccount(theBank.getAccountById((int) table.getValueAt(i, 0)));
					theView.setAccountsTable(Controller.createAccountsTable(theBank.getAccounts()));
					theView.setViewAccountsPanelVisible();
				}
			} catch(AssertionError e) {
					theView.displayErrorMessage("No account selected from <Accounts> table for removal!");
					e.getStackTrace();
			} 
		}
	}
	
	public class addPersonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Person p;
			try {
				if(theView.getCNP().trim().isEmpty()==true)
					p = null;
				else
					p = new Person(Integer.parseInt(theView.getCNP()), theView.getFirstName(), theView.getLastName(), theView.getEmail());
				theBank.addPerson(p);
				theView.setPersonsTable(Controller.createPersonsTable(theBank.getPersons()));
				theView.setViewPersonsPanelVisible();
				theView.setAddPersonTextFieldsEmpty();
			} catch(AssertionError | AlreayExistingException e) {
				theView.displayErrorMessage("The Add Person operation is invalid!");
				e.printStackTrace();
			}
		}
	}
	
	public class addAccountListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JTable table = theView.getPersonTable();
			int selectedRow = table.getSelectedRow();
			Person person;
			try {
				if(selectedRow == -1)
					person = null;
				else 
					person = new Person((int) table.getValueAt(selectedRow, 0));
				if(theView.getIdAccount().trim().isEmpty()==true || theView.getTypeOfAccount().trim().isEmpty()==true)
					theBank.addAccount(person, null);
				else
					if(theView.getTypeOfAccount().equals("Saving")) {
						if(theView.getInterest().trim().isEmpty()==true || theView.getNoOfMonths().trim().isEmpty()==true)
							throw new EmptyFieldException("The Add Acount operation is invalid!");
						SavingAccount account = new SavingAccount(Integer.parseInt(theView.getIdAccount()), Double.parseDouble(theView.getInterest()), Integer.parseInt(theView.getNoOfMonths()));
						theBank.addAccount(person , account);
					} else if(theView.getTypeOfAccount().equals("Spending")) {
						SpendingAccount account = new SpendingAccount(Integer.parseInt(theView.getIdAccount()));
						theBank.addAccount(person, account);	
					}
				theView.setAccountsTable(Controller.createAccountsTable(theBank.getAccounts()));
				theView.setViewAccountsPanelVisible();
				theView.setAddAccountTextFieldsEmpty();
			} catch(AssertionError | AlreayExistingException | EmptyFieldException e) {
				theView.displayErrorMessage("The Add Account operation is invalid!");
				e.printStackTrace();
			}
		}
	}
	
	public class editPersonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JTable table = theView.getPersonTable();
			int selectedRow = table.getSelectedRow();
			Person oldPerson;
			try {
				if(selectedRow == -1)
					theBank.editPerson(null, null);
				else {
					oldPerson = new Person((int)table.getValueAt(selectedRow, 0), (String)table.getValueAt(selectedRow, 1), (String)table.getValueAt(selectedRow, 1), (String)table.getValueAt(selectedRow, 3));
					int newId = (int)table.getValueAt(selectedRow, 0);
					String newFirstName = (String)table.getValueAt(selectedRow, 1), newLastName = (String)table.getValueAt(selectedRow, 2), newEmail = (String)table.getValueAt(selectedRow, 3);
					if(theView.getIdPersonToEdit().trim().isEmpty()==false)
						if(Integer.parseInt(theView.getIdPersonToEdit())!=newId)
							newId = Integer.parseInt(theView.getIdPersonToEdit());
					if(theView.getFirstNamePersonOfToEdit().trim().isEmpty()==false)
						if(!theView.getFirstNamePersonOfToEdit().equals(newFirstName))
							newFirstName = theView.getFirstNamePersonOfToEdit();
					if(theView.getLastNameOfPersonToEdit().trim().isEmpty()==false)
						if(!theView.getLastNameOfPersonToEdit().equals(newLastName))
							newLastName = theView.getLastNameOfPersonToEdit();
					if(theView.getEmailOfPersonToEdit().trim().isEmpty()==false)
						if(!theView.getEmailOfPersonToEdit().equals(newEmail))
							newEmail = theView.getEmailOfPersonToEdit();
					Person newPerson = new Person(newId, newFirstName, newLastName, newEmail);
					theBank.editPerson(oldPerson, newPerson);
					theView.setPersonsTable(Controller.createPersonsTable(theBank.getPersons()));
					theView.setViewPersonsPanelVisible();
					theView.setEditPersonTextFieldsEmpty();
				}
			}catch(AssertionError | AlreayExistingException e) {
				theView.displayErrorMessage("The Edit Person operation is not valid!");
				e.printStackTrace();
			}
		}
	}
	
	public class editAccountListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JTable table = theView.getAccountsTable();
			Account account;
			try {
				if(table.getSelectedRow() == -1)
					theBank.editAccount(null, null);
				else {
					account = theBank.getAccountById((int)table.getValueAt(table.getSelectedRow(), 0));
					int newOwner = account.getOwner(), newNoOfActions = account.getNoOfActions(), newNoOfMonths = account.getNoOfMonths();
					String newType = account.getType();
					double newBalance = account.getBalance(), newInterest = account.getInterest();
					if(theView.getIdOwnerToEdit().trim().isEmpty()==false)
						if(Integer.parseInt(theView.getIdOwnerToEdit())!=account.getOwner()) 
							newOwner = Integer.parseInt(theView.getIdOwnerToEdit());
					if(theView.getBalanceToEdit().trim().isEmpty()==false)
						if(Double.parseDouble(theView.getBalanceToEdit())!=account.getBalance())
							newBalance = Double.parseDouble(theView.getBalanceToEdit());
					if(!theView.getTypeOfAccountToEdit().equals(account.getType()) && theView.getTypeOfAccountToEdit().trim().isEmpty()==false) {
						newType = theView.getTypeOfAccountToEdit();
						newNoOfActions = 0;
						if(account.getType().equals("Spending") && theView.getTypeOfAccountToEdit().equals("Saving"))
							newBalance = 0;
					}
					if(theView.getInterestToEdit().trim().isEmpty()==false)
						if(Double.parseDouble(theView.getInterestToEdit())!=account.getInterest())
							newInterest = Double.parseDouble(theView.getInterestToEdit());
					if(theView.getNoOfMonthsToEdit().trim().isEmpty()==false)
						if(Integer.parseInt(theView.getNoOfMonthsToEdit())!=account.getNoOfMonths())
							newNoOfMonths = Integer.parseInt(theView.getNoOfMonthsToEdit());
					if(newType.equals("Saving")) {
						try {
							if(theView.getInterestToEdit().trim().isEmpty()==true || theView.getNoOfMonthsToEdit().trim().isEmpty()==true || Integer.parseInt(theView.getNoOfMonthsToEdit())==0 || Integer.parseInt(theView.getInterestToEdit())==0)
								throw new EmptyFieldException("Interest or Age field are empty!");
							else {
								SavingAccount savingAccount =  new SavingAccount(account.getIdAccount(), newOwner, newBalance, newType, newInterest, newNoOfMonths, newNoOfActions);;
								theBank.editAccount(account, savingAccount);
							}
						} catch(EmptyFieldException e) {
							theView.displayErrorMessage(e.getMessage());
							e.printStackTrace();
						}
					} else if(newType.equals("Spending")) {
						SpendingAccount spendingAccount = new SpendingAccount(account.getIdAccount(), newOwner, newBalance, newType, newInterest, newNoOfMonths, newNoOfActions);
						theBank.editAccount(account, spendingAccount);
					}
					theView.setAccountsTable(Controller.createAccountsTable(theBank.getAccounts()));
					theView.setViewAccountsPanelVisible();	
					theView.setEditAccountTextFieldsEmpty();
				}
			}catch(AssertionError | AlreayExistingException e) {
				theView.displayErrorMessage("The edit Account operation is invalid!");
				e.printStackTrace();
			}
		}
	}
	
	public class withdrawListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JTable table = theView.getAccountsTable();
			try {
				if(table.getSelectedRow() == -1)
					throw new NoElementSelectedInTableException("No account selected from <Accounts> table for the withdraw operation!");
				else {
					if(((String)table.getValueAt(table.getSelectedRow(), 3)).equals("Spending")) {
						SpendingAccount account = (SpendingAccount) theBank.getAccountById((int)table.getValueAt(table.getSelectedRow(), 0));
						if(theView.getWithdrawAmount().trim().isEmpty()==false)
							try {
								try {
									account.withdraw(Integer.parseInt(theView.getWithdrawAmount()));
								} catch (NumberFormatException e) {
									theView.displayErrorMessage(e.getMessage());
									e.printStackTrace();
								}
							} catch (InvalidWithdrawalException e) {
								theView.displayErrorMessage(e.getMessage());
								e.printStackTrace();
							}
						theBank.editAccount(theBank.getAccountById((int)table.getValueAt(table.getSelectedRow(), 0)), account);
					} else if(((String)table.getValueAt(table.getSelectedRow(), 3)).equals("Saving")) {
						SavingAccount account = (SavingAccount) theBank.getAccountById((int)table.getValueAt(table.getSelectedRow(), 0));
						if(theView.getWithdrawAmount().trim().isEmpty()==false)
							try {
								account.withdraw(Integer.parseInt(theView.getWithdrawAmount()));
							} catch (InvalidWithdrawalException e) {
								theView.displayErrorMessage(e.getMessage());
								e.printStackTrace();
							}
						theBank.editAccount(theBank.getAccountById((int)table.getValueAt(table.getSelectedRow(), 0)), account);
					}
					theView.setAccountsTable(Controller.createAccountsTable(theBank.getAccounts()));
					theView.setViewAccountsPanelVisible();
					theView.setWithdrawTextFieldEmpty();
				}
			} catch(NoElementSelectedInTableException | AlreayExistingException e) {
				theView.displayErrorMessage(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public class depositListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JTable table = theView.getAccountsTable();
			try {
				if(table.getSelectedRow() == -1)
					throw new NoElementSelectedInTableException("No account selected from <Accounts> table for the deposit operation!");
				else {
					if(((String)table.getValueAt(table.getSelectedRow(), 3)).equals("Spending")) {
						SpendingAccount account = (SpendingAccount) theBank.getAccountById((int)table.getValueAt(table.getSelectedRow(), 0));
						if(theView.getDepositAmount().trim().isEmpty()==false)
							try {
								account.deposit(Integer.parseInt(theView.getDepositAmount()));
							} catch (NumberFormatException e) {
								theView.displayErrorMessage(e.getMessage());
								e.printStackTrace();
							}
						theBank.editAccount(theBank.getAccountById((int)table.getValueAt(table.getSelectedRow(), 0)), account);
					} else if(((String)table.getValueAt(table.getSelectedRow(), 3)).equals("Saving")) {
						SavingAccount account = (SavingAccount) theBank.getAccountById((int)table.getValueAt(table.getSelectedRow(), 0));
						if(theView.getDepositAmount().trim().isEmpty()==false)
							try {
								account.deposit(Integer.parseInt(theView.getDepositAmount()));
							} catch (InvalidDepositException e) {
								theView.displayErrorMessage(e.getMessage());
								e.printStackTrace();
							}
						theBank.editAccount(theBank.getAccountById((int)table.getValueAt(table.getSelectedRow(), 0)), account);
					}
					theView.setAccountsTable(Controller.createAccountsTable(theBank.getAccounts()));
					theView.setViewAccountsPanelVisible();
					theView.setDepositTextFieldEmpty();
				}
			} catch(NoElementSelectedInTableException | AlreayExistingException e) {
				theView.displayErrorMessage(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public class saveDataWhenClosingListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			theBank.writeBankData();
		}
	}
	
	public class loadDataWhenClosingListener extends WindowAdapter {
		public void windowOpened(WindowEvent e) {
			theBank = theBank.readBankData();
			theBank.addObservers();
		}
	}
		
}
