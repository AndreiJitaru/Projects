package project1;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class View extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel l1 = new JLabel("First Polynomial: ");
	private JTextField t1 = new JTextField(28);
	private JLabel l2 = new JLabel("Second Polynomial: ");
	private JTextField t2 = new JTextField(28);
	private JButton b1 = new JButton();
	private JButton b2 = new JButton();
	private JButton b3 = new JButton();
	private JButton b4 = new JButton();
	JComboBox<String> chooseOperationPol1 = new JComboBox<String>();
	JComboBox<String> chooseOperationPol2 = new JComboBox<String>();
	private JLabel l3 = new JLabel("Result: ");
	private JTextField t3 = new JTextField(28);

	public View(){ //cream interfata grafica - detalii in documentatie
		JPanel panel = new JPanel();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 500);
		panel.setSize(700, 500);
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		Font font1 = new Font("Times New Roman", Font.BOLD, 20);
		Font font3 = new Font("Times New Roman", Font.BOLD, 20);
		t1.setFont(font1);
		t2.setFont(font1);
		t3.setFont(font1);
		l1.setFont(font3);
		l2.setFont(font3);
		l3.setFont(font3);

		b1.setIcon(new ImageIcon("E:\\Proiecte_Andrei\\Stuff\\project1\\src\\resorces\\add.png"));
		b2.setIcon(new ImageIcon("E:\\\\Proiecte_Andrei\\\\Stuff\\\\project1\\\\src\\\\resorces\\\\sub.png"));
		b3.setIcon(new ImageIcon("E:\\\\Proiecte_Andrei\\\\Stuff\\\\project1\\\\src\\\\resorces\\\\mul.png"));
		b4.setIcon(new ImageIcon("E:\\\\Proiecte_Andrei\\\\Stuff\\\\project1\\\\src\\\\resorces\\\\div.png"));

		chooseOperationPol1.addItem("");
		chooseOperationPol1.addItem("Derivate");
		chooseOperationPol1.addItem("Integrate");

		chooseOperationPol2.addItem("");
		chooseOperationPol2.addItem("Derivate");
		chooseOperationPol2.addItem("Integrate");

		chooseOperationPol1.setSelectedIndex(0);
		chooseOperationPol2.setSelectedIndex(0);
		JScrollPane op1ListScrollPane = new JScrollPane(chooseOperationPol1);
		JScrollPane op2ListScrollPane = new JScrollPane(chooseOperationPol2);


		JPanel firstPolPanel = new JPanel();
		firstPolPanel.setSize(800, 100);
		firstPolPanel.setLayout(new FlowLayout(BoxLayout.LINE_AXIS));
		firstPolPanel.add(l1);
		firstPolPanel.add(t1);
		firstPolPanel.add(op1ListScrollPane);

		JPanel secondPolPanel = new JPanel();
		secondPolPanel.setSize(800, 100);
		secondPolPanel.setLayout(new FlowLayout(BoxLayout.LINE_AXIS));
		secondPolPanel.add(l2);
		secondPolPanel.add(t2);
		secondPolPanel.add(op2ListScrollPane);

		JPanel thirdPolPanel = new JPanel();
		thirdPolPanel.setSize(800, 100);
		thirdPolPanel.setLayout(new FlowLayout(BoxLayout.LINE_AXIS));
		b1.setPreferredSize(new Dimension(70, 50));
		thirdPolPanel.add(b1);
		thirdPolPanel.add(Box.createRigidArea(new Dimension(9, 0)));
		b2.setPreferredSize(new Dimension(70, 50));
		thirdPolPanel.add(b2);
		thirdPolPanel.add(Box.createRigidArea(new Dimension(9, 0)));
		b3.setPreferredSize(new Dimension(70, 50));
		thirdPolPanel.add(b3);
		thirdPolPanel.add(Box.createRigidArea(new Dimension(9, 0)));
		b4.setPreferredSize(new Dimension(70, 50));
		thirdPolPanel.add(b4);
		thirdPolPanel.add(Box.createRigidArea(new Dimension(120, 0)));

		JPanel forthPolPanel = new JPanel();
		forthPolPanel.setSize(800, 100);
		forthPolPanel.setLayout(new FlowLayout(BoxLayout.LINE_AXIS));
		forthPolPanel.add(l3);
		forthPolPanel.add(t3);
		forthPolPanel.add(Box.createRigidArea(new Dimension(80, 0)));

		JPanel aux1 = new JPanel();
		aux1.setSize(800, 100);

		firstPolPanel.setBackground(new Color(71, 117, 209));
		aux1.setBackground(new Color(71, 117, 209));
		secondPolPanel.setBackground(new Color(71, 117, 209));
		thirdPolPanel.setBackground(new Color(71, 117, 209));
		forthPolPanel.setBackground(new Color(71, 117, 209));

		panel.add(aux1);
		panel.add(firstPolPanel);
		panel.add(secondPolPanel);
		panel.add(thirdPolPanel);
		panel.add(forthPolPanel);

		this.add(panel);
	}

	public String getFirstPolynomialAsString() { //luam valoarea din primul textField
		return t1.getText();
	}

	public String getSecondPolynomialAsString() { //luam valoare din al doilea textFiel
		return t2.getText();
	}

	public String getResult() { //luam valoara din al treilea textField
		return t3.getText();
	}

	public String getOperationFromComboBox1() { //luam operatia din primul JComboBox
		return (String)chooseOperationPol1.getSelectedItem();
	}

	public String getOperationFromComboBox2() { //luam operatia din al doilea JComboBox
		return (String)chooseOperationPol2.getSelectedItem();
	}

	public void setOperationFromComboBox1Null() { //punem pe null operatia din primul JComboBox
		chooseOperationPol1.setSelectedIndex(0);
	}

	public void setOperationFromComboBox2Null() {//punem pe null operatia din al doilea JComboBox
		chooseOperationPol2.setSelectedIndex(0);
	}

	public void setIntegerTypeResult(Polinom result) { //punem in al treilea textField polinomul cu coeficienti intregi dat ca parametru sub forma de String
		t3.setText(result.convertIntegerPolynomialBackToString());
	}

	public void setDoubleTypeResult(Polinom result) { //punem in al treilea textField polinomul cu coeficienti reali dat ca parametru sub forma de String
		t3.setText(result.convertDoublePolynomialBackToString());
	}

	public void setDivisionResult(ArrayList<Polinom> result) { //punem in al treilea textField rezultatul impartirii
		String resultAsString = new String();
		int k = 0;
		Iterator<Polinom> i = result.iterator();
		while(i.hasNext()) {
			Polinom p = i.next();
			if(k==0) {
				resultAsString = p.convertDoublePolynomialBackToString(); 
				k++; // daca nu am pus inca quotientul in resultAsString il punem
			} 
			else { // urmeaza catul
				if(p.polinom.size()>0) {
					resultAsString = "C: " + resultAsString + "  R: " + p.convertDoublePolynomialBackToString(); //daca e diferit de 0
				}
				else {
					resultAsString = "C: " + resultAsString + "  R: 0"; //daca e zero
				}
			}
		}
		t3.setText(resultAsString);
	}

	public void setResultZero() { // punem rezultatul pe 9
		t3.setText("0");
	}

	public void setResultEmpty() { // nu punem nimic in rezultat
		t3.setText("");
	}

	//metode de adaugare ActionListener pentru interactiunei elementelor grafice cu logica
	public void addTwoPolynomialsListener(ActionListener listenForAddButton) {
		b1.addActionListener(listenForAddButton);
	}

	public void subTwoPolynomialsListener(ActionListener listenForSubButton) {
		b2.addActionListener(listenForSubButton);
	}

	public void mulTwoPolynomialsListener(ActionListener listenForMulButton) {
		b3.addActionListener(listenForMulButton);
	}

	public void divTwoPolynomialsListener(ActionListener listenForDivButton) {
		b4.addActionListener(listenForDivButton);
	}

	public void comboBoxPol1DerivateOrIntegrateListener(ActionListener listenForComboBox1) {
		chooseOperationPol1.addActionListener(listenForComboBox1);
	}

	public void comboBoxPol2DerivateOrIntegrateListener(ActionListener listenForComboBox2) {
		chooseOperationPol2.addActionListener(listenForComboBox2);
	}

	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		View w = new View();
		w.setVisible(true);
	}

}
