package tp1.logic;

public class GameModelException extends Exception{

	public GameModelException() { super(); }
	
	public GameModelException(String message){ super(message); }
	
	public GameModelException(String message, Throwable cause){
		super(message, cause);
	}
	
	public GameModelException(Throwable cause){ super(cause); }
		GameModelException(String message, Throwable cause,
		boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);
	}

}
