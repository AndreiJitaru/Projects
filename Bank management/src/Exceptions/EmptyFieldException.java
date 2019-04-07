package Exceptions;

public class EmptyFieldException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmptyFieldException() {}
	
	public EmptyFieldException(String msg) {
		super(msg);
	}
}