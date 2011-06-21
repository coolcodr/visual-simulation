package mcomponent.queue;
import engine.*;
import statistic.*;
import animation.*;
import mcomponent.distribution.*;

public class MessageQueueFIFO extends Queue implements MessageQueue, AnimationQueueHandler {
//modified by matthew
//animation feature		
	private AnimationLine sendAnimationLine = null;
	private AnimationLine receiveAnimationLine = null;
	
	public MessageQueueFIFO() {
		super();
	}
	
	public MessageQueueFIFO(String _id) {
		super(_id);
	}
	
	public MessageQueueFIFO(boolean _withExit) {
		super(_withExit);
	}
	
	public MessageQueueFIFO(int _length) {
		super(_length);
	}
	
	public MessageQueueFIFO(String _id, boolean _withExit) {
		super(_id, _withExit);
	}
	
	public MessageQueueFIFO(String _id, int _length) {
		super(_id, _length);
	}
	
	public MessageQueueFIFO(int _length, boolean _withExit) {
		super(_length, _withExit);
	}
	
	public MessageQueueFIFO(String _id, int _length, boolean _withExit) {
		super(_id, _length, _withExit);
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
	
	public void receive(Object _object, boolean _wait) {
		receive(_object, _wait, true);
	}
	
//modified by matthew
//animation feature
	public synchronized void receive(Object _object, boolean _wait, boolean animationOn) {		
		notifyAll();
		if (isFull()) {
			sendToExit(_object);
		}
		else if ((!isEmpty()) && (!_wait)) {
			sendToExit(_object);
		}
		else {
			if(AnimationLine.getAnimationStatus())
				triggerReceiveAnimation(_object);
			enqueue(_object);
			System.out.println("Queue:["+this.toString()+"] <<<<"+_object);
			AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Input", SimThread.getSimSystemData().getCurrentTime());
		}
	}
	
//modified by matthew
//animation feature
	public synchronized Object send() {
		
		while (isEmpty()) {
			SimThread.getSimSystemData().decrementActiveCount();
			try {
				wait();
			} catch (Exception _e) {}
			
			SimThread.getSimSystemData().incrementActiveCount();
		}
		Object _temp = dequeue();
		if(AnimationLine.getAnimationStatus())
			triggerSendAnimation(_temp);
		System.out.println("Queue:["+this.toString()+"] >>>>"+_temp);
		AnalysisTool.getAnalysisInfo().addStatisticData(this, _temp, "Output", SimThread.getSimSystemData().getCurrentTime());
		return  _temp;
	}
	
//modified by matthew
//animation feature	
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

