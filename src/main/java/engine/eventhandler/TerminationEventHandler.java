package engine.eventhandler;
import engine.*;

public interface TerminationEventHandler {
	
	public void processAddTerminationEvent();
	
	public void processTerminationEvent(Event _event);
}