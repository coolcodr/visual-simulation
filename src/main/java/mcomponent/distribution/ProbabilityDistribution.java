package mcomponent.distribution;

public class ProbabilityDistribution extends Distribution {
	private double _probability;
	
	public ProbabilityDistribution(){
		this(0.5);
	}
	
	public ProbabilityDistribution(double probability) {
		setProbability(probability);
	}
	
	public void setProbability(double probability) {
		if((probability < 0.0) || (probability > 1.0))
			System.err.print("Error");
		else
			_probability = probability;
	}
	
	public double getProbability(){
		return _probability;
	}
	
	public void setProbability(Double probability) {
		setProbability(probability.doubleValue());
	}
	
	public double getNextValue() {
  		return (_probability >= Math.random())? 1 : 0;
  	}
}