package segnali;

import java.util.ArrayList;
import java.util.List;

import porte.PortaLogica;

public class Segnale {
	private boolean		stato;
	private boolean		statoIniziale;
	/**
	 * Concetto di sensitivity list:
	 * Il segnale mantiene una lista di tutte le porte a cui è collegato.
	 * Quando il segnale cambia stato, vuol dire che tutte queste porte devono essere
	 * rieseguite (ovvero devono ricalcolare il loro output).
	 * */
	private List<PortaLogica> 	sensitivtyList = new ArrayList<PortaLogica>();	
	
	public Segnale() {
		stato = false;
		statoIniziale = stato;
	}
	
	public Segnale(boolean statoIniziale) {
		this.stato = statoIniziale;
		this.statoIniziale = statoIniziale;
	}
	
	public void reset() {
		stato = statoIniziale;
	}
	
	public void setStato(boolean nuovoStato) {
		if(stato != nuovoStato) {
			stato = nuovoStato;
			
			//Se lo stato del segnale cambia, riesegui tutte le 
			//porte che ne dipendono.
			for(PortaLogica pl : sensitivtyList) {
				pl.esegui();
			}			
		}
	}
	
	public boolean getStato() {
		return stato;
	}
	
	public boolean getStatoIniziale() {
		return statoIniziale;
	}
	
	public void addToSensitivity(PortaLogica pl) {
		if(!sensitivtyList.contains(pl))
			sensitivtyList.add(pl);
	}
	
	public void removeFromSensitivity(PortaLogica pl) {
		if(sensitivtyList.contains(pl))
			sensitivtyList.remove(pl);
	}
}
