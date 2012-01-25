package net.remgant.games.twentyone;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.remgant.games.framework.Game;
import net.remgant.games.framework.GameException;
import net.remgant.games.twentyone.states.InitialState;

public class TwentyOneGame extends Game
{
	private List<Card> dealerHand;
	private List<Card> playerHand;
	private Card deck[];
	private int playerStack;
	private int wager;
	private String playerAction;
	protected TwentyOneGame()
	{
		super();
		state = new InitialState();
		random = new Random();
		deck = new Card[]{Card.KING,Card.QUEEN,Card.JACK,Card.TEN,Card.NINE,Card.EIGHT,
				Card.SEVEN,Card.SIX,Card.FIVE,Card.FOUR,Card.THREE,Card.DEUCE,Card.ACE};
		dealerHand = new ArrayList<Card>();
		playerHand = new ArrayList<Card>();
		playerAction = "";
		playerStack = 250;
	}

	
	private Random random;
	public Card getCardFromDeck()
	{
		return deck[random.nextInt(deck.length)];
	}
	
	public void dealHand()
	{
		addToDealerHand(getCardFromDeck());
		addToDealerHand(getCardFromDeck());
		addToPlayerHand(getCardFromDeck());
		addToPlayerHand(getCardFromDeck());
	}
	
	public void clearHands()
	{
		dealerHand.clear();
		playerHand.clear();
	}
	public void addToDealerHand(Card c)
	{
		dealerHand.add(c);
	}
	public void addToPlayerHand(Card c)
	{
		playerHand.add(c);
	}
	public int getPlayerHandValue()
	{
		return getHandValue(playerHand);
	}
	public int getDealerHandValue()
	{
		return getHandValue(dealerHand);
	}
	protected int getHandValue(List<Card> l)
	{
		int lowValue=0;
		int highValue=0;
		for (Card c : l)
		{
			lowValue += c.lowValue();
			highValue += c.highValue();
		}
		if (highValue > 21 && lowValue <= 21)
			return lowValue;
		else
			return highValue;
	}
	public boolean isDealerHandSoft()
	{
		int lowValue=0;
		int highValue=0;
		for (Card c : dealerHand)
		{
			lowValue += c.lowValue();
			highValue += c.highValue();
		}
		if (lowValue < highValue && lowValue <= 21)
			return true;
		else
			return false;
	}

	public List<Card> getDealerHand()
	{
		return dealerHand;
	}

	public List<Card> getPlayerHand()
	{
		return playerHand;
	}

	public String getPlayerAction()
	{
		return playerAction;
	}

	public void setPlayerAction(String playerAction)
	{
		this.playerAction = playerAction;
	}

	public int getPlayerStack()
	{
		return playerStack;
	}

	public void setPlayerStack(int playerStack)
	{
		this.playerStack = playerStack;
	}

	public int getWager()
	{
		return wager;
	}
	public void setWager(int wager)
	{
		this.wager = wager;	
	}

	public void addToStack(int wager)
	{
		playerStack += wager;
		
	}

	public void subtractFromStack(int wager)
	{
		playerStack -= wager;
		
	}

	public boolean isPlayerHandSoft()
	{
		int lowValue=0;
		int highValue=0;
		for (Card c : playerHand)
		{
			lowValue += c.lowValue();
			highValue += c.highValue();
		}
		if (lowValue < highValue && lowValue <= 21)
			return true;
		else
			return false;
	}

	@Override
	public void saveGame(String fileName) throws GameException
	{
		ObjectOutputStream oos;
		try
		{
			oos = new ObjectOutputStream(new FileOutputStream(fileName));
		}
		catch (IOException ioe)
		{
			throw new GameException(ioe);
		}
		
		super.saveGame(oos);
	}

	@Override
	public void restoreGame(String fileName) throws GameException
	{
		ObjectInputStream ois;
		try
		{
			ois = new ObjectInputStream(new FileInputStream(fileName));
		}
		catch (IOException ioe)
		{
			throw new GameException(ioe);
		}
		
		super.restoreGame(ois);
		
	}
	
}
