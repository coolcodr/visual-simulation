package mcomponent.distribution;

public class GammaDistribution extends Distribution {
	private double _alpha;
	private double _beta;
	
	public GammaDistribution(){
		this(0.0,1.0);
	}
	
	public GammaDistribution(double alpha, double beta) {
		setAlpha(alpha);
		setBeta(beta);
	}
	
	public void setAlpha(double alpha) {
		if(alpha < 0.0)
			System.out.print("Error input");
		else
			_alpha = alpha;
	}
	
	public void setBeta(double beta) {
		if(beta < 0.0)
			System.out.print("Error input");
		else
			_beta = beta;
	}
	
	public double getAlpha(){
		return _alpha;
	}
	
	public double getBeta(){
		return _beta;
	}

	public void setAlpha(Double alpha) {
		setAlpha(alpha.doubleValue());
	}
	
	public void setBeta(Double beta) {
		setBeta(beta.doubleValue());
	}
	
	public double getNextValue() {
		double U1,U2,a,b,z,v1,v2,y,am,s,avg,e;
		
		if(_alpha<1) {
			do {
				U1 = Math.random();
				a = Math.pow(U1,1.0/(1-_alpha));
				U2 = Math.random();
				b = Math.pow(U2,1/(1-_alpha));
			} while((a+b)>1);
			z = -1*Math.log(1.0-Math.random());
			return (b*z)/(_beta*(a+b));
		}
		else if(_alpha==1) {
			return (-(1/_beta))*Math.log(1.0-Math.random());
		}
		else{
			do {
		    	do {
			    	do {
			        	v1 = 2.0 * Math.random() - 1.0;
			        	v2 = 2.0 * Math.random() - 1.0;
			      	} while (v1 * v1 + v2 * v2 > 1.0);
					y = v2 / v1;
			      	am = _alpha - 1.0;
			      	s = Math.sqrt(2.0 * am + 1.0);
			      	avg = s * y + am;
			    } while (avg <= 0.0);
			    e = (1.0 + y * y) * Math.exp(am * Math.log(avg / am) - s * y);
			} while (Math.random() > e);
			return avg / _beta;
		}
	}
}