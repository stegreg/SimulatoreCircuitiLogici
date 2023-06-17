package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Eccezioni.EventoNonValidoException;
import Eccezioni.IstanteNonValidoException;
import eventi.TipoEvento;
import main.Timeline;
import segnali.Segnale;

class TimelineBlackBoxTest {

	/**
	 * 			TEST aggiungiEvento()
	 * 
	 * Classi valide:
	 * 1. sigRef non nullo; 	2. istante >= 0
	 * Classi non valide:
	 * 3. sigRef nullo			4. istante < 0
	 * 
	 * Casi di test:
	 * C1) sigRef non nullo 	istante = 4
	 * C2) sigRef nullo			istante = 6
	 * C3) sigRef non nullo		istante = -8
	 * 
	 * */
	
	@Test
	void testAggiungiEventoC1() {		
		Timeline timeline = new Timeline();
		Segnale sig = new Segnale();
		
		boolean eccezioneGenerata = false;
		
		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 4);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertFalse(eccezioneGenerata);
		assertEquals(1, timeline.getNumEventi());
		
		while(timeline.getTempoCorrente() < 10) {
			timeline.avanza();

			if(timeline.getTempoCorrente() == 4)
				assertEquals(true, sig.getStato());
		}
		
	}
	
	@Test
	void testAggiungiEventoC2() {		
		Timeline timeline = new Timeline();
		Segnale sig = null;

		boolean eccezioneGenerata = false;
		
		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 6);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			eccezioneGenerata = true;
		}
		assertTrue(eccezioneGenerata);
		assertEquals(0, timeline.getNumEventi());
	}
	
	@Test
	void testAggiungiEventoC3() {		
		Timeline timeline = new Timeline();
		Segnale sig = new Segnale();

		boolean eccezioneGenerata = false;
		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, -8);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertTrue(eccezioneGenerata);
		assertEquals(0, timeline.getNumEventi());
	}
	
	
	/**
	 * 			TEST rimuoviEvento()
	 * 
	 * Classi valide:
	 * 1. sigRef non nullo		2. istante >= 0
	 * Classi non valide:
	 * 3. sigRef nullo			4. istante < 0
	 * 
	 * C1) sigRef non nullo		istante = 5
	 * C2) sigRef nullo			istante = 3
	 * C3) sigRef non nullo		istante = -4 
	 * */
	@Test
	void testRimuoviEventoC1() {		
		Timeline timeline = new Timeline();
		Segnale sig = new Segnale();

		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 5);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
		}
		boolean eccezioneGenerata = false;

		try {
			timeline.rimuoviEvento(sig, 5);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertFalse(eccezioneGenerata);
		assertEquals(0, timeline.getNumEventi());
	}
	@Test
	void testRimuoviEventoC2() {		
		Timeline timeline = new Timeline();
		Segnale sig = new Segnale();

		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 3);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
		}
		boolean eccezioneGenerata = false;

		try {
			timeline.rimuoviEvento(null, 3);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertTrue(eccezioneGenerata);
		assertEquals(1, timeline.getNumEventi());
	}
	@Test
	void testRimuoviEventoC3() {		
		Timeline timeline = new Timeline();
		Segnale sig = new Segnale();

		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 5);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
		}
		boolean eccezioneGenerata = false;

		try {
			timeline.rimuoviEvento(null, -4);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertTrue(eccezioneGenerata);
		assertEquals(1, timeline.getNumEventi());
	}
	
	/**
	 * 			TEST rimuoviEventiAssociati()
	 * Classi valide:
	 * 1. sigRef non nullo
	 * Classi non valide:
	 * 2. sigRef nullo
	 * 
	 * */	
	@Test
	void testRimuoviEventiAssociatiC1() {
		Timeline timeline = new Timeline();
		Segnale sig = new Segnale();

		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 5);
			timeline.aggiungiEvento(TipoEvento.EVT_FALL, sig, 8);
			timeline.aggiungiEvento(TipoEvento.EVT_FALL, sig, 7);
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 4);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
		}
		
		timeline.rimuoviEventiAssociati(sig);
		
		assertEquals(0, timeline.getNumEventi());
	}
	@Test
	void testRimuoviEventiAssociatiC2() {
		Timeline timeline = new Timeline();
		Segnale sig = new Segnale();

		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 5);
			timeline.aggiungiEvento(TipoEvento.EVT_FALL, sig, 8);
			timeline.aggiungiEvento(TipoEvento.EVT_FALL, sig, 7);
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 4);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
		}
		
		boolean eccezioneGenerata = false;
		try {
			timeline.rimuoviEventiAssociati(null);
		}catch(NullPointerException e) {
			eccezioneGenerata = true;
		}
		
		assertTrue(eccezioneGenerata);
		assertEquals(4, timeline.getNumEventi());
	}
	
	
	/**
	 * 		TEST avanza()
	 * */
	@Test
	void testAvanzaC1() {
		Timeline timeline = new Timeline();
		Segnale sig = new Segnale();

		try {
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 2);
			timeline.aggiungiEvento(TipoEvento.EVT_FALL, sig, 4);
			timeline.aggiungiEvento(TipoEvento.EVT_RAISE, sig, 6);
			timeline.aggiungiEvento(TipoEvento.EVT_FALL, sig, 8);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
		}
		
		while(timeline.getTempoCorrente() < 10) {
			timeline.avanza();
			
			if(timeline.getTempoCorrente() == 2 || timeline.getTempoCorrente() == 3)
				assertEquals(true, sig.getStato());
			if(timeline.getTempoCorrente() == 4 || timeline.getTempoCorrente() == 5)
				assertEquals(false, sig.getStato());
			if(timeline.getTempoCorrente() == 6 || timeline.getTempoCorrente() == 7)
				assertEquals(true, sig.getStato());
			if(timeline.getTempoCorrente() == 8 || timeline.getTempoCorrente() == 9)
				assertEquals(false, sig.getStato());
		}
	}
}
