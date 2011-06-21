package mcomponent.queue;

import mcomponent.distribution.Distribution;
import mcomponent.distribution.Entity;
import statistic.AnalysisTool;
import animation.AnimationLine;
import animation.AnimationQueueHandler;
import engine.Event;
import engine.SimThread;
import engine.eventhandler.TimeEventHandler;

public class TimeOutPriorityQueue extends Queue implements MessageQueue, TimeEventHandler, AnimationQueueHandler {
    private PriorityModel _priorityModel;
    private Distribution _distribution;
    private AnimationLine sendAnimationLine = null;
    private AnimationLine receiveAnimationLine = null;

    public TimeOutPriorityQueue() {
        super();
    }

    public TimeOutPriorityQueue(boolean _withExit) {
        super(_withExit);
    }

    public TimeOutPriorityQueue(int _length) {
        super(_length);
    }

    public TimeOutPriorityQueue(int _length, boolean _withExit) {
        super(_length, _withExit);
    }

    public void setDistribution(Distribution _d) {
        _distribution = _d;
    }

    public void setPriorityModel(PriorityModel _pm) {
        _priorityModel = _pm;
    }

    public void enqueue(Object _object) {
        QueueNode _qNode = new QueueNode(_object, _priorityModel.getPriority(_object));

        if (isEmpty()) {
            setHead(_qNode);
            setTail(_qNode);
        } else {
            QueueNode _tracy = getHead();
            QueueNode _tracyParent = null;

            while ((_tracyParent != getTail()) && (_qNode.getPriority() >= _tracy.getPriority())) {
                _tracyParent = _tracy;
                _tracy = _tracy.getNext();
            }

            if (_tracyParent != null) {
                _tracyParent.setNext(_qNode);
            }
            _qNode.setNext(_tracy);

            if (_qNode.getNext() == getHead()) {
                setHead(_qNode);
            }
            if (_tracyParent == getTail()) {
                setTail(_qNode);
            }
        }
    }

    public Object dequeue() {
        return super.dequeue();
    }

    public synchronized void receive(Object _object) {
        receive(_object, true);
    }

    public synchronized void receive(Object _object, boolean _wait) {
        notifyAll();

        if (isFull()) {
            sendToExit(_object);
        } else if ((!isEmpty()) && (!_wait)) {
            sendToExit(_object);
        } else {
            enqueue(_object);
            if (AnimationLine.getAnimationStatus()) {
                triggerReceiveAnimation(_object);
            }
            processAddTimeEvent(_object);

            AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Input", SimThread.getSimSystemData().getCurrentTime());

            notifyAll();
        }
    }

    public synchronized Object send() {
        while (isEmpty()) {
            SimThread.getSimSystemData().decrementActiveCount();
            try {
                wait();
            } catch (Exception _e) {
            }

            SimThread.getSimSystemData().incrementActiveCount();
        }

        Object _temp = dequeue();
        if (AnimationLine.getAnimationStatus()) {
            triggerSendAnimation(_temp);
        }
        AnalysisTool.getAnalysisInfo().addStatisticData(this, _temp, "Output", SimThread.getSimSystemData().getCurrentTime());

        SimThread.getSimSystemData().searchEvent(this, _temp).setValidity(false);

        return _temp;
    }

    public Object exit(Object _object) {
        if (isEmpty()) {
            return null;
        }

        QueueNode _trace = getHead();
        QueueNode _tracePrevious = null;

        while ((_trace.getObject() != _object) && _trace != getTail().getNext()) {
            _tracePrevious = _trace;
            _trace = _trace.getNext();
        }

        if (_trace == getTail().getNext()) {
            return null;
        } else {
            if (getLength() == 1) {
                setHead(null);
                setTail(null);
            } else if (_trace == getHead()) {
                setHead(_trace.getNext());
            } else if (_trace == getTail()) {
                setTail(_tracePrevious);
            } else {
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
        sendAnimationLine.start((Entity) object);
    }

    public void triggerReceiveAnimation(Object object) {
        receiveAnimationLine.start((Entity) object);
    }
}
