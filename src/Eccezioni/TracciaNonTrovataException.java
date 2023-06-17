package Eccezioni;

public class TracciaNonTrovataException extends Exception {
	public TracciaNonTrovataException() {
		super();
	}
	
	public TracciaNonTrovataException(String message) {
		super(message);
	}
}
