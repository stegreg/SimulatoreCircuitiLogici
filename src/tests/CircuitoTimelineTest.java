package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Eccezioni.ElementoEsistenteException;
import Eccezioni.ElementoNonTrovatoException;
import Eccezioni.EventoNonValidoException;
import Eccezioni.IstanteNonValidoException;
import Eccezioni.SlotNonValidoException;
import Eccezioni.TracciaNonTrovataException;
import Eccezioni.ValidazioneNonRiuscitaException;
import eventi.TipoEvento;
import main.Circuito;
import main.Timeline;
import porte.PortaLogicaAND;
import porte.PortaLogicaNOT;
import porte.PortaLogicaOR;
import porte.TipoPorta;

/*
 * Test di sottositema: 
 * integra Circuito (il quale a sua volta integra Segnale e PortaLogica) e Timeline
 * */
class CircuitoTimelineTest {

	@Test
	void testCircuitoB() {
		Timeline timeline = new Timeline();
		Circuito cir = new Circuito(timeline);
		
		try {
			cir.addSegnale("A", false);
			cir.addSegnale("B", false);
			cir.addSegnale("C", false);
			cir.addSegnale("O1", false);
			cir.addSegnale("O2", false);
			cir.addSegnale("O3", false);
			cir.addSegnale("O4", false);
			
			cir.addPorta("And1", TipoPorta.PORTA_AND);
			cir.addPorta("And2", TipoPorta.PORTA_AND);
			cir.addPorta("Not", TipoPorta.PORTA_NOT);
			cir.addPorta("Or", TipoPorta.PORTA_OR);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e2) {
			e2.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "And1", 0);

			cir.collegaInput("B", "And1", 1);
			cir.collegaOutput("O2", "And1", 0);
			
			cir.collegaInput("O1", "And2", 0);
			cir.collegaInput("C", "And2", 1);
			cir.collegaOutput("O3", "And2", 0);

			cir.collegaInput("O2", "Or", 0);
			cir.collegaInput("O3", "Or", 1);
			cir.collegaOutput("O4", "Or", 0);

			cir.collegaInput("B", "Not", 0);
			cir.collegaOutput("O1", "Not", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e1) {
			e1.printStackTrace();
		}
		
		try {
			cir.aggiungiEvento(TipoEvento.EVT_RAISE, "A", 5);
			cir.aggiungiEvento(TipoEvento.EVT_RAISE, "B", 5);
			cir.aggiungiEvento(TipoEvento.EVT_FALL, "A", 6);
		} catch (IstanteNonValidoException | EventoNonValidoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.simula(10);
		} catch (IstanteNonValidoException | ValidazioneNonRiuscitaException e) {
			e.printStackTrace();
		}
		
		try {
			assertEquals(true, cir.getTracciaSegnale("O4").get(5));
			assertEquals(false, cir.getTracciaSegnale("O4").get(6));
		} catch (TracciaNonTrovataException e) {
			e.printStackTrace();
		}
		
	}

}
