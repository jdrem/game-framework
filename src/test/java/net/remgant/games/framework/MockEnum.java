package net.remgant.games.framework;

import java.util.HashMap;
import java.util.Map;

@StringToEnumMap("stringToEnum")
public enum MockEnum
{
	AAA(1,"aaa"),
	BBB(2,"bbb"),
	CCC(3,"ccc");
	
	private int code;
	private String description;
	MockEnum(int code, String description)
	{
		this.code = code;
		this.description = description;
	}
	public int code(){return code;}
	public String description(){return description;}

	public static Map<String,MockEnum> stringToEnum = new HashMap<String,MockEnum>();
	static
	{
		MockEnum values[] = MockEnum.values();
		for (int i=0; i<values.length; i++)
			stringToEnum.put(values[i].description(),values[i]);
	}
}
