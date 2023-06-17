package ui.adattatori;

import Eccezioni.ElementoNonTrovatoException;

public class AdattatoreStatoSegnale {
	public static boolean getStato(String value) throws ElementoNonTrovatoException {
		if(value.toLowerCase().equals("high"))
			return true;		
		else if(value.toLowerCase().equals("low")) 
			return false;
		else
			throw new ElementoNonTrovatoException("Stato segnale '" + value + "' non valido.");
	}
}
