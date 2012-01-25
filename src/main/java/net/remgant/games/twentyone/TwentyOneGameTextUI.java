package net.remgant.games.twentyone;

import java.io.PrintStream;
import java.util.List;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.State;
import net.remgant.games.framework.textui.TextUI;
import net.remgant.games.twentyone.actions.GetPlayerAction;
import net.remgant.games.twentyone.actions.GetWagerAction;
import net.remgant.games.twentyone.proxies.GetPlayerActionProxy;
import net.remgant.games.twentyone.proxies.GetWagerActionProxy;
import net.remgant.games.twentyone.states.EndOfHandState;
import net.remgant.games.twentyone.states.GetPlayerActionState;
import net.remgant.games.twentyone.states.InitialState;
import net.remgant.games.twentyone.states.ProcessDealerHandState;

public class TwentyOneGameTextUI extends TextUI
{
	@SuppressWarnings("unused")
	private String args[];
	private TwentyOneGameTextUI(String args[])
	{
		super();
		this.args = args;
		this.game = new TwentyOneGame();
		Action.setProxy(GetWagerAction.class,new GetWagerActionProxy());
		Action.setProxy(GetPlayerAction.class,new GetPlayerActionProxy());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			TwentyOneGameTextUI ui = new TwentyOneGameTextUI(args);
			ui.run();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	protected void printGameState()
	{
		TwentyOneGame g = (TwentyOneGame)game;
		PrintStream out = g.getWriter();
		out.println(state);
		if (state instanceof InitialState)
		{
			out.println("Player Stack: "+g.getPlayerStack());
		}
		else if (state instanceof GetPlayerActionState)
		{
			List<Card> l = g.getDealerHand();
			out.print("Dealer: ");
			for (int i=0; i<l.size(); i++)
			{
				if (i == 0)
					out.print("* ");
				else
					out.print(l.get(i).displayName()+" ");
			}
			out.println();
			out.print("Player: ");
			l = g.getPlayerHand();
			for (Card c : l)
			{
				out.print(c.displayName()+" ");
			}
			out.println("("+g.getPlayerHandValue()+")");
		}
		else if (state instanceof ProcessDealerHandState ||
				state instanceof EndOfHandState)
		{
			out.print("Dealer: ");
			List<Card> l = g.getDealerHand();
			for (Card c : l)
			{
				out.print(c.displayName()+" ");
			}
			out.println("("+g.getDealerHandValue()+")");
			out.print("Player: ");
			l = g.getPlayerHand();
			for (Card c : l)
			{
				out.print(c.displayName()+" ");
			}
			out.println("("+g.getPlayerHandValue()+")");
		}
	}

	
}
