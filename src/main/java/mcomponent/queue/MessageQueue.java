package mcomponent.queue;

public interface MessageQueue {
    public void receive(Object _object);

    public void receive(Object _object, boolean _wait);

    public Object send();
}
