package designer.deployment;

public interface MainControlHandler
{


	public void init() throws InvalidDataException;


	public void overrideData() throws InvalidDataException;


	public void processAddTerminationEvent();


	public void processTerminationEvent(engine.Event _event);


	public void startRun() throws InvalidDataException;


}