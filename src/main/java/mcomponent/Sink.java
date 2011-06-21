package mcomponent;

import engine.*;
import mcomponent.queue.*;
import mcomponent.distribution.*;
import statistic.*;

public class Sink extends SimThread {
	private Object _input;
	private Distribution _distribution;
	
	public Sink(String _id) {
		super(_id);
	}
	
	public void setInput(Object _mq) {
		_input = _mq;
	}
	
	public synchronized void run() {
		
		int counter = 0;
		
		while (!getStop()) {
			Object _object = ((MessageQueue)_input).send();
			System.out.println("Sink:["+this.getName()+"] <<<<"+_object);
			AnalysisTool.getAnalysisInfo().addStatisticData(this, _object, "Input", getSimSystemData().getCurrentTime());
		}
		
//		destroy();
	}
}