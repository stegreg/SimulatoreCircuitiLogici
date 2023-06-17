package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import Eccezioni.ComandoNonValidoException;
import main.Circuito;
import main.Timeline;

public class UI {
	private final String help = "Digita 'help' per ottenere la lista dei comandi. Oppure 'help' + [nome_comando] per l'help relativo a [nome_comando].";
	
	private EsecutoreComandi esecutore;
	
	private Circuito circuito = new Circuito(new Timeline());
	
	public UI() {
		esecutore = new EsecutoreComandi();
		
		System.out.println("Simulatore di circuiti logici. " + help);
	}
	
	public void start() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String comando;
		while(true) {
			System.out.print(">> ");
			
			comando = br.readLine();
			
			if(comando != null) {
				String cmds[] = comando.split(" ");
				
				try {
					esecutore.esegui(Arrays.asList(cmds), circuito);
				} catch (ComandoNonValidoException e) {
					System.out.println(e.getMessage() + " " + help);
				}
			}
		}		

	}
}
