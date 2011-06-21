package statistic;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ReportingFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -4748627417428076255L;
    private DataPanel _dataPanel;
    private GraphicPanel _gPanel;

    public ReportingFrame() {
        super();

        _dataPanel = new DataPanel();
        _gPanel = new GraphicPanel();
        setContentPane(_dataPanel);
        setSize(new Dimension(910, 610));
    }

    public void display(String name, double[][] result2, double[] result1) {
        // JOptionPane.showMessageDialog(this, "hello");
        _dataPanel.display(name, result1);
        _dataPanel.setGraphicPanel(_gPanel);

        _gPanel.display(result2);

        setVisible(false);
        setVisible(true);
        toFront();
        show();
        repaint();
    }
}
