package engine;

public class Node {
    private Event _event;
    private Node _left;
    private Node _right;

    public Node(Event _event) {
        setData(_event);
    }

    public void setData(Event _content) {
        _event = _content;
    }

    public void setLeft(Node _node) {
        _left = _node;
    }

    public void setRight(Node _node) {
        _right = _node;
    }

    public Event getData() {
        return _event;
    }

    public Node getLeft() {
        return _left;
    }

    public Node getRight() {
        return _right;
    }
}
