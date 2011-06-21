package mcomponent.queue;

import mcomponent.distribution.Entity;
import statistic.AnalysisTool;
import animation.AnimationLine;
import animation.AnimationQueueHandler;
import engine.SimThread;

public class PriorityQueue extends Queue implements MessageQueue, AnimationQueueHandler {
    private PriorityModel _priorityModel;
    private AnimationLine sendAnimationLine = null;
    private AnimationLine receiveAnimationLine = null;

    public PriorityQueue() {
        super();
    }

    public PriorityQueue(boolean _withExit) {
        super(_withExit);
    }

    public PriorityQueue(int _length) {
        super(_length);
    }

    public PriorityQueue(int _length, boolean _withExit) {
        super(_length, _withExit);
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
        return _temp;
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
