package ui.adattatori;

import Eccezioni.ElementoNonTrovatoException;
import porte.TipoPorta;

/**
 * Classe adattatore: il suo compito è convertire il nome di
 * una porta logica a livello UI("AND", "OR", "NOT", ecc..) 
 * in un nome interpreatbile dallo strato di dominio ("TipoPorta.PORTA_AND", "TipoPorta.PORTA_OR", "TipoPorta.PORTA_NOT", ecc..)
 * */
public class AdattatoreNomePorta {
	public static TipoPorta getPorta(String tipo) throws ElementoNonTrovatoException {
		if(tipo.toLowerCase().equals("and"))
			return TipoPorta.PORTA_AND;
		else if(tipo.toLowerCase().equals("or"))
			return TipoPorta.PORTA_OR;
		else if(tipo.toLowerCase().equals("not"))
			return TipoPorta.PORTA_NOT;

		else
			throw new ElementoNonTrovatoException("Tipo porta '" + tipo + "' non trovato.");
	}
}
