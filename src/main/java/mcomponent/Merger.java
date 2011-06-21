package mcomponent;

import engine.*;
import mcomponent.queue.*;
import mcomponent.distribution.*;
import statistic.*;
//created by matthew, to combine 2 queue into 1.
public class Merger extends SimThread {
	private Object _output;
	private Object[] _input;
	
	private MergerModel _mergerModel;
	
	public Merger(String _id, int _inputNo)
	{
		super(_id);
		_input = new MessageQueue[_inputNo];
	}
	
	public void setOutput(MessageQueue _mq)
	{
		_output = _mq;
	}
	
	public void setInput(int _id, MessageQueue _mq)
	{
		_input[_id] = _mq;
	}
	
	public Object getOutput()
	{
		return _output;
	}
	
	public Object[] getInput()
	{
		return _input;
	}
	
	public void setMergerModel(MergerModel _mergerModel)
	{
		this._mergerModel = _mergerModel;
	}
	
	public synchronized void run()
	{
		Object _object=null;
		while (true)
		{
			_mergerModel.setSource(this);
			_mergerModel.setObject(_object);
			_mergerModel.start();
			_object = ((MessageQueue)_input[_mergerModel.getInputNo()]).send();
			System.out.println("Merger:["+this.getName()+"] <<<<"+_object);			
			System.out.println("Merger:["+this.getName()+"] >>>>"+_object);
			((MessageQueue)_output).receive(_object);
			
		}
	}
}