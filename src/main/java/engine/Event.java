package engine;

public class Event
{
	private Object _eventSource;
	private double _eventTime;
	private String _eventType;
	private Object _object;
	private boolean _isValid;

	
	public Event(double _time, Object _source, String _type, Object _obj)
	{
		setTime(_time);
		setSource(_source);
		setType(_type);
		setObject(_obj);
		setValidity(true);
	}	
	public Event(double _time, Object _source, String _type)
	{
		this(_time, _source, _type, null);		
	}
	
	public void setTime(double _time)
	{
		_eventTime = _time;
	}
	
	public void setSource(Object _source)
	{
		_eventSource = _source;
	}
	
	public void setType(String _type)
	{
		_eventType = _type;
	}
	
	public void setObject(Object _obj)
	{
		_object = _obj;
	}

	public Object getSource()
	{
		return(_eventSource);
	}
	
	public double getTime()
	{
		return(_eventTime);
	}
	
	public String getType()
	{
		return(_eventType);
	}
	
	public Object getObject()
	{
		return(_object);
	}
	
	public void setValidity(boolean _valid)
	{
		_isValid = _valid;
	}
	
	public boolean getValidity()
	{
		return _isValid;
	}
}