package mcomponent.distribution;

public class GeometricDistribution extends Distribution {
    private double _p;

    public GeometricDistribution() {
        this(0.5);
    }

    public GeometricDistribution(double p) {
        setP(p);
    }

    public void setP(double p) {
        if ((p <= 0.0) || (p >= 1.0)) {
            System.out.print("Error!");
        } else {
            _p = p;
        }
    }

    public double getP() {
        return _p;
    }

    public void setP(Double p) {
        setP(p.doubleValue());
    }

    public double getMean() {
        return 1 / _p;
    }

    public double getStdDev() {
        return (1 - _p) / (_p * _p);
    }

    public double getNextValue() {
        return (Math.ceil(Math.log(Math.random()) / Math.log(1.0 - _p)));
    }
}
