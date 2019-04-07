package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	private JPanel backgroundPanel = new JPanel(new BorderLayout());
	
	private JPanel menuPanel = new JPanel();
	private JButton addPersonButton = new JButton("ADD PERSON");
	private JButton editPersonButton = new JButton("EDIT PERSON");
	private JButton removePersonButton = new JButton("REMOVE PERSON");
	private JButton viewPersonsButton = new JButton("VIEW PERSONS");
	private JButton addAccountButton = new JButton("ADD ACCOUNT");
	private JButton editAccountButton = new JButton("EDIT ACCOUNT");
	private JButton removeAccountButton = new JButton("REMOVE ACCOUNT");
	private JButton viewAccountsButton = new JButton("VIEW ACCOUNTS");
	
	private JPanel displayDataPanel = new JPanel(new CardLayout());
	
	private JPanel addPersonPanel = new JPanel();
	private JLabel idPersonLabel = new JLabel("CNP: ");
	private JLabel firstNameLabel = new JLabel("First Name: ");
	private JLabel secondNameLabel = new JLabel("Last Name: ");
	private JLabel emailLabel = new JLabel("Email: ");
	private JTextField idPersonTextField = new JTextField();
	private JTextField firstNameTextField = new JTextField();
	private JTextField secondNameTextField = new JTextField();
	private JTextField emailTextField = new JTextField();
	private JButton addPersonFromPanelButton = new JButton("ADD PERSON");
	
	private JPanel editPersonPanel = new JPanel();
	private JLabel idPersonToEditLabel = new JLabel("CNP: ");
	private JLabel firstNameToEditLabel = new JLabel("First Name: ");
	private JLabel secondNameToEditLabel = new JLabel("Last Name: ");
	private JLabel emailToEditLabel = new JLabel("Email: ");
	private JTextField idPersonToEditTextField = new JTextField();
	private JTextField firstNameToEditTextField = new JTextField();
	private JTextField secondNameToEditTextField = new JTextField();
	private JTextField emailToEditTextField = new JTextField();
	private JButton editPersonFromPanelButton = new JButton("EDIT PERSON");
	
	private JPanel addAccountPanel = new JPanel();
	private JLabel idAccountLabel = new JLabel("Id: ");
	private JLabel noOfMonthsLabel = new JLabel("Age: ");
	private JLabel typeLabel = new JLabel("Type: ");
	private JLabel interestLabel = new JLabel("Interest: ");
	private JTextField idAccountTextField = new JTextField();
	private JTextField noOfMonthsTextField = new JTextField();
	private JTextField typeTextField = new JTextField();
	private JTextField interestTextField = new JTextField();
	private JButton addAccountFromPanelButton = new JButton("ADD ACCOUNT");
	
	
	private JPanel editAccountPanel = new JPanel();
	private JLabel cnpOwnerToEditLabel = new JLabel("Owner: ");
	private JLabel balanceToEditLabel = new JLabel("Balance: ");
	private JLabel typeToEditLabel = new JLabel("Type: ");
	private JLabel interestToEditLabel = new JLabel("Interest: ");
	private JLabel noOfMonthsToEditLabel = new JLabel("Age: ");
	private JTextField cnpOwnerToEditTextField = new JTextField();
	private JTextField balanceToEditTextField = new JTextField();
	private JTextField typeToEditTextField = new JTextField();
	private JTextField interestToEditTextField = new JTextField();
	private JTextField noOfMonthsToEditTextField = new JTextField();
	private JButton editAccountFromPanelButton = new JButton("EDIT ACCOUNT");
	
	
	
	private JButton withdrawButton = new JButton("WITHDRAW");
	private JButton depositButton = new JButton("DEPOSIT");
	
	
	private JPanel withdrawPanel = new JPanel();
	private JLabel amountWithdrawLabel = new JLabel("Withdraw amount: ");
	private JTextField amountWithdrawTextField = new JTextField(); 
	private JButton withdrawFromPanelButton = new JButton("WITHDRAW");
	
	private JPanel depositPanel = new JPanel();
	private JLabel amountDepositLabel = new JLabel("Deposit amount: ");
	private JTextField amountDepositTextField = new JTextField(); 
	private JButton depositFromPanelButton = new JButton("DEPOSIT");
	
	JScrollPane scrollPanePersons;
	JScrollPane scrollPaneAccounts;
	private JPanel emptyBackgroundPanel = new JPanel(); 

	
	public View() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 500);
		this.setName("Login Form");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		menuPanel.setPreferredSize(new Dimension(200, 500));
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));
		
		addPersonButton.setMaximumSize(new Dimension(200, 40));
		editPersonButton.setMaximumSize(new Dimension(200, 40));
		removePersonButton.setMaximumSize(new Dimension(200, 40));
		viewPersonsButton.setMaximumSize(new Dimension(200, 40));
		addAccountButton.setMaximumSize(new Dimension(200, 40));
		editAccountButton.setMaximumSize(new Dimension(200, 40));
		removeAccountButton.setMaximumSize(new Dimension(200, 40));
		viewAccountsButton.setMaximumSize(new Dimension(200, 40));
		withdrawButton.setMaximumSize(new Dimension(200, 40));
		depositButton.setMaximumSize(new Dimension(200, 40));
		
		menuPanel.add(addPersonButton);
		menuPanel.add(removePersonButton);
		menuPanel.add(editPersonButton);
		menuPanel.add(viewPersonsButton);
		menuPanel.add(addAccountButton);
		menuPanel.add(editAccountButton);
		menuPanel.add(removeAccountButton);
		menuPanel.add(viewAccountsButton);
		menuPanel.add(withdrawButton);
		menuPanel.add(depositButton);
		
		
		// add Person
		JPanel idPersonPanel = new JPanel(new FlowLayout());
		JPanel firstNamePanel = new JPanel(new FlowLayout());
		JPanel secondNamePanel = new JPanel(new FlowLayout());
		JPanel emailPanel = new JPanel(new FlowLayout());
		JPanel addPersonButtonPanel = new JPanel();
		JPanel auxPanel1 = new JPanel();
		JPanel auxPanel2 = new JPanel();
		
		idPersonTextField.setPreferredSize(new Dimension(150, 30));
		firstNameTextField.setPreferredSize(new Dimension(150, 30));
		secondNameTextField.setPreferredSize(new Dimension(150, 30));
		emailTextField.setPreferredSize(new Dimension(150, 30));
		auxPanel1.setPreferredSize(new Dimension(700, 50));
		auxPanel2.setPreferredSize(new Dimension(700, 250));
		addPersonFromPanelButton.setPreferredSize(new Dimension(150, 40));
		
		JLabel emptyLabel1 = new JLabel();
		JLabel emptyLabel2 = new JLabel();
		emptyLabel1.setPreferredSize(new Dimension(40, 40));
		emptyLabel2.setPreferredSize(new Dimension(30, 40));
		
		Font font = new Font("Times New Roman", Font.LAYOUT_NO_LIMIT_CONTEXT, 18);
		
		idPersonLabel.setFont(font);
		firstNameLabel.setFont(font);
		secondNameLabel.setFont(font);
		emailLabel.setFont(font);
		addPersonFromPanelButton.setFont(font);
		
		idPersonPanel.add(emptyLabel1);
		idPersonPanel.add(idPersonLabel);
		idPersonPanel.add(idPersonTextField);
		firstNamePanel.add(firstNameLabel);
		firstNamePanel.add(firstNameTextField);
		secondNamePanel.add(secondNameLabel);
		secondNamePanel.add(secondNameTextField);
		emailPanel.add(emptyLabel2);
		emailPanel.add(emailLabel);
		emailPanel.add(emailTextField);
		addPersonButtonPanel.add(addPersonFromPanelButton);
		
		addPersonPanel.setLayout(new BoxLayout(addPersonPanel, BoxLayout.Y_AXIS));
		
		addPersonPanel.add(auxPanel1);
		addPersonPanel.add(idPersonPanel);
		addPersonPanel.add(firstNamePanel);
		addPersonPanel.add(secondNamePanel);
		addPersonPanel.add(emailPanel);
		addPersonPanel.add(addPersonButtonPanel);
		addPersonPanel.add(auxPanel2);
		
		
		
		// edit Person
		JPanel idPersonToEditPanel = new JPanel(new FlowLayout());
		JPanel firstNameToEditPanel = new JPanel(new FlowLayout());
		JPanel secondNameToEditPanel = new JPanel(new FlowLayout());
		JPanel emailToEditPanel = new JPanel(new FlowLayout());
		JPanel editPersonButtonPanel = new JPanel();
		JPanel auxPanelToEdit1 = new JPanel();
		JPanel auxPanelToEdit2 = new JPanel();
		
		idPersonToEditTextField.setPreferredSize(new Dimension(150, 30));
		firstNameToEditTextField.setPreferredSize(new Dimension(150, 30));
		secondNameToEditTextField.setPreferredSize(new Dimension(150, 30));
		emailToEditTextField.setPreferredSize(new Dimension(150, 30));
		auxPanelToEdit1.setPreferredSize(new Dimension(700, 50));
		auxPanelToEdit2.setPreferredSize(new Dimension(700, 250));
		editPersonFromPanelButton.setPreferredSize(new Dimension(150, 40));
		
		JLabel emptyLabelToEdit1 = new JLabel();
		JLabel emptyLabelToEdit2 = new JLabel();
		emptyLabelToEdit1.setPreferredSize(new Dimension(40, 40));
		emptyLabelToEdit2.setPreferredSize(new Dimension(30, 40));
		
		Font font1 = new Font("Times New Roman", Font.LAYOUT_NO_LIMIT_CONTEXT, 15);
		
		idPersonToEditLabel.setFont(font);
		firstNameToEditLabel.setFont(font);
		secondNameToEditLabel.setFont(font);
		emailToEditLabel.setFont(font);
		editPersonFromPanelButton.setFont(font1);
		
		idPersonToEditPanel.add(emptyLabelToEdit1);
		idPersonToEditPanel.add(idPersonToEditLabel);
		idPersonToEditPanel.add(idPersonToEditTextField);
		firstNameToEditPanel.add(firstNameToEditLabel);
		firstNameToEditPanel.add(firstNameToEditTextField);
		secondNameToEditPanel.add(secondNameToEditLabel);
		secondNameToEditPanel.add(secondNameToEditTextField);
		emailToEditPanel.add(emptyLabelToEdit2);
		emailToEditPanel.add(emailToEditLabel);
		emailToEditPanel.add(emailToEditTextField);
		editPersonButtonPanel.add(editPersonFromPanelButton);
		
		editPersonPanel.setLayout(new BoxLayout(editPersonPanel, BoxLayout.Y_AXIS));
		
		editPersonPanel.add(auxPanelToEdit1);
		editPersonPanel.add(idPersonToEditPanel);
		editPersonPanel.add(firstNameToEditPanel);
		editPersonPanel.add(secondNameToEditPanel);
		editPersonPanel.add(emailToEditPanel);
		editPersonPanel.add(editPersonButtonPanel);
		editPersonPanel.add(auxPanelToEdit2);
		
		
		
		//add account
		JPanel idAccountPanel = new JPanel(new FlowLayout());
		JPanel noOfMonthsPanel = new JPanel(new FlowLayout());
		JPanel typePanel = new JPanel(new FlowLayout());
		JPanel interestPanel = new JPanel(new FlowLayout());
		JPanel addAccountButtonPanel = new JPanel();
		JPanel auxPanel3 = new JPanel();
		JPanel auxPanel4 = new JPanel();
		
		idAccountTextField.setPreferredSize(new Dimension(150, 30));
		noOfMonthsTextField.setPreferredSize(new Dimension(150, 30));
		typeTextField.setPreferredSize(new Dimension(150, 30));
		interestTextField.setPreferredSize(new Dimension(150, 30));
		auxPanel3.setPreferredSize(new Dimension(700, 50));
		auxPanel4.setPreferredSize(new Dimension(700, 250));
		addAccountFromPanelButton.setPreferredSize(new Dimension(150, 40));
		
		JLabel emptyLabel3 = new JLabel();
		JLabel emptyLabel4 = new JLabel();
		JLabel emptyLabel5 = new JLabel();
		JLabel emptyLabel6 = new JLabel();
		emptyLabel3.setPreferredSize(new Dimension(37, 40));
		emptyLabel4.setPreferredSize(new Dimension(13, 40));
		emptyLabel5.setPreferredSize(new Dimension(23, 40));
		emptyLabel6.setPreferredSize(new Dimension(23, 40));
		
		idAccountLabel.setFont(font);
		noOfMonthsLabel.setFont(font);
		typeLabel.setFont(font);
		interestLabel.setFont(font);
		addAccountFromPanelButton.setFont(font1);
		
		idAccountPanel.add(emptyLabel3);
		idAccountPanel.add(idAccountLabel);
		idAccountPanel.add(idAccountTextField);
		noOfMonthsPanel.add(emptyLabel5);
		noOfMonthsPanel.add(noOfMonthsLabel);
		noOfMonthsPanel.add(noOfMonthsTextField);
		typePanel.add(emptyLabel4);
		typePanel.add(typeLabel);
		typePanel.add(typeTextField);
		interestPanel.add(interestLabel);
		interestPanel.add(interestTextField);
		addAccountButtonPanel.add(emptyLabel6);
		addAccountButtonPanel.add(addAccountFromPanelButton);
		
		addAccountPanel.setLayout(new BoxLayout(addAccountPanel, BoxLayout.Y_AXIS));
		
		addAccountPanel.add(auxPanel3);
		addAccountPanel.add(idAccountPanel);
		addAccountPanel.add(typePanel);
		addAccountPanel.add(noOfMonthsPanel);
		addAccountPanel.add(interestPanel);
		addAccountPanel.add(addAccountButtonPanel);
		addAccountPanel.add(auxPanel4);
		
		
		
		
		//edit account
		JPanel cnpOwnerToEditPanel = new JPanel(new FlowLayout());
		JPanel balanceToEditPanel = new JPanel(new FlowLayout());
		JPanel typeToEditPanel = new JPanel(new FlowLayout());
		JPanel interestToEditPanel = new JPanel(new FlowLayout());
		JPanel noOfMonthsToEditPanel = new JPanel(new FlowLayout());
		JPanel editAccountButtonPanel = new JPanel();
		JPanel auxPanelToEdit3 = new JPanel();
		JPanel auxPanelToEdit4 = new JPanel();
		
		cnpOwnerToEditTextField.setPreferredSize(new Dimension(150, 30));
		balanceToEditTextField.setPreferredSize(new Dimension(150, 30));
		typeToEditTextField.setPreferredSize(new Dimension(150, 30));
		interestToEditTextField.setPreferredSize(new Dimension(150, 30));
		noOfMonthsToEditTextField.setPreferredSize(new Dimension(150, 30));
		auxPanelToEdit3.setPreferredSize(new Dimension(700, 50));
		auxPanelToEdit4.setPreferredSize(new Dimension(700, 250));
		editAccountFromPanelButton.setPreferredSize(new Dimension(150, 40));
		
		JLabel emptyLabelToEdit3 = new JLabel();
		JLabel emptyLabelToEdit4 = new JLabel();
		JLabel emptyLabelToEdit5 = new JLabel();
		JLabel emptyLabelToEdit6 = new JLabel();
		emptyLabelToEdit3.setPreferredSize(new Dimension(5, 40));
		emptyLabelToEdit4.setPreferredSize(new Dimension(13, 40));
		emptyLabelToEdit5.setPreferredSize(new Dimension(22, 40));
		emptyLabelToEdit6.setPreferredSize(new Dimension(22, 40));
		
		cnpOwnerToEditLabel.setFont(font);
		noOfMonthsToEditLabel.setFont(font);
		balanceToEditLabel.setFont(font);
		typeToEditLabel.setFont(font);
		interestToEditLabel.setFont(font);
		editAccountFromPanelButton.setFont(font1);
		
		cnpOwnerToEditPanel.add(emptyLabelToEdit3);
		cnpOwnerToEditPanel.add(cnpOwnerToEditLabel);
		cnpOwnerToEditPanel.add(cnpOwnerToEditTextField);
		balanceToEditPanel.add(balanceToEditLabel);
		balanceToEditPanel.add(balanceToEditTextField);
		typeToEditPanel.add(emptyLabelToEdit4);
		typeToEditPanel.add(typeToEditLabel);
		typeToEditPanel.add(typeToEditTextField);
		interestToEditPanel.add(interestToEditLabel);
		interestToEditPanel.add(interestToEditTextField);
		noOfMonthsToEditPanel.add(emptyLabelToEdit5);
		noOfMonthsToEditPanel.add(noOfMonthsToEditLabel);
		noOfMonthsToEditPanel.add(noOfMonthsToEditTextField);
		editAccountButtonPanel.add(emptyLabelToEdit6);
		editAccountButtonPanel.add(editAccountFromPanelButton);
		
		editAccountPanel.setLayout(new BoxLayout(editAccountPanel, BoxLayout.Y_AXIS));
		
		editAccountPanel.add(auxPanelToEdit3);
		editAccountPanel.add(cnpOwnerToEditPanel);
		editAccountPanel.add(balanceToEditPanel);
		editAccountPanel.add(typeToEditPanel);
		editAccountPanel.add(interestToEditPanel);
		editAccountPanel.add(noOfMonthsToEditPanel);
		editAccountPanel.add(editAccountButtonPanel);
		editAccountPanel.add(auxPanelToEdit4);
		
		
		
		JPanel auxPanel14 = new JPanel();
		JPanel auxPanel = new JPanel();
		JPanel auxPanel12 = new JPanel();
		JPanel auxPanel13 = new JPanel();
		JPanel amountInputPanel = new JPanel(new FlowLayout());
		amountWithdrawTextField.setPreferredSize(new Dimension(150, 30));
		amountWithdrawLabel.setFont(font);
		amountInputPanel.add(amountWithdrawLabel);
		amountInputPanel.add(amountWithdrawTextField);
		JPanel amountButtonPanel = new JPanel(new FlowLayout());
		withdrawFromPanelButton.setPreferredSize(new Dimension(150, 40));
		amountButtonPanel.add(withdrawFromPanelButton);
		
		withdrawPanel.add(auxPanel14);
		withdrawPanel.setLayout(new BoxLayout(withdrawPanel, BoxLayout.Y_AXIS));
		withdrawPanel.add(auxPanel);
		withdrawPanel.add(amountInputPanel);
		withdrawPanel.add(amountButtonPanel);
		withdrawPanel.add(auxPanel12);
		withdrawPanel.add(auxPanel13);
		
		
		
		JPanel auxPanel21 = new JPanel();
		JPanel auxPanel22 = new JPanel();
		JPanel auxPanel23 = new JPanel();
		JPanel auxPanel24 = new JPanel();
		JPanel amountInputPanel1 = new JPanel(new FlowLayout());
		amountDepositTextField.setPreferredSize(new Dimension(150, 30));
		amountDepositLabel.setFont(font);
		amountInputPanel1.add(amountDepositLabel);
		amountInputPanel1.add(amountDepositTextField);
		JPanel amountButtonPanel1 = new JPanel(new FlowLayout());
		depositFromPanelButton.setPreferredSize(new Dimension(150, 40));
		amountButtonPanel1.add(depositFromPanelButton);
		
		depositPanel.add(auxPanel21);
		depositPanel.setLayout(new BoxLayout(depositPanel, BoxLayout.Y_AXIS));
		depositPanel.add(auxPanel22);
		depositPanel.add(amountInputPanel1);
		depositPanel.add(amountButtonPanel1);
		depositPanel.add(auxPanel23);
		depositPanel.add(auxPanel24);
		
		
		displayDataPanel.add(emptyBackgroundPanel, "Card0");
		displayDataPanel.add(addPersonPanel, "Card1");
		displayDataPanel.add(editPersonPanel, "Card2");
		displayDataPanel.add(addAccountPanel, "Card3");
		displayDataPanel.add(editAccountPanel, "Card4");
		displayDataPanel.add(withdrawPanel, "Card5");
		displayDataPanel.add(depositPanel, "Card6");
		
		backgroundPanel.add(menuPanel, BorderLayout.LINE_START);
		backgroundPanel.add(displayDataPanel, BorderLayout.CENTER);
		this.add(backgroundPanel);
		
		this.setVisible(true);
	}
	
	public void viewAddPersonPanel(ActionListener listenForViewOrdersTable) {
		addPersonButton.addActionListener(listenForViewOrdersTable);
	}
	
	public void viewEditPersonPanel(ActionListener listenForViewOrdersTable) {
		editPersonButton.addActionListener(listenForViewOrdersTable);
	}
	
	public void viewPersonsTablePanel(ActionListener listenForViewOrdersTable) {
		viewPersonsButton.addActionListener(listenForViewOrdersTable);
	}
	
	public void viewAddAccountPanel(ActionListener listenForViewOrdersTable) {
		addAccountButton.addActionListener(listenForViewOrdersTable);
	}
	
	public void viewEditAccountPanel(ActionListener listenForViewOrdersTable) {
		editAccountButton.addActionListener(listenForViewOrdersTable);
	}
	
	public void viewWithdrawPanel(ActionListener listener) {
		withdrawButton.addActionListener(listener);
	}
	
	public void viewDepositPanel(ActionListener listener) {
		depositButton.addActionListener(listener);
	}
	
	public void viewAccountsTablePanel(ActionListener listenForViewOrdersTable) {
		viewAccountsButton.addActionListener(listenForViewOrdersTable);
	}
	
	public void addPersonFromPanel(ActionListener listenForAddButtonTable) {
		addPersonFromPanelButton.addActionListener(listenForAddButtonTable);
	}
	
	public void addAccountFromPanel(ActionListener listenForAddButtonTable) {
		addAccountFromPanelButton.addActionListener(listenForAddButtonTable);
	}
	
	public void editPersonFromPanel(ActionListener listener) {
		editPersonFromPanelButton.addActionListener(listener);
	}
	
	public void editAccountFromPanel(ActionListener listener) {
		editAccountFromPanelButton.addActionListener(listener);
	}
	
	public void removePersonButtonActionListener(ActionListener actionListener) {
		removePersonButton.addActionListener(actionListener);
	}
	
	public void removeAccountButtonActionListener(ActionListener actionListener) {
		removeAccountButton.addActionListener(actionListener);
	}
	
	public void withdrawFromPanelActionListener(ActionListener listener) {
		withdrawFromPanelButton.addActionListener(listener);
	}
	
	public void depositFromPanelActionListener(ActionListener listener) {
		depositFromPanelButton.addActionListener(listener);
	}
	
	public void saveDataWhenClosingActionListener(WindowAdapter windowListener) {
		this.addWindowListener(windowListener);
	}
	
	public void loadDataWhenClosingActionListener(WindowAdapter windowListener) {
		this.addWindowListener(windowListener);
	}
	
	public void setEmptyBackgroundPanelVisible() {
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Card0");
	}
	
	public void setAddPersonPanelVisible() {
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Card1");
	}
	
	public void setEditPersonPanelVisible() {
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Card2");
	}
	
	public void setAddAccountPanelVisible() {
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Card3");
	}
	
	public void setEditAccountPanelVisible() {
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Card4");
	}
	
	public void setViewPersonsPanelVisible() {
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Table1");
	}
	
	public void setViewAccountsPanelVisible() {
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Table2");
	}
	
	public void setWithdrawPanelVisible() {
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Card5");
	}
	
	public void setDepositPanelVisible() {
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Card6");
	}
	
	
	public String getCNP() {
		return idPersonTextField.getText();
	}
	
	public String getFirstName() {
		return firstNameTextField.getText();
	}
	
	public String getLastName() {
		return secondNameTextField.getText();
	}
	
	public String getEmail() {
		return emailTextField.getText();
	}
	
	public String getIdPersonToEdit() {
	   	return idPersonToEditTextField.getText();
	}
	    
	public String getFirstNamePersonOfToEdit() {
		return firstNameToEditTextField.getText();
	}
	    
	public String getLastNameOfPersonToEdit() {
	  	return secondNameToEditTextField.getText();
	}
	    
	public String getEmailOfPersonToEdit() {
	 	return emailToEditTextField.getText();
	}
	    
	public JTable getPersonTable() {
		JViewport viewport = scrollPanePersons.getViewport();
		return (JTable)viewport.getView();
	}

	public void setAddPersonTextFieldsEmpty() {
		idPersonTextField.setText("");
		firstNameTextField.setText("");
		secondNameTextField.setText("");
		emailTextField.setText("");
	}
	
	public void setEditPersonTextFieldsEmpty() {
		idPersonToEditTextField.setText("");
		firstNameToEditTextField.setText("");
		secondNameToEditTextField.setText("");
		emailToEditTextField.setText("");
	}
	
	
    public String getIdAccount() {
		return idAccountTextField.getText();
	}
	
	public String getTypeOfAccount() {
		return typeTextField.getText();
	}
	
	public String getInterest() {
		return interestTextField.getText();
	}
	
	public String getNoOfMonths() {
		return noOfMonthsTextField.getText();
	}
    
	public String getIdOwnerToEdit() {
		return cnpOwnerToEditTextField.getText();
	}
	
	public String getBalanceToEdit() {
		return balanceToEditTextField.getText();
	}
	
	public String getTypeOfAccountToEdit() {
		return typeToEditTextField.getText();
	}
	
	public String getInterestToEdit() {
		return interestToEditTextField.getText();
	}
	
	public String getNoOfMonthsToEdit() {
		return noOfMonthsToEditTextField.getText();
	}
    
	public JTable getAccountsTable() {
		JViewport viewport = scrollPaneAccounts.getViewport();
		return (JTable)viewport.getView();
	}
	
	public void setAddAccountTextFieldsEmpty() {
		idAccountTextField.setText("");
		typeTextField.setText("");
		interestTextField.setText("");
		noOfMonthsTextField.setText("");
	}
	
	public void setEditAccountTextFieldsEmpty() {
		cnpOwnerToEditTextField.setText("");
		balanceToEditTextField.setText("");
		typeToEditTextField.setText("");
		interestToEditTextField.setText("");
		noOfMonthsToEditTextField.setText("");
	}
	
	public String getWithdrawAmount() {
		return amountWithdrawTextField.getText();
	}
	
	public String getDepositAmount() {
		return amountDepositTextField.getText();
	}
	
	public void setWithdrawTextFieldEmpty() {
		amountWithdrawTextField.setText("");
	}
	
	public void setDepositTextFieldEmpty() {
		amountDepositTextField.setText("");
	}
	
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void setPersonsTable(JTable table) {
		scrollPanePersons = new JScrollPane(table);
		JPanel aux = new JPanel();
		aux.add(scrollPanePersons, BorderLayout.CENTER);
		displayDataPanel.add(aux, "Table1");
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Table1");
	}
	
	public void setAccountsTable(JTable table) {
		scrollPaneAccounts = new JScrollPane(table);
		JPanel aux = new JPanel();
		aux.add(scrollPaneAccounts, BorderLayout.CENTER);
		displayDataPanel.add(aux, "Table2");
		CardLayout cardLayout = (CardLayout) displayDataPanel.getLayout();
		cardLayout.show(displayDataPanel, "Table2");
	}

}
