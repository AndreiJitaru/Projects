package project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import errors.DivisionByZeroException;
import errors.PolynomialFormatException;

public class Controller {

	private View theView;

	public Controller(View theView) {
		this.theView = theView;

		this.theView.addTwoPolynomialsListener(new AddListener());
		this.theView.subTwoPolynomialsListener(new SubListener());
		this.theView.mulTwoPolynomialsListener(new MulListener());
		this.theView.divTwoPolynomialsListener(new DivListener());
		this.theView.comboBoxPol1DerivateOrIntegrateListener(new ComboBox1Listener());
		this.theView.comboBoxPol2DerivateOrIntegrateListener(new ComboBox2Listener());
	}

	public class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				Polinom polynomial1 = Polinom.convertStringToPolynomial(theView.getFirstPolynomialAsString()); //convertim String-ul introdus in primul textField ca polinom  
				if(theView.getFirstPolynomialAsString().length()!=polynomial1.convertIntegerPolynomialBackToString().length() && polynomial1.convertIntegerPolynomialBackToString()!=null) { //polinomul obtinut il reconvertim in String si verificam daca valoarea sa este diferita de cea initiala
					throw new PolynomialFormatException("You need to introduce 2 valid Polynomials!"); //daca da atunci aruncam o exceptie
				}
				else {
					try {
						Polinom polynomial2 = Polinom.convertStringToPolynomial(theView.getSecondPolynomialAsString());//convertim String-ul introdus in cel de-al doilea textField ca polinom
						if(theView.getSecondPolynomialAsString().length()!=polynomial2.convertIntegerPolynomialBackToString().length() && polynomial2.convertIntegerPolynomialBackToString()!=null) { //polinomul obtinut il reconvertim in String si verificam daca valoarea sa este diferita de cea initiala
							throw new PolynomialFormatException("You need to introduce 2 valid Polynomials!"); //daca da atunci aruncam o exceptie
						} 
						else {				
							theView.setIntegerTypeResult(polynomial1.addPolynomial(polynomial2)); //altfel calculam valoarea in urma operatiei cerute si o punem in TextField-ul Result
						}
					}
					catch(PolynomialFormatException e) {
						theView.displayErrorMessage(e.getMessage()); //prindem prima exceptie
					}
				}
			}
			catch(PolynomialFormatException e) {
				theView.displayErrorMessage(e.getMessage()); //prindem cea de-a doua exceptie
			}
		}
	}

	// idem si in cazul celorlalte clase de ActionListeneri
	
	public class SubListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				Polinom polynomial1 = Polinom.convertStringToPolynomial(theView.getFirstPolynomialAsString());
				if(theView.getFirstPolynomialAsString().length()!=polynomial1.convertIntegerPolynomialBackToString().length() && polynomial1.convertIntegerPolynomialBackToString()!=null) {
					throw new PolynomialFormatException("You need to introduce 2 valid Polynomials!");
				}
				else {
					try {
						Polinom polynomial2 = Polinom.convertStringToPolynomial(theView.getSecondPolynomialAsString());
						if(theView.getSecondPolynomialAsString().length()!=polynomial2.convertIntegerPolynomialBackToString().length() && polynomial2.convertIntegerPolynomialBackToString()!=null) {
							throw new PolynomialFormatException("You need to introduce 2 valid Polynomials!");
						} 
						else {								
							theView.setIntegerTypeResult(polynomial1.subPolynomial(polynomial2));
						}
					}
					catch(PolynomialFormatException e) {
						theView.displayErrorMessage(e.getMessage());
					}
				}
			}
			catch(PolynomialFormatException e) {
				theView.displayErrorMessage(e.getMessage());
			}
		}
	}

	public class MulListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				Polinom polynomial1 = Polinom.convertStringToPolynomial(theView.getFirstPolynomialAsString());
				if(theView.getFirstPolynomialAsString().length()!=polynomial1.convertIntegerPolynomialBackToString().length() && polynomial1.convertIntegerPolynomialBackToString()!=null) {
					throw new PolynomialFormatException("You need to introduce 2 valid Polynomials!");
				}
				else {
					try {
						Polinom polynomial2 = Polinom.convertStringToPolynomial(theView.getSecondPolynomialAsString());
						if(theView.getSecondPolynomialAsString().length()!=polynomial2.convertIntegerPolynomialBackToString().length() && polynomial2.convertIntegerPolynomialBackToString()!=null) {
							throw new PolynomialFormatException("You need to introduce 2 valid Polynomials!");
						} 
						else {								
							theView.setIntegerTypeResult(polynomial1.multiplyPolynomial(polynomial2));
						}
					}
					catch(PolynomialFormatException e) {
						theView.displayErrorMessage(e.getMessage());
					}
				}
			}
			catch(PolynomialFormatException e) {
				theView.displayErrorMessage(e.getMessage());
			}
		}
	}

	public class DivListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				Polinom polynomial1 = Polinom.convertStringToPolynomial(theView.getFirstPolynomialAsString());
				if(theView.getFirstPolynomialAsString().length()!=polynomial1.convertIntegerPolynomialBackToString().length() && polynomial1.convertIntegerPolynomialBackToString()!=null) {
					throw new PolynomialFormatException("You need to introduce 2 valid Polynomials!");
				}
				else {
					try {
						Polinom polynomial2 = Polinom.convertStringToPolynomial(theView.getSecondPolynomialAsString());
						if(theView.getSecondPolynomialAsString().length()!=polynomial2.convertIntegerPolynomialBackToString().length() && polynomial2.convertIntegerPolynomialBackToString()!=null) {
							throw new PolynomialFormatException("You need to introduce 2 valid Polynomials!");
						} 
						else {	
							try {
								if(theView.getSecondPolynomialAsString().equals("0")) { // verificam daca deimpartitul este egal cu polinomul null
									throw new DivisionByZeroException("You can't divide a Polynomial by 0!");//aruncam exceptia
								}
								else {
									theView.setDivisionResult(polynomial1.dividePolynomial(polynomial2));
								}
							}
							catch(DivisionByZeroException e) {
								theView.displayErrorMessage(e.getMessage());//prindem exceptie
							}
						}
					}
					catch(PolynomialFormatException e) {
						theView.displayErrorMessage(e.getMessage());
					}
				}
			}
			catch(PolynomialFormatException e) {
				theView.displayErrorMessage(e.getMessage());
			}
		}
	}

	public class ComboBox1Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				Polinom Polynomial = Polinom.convertStringToPolynomial(theView.getFirstPolynomialAsString());
				if(theView.getFirstPolynomialAsString().length()!=Polynomial.convertIntegerPolynomialBackToString().length() && Polynomial.convertIntegerPolynomialBackToString()!=null) {
					throw new PolynomialFormatException("You need to introduce a valid Polynomial!");
				}
				else {
					if(theView.getOperationFromComboBox1().equals("Derivate")) {
						theView.setIntegerTypeResult(Polynomial.derivatePolynomial());
						theView.setOperationFromComboBox1Null();
					}
					else if(theView.getOperationFromComboBox1().equals("Integrate")) {
						theView.setDoubleTypeResult(Polynomial.integratePolynomial());
						theView.setOperationFromComboBox1Null();
					}
					else {
						theView.setResultEmpty();
					}
				}
			}
			catch(PolynomialFormatException e) {
				theView.displayErrorMessage(e.getMessage());
			}
		}
	}

	public class ComboBox2Listener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				Polinom polynomial = Polinom.convertStringToPolynomial(theView.getSecondPolynomialAsString());
				if(theView.getSecondPolynomialAsString().length()!=polynomial.convertIntegerPolynomialBackToString().length() && polynomial.convertIntegerPolynomialBackToString()!=null) {
					throw new PolynomialFormatException("You need to introduce a valid Polynomial!");
				}
				else {
					if(theView.getOperationFromComboBox2().equals("Derivate")) {
						theView.setIntegerTypeResult(polynomial.derivatePolynomial());
						theView.setOperationFromComboBox2Null();
					}
					else if(theView.getOperationFromComboBox2().equals("Integrate")) {
						theView.setDoubleTypeResult(polynomial.integratePolynomial());
						theView.setOperationFromComboBox2Null();
					}
					else {
						theView.setResultEmpty();
					}
				}
			}
			catch(PolynomialFormatException e) {
				theView.displayErrorMessage(e.getMessage());
			}
		}
	}
}

