package net.remgant.games.framework;

import java.util.Date;

@DefaultAttribute("stringAttribute")
public class MockAction extends Action
{
	@ActionAttribute
	private String stringAttribute;
	@ActionAttribute
	private int intAttribute;
	@ActionAttribute
	private boolean booleanAttribute;
	@ActionAttribute
	private byte byteAttribute;
	@ActionAttribute
	private char charAttribute;
	@ActionAttribute
	private short shortAttribute;
	@ActionAttribute
	private long longAttribute;
	@ActionAttribute
	private float floatAttribute;
	@ActionAttribute
	private double doubleAttribute;
	@ActionAttribute
	private Date dateAttribute;
	@ActionAttribute
	private MockEnum enumAttribute;
	private String dummyAttribute;
	
	public int getIntAttribute()
	{
		return intAttribute;
	}
	public void setIntAttribute(int intAttribute)
	{
		this.intAttribute = intAttribute;
	}
	public String getStringAttribute()
	{
		return stringAttribute;
	}
	public void setStringAttribute(String stringAttribute)
	{
		this.stringAttribute = stringAttribute;
	}
	public boolean isBooleanAttribute()
	{
		return booleanAttribute;
	}
	public void setBooleanAttribute(boolean booleanAttribute)
	{
		this.booleanAttribute = booleanAttribute;
	}
	public byte getByteAttribute()
	{
		return byteAttribute;
	}
	public void setByteAttribute(byte byteAttribute)
	{
		this.byteAttribute = byteAttribute;
	}
	public char getCharAttribute()
	{
		return charAttribute;
	}
	public void setCharAttribute(char charAttribute)
	{
		this.charAttribute = charAttribute;
	}
	public double getDoubleAttribute()
	{
		return doubleAttribute;
	}
	public void setDoubleAttribute(double doubleAttribute)
	{
		this.doubleAttribute = doubleAttribute;
	}
	public float getFloatAttribute()
	{
		return floatAttribute;
	}
	public void setFloatAttribute(float floatAttribute)
	{
		this.floatAttribute = floatAttribute;
	}
	public long getLongAttribute()
	{
		return longAttribute;
	}
	public void setLongAttribute(long longAttribute)
	{
		this.longAttribute = longAttribute;
	}
	public short getShortAttribute()
	{
		return shortAttribute;
	}
	public void setShortAttribute(short shortAttribute)
	{
		this.shortAttribute = shortAttribute;
	}
	public String getDummyAttribute()
	{
		return dummyAttribute;
	}
	public void setDummyAttribute(String dummyAttribute)
	{
		this.dummyAttribute = dummyAttribute;
	}
	public Date getDateAttribute()
	{
		return dateAttribute;
	}
	public void setDateAttribute(Date dateAttribute)
	{
		this.dateAttribute = dateAttribute;
	}
	public MockEnum getEnumAttribute()
	{
		return enumAttribute;
	}
	public void setEnumAttribute(MockEnum enumAttribute)
	{
		this.enumAttribute = enumAttribute;
	}
	
}
