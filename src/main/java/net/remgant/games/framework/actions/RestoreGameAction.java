package net.remgant.games.framework.actions;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.ActionAttribute;

public class RestoreGameAction extends Action
{
	@ActionAttribute
	protected String fileName;
	
	public RestoreGameAction()
	{
		super("restore_game","Restore current game from a file");
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}


}
