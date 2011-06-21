package mcomponent.distribution;

abstract public class Distribution {	
	private	Object _object;
	
	public void setObject(Object _obj)  {
		_object = _obj;
	}
	
	public abstract double getNextValue();
}