package tp1.logic.gameobjects;

import tp1.logic.*;
import tp1.view.Messages;

public class UCMShip extends Ship {
	private static final int ARMOR = 3;
	private static final int DAMAGE = 1;	
	private static final Position INITIAL_POS = new Position(4, 7);

	private int points;
	private boolean hasShockWave;
	private boolean canShoot;
	
	public UCMShip(GameWorld game) {
		super(game, INITIAL_POS, ARMOR);
		points = 0;
		hasShockWave = false;
		canShoot = true;
	}
		
	@Override
	protected int getDamage() {
		return DAMAGE;
	}
	
	@Override
	protected int getArmor() {
		return ARMOR;
	}
	
	@Override
	protected String getSymbol() {
		return isAlive()
				? Messages.UCMSHIP_SYMBOL 
				: Messages.UCMSHIP_DEAD_SYMBOL;
	}
	
	@Override
	public String getInfo() {
		return Messages.ucmShipDescription(getDescription(), DAMAGE, ARMOR);
	}
	
	public String getDescription() {
		return Messages.UCMSHIP_DESCRIPTION;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void receivePoints(int points) {
		this.points += points;
	}
	
	public void enableLaser(){
		canShoot = true;
	}
	
	public boolean hasShockWave() {
		return hasShockWave;
	}
	public void enableShockWave() {
		hasShockWave = true;
	}
	
	public void moveUCMShip(Move dir) throws OffWorldException, NotAllowedMoveException {
		if(dir != Move.UP && dir != Move.DOWN && dir != Move.NONE) {
			Position new_pos = new Position(pos.getCol() + dir.getX(), pos.getRow() + dir.getY());
			if (new_pos.isOnBoard()) {
				pos = new_pos;
			}
			else {
				throw new OffWorldException("Cannot move in direction " + dir + " from position (" + pos.getCol() + ", " + pos.getRow() + ")");
			}
		}
		else {
			throw new NotAllowedMoveException("Allowed UCMShip moves: <left|lleft|right|rright>");
		}
	}
	
	public void shootLaser() throws LaserInFlightException{
		if(canShoot) {
			UCMLaser laser = new UCMLaser(game, pos);
			game.addObject(laser);
			canShoot = false;
		}
		else {
			throw new LaserInFlightException(Messages.LASER_ALREADY_IN_FLIGHT);
		}
	}
	
	public void shootSuperLaser() throws NotEnoughPointsException, LaserInFlightException{
		if(canShoot && points >= 5) {
			SuperLaser superLaser = new SuperLaser(game, pos);
			game.addObject(superLaser);
			canShoot = false;
			points -= 5;
		}
		else {
			if(points < 5) {
				throw new NotEnoughPointsException("Not enough points: only " + points + " points, 5 points required");
			}
			else {
				throw new LaserInFlightException(Messages.LASER_ALREADY_IN_FLIGHT);
			}
		}
	}
	
	public void shockWave() throws NoShockWaveException{
		if(hasShockWave) {
			ShockWave shockwave = new ShockWave(game, pos);
			game.addObject(shockwave);
			game.performAttacks(shockwave);
			game.removeObject(shockwave);
			hasShockWave = false;
		}
		else {
			throw new NoShockWaveException(Messages.SHOCKWAVE_ERROR);
		}
	}
	
	public void receiveAttack(EnemyWeapon weapon) {
		receiveDamage(weapon.getDamage());
	}
	
	public String toString() {
		return isAlive()
				? Messages.UCMSHIP_SYMBOL 
				: Messages.UCMSHIP_DEAD_SYMBOL;
	}
	
	public boolean onDelete() {
		life = 0;
		return false;
	}
}
