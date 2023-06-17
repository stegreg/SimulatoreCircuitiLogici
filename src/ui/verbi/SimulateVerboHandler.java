package ui.verbi;

import java.util.List;

import Eccezioni.ComandoNonValidoException;
import Eccezioni.IstanteNonValidoException;
import Eccezioni.ValidazioneNonRiuscitaException;
import main.Circuito;

public class SimulateVerboHandler extends VerboHandler{

	@Override
	public void esegui(List<String> parametriVerbo, Circuito cirRef) throws ComandoNonValidoException {
		if(parametriVerbo.size() != 1) throw new ComandoNonValidoException("Comando non valido.");
		
		int tempoSimulazione = Integer.parseInt(parametriVerbo.get(0));
		try {
			cirRef.simula(tempoSimulazione);
		} catch (IstanteNonValidoException | ValidazioneNonRiuscitaException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}

}
