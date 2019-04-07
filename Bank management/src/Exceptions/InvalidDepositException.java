package Exceptions;

public class InvalidDepositException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidDepositException() {}
	
	public InvalidDepositException(String msg) {
		super(msg);
	}
}