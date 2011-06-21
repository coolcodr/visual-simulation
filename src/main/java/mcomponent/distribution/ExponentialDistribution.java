package mcomponent.distribution;

public class ExponentialDistribution extends Distribution {

    private double _mean;

    public ExponentialDistribution() {
        this(1);
    }

    public ExponentialDistribution(double mean) {
        setMean(mean);
    }

    public void setMean(double mean) {
        _mean = mean;
    }

    public double getMean() {
        return _mean;
    }

    public double getStdDev() {
        return _mean;
    }

    public void setMean(Double mean) {
        setMean(mean.doubleValue());
    }

    public double getNextValue() {
        double U;

        U = Math.random();
        return -_mean * Math.log(1.0 - U);
    }
}
