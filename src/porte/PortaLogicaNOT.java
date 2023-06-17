package porte;

public class PortaLogicaNOT extends PortaLogica {

	public PortaLogicaNOT() {
		super(1, 1);
		
	}

	@Override
	public void esegui() {
		if(inputSlots.get(0).getSegnaleCollegato().getStato())
			outputSlots.get(0).getSegnaleCollegato().setStato(false);
		else
			outputSlots.get(0).getSegnaleCollegato().setStato(true);
	}

}
