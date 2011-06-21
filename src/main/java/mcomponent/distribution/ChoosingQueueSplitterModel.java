package mcomponent.distribution;
import mcomponent.queue.*;


public class ChoosingQueueSplitterModel extends SplitterModel
{
	public void start()
	{
		int _min = ((Queue)(getSplitter().getOutput()[0])).getLength();
		setOutputNo(0);
		
		for (int _i = 1; _i < getSplitter().getOutput().length; _i++)
		{
			if (((Queue)(getSplitter().getOutput()[_i])).getLength() < _min)
			{
				_min = ((Queue)(getSplitter().getOutput()[_i])).getLength();
				setOutputNo(_i);
			}
		}
	}
}