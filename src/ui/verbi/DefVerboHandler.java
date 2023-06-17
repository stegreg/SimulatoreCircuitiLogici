package ui.verbi;

import java.util.List;

import Eccezioni.ComandoNonValidoException;
import Eccezioni.ElementoEsistenteException;
import Eccezioni.ElementoNonTrovatoException;
import Eccezioni.EventoNonValidoException;
import Eccezioni.IstanteNonValidoException;
import main.Circuito;
import ui.adattatori.AdattatoreNomePorta;
import ui.adattatori.AdattatoreStatoSegnale;
import ui.adattatori.AdattatoreTipoEvento;

public class DefVerboHandler extends VerboHandler {
	private Circuito cirRef;
	
	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		if(parametriVerbo.size() == 0) throw new ComandoNonValidoException("Numero parametri non valido."); 
		
		cirRef = cir;
		
		List<String> subListCmd = parametriVerbo.subList(1, parametriVerbo.size());
		String complementoOggetto = parametriVerbo.get(0).toLowerCase();
		
		if(complementoOggetto.equals("gate"))
			defPorta(subListCmd);
		else if(complementoOggetto.equals("signal"))
			defSegnale(subListCmd);
		else if(complementoOggetto.equals("event"))
			defEvento(subListCmd);
		else 
			throw new ComandoNonValidoException("Tipo elemento da definire non valido.");
	}
	
	private void defPorta(List<String> params) throws ComandoNonValidoException{
		if(params.size() != 2) throw new ComandoNonValidoException("Numero parametri non valido.");
		
		try {
			cirRef.addPorta(params.get(0), AdattatoreNomePorta.getPorta(params.get(1)));
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}
	private void defSegnale(List<String> params) throws ComandoNonValidoException{		
		boolean statoIniziale = false;
		
		if(params.size() == 2) {
			try {
				statoIniziale = AdattatoreStatoSegnale.getStato(params.get(1));
			} catch (ElementoNonTrovatoException e) {
				throw new ComandoNonValidoException(e.getMessage());
			}
		}
		else if(params.size() == 1)
			statoIniziale = false;
		else
			throw new ComandoNonValidoException("Numero parametri non valido.");			

		try {
			cirRef.addSegnale(params.get(0), statoIniziale);
		} catch (ElementoEsistenteException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}
	private void defEvento(List<String> params) throws ComandoNonValidoException{
		if(params.size() != 3) throw new ComandoNonValidoException("Numero parametri non valido.");
		
		try {
			cirRef.aggiungiEvento(AdattatoreTipoEvento.getTipo(params.get(0)), params.get(1), Integer.parseInt(params.get(2)));
		} catch (IstanteNonValidoException | ElementoNonTrovatoException | EventoNonValidoException | NumberFormatException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}
}
