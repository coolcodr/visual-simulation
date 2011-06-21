package engine;


public class SystemThread extends Thread
{
	public SystemThread()
	{
		super("System Thread");
		setPriority(MIN_PRIORITY);
	}
	
	public void run()
	{
		yield();
		while (!SimThread.getSimSystemData().isTerminate()&&!getStop())
		{
			if (SimThread.getSimSystemData().getActiveCount() == 0)
			{
				SimThread.getSimSystemData().executeNextEvent();
			}
			
			yield();
		}
		
		System.out.println("Simulation Time : " + SimThread.getSimSystemData().getCurrentTime());
		System.out.println("End Of Simulation");
	}
	
//added
	static boolean _stop=false;
	public static void setStop(boolean stop) {
		_stop=stop;
	}	
	
	public static boolean getStop() {
		return _stop;
	}		
}