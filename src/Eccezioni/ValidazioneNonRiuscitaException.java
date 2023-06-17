package Eccezioni;

public class ValidazioneNonRiuscitaException extends Exception {
	public ValidazioneNonRiuscitaException() {
		super();
	}
	
	public ValidazioneNonRiuscitaException(String message) {
		super(message);
	}
}
