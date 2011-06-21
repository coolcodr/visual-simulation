package mcomponent.distribution;

import mcomponent.queue.*;

public class FoodList extends Queue
{
	static public String _FoodOrder = new String("FoodOrder");
	static public String _Food = new String("Food");
	static public String _DrinkOrder = new String("DrinkOrder");
	static public String _Drink = new String("Drink");
	
	public Object delete(Object _object)
	{
		if (isEmpty())
			return null;
		
		QueueNode _trace = getHead();
		QueueNode _tracePrevious = null;
		
		while ((_trace != getTail().getNext()) && (_trace.getObject() != _object))
		{
			_tracePrevious = _trace;
			_trace = _trace.getNext();
		}
		
		if (_trace == getTail().getNext())
			return null;
		else
		{
			if (getLength() == 1)
			{
				setHead(null);
				setTail(null);
			}
			else if (_trace == getHead())
			{
				setHead(_trace.getNext());
			}
			else if (_trace == getTail())
			{
				setTail(_tracePrevious);
			}
			else
			{
				_tracePrevious.setNext(_trace.getNext());
			}
	
			return _trace.getObject();
		}
	}
	
	public int search(Object _object)
	{
		int _count = 1;
		int _show = 0;
		
		if (isEmpty())
			return -1;
		
		QueueNode _trace = getHead();
		QueueNode _tracePrevious = null;
		
		while ((_trace != getTail().getNext()) && (_trace.getObject() != _object))
		{
			_tracePrevious = _trace;
			_trace = _trace.getNext();
			_count++;
		}
		
		if (_trace == getTail().getNext())
			return -1;
		else
			return _count;
	}
}