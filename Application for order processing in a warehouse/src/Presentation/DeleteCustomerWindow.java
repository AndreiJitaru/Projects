package Presentation;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class DeleteCustomerWindow extends JFrame {
	private Container container = getContentPane();
	
	private JLabel giveIdLabel = new JLabel("GIVE THE ID");
	private JTextField giveIdTextField = new JTextField();
	private JButton deleteButton = new JButton("DELETE");
		
	public DeleteCustomerWindow() {
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(10, 10, 300, 200);
		this.setName("Delete Form");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
			
		Font font = new Font("Times New Roman", Font.LAYOUT_NO_START_CONTEXT, 20);
		giveIdLabel.setFont(font);
		
		container.setLayout(null);
		
		giveIdLabel.setBounds(90 , 25, 150, 30);
		giveIdTextField.setBounds(75, 65, 150, 30);
		deleteButton.setBounds(85, 105, 130, 30);
			
		container.add(giveIdLabel);
		container.add(giveIdTextField);	
	    container.add(deleteButton);
	        
	}
	
	public void deleteCustomer(ActionListener listenForDeleteButton) {
		deleteButton.addActionListener(listenForDeleteButton);
	}
	
	public int getId() {
		if(giveIdTextField.getText().equals(""))
			return 0;
		return Integer.parseInt(giveIdTextField.getText());
	}
	
	public void setIdToNull() {
		giveIdTextField.setText(null);
	}
}
