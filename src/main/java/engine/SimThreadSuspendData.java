package engine;

import java.lang.*;

public class SimThreadSuspendData
{
	public synchronized void pause()
	{
		try
		{
			wait();
		} catch (Exception _e) {}
	}
	
	public synchronized void cont()
	{
		notify();
	}
}