package eventi;

import segnali.Segnale;

public class EventoFactory {
	private static EventoFactory instance = null;
	
	private EventoFactory() {}
	
	public static EventoFactory getSingleton() {
		if(instance == null) {
			instance = new EventoFactory();
		}
		
		return instance;
	}
	
	public Evento creaEvento(TipoEvento tipo, Segnale sigRef, long istante) {
		if(tipo == TipoEvento.EVT_RAISE) {
			return new EventoRaise(sigRef, istante);
		}
		else if(tipo == TipoEvento.EVT_FALL) {
			return new EventoFall(sigRef, istante);
		}
		
		return null;
	}
	
}
