package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Eccezioni.ElementoEsistenteException;
import Eccezioni.ElementoNonTrovatoException;
import Eccezioni.SlotNonValidoException;
import main.Circuito;
import main.Timeline;
import porte.TipoPorta;
import segnali.Segnale;

class CircuitoWhiteBoxTest {

	/*
	 * boolean segnaleAncoraCollegato = false;
		for(int i = 0; i < porte.get(nomePorta).getNumInputSlots(); i++)
			if(i != slotIndex && porte.get(nomePorta).getInputSlot(i).getSegnaleCollegato() == segnaleDaScollegare)
				segnaleAncoraCollegato = true;
		
		if(!segnaleAncoraCollegato)
			segnaleDaScollegare.removeFromSensitivity(porte.get(nomePorta));
	 * */
	@Test
	void testScollegaInputC1() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addPorta("AndGate", TipoPorta.PORTA_AND);
			cir.addSegnale("A", false);
			cir.addSegnale("B", false);
			cir.collegaInput("A", "AndGate", 0);
			cir.collegaInput("B", "AndGate", 1);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			/*
			 * Il ciclo for viene eseguito due volte: vengono coperte le condizioni dell'if, ma non
			 * le decisioni (ovvero l'if viene sempre valutato a false)
			 * La funzione testScollegaInputC2() valuterà l'if a true
			 * */
			cir.scollegaInput("AndGate", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		assertEquals(null, cir.getListaPorte().get("AndGate").getInputSlot(0).getSegnaleCollegato());
		assertFalse(eccezioneGenerata);
	}
	@Test
	void testScollegaInputC2() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addPorta("AndGate", TipoPorta.PORTA_AND);
			cir.addSegnale("A", false);
			
			//Collega A agli slot I0 e I1
			cir.collegaInput("A", "AndGate", 0);
			cir.collegaInput("A", "AndGate", 1);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.scollegaInput("AndGate", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertFalse(eccezioneGenerata);
	}
	
	
	/*
	 * for(int i = 0; i < portaDaEliminare.getNumInputSlots(); i++) {
			//Prendi il segnale collegato allo slot i-esimo
			Segnale segnaleCollegato = portaDaEliminare.getInputSlot(i).getSegnaleCollegato();
			
			//Se allo slot è collegato un segnale...
			if(segnaleCollegato != null)
				//...rimuovi la porta dalla sensitivity list di tale segnale
				segnaleCollegato.removeFromSensitivity(portaDaEliminare);
		}
	 * */
	@Test
	void testRimuoviPorta() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addPorta("AndGate", TipoPorta.PORTA_AND);
			cir.addSegnale("A", false);
			
			//Collega A al primo slot; al secondo non collegare niente
			cir.collegaInput("A", "AndGate", 0);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}

		boolean eccezioneGenerata = false;
		try {
			/*
			 * Il for viene eseguito due volte. Copertura delle condizioni e delle decisioni:
			 * La prima volta la condizione nell'if è vera e la decisione "true" viene presa.
			 * La seconda volta la condizione nell'if è false e la decisione "false" viene presa.
			 * */
			cir.rimuoviPorta("AndGate");
		} catch (ElementoNonTrovatoException e) {
			eccezioneGenerata = true;
		}
		
		assertFalse(eccezioneGenerata);
		assertEquals(0, cir.getListaPorte().size());
		
	}
}
