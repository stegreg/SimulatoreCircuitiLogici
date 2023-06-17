package segnali;

import java.util.Map;

import Eccezioni.IstanteNonValidoException;
import Eccezioni.TracciaNonTrovataException;

import java.util.HashMap;

public class RegistratoreSegnale {
	private Map<String, Map<Integer, Boolean>> tracce = new HashMap<String, Map<Integer, Boolean>>();
	
	public void creaTraccia(String nome) {
		tracce.put(nome, new HashMap<Integer, Boolean>());
	}
	
	public void eliminaTraccia(String nomeTraccia)
			throws TracciaNonTrovataException{
		if(!tracce.containsKey(nomeTraccia)) throw new TracciaNonTrovataException();
		
		tracce.remove(nomeTraccia);
	}
	
	public void pulisci() {
		tracce.clear();
	}
	
	public void pulisciTracce() {
		for(Map.Entry<String, Map<Integer, Boolean>> entry : tracce.entrySet()) {
			entry.getValue().clear();
		}
	}
	
	public void registra(String nomeTraccia, Segnale sigRef, int istante) 
			throws NullPointerException, IstanteNonValidoException, TracciaNonTrovataException {	
		
		if(istante < 0) 						throw new IstanteNonValidoException();
		if(sigRef == null) 						throw new NullPointerException();
		if(!tracce.containsKey(nomeTraccia)) 	throw new TracciaNonTrovataException();
		
		tracce.get(nomeTraccia).put(istante, sigRef.getStato());
	}
	
	public Map<Integer, Boolean> getTraccia(String nomeTraccia) 
			throws TracciaNonTrovataException{
		if(!tracce.containsKey(nomeTraccia)) throw new TracciaNonTrovataException("Traccia '" + nomeTraccia + "' non esiste.");
		
		return tracce.get(nomeTraccia);
	}
}
