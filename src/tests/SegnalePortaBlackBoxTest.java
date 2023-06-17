package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Eccezioni.ElementoEsistenteException;
import Eccezioni.ElementoNonTrovatoException;
import Eccezioni.IstanteNonValidoException;
import Eccezioni.SlotNonValidoException;
import Eccezioni.TracciaNonTrovataException;
import Eccezioni.ValidazioneNonRiuscitaException;
import main.Circuito;
import main.Timeline;
import porte.TipoPorta;

/**
 * Test di sottosistema: 
 * integra le componenti Segnale e PortaLogica.
 * */
class SegnalePortaBlackBoxTest {

	/**
	 * Simula una semplice porta AND
	 * */
	boolean simulaAND(boolean a, boolean b) {
		Timeline timeline = new Timeline();
		Circuito c = new Circuito(timeline);
		
		try {
			c.addSegnale("A", a);

			c.addSegnale("B", b);
			c.addSegnale("RES", false);
			
			c.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e3) {
			e3.printStackTrace();
		}
		
		try {
			c.collegaInput("A", "MyAnd", 0);
			c.collegaInput("B", "MyAnd", 1);
			c.collegaOutput("RES", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e2) {
			e2.printStackTrace();
		}
		
		try {
			c.simula(15);
		} catch (IstanteNonValidoException | ValidazioneNonRiuscitaException e1) {
			e1.printStackTrace();
		}
		
		boolean res = false;
		try {
			res = c.getTracciaSegnale("RES").get(0);
		} catch (TracciaNonTrovataException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	/**
	 * Simula il circuito ottenuto dall'espressione:
	 * 		o2 = a * b * c
	 * */
	boolean simulaCircuitoA(boolean a, boolean b, boolean c) {	
		Timeline timeline = new Timeline();
		Circuito cir = new Circuito(timeline);
		
		try {
			cir.addSegnale("A", a);

			cir.addSegnale("B", b);
			cir.addSegnale("C", c);
			cir.addSegnale("O1", false);
			cir.addSegnale("O2", false);
			
			cir.addPorta("And1", TipoPorta.PORTA_AND);
			cir.addPorta("And2", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e3) {
			e3.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "And1", 0);

			cir.collegaInput("B", "And1", 1);
			cir.collegaOutput("O1", "And1", 0);
			
			cir.collegaInput("O1", "And2", 0);
			cir.collegaInput("C", "And2", 1);
			cir.collegaOutput("O2", "And2", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e2) {
			e2.printStackTrace();
		}

		try {
			cir.simula(15);
		} catch (IstanteNonValidoException | ValidazioneNonRiuscitaException e1) {
			e1.printStackTrace();
		}
		
		boolean res = false;
		try {
			res = cir.getTracciaSegnale("O2").get(0);
		} catch (TracciaNonTrovataException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	/**
	 * Simula il circuito ottenuto dall'espressione:
	 * 		o4 = a * b + c * not(b)
	 * */
	boolean simulaCircuitoB(boolean a, boolean b, boolean c) {
		Timeline timeline = new Timeline();
		Circuito cir = new Circuito(timeline);
		
		try {
			cir.addSegnale("A", a);

			cir.addSegnale("B", b);
			cir.addSegnale("C", c);
			cir.addSegnale("O1", false);
			cir.addSegnale("O2", false);
			cir.addSegnale("O3", false);
			cir.addSegnale("O4", false);
			
			cir.addPorta("And1", TipoPorta.PORTA_AND);
			cir.addPorta("And2", TipoPorta.PORTA_AND);
			cir.addPorta("Not", TipoPorta.PORTA_NOT);
			cir.addPorta("Or", TipoPorta.PORTA_OR);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e3) {
			e3.printStackTrace();
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
		} catch (ElementoNonTrovatoException | SlotNonValidoException e2) {
			e2.printStackTrace();
		}
		
		try {
			cir.simula(15);
		} catch (IstanteNonValidoException | ValidazioneNonRiuscitaException e1) {
			e1.printStackTrace();
		}
		
		boolean res = false;
		try {
			res = cir.getTracciaSegnale("O4").get(0);
		} catch (TracciaNonTrovataException e) {
			e.printStackTrace();
		}

		return res;
	}
	
	@Test
	void testAND() {
		assertEquals(true, simulaAND(true, true));
		assertEquals(false, simulaAND(false, true));
		assertEquals(false, simulaAND(false, false));
		assertEquals(false, simulaAND(true, false));
	}
	
	@Test
	void testCircuitoA() {
		assertEquals(false, simulaCircuitoA(false, false, false));
		assertEquals(false, simulaCircuitoA(false, false, true));
		assertEquals(false, simulaCircuitoA(false, true, false));
		assertEquals(false, simulaCircuitoA(false, true, true));
		assertEquals(false, simulaCircuitoA(true, false, false));
		assertEquals(false, simulaCircuitoA(true, false, true));
		assertEquals(false, simulaCircuitoA(true, true, false));
		assertEquals(true, simulaCircuitoA(true, true, true));
	}
	
	@Test
	void testCircuitoB() {
		assertEquals(false, simulaCircuitoB(false, false, false));
		assertEquals(true, simulaCircuitoB(false, false, true));
		assertEquals(false, simulaCircuitoB(false, true, false));
		assertEquals(false, simulaCircuitoB(false, true, true));
		assertEquals(false, simulaCircuitoB(true, false, false));
		assertEquals(true, simulaCircuitoB(true, false, true));
		assertEquals(true, simulaCircuitoB(true, true, false));
		assertEquals(true, simulaCircuitoB(true, true, true));
	}
}
