package mcomponent.distribution;

public class BinomialDistribution extends Distribution {
	private double _p;
	private int _n;
	
	public BinomialDistribution(){
		this(0.5,10);
	}
	
	public BinomialDistribution(double p, int n) {
		setP(p);
		setN(n);
	}
	
	public void setP(double p) {
		_p = p;
	}
	
	public void setN(int n) {
		_n = n;
	}
	
	public double getP(){
		return _p;
	}
	
	public int getN(){
		return _n;
	}

	public void setP(Double p) {
		setP(p.doubleValue());
	}
	
	public void setN(Integer n) {
		setN(n.intValue());
	}
	
	public double getMean() {
		return _n*_p;
	}
	
	public double getStdDev() {
		return _n*_p*(1-_p);
	}
	
	private double Bernoulli(double p) {
		return ((Math.random() < (1.0 - p)) ? 0 : 1);
	}
	
	public double getNextValue() {
	
		int i; 
		double x = 0;

  		for (i = 0; i < _n; i++)
    		x = x + Bernoulli(_p);
  		
  		return x;
	}
}