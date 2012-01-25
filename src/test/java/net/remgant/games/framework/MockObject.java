package net.remgant.games.framework;

public class MockObject
{
	private int a;
	private boolean b;
	private String c;
	public MockObject()
	{
		
	}
	public MockObject(int a, boolean b, String c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public static MockObject createObject(String s)
	{
		String t[] = s.split(",");
		return new MockObject(Integer.parseInt(t[0]),Boolean.parseBoolean(t[1]),t[2]);
	}
	public int getA()
	{
		return a;
	}
	public void setA(int a)
	{
		this.a = a;
	}
	public boolean isB()
	{
		return b;
	}
	public void setB(boolean b)
	{
		this.b = b;
	}
	public String getC()
	{
		return c;
	}
	public void setC(String c)
	{
		this.c = c;
	}
	
	
}
