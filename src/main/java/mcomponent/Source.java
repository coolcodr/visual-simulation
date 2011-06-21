package mcomponent;

import engine.*;
import mcomponent.queue.*;
import mcomponent.distribution.*;
import statistic.*;
public class Source extends SimThread
{
	private Object _output;
	private Distribution _distribution;
	private ObjectCreator _objectCreator;

	public Source(String _id)
	{
		super(_id);
	}

	public void setOutput(Object _mq)
	{
		_output = _mq;
	}

	public void setDistribution(Distribution _d)
	{
		_distribution = _d;
	}

	public void setObjectCreator(ObjectCreator _oc)
	{
		_objectCreator = _oc;
	}

	public void run()
	{
		while (!getStop())
		{
			double _time = _distribution.getNextValue();
			if(_time<0)
				_time=(_time*-1);
			hold(this, _time);

			Object _object = _objectCreator.createObject();
			//add by matthew
			System.out.println("Source:["+this.getName()+"] >>>>"+_object);

			AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Output", getSimSystemData().getCurrentTime());

			((MessageQueue)_output).receive(_object);
		}
	}

	public Object createObject()
	{
		return null;
	}
}