package mcomponent;

import engine.*;
import mcomponent.queue.*;
import mcomponent.distribution.*;
import statistic.*;

public class Splitter extends SimThread {
	private Object _input;
	private Object[] _output;
	
	private SplitterModel _splitterModel;
	
	public Splitter(String _id, int _outputNo)
	{
		super(_id);
		_output = new MessageQueue[_outputNo];
	}
	
	public void setInput(MessageQueue _mq)
	{
		_input = _mq;
	}
	
	public void setOutput(int _id, MessageQueue _mq)
	{
		_output[_id] = _mq;
	}
	
	public Object getInput()
	{
		return _input;
	}
	
	public Object[] getOutput()
	{
		return _output;
	}
	
	public void setSplitterModel(SplitterModel _splitterModel)
	{
		this._splitterModel = _splitterModel;
	}
	
	public synchronized void run()
	{
		while (true)
		{
			Object _object = ((MessageQueue)_input).send();
			System.out.println("Splitter:["+this.getName()+"] <<<<"+_object);
			_splitterModel.setSource(this);
			_splitterModel.setObject(_object);
			
			_splitterModel.start();
			System.out.println("Splitter:["+this.getName()+"] >>>>"+_object);
			((MessageQueue)_output[_splitterModel.getOutputNo()]).receive(_object);
			
		}
	}
}