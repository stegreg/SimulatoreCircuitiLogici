package ui.verbi;

import java.util.List;
import java.util.Map;

import Eccezioni.ComandoNonValidoException;
import main.Circuito;
import porte.PortaLogica;
import segnali.Segnale;

public class ListVerboHandler extends VerboHandler{

	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		Map<String, PortaLogica> 	porte = cir.getListaPorte();
		Map<String, Segnale> 		segnali = cir.getListaSegnali();
		
		if(porte.isEmpty()) 
			System.out.println("Nessuna porta definita.");

		if(segnali.isEmpty()) 
			System.out.println("Nessun segnale definito.");
		
		for(Map.Entry<String, PortaLogica> entry : porte.entrySet())
			System.out.println(entry.getKey() + "\t" + entry.getValue().getClass().getName());

		for(Map.Entry<String, Segnale> entry : segnali.entrySet())
			System.out.println(entry.getKey() + "\t" + entry.getValue().getClass().getName());
	}

}
