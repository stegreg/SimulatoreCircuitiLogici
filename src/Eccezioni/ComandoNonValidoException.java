package Eccezioni;

public class ComandoNonValidoException extends Exception {
	public ComandoNonValidoException() {
		super();
	}
	
	public ComandoNonValidoException(String message) {
		super(message);
	}
}
