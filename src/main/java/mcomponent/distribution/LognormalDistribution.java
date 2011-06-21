package mcomponent.distribution;

public class LognormalDistribution extends Distribution {
    private double _mean;
    private double _stdDev;

    public LognormalDistribution() {
        this(0.0, 1.0);
    }

    public LognormalDistribution(double mean, double stdDev) {
        setMean(mean);
        setStdDev(stdDev);
    }

    public void setMean(double mean) {
        _mean = mean;
    }

    public void setStdDev(double stdDev) {
        _stdDev = stdDev;
    }

    public double getMean() {
        return Math.exp(_mean + 0.5 * _stdDev * _stdDev);
    }

    public double getStdDev() {
        return Math.sqrt((Math.exp(_stdDev * _stdDev) - 1) * Math.exp(2 * _mean + _stdDev * _stdDev));
    }

    public void setMean(Double mean) {
        setMean(mean.doubleValue());
    }

    public void setStdDev(Double stdDev) {
        setStdDev(stdDev.doubleValue());
    }

    public double getNextValue() {
        return (Math.exp(new NormalDistribution(_mean, _stdDev).getNextValue()));
    }
}
