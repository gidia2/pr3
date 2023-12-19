package tp1.logic;

public class LaserInFlightException extends GameModelException{

	public LaserInFlightException() { super(); }
	
	public LaserInFlightException(String message){ super(message); }
	
	public LaserInFlightException(String message, Throwable cause){
		super(message, cause);
	}
	
	public LaserInFlightException(Throwable cause){ super(cause); }
		LaserInFlightException(String message, Throwable cause,
		boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);
	}

}
