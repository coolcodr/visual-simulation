package engine;

import java.lang.*;

public class SimThread extends Thread
{
	static SimSystemData _simSystem = new SimSystemData();
	SimThreadSuspendData _suspendData = new SimThreadSuspendData();

	private boolean _isActive;
	
	private String _identity;
	
	public SimThread(String _id)
	{
		super(_id);
		getSimSystemData().incrementActiveCount();
		
		_identity = _id;
		_isActive = false;
	}
	
	public SimThreadSuspendData getSimThreadSuspendData()
	{
		return _suspendData;
	}
	
	public static SimSystemData getSimSystemData()
	{
		return _simSystem;
	}

	public void hold(SimThread _thread, double _delay)
	{
		getSimSystemData().addEvent(new Event(getSimSystemData().getCurrentTime() + _delay, _thread, "Hold"));
		pause();
	}
	
	final public void pause()
	{
		getSimSystemData().decrementActiveCount();
		_isActive = false;
		getSimThreadSuspendData().pause();
	}

	final public void cont()
	{
		getSimThreadSuspendData().cont();
		getSimSystemData().incrementActiveCount();
		_isActive = true;
		
	}
	
	final public synchronized void start()
	{
		_isActive = true;
		super.start();
	}
	
	public synchronized void destroy()
	{
		getSimSystemData().decrementActiveCount();
	}
	
	public String toString()
	{
		return _identity;
	}

//added by matthew, to clear the systemdata allow rerun simulation process	
	public static void clearSystemData() {
		_simSystem = new SimSystemData();
	}
	
	static boolean _stop=false;
	
	public static void setStop(boolean stop) {
		_stop = stop;
	}
	
	public static boolean getStop() {
		return _stop;
	}
}

