package ui.verbi;

import java.util.List;

import Eccezioni.ComandoNonValidoException;
import main.Circuito;

public class QuitVerboHandler extends VerboHandler {

	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		System.out.println("Quitting program...bye!");
		System.exit(0);
	}

}
