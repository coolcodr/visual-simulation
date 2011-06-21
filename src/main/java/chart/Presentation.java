package chart;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

abstract class Presentation extends JComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -6815050536768590100L;
    private String _name = "";
    private Chart _chart;

    // private AnalysisTool2 _dataSource;
    // private DataProcessor _dProcesser;

    public Presentation() {
        this(null);
    }

    public Presentation(Chart chartToShow) {
        _chart = chartToShow;
    }

    public Chart getChart() {
        return _chart;
    }

    public void setChart(Chart chartToShow) {
        _chart = chartToShow;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
        Graphics g = getGraphics();
        Graphics2D g2 = (Graphics2D) g;

    }

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
    /*
     * public void setDataSource(AnalysisTool2 dataSource){ _dataSource =
     * dataSource; }
     * 
     * public AnalysisTool2 getDataSource(){ return _dataSource; }
     * 
     * public void setDataProcessor(DataProcessor dProcesser){ _dProcesser =
     * dProcesser; }
     * 
     * public DataProcessor getDataProcessor(){ return _dProcesser; }
     */
}
