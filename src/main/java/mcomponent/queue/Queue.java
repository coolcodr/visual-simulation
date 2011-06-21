package mcomponent.queue;

import statistic.AnalysisTool;
import engine.SimThread;

abstract public class Queue {

    private String _identity;
    private QueueNode _head;
    private QueueNode _tail;

    private int _limitedLength;
    private MessageQueueFIFO _exit = null;

    public Queue() {
        this("Default Name", -1, true);
    }

    public Queue(String _id) {
        this(_id, -1, true);
    }

    public Queue(boolean _withExit) {
        this("Default Name", -1, _withExit);
    }

    public Queue(int _length) {
        this("Default Name", _length, true);
    }

    public Queue(String _id, boolean _withExit) {
        this(_id, -1, _withExit);
    }

    public Queue(String _id, int _length) {
        this(_id, _length, true);
    }

    public Queue(int _length, boolean _withExit) {
        this("Default Name", _length, _withExit);
    }

    public Queue(String _id, int _length, boolean _withExit) {
        _limitedLength = _length;

        if (_withExit) {
            _exit = new MessageQueueFIFO(false);
        }

        setHead(null);
        setTail(null);
        setIdentity(_id);
    }

    public String getIdentity() {
        return _identity;
    }

    public void setIdentity(String _id) {
        _identity = _id;
    }

    public void setHead(QueueNode _h) {
        _head = _h;
    }

    public void setTail(QueueNode _t) {
        _tail = _t;
        if (_t != null) {
            _t.setNext(null);
        }
    }

    public QueueNode getHead() {
        return _head;
    }

    public QueueNode getTail() {
        return _tail;
    }

    public int getLength() {
        if (isEmpty()) {
            return 0;
        } else {
            QueueNode _count = getHead();
            int _i = 1;
            while (_count != getTail()) {
                _i++;
                _count = _count.getNext();
            }
            return _i;
        }
    }

    public boolean isEmpty() {
        return (getHead() == null);
    }

    public void setLimitedLength(int _length) {
        _limitedLength = _length;
    }

    public boolean isFull() {
        return (getLength() == _limitedLength);
    }

    public MessageQueueFIFO getExit() {
        return _exit;
    }

    public void sendToExit(Object _object) {
        AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Exit", SimThread.getSimSystemData().getCurrentTime());

        _exit.receive(_object);
    }

    public void enqueue(Object _object) {
        QueueNode _qNode = new QueueNode(_object);

        if (isEmpty()) {
            setHead(_qNode);
            setTail(_qNode);
        } else {
            getTail().setNext(_qNode);
            setTail(_qNode);
        }
    }

    public Object dequeue() {
        QueueNode _qNode = getHead();
        setHead(getHead().getNext());

        return _qNode.getObject();
    }

    public String toString() {
        return _identity;
    }

    public void display() {
        System.out.println("********** " + toString() + " Display **************");

        System.out.println("Head		: " + getHead());
        System.out.println("Tail		: " + getTail());

        if (!(isEmpty())) {
            System.out.println("Queue Length	: " + getLength());
            System.out.println("Head		: " + getHead() + "	,	" + getHead().getObject());
            System.out.println("Tail		: " + getTail() + "	,	" + getTail().getObject());
            System.out.println();

            QueueNode _trace = getHead();

            for (int i = 0; i < getLength(); i++) {
                System.out.println("Object Name : " + _trace + "	,	" + _trace.getObject());
                _trace = _trace.getNext();
            }
        }

        System.out.println("********************************************************");
    }
}
