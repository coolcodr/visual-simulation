package mcomponent.queue;

import statistic.StatisticData;

public class StatisticQueue extends Queue {
    public StatisticQueue() {
        setHead(null);
        setTail(null);
    }

    public synchronized void receive(Object _object) {
        receive(_object, true);
    }

    public synchronized void receive(Object _object, boolean _wait) {
        QueueNode _qNode = new QueueNode(_object);

        if (isEmpty()) {
            setHead(_qNode);
            setTail(_qNode);
        } else {
            getTail().setNext(_qNode);
            setTail(_qNode);
        }
    }

    public synchronized Object send() {
        QueueNode _qNode = getHead();
        setHead(getHead().getNext());

        return _qNode.getObject();
    }

    public StatisticData delete(Object _object) {
        if (isEmpty()) {
            return null;
        }

        QueueNode _trace = getHead();
        QueueNode _tracePrevious = null;

        while ((_trace != getTail().getNext()) && (((StatisticData) _trace.getObject()).getObject() != ((StatisticData) _object).getObject())) {
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

            return (StatisticData) (_trace.getObject());
        }
    }

    public int search(Object _object) {
        int _count = 1;
        int _show = 0;
        display();

        if (isEmpty()) {
            return -1;
        }

        QueueNode _trace = getHead();
        QueueNode _tracePrevious = null;

        while ((_trace != getTail().getNext()) && (((StatisticData) (_trace.getObject())).getObject() != ((StatisticData) _object).getObject())) {
            _tracePrevious = _trace;
            _trace = _trace.getNext();
            _count++;
        }

        if (_trace == getTail().getNext()) {
            return -1;
        } else {
            return _count;
        }
    }
}
