package errors;

public class PolynomialFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public PolynomialFormatException() {}
	
	public PolynomialFormatException(String msg) {
		super(msg);
	}
}
