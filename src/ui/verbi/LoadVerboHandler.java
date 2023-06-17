package ui.verbi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import Eccezioni.ComandoNonValidoException;
import main.Circuito;
import ui.EsecutoreComandi;

public class LoadVerboHandler extends VerboHandler {
	private EsecutoreComandi esecutore = new EsecutoreComandi();
	
	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		if(parametriVerbo.size() != 1) throw new ComandoNonValidoException("Numero parametri non valido.");
		
		String fileName = parametriVerbo.get(0);
		
		FileReader fr;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			throw new ComandoNonValidoException("Impossibile caricare il file '" + fileName + "'.");
		}
		
		//Elimina tutte le definizioni fatte precedentemente 
		cir.pulisci();		
		
		BufferedReader br = new BufferedReader(fr);
		String comandoLetto;
		try {
			while((comandoLetto = br.readLine()) != null) {
				
				String cmdArray[] = comandoLetto.split(" ");
				List<String> cmd = Arrays.asList(cmdArray);
				
				if(cmd.size() > 0) {				
					//Assicurati che il comando non sia un altro "load", per evitare spiacevoli ricorsioni
					String verbo = cmd.get(0);
					if(!verbo.equals("load"))
						esecutore.esegui(cmd, cir);					
				}
				
			}
		} catch (IOException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}

}
