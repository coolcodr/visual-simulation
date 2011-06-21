package mcomponent.distribution;

public class FDistribution extends Distribution {
	private int _m;
	private int _n;
	
	public FDistribution(){
		this(5,5);
	}
	
	public FDistribution(int m, int n) {
		setM(m);
		setN(n);
	}
	
	public void setM(int m) {
		_m = m;
	}
	
	public void setN(int n) {
		_n = n;
	}
	
	public double getM(){
		return _m;
	}
	
	public double getN() {
		return _n;
	}
	
	public void setM(Integer m) {
		setM(m.intValue());
	}
	
	public void setN(Integer n) {
		setN(n.intValue());
	}
	
	public double getNextValue() {
		return ( (_n * new ChisquareDistribution (_m ).getNextValue()) / ( _m * new ChisquareDistribution (_n).getNextValue() ) );
	}
}