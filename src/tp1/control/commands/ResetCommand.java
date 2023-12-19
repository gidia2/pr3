package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.logic.InitializationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import tp1.logic.Move;
import tp1.view.Messages;
import tp1.control.InitialConfiguration;

public class ResetCommand extends Command{
	
	InitialConfiguration conf;
	
	public ResetCommand() {conf = InitialConfiguration.NONE;}
	
	protected ResetCommand(InitialConfiguration conf) {
		this.conf = conf;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	protected String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	protected String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameModel game) throws InitializationException {
		try {
			game.reset(conf);
			return true;
		}
		catch(InitializationException e) {
			throw new InitializationException("Invalid initial configuration", e);
		}
		
	}
	
	@Override
	public Command parse(String[] commandWords) throws InitializationException{
		//TODO fill with your code
		if (matchCommandName(commandWords[0])) {
			if(commandWords.length == 1) {
				return new ResetCommand(InitialConfiguration.NONE);
			}
			else {
				try {
					conf = InitialConfiguration.readFromFile(commandWords[1]);
					return new ResetCommand(conf);
				}
				catch(InitializationException e) {
					throw e;
				}
				catch(IOException ioe) {
					System.out.println(ioe.getMessage());
					return null;
				}
			}
		}
		else return null;
	}

}
