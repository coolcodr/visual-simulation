package mcomponent.queue;
import engine.*;
import statistic.*;
import engine.eventhandler.*;
import mcomponent.distribution.*;
import animation.*;

public class TimeOutLIFO extends Queue implements MessageQueue,TimeEventHandler, AnimationQueueHandler
{
	private Distribution _distribution;
	private AnimationLine sendAnimationLine = null;
	private AnimationLine receiveAnimationLine = null;	
	
	public TimeOutLIFO()
	{
		super();
	}

	public TimeOutLIFO(String _id) {
		super(_id);
	}

	public TimeOutLIFO(String _id, int _length) {
		super(_id, _length);
	}

	
	public TimeOutLIFO(boolean _withExit)
	{
		super(_withExit);
	}
	
	public TimeOutLIFO(int _length)
	{
		super(_length);
	}
	
	public TimeOutLIFO(int _length, boolean _withExit)
	{
		super(_length, _withExit);
	}
	
	public void setDistribution(Distribution _d)
	{
		_distribution = _d;
	}
	
	public void enqueue(Object _object)
	{
		QueueNode _qNode = new QueueNode(_object);
		
		if (isEmpty())
		{
			setHead(_qNode);
			setTail(_qNode);
		}
		else
		{
			_qNode.setNext(getHead());
			setHead(_qNode);
		}
	}
	
	public Object dequeue()
	{
		return super.dequeue();
	}
	
	public void receive(Object _object)
	{
		receive(_object, true);
	}
	
	public synchronized void receive(Object _object, boolean _wait)
	{
		notifyAll();
		
		if (isFull())
		{
			sendToExit(_object);
		}
		else if ((!isEmpty()) && (!_wait))
		{
			sendToExit(_object);
		}
		else
		{
			processAddTimeEvent(_object);
			if(AnimationLine.getAnimationStatus())
				triggerReceiveAnimation(_object);				
			enqueue(_object);
			
			AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Input", SimThread.getSimSystemData().getCurrentTime());
		}
		
		display();
	}
	
	public synchronized Object send()
	{
		while (isEmpty())
		{
			SimThread.getSimSystemData().decrementActiveCount();
			try{
				wait();
			} catch (Exception _e) {}
			
			SimThread.getSimSystemData().incrementActiveCount();
		}
		
		Object _temp = dequeue();
		if(AnimationLine.getAnimationStatus())
			triggerSendAnimation(_temp);		
		AnalysisTool.getAnalysisInfo().addStatisticData(this, _temp, "Output", SimThread.getSimSystemData().getCurrentTime());
		
		SimThread.getSimSystemData().searchEvent(this, _temp).setValidity(false);
		
		display();
		
		return _temp;
	}
	
	public Object exit(Object _object)
	{
		if (isEmpty())
			return null;
		
		QueueNode _trace = getHead();
		QueueNode _tracePrevious = null;
		
		while ((_trace.getObject() != _object) && _trace != getTail().getNext())
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
			
			display();
			
			return _trace.getObject();
		}
	}
	
	public void processAddTimeEvent(Object _object)
	{
		double _outTime = _distribution.getNextValue();
		SimThread.getSimSystemData().addEvent(new Event(_outTime + SimThread.getSimSystemData().getCurrentTime(), this, "Time", _object));
	}
	
	public void processTimeEvent(){}
	
	public void processTimeEvent(Event _event)
	{
		sendToExit(exit(_event.getObject()));
	}
	public void setSendAnimationLine(AnimationLine al) {
		sendAnimationLine = al;
	}
	
	public void setReceiveAnimationLine(AnimationLine al) {
		receiveAnimationLine = al;
	}
	
	public void triggerSendAnimation(Object object) {
		sendAnimationLine.start((Entity)object);
	}

	public void triggerReceiveAnimation(Object object) {
		receiveAnimationLine.start((Entity)object);
	}		
}