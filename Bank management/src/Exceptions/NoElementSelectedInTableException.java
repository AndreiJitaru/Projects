package Exceptions;

public class NoElementSelectedInTableException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoElementSelectedInTableException() {}
	
	public NoElementSelectedInTableException(String msg) {
		super(msg);
	}
}