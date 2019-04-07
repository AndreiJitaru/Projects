package Presentation;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainView extends JFrame{
	
	private JButton customersButton = new JButton("CUSTOMERS");
	private JButton productsButton = new JButton("PRODUCTS");
	private JButton ordersButton = new JButton("CREATE ORDER");
	private JButton viewOrders = new JButton("VIEW ORDERS");
	
	private JButton addCustomerButton = new JButton("ADD Customer");
	private JButton updateCustomerButton = new JButton("UPDATE CUSTOMER");
	private JButton findCustomerButton = new JButton("FIND CUSTOMER");
	private JButton deleteCustomerButton = new JButton("DELETE CUSTOMER");
	private JButton viewCustomersButton = new JButton("VIEW CUSTOMERS");
	private JButton backFromCustomersButton = new JButton("BACK");
	
	private JButton addProductButton = new JButton("ADD PRODUCT");
	private JButton updateProductButton = new JButton("UPDATE PRODUCT");
	private JButton findProductButton = new JButton("FIND PRODUCT");
	private JButton deleteProductButton = new JButton("DELETE PRODUCT");
	private JButton viewProductsButton = new JButton("VIEW PRODUCTS");
	private JButton backFromProductsButton = new JButton("BACK");
	
	private JPanel backgroundPanel = new JPanel();
	private JPanel menuPanel = new JPanel(new CardLayout());
	private JPanel customersMenuPanel = new JPanel();
	private JPanel productsMenuPanel = new JPanel();
	private JPanel generalAccesMenuPanel = new JPanel();
	private JPanel displayTablePanel = new JPanel(new CardLayout());
	
	private JPanel defaultBackgroundPanel = new JPanel();
	
	public MainView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 600);
		this.setName("Login Form");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		//un panou mare care il vom aseza pe frame
		backgroundPanel.setLayout(new BorderLayout());
		
		//panou cu cardLayout ca sa putem realiza interschimbarea dintre meniuri
		menuPanel.setPreferredSize(new Dimension(200, 600));
		menuPanel.setBackground(new Color(45, 32, 100));
		
		
		//panoul de acces general
		generalAccesMenuPanel.setPreferredSize(new Dimension(200, 600));
		generalAccesMenuPanel.setLayout(new BoxLayout(generalAccesMenuPanel, BoxLayout.PAGE_AXIS));
		generalAccesMenuPanel.setBackground(new Color(45, 32, 100));
		
		customersButton.setMaximumSize(new Dimension(200, 40));
		productsButton.setMaximumSize(new Dimension(200, 40));
		ordersButton.setMaximumSize(new Dimension(200, 40));
		viewOrders.setMaximumSize(new Dimension(200, 40));
		
		generalAccesMenuPanel.add(customersButton);
		generalAccesMenuPanel.add(productsButton);
		generalAccesMenuPanel.add(ordersButton);
		generalAccesMenuPanel.add(viewOrders);
		
		//panou pentru lucrul cu customerii
		customersMenuPanel.setPreferredSize(new Dimension(200, 600));
		customersMenuPanel.setLayout(new BoxLayout(customersMenuPanel, BoxLayout.PAGE_AXIS));
		customersMenuPanel.setBackground(new Color(45, 32, 100));
		
		addCustomerButton.setMaximumSize(new Dimension(200, 40));
		updateCustomerButton.setMaximumSize(new Dimension(200, 40));
		findCustomerButton.setMaximumSize(new Dimension(200, 40));
		deleteCustomerButton.setMaximumSize(new Dimension(200, 40));
		viewCustomersButton.setMaximumSize(new Dimension(200, 40));
		backFromCustomersButton.setMaximumSize(new Dimension(200, 40));
		
		customersMenuPanel.add(addCustomerButton);
		customersMenuPanel.add(updateCustomerButton);
		customersMenuPanel.add(findCustomerButton);
		customersMenuPanel.add(deleteCustomerButton);
		customersMenuPanel.add(viewCustomersButton);
		customersMenuPanel.add(backFromCustomersButton);
		
		productsMenuPanel.setPreferredSize(new Dimension(200, 600));
		productsMenuPanel.setLayout(new BoxLayout(productsMenuPanel, BoxLayout.PAGE_AXIS));
		productsMenuPanel.setBackground(new Color(45, 32, 100));
		
		addProductButton.setMaximumSize(new Dimension(200, 40));
		updateProductButton.setMaximumSize(new Dimension(200, 40));
		findProductButton.setMaximumSize(new Dimension(200, 40));
		deleteProductButton.setMaximumSize(new Dimension(200, 40));
		viewProductsButton.setMaximumSize(new Dimension(200, 40));
		backFromProductsButton.setMaximumSize(new Dimension(200, 40));
		
		productsMenuPanel.add(addProductButton);
		productsMenuPanel.add(updateProductButton);
		productsMenuPanel.add(findProductButton);
		productsMenuPanel.add(deleteProductButton);
		productsMenuPanel.add(viewProductsButton);
		productsMenuPanel.add(backFromProductsButton);
		
		//punem meniurile in panoul cu cardLayout
		menuPanel.add(generalAccesMenuPanel, "Card 1");
		menuPanel.add(customersMenuPanel, "Card 2");
		menuPanel.add(productsMenuPanel, "Card 3");
		
		//panoul unde vom afisa tabelele
		defaultBackgroundPanel.setBackground(new Color(107, 3, 135));
		displayTablePanel.add(defaultBackgroundPanel, "Table 1");
		
		//punem panel-uri pe panoul principal
		backgroundPanel.add(displayTablePanel, BorderLayout.CENTER);
		backgroundPanel.add(menuPanel, BorderLayout.LINE_START);
		
		this.add(backgroundPanel);
		
	}
	
	public void showCustomersMenu(ActionListener listenForCustomersButton) {
		customersButton.addActionListener(listenForCustomersButton);
	}

	public void showProductsMenu(ActionListener listenForProductsButton) {
		productsButton.addActionListener(listenForProductsButton);
	}

	
	public void viewCustomerTable(ActionListener listenForViewCustomerButton) {
		viewCustomersButton.addActionListener(listenForViewCustomerButton);
	}
	
	public void openAddCustomerWindow(ActionListener listenForAddCustomerButton) {
		addCustomerButton.addActionListener(listenForAddCustomerButton);
	}
	
	public void openDeleteCustomerWindow(ActionListener listenForDeleteCustomer) {
		deleteCustomerButton.addActionListener(listenForDeleteCustomer);
	}
	
	public void openUpdateCustomerWindow(ActionListener listenForUpdateCustomerButton) {
		updateCustomerButton.addActionListener(listenForUpdateCustomerButton);
	}
	
	public void openFindCustomerWindow(ActionListener listenForFindCustomer) {
		findCustomerButton.addActionListener(listenForFindCustomer);
	}
	
	public void backToMainMenuFromCustomers(ActionListener listenForBackButton) {
		backFromCustomersButton.addActionListener(listenForBackButton);
	}

	
	public void openCreateOrderWindow(ActionListener listenForCreateOrderButton) {
		ordersButton.addActionListener(listenForCreateOrderButton);
	}
	
	public void viewOrdersTable(ActionListener listenForViewOrdersTable) {
		viewOrders.addActionListener(listenForViewOrdersTable);
	}
	
	
	public void viewProductsTable(ActionListener listenForViewProductsButton) {
		viewProductsButton.addActionListener(listenForViewProductsButton);
	}
	
	public void openAddProductWindow(ActionListener listenForAddProductButton) {
		addProductButton.addActionListener(listenForAddProductButton);
	}
	
	public void openDeleteProductWindow(ActionListener listenForDeleteProductButton) {
		deleteProductButton.addActionListener(listenForDeleteProductButton);
	}
	
	public void openUpdateProductWindow(ActionListener listenForUpdateProductButton) {
		updateProductButton.addActionListener(listenForUpdateProductButton);
	}
	
	public void openFindProductWindow(ActionListener listenForFindProductButton) {
		findProductButton.addActionListener(listenForFindProductButton);
	}
	
	public void backToMainMenuFromProducts(ActionListener listenForBackButton) {
		backFromProductsButton.addActionListener(listenForBackButton);
	}
	
	
	public void setCustomersMenuVisible() {
		CardLayout cardLayout = (CardLayout) menuPanel.getLayout();
		cardLayout.show(menuPanel, "Card 2");
	}
	
	public void setProductsMenuVisible() {
		CardLayout cardLayout = (CardLayout) menuPanel.getLayout();
		cardLayout.show(menuPanel, "Card 3");
	}
	
	public void setGeneralAccesMenuVisible() {
		CardLayout cardLayout = (CardLayout) menuPanel.getLayout();
		cardLayout.show(menuPanel, "Card 1");
	}

	public void setTablePanelVisible() {
		CardLayout cardLayout = (CardLayout) displayTablePanel.getLayout();
		cardLayout.show(displayTablePanel, "Table 2");
	}

	public void setEmptyPanelVisible() {
		CardLayout cardLayout = (CardLayout) displayTablePanel.getLayout();
		cardLayout.show(displayTablePanel, "Table 1");
	}
	
	public void setTable(JTable table) {
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(695, 600));
		JPanel viewCustomersPanel = new JPanel();
		viewCustomersPanel.add(scrollPane, BorderLayout.CENTER);
		viewCustomersPanel.setPreferredSize(new Dimension(695, 600));
		displayTablePanel.add(viewCustomersPanel, "Table 2");
		CardLayout cardLayout = (CardLayout) displayTablePanel.getLayout();
		cardLayout.show(displayTablePanel, "Table 2");
	}

}
