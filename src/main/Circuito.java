package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Eccezioni.ElementoEsistenteException;
import Eccezioni.ElementoNonTrovatoException;
import Eccezioni.EventoNonValidoException;
import Eccezioni.IstanteNonValidoException;
import Eccezioni.SlotNonValidoException;
import Eccezioni.TracciaNonTrovataException;
import Eccezioni.ValidazioneNonRiuscitaException;
import eventi.TipoEvento;
import porte.PortaLogica;
import porte.PortaLogicaFactory;
import porte.TipoPorta;
import segnali.RegistratoreSegnale;
import segnali.Segnale;

/**
 * Classe Controller
 * */
public class Circuito {
	private Map<String, PortaLogica> 	porte 		= new HashMap<String, PortaLogica>();
	private Map<String, Segnale> 	 	segnali  	= new HashMap<String, Segnale>();
	private RegistratoreSegnale			recorder	= new RegistratoreSegnale();
	private Timeline					timeline;
	
	public Circuito(Timeline timeline) {
		this.timeline = timeline;
	}
	
	public void addPorta(String nomePorta, TipoPorta tipo) throws ElementoEsistenteException, ElementoNonTrovatoException {
		if(porte.containsKey(nomePorta)) throw new ElementoEsistenteException("Porta '" + nomePorta + "' già esistente.");
		
		porte.put(nomePorta, PortaLogicaFactory.getSingleton().creaPorta(tipo));
	}
	
	public void addSegnale(String nomeSegnale, boolean statoIniziale) throws ElementoEsistenteException {
		if(segnali.containsKey(nomeSegnale)) throw new ElementoEsistenteException("Segnale '" + nomeSegnale + "' già esistente.");
		
		segnali.put(nomeSegnale, new Segnale(statoIniziale));
		
		recorder.creaTraccia(nomeSegnale);
	}
	
	public void aggiungiEvento(TipoEvento tipo, String nomeSegnale, long istante) throws EventoNonValidoException, IstanteNonValidoException {
		if(!segnali.containsKey(nomeSegnale)) throw new EventoNonValidoException("Segnale '" + nomeSegnale + "' non trovato.");
		
		timeline.aggiungiEvento(tipo, segnali.get(nomeSegnale), istante);
	}
	
	public void collegaInput(String nomeSegnale, String nomePorta, int slotIndex) throws ElementoNonTrovatoException, SlotNonValidoException {
		if(!porte.containsKey(nomePorta))		
			throw new ElementoNonTrovatoException("Porta '" + nomePorta + "' non trovata.");
		if(!segnali.containsKey(nomeSegnale))	
			throw new ElementoNonTrovatoException("Segnale '" + nomeSegnale + "' non trovato.");
		if(slotIndex < 0 || slotIndex >= porte.get(nomePorta).getNumInputSlots()) 	
			throw new SlotNonValidoException("Indice di porta '" + slotIndex + "' non valido.");
		
		porte.get(nomePorta).getInputSlot(slotIndex).collegaSegnale(segnali.get(nomeSegnale));
		
		//Aggiungi la porta alla sens list del segnale
		segnali.get(nomeSegnale).addToSensitivity(porte.get(nomePorta));
	}
	
	public void collegaOutput(String nomeSegnale, String nomePorta, int slotIndex) 
			throws ElementoNonTrovatoException, SlotNonValidoException {
		if(!porte.containsKey(nomePorta))		
			throw new ElementoNonTrovatoException("Porta" + nomePorta + " non trovata.");
		if(!segnali.containsKey(nomeSegnale))	
			throw new ElementoNonTrovatoException("Segnale" + nomeSegnale + " non trovato.");
		if(slotIndex < 0 || slotIndex >= porte.get(nomePorta).getNumOutputSlots()) 	
			throw new SlotNonValidoException("Indice di porta " + slotIndex + " non valido.");
		
		porte.get(nomePorta).getOutputSlot(slotIndex).collegaSegnale(segnali.get(nomeSegnale));	
	}	
	
