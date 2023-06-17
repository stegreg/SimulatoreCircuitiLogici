package porte;

import Eccezioni.ElementoNonTrovatoException;

public class PortaLogicaFactory {
	
	static PortaLogicaFactory instance = null;
	
	private PortaLogicaFactory() {
		
	}
	
	public PortaLogica creaPorta(TipoPorta tipo) throws ElementoNonTrovatoException {		
		if(tipo == TipoPorta.PORTA_AND)
			return new PortaLogicaAND();	
		
		else if(tipo == TipoPorta.PORTA_OR)
			return new PortaLogicaOR();		
		
		else if(tipo == TipoPorta.PORTA_NOT)
			return new PortaLogicaNOT();
		
		else 
			throw new ElementoNonTrovatoException();
	}
	
	public static PortaLogicaFactory getSingleton() {
		if(instance == null)
			instance = new PortaLogicaFactory();
		
		return instance;
	}
}
