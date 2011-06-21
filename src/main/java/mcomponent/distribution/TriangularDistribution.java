package mcomponent.distribution;

public class TriangularDistribution extends Distribution {
    private double _min;
    private double _max;
    private double _mode;

    public TriangularDistribution() {
        this(0.0, 1.0, 0.5);
    }

    public TriangularDistribution(double min, double max, double mode) {
        setMin(min);
        setMax(max);
        setMode(mode);
    }

    public void setMin(double min) {
        _min = min;
    }

    public void setMax(double max) {
        _max = max;
    }

    public void setMode(double mode) {
        _mode = mode;
    }

    public double getMin() {
        return _min;
    }

    public double getMax() {
        return _max;
    }

    public double getMode() {
        return _mode;
    }

    public double getMean() {
        return (_min + _max + _mode) / 3;
    }

    public double getStdDev() {

        return Math.sqrt((_min * _min + _max * _max + _mode * _mode - _min * _max - _min * _mode - _max * _mode) / 18);
    }

    public void setMin(Double min) {
        setMin(min.doubleValue());
    }

    public void setMax(Double max) {
        setMax(max.doubleValue());
    }

    public void setMode(Double mode) {
        setMode(mode.doubleValue());
    }

    public double getNextValue() {
        double U1, U2;

        U1 = Math.random();
        U2 = Math.random();
        if (U1 <= 0.5) {
            return _min + Math.sqrt(U2 * (_max - _min) * (_mode - _min));
        } else {
            return _max - Math.sqrt((1 - U2) * (_max - _min) * (_max - _mode));
        }
    }
}
