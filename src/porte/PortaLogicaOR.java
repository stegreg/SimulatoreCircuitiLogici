package porte;

public class PortaLogicaOR extends PortaLogica{
	public PortaLogicaOR() {
		super(2, 1);
		
	}

	@Override
	public void esegui() {
		if(inputSlots.get(0).getSegnaleCollegato().getStato() || 
				inputSlots.get(1).getSegnaleCollegato().getStato()) {
			outputSlots.get(0).getSegnaleCollegato().setStato(true);
		}
		else {
			outputSlots.get(0).getSegnaleCollegato().setStato(false);
		}
	}
}
