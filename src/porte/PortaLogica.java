package porte;

import java.util.ArrayList;
import java.util.List;

public abstract class PortaLogica {
	//Slot di ingresso della porta logica.
	protected List<Slot> inputSlots  = new ArrayList<Slot>();
	//Slot di uscita della porta logica.
	protected List<Slot> outputSlots = new ArrayList<Slot>();
	
	public PortaLogica(int nInputs, int nOutputs) {
		//Crea gli slot in ingresso
		for(int i = 0; i < nInputs; i++) 
			inputSlots.add(new Slot(this));

		//Crea gli slot in uscita
		for(int i = 0; i < nOutputs; i++) 
			outputSlots.add(new Slot(this));
	}
	
	public int getNumInputSlots() {
		return inputSlots.size();
	}
	
	public int getNumOutputSlots() {
		return outputSlots.size();
	}	
	
	public Slot getInputSlot(int index) {
		if(index < 0 || index >= inputSlots.size()) return null;
		
		return inputSlots.get(index);
	}
	
	public Slot getOutputSlot(int index) {
		if(index < 0 || index >= outputSlots.size()) return null;
		
		return outputSlots.get(index);
	}
	
	public abstract void esegui();
}
