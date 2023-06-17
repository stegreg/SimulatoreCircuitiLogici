package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Eccezioni.IstanteNonValidoException;
import Eccezioni.TracciaNonTrovataException;
import segnali.RegistratoreSegnale;
import segnali.Segnale;

class RegistratoreSegnaleBlackBoxTest {
	private RegistratoreSegnale recorder = new RegistratoreSegnale();
	private Segnale sig = new Segnale();
	
	
	/*
	 * 					registra()
	 * 
	 * Classi valide:
	 * 	1: nomeTraccia che esiste		2: sigRef non nullo		3: istante >= 0
	 * Classi non valide:
	 * 	4: nomeTraccia non esiste		5: sigRef nullo 		6: istante < 0
	 * 
	 * C1) "Traccia1"				sig		5		: classi 1, 2, 3
	 * C2) "Traccia1"				sig 	-8		: classe 6
	 * C3) "Traccia1"				null 	9		: classe 5
	 * C4) "TracciaNonDefinita"		sig 	10		: classe 4
	 * 
	 * */
	
	@Test
	void testRegistraC1() {
		sig.setStato(true);
		
		recorder.creaTraccia("Traccia1");
		
		try {
			recorder.registra("Traccia1", sig, 5);
		} catch (NullPointerException | IstanteNonValidoException | TracciaNonTrovataException e) {
			e.printStackTrace();
		}
		
		boolean stato = false;
		try {
			stato = recorder.getTraccia("Traccia1").get(5);
		} catch (TracciaNonTrovataException e) {
			assert(false);
		}
		
		assertEquals(true, stato);		
	}
	
	@Test
	void testRegistraC2() {
		
		recorder.creaTraccia("Traccia1");
		try{
			recorder.registra("Traccia1", sig, -8);
		}catch(NullPointerException | IstanteNonValidoException | TracciaNonTrovataException e) {
			assert(true);
			
			return;
		}
		
		assert(false);
	}
	
	@Test
	void testRegistraC3() {
		
		recorder.creaTraccia("Traccia1");
		try{
			recorder.registra("Traccia1", null, 9);
		}catch(NullPointerException | IstanteNonValidoException | TracciaNonTrovataException e) {
			assert(true);
			
			return;
		}
		
		assert(false);
	}
	
	@Test
	void testRegistraC4() {
		
		recorder.creaTraccia("Traccia1");
		try{
			recorder.registra("TracciaNonDefinita", sig, 10);
		}catch(NullPointerException | IstanteNonValidoException | TracciaNonTrovataException e) {
			assert(true);
			
			return;
		}
		
		assert(false);
	}
	
	/*
	 * 					eliminaTraccia()
	 * 
	 * Classi valide:
	 * 	1: nomeTraccia che esiste
	 * Classi non valide:
	 * 	2: nomeTraccia non esiste
	 * 
	 * C1) "Traccia1"
	 * C2) "TracciaCheNonEsiste"
	 * 
	 * */
	
	@Test
	void testEliminaTracciaC1() {
		recorder.creaTraccia("Traccia1");
		
		try {
			recorder.eliminaTraccia("Traccia1");
		} catch (TracciaNonTrovataException e) {
			assert(false);
		}
		
		assert(true);
	}
	@Test
	void testEliminaTracciaC2() {
		recorder.creaTraccia("Traccia1");
		
		try {
			recorder.eliminaTraccia("TracciaCheNonEsiste");
		} catch (TracciaNonTrovataException e) {
			assert(true);
			
			return;
		}
		
		assert(false);
	}
}
