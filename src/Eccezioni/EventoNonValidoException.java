package Eccezioni;

public class EventoNonValidoException extends Exception {
	public EventoNonValidoException() {
		super();
	}
	
	public EventoNonValidoException(String message) {
		super(message);
	}
}
