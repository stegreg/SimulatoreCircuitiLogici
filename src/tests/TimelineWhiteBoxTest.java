package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Eccezioni.EventoNonValidoException;
import Eccezioni.IstanteNonValidoException;
import eventi.TipoEvento;
import main.Circuito;
import main.Timeline;
import segnali.Segnale;

class TimelineWhiteBoxTest {	
	/*
	 * 	for(int e = 0; e < eventi.size(); e++) {
			if(eventi.get(e).getSegnale() == sigRef && eventi.get(e).getIstante() == istante) {
				eventi.remove(e);
				
				return;
			}
		}
	 * */
	@Test
	void testRimuoviEvento() {
		Timeline timeline = new Timeline();
		
		Segnale sig1 = new Segnale();
		Segnale sig2 = new Segnale();
		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig1, 5);
			timeline.aggiungiEvento(TipoEvento.EVT_FALL, sig2, 7);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			/*
			 * Il ciclo for viene eseguito due volte: nella prima entrambe le condizioni sono false,
			 * e la decisione false viene presa; nella seconda entrambe le condizioni sono true,
			 * e la decisione true viene presa.
			 * */
			timeline.rimuoviEvento(sig2, 7);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertFalse(eccezioneGenerata);
		assertEquals(1, timeline.getNumEventi());
	}
	
	/*
	 * 	for(int e = 0; e < eventi.size(); e++) {
			if(eventi.get(e).getSegnale() == sig)
				eventiDaEliminare.add(e);
		}
		
		for(int i = 0; i < eventiDaEliminare.size(); i++) {
			eventi.remove(eventiDaEliminare.get(i).intValue() - i);
		}
	 * */
	@Test
	void testRimuoviEventiAssociati() {
		Timeline timeline = new Timeline();
		
		Segnale sig1 = new Segnale();
		Segnale sig2 = new Segnale();
		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig1, 5);
			timeline.aggiungiEvento(TipoEvento.EVT_FALL, sig2, 7);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			/*
			 * Il ciclo for viene eseguito due volte: nella prima la condizione è false,
			 * e la decisione false viene presa; nella seconda la condizione è true,
			 * e la decisione true viene presa.
			 * */
			timeline.rimuoviEventiAssociati(sig2);
		} catch (NullPointerException e) {
			eccezioneGenerata = true;
		}
		timeline.rimuoviEventiAssociati(sig2);		

		assertFalse(eccezioneGenerata);
		assertEquals(1, timeline.getNumEventi());
	}
	
	/*
	 * 	for(int e = 0; e < eventi.size(); e++) {
			if(eventi.get(e).getIstante() == istanteCorrente)
				eventi.get(e).eseguiAzione();
		}
	 * */
	@Test
	void testAvanza() {
		Timeline timeline = new Timeline();
		
		Segnale sig = new Segnale();
		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 0);
			timeline.aggiungiEvento(TipoEvento.EVT_FALL, sig, 5);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			e.printStackTrace();
		}
		
		/*
		 * Nella prima esecizione del ciclo for la condizione dell'if è true, e la decisione viene presa;
		 * Nella seconda esecuzione la condizione dell'if è false, e la decisione non viene presa
		 * */
		timeline.avanza();	
		
		assertEquals(true, sig.getStato());
	}
}
