package net.remgant.games.framework;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Game 
{
	protected State state;
	protected PrintStream writer;
	protected BufferedReader reader;
	protected List<MessageListener> messageListenerList;
	
	protected Game()
	{
		messageListenerList = new ArrayList<MessageListener>();
	}
	
	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public BufferedReader getReader()
	{
		return reader;
	}

	public void setReader(BufferedReader reader)
	{
		this.reader = reader;
	}

	public PrintStream getWriter()
	{
		return writer;
	}

	public void setWriter(PrintStream writer)
	{
		this.writer = writer;
	}

	protected void saveGame(ObjectOutputStream oos) throws GameException
	{
		try
		{
			oos.writeObject(state);
		}
		catch(Exception e)
		{
			throw new GameException(e);
		}
	}
	abstract public void saveGame(String fileName) throws GameException;
		
	protected void restoreGame(ObjectInputStream ois) throws GameException
	{
		try
		{
			state = (State)ois.readObject();
		}
		catch (Exception e)
		{
			throw new GameException(e);
		}
	
	}
	
	abstract public void restoreGame(String fileName) throws GameException;
	
	
	public void sendMessage(Message message)
	{
		for (MessageListener ml : messageListenerList)
		{
			ml.onMessage(message);
		}
	}

	public void addMessageListener(MessageListener messageListener)
	{
		messageListenerList.add(messageListener);
	}
}
