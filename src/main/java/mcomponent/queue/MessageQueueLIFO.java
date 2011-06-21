package mcomponent.queue;

import mcomponent.distribution.Entity;
import statistic.AnalysisTool;
import animation.AnimationLine;
import animation.AnimationQueueHandler;
import engine.SimThread;

public class MessageQueueLIFO extends Queue implements MessageQueue, AnimationQueueHandler {
    private AnimationLine sendAnimationLine = null;
    private AnimationLine receiveAnimationLine = null;

    public MessageQueueLIFO() {
        super();
    }

    public MessageQueueLIFO(String _id) {
        super(_id);
    }

    public MessageQueueLIFO(boolean _withExit) {
        super(_withExit);
    }

    public MessageQueueLIFO(int _length) {
        super(_length);
    }

    public MessageQueueLIFO(String _id, boolean _withExit) {
        super(_id, _withExit);
    }

    public MessageQueueLIFO(String _id, int _length) {
        super(_id, _length);
    }

    public MessageQueueLIFO(int _length, boolean _withExit) {
        super(_length, _withExit);
    }

    public MessageQueueLIFO(String _id, int _length, boolean _withExit) {
        super(_id, _length, _withExit);
    }

    public void enqueue(Object _object) {
        QueueNode _qNode = new QueueNode(_object);

        if (isEmpty()) {
            setHead(_qNode);
            setTail(_qNode);
        } else {
            _qNode.setNext(getHead());
            setHead(_qNode);
        }
    }

    public Object dequeue() {
        return super.dequeue();
    }

    public synchronized void receive(Object _object) {
        receive(_object, true);
    }

//modified by matthew
//animation feature		
    public synchronized void receive(Object _object, boolean _wait) {
        notifyAll();

        if (isFull()) {
            sendToExit(_object);
        } else if ((!isEmpty()) && (!_wait)) {
            sendToExit(_object);
        } else {
            if (AnimationLine.getAnimationStatus()) {
                triggerSendAnimation(_object);
            }
            enqueue(_object);

            AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Input", SimThread.getSimSystemData().getCurrentTime());

            notifyAll();
        }
    }

//modified by matthew
//animation feature		
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

//modified by matthew
//animation feature	
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
