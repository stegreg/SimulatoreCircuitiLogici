package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import porte.PortaLogicaAND;
import segnali.Segnale;

class PortaLogicaANDWhiteBoxTest {
	private PortaLogicaAND andGate = new PortaLogicaAND();
	
	boolean testCommon(boolean Aval, boolean Bval) {
		Segnale A = new Segnale(Aval);
		Segnale B = new Segnale(Bval);
		Segnale RES = new Segnale(false);
		
		andGate.getInputSlot(0).collegaSegnale(A);
		andGate.getInputSlot(1).collegaSegnale(B);
		andGate.getOutputSlot(0).collegaSegnale(RES);
		
		andGate.esegui();
		
		return andGate.getOutputSlot(0).getSegnaleCollegato().getStato();
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