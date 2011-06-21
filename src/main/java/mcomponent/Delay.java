package mcomponent;

import engine.*;
import mcomponent.queue.*;
import mcomponent.distribution.*;
import engine.eventhandler.*;
import statistic.*;

public class Delay extends SimThread implements TimeEventHandler
{
	private MessageQueue _input;
	private MessageQueue _output;
	private Distribution _distribution;
	private Transform _transform;	
	
	public Delay(String _id)
	{
		super(_id);
	}
	
	public void setInput(MessageQueue _mq)
	{
		_input = _mq;
	}
	
	public void setOutput(MessageQueue _mq)
	{
		_output = _mq;
	}
	
	public void setDistribution(Distribution _d)
	{
		_distribution = _d;
	}
	
	public void setTransform(Transform _tf)
	{
		_transform = _tf;
	}
	
	public void processAddTimeEvent(Object _object)
	{
		double _delay = _distribution.getNextValue();
		if(_delay<0)
			_delay=(_delay*-1);
		getSimSystemData().addEvent(new Event(getSimSystemData().getCurrentTime() + _delay, this, "Time", _object));
	}
	
	public void processTimeEvent(){}

	public void processTimeEvent(Event _event)
	{
		_output.receive(_event.getObject());
	}
	
	public void run()
	{
		for (int i = 0; i<20; i++)
		{
			Object _object = _input.send();
			System.out.println("Delay:["+this.getName()+"] <<<<"+_object);

			processAddTimeEvent(_object);
					
		}
		
		//destroy();
	}
}