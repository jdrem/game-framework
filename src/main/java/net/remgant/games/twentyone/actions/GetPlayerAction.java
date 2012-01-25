package net.remgant.games.twentyone.actions;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.ActionAttribute;
import net.remgant.games.framework.DefaultAttribute;

@DefaultAttribute("playerAction")
public class GetPlayerAction extends Action
{
		@ActionAttribute
		String playerAction;
		
		public GetPlayerAction()
		{
			super("player_action","Get the players action");
		}

		public String getPlayerAction()
		{
			return playerAction;
		}

		public void setPlayerAction(String action)
		{
			this.playerAction = action;
		}
		
		
}
