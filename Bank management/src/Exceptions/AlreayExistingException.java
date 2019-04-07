package Exceptions;

public class AlreayExistingException extends Exception {
	private static final long serialVersionUID = 1L;

	public AlreayExistingException() {}
	
	public AlreayExistingException(String msg) {
		super(msg);
	}
}