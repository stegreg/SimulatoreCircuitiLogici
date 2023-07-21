package ui.verbi;

import java.util.List;

import Eccezioni.ComandoNonValidoException;
import main.Circuito;

public class HelpVerboHandler extends VerboHandler {

	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		if(parametriVerbo.size() == 0)
			printAll();
		else if(parametriVerbo.size() == 1) {
			String verboCercato = parametriVerbo.get(0);
			
			if(verboCercato.equalsIgnoreCase("cls")) 
				printClear();

			if(verboCercato.equalsIgnoreCase("def")) 
				printDef();
			
			if(verboCercato.equalsIgnoreCase("link")) 
				printLink();

			if(verboCercato.equalsIgnoreCase("list")) 
				printList();
			
			if(verboCercato.equalsIgnoreCase("print")) 
				printPrint();

			if(verboCercato.equalsIgnoreCase("quit")) 
				printQuit();
			
			if(verboCercato.equalsIgnoreCase("simulate")) 
				printSimulate();

			if(verboCercato.equalsIgnoreCase("undef")) 
				printUndef();
			
			if(verboCercato.equalsIgnoreCase("unlink")) 
				printUnlink();

			if(verboCercato.equalsIgnoreCase("validate")) 
				printValidate();

			if(verboCercato.equalsIgnoreCase("load")) 
				printLoad();
		}
	}
	
	private void printAll() {
		printClear();
		printDef();
		printLink();
		printList();
		printPrint();
		printQuit();
		printSimulate();
		printUndef();
		printUnlink();
		printValidate();
		printLoad();
	}
	
	private void printClear() {
		System.out.println("cls: clears all previously placed circuit elements (gates and signals).\n");
	}
	
	private void printDef() {
		System.out.println(
				"def: defines a circuit element.\n"
				+ "\tdef gate [gate_name] [gate_type]: defines a logic gate, where:\n"
				+ "\t\t-[gate_name]:----------gate's name\n"
				+ "\t\t-[gate_type]:----------can be: and, or, not\n\n"
				+ "\tdef signal [signal_name] [initial_state]: defines a signal, where:\n"
				+ "\t\t-[signal_name]:--------signal's name\n"
				+ "\t\t-[initial_state]:------optional, can be either high or low\n\n"
				+ "\tdef event [event_type] [signal_name] [time]: defines an event, where:\n"
				+ "\t\t-[event_type]:---------can be either raise or fall\n"
				+ "\t\t-[signal_name]:--------previously defined signal that the event must modify\n"
				+ "\t\t-[time]:---------------time instant when the event must occur\n\n");
	}
	
	private void printLink() {
		System.out.println(
				"link [signal_name] [gate_name] [slot]: links a signal to a slot of a previously defined gate, where:\n"
				+ "\t-[signal_name]:------name of the previously defined signal to link to the slot.\n"
				+ "\t-[gate_name]:--------name of the previously defined gate to be linked to the signal\n"
				+ "\t-[slot]:-------------gate's slot to be linked. Its format is"
				+ "[I/O][slotIndex], where:\n"
				+ "\t\t-[I]---------means that the slot is an input slot.\n"
				+ "\t\t-[O]---------means that the slot is an output slot.\n"
				+ "\t\t-[slotIndex]-index of the slot.\n"
				+ "\t\tFor example, I0 means first input slot, while O1 means second output slot.\n"
				);
	}
	
	private void printList() {
		System.out.println("list: shows a list of all the signals and gates defined so far.\n");
	}
	
	private void printPrint() {
		System.out.println("print: ask the program to visualize something:\n"
				+ "\tprint [what_to_visualize] [parameters], where:\n"
				+ "\t\t[what_to_visualize] can be:\n"
				+ "\t\t\t-track. Prints a signal's track. Here, [parameters] must be the name of the track to print.\n");
	}
	private void printQuit() {
		System.out.println("quit: exits the program.\n");
	}
	private void printSimulate() {
		System.out.println("simulate: simulates the circuit.\n"
				+ "\tsimulate [total_time] where:\n"
				+ "\t\t[total_time]---For how much time the circuit must be simulated\n");
	}
	private void printUndef() {
		System.out.println(
				"undef: rimuove la definizione di un elemento dal circuito.\n"
				+ "\tundef gate [nome_gate] [tipo_porta]: elimina una porta logica, dove:\n"
				+ "\t\t-[nome_gate] � il nome della porta\n"
				+ "\tundef signal [nome_segnale]: definisce un segnale, dove:\n"
				+ "\t\t-[nome_segnale] � il nome del segnale\n"
				+ "\tundef event [nome_segnale] [istante]: elimina un evento dalla timeline, dove:\n"
				+ "\t\t-[nome_segnale]: il segnale a cui � associato l'evento.\n"
				+ "\t\t-[istante]: istante di tempo in cui si verifica l'evento\n");
	}
	private void printUnlink() {
		System.out.println(
				"unlink [nome_porta] [slot]: scollega un segnale da uno slot di una porta logica definita precedentemente, dove:\n"
				+ "\t-[nome_porta]: nome della porta logica definita precedentemente in cui si trova lo slot da scollegare\n"
				+ "\t-[slot]: slot della porta da scollegare. Il formato �: "
				+ "[I/O][slotIndex], dove:\n"
				+ "\t\t-[I] indica che lo slot � di input.\n"
				+ "\t\t-[O] indica che lo slot � di output.\n"
				+ "\t\t-[slotIndex] � l'indice dello slot.\n"
				+ "\t\tPer esempio, I0 indica il primo slot di ingresso, O1 indica il secondo slot di uscita.\n"
				);
	}
	
	private void printValidate() {
		System.out.println("validate: executes circuit's validation.\n");
	}
	
	private void printLoad() {
		System.out.println("load: loads a circuit definition from a file. NB: before loading, this clears the circuit from all previously defined elements.\n"
				+ "\tload [file_name] where:\n"
				+ "\t\t[file_name]---name of the file to load");
	}
}
