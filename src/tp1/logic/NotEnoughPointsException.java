package tp1.logic;

public class NotEnoughPointsException extends GameModelException{

	public NotEnoughPointsException() { super(); }
	
	public NotEnoughPointsException(String message){ super(message); }
	
	public NotEnoughPointsException(String message, Throwable cause){
		super(message, cause);
	}
	
	public NotEnoughPointsException(Throwable cause){ super(cause); }
		NotEnoughPointsException(String message, Throwable cause,
		boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);
	}

}
