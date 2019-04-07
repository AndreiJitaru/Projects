package Presentation;

import javax.swing.JFrame;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class UpdateCustomerWindow extends JFrame {
	private Container container = getContentPane();
	private JLabel idLabel = new JLabel("ID");
	private JLabel firstNameLabel = new JLabel("FIRST NAME");
	private JLabel lastNameLabel = new JLabel("SECOND NAME");
	private JLabel phoneNumberLabel = new JLabel("PHONE NUMBER");
	private JLabel emailLabel = new JLabel("EMAIL");
	private JLabel usernameLabel = new JLabel("USERNAME");
	private JLabel passwordLabel = new JLabel("PASSWORD");
	
	private JTextField idTextField = new JTextField();
	private JTextField firstNameTextField = new JTextField();
	private JTextField lastNameTextField = new JTextField();
	private JTextField phoneNumberTextField = new JTextField();
	private JTextField emailTextField = new JTextField();
	private JTextField usernameTextField = new JTextField();
	private JTextField passwordTextField = new JTextField();
	
	private JButton updateCustomerButton = new JButton("UPDATE Customer");
	
	public UpdateCustomerWindow() {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10, 10, 370, 370);
		this.setName("Update Customer");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		container.setLayout(null);
		
		Font font = new Font("Times New Roman", Font.LAYOUT_NO_LIMIT_CONTEXT, 18);
		idLabel.setFont(font);
		firstNameLabel.setFont(font);
		lastNameLabel.setFont(font);
		phoneNumberLabel.setFont(font);
		emailLabel.setFont(font);
		usernameLabel.setFont(font);
		passwordLabel.setFont(font);
		
		
		idLabel.setBounds(20, 15, 170, 30);
		idTextField.setBounds(170, 15, 170, 30);
		firstNameLabel.setBounds(20, 60, 170, 30);
		firstNameTextField.setBounds(170, 60, 170, 30);
		lastNameLabel.setBounds(20, 80, 170, 50);
		lastNameTextField.setBounds(170, 90, 170, 30);
		phoneNumberLabel.setBounds(20, 110, 170, 50);
		phoneNumberTextField.setBounds(170, 120, 170, 30);
		emailLabel.setBounds(20, 140, 170, 50);
		emailTextField.setBounds(170, 150, 170, 30);
		usernameLabel.setBounds(20, 180, 170, 30);
		usernameTextField.setBounds(170, 180, 170, 30);
		passwordLabel.setBounds(20, 210, 170, 30);
		passwordTextField.setBounds(170, 210, 170, 30);
		updateCustomerButton.setBounds(100, 260, 160, 40);
		
		container.add(idLabel);
		container.add(idTextField);
		container.add(firstNameLabel);
		container.add(firstNameTextField);
		container.add(lastNameLabel);
		container.add(lastNameTextField);
		container.add(phoneNumberLabel);
		container.add(phoneNumberTextField);
		container.add(emailLabel);
		container.add(emailTextField);
		container.add(usernameLabel);
		container.add(usernameTextField);
		container.add(passwordLabel);
		container.add(passwordTextField);
		container.add(updateCustomerButton);
	
	}
		
	public void updateCustomerFromUpdateWindow(ActionListener listenForUpdateCustomerButton) {
		updateCustomerButton.addActionListener(listenForUpdateCustomerButton);
	}
	
	public int getId() {
		if(idTextField.getText().equals(""))
			return 0;
		return Integer.parseInt(idTextField.getText());
	}
	
	public String getFirstName() {
		return firstNameTextField.getText();
	}
	
	public String getLastName() {
		return lastNameTextField.getText();
	}

	public String getPhoneNumber() {
		return phoneNumberTextField.getText();
	}
	
	public String getEmail() {
		return emailTextField.getText();
	}

	public String getUsername() {
		return usernameTextField.getText();
	}
	
	public String getPassword() {
		return passwordTextField.getText();
	}
	
	public void setIdAndUsernameUneditable() {
		idTextField.setEditable(false);
		usernameTextField.setEditable(false);
	}
	
	public void setIdAndUsernameEditable() {
		idTextField.setEditable(true);
		usernameTextField.setEditable(true);
	}
	
	public void setIdTextField(int value) {
		idTextField.setText(Integer.toString(value));
	}
	
	public void setValuesFromUpdateCustomerWindowToNull() {
		idTextField.setText(null);
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		phoneNumberTextField.setText("");
		emailTextField.setText("");
		usernameTextField.setText("");
		passwordTextField.setText("");
	}
}
