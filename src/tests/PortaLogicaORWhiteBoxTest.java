package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import porte.PortaLogicaOR;
import segnali.Segnale;

class PortaLogicaORWhiteBoxTest {
	private PortaLogicaOR orGate = new PortaLogicaOR();
	
	boolean testCommon(boolean Aval, boolean Bval) {
		Segnale A = new Segnale(Aval);
		Segnale B = new Segnale(Bval);
		Segnale RES = new Segnale();
		
		orGate.getInputSlot(0).collegaSegnale(A);
		orGate.getInputSlot(1).collegaSegnale(B);
		orGate.getOutputSlot(0).collegaSegnale(RES);
		
		orGate.esegui();
		
		return orGate.getOutputSlot(0).getSegnaleCollegato().getStato();
	}
	
	@Test
	void testHH() {
		assertEquals(true, testCommon(true, true));
	}

	@Test
	void testLL() {
		assertEquals(false, testCommon(false, false));
	}
}
