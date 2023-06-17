package ui.verbi;

import java.util.List;

import Eccezioni.ComandoNonValidoException;
import main.Circuito;

/*
 * Classe base per gestire il verbo ("def", "undef", "simulate", ecc)
 * di un comando UI.
 * */
public abstract class VerboHandler {
	public abstract void esegui(List<String> parametriVerbo, Circuito cir) throws ComandoNonValidoException;
}
