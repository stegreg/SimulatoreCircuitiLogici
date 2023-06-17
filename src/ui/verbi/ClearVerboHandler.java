package ui.verbi;

import java.util.List;

import Eccezioni.ComandoNonValidoException;
import main.Circuito;

public class ClearVerboHandler extends VerboHandler{

	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		cir.pulisci();
	}

}
