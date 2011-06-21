package mcomponent.distribution;

import java.util.Vector;

public class EmpiricalDistribution extends Distribution {

    Vector entryStorage;
    private Vector cdfXAxis;
    private Vector cdfYAxis;
    private double width;

    public EmpiricalDistribution() {
        entryStorage = new Vector();
    }

    public boolean addEntry(double value, int frequency) {

        EmpiricalEntry entry;
        for (int i = 0; i < entryStorage.size(); i++) {
            entry = (EmpiricalEntry) entryStorage.elementAt(i);
            if (value <= entry.getValue()) {
                return false;
            }
        }
        entry = new EmpiricalEntry(value, frequency);
        entryStorage.add(entry);
        return true;
    }

    public int getNumOfEntry() {
        return entryStorage.size();
    }

    public void makeCdfTable() {
        int S = 0;

        int cumulativeFrequency = 0;

        cdfXAxis = new Vector();
        cdfYAxis = new Vector();
        for (int i = 0; i < entryStorage.size(); i++) {
            S = S + ((EmpiricalEntry) entryStorage.elementAt(i)).getFrequency();
        }
        for (int i = 0; i < entryStorage.size(); i++) {
            cumulativeFrequency = cumulativeFrequency + ((EmpiricalEntry) entryStorage.elementAt(i)).getFrequency();
            cdfYAxis.add(new Double(((double) cumulativeFrequency) / (double) S));
            cdfXAxis.add(new Double(((EmpiricalEntry) entryStorage.elementAt(i)).getValue()));
        }
    }

    private int searchY(double y) {
        int i;
        for (i = 0; y > ((Double) cdfYAxis.elementAt(i)).doubleValue(); i++) {
        }
        return i;
    }

    public double getNextValue() {

        double U, x1, x2, y1, y2, grad;
        int i;
        U = Math.random();
        if (entryStorage.size() == 0) {
            return 0.0;
        }
        i = searchY(U);
        if (i == 0) {
            x1 = 0;
        } else {
            x1 = ((Double) cdfXAxis.elementAt(i - 1)).doubleValue();
        }
        if (i == 0) {
            y1 = 0;
        } else {
            y1 = ((Double) cdfYAxis.elementAt(i - 1)).doubleValue();
        }
        x2 = ((Double) cdfXAxis.elementAt(i)).doubleValue();
        y2 = ((Double) cdfYAxis.elementAt(i)).doubleValue();
        grad = (y2 - y1) / (x2 - x1);
        return (x1 + (U - y1) / grad);

    }
}
