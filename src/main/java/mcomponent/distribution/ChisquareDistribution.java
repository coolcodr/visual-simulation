package mcomponent.distribution;

public class ChisquareDistribution extends Distribution {
    private int _n;

    public ChisquareDistribution() {
        this(10);
    }

    public ChisquareDistribution(int n) {
        setN(n);
    }

    public void setN(int n) {
        _n = n;
    }

    public void setN(Integer n) {
        setN(n.intValue());
    }

    public int getN() {
        return _n;
    }

    public double getNextValue() {
        int i;
        double z, x = 0.0;
        for (i = 0; i < _n; i++) {
            z = new NormalDistribution(0.0, 1.0).getNextValue();
            x += z * z;
        }
        return (x);
    }
}
