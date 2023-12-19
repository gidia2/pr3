package tp1.control.commands;

import tp1.logic.CommandExecuteException;
import tp1.logic.NotEnoughPointsException;
import tp1.logic.GameModel;
import tp1.logic.LaserInFlightException;
import tp1.view.Messages;

public class SuperLaserCommand extends NoParamsCommand {

	@Override
	protected String getName() {
		return Messages.COMMAND_SUPER_LASER_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_SUPER_LASER_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_SUPER_LASER_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_SUPER_LASER_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException {
		try {
			game.shootSuperLaser();
			game.update();
			return true;
		}
		catch (LaserInFlightException e1) {
			throw new CommandExecuteException(Messages.LASER_ERROR, e1); 
		}
		catch (NotEnoughPointsException e2) {
			throw new CommandExecuteException(Messages.LASER_ERROR, e2);
		}
	}

}
