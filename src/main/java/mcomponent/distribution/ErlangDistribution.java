package mcomponent.distribution;

public class ErlangDistribution extends Distribution {
	private double _k;
	private double _mean;
	
	public ErlangDistribution(){
		this(10,1);
	}
	
	public ErlangDistribution(double k, double mean) {
		setK(k);
		setMean(mean);
	}
	
	public void setK(double k) {
		_k = k;
	}
	
	public void setMean(double mean) {
		_mean = mean;
	}
	
	public double getK(){
		return _k;
	}
	
	public double getMean(){
		return _mean;
	}
	
	public void setMean(Double mean) {
		setMean(mean.doubleValue());
	}
	
	public void setK(Double k) {
		setK(k.doubleValue());
	}
	
	public double getNextValue() {
		double sum=0;
		for (int i = 0; i < _k;i++)
			sum = sum + new ExponentialDistribution(_mean).getNextValue();
		return sum;
		//double avg = _mean;
		//if (_k < 7) {
  		//	for (int i = 0; i < _k;i++) avg *= Math.random();
    	//		return ( -Math.log (avg) );
		//}    		
  		//else 
		 //   return new GammaDistribution ((double)_k,avg).getNextValue() / ((double)_k);
	}
}