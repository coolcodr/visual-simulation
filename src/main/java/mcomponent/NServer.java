package mcomponent;

import engine.*;
import mcomponent.queue.*;
import mcomponent.distribution.*;
import engine.eventhandler.*;
import statistic.*;

public class NServer extends SimThread implements TimeEventHandler {
	private Object _input;
	private Object _output;
	private Distribution _distribution;	
	private Transform _transform;
	private int _totalServer;
	private int _serverAvailable;
	
	public NServer(String _id, int _ts) {
		super(_id);
		_totalServer = _ts;
		_serverAvailable = _totalServer;	
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
	
	public void incrementServerAvailable()
	{
		_serverAvailable++;
	}
	
	public void decrementServerAvailable()
	{
		_serverAvailable--;
	}
	
	public void processAddTimeEvent(Object _object)
	{
		double _delay = _distribution.getNextValue();
		if(_delay<0)
			_delay=(_delay*-1);		
		getSimSystemData().addEvent(new Event(getSimSystemData().getCurrentTime() + _delay, this, "Time", _object));
	}
	
	public void processTimeEvent()
	{
	}
	
	public void processTimeEvent(Event _event)
	{
		AnalysisTool.getAnalysisInfo().addStatisticData(this, _event.getObject(), "Output", getSimSystemData().getCurrentTime());
		//add by matthew
		System.out.println("NServer:["+this.getName()+"] >>>>"+_event.getObject());
		
		((MessageQueue)_output).receive(_event.getObject());

		incrementServerAvailable();
		
		getSimThreadSuspendData().cont();
	}
	
	public int getServerAvailable()
	{
		return _serverAvailable;
	}
	public void run()
	{
		while (true)
		{
			if (getServerAvailable()!= 0)
			{
				Object _object = ((MessageQueue)_input).send();
				//add by matthew
				System.out.println("NServer:["+this.getName()+"] <<<<"+_object);
				
				AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Input", getSimSystemData().getCurrentTime());
				
				_transform.setObject(_object);
				_transform.start();
				
				processAddTimeEvent(_object);
				decrementServerAvailable();
			}
			
			if(getServerAvailable() == 0)
			{
				getSimSystemData().decrementActiveCount();
				getSimThreadSuspendData().pause();
				getSimSystemData().incrementActiveCount();
			}
			
		}
		
//		System.out.println("				Server " + _identity + " END SERVICE");
//		destroy();
	}
}