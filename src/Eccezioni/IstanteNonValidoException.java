package Eccezioni;

public class IstanteNonValidoException extends Exception {
	public IstanteNonValidoException() {
		super();
	}
	
	public IstanteNonValidoException(String message) {
		super(message);
	}
}
