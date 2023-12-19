package tp1.logic;

public class InitializationException extends GameModelException{

	public InitializationException() { super(); }
	
	public InitializationException(String message){ super(message); }
	
	public InitializationException(String message, Throwable cause){
		super(message, cause);
	}
	
	public InitializationException(Throwable cause){ super(cause); }
		InitializationException(String message, Throwable cause,
		boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);
	}

}
