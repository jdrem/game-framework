package net.remgant.games.twentyone.states;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.Game;
import net.remgant.games.framework.State;
import net.remgant.games.framework.actions.NullAction;
import net.remgant.games.twentyone.TwentyOneGame;

@SuppressWarnings("serial")
public class EndOfHandState extends State
{

	@Override
	public boolean requiresInput()
	{
		// TODO Auto-generated method stub
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
		thisGame.clearHands();
		game.setState(new InitialState());
	}

	@Override
	public String toString()
	{
		return "EndOfHandState";
	}

	
}
