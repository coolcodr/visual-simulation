package mcomponent.distribution;

public class BetaDistribution extends Distribution {
    private double _alpha;
    private double _beta;

    public BetaDistribution() {
        this(0.0, 1.0);
    }

    public BetaDistribution(double alpha, double beta) {
        setAlpha(alpha);
        setBeta(beta);
    }

    public void setAlpha(double alpha) {
        _alpha = alpha;
    }

    public void setBeta(double beta) {
        _beta = beta;
    }

    public double getAlpha() {
        return _alpha;
    }

    public double getBeta() {
        return _beta;
    }

    public void setAlpha(Double alpha) {
        setAlpha(alpha.doubleValue());
    }

    public void setBeta(Double beta) {
        setBeta(beta.doubleValue());
    }

    public double getNextValue() {
        double z;

        z = new GammaDistribution(_alpha, 1.0).getAlpha();

        return (z / (z + new GammaDistribution(_beta, 1.0).getNextValue()));
    }
}
