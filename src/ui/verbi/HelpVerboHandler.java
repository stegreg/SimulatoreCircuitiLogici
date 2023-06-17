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
		System.out.println("cls: pulisce tutte le definizioni di porte logiche e di segnali fatte precedentemente.\n");
	}
	
	private void printDef() {
		System.out.println(
				"def: definisce un elemento nel circuito.\n"
				+ "\tdef gate [nome_gate] [tipo_porta]: definisce una porta logica, dove:\n"
				+ "\t\t-[nome_gate] è il nome della porta\n"
				+ "\t\t-[tipo_porta] può essere and, or oppure low\n"
				+ "\tdef signal [nome_segnale] [stato_iniziale]: definisce un segnale, dove:\n"
				+ "\t\t-[nome_segnale] è il nome del segnale\n"
				+ "\t\t-[stato_iniziale] è opzionale, e può essere high oppure low\n"
				+ "\tdef event [tipo_evento] [nome_segnale] [istante]: definisce un evento nella timeline, dove:\n"
				+ "\t\t-[tipo_evento]: può essere raise oppure fall\n"
				+ "\t\t-[nome_segnale]: il segnale definito precedentemente a cui associare l'evento.\n"
				+ "\t\t-[istante]: istante di tempo positivo in cui si deve verificare l'evento\n");
	}
	
	private void printLink() {
		System.out.println(
				"link [nome_segnale] [nome_porta] [slot]: collega un segnale ad uno slot di una porta logica definita precedentemente, dove:\n"
				+ "\t-[nome_segnale]: nome del segnale definito precedentemente, da collegare allo slot.\n"
				+ "\t-[nome_porta]: nome della porta logica definita precedentemente, da collegare al segnale\n"
				+ "\t-[slot]: slot della porta da collegare. Il formato è: "
				+ "[I/O][slotIndex], dove:\n"
				+ "\t\t-[I] indica che lo slot è di input.\n"
				+ "\t\t-[O] indica che lo slot è di output.\n"
				+ "\t\t-[slotIndex] è l'indice dello slot.\n"
				+ "\t\tPer esempio, I0 indica il primo slot di ingresso, O1 indica il secondo slot di uscita.\n"
				);
	}
	
	private void printList() {
		System.out.println("list: mostra la lista dei segnali e porte logiche definite fino ad ora.\n");
	}
	
	private void printPrint() {
		System.out.println("print: chiedi al Sistema di visualizzare qualcosa.\n"
				+ "\tprint [cosa_visualizzare] [parametri], dove:\n"
				+ "\t\t[cosa_visualizzare] può essere:\n"
				+ "\t\t\t-track. In questo caso [parametri] corrisponde al nome della traccia da visualizzare\n");
	}
	private void printQuit() {
		System.out.println("quit: esce dal programma.\n");
	}
	private void printSimulate() {
		System.out.println("simulate: simula il circuito definito e convalidato.\n"
				+ "\tsimulate [tempo] dove:\n"
				+ "\t\t[tempo] è indica per quanto tempo si vuole simulare il circuito\n");
	}
	private void printUndef() {
		System.out.println(
				"undef: rimuove la definizione di un elemento dal circuito.\n"
				+ "\tundef gate [nome_gate] [tipo_porta]: elimina una porta logica, dove:\n"
				+ "\t\t-[nome_gate] è il nome della porta\n"
				+ "\tundef signal [nome_segnale]: definisce un segnale, dove:\n"
				+ "\t\t-[nome_segnale] è il nome del segnale\n"
				+ "\tundef event [nome_segnale] [istante]: elimina un evento dalla timeline, dove:\n"
				+ "\t\t-[nome_segnale]: il segnale a cui è associato l'evento.\n"
				+ "\t\t-[istante]: istante di tempo in cui si verifica l'evento\n");
	}
	private void printUnlink() {
		System.out.println(
				"unlink [nome_porta] [slot]: scollega un segnale da uno slot di una porta logica definita precedentemente, dove:\n"
				+ "\t-[nome_porta]: nome della porta logica definita precedentemente in cui si trova lo slot da scollegare\n"
				+ "\t-[slot]: slot della porta da scollegare. Il formato è: "
				+ "[I/O][slotIndex], dove:\n"
				+ "\t\t-[I] indica che lo slot è di input.\n"
				+ "\t\t-[O] indica che lo slot è di output.\n"
				+ "\t\t-[slotIndex] è l'indice dello slot.\n"
				+ "\t\tPer esempio, I0 indica il primo slot di ingresso, O1 indica il secondo slot di uscita.\n"
				);
	}
	
	private void printValidate() {
		System.out.println("validate: esegue la validazione del circuito.\n");
	}
	
	private void printLoad() {
		System.out.println("load: carica la definizione di circuito da file. NOTA: prima di caricare, elimina qualsiasi definizione fatta precedentemente.\n"
				+ "\tload [nome_file] dove:\n"
				+ "\t\t[nome_file] indica il nome del file che contiene la definizione del circuito.");
	}
}
