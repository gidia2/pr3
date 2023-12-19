package tp1.logic;

public class OffWorldException extends GameModelException{

	public OffWorldException() { super(); }
	
	public OffWorldException(String message){ super(message); }
	
	public OffWorldException(String message, Throwable cause){
		super(message, cause);
	}
	
	public OffWorldException(Throwable cause){ super(cause); }
		OffWorldException(String message, Throwable cause,
		boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);
	}

}
