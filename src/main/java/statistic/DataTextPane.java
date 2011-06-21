package statistic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class DataTextPane extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 5201324463996783425L;

    private AnalysisTool2 _analysisTool;

    private double[] _serviceTimes;
    private int[] _throughPut;
    private int[] _objectInComponent;
    private double[] _utilizations;

    private JLabel[][] _labels;

    private JPanel _textPanel;
    private JPanel _dataPanel;

    public DataTextPane() {
        super();

        _textPanel = new JPanel();
        _dataPanel = new JPanel();

        _textPanel.setLayout(new GridLayout(5, 1));
        _textPanel.add(new JLabel("Total Service Time"));
        _textPanel.add(new JLabel("Mean Service Time"));
        _textPanel.add(new JLabel("Through-put"));
        _textPanel.add(new JLabel("Object in Component"));
        _textPanel.add(new JLabel("Utilization"));

        setLayout(new BorderLayout());

        add(BorderLayout.WEST, _textPanel);
        add(BorderLayout.EAST, _dataPanel);

        _textPanel.setPreferredSize(new Dimension(150, 200));
        _dataPanel.setPreferredSize(new Dimension(700, 200));

        setPreferredSize(new Dimension(850, 200));
    }

    public void display(AnalysisTool2 analysisTool, double startTime, double analyzeTime, double[] serviceTimes, int[] throughPut, int[] objectInComponent, double[] utilizations) {
        _analysisTool = analysisTool;
        repaint();
        _serviceTimes = serviceTimes;
        _throughPut = throughPut;
        _objectInComponent = objectInComponent;
        _utilizations = utilizations;

        int xlength = 5;
        int ylength = _serviceTimes.length;

        _labels = new JLabel[xlength][ylength];
        _dataPanel.setLayout(new GridLayout(xlength, ylength));
        for (int i = 0; i < ylength; i++) {
            _labels[0][i] = new JLabel("" + Math.round(_serviceTimes[i]));
            _labels[1][i] = new JLabel("" + Math.round(_serviceTimes[i] / throughPut[i]));
            _labels[2][i] = new JLabel("" + _throughPut[i]);
            _labels[3][i] = new JLabel("" + _objectInComponent[i]);
            _labels[4][i] = new JLabel("" + Math.round(_utilizations[i] * 100));
        }

        for (int i = 0; i < xlength; i++) {
            for (int j = 0; j < ylength; j++) {
                _dataPanel.add(_labels[i][j]);
            }
        }

        _dataPanel.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        g.drawString(_analysisTool + "", 0, 0);
    }
}
