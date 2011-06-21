package mcomponent.distribution;

public class PoissonDistribution extends Distribution {
    private double _mean;

    public PoissonDistribution() {
        this(1.0);
    }

    public PoissonDistribution(double mean) {
        setMean(mean);
    }

    public void setMean(double mean) {
        _mean = mean;
    }

    public double getMean() {
        return _mean;
    }

    public double getStdDev() {
        return Math.sqrt(_mean);
    }

    public void setMean(Double mean) {
        setMean(mean.doubleValue());
    }

    public double getNextValue() {
        double t = 0.0;
        int x = 0;

        while (t < _mean) {
            t += new ExponentialDistribution(1.0).getNextValue();
            x++;
        }
        return (x - 1);
    }
}
