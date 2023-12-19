package tp1.logic;

public class NoShockWaveException extends GameModelException{

	public NoShockWaveException() { super(); }
	
	public NoShockWaveException(String message){ super(message); }
	
	public NoShockWaveException(String message, Throwable cause){
		super(message, cause);
	}
	
	public NoShockWaveException(Throwable cause){ super(cause); }
		NoShockWaveException(String message, Throwable cause,
		boolean enableSuppression, boolean writeableStackTrace){
		super(message, cause, enableSuppression, writeableStackTrace);
	}

}
