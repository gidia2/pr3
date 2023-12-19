package tp1.logic;

public class CommandExecuteException extends Exception{

	public CommandExecuteException() { super(); }
	
	public CommandExecuteException(String message){ super(message); }
	
	public CommandExecuteException(String message, Throwable cause){
		super(message, cause);
	}
	
	public CommandExecuteException(Throwable cause){ super(cause); }
		CommandExecuteException(String message, Throwable cause,
		boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);
	}

}
