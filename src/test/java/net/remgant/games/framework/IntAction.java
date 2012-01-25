package net.remgant.games.framework;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.ActionAttribute;
import net.remgant.games.framework.DefaultAttribute;

@DefaultAttribute("intAttribute")
public class IntAction extends Action
{
	@ActionAttribute
	private int intAttribute;

	public int getIntAttribute()
	{
		return intAttribute;
	}

	public void setIntAttribute(int intAttribute)
	{
		this.intAttribute = intAttribute;
	}
	
}
