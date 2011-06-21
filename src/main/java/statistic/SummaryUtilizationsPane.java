package statistic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SummaryUtilizationsPane extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -3077957849883170152L;
    private AnalysisTool2[] _analysisTools;
    private double[] _data;

    private double _max = 1;

    public SummaryUtilizationsPane() {
        super();

        setPreferredSize(new Dimension(600, 200));
    }

    public void display(AnalysisTool2[] analysisTools, double[] data) {

        _analysisTools = analysisTools;
        _data = data;

        _max = 100;

        repaint();
    }

    public void paint(Graphics g) {

        super.paint(g);

        int xlength = 600 / _data.length;
        double ylength = 300 / _max;

        int p1x = 50;
        int p1y = 50;
        int p2x = p1x + 600;
        int p2y = p1y + 300;

        g.drawString("Summary of Utilizations", p1x / 2, p1y / 2);

        g.drawString("100%", p1x - 20, p1y);
        g.drawString("0%", p1x - 20, p2y);

        g.drawString("Analysis Tool Name", (p1x + p2x) / 2, p2y + 50);

        g.drawLine(p1x, p2y, p2x, p2y);
        g.drawLine(p1x, p1y, p1x, p2y);

        for (int i = 0; i < _data.length; i++) {
            double u = _data[i] * 100;
            if (u > 100) {
                u = 100;
            }

            g.fillOval(p1x + xlength * i - 1, (int) (p1y + ylength * (_max - u)) - 1, 3, 3);

            g.drawString("" + _analysisTools[i], p1x + xlength * i, p2y + 20);
        }

        g.setColor(new Color(255, 0, 0));

        for (int i = 0; i < _data.length - 1; i++) {
            double u = _data[i] * 100;
            if (u > 100) {
                u = 100;
            }

            g.drawLine(p1x + xlength * i, (int) (p1y + ylength * (_max - u)), p1x + xlength * (i + 1), (int) (p1y + ylength * (_max - u)));
        }
    }
}