	public void scollegaInput(String nomePorta, int slotIndex)
		throws ElementoNonTrovatoException, SlotNonValidoException{
		if(!porte.containsKey(nomePorta))		
			throw new ElementoNonTrovatoException("Porta" + nomePorta + " non trovata.");
		if(slotIndex < 0 || slotIndex >= porte.get(nomePorta).getNumInputSlots()) 	
			throw new SlotNonValidoException("Indice di porta " + slotIndex + " non valido.");
		
		Segnale segnaleDaScollegare = porte.get(nomePorta).getInputSlot(slotIndex).getSegnaleCollegato();
		if(segnaleDaScollegare == null) return;
		
		//Controlla se, una volta rimosso il segnale dallo slot, rimane 
		//ancora collegato a qualche altro slot della stessa porta
		boolean segnaleAncoraCollegato = false;
		for(int i = 0; i < porte.get(nomePorta).getNumInputSlots(); i++)
			if(i != slotIndex && porte.get(nomePorta).getInputSlot(i).getSegnaleCollegato() == segnaleDaScollegare)
				segnaleAncoraCollegato = true;
		
		if(!segnaleAncoraCollegato)
			segnaleDaScollegare.removeFromSensitivity(porte.get(nomePorta));		

		porte.get(nomePorta).getInputSlot(slotIndex).scollegaSegnale();
	}
	
	public void scollegaOutput(String nomePorta, int slotIndex) 
			throws ElementoNonTrovatoException, SlotNonValidoException{
		if(!porte.containsKey(nomePorta))		
			throw new ElementoNonTrovatoException("Porta" + nomePorta + " non trovata.");
		if(slotIndex < 0 || slotIndex >= porte.get(nomePorta).getNumOutputSlots()) 	
			throw new SlotNonValidoException("Indice di porta " + slotIndex + " non valido.");
		
		porte.get(nomePorta).getOutputSlot(slotIndex).scollegaSegnale();
	}
	
	public void rimuoviSegnale(String nomeSegnale)
			throws ElementoNonTrovatoException{
		if(!segnali.containsKey(nomeSegnale))	
			throw new ElementoNonTrovatoException("Segnale '" + nomeSegnale + "' non trovato.");
		
		Segnale segnaleDaEliminare = segnali.get(nomeSegnale);
		
		//Scorri tutte le porte definite nel circuito
		for(Map.Entry<String, PortaLogica> entry : porte.entrySet()) {
			PortaLogica porta = entry.getValue();
			
			//Controlla se il segnale è collegato a qualche input della porta
			for(int i = 0; i < porta.getNumInputSlots(); i++)			
				if(porta.getInputSlot(i).getSegnaleCollegato() == segnaleDaEliminare)
					porta.getInputSlot(i).scollegaSegnale();				

			//Controlla se il segnale è collegato a qualche output della porta
			for(int o = 0; o < porta.getNumOutputSlots(); o++)			
				if(porta.getOutputSlot(o).getSegnaleCollegato() == segnaleDaEliminare)
					porta.getOutputSlot(o).scollegaSegnale();
		}
		
		//Chiedi alla Timeline di eliminare tutti gli eventi associati
		//al segnale da eliminare
		timeline.rimuoviEventiAssociati(segnaleDaEliminare);
		
		//Rimuovi la traccia associata al segnale
		try {
			recorder.eliminaTraccia(nomeSegnale);
		} catch (TracciaNonTrovataException e) {
			e.printStackTrace();
		}
		
		segnali.remove(nomeSegnale);
	}
	
	public void rimuoviPorta(String nomePorta) throws ElementoNonTrovatoException{
		if(!porte.containsKey(nomePorta))	
			throw new ElementoNonTrovatoException("Porta '" + nomePorta + "' non trovata.");
		
		PortaLogica portaDaEliminare = porte.get(nomePorta);
		
		//Aggiorna la sensitivity list dei segnali collegati alla porta
		for(int i = 0; i < portaDaEliminare.getNumInputSlots(); i++) {
			//Prendi il segnale collegato allo slot i-esimo
			Segnale segnaleCollegato = portaDaEliminare.getInputSlot(i).getSegnaleCollegato();
			
			//Se allo slot è collegato un segnale...
			if(segnaleCollegato != null)
				//...rimuovi la porta dalla sensitivity list di tale segnale
				segnaleCollegato.removeFromSensitivity(portaDaEliminare);
		}
		
		//Elimina la porta
		porte.remove(nomePorta);
	}
	
