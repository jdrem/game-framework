package net.remgant.games.framework.actions;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.ActionAttribute;
import net.remgant.games.framework.DefaultAttribute;

@DefaultAttribute("saveBeforeExit")
public class QuitGameAction extends Action
{
	@ActionAttribute
	private boolean saveBeforeExit;
	public QuitGameAction()
	{
		super("quit_game","Quit current game");
	}
	public boolean isSaveBeforeExit()
	{
		return saveBeforeExit;
	}
	public void setSaveBeforeExit(boolean saveBeforeExit)
	{
		this.saveBeforeExit = saveBeforeExit;
	}
	
}
