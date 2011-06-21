package mcomponent.queue;

public class QueueNode {
    private Object _object;
    private int _priority;
    private QueueNode _next;

    public QueueNode() {
        this(null, 0);
    }

    public QueueNode(Object _object) {
        this(_object, 0);
    }

    public QueueNode(Object _object, int _priority) {
        setObject(_object);
        setPriority(_priority);
        setNext(null);
    }

    public void setObject(Object _content) {
        _object = _content;
    }

    public void setNext(QueueNode _qNode) {
        _next = _qNode;
    }

    public void setPriority(int _p) {
        _priority = _p;
    }

    public Object getObject() {
        return _object;
    }

    public QueueNode getNext() {
        return _next;
    }

    public int getPriority() {
        return _priority;
    }
}
