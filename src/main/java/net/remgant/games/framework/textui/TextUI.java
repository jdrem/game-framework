package net.remgant.games.framework.textui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.remgant.games.framework.Action;
import net.remgant.games.framework.Game;
import net.remgant.games.framework.Message;
import net.remgant.games.framework.MessageListener;
import net.remgant.games.framework.State;


abstract public class TextUI implements MessageListener
{
	enum Cmd 
	{
		CMD_QUIT("quit"),
		CMD_SHOW_STATE("state"),
		CMD_SHOW_AVAILABLE_ACTIONS("actions");
		Cmd(String name)
		{
			this.name = name;
		}
		String name;
		String getName(){return name;}
	}
	
	private BufferedReader in;
	protected Game game;
	protected State state;
	protected List<Message> messageQueue;
	
	private HashMap<String,Cmd> cmdMap;
	private Pattern cmdPtrn;
	protected TextUI() 
	{	
		cmdMap = new HashMap<String,Cmd>();
		for (Cmd e : Cmd.values())
		{
			cmdMap.put(e.getName(),e);
		}
		cmdPtrn = Pattern.compile("\\s*([\\w_]+)(?:\\s.*)?");
		messageQueue = new ArrayList<Message>();
	}
	
	public void run() throws Exception
	{	
		in = new BufferedReader(new InputStreamReader(System.in));
		game.setReader(in);
		game.setWriter(System.out);
		game.addMessageListener(this);
		for (;;)
		{
			printMessages();
			state = game.getState();
			printGameState();
			Action action = null;
			if (state.requiresInput())
			{
				String line = in.readLine();
				Matcher m = cmdPtrn.matcher(line);
				if (!m.matches())
				{
					System.out.println("invalid command(1): "+line);
					continue;
				}
				String cmdStr = m.group(1);
				if (cmdMap.containsKey(cmdStr))
				{	
					Cmd cmd = cmdMap.get(cmdStr);
					switch (cmd)
					{
					case CMD_SHOW_STATE:
						System.out.println(state);
						break;
					case CMD_SHOW_AVAILABLE_ACTIONS:
						List<Action> l = state.getAvailableActions();
						for (Action a : l)
							System.out.print(a.getShortDescription()+" ");
						System.out.println();
						break;
					case CMD_QUIT:
						return;
					}	
				}	
				else if (state.isActionAvailable(cmdStr))
				{
					action = state.getAction(cmdStr);
					action.getActionInput(game,getProperties(line));
				}
				else	
				{	
					System.out.println("invalid command(2): "+cmdStr);
				}
			}
			else
			{
				action = state.getDefaultAction();
				action.getActionInput(game);
			}
			if (action != null)
			{
				state.applyAction(game,action);
				state = state.getNextState();
			}
		}
	}
	
	abstract protected void printGameState();
	
	protected Map<String,String> getProperties(String line)
	{
		Pattern p1 = Pattern.compile("^\\s*\\w+(?:\\s+([\\w=,;/ ]+))?$");
		Matcher m = p1.matcher(line);
			
		if (!m.matches() || m.group(1) == null)
		{
			//System.out.println("no match: "+line);
			return new HashMap<String,String>();
		}
		String props = m.group(1);
		HashMap<String,String> map = new HashMap<String,String>();
		Pattern p2 = Pattern.compile("(?:([A-Za-z_]\\w+)=)?([\\w\\., /\\-]+);?");
		m = p2.matcher(props);
		while (m.find())
		{
			String k = m.group(1);
			String v = m.group(2);
			if (v != null)
				map.put(k,v);
			else
				map.put(null,k);
		}
		return map;
	}

	public void onMessage(Message message)
	{
		synchronized(messageQueue)
		{
			messageQueue.add(message);	
		}
	}
	
	private void printMessages()
	{
		synchronized (messageQueue)
		{
			while (!messageQueue.isEmpty())
				System.out.println(messageQueue.remove(0));
		}
	}
}
