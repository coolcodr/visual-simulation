package engine.eventhandler;

import engine.Event;

public interface TimeEventHandler {

    public void processAddTimeEvent(Object _object);

    public void processTimeEvent(Event _event);

    public void processTimeEvent();
}
