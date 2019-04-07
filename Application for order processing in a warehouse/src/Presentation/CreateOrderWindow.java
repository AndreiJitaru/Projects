package Presentation;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class CreateOrderWindow extends JFrame{
	private Container container = getContentPane();
	
	private JLabel idCustomerLabel = new JLabel("Customer'S ID");
	private JLabel idProductLabel = new JLabel("PRODUCTS'S ID");
	private JLabel quantityLabel = new JLabel("QUANTITY");
	
	private JTextField idCustomerTextField = new JTextField();
	private JTextField idProductTextField = new JTextField();
	private JTextField quantityTextField = new JTextField();
	
	private JButton createOrderButton = new JButton("CREATE");
	
	public CreateOrderWindow() {
		this.setBounds(10, 10, 370, 270);
		this.setName("Create Order");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
				
		Font font = new Font("Times New Roman", Font.LAYOUT_NO_LIMIT_CONTEXT, 18);
		idCustomerLabel.setFont(font);
		idProductLabel.setFont(font);
		quantityLabel.setFont(font);
		
		idCustomerLabel.setBounds(20, 30, 140, 30);
		idCustomerTextField.setBounds(170, 30, 170, 30);
		idProductLabel.setBounds(20, 60, 140, 30);
		idProductTextField.setBounds(170, 60, 170, 30);
		quantityLabel.setBounds(20, 90, 140, 30);
		quantityTextField.setBounds(170, 90, 170, 30);
		
		createOrderButton.setBounds(125, 160, 120, 40);
		
		container.add(idCustomerLabel);
		container.add(idCustomerTextField);
		container.add(idProductLabel);
		container.add(idProductTextField);
		container.add(quantityLabel);
		container.add(quantityTextField);
		container.add(createOrderButton);
		
		container.setLayout(null);
	}
	
	public void createOrder(ActionListener listenForCreateOrderButton) {
		createOrderButton.addActionListener(listenForCreateOrderButton);
	}
	
	public int getIdCustomer() {
		if(idCustomerTextField.getText().equals(""))
			return 0;
		return Integer.parseInt(idCustomerTextField.getText());
	}
	
	public int getIdProduct() {
		if(idProductTextField.getText().equals(""))
			return 0;
		return Integer.parseInt(idProductTextField.getText());
	}
	
	public int getQuantity() {
		if(quantityTextField.getText().equals(""))
			return 0;
		return Integer.parseInt(quantityTextField.getText());
	}
	
	public void setValuesFromCreateOrderWindowToNull() {
		idCustomerTextField.setText("");
		idProductTextField.setText("");
		quantityTextField.setText("");
	}
}
