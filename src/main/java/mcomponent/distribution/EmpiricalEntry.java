package mcomponent.distribution;

public class EmpiricalEntry {
	
	private double _value;
	private int _frequency;
	
	public EmpiricalEntry(double value, int frequency) {
		setValue(value);
		setFrequency(frequency);
	}
	
	public void setValue(double value){
		_value = value;
	}
	
	public void setFrequency(int frequency){
		_frequency = frequency;
	}
	
	public double getValue() {
		return _value;
	}
	
	public int getFrequency() {
		return _frequency;	
	}
}		