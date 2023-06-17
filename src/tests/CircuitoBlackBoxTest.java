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

/*
 * Test a scatola chiusa per Circuito.
 * */

class CircuitoBlackBoxTest {

	/**
	 * 			TEST addPorta()
	 * 
	 * Classi valide:
	 * 1. nomePorta non esistente	2. tipoPorta != null
	 * 
	 * Classi non valide:
	 * 3. nomePorta esistente		4. tipoPorta == null
	 * 
	 * Casi di Test:
	 * C1) nomePorta = "PortaNonEsistente"	tipoPorta != null
	 * C2) nomePorta = "PortaEsistente"		tipoPorta != null
	 * C3) nomePorta = "PortaNonEsistente"	tipoPorta == null
	 * */
	@Test
	void testAddPortaC1() {
		Circuito cir = new Circuito(new Timeline());
		
		boolean eccezioneGenerata = false;
		try {
			cir.addPorta("PortaNonEsistente", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			eccezioneGenerata = true;
		}
		//Mi assicuro che non abbia generato l'eccezione
		assertFalse(eccezioneGenerata);	
		
		//Ci deve essere soltanto una porta, di nome "PortaNonEsistente"
		assertEquals(1, cir.getListaPorte().size());
		assertEquals(true, cir.getListaPorte().keySet().contains("PortaNonEsistente"));			
	}
	
	@Test
	void testAddPortaC2() {
		Circuito cir = new Circuito(new Timeline());
		
		boolean eccezioneGenerata = false;
		try {
			cir.addPorta("PortaEsistente", TipoPorta.PORTA_AND);
			cir.addPorta("PortaEsistente", TipoPorta.PORTA_OR);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			eccezioneGenerata = true;
		}
		
		//Mi assicuro che abbia generato l'eccezione
		assertTrue(eccezioneGenerata);	
		
		//Deve aver creato solo una porta di nome "PortaEsistente"
		assertEquals(1, cir.getListaPorte().size());	
		assertEquals(true, cir.getListaPorte().keySet().contains("PortaEsistente"));			
	}
	
	@Test
	void testAddPortaC3() {
		Circuito cir = new Circuito(new Timeline());
		
		boolean eccezioneGenerata = false;
		try {
			cir.addPorta("PortaNonEsistente", null);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			eccezioneGenerata = true;
		}
		
		//Mi assicuro che abbia generato l'eccezione
		assertTrue(eccezioneGenerata);	
		
		//Non deve aver creato alcuna porta logica
		assertEquals(0, cir.getListaPorte().size());			
	}
	/**
	 * 			TEST addSegnale()
	 * 
	 * Classi valide:
	 * 1. nomeSegnale non esistente		2. statoIniziale = qualsiasi
	 * 
	 * Classi non valide:
	 * 3. nomeSegnale esistente
	 * 
	 * Casi di Test:
	 * C1) nomeSegnale = "SegnaleNonEsistente"	statoIniziale = true
	 * C2) nomeSegnale = "SegnaleEsistente"		statoIniziale = false
	 * */
	@Test
	void testAddSegnaleC1() {
		Circuito cir = new Circuito(new Timeline());
		
		boolean eccezioneGenerata = false;
		try {
			cir.addSegnale("SegnaleNonEsistente", true);
		} catch (ElementoEsistenteException e) {
			eccezioneGenerata = true;
		}
		
		//Mi assicuro che non abbia generato l'eccezione
		assertFalse(eccezioneGenerata);	
		
		//Ci deve essere soltanto una segnale, di nome "SegnaleNonEsistente"
		assertEquals(1, cir.getListaSegnali().size());
		assertEquals(true, cir.getListaSegnali().keySet().contains("SegnaleNonEsistente"));			
	}
	
	@Test
	void testAddSegnaleC2() {
		Circuito cir = new Circuito(new Timeline());
		
		boolean eccezioneGenerata = false;
		try {
			cir.addSegnale("SegnaleEsistente", true);
			cir.addSegnale("SegnaleEsistente", false);
		} catch (ElementoEsistenteException e) {
			eccezioneGenerata = true;
		}
		
		//Mi assicuro che abbia generato l'eccezione
		assertTrue(eccezioneGenerata);	
		
		//Deve aver creato solo una porta di nome "SegnaleEsistente"
		assertEquals(1, cir.getListaSegnali().size());	
		assertEquals(true, cir.getListaSegnali().keySet().contains("SegnaleEsistente"));			
	}
	
	/**
	 * 			TEST collegaInput()
	 * 
	 * Classi valide:
	 * 1. Nome segnale esistente		2. Nome porta esistente			3. slotIndex nell'intervallo [0, numInputSlots)
	 * 
	 * Classi non valide:
	 * 4. Nome segnale non esistente	5. Nome porta non esistente		6. slotIndex fuori dall'intervallo [0, numInputSlots)
	 * 
	 * Casi di Test:
	 * C1) 	nomeSegnale = "SegnaleEsistente"	nomePorta = "PortaEsistente"	slotIndex = 0 
	 * C2)	nomeSegnale = "SegnaleNonEsistente"	nomePorta = "PortaEsistente"	slotIndex = 0
	 * C3)	nomeSegnale = "SegnaleEsistente"	nomePorta = "PortaNonEsistente"	slotIndex = 0
	 * C4)	nomeSegnale = "SegnaleEsistente"	nomePorta = "PortaEsistente"	slotIndex = -8
	 * */
	
	@Test
	void testCollegaInputC1() {
		Circuito cir = new Circuito(new Timeline());
		try {
			cir.addSegnale("SegnaleEsistente", false);
			cir.addPorta("PortaEsistente", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.collegaInput("SegnaleEsistente", "PortaEsistente", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		//L'eccezione non deve essere generata
		assertFalse(eccezioneGenerata);
		
		//"SegnaleEsistente" deve essere collegato allo slot I0 di "PortaEsistente"
		assertEquals(
				cir.getListaSegnali().get("SegnaleEsistente"),
				cir.getListaPorte().get("PortaEsistente").getInputSlot(0).getSegnaleCollegato());
	}
	
	@Test
	void testCollegaInputC2() {
		Circuito cir = new Circuito(new Timeline());
		try {
			cir.addSegnale("SegnaleEsistente", false);
			cir.addPorta("PortaEsistente", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.collegaInput("SegnaleNonEsistente", "PortaEsistente", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		//L'eccezione deve essere generata
		assertTrue(eccezioneGenerata);
		
		//Lo slot I0 di "PortaEsistente" non deve essere collegato ad alcun segnale
		assertEquals(null, cir.getListaPorte().get("PortaEsistente").getInputSlot(0).getSegnaleCollegato());
	}
	
	@Test
	void testCollegaInputC3() {
		Circuito cir = new Circuito(new Timeline());
		try {
			cir.addSegnale("SegnaleEsistente", false);
			cir.addPorta("PortaEsistente", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.collegaInput("SegnaleEsistente", "PortaNonEsistente", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		//L'eccezione deve essere generata
		assertTrue(eccezioneGenerata);
	}
	
	@Test
	void testCollegaInputC4() {
		Circuito cir = new Circuito(new Timeline());
		try {
			cir.addSegnale("SegnaleEsistente", false);
			cir.addPorta("PortaEsistente", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.collegaInput("SegnaleEsistente", "PortaEsistente", -8);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		//L'eccezione deve essere generata
		assertTrue(eccezioneGenerata);
	}
	
	/**
	 * 			TEST collegaOutput()
	 * 
	 * Classi valide:
	 * 1. Nome segnale esistente		2. Nome porta esistente			3. slotIndex nell'intervallo [0, numOutputSlots)
	 * 
	 * Classi non valide:
	 * 4. Nome segnale non esistente	5. Nome porta non esistente		6. slotIndex fuori dall'intervallo [0, numOutputSlots)
	 * 
	 * Casi di Test:
	 * C1) 	nomeSegnale = "SegnaleEsistente"	nomePorta = "PortaEsistente"	slotIndex = 0 
	 * C2)	nomeSegnale = "SegnaleNonEsistente"	nomePorta = "PortaEsistente"	slotIndex = 0
	 * C3)	nomeSegnale = "SegnaleEsistente"	nomePorta = "PortaNonEsistente"	slotIndex = 0
	 * C4)	nomeSegnale = "SegnaleEsistente"	nomePorta = "PortaEsistente"	slotIndex = -8
	 * */
	
	@Test
	void testCollegaOutputC1() {
		Circuito cir = new Circuito(new Timeline());
		try {
			cir.addSegnale("SegnaleEsistente", false);
			cir.addPorta("PortaEsistente", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.collegaOutput("SegnaleEsistente", "PortaEsistente", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		//L'eccezione non deve essere generata
		assertFalse(eccezioneGenerata);
		
		//"SegnaleEsistente" deve essere collegato allo slot I0 di "PortaEsistente"
		assertEquals(
				cir.getListaSegnali().get("SegnaleEsistente"),
				cir.getListaPorte().get("PortaEsistente").getOutputSlot(0).getSegnaleCollegato());
	}
	
	@Test
	void testCollegaOutputC2() {
		Circuito cir = new Circuito(new Timeline());
		try {
			cir.addSegnale("SegnaleEsistente", false);
			cir.addPorta("PortaEsistente", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.collegaOutput("SegnaleNonEsistente", "PortaEsistente", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		//L'eccezione deve essere generata
		assertTrue(eccezioneGenerata);
		
		//Lo slot I0 di "PortaEsistente" non deve essere collegato ad alcun segnale
		assertEquals(null, cir.getListaPorte().get("PortaEsistente").getOutputSlot(0).getSegnaleCollegato());
	}
	
	@Test
	void testCollegaOutputC3() {
		Circuito cir = new Circuito(new Timeline());
		try {
			cir.addSegnale("SegnaleEsistente", false);
			cir.addPorta("PortaEsistente", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.collegaOutput("SegnaleEsistente", "PortaNonEsistente", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		//L'eccezione deve essere generata
		assertTrue(eccezioneGenerata);
	}
	
	@Test
	void testCollegaOutputC4() {
		Circuito cir = new Circuito(new Timeline());
		try {
			cir.addSegnale("SegnaleEsistente", false);
			cir.addPorta("PortaEsistente", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.collegaOutput("SegnaleEsistente", "PortaEsistente", -8);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		//L'eccezione deve essere generata
		assertTrue(eccezioneGenerata);
	}
	
	/**
	 * 			TEST pulisci()
	 * */
	@Test
	void testPulisci() {
		Timeline timeline = new Timeline();
		Circuito cir = new Circuito(timeline);
		try {
			cir.addSegnale("SegnaleA", false);
			cir.addPorta("PortaAND", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		cir.pulisci();
		
		assertEquals(0, cir.getListaPorte().size());
		assertEquals(0, cir.getListaSegnali().size());
		assertEquals(0, timeline.getNumEventi());
		
		boolean eccezioneGenerata = false;
		try {
			cir.getTracciaSegnale("SegnaleA");
		} catch (TracciaNonTrovataException e) {
			eccezioneGenerata = true;
		}
		
		assertTrue(eccezioneGenerata);
	}
	
	/**
	 * 			TEST simula()
	 * Classi valide:
	 * 1. tempo > 0
	 * Classi non valide:
	 * 2. tempo <= 0
	 * 
	 * Casi di Test:
	 * C1) tempo = 4
	 * C2) tempo = -5
	 * */
	
	@Test
	void testSimulaC1() {
		Circuito cir = new Circuito(new Timeline());
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		
		try {
			cir.simula(4);
		} catch (IstanteNonValidoException | ValidazioneNonRiuscitaException e) {
			eccezioneGenerata = true;
		}
		
		assertFalse(eccezioneGenerata);
		
		try {
			//L'uscita deve essere true per tutti gli istanti di tempo (visto che A e B sono true sin dall'inizio)
			for(int i = 0; i < cir.getTracciaSegnale("C").size(); i++)
				assertEquals(true, cir.getTracciaSegnale("C").get(i));
			
		} catch (TracciaNonTrovataException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testSimulaC2() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		
		try {
			cir.simula(-5);
		} catch (IstanteNonValidoException | ValidazioneNonRiuscitaException e) {
			eccezioneGenerata = true;
		}
		
		assertTrue(eccezioneGenerata);
		
		try {
			//L'uscita non deve essere cambiata
			for(int i = 0; i < cir.getTracciaSegnale("C").size(); i++)
				assertEquals(false, cir.getTracciaSegnale("C").get(i));
			
		} catch (TracciaNonTrovataException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 			TEST rimuoviSegnale()
	 * Classi valide:
	 * 1. nomeSegnale esistente
	 * Classi non valide
	 * 2. nomeSegnale non esistente
	 * */
	@Test
	void testRimuoviSegnaleC1() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		
		try {
			cir.rimuoviSegnale("A");
		} catch (ElementoNonTrovatoException e) {
			eccezioneGenerata = true;
		}
		
		assertFalse(eccezioneGenerata);		
		assertFalse(cir.getListaSegnali().containsKey("A"));	
	}
	
	@Test
	void testRimuoviSegnaleC2() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		
		try {
			cir.rimuoviSegnale("SegnaleCheNonEsiste");
		} catch (ElementoNonTrovatoException e) {
			eccezioneGenerata = true;
		}
		
		assertTrue(eccezioneGenerata);	
	}
	/**
	 * 			TEST scollegaInput
	 * */
	@Test
	void testScollegaInputC1() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.scollegaInput("MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertEquals(null, cir.getListaPorte().get("MyAnd").getInputSlot(0).getSegnaleCollegato());
		assertFalse(eccezioneGenerata);
	}
	@Test
	void testScollegaInputC2() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.scollegaInput("PortaCheNonEsiste", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertNotEquals(null, cir.getListaPorte().get("MyAnd").getInputSlot(0).getSegnaleCollegato());
		assertTrue(eccezioneGenerata);
	}
	@Test
	void testScollegaInputC3() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.scollegaInput("MyAnd", -8);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertNotEquals(null, cir.getListaPorte().get("MyAnd").getInputSlot(0).getSegnaleCollegato());
		assertTrue(eccezioneGenerata);
	}
	
	/**
	 * 			TEST scollegaOutput
	 * */
	@Test
	void testScollegaOutputC1() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.scollegaOutput("MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertEquals(null, cir.getListaPorte().get("MyAnd").getOutputSlot(0).getSegnaleCollegato());
		assertFalse(eccezioneGenerata);
	}
	@Test
	void testScollegaOutputC2() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.scollegaOutput("PortaCheNonEsiste", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertNotEquals(null, cir.getListaPorte().get("MyAnd").getOutputSlot(0).getSegnaleCollegato());
		assertTrue(eccezioneGenerata);
	}
	@Test
	void testScollegaOutputC3() {
		Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}
		
		boolean eccezioneGenerata = false;
		try {
			cir.scollegaOutput("MyAnd", -8);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			eccezioneGenerata = true;
		}
		
		assertNotEquals(null, cir.getListaPorte().get("MyAnd").getOutputSlot(0).getSegnaleCollegato());
		assertTrue(eccezioneGenerata);
	}
	
	/**
	 * 			TEST rimuoviPorta
	 * */
	
	@Test
	void testRimuoviPortaC1() {
	Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}

		boolean eccezioneGenerata = false;
		try {
			cir.rimuoviPorta("MyAnd");
		} catch (ElementoNonTrovatoException e) {
			eccezioneGenerata = true;
		}
		assertEquals(0, cir.getListaPorte().size());
		assertFalse(eccezioneGenerata);
	}
	@Test
	void testRimuoviPortaC2() {
	Circuito cir = new Circuito(new Timeline());
		
		try {
			cir.addSegnale("A", true);
			cir.addSegnale("B", true);
			cir.addSegnale("C", false);
			cir.addPorta("MyAnd", TipoPorta.PORTA_AND);
		} catch (ElementoEsistenteException | ElementoNonTrovatoException e) {
			e.printStackTrace();
		}
		
		try {
			cir.collegaInput("A", "MyAnd", 0);
			cir.collegaInput("B", "MyAnd", 1);
			cir.collegaOutput("C", "MyAnd", 0);
		} catch (ElementoNonTrovatoException | SlotNonValidoException e) {
			e.printStackTrace();
		}

		boolean eccezioneGenerata = false;
		try {
			cir.rimuoviPorta("PortaCheNonEsiste");
		} catch (ElementoNonTrovatoException e) {
			eccezioneGenerata = true;
		}
		assertEquals(1, cir.getListaPorte().size());
		assertTrue(eccezioneGenerata);
	}
}
