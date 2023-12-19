package tp1.logic;

public class NotAllowedMoveException extends GameModelException{

	public NotAllowedMoveException() { super(); }
	
	public NotAllowedMoveException(String message){ super(message); }
	
	public NotAllowedMoveException(String message, Throwable cause){
		super(message, cause);
	}
	
	public NotAllowedMoveException(Throwable cause){ super(cause); }
		NotAllowedMoveException(String message, Throwable cause,
		boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);
	}
}
