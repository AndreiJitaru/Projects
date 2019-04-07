package Exceptions;

public class InvalidWithdrawalException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidWithdrawalException() {}
	
	public InvalidWithdrawalException(String msg) {
		super(msg);
	}
}