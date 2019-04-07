package Presentation;

import javax.swing.JFrame;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class UpdateProductWindow extends JFrame {
	private Container container = getContentPane();
	private JLabel idLabel = new JLabel("ID");
	private JLabel nameLabel = new JLabel("PRODUCT NAME");
	private JLabel priceLabel = new JLabel("PRICE");
	private JLabel descriptionLabel = new JLabel("DESCRIPTION");
	private JLabel stocLabel = new JLabel("STOC");
	
	private JTextField idTextField = new JTextField();
	private JTextField nameTextField = new JTextField();
	private JTextField priceTextField = new JTextField();
	private JTextField descriptionTextField = new JTextField();
	private JTextField stocTextField = new JTextField();
	
	private JButton updateProductButton = new JButton("UPDATE PRODUCT");
	
	public UpdateProductWindow() {
		this.setBounds(10, 10, 370, 300);
		this.setName("Update Product");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		container.setLayout(null);
		
		Font font = new Font("Times New Roman", Font.LAYOUT_NO_LIMIT_CONTEXT, 18);
		idLabel.setFont(font);
		nameLabel.setFont(font);
		priceLabel.setFont(font);
		descriptionLabel.setFont(font);
		stocLabel.setFont(font);
		
		idLabel.setBounds(20, 15, 170, 30);
		idTextField.setBounds(170, 15, 170, 30);
		nameLabel.setBounds(20, 60, 170, 30);
		nameTextField.setBounds(170, 60, 170, 30);
		priceLabel.setBounds(20, 80, 170, 50);
		priceTextField.setBounds(170, 90, 170, 30);
		descriptionLabel.setBounds(20, 110, 170, 50);
		descriptionTextField.setBounds(170, 120, 170, 30);
		stocLabel.setBounds(20, 140, 170, 50);
		stocTextField.setBounds(170, 150, 170, 30);
		updateProductButton.setBounds(100, 200, 160, 40);
		
		container.add(idLabel);
		container.add(idTextField);
		container.add(nameLabel);
		container.add(nameTextField);
		container.add(priceLabel);
		container.add(priceTextField);
		container.add(descriptionLabel);
		container.add(descriptionTextField);
		container.add(stocLabel);
		container.add(stocTextField);
		container.add(updateProductButton);
	
	}
		
	public void updateProductFromUpdateWindow(ActionListener listenForUpdateProductButton) {
		updateProductButton.addActionListener(listenForUpdateProductButton);
	}
	
	public int getId() {
		if(idTextField.getText().equals(""))
			return 0;
		return Integer.parseInt(idTextField.getText());
	}
	
	public String getProductName() {
		return nameTextField.getText();
	}
	
	public String getPrice() {
		return priceTextField.getText();
	}

	public String getDescription() {
		return descriptionTextField.getText();
	}
	
	public String getStoc() {
		return stocTextField.getText();
	}
	
	public void setValuesFromUpdateProductWindowToNull() {
		idTextField.setText(null);
		nameTextField.setText("");
		priceTextField.setText(null);
		descriptionTextField.setText("");
		stocTextField.setText(null);
	}
	
}
