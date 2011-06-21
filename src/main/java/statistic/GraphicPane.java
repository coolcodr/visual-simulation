package statistic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GraphicPane extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -1835446422906435767L;

    private AnalysisTool2 _analysisTool;

    private double _startTime;
    private double _analyzeTime;

    private double[] _data;

    public GraphicPane() {
        super();

        setPreferredSize(new Dimension(600, 200));
    }

    public void display(AnalysisTool2 analysisTool, double startTime, double analyzeTime, double[] data) {
        _analysisTool = analysisTool;
        _startTime = startTime;
        _analyzeTime = analyzeTime;

        _data = data;

        repaint();
    }

    public void paint(Graphics g) {

        super.paint(g);

        g.drawString(_analysisTool + "", 0, 0);

        int xlength = 600 / _data.length;
        int ylength = 300 / 100;

        int p1x = 50;
        int p1y = 50;
        int p2x = p1x + 600;
        int p2y = p1y + 300;

        g.drawString("Utilization", p1x / 2, p1y / 2);

        g.drawString("100%", p1x - 20, p1y);
        g.drawString("0%", p1x - 20, p2y);

        g.drawString("" + _startTime, p1x - 5, p2y + 15);
        g.drawString("" + (_startTime + _analyzeTime), p2x - 5, p2y + 15);

        g.drawString("Time", (p1x + p2x) / 2, p2y + 20);

        g.drawLine(p1x, p2y, p2x, p2y);
        g.drawLine(p1x, p1y, p1x, p2y);
        for (int i = 0; i < _data.length; i++) {
            int u = (int) (_data[i] * 100);

            if (u > 100) {
                u = 100;
            } else if (u < 0) {
                u = 0;
            }
            g.fillOval(p1x + xlength * i - 1, p1y + ylength * (100 - u) - 1, 3, 3);
        }

        g.setColor(new Color(255, 0, 0));

        for (int i = 0; i < _data.length - 1; i++) {
            int u1 = (int) (_data[i] * 100);
            int u2 = (int) (_data[i + 1] * 100);

            if (u1 > 100) {
                u1 = 100;
            } else if (u1 < 0) {
                u1 = 0;
            }

            if (u2 > 100) {
                u2 = 100;
            } else if (u2 < 0) {
                u2 = 0;
            }

            g.drawLine(p1x + xlength * i, p1y + ylength * (100 - u1), p1x + xlength * (i + 1), p1y + ylength * (100 - u2));
        }
    }
}
