package mcomponent.distribution;

public class StudentTDistribution extends Distribution {
    private int _n;

    public StudentTDistribution() {
        this(10);
    }

    public StudentTDistribution(int n) {
        setN(n);
    }

    public void setN(int n) {
        _n = n;
    }

    public int getN() {
        return _n;
    }

    public void setN(Integer n) {
        setN(n.intValue());
    }

    public double getNextValue() {
        return (new NormalDistribution(0.0, 1.0).getNextValue() / Math.sqrt(new ChisquareDistribution(_n).getNextValue() / _n));
    }
}
