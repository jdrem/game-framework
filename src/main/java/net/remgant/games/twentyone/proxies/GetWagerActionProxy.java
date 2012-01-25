package net.remgant.games.twentyone.proxies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.ActionProxy;
import net.remgant.games.framework.Game;
import net.remgant.games.framework.GameException;
import net.remgant.games.twentyone.actions.GetWagerAction;

public class GetWagerActionProxy implements ActionProxy
{

	public void getInput(Game game, Action action) throws GameException
	{
		GetWagerAction a = (GetWagerAction)action;
		if (a.getWager() > 0)
			return;
		PrintStream out = game.getWriter();
		BufferedReader in = game.getReader();
		int wager = 0;
		while (true)
		{
			out.print("Player wager: ");
			String response = null;
			try
			{
				response = in.readLine();
			}
			catch (IOException ioe)
			{
				throw new GameException("reading input",ioe);
			}
			try
			{
				wager = Integer.parseInt(response);
			}
			catch (NumberFormatException nfe)
			{
				out.println("invalid response: "+response);
			}
			if (wager <= 0)
				out.println("wager must be greater than zero");
			else
				break;
		}	
		a.setWager(wager);
	}
}
