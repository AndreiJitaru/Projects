package Exceptions;

@SuppressWarnings("serial")
public class IllegalInputException extends Exception {

	public IllegalInputException() {}
	
	public IllegalInputException(String msg) {
		super(msg);
	}
}