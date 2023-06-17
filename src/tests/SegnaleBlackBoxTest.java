package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import porte.PortaLogicaNOT;
import segnali.Segnale;

class SegnaleBlackBoxTest {

	/*
	 * Classi di equivalenza valide:
	 * 1. nuovoStato = true 	2. nuovoStato = false
	 * 
	 * Casi di test:
	 * C1) nuovoStato = true 	copre classe 1.
	 * C2) nuovoStato = false 	copre classe 2.
	 * */
	
	@Test
	void testC1() {
		PortaLogicaNOT notGate = new PortaLogicaNOT();
		Segnale A = new Segnale();
		Segnale O = new Segnale();
		
		notGate.getInputSlot(0).collegaSegnale(A);
		A.addToSensitivity(notGate);
		notGate.getOutputSlot(0).collegaSegnale(O);
		notGate.esegui();  //porta il gate ad uno stato iniziale
		
		A.setStato(true);		
		assertEquals(true, A.getStato());

		//Controlla che l'uscita del NOT si sia aggiornata correttamente
		assertEquals(false, O.getStato());
	}

	@Test
	void testC2() {
		PortaLogicaNOT notGate = new PortaLogicaNOT();
		Segnale A = new Segnale();
		Segnale O = new Segnale();
		
		notGate.getInputSlot(0).collegaSegnale(A);
		A.addToSensitivity(notGate);
		notGate.getOutputSlot(0).collegaSegnale(O);
		notGate.esegui(); //porta il gate ad uno stato iniziale
		
		A.setStato(false);		
		assertEquals(false, A.getStato());

		//Controlla che l'uscita del NOT si sia aggiornata correttamente
		assertEquals(true, O.getStato());
		
	}
}
