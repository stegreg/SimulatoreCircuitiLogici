package ui.verbi;

import java.util.List;

import Eccezioni.ComandoNonValidoException;
import Eccezioni.ElementoNonTrovatoException;
import Eccezioni.EventoNonValidoException;
import Eccezioni.IstanteNonValidoException;
import main.Circuito;

public class UndefVerboHandler extends VerboHandler {
	private Circuito cirRef;
	
	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		if(parametriVerbo.size() == 0) throw new ComandoNonValidoException("Numero parametri non valido."); 
		
		cirRef = cir;
		
		List<String> subListCmd = parametriVerbo.subList(1, parametriVerbo.size());
		String complementoOggetto = parametriVerbo.get(0).toLowerCase();
		
		if(complementoOggetto.equals("gate"))
			undefPorta(subListCmd);
		else if(complementoOggetto.equals("signal"))
			undefSegnale(subListCmd);
		else if(complementoOggetto.equals("event"))
			undefEvento(subListCmd);
		else 
			throw new ComandoNonValidoException("Tipo elemento da eliminare non valido.");
	}

	private void undefEvento(List<String> subListCmd) throws ComandoNonValidoException {		
		if(subListCmd.size() != 2) throw new ComandoNonValidoException("Numero parametri non valido.");
		
		try {
			cirRef.rimuoviEvento(subListCmd.get(0), Integer.parseInt(subListCmd.get(1)));
		} catch (IstanteNonValidoException | ElementoNonTrovatoException | EventoNonValidoException | NumberFormatException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}

	private void undefSegnale(List<String> subListCmd) throws ComandoNonValidoException {
		if(subListCmd.size() != 1) throw new ComandoNonValidoException("Numero parametri non valido.");		
		
		try {
			cirRef.rimuoviSegnale(subListCmd.get(0));
		} catch (ElementoNonTrovatoException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}

	private void undefPorta(List<String> subListCmd) throws ComandoNonValidoException {
		if(subListCmd.size() != 1) throw new ComandoNonValidoException("Numero parametri non valido.");		
		
		try {
			cirRef.rimuoviPorta(subListCmd.get(0));
		} catch (ElementoNonTrovatoException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}

}
