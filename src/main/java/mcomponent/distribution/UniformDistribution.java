package mcomponent.distribution;

public class UniformDistribution extends Distribution {
	
	private double _lowerLimit;
	private double _upperLimit;
	
	public UniformDistribution() {
	}
	
	public UniformDistribution(double lowerLimit, double upperLimit) {
		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}
	
	public void setLowerLimit(double lowerLimit) {
		_lowerLimit = lowerLimit;
	}
	
	public void setUpperLimit(double upperLimit) {
		_upperLimit = upperLimit;
	}
	
	public double getLowerLimit() {
		return _lowerLimit;
	}
	
	public double getUpperLimit() {
		return _upperLimit;
	}
	
	public double getNextValue() {
		return (Math.random()*(_upperLimit - _lowerLimit)) + _lowerLimit;
	}
	
	public void setUpperLimit(Double upperLimit) {
		setUpperLimit(upperLimit.doubleValue());
	}
	
	public void setLowerLimit(Double lowerLimit) {
		setLowerLimit(lowerLimit.doubleValue());
	}
}