package tp1.logic;

import java.util.Random;
import tp1.logic.gameobjects.RegularAlien;
import tp1.logic.gameobjects.DestroyerAlien;
import tp1.logic.gameobjects.ExplosiveAlien;
import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.AlienShip;
import tp1.logic.gameobjects.UCMShip;
import tp1.logic.gameobjects.Ufo;
import tp1.view.Messages;
import tp1.control.InitialConfiguration;


public class Game implements GameStatus, GameWorld, GameModel {

	public static final int DIM_X = 9;
	public static final int DIM_Y = 8;

	private int currentCycle;
	private Level level;
	private long seed;
	private Random rand;
	private AlienManager alienManager;
	private GameObjectContainer container;
	private UCMShip player;
	private boolean doExit;
	
	
	public Game(Level level, long seed) {
		currentCycle = 0;
		this.level= level ;
		this.seed = seed ;
		rand = new Random(this.seed);
		doExit = false;
		alienManager = new AlienManager(this);
		initGame();
	}
	
	private void initGame() {
		this.container = alienManager.initialize();
		this.player = new UCMShip(this);
		container.add(player);
	}
	
	private void initGame(InitialConfiguration conf, AlienManager oldAlienManager) throws InitializationException{
		this.container = alienManager.initialize(conf, oldAlienManager.getRemainingAliens());
		this.player = new UCMShip(this);
		container.add(player);
	}
	
	
	//Méetodos para la interfaz GameModel para la clase Controller
	public void exit(){
		doExit = true;
	}
	
	public void reset(InitialConfiguration conf) throws InitializationException{
		currentCycle = 0;
		rand = new Random(this.seed);
		AlienManager oldAlienManager = alienManager;
		alienManager = new AlienManager(this);
		initGame(conf, oldAlienManager);
		doExit = false;
	}
	
	public void list() {
		System.out.println(infoToString());
	}
	
	public void move(Move move) throws OffWorldException, NotAllowedMoveException{
		player.moveUCMShip(move);
	}
	
	public void shoot() throws LaserInFlightException{
		player.shootLaser();
	}
	
	public void shootSuperLaser() throws LaserInFlightException, NotEnoughPointsException{
		player.shootSuperLaser();
	}
	
	public void shockWave() throws NoShockWaveException{
		player.shockWave();
	}
	
	public void explosion(ExplosiveAlien alien){
		Position centro = alien.getPosition();
		for(int i = centro.getRow() - 1; i < centro.getRow() + 2; i++) {
			for (int j = centro.getCol()- 1; j < centro.getCol()+ 2; j++) {
				Position pos = new Position(j,i);
				container.checkExplosion(pos, alien);
			}
		}
	}
	
	public void update(){
		updateCycle();
		computerActions();
		automaticMoves();
		performAttacks();
	}
	
	public boolean isFinished() {
		return doExit || playerWin() || aliensWin();
	}
	
	private void updateCycle() {
		currentCycle++;
	}
	
	private void computerActions(){
		container.computerActions();
	}
	
	private void automaticMoves() {
		container.automaticMoves();
	}
	
	private void performAttacks() {
		container.performAttacks();
	}
	
	private String infoToString() {
		StringBuilder info = new StringBuilder();
		info
		.append(new UCMShip(this).getInfo()).append("\n")
		.append(new RegularAlien(this, new Position(0,0), alienManager).getInfo()).append("\n")
		.append(new DestroyerAlien(this, new Position(0, 0), alienManager).getInfo()).append("\n")
		.append(new Ufo(this).getInfo()).append("\n")
		.append(new ExplosiveAlien(this, new Position(0,0),alienManager).getInfo()).append("\n");
		
		return info.toString();
	}
	
	
	//Métodos de la interfaz GameWorld para la clase GameObject
	public Random getRandom() {
		return rand;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void addObject(GameObject object) {
		container.add(object);
	}
	
	public void removeObject(GameObject object) {
		container.remove(object);
	}
	
	public void performAttacks(GameObject object) {
		container.performAttacks(object);
	}
	
	public void checkAttacks(GameObject object) {
		container.checkAttacks(object);
	}	
	
	public void enableBomb(DestroyerAlien alien) {
		alien.enableBomb();
	}
	
	public void enableLaser() {
		player.enableLaser();
	}
	
	public void enableShockWave() {
		player.enableShockWave();
	}
	
	public void alienDead(AlienShip alien) {
		alienManager.alienDead();
		removeObject(alien);
	}
	
	public void receivePoints(int points){
		player.receivePoints(points);
	}
	
	
	//Métodos de la interfaz GameStatus para la Clase BoardPrinter
	public int getCycle() {
		return currentCycle;
	}
	
	public int getRemainingAliens() {
		return alienManager.getRemainingAliens();
	}
	
	public boolean playerWin() {
		return alienManager.allAlienDead();
	}

	public boolean aliensWin() {
		return alienManager.haveLanded() || !player.isAlive();
	}
	
	public String stateToString() {
		StringBuilder buffer = new StringBuilder();
		/* @formatter:off */
		buffer
		.append(Messages.REMAINING_LIFES).append(" ").append(player.getLife()).append("\n")
		.append(Messages.POINTS).append(" ").append(player.getPoints()).append("\n")
		.append(Messages.HAS_SHOCKWAVE).append(" ").append(player.hasShockWave() ? Messages.SHOCKWAVE_ON : Messages.SHOCKWAVE_OFF).append("\n");
		
		return buffer.toString();
	}
	
	public String positionToString(int col, int row) {
		return container.toString(col, row);
	}
}
