package diagram;

/* This class store the component(object) Name and Type of its corressponding diagram shape's ID*/
public class NameIDLink
{
	private int _id;
	private String _componentName;
	private String _componentType;

	public NameIDLink(int id, String coName, String cType)
	{
		setId(id);
		setComponentName(coName);
		setComponentType(cType);
	}

	public void setId(int id) 
	{
		_id = id;
	}

	public void setComponentName(String coName)
	{
		_componentName = coName;
	}

	public void setComponentType(String cType)
	{
		_componentType = cType;
	}

	public int getId() 
	{
		return _id;
	}

	public String getComponentName()
	{
		return _componentName;
	}

	public String getComponentType()
	{
		return _componentType;
	}

	
}
