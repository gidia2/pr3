package tp1.control.commands;

import tp1.logic.CommandExecuteException;
import tp1.logic.OffWorldException;
import tp1.logic.NotAllowedMoveException;
import tp1.logic.CommandParseException;
import tp1.logic.GameModel;
import tp1.logic.Move;
import tp1.view.Messages;

public class MoveCommand extends Command {

	private Move move;

	public MoveCommand() {}

	protected MoveCommand(Move move) {
		this.move = move;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_MOVE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_MOVE_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_MOVE_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_MOVE_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws CommandExecuteException{
		//TODO fill with your code
		try{
			game.move(move);
			game.update();
			return true;
		}
		catch(OffWorldException owe) {
			throw new CommandExecuteException(Messages.MOVEMENT_ERROR, owe);
		}
		catch(NotAllowedMoveException me) {
			throw new CommandExecuteException("Wrong direction: " + move , me);
		}
	}


	@Override
	public Command parse(String[] commandWords) throws CommandParseException{
		//TODO fill with your code
		if (matchCommandName(commandWords[0])) {
			//move = getMove(commandWords[1].toLowerCase());
			try {
				move = Move.valueOf(commandWords[1].toUpperCase());
				return new MoveCommand(move);
			} catch(IllegalArgumentException e){
				throw new CommandParseException(Messages.DIRECTION_ERROR + commandWords[1]);
			}
		}
		else return null;
	}
}
