package main;

import java.util.ArrayList;
import java.util.List;

import Eccezioni.EventoNonValidoException;
import Eccezioni.IstanteNonValidoException;
import eventi.Evento;
import eventi.EventoFactory;
import eventi.TipoEvento;
import segnali.Segnale;

public class Timeline {
	private long 			istanteCorrente = -1;
	private List<Evento> 	eventi 			= new ArrayList<Evento>();
	
	public Timeline() {
	}
	
	public void aggiungiEvento(TipoEvento tipo, Segnale sigRef, long istante) 
			throws IstanteNonValidoException, EventoNonValidoException{
		if(istante < 0) 	throw new IstanteNonValidoException("Istante '" + istante + "' non valido.");
		if(sigRef == null) 	throw new EventoNonValidoException("Segnale non esistente.");
		
		//Controlla se già esiste un evento per il segnale nell'istante specificato
		//se già esiste, rimuovilo
		rimuoviEvento(sigRef, istante);
		
		Evento evt = EventoFactory.getSingleton().creaEvento(tipo, sigRef, istante);
		
		if(evt == null) throw new EventoNonValidoException("Tipo di evento non valido.");
		
		eventi.add(evt);
	}
	
	public void rimuoviEvento(Segnale sigRef, long istante) 
			throws IstanteNonValidoException, EventoNonValidoException {
		if(istante < 0) 	throw new IstanteNonValidoException("Istante '" + istante + "' non valido");
		if(sigRef == null) 	throw new EventoNonValidoException("Segnale non esistente.");

		for(int e = 0; e < eventi.size(); e++) {
			if(eventi.get(e).getSegnale() == sigRef && eventi.get(e).getIstante() == istante) {
				eventi.remove(e);
				
				return;
			}
		}
	}
	
	public void pulisci() {
		eventi.clear();
	}
	
	/**
	 * Rimuove tutti gli eventi associati ad uno specifico segnale
	 * */
	public void rimuoviEventiAssociati(Segnale sig) {
		if(sig == null) throw new NullPointerException();
		
		List<Integer> eventiDaEliminare = new ArrayList<Integer>();
		
		for(int e = 0; e < eventi.size(); e++) {
			if(eventi.get(e).getSegnale() == sig)
				eventiDaEliminare.add(e);
		}
		
		for(int i = 0; i < eventiDaEliminare.size(); i++) {
			eventi.remove(eventiDaEliminare.get(i).intValue() - i);
		}
	}
	
	public int getNumEventi() {
		return eventi.size();
	}
	
	public long getTempoCorrente() {
		return istanteCorrente;
	}
	
	public void reset() {
		istanteCorrente = -1;
	}
	
	public void avanza() {
		//Avanza il tempo di una unità
		istanteCorrente++;
				
		//Scorri tutti gli eventi e controlla se ce n'è qualcuno da eseguire
		for(int e = 0; e < eventi.size(); e++) {
			if(eventi.get(e).getIstante() == istanteCorrente)
				eventi.get(e).eseguiAzione();
		}
	}
	
}
