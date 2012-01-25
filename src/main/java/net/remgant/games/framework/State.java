package net.remgant.games.framework;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

abstract public class State implements Serializable
{
	protected List<Action> availableActions;
	abstract public boolean requiresInput();
	abstract public void applyAction(Game game,Action action);
	protected State nextState;
	public State getNextState()
	{
		return nextState;
	}
	
	final public List<Action> getAvailableActions()
	{
		return availableActions;
	}

	final public boolean isActionAvailable(String cmdStr)
	{
		return (getAction(cmdStr) != null);
	}

	final public Action getAction(String cmdStr)
	{
		final String s = cmdStr;
		Action t = new Action(){@Override @SuppressWarnings("unused")
			public String getShortDescription(){return s;}};
		Comparator<Action> c = new Comparator<Action>(){
			public int compare(Action a1, Action a2)
			{
				return a1.getShortDescription().compareTo(a2.getShortDescription());
			}};
		for (Action a : availableActions)
		{
			if (c.compare(t,a) == 0)
				return a;
		}
		return null;
	}

	public Action getDefaultAction()
	{
		return null;
	}
	@Override
	public String toString()
	{
		return this.getClass().getName().replaceAll(".*\\.","");
	}
	
	
}