	public void rimuoviEvento(String nomeSegnale, long istante) 
			throws IstanteNonValidoException, ElementoNonTrovatoException, EventoNonValidoException {
		if(!segnali.containsKey(nomeSegnale)) 
			throw new ElementoNonTrovatoException("Segnale '" + nomeSegnale + "' non trovato.");
		
		timeline.rimuoviEvento(segnali.get(nomeSegnale), istante);
	}
	
	public Map<String, List<String>> valida() {
		//Mappa il nome della porta con la lista di slot non validi
		Map<String, List<String>> porteNonValide = new HashMap<String, List<String>>();
		
		//Controlla tutte le porte del circuito
		for(Map.Entry<String, PortaLogica> entry : porte.entrySet()) {
			int nInputSlots = entry.getValue().getNumInputSlots();
			int nOutputSlots = entry.getValue().getNumOutputSlots();
			
			//Controlla tutti gli slot di ingresso della porta
			for(int i = 0;  i< nInputSlots; i++)
				if(entry.getValue().getInputSlot(i).getSegnaleCollegato() == null) {
					
					if(!porteNonValide.containsKey(entry.getKey()))
						porteNonValide.put(entry.getKey(), new ArrayList<String>());
					
					porteNonValide.get(entry.getKey()).add("I" + i);
				}
			
			//Controlla tutti gli slot di uscita della porta
			for(int i = 0;  i< nOutputSlots; i++)				
				if(entry.getValue().getOutputSlot(i).getSegnaleCollegato() == null) {
					
					if(!porteNonValide.containsKey(entry.getKey()))
						porteNonValide.put(entry.getKey(), new ArrayList<String>());
					
					porteNonValide.get(entry.getKey()).add("O" + i);
				}
		}
		
		return porteNonValide;
	}
	
	public Map<Integer, Boolean> getTracciaSegnale(String nomeSegnale) throws TracciaNonTrovataException{
		return recorder.getTraccia(nomeSegnale);
	}
		
	public Map<String, PortaLogica> getListaPorte() {
		return porte;
	}
	
	public Map<String, Segnale> getListaSegnali(){
		return segnali;
	}
	
	public void pulisci() {
		porte.clear();
		segnali.clear();
		recorder.pulisci();
		
		timeline.pulisci();
	}
	
	public void simula(int tempo) throws IstanteNonValidoException, ValidazioneNonRiuscitaException {
		if(tempo <= 0)				throw new IstanteNonValidoException("Tempo di simulazione '"+ tempo + "' non valido.");
		if(valida().size() != 0) 	throw new ValidazioneNonRiuscitaException("Validazione non riuscita.");
		
		//Riporta tutti i segnali nel loro stato iniziale
		for(Map.Entry<String, Segnale> entry : segnali.entrySet())
			entry.getValue().reset();
		//Pulisci le tracce dei segnali salvate precedentemente
		recorder.pulisciTracce();
		//Riporta la timeline all'istante 0
		timeline.reset();
		
		//Porta il circuito in uno stato iniziale (considerando lo stato iniziale dei segnali).
		for(Map.Entry<String, PortaLogica> entry : porte.entrySet()) {
			entry.getValue().esegui();
		}
		
		for(int t = 0; t < tempo; t++) {					
			//Fai avanzare la timeline (la quale eseguirà tutti gli eventi ad essa registrati)
			timeline.avanza();
			
			//Registra tutti i segnali in questo istante di tempo
			for(Map.Entry<String, Segnale> entry : segnali.entrySet()) {
				try {
					recorder.registra(entry.getKey(), entry.getValue(), t);
				} catch (NullPointerException | IstanteNonValidoException | TracciaNonTrovataException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
