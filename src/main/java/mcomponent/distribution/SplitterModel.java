package mcomponent.distribution;

import mcomponent.*;

abstract public class SplitterModel {
	private Splitter _splitter;
	private Object _object;
	private int _outputNo;
	
	public void setSource(Splitter _splitter) {
		this._splitter = _splitter;
	}
	
	public Splitter getSplitter() {
		return _splitter;
	}
	
	public void setObject(Object _obj) {
		_object = _obj;
	}
	
	abstract public void start();
	
	public Object getObject() {
		return _object;
	}
	
	public void setOutputNo(int _no) {
		_outputNo = _no;
	}
	
	public int getOutputNo() {
		return _outputNo;
	}
}