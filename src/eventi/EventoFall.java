package eventi;

import segnali.Segnale;

public class EventoFall extends Evento {

	public EventoFall(Segnale sigRef, long istante) {
		super(sigRef, istante);
	}

	@Override
	public void eseguiAzione() {
		segnaleRef.setStato(false);
	}

}
