package net.remgant.games.framework.actions;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.ActionAttribute;

public class SaveGameAction extends Action
{
	@ActionAttribute
	protected String fileName;
	
	public SaveGameAction()
	{
		super("save_game","Save current game to a file");
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
