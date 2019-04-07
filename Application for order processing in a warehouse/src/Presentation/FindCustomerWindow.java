package Presentation;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class FindCustomerWindow extends JFrame {
	private Container container = getContentPane();
	
	private JLabel giveIdLabel = new JLabel("GIVE THE ID");
	private JTextField giveIdTextField = new JTextField();
	private JButton findButton = new JButton("FIND");
		
	public FindCustomerWindow() {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10, 10, 300, 200);
		this.setName("Find Form");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
			
		Font font = new Font("Times New Roman", Font.LAYOUT_NO_START_CONTEXT, 25);
		giveIdLabel.setFont(font);
		
		container.setLayout(null);
		
		giveIdLabel.setBounds(90 , 25, 150, 30);
		giveIdTextField.setBounds(75, 65, 150, 30);
		findButton.setBounds(85, 105, 130, 30);
			
		container.add(giveIdLabel);
		container.add(giveIdTextField);	
	    container.add(findButton);
	        
	}
	
	public void findCustomer(ActionListener listenForDeleteButton) {
		findButton.addActionListener(listenForDeleteButton);
	}
	
	public int getId() {
		if(giveIdTextField.getText().equals(""))
			return 0;
		return Integer.parseInt(giveIdTextField.getText());
	}
	
	public void setIdTextFieldToNull() {
		giveIdTextField.setText(null);
	}
	
}
