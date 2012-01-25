package net.remgant.games.framework;

public class ObjectAction extends Action
{
	@ActionAttribute
	private int intAttribute;
	@ActionAttribute
	@ObjectFactoryMethod("createObject")
	private MockObject objectAttribute;
	public int getIntAttribute()
	{
		return intAttribute;
	}
	public void setIntAttribute(int intAttribute)
	{
		this.intAttribute = intAttribute;
	}
	public MockObject getObjectAttribute()
	{
		return objectAttribute;
	}
	public void setObjectAttribute(MockObject objectAttribute)
	{
		this.objectAttribute = objectAttribute;
	}
	
}
