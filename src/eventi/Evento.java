package eventi;

import segnali.Segnale;

public abstract class Evento {
	protected long 		istante;
	protected Segnale 	segnaleRef;
	
	public Evento(Segnale sigRef, long istante) {
		this.istante 	= istante;
		this.segnaleRef = sigRef;
	}
	
	public long getIstante() {
		return istante;
	}
	
	public Segnale getSegnale() {
		return segnaleRef;
	}
	
	public abstract void eseguiAzione();
}
