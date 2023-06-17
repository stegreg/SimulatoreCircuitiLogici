package ui.verbi;

public class VerboHandlerFactory {
	private static VerboHandlerFactory instance;
	
	private VerboHandlerFactory() {}
	
	public static VerboHandlerFactory getSingleton() {
		if(instance == null)
			instance = new VerboHandlerFactory();
		
		return instance;
	}
	
	public VerboHandler getHandler(String tipo) {
		String tipoLC = tipo.toLowerCase();
		
		if(
				tipoLC.equals("def") 		|| 
				tipoLC.equals("define") 	||
				tipoLC.equals("definisci")
				)
			return new DefVerboHandler();
		
		if(
				tipoLC.equals("undef") 		|| 
				tipoLC.equals("undefine") 	||
				tipoLC.equals("elimina")
				)
			return new UndefVerboHandler();
		
		if(
				tipoLC.equals("link") 		||
				tipoLC.equals("collega")
				)
			return new LinkVerboHandler();
		
		if(
				tipoLC.equals("unlink")		||
				tipoLC.equals("scollega")
				)
			return new UnlinkVerboHandler();
		
		if(
				tipoLC.equals("simulate") 	||
				tipoLC.equals("simula")
				)
			return new SimulateVerboHandler();
		
		if(
				tipoLC.equals("print")
				)
			return new PrintVerboHandler();
		
		if(
				tipoLC.equals("clear") 		||
				tipoLC.equals("pulisci")	||
				tipoLC.equals("cls")
				)
			return new ClearVerboHandler();
		
		if(
				tipoLC.equals("list") 		||
				tipoLC.equals("lista")	||
				tipoLC.equals("elenco")
				)
			return new ListVerboHandler();
		
		if(
				tipoLC.equals("validate") 		||
				tipoLC.equals("valida")	||
				tipoLC.equals("convalida")
				)
			return new ValidateVerboHandler();
		
		if(
				tipoLC.equals("quit") 		||
				tipoLC.equals("esci")		||
				tipoLC.equals("exit")
				)
			return new QuitVerboHandler();
		
		if(tipoLC.equals("help"))
			return new HelpVerboHandler();
		
		if(tipoLC.equals("load"))
			return new LoadVerboHandler();
		
		
		return null;
	}
}
