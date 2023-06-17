package eventi;

import segnali.Segnale;

public class EventoRaise extends Evento {

	
	public EventoRaise(Segnale sigRef, long istante) {
		super(sigRef, istante);
	}

	@Override
	public void eseguiAzione() {
		segnaleRef.setStato(true);
	}

}
