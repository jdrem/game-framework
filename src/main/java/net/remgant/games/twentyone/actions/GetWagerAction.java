package net.remgant.games.twentyone.actions;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.ActionAttribute;
import net.remgant.games.framework.DefaultAttribute;

@DefaultAttribute("wager")
public class GetWagerAction extends Action
{
	@ActionAttribute
	int wager;

	public GetWagerAction()
	{
		super("get_wager", "Get amount of wager from player");
	}

	public int getWager()
	{
		return wager;
	}

	public void setWager(int wager)
	{
		this.wager = wager;
	}
	
	
}
