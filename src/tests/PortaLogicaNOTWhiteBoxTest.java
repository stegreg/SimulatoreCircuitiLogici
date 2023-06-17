package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import porte.PortaLogicaNOT;
import segnali.Segnale;

class PortaLogicaNOTWhiteBoxTest {
	private PortaLogicaNOT notGate = new PortaLogicaNOT();
		
	boolean testCommon(boolean Aval) {
		Segnale A = new Segnale(Aval);
		Segnale RES = new Segnale();
		
		notGate.getInputSlot(0).collegaSegnale(A);
		notGate.getOutputSlot(0).collegaSegnale(RES);
		
		notGate.esegui();
		
		return notGate.getOutputSlot(0).getSegnaleCollegato().getStato();
	}
	
	@Test
	void testH() {
		assertEquals(true, testCommon(false));
	}
	
	@Test
	void testL() {
		assertEquals(false, testCommon(true));
	}
}
