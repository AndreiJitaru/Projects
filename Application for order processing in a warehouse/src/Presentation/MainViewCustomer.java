package Presentation;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class MainViewCustomer extends JFrame{
	
	private JButton viewProfileButton = new JButton("VIEW PROFILE");
	private JButton editProfileButton = new JButton("EDIT PROFILE");
	private JButton createOrderButton = new JButton("CREATE ORDER");
	private JButton viewProductsButton = new JButton("VIEW PRODUCTS");
	private JButton findProductButton = new JButton("FIND PRODUCT");
	private JButton viewOrders = new JButton("VIEW ORDERS");
	
	private JPanel backgroundPanel = new JPanel();
	private JPanel generalAccesMenuPanel = new JPanel();
	private JPanel fullProfilePanel = new JPanel(new BorderLayout());
	private JPanel displayTablePanel = new JPanel(new CardLayout());
	
	private JLabel profileLabel = new JLabel("MY PROFILE");
	private JLabel idLabel = new JLabel("ID: ");
	private JLabel fullNameLabel = new JLabel("NAME: ");
	private JLabel emailLabel = new JLabel("EMAIL: ");
	private JLabel usernameLabel = new JLabel("USERNAME: ");
	private JLabel passwordLabel = new JLabel("PASSWORD: ");
	
	private JLabel idValueLabel = new JLabel();
	private JLabel fullNameValueLabel = new JLabel();
	private JLabel emailValueLabel = new JLabel();
	private JLabel usernameValueLabel = new JLabel();
	private JLabel passwordValueLabel = new JLabel();
	
	private JPanel defaultBackgroundPanel = new JPanel();
	
	public MainViewCustomer() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 600);
		this.setName("Login Form");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		backgroundPanel.setLayout(new BorderLayout());
	
		generalAccesMenuPanel.setPreferredSize(new Dimension(200, 600));
		generalAccesMenuPanel.setLayout(new BoxLayout(generalAccesMenuPanel, BoxLayout.PAGE_AXIS));
		generalAccesMenuPanel.setBackground(new Color(45, 32, 100));
		
		viewProfileButton.setMaximumSize(new Dimension(200, 40));
		editProfileButton.setMaximumSize(new Dimension(200, 40));
		createOrderButton.setMaximumSize(new Dimension(200, 40));
		viewProductsButton.setMaximumSize(new Dimension(200, 40));
		findProductButton.setMaximumSize(new Dimension(200, 40));
		viewOrders.setMaximumSize(new Dimension(200, 40));
		
		Font font = new Font("Times New Roman", Font.LAYOUT_NO_LIMIT_CONTEXT, 30);
		Font font1 = new Font("Times New Roman", Font.LAYOUT_NO_LIMIT_CONTEXT, 18);
	
		profileLabel.setFont(font);
		idLabel.setFont(font1);
		fullNameLabel.setFont(font1);
		emailLabel.setFont(font1);
		usernameLabel.setFont(font1);
		passwordLabel.setFont(font1);
		idValueLabel.setFont(font1);
		fullNameValueLabel.setFont(font1);
		emailValueLabel.setFont(font1);
		usernameValueLabel.setFont(font1);
		passwordValueLabel.setFont(font1);
		
		profileLabel.setForeground(Color.WHITE);
		idLabel.setForeground(Color.WHITE);
		fullNameLabel.setForeground(Color.WHITE);
		emailLabel.setForeground(Color.WHITE);
		usernameLabel.setForeground(Color.WHITE);
		passwordLabel.setForeground(Color.WHITE);
		idValueLabel.setForeground(Color.WHITE);
		fullNameValueLabel.setForeground(Color.WHITE);
		emailValueLabel.setForeground(Color.WHITE);
		usernameValueLabel.setForeground(Color.WHITE);
		passwordValueLabel.setForeground(Color.WHITE);
		
		JPanel fillerPanel = new JPanel();
		fillerPanel.setPreferredSize(new Dimension(600, 200));
		
		JPanel title = new JPanel();
		title.add(profileLabel);
		
		JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		idPanel.add(idLabel);
		idPanel.add(idValueLabel);
		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		namePanel.add(fullNameLabel);
		namePanel.add(fullNameValueLabel);
		
		JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		emailPanel.add(emailLabel);
		emailPanel.add(emailValueLabel);
		
		
		JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		usernamePanel.add(usernameLabel);
		usernamePanel.add(usernameValueLabel);
		
		JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordValueLabel);
		
		JPanel profilePanel = new JPanel();
		profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.PAGE_AXIS));
		
		idPanel.setBackground(new Color(107, 3, 135));
		namePanel.setBackground(new Color(107, 3, 135));
		emailPanel.setBackground(new Color(107, 3, 135));
		usernamePanel.setBackground(new Color(107, 3, 135));
		passwordPanel.setBackground(new Color(107, 3, 135));
		title.setBackground(new Color(107, 3, 135));
		fillerPanel.setBackground(new Color(107, 3, 135));
		
		profilePanel.add(idPanel);
		profilePanel.add(namePanel);
		profilePanel.add(emailPanel);
		profilePanel.add(usernamePanel);
		profilePanel.add(passwordPanel);
		profilePanel.add(fillerPanel);
		
		fullProfilePanel.add(title, BorderLayout.PAGE_START);
		fullProfilePanel.add(profilePanel, BorderLayout.CENTER);
		
		generalAccesMenuPanel.add(viewProfileButton);
		generalAccesMenuPanel.add(editProfileButton);
		generalAccesMenuPanel.add(createOrderButton);
		generalAccesMenuPanel.add(viewProductsButton);
		generalAccesMenuPanel.add(findProductButton);
		generalAccesMenuPanel.add(viewOrders);

		displayTablePanel.add(defaultBackgroundPanel, "Table 1");
		displayTablePanel.add(fullProfilePanel, "Profile");
		
		profilePanel.setBackground(new Color(107, 3, 135));
		fullProfilePanel.setBackground(new Color(107, 3, 135));
		backgroundPanel.setBackground(new Color(107, 3, 135));
		defaultBackgroundPanel.setBackground(new Color(107, 3, 135));
		this.setBackground(new Color(107, 3, 135));
		
		backgroundPanel.add(displayTablePanel, BorderLayout.CENTER);
		backgroundPanel.add(generalAccesMenuPanel, BorderLayout.LINE_START);
		
		this.add(backgroundPanel);
	}
	
	public void viewProfile(ActionListener listenForViewProfileButton) {
		viewProfileButton.addActionListener(listenForViewProfileButton);
	}

	public void openEditProfileWindow(ActionListener listenForEditProfileButton) {
		editProfileButton.addActionListener(listenForEditProfileButton);
	}
	
	public void openCreateOrderWindow(ActionListener listenForCreateOrderButton) {
		createOrderButton.addActionListener(listenForCreateOrderButton);
	}
	
	public void viewProductsTable(ActionListener listenForViewProductsButton) {
		viewProductsButton.addActionListener(listenForViewProductsButton);
	}

	public void openFindProductWindow(ActionListener listenForFindProductButton) {
		findProductButton.addActionListener(listenForFindProductButton);
	}
	
	public void viewOrdersTable(ActionListener listenForViewOrdersTable) {
		viewOrders.addActionListener(listenForViewOrdersTable);
	}
	
	public void setTablePanelVisible() {
		CardLayout cardLayout = (CardLayout) displayTablePanel.getLayout();
		cardLayout.show(displayTablePanel, "Table 2");
	}

	public void setProfilePanelVisible() {
		CardLayout cardLayout = (CardLayout) displayTablePanel.getLayout();
		cardLayout.show(displayTablePanel, "Profile");
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
	
	public void setProfileValues(int id, String name, String email, String username, String password) {
		idValueLabel.setText(Integer.toString(id));
		fullNameValueLabel.setText(name);
		emailValueLabel.setText(email);
		usernameValueLabel.setText(username);
		passwordValueLabel.setText(password);
	}
}
