package net.remgant.games.framework;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import net.remgant.games.framework.textui.TextUI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PropertiesTest
{
	private TestTextUI textUI;
	private MockAction action;
	private Game game;
	
	class TestTextUI extends TextUI
	{
		@Override
		protected void printGameState()
		{			
		}
		public Map<String,String> processProperties(String s)
		{
			return getProperties(s);
		}
		
	}
	
	@SuppressWarnings("serial") 
	class TestGame extends Game
	{
		@Override
		public void saveGame(String fileName) throws GameException
		{
		}
		@Override
		public void restoreGame(String fileName) throws GameException
		{
		}
	}
	
	@Before
	public void setup()
	{
		textUI = new TestTextUI();
		game = new TestGame();
		action = new MockAction();
	}
	 
	@After 
	public void cleanup()
	{
		textUI = null;
		game = null;
		action =  null;
	}
	
	@Test
	public void testSingleProperty() throws Exception
	{
		Map<String,String> map = 
			textUI.processProperties("test StringAttribute=aaaa");
		assertNotNull("StringAttribute not in map",map.get("StringAttribute"));
		action.getActionInput(game,map);
		assertNotNull("stringAttribute is null",action.getStringAttribute());
		assertEquals("stringAttribute not set correctly","aaaa",action.getStringAttribute());
	}
	
	@Test
	public void testMultipleProperties() throws Exception
	{
		Map<String,String> map = 
			textUI.processProperties("test IntAttribute=999;StringAttribute=aaaa;BooleanAttribute=true");
		assertNotNull("IntAttribute not in map",map.get("IntAttribute"));
		assertNotNull("StringAttribute not in map",map.get("StringAttribute"));
		assertNotNull("BooleanAttribute not in map",map.get("BooleanAttribute"));
		action.getActionInput(game,map);
		assertEquals("intAttribute not set",999,action.getIntAttribute());
		assertNotNull("stringAttribute is null",action.getStringAttribute());
		assertEquals("stringAttribute not set correctly","aaaa",action.getStringAttribute());
		assertTrue("booleanAttribute not set correctly",action.isBooleanAttribute());
	}
	
	@Test
	public void testNullProperty() throws Exception
	{
		Map<String,String> map = 
			textUI.processProperties("test aaaa");
		assertNotNull("default attribute not in map",map.get(null));
		action.getActionInput(game,map);
		assertNotNull("stringAttribute is null",action.getStringAttribute());
		assertEquals("stringAttribute not set correctly","aaaa",action.getStringAttribute());
	}
	
	@Test
	public void testDefaultIntProperty() throws Exception
	{
		Map<String,String> map = 
			textUI.processProperties("test 123");
		assertNotNull("default attribute not in map",map.get(null));
		IntAction intAction = new IntAction();
		intAction.getActionInput(game,map);
		assertEquals("intAttribute not set",123,intAction.getIntAttribute());
	}
	
	@Test 
	public void testDummyAttribute() throws Exception
	{	
		Map<String,String> map = 
		textUI.processProperties("test DummyAttribute=xxxx");
		assertNotNull("default attribute not in map",map.get("DummyAttribute"));
		action.getActionInput(game,map);
		assertNull("dummyAttribute is not null",action.getDummyAttribute());
	}
	
	@Test
	public void testObjectProperty() throws Exception
	{
		Map<String,String> map = 
			textUI.processProperties("test ObjectAttribute=1,true,abc");
		ObjectAction objectAction = new ObjectAction();
		assertNotNull("attribute not in map",map.get("ObjectAttribute"));
		objectAction.getActionInput(game,map);
		assertNotNull("object not set",objectAction.getObjectAttribute());
		MockObject testObject = objectAction.getObjectAttribute();
		assertEquals("a not set correctly",1,testObject.getA());
		assertTrue("b not set correctly",testObject.isB());
		assertNotNull("c is null",testObject);
		assertEquals("c not set correctly","abc",testObject.getC());
	}
	
	@Test
	public void testDateAttribute() throws Exception
	{
		Map<String,String> map = 
			textUI.processProperties("test DateAttribute=2007/01/22");
		assertNotNull("DateAttribute not in map",map.get("DateAttribute"));
		action.getActionInput(game,map);
		assertNotNull("dateAttribute is null",action.getDateAttribute());
		Date expected = new GregorianCalendar(2007,0,22).getTime();
		assertEquals("dateAttribute not set correctly",expected,action.getDateAttribute());
	}
	
	@Test
	public void testEnumAttribute() throws Exception
	{
		Map<String,String> map = 
			textUI.processProperties("test EnumAttribute=aaa");
		assertNotNull("EnumAttribute not in map",map.get("EnumAttribute"));
		action.getActionInput(game,map);
		assertNotNull("enumAttribute is null",action.getEnumAttribute());
		assertEquals("enumAttribute not set correctly", MockEnum.AAA,action.getEnumAttribute());
	}
			
}
