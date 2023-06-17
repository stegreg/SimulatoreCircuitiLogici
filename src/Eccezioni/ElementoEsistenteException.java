package Eccezioni;

public class ElementoEsistenteException extends Exception {
	public ElementoEsistenteException() {
		super();
	}
	
	public ElementoEsistenteException(String message) {
		super(message);
	}
}
