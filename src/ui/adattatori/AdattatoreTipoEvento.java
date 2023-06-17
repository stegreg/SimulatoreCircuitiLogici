package ui.adattatori;

import Eccezioni.ElementoNonTrovatoException;
import eventi.TipoEvento;

public class AdattatoreTipoEvento {
	public static TipoEvento getTipo(String evtStr) throws ElementoNonTrovatoException {
		if(evtStr.toLowerCase().equals("raise"))
			return TipoEvento.EVT_RAISE;
		else if(evtStr.toLowerCase().equals("fall"))
			return TipoEvento.EVT_FALL;
		else
			throw new ElementoNonTrovatoException("tipo evento '" + evtStr + "' non trovato.");
	}
}
