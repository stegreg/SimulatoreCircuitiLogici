package porte;

import segnali.Segnale;

public class Slot {
	private Segnale 	segnaleRef;	//Riferimento al segnale collegato in questo slot
	private PortaLogica parent;		//Porta logica a cui appartiene questo slot
	
	public Slot(PortaLogica parent) {
		this.parent = parent;
	}
	
	public void collegaSegnale(Segnale s) {
		segnaleRef = s;		
		
		//s.addToSensitivity(parent);
	}
	
	public void scollegaSegnale() {
		segnaleRef = null;
	}
	
	public Segnale getSegnaleCollegato() {
		return segnaleRef;
	}
}
