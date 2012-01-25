package net.remgant.games.twentyone.states;

import java.util.ArrayList;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.Game;
import net.remgant.games.framework.Message;
import net.remgant.games.framework.State;
import net.remgant.games.framework.TextMessage;
import net.remgant.games.framework.actions.RestoreGameAction;
import net.remgant.games.framework.actions.SaveGameAction;
import net.remgant.games.twentyone.TwentyOneGame;
import net.remgant.games.twentyone.actions.GetWagerAction;

@SuppressWarnings("serial")
public class InitialState extends State
{

	public InitialState()
	{
		availableActions = new ArrayList<Action>();
		availableActions.add(new SaveGameAction());
		availableActions.add(new RestoreGameAction());
		availableActions.add(new GetWagerAction());	
	}

	@Override
	public boolean requiresInput()
	{
		return true;
	}

	@Override
	public void applyAction(Game game,Action action)
	{
		
		if (action instanceof SaveGameAction)
		{
			// game.saveGame(((SaveGameAction)action).getFileName());
			nextState = this;
			return;
		}
		if (action instanceof RestoreGameAction)
		{
			// game.restoreGame(((RestoreGameAction)action).getFileName());
			nextState = this;
			return;
		}
		if (action instanceof GetWagerAction)
		{
			TwentyOneGame thisGame = (TwentyOneGame)game;
			thisGame.setWager(((GetWagerAction)action).getWager());
			thisGame.dealHand();
			nextState = new EndOfHandState();
			if (thisGame.getPlayerHandValue() == 21 && thisGame.getDealerHandValue() != 21)
			{
				thisGame.addToStack(thisGame.getWager()*3/2);
				thisGame.setWager(0);
				thisGame.sendMessage(new TextMessage("Plyaer has blackjack, player wins 3:2 "));
			}
			else if (thisGame.getPlayerHandValue() == 21 && thisGame.getDealerHandValue() == 21)
			{
				thisGame.setWager(0);
				thisGame.sendMessage(new TextMessage("Player and Dealer have blackjack, push"));
			}
			else if (thisGame.getDealerHandValue() == 21)
			{
				thisGame.subtractFromStack(thisGame.getWager());
				thisGame.setWager(0);
				thisGame.sendMessage(new TextMessage("Dealer has blackjack, player loses"));
			}
			else
			{
				nextState = new GetPlayerActionState();
				//game.setState(new GetPlayerActionState());
			}
			game.setState(nextState);
		}
		
	}

	@Override
	public String toString()
	{
		return "InitialState";
	}

}
