package errors;

public class DivisionByZeroException extends Exception {
	private static final long serialVersionUID = 1L;

	public DivisionByZeroException() {
	}

	public DivisionByZeroException(String msg) {
		super(msg);
	}
}
