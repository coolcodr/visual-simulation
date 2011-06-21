package mcomponent.queue;

import engine.*;
import statistic.*;
import engine.eventhandler.*;
import mcomponent.distribution.*;
import animation.*;

public class TimeOutFIFO extends Queue implements MessageQueue, TimeEventHandler, AnimationQueueHandler{
	
	private Distribution _distribution;
	
	private AnimationLine sendAnimationLine = null;
	private AnimationLine receiveAnimationLine = null;	
	
	public TimeOutFIFO() {
		super();
	}

	public TimeOutFIFO(String _id) {
		super(_id);
	}

	public TimeOutFIFO(String _id, int _length) {
		super(_id, _length);
	}

	public TimeOutFIFO(boolean _withExit) {
		super(_withExit);
	}
	
	public TimeOutFIFO(int _length) {
		super(_length);
	}
	
	public TimeOutFIFO(int _length, boolean _withExit) {
		super(_length, _withExit);
	}
	
	public void setDistribution(Distribution _d) {
		_distribution = _d;
	}
	
	public void enqueue(Object _object) {
		super.enqueue(_object);
	}
	
	public Object dequeue() {
		return super.dequeue();
	}
	
	public void receive(Object _object) {
		receive(_object, true);
	}
	
	public synchronized void receive(Object _object, boolean _wait) {
		notifyAll();
		
		if ((!isEmpty() && !_wait) || (isFull())) {
			sendToExit(_object);
		}
		else {
			processAddTimeEvent(_object);
			if(AnimationLine.getAnimationStatus())
				triggerReceiveAnimation(_object);			
			enqueue(_object);
			
			AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Input", SimThread.getSimSystemData().getCurrentTime());
		}
	}
	
	public synchronized Object send() {
		while (isEmpty()) {
			SimThread.getSimSystemData().decrementActiveCount();
			
			try {
				wait();
			}
			catch (Exception _e) {
			}
			
			SimThread.getSimSystemData().incrementActiveCount();
		}
		
		QueueNode _qNode = getHead();
		setHead(getHead().getNext());
		
		AnalysisTool.getAnalysisInfo().addStatisticData(this, _qNode.getObject(), "Output", SimThread.getSimSystemData().getCurrentTime());
		
		SimThread.getSimSystemData().searchEvent(this, _qNode.getObject()).setValidity(false);
		if(AnimationLine.getAnimationStatus())
			triggerSendAnimation(_qNode.getObject());		
		return _qNode.getObject();
	}
	
	public Object exit(Object _object) {
		if (isEmpty())
			return null;
		
		QueueNode _trace = getHead();
		QueueNode _tracePrevious = null;
		
		while ((_trace.getObject() != _object) && _trace != getTail().getNext()) {
			_tracePrevious = _trace;
			_trace = _trace.getNext();
		}
		
		if (_trace == getTail().getNext())
			return null;
		else {
			if (getLength() == 1) {
				setHead(null);
				setTail(null);
			}
			else if (_trace == getHead()) {
				setHead(_trace.getNext());
			}
			else if (_trace == getTail()) {
				setTail(_tracePrevious);
			}
			else {
				_tracePrevious.setNext(_trace.getNext());
			}
	
			return _trace.getObject();
		}
	}
	
	public void processAddTimeEvent(Object _object) {
		double _outTime = _distribution.getNextValue();
		SimThread.getSimSystemData().addEvent(new Event(_outTime + SimThread.getSimSystemData().getCurrentTime(), this, "Time", _object));
	}
	
	public void processTimeEvent() {
	}
	
	public void processTimeEvent(Event _event) {
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