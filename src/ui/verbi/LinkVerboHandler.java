package ui.verbi;

import java.util.List;

import Eccezioni.ComandoNonValidoException;
import Eccezioni.ElementoNonTrovatoException;
import Eccezioni.SlotNonValidoException;
import main.Circuito;

public class LinkVerboHandler extends VerboHandler {

	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		if(parametriVerbo.size() != 3) throw new ComandoNonValidoException("Numero parametri non valido.");
		
		String 	nomeSegnale = parametriVerbo.get(0);
		String 	nomePorta = parametriVerbo.get(1);
		
		String 	tipoPorta;
		if(parametriVerbo.get(2).toLowerCase().startsWith("i"))
			tipoPorta = "input";
		else if(parametriVerbo.get(2).toLowerCase().startsWith("o"))
			tipoPorta = "output";
		else 
			throw new ComandoNonValidoException("Tipologia di slot '"+ parametriVerbo.get(2).charAt(0) + "' non valida.");
		
		int slotPorta = 0;
		try {
			slotPorta = Integer.parseInt(parametriVerbo.get(2).substring(1));
		}catch(NumberFormatException e) {
			throw new ComandoNonValidoException("Formato slot non valido.");			
		}
		try {
			
			if(tipoPorta.equals("input")) 
				cir.collegaInput(nomeSegnale, nomePorta, slotPorta);
			else
				cir.collegaOutput(nomeSegnale, nomePorta, slotPorta);
			
		}catch(ElementoNonTrovatoException | SlotNonValidoException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}

}
