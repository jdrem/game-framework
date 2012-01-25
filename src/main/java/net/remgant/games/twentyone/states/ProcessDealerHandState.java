package net.remgant.games.twentyone.states;

import java.util.ArrayList;


import net.remgant.games.framework.Action;
import net.remgant.games.framework.Game;
import net.remgant.games.framework.State;
import net.remgant.games.framework.TextMessage;
import net.remgant.games.framework.actions.NullAction;
import net.remgant.games.twentyone.TwentyOneGame;

@SuppressWarnings("serial")
public class ProcessDealerHandState extends State
{
	public ProcessDealerHandState()
	{
		availableActions = new ArrayList<Action>();
		// availableActions.add(new ProcessDealerHandAction());
	}
	@Override
	public boolean requiresInput()
	{
		return false;
	}
	@Override
	public Action getDefaultAction()
	{
		return new NullAction();
	}
	@Override
	public void applyAction(Game game, Action action)
	{
		TwentyOneGame thisGame = (TwentyOneGame)game;
		while (thisGame.getDealerHandValue() < 17 ||
				(thisGame.getDealerHandValue() == 17 && thisGame.isDealerHandSoft()))
		{
			thisGame.addToDealerHand(thisGame.getCardFromDeck());
		}
		if (thisGame.getDealerHandValue() > 21 ||
			thisGame.getPlayerHandValue() > thisGame.getDealerHandValue())
		{
			thisGame.addToStack(thisGame.getWager());
			thisGame.setWager(0);
			game.sendMessage(new TextMessage("Player = "+thisGame.getPlayerHandValue()+", Dealer: "+
					thisGame.getDealerHandValue()+", Player wins"));
		}
		else if (thisGame.getPlayerHandValue() == thisGame.getDealerHandValue())
		{
			thisGame.setWager(0);
			game.sendMessage(new TextMessage("Player = "+thisGame.getPlayerHandValue()+", Dealer: "+
					thisGame.getDealerHandValue()+", push"));
		}
		else
		{
			thisGame.subtractFromStack(thisGame.getWager());
			thisGame.setWager(0);
			game.sendMessage(new TextMessage("Player = "+thisGame.getPlayerHandValue()+", Dealer: "+
					thisGame.getDealerHandValue()+", Dealer wins"));
		}
		game.setState(new EndOfHandState());
	}
	@Override
	public String toString()
	{
		return "ProcessDealerHandState";
	}

}
