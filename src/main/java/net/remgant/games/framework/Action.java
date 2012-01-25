package net.remgant.games.framework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.remgant.games.framework.ActionProxy;
import net.remgant.games.framework.Game;

abstract public class Action 
{
	private static DateFormat dtFmt = new SimpleDateFormat("yyyy/MM/dd");
	protected String shortDescription;
	protected String longDescription;
	protected Action()
	{
		
	}
	public String getLongDescription()
	{
		return longDescription;
	}

	public String getShortDescription()
	{
		return shortDescription;
	}

	protected Action(String shortDescription, String longDescription)
	{
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
	}

	@SuppressWarnings("unchecked")
	final public void getActionInput(Game game,Map<String,String> properties) throws GameException
	{
		DefaultAttribute defaultAttribute = getClass().getAnnotation(DefaultAttribute.class);
		for (String key : properties.keySet())
		{	
			String fieldName; 
			String methodName;
			if (key == null && defaultAttribute != null)
			{
				fieldName = defaultAttribute.value();
				methodName = "set"+Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1);
			}
			else
			{
				fieldName = Character.toLowerCase(key.charAt(0))+key.substring(1);
				methodName = "set"+key;
			}
			Field f = null;
			try
			{
				f = getClass().getDeclaredField(fieldName);
			}
			catch (NoSuchFieldException nfe)
			{
				System.out.println(nfe);
				continue;
			}
			if (!f.isAnnotationPresent(ActionAttribute.class))
				continue;
			String value = properties.get(key);
			Class fieldType = f.getType();
			Method m = null;
			try
			{
				m = getClass().getMethod(methodName,new Class[]{fieldType});
			}
			catch (NoSuchMethodException nsme)
			{
				System.out.println(nsme);
				continue;
			}
			try
			{
				if (fieldType == int.class)
					m.invoke(this,new Object[]{Integer.parseInt(value)});
				else if (fieldType == String.class)
					m.invoke(this,new Object[]{value});
				else if (fieldType == boolean.class)
					m.invoke(this,new Object[]{Boolean.parseBoolean(value)});
				else if (fieldType == double.class)
					m.invoke(this,new Object[]{Double.parseDouble(value)});
				else if (fieldType == float.class)
					m.invoke(this,new Object[]{Float.parseFloat(value)});
				else if (fieldType == long.class)
					m.invoke(this,new Object[]{Long.parseLong(value)});
				else if (fieldType == short.class)
					m.invoke(this,new Object[]{Short.parseShort(value)});
				else if (fieldType == char.class)
					m.invoke(this,new Object[]{value.charAt(0)});
				else if (fieldType == byte.class)
					m.invoke(this,new Object[]{Byte.parseByte(value)});
				else if (fieldType == java.util.Date.class)
					m.invoke(this,new Object[]{dtFmt.parse(value)});
				else if (Enum.class.isAssignableFrom(fieldType))
				{
					if (!fieldType.isAnnotationPresent(StringToEnumMap.class))
						continue;	
					String mapName = 
						((StringToEnumMap)fieldType.getAnnotation(StringToEnumMap.class)).value();
					Map<String,? extends Enum> map = (Map<String,? extends Enum>)fieldType.getField(mapName).get(null);
					Enum e = map.get(value);
					m.invoke(this,new Object[]{e});
				}
				else
				{
					if (!f.isAnnotationPresent(ObjectFactoryMethod.class))
						continue;
					String objectFactoryMethodName = f.getAnnotation(ObjectFactoryMethod.class).value();
					Method factoryMethod = fieldType.getMethod(objectFactoryMethodName,
							new Class[]{String.class});
					Object o = factoryMethod.invoke(null,new Object[]{value});
					m.invoke(this,new Object[]{o});
				}
			}
			catch (InvocationTargetException ite)
			{
				System.out.println(ite);
			}
			catch (IllegalAccessException iae)
			{
				System.out.println(iae);
			}
			catch (NoSuchMethodException nsme)
			{
				System.out.println(nsme);
			}
			catch (NoSuchFieldException nsf)
			{
				System.out.println(nsf);
			}
			catch (ParseException pe)
			{
				System.out.println(pe);
			}
		}
		ActionProxy proxy = getProxy(this.getClass());
		if (proxy != null)
			proxy.getInput(game,this);
	}
	
	final public void getActionInput(Game game) throws GameException
	{
		ActionProxy proxy = getProxy(this.getClass());
		if (proxy != null)
			proxy.getInput(game,this);
	}
	
	static HashMap<Class,ActionProxy> proxyMap = 
		new HashMap<Class,ActionProxy>();
	protected static ActionProxy getProxy(Class c)
	{
		ActionProxy proxy = (ActionProxy)proxyMap.get(c);
		return proxy;
	}
	public static void setProxy(Class<? extends Action> c,ActionProxy proxy)
	{
		proxyMap.put(c,proxy);
	}
}
