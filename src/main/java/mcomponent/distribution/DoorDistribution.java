package mcomponent.distribution;
import engine.*;

public class DoorDistribution extends Distribution
{
	UniformDistribution _ud = new UniformDistribution(1, 1);
	
	public DoorDistribution()
	{
	}
	
	public double getNextValue()
	{
		double _time = SimThread.getSimSystemData().getCurrentTime();
		
		if ((_time >= 37800) && (_time < 48600))
		{
			_ud.setLowerLimit(2);
			_ud.setUpperLimit(4);
		}
		else if ((_time >= 63000) && (_time < 70200))
		{
			_ud.setLowerLimit(5);
			_ud.setUpperLimit(10);
		}
		else
		{
			_ud.setLowerLimit(30);
			_ud.setUpperLimit(40);
		}
		
		return _ud.getNextValue();
	}

}