package net.remgant.games.twentyone.states;

import java.util.ArrayList;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.Game;
import net.remgant.games.framework.State;
import net.remgant.games.framework.TextMessage;
import net.remgant.games.twentyone.TwentyOneGame;
import net.remgant.games.twentyone.actions.GetPlayerAction;

@SuppressWarnings("serial")
public class GetPlayerActionState extends State
{
	public GetPlayerActionState()
	{
		availableActions = new ArrayList<Action>();
		availableActions.add(new GetPlayerAction());
	}
	@Override
	public boolean requiresInput()
	{
		return true;
	}
	
	
	@Override
	public void applyAction(Game game, Action action)
	{
		GetPlayerAction playerAction = (GetPlayerAction)action;
		String a = playerAction.getPlayerAction();
		playerAction.setPlayerAction(null);
		TwentyOneGame thisGame = (TwentyOneGame)game;
		nextState = this;
		if (a.equals("stand"))
		{
			game.setState(new ProcessDealerHandState());
			return;
		}
		if (a.equals("hit"))
		{
			thisGame.addToPlayerHand(thisGame.getCardFromDeck());
			if (thisGame.getPlayerHandValue() == 21)
			{
				game.setState(new ProcessDealerHandState());
				return;
			}
			if (thisGame.getPlayerHandValue() > 21 && !thisGame.isPlayerHandSoft())
			{
				thisGame.subtractFromStack(thisGame.getWager());
				thisGame.setWager(0);
				thisGame.sendMessage(new TextMessage("Plaer busted"));
				game.setState(new EndOfHandState());
				return;
			}
			else
			{
				return;
			}
		}
		
	}
	@Override
	public String toString()
	{
		return "GetPlayerActionState";
	}
	

}
