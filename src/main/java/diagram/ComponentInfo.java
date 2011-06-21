package diagram;

import java.util.*;
/* This class store the component Class Name and Type of its corressponding diagram shape*/
public class ComponentInfo
{
	private String _shapeName;
	private String _componentName;
	private String _componentType;
	private String _getConstructorCodeMethod;
	private String[] _setMode;
	private int _count;

	public ComponentInfo(String sName, String coName, String getConstructorCodeMethod, String[] setMode, String cType)
	{
		setShapeName(sName);
		setComponentName(coName);
		setComponentType(cType);
		setGetConstructorCodeMethod(getConstructorCodeMethod);
		setSetMode(setMode);
		setCount(0);
	}

	public void setShapeName(String sName) 
	{
		_shapeName = sName;
	}

	public void setComponentName(String coName)
	{
		_componentName = coName;
	}

	public void setComponentType(String cType)
	{
		_componentType = cType;
	}
	
	public void setGetConstructorCodeMethod(String cMethod)
	{	
		_getConstructorCodeMethod = cMethod;
	}
	
	public void setSetMode(String[] sMode)
	{
		_setMode = sMode;
	}

	public void setCount(int count)
	{
		_count = count;
	}

	public String getShapeName() 
	{
		return _shapeName;
	}

	public String getComponentName()
	{
		return _componentName;
	}

	public String getComponentType()
	{
		return _componentType;
	}
	
	public String getGetConstructorCodeMethod()
	{
		return _getConstructorCodeMethod;
	}
	
	public String[] getSetMode()
	{
		return _setMode;
	}

	public int getCount()
	{
		return _count;
	}
	

	public void incrementCount()
	{
		_count++;
	}
	
}
