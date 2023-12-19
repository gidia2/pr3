package tp1.logic;

import tp1.control.InitialConfiguration;

public interface GameModel {
	public void exit();
	public void reset(InitialConfiguration conf) throws InitializationException;
	public void list();
	public void move(Move dir) throws OffWorldException, NotAllowedMoveException;
	public void shoot() throws LaserInFlightException;
	public void shootSuperLaser() throws LaserInFlightException, NotEnoughPointsException;
	public void shockWave() throws NoShockWaveException;
	
	public void update();
	
	public boolean isFinished();
}
