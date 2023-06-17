package ui.verbi;

import java.util.List;

import Eccezioni.ComandoNonValidoException;
import Eccezioni.ElementoNonTrovatoException;
import Eccezioni.SlotNonValidoException;
import main.Circuito;

public class UnlinkVerboHandler extends VerboHandler {

	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		if(parametriVerbo.size() != 2) throw new ComandoNonValidoException("Numero parametri non valido");
		
		String 	nomePorta = parametriVerbo.get(0);
		String 	tipoPorta;
		
		if(parametriVerbo.get(1).toLowerCase().startsWith("i"))
			tipoPorta = "input";
		else if(parametriVerbo.get(1).toLowerCase().startsWith("o"))
			tipoPorta = "output";
		else 
			throw new ComandoNonValidoException("Tipologia di slot '"+ parametriVerbo.get(1).charAt(0) + "' non valida.");

		int 	slotPorta = Integer.parseInt(parametriVerbo.get(1).substring(1));
		
		try {
			if(tipoPorta.equals("input")) 
				cir.scollegaInput(nomePorta, slotPorta);
			else
				cir.scollegaOutput(nomePorta, slotPorta);
		}catch(ElementoNonTrovatoException | SlotNonValidoException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}

}
