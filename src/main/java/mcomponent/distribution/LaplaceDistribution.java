package mcomponent.distribution;

public class LaplaceDistribution extends Distribution {

    public LaplaceDistribution() {
    }

    public double getNextValue() {
        if (new ProbabilityDistribution(0.5).getNextValue() == 1) {
            return (-Math.log(Math.random()));
        } else {
            return (Math.log(Math.random()));
        }
    }
}
