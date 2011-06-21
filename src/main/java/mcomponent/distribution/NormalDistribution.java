package mcomponent.distribution;

public class NormalDistribution extends Distribution {
    private double _mean;
    private double _stdDev;

    public NormalDistribution() {
        this(0.0, 1.0);
    }

    public NormalDistribution(double mean, double stdDev) {
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
        return _mean;
    }

    public double getStdDev() {
        return _stdDev;
    }

    public void setMean(Double mean) {
        setMean(mean.doubleValue());
    }

    public void setStdDev(Double stdDev) {
        setStdDev(stdDev.doubleValue());
    }

    public double getNextValue() {
        double U1, v1, U2, v2, R, X;

        do {
            U1 = Math.random();
            v1 = 2 * U1 - 1;
            U2 = Math.random();
            v2 = 2 * U2 - 1;
            R = v1 * v1 + v2 * v2;
        } while (R >= 1.0 || R == 0.0);
        X = Math.sqrt((-2 * Math.log(R)) / R) * v1;
        return _mean + _stdDev * X;
    }
}
