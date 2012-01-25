package net.remgant.games.twentyone;

public enum Card 
{
	ACE("A",11,1),
	DEUCE("2",2,2),
	THREE("3",3,3),
	FOUR("4",4,4),
	FIVE("5",5,5),
	SIX("6",6,6),
	SEVEN("7",7,7),
	EIGHT("8",8,8),
	NINE("9",9,9),
	TEN("10",10,10),
	JACK("J",10,10),
	QUEEN("Q",10,10),
	KING("K",10,10);
	
	private final String displayName;
	private final int highValue;
	private final int lowValue;
	Card(String displayName,int highValue,int lowValue)
	{
		this.displayName = displayName;
		this.highValue = highValue;
		this.lowValue = lowValue;
	}
	public String displayName(){return displayName;}
	public int lowValue(){return lowValue;}
	public int highValue(){return highValue;}
}
