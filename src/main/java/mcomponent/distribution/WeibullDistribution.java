package mcomponent.distribution;

public class WeibullDistribution extends Distribution {
    private double _theta;
    private double _gamma;

    public WeibullDistribution() {
        this(1.0, 1.0);
    }

    public WeibullDistribution(double theta, double gamma) {
        setTheta(theta);
        setGamma(gamma);
    }

    public void setTheta(double theta) {
        _theta = theta;
    }

    public void setGamma(double gamma) {
        _gamma = gamma;
    }

    public double getTheta() {
        return _theta;
    }

    public double getGamma() {
        return _gamma;
    }

    public void setTheta(Double theta) {
        setTheta(theta.doubleValue());
    }

    public void setGamma(Double gamma) {
        setGamma(gamma.doubleValue());
    }

    public double getNextValue() {
        return Math.pow(new ExponentialDistribution(_theta).getNextValue(), (1.0 / _gamma));
    }
}
