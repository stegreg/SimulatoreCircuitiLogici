package ui.verbi;

import java.util.List;
import java.util.Map;

import Eccezioni.ComandoNonValidoException;
import main.Circuito;

public class ValidateVerboHandler extends VerboHandler {

	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		Map<String, List<String>> porteNonValide = cir.valida();
		
		System.out.println("Risultato validazione: ");
		if(porteNonValide.size() > 0) {
			System.out.println("I seguenti slot non sono stati collegati:");
			for(Map.Entry<String, List<String>> entry : porteNonValide.entrySet()) {
				for(int i = 0; i < entry.getValue().size(); i++)
					System.out.println("Slot '" + entry.getValue().get(i)  +"' della porta '"  + entry.getKey() + "'");
			}
		}
		else
			System.out.println("Tutto collegato!");
	}

}
