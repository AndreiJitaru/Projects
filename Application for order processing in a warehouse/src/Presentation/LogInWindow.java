package Presentation;

import javax.swing.JFrame;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class LogInWindow extends JFrame {
	private Container container = getContentPane();
	private JLabel titleLabel = new JLabel("WarehouseApp");
	private JLabel userLabel = new JLabel("USERNAME");
	private JLabel passwordLabel = new JLabel("PASSWORD");
	private JTextField usernameTextField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JButton loginButton = new JButton("LOGIN");
	private JCheckBox showPassword = new JCheckBox("Show Password");
	private JLabel logInAsLabel = new JLabel("LOGIN AS");
	private JComboBox<String> logInAsComboBox = new JComboBox<String>();
	
	public LogInWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10, 10, 370, 370);
		this.setName("Login Form");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		container.setLayout(null);
		
		titleLabel.setBounds(80, -10, 200, 100);
		userLabel.setBounds(50, 80, 100, 30);
		usernameTextField.setBounds(150, 80, 150, 30);
        passwordLabel.setBounds(50, 130, 100, 30);
        passwordField.setBounds(150, 130, 150, 30);
        logInAsLabel.setBounds(50, 180, 100, 30);
        logInAsComboBox.setBounds(150, 180, 150, 30);
        showPassword.setBounds(150, 220, 150, 30);
        loginButton.setBounds(150, 260, 100, 30);
		
		Font font = new Font("Times New Roman", Font.BOLD, 30);
		titleLabel.setFont(font);
		logInAsComboBox.addItem("ADMIN");
		logInAsComboBox.addItem("CLIENT");
		logInAsComboBox.setSelectedIndex(-1);
        
        container.add(titleLabel);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(usernameTextField);
        container.add(passwordField);
        container.add(logInAsLabel);
        container.add(logInAsComboBox);
        container.add(showPassword);
        container.add(loginButton);
        
		this.setVisible(true);
	}
	
	public void showPasswordWhenClicked(ActionListener listenForPassword) {
		showPassword.addActionListener(listenForPassword);
	}
	
	public void connectWhenClicked(ActionListener listenForLoginButton) {
		loginButton.addActionListener(listenForLoginButton);
	}
	
	public String getPassword() {
		return String.valueOf(passwordField.getPassword());
	}
	
	public String getUsername() {
		return usernameTextField.getText();
	}
	
	public boolean getPasswordStatus() {
		if (showPassword.isSelected()==true) 
			return true;
        return false;
	}
	
	public void setPasswordVisible() {
		passwordField.setEchoChar((char) 0);
	}
	
	public void setPasswordInvisible() {
		passwordField.setEchoChar('*');
	}	

	public String getLogInAsValue() {
		return (String)logInAsComboBox.getSelectedItem();
	}

	public boolean getJComboBoxStatus() {
		if(logInAsComboBox.getSelectedIndex()==-1) {
			return true;
		}
		return false;
	}
}
