package ui.verbi;

import java.util.List;
import java.util.Map;

import Eccezioni.ComandoNonValidoException;
import Eccezioni.TracciaNonTrovataException;
import main.Circuito;

/*
 * 
>> def signal A low
>> def signal B low
>> def gate MyAnd AND
>> link A MyAnd I0
>> link B MyAnd I1
>> def signal C low
>> link C MyAnd O0
>> simulate 15
>> print track A
 * */

public class PrintVerboHandler extends VerboHandler {

	@Override
	public void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException {
		if(parametriVerbo.size() == 0) throw new ComandoNonValidoException("Numero parametri non valido.");
		
		if(parametriVerbo.get(0).toLowerCase().equals("track"))
			printTraccia(parametriVerbo.get(1), cir);
		else 
			throw new ComandoNonValidoException("Comando non valido.");
	}

	public void printTraccia(String nomeSegnale, Circuito cir) throws ComandoNonValidoException {
		try {
			
			Map<Integer, Boolean> track = cir.getTracciaSegnale(nomeSegnale);
			
			final int tempoMax = track.size();
			
			if(tempoMax > 0) {
				//Disegna la timeline
				System.out.print("\t");
				for(int i = 0; i < tempoMax; i++) {
					System.out.print(i + "\t");
				}
				System.out.println("\n");
				
				//Disegna il segnale nel tempo
				System.out.print(nomeSegnale + "\t");			
				for(int i = 0; i < tempoMax; i++) 
					System.out.print(track.get(i) == true ? "H\t" : "L\t");
				System.out.print("\n");
			}
			
		}catch(TracciaNonTrovataException e) {
			throw new ComandoNonValidoException(e.getMessage());
		}
	}
}
