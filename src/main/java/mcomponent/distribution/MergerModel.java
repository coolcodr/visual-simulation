package mcomponent.distribution;

import mcomponent.*;

abstract public class MergerModel {
	private Merger _merger;
	private Object _object;
	private int _inputNo;
	
	public void setSource(Merger _merger) {
		this._merger = _merger;
	}
	
	public Merger getMerger() {
		return _merger;
	}
	
	public void setObject(Object _obj) {
		_object = _obj;
	}
	
	abstract public void start();
	
	public Object getObject() {
		return _object;
	}
	
	public void setInputNo(int _no) {
		_inputNo = _no;
	}
	
	public int getInputNo() {
		return _inputNo;
	}
}