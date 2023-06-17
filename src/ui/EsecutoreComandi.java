package ui;

import java.util.List;

import Eccezioni.ComandoNonValidoException;
import main.Circuito;
import main.Timeline;
import ui.verbi.VerboHandler;
import ui.verbi.VerboHandlerFactory;

public class EsecutoreComandi {	
	public void esegui(List<String> comando, Circuito cirRef) throws ComandoNonValidoException {
		if(comando.get(0).equals("") || comando.get(0).startsWith("//")) return;
		
		//Ottieni l'handler del verbo relativo al comando
		VerboHandler verboHandler = VerboHandlerFactory.getSingleton().getHandler(comando.get(0));
		if(verboHandler == null)
			throw new ComandoNonValidoException("Comando non valido.");
		
		verboHandler.esegui(comando.subList(1, comando.size()), cirRef);
	}
}
