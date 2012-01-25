package net.remgant.games.framework;

public class TextMessage extends Message
{
	protected String messageText;
	public TextMessage()
	{
		messageText = "";
	}
	public TextMessage(String messageText)
	{
		this.messageText = messageText;
	}
	
	@Override
	public String toString()
	{
		return messageText;
	}
}
