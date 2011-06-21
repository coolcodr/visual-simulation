package engine.eventhandler;

import engine.Event;

public interface TerminationEventHandler {

    public void processAddTerminationEvent();

    public void processTerminationEvent(Event _event);
}
