package project1;

public class MVCPolynomialCalculator {

	public static void main(String[] args) {
		View theView = new View();

		Controller theController = new Controller(theView);
		theView.setVisible(true);
	}
}
