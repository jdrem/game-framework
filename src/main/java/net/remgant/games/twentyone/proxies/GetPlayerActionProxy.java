package net.remgant.games.twentyone.proxies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.regex.Pattern;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.ActionProxy;
import net.remgant.games.framework.Game;
import net.remgant.games.framework.GameException;
import net.remgant.games.twentyone.actions.GetPlayerAction;

public class GetPlayerActionProxy implements ActionProxy
{
	public void getInput(Game game,Action action) throws GameException
	{
		GetPlayerAction getPlayerAction = (GetPlayerAction)action;
		if (getPlayerAction.getPlayerAction() != null)
			return;
		PrintStream out = game.getWriter();
		BufferedReader in = game.getReader();
		
		String playerAction="";
		while (true)
		{
			out.print("Player action: ");
			try
			{
				playerAction = in.readLine();
				if (Pattern.matches("hit|stand",playerAction))
					break;
			}
			catch (IOException ioe)
			{
				throw new GameException("reading input",ioe);
			}
			out.println("invalid action: "+playerAction);
		}	
		getPlayerAction.setPlayerAction(playerAction);
	}

}
