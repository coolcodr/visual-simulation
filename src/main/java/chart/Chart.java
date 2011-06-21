package chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JPanel;

import statistic.AnalysisTool2;

public class Chart extends JPanel {
    // For temporary display
    // protected static TmpChartFrame _commonFrame = new TmpChartFrame();

    /**
     * 
     */
    private static final long serialVersionUID = -4117780650969264023L;

    private Dimension _immediateSize = new Dimension(800, 550);

    private int _numOfSampleValue = 30;
    private String _xAxisUnit = "";
    private String _yAxisUnit = "";
    private double _maxLimitOnXAxis;
    private double _minLimitOnXAxis;
    private double _maxLimitOnYAxis;
    private double _minLimitOnYAxis;
    private String _title;

    private Presentation _presentation;

    private AnalysisTool2 _dataSource;
    private DataProcessor _dProcesser;

    private LinkedList _sampleValues;

    public Chart() {
        this("");
    }

    public void prepareImage() {/*
                                 * _commonFrame = null; _presentation = null;
                                 * _dataSource = null; _dProcesser = null;
                                 */
        // _commonFrame = null;
        _dataSource = null;
        // _commonFrame = null;
        _dProcesser = null;
    }

    public Chart(String title) {
        _title = title;
        _sampleValues = new LinkedList();
        setBackground(Color.white);
        setPreferredSize(_immediateSize);
        this.setBounds(0, 0, _immediateSize.width, _immediateSize.height);
        this.setSize(_immediateSize);
    }

    public void addSampleValue(double newValue, String newLabel) {
        if (_sampleValues.size() + 1 > _numOfSampleValue) {
            _sampleValues.removeFirst();
        }
        _sampleValues.add(new SampleValue(newValue, newLabel, new Font("Dialog", Font.PLAIN, 12)));

        if (_presentation != null) {
            _presentation.repaint();
        }
        /*
         * repaint2(); repaint();
         */
    }

    public void addSampleValue(SampleValue sampleValue) {
        if (_sampleValues.size() + 1 > _numOfSampleValue) {
            _sampleValues.removeFirst();
        }
        _sampleValues.add(sampleValue);

        if (_presentation != null) {
            _presentation.repaint();
        }
    }

    public void addSampleValue(double newValue) {
        if (_sampleValues.size() + 1 > _numOfSampleValue) {
            _sampleValues.removeFirst();
        }
        _sampleValues.add(new SampleValue(newValue, "", new Font("Dialog", Font.PLAIN, 12)));

        if (_presentation != null) {
            _presentation.repaint();
        }
    }

    public void setSampleValue(int index, SampleValue sampleValue) {
        if (index > _sampleValues.size()) {
            _sampleValues.removeFirst();
        }
        _sampleValues.set(index, sampleValue);
    }

    public SampleValue getSampleValue(int index) {
        if (index < _sampleValues.size()) {
            return (SampleValue) _sampleValues.get(index);
        } else {
            return null;
        }
    }

    public LinkedList getSampleValues() {
        return _sampleValues;
    }

    public int getNumOfSampleValue() {
        return _numOfSampleValue;
    }

    public String getXAxisUnit() {
        return _xAxisUnit;
    }

    public String getYAxisUnit() {
        return _yAxisUnit;
    }

    public double getMaxLimitOnXAxis() {
        return _maxLimitOnXAxis;
    }

    public double getMinLimitOnXAxis() {
        return _minLimitOnXAxis;
    }

    public double getMaxLimitOnYAxis() {
        return _maxLimitOnYAxis;
    }

    public double getMinLimitOnYAxis() {
        return _minLimitOnYAxis;
    }

    public SampleValue getMaxSampleValue() {
        double max;
        SampleValue result = null;
        max = Double.MIN_VALUE;

        for (int i = 0; i < _sampleValues.size(); i++) {
            if (max < ((SampleValue) _sampleValues.get(i)).getValue()) {
                result = (SampleValue) _sampleValues.get(i);
                max = ((SampleValue) _sampleValues.get(i)).getValue();
            }
            // System.out.print(" " + max +
            // "((SampleValue)_sampleValues.get(i)).getValue()" +
            // ((SampleValue)_sampleValues.get(i)).getValue());
        }
        // System.out.println();
        // System.out.println(max);

        return result;
    }

    public Presentation getPresentation() {
        return _presentation;
    }

    public String getTitle() {
        return _title;
    }

    public void setNumOfSampleValue(int numOfSampleValue) {
        _numOfSampleValue = numOfSampleValue;
    }

    public void setXAxisUnit(String xAxisUnit) {
        _xAxisUnit = xAxisUnit;
    }

    public void setYAxisUnit(String yAxisUnit) {
        _yAxisUnit = yAxisUnit;
    }

    public void setMaxLimitOnXAxis(double maxLimitOnXAxis) {
        _maxLimitOnXAxis = maxLimitOnXAxis;
    }

    public void setMinLimitOnXAxis(double minLimitOnXAxis) {
        _minLimitOnXAxis = minLimitOnXAxis;
    }

    public void setMaxLimitOnYAxis(double maxLimitOnYAxis) {
        _maxLimitOnYAxis = maxLimitOnYAxis;
    }

    public void setMinLimitOnYAxis(double minLimitOnYAxis) {
        _minLimitOnYAxis = minLimitOnYAxis;
    }

    public void setPresentation(Presentation presentation) {
        if (_presentation != null) {
            this.remove(_presentation);
        }

        presentation.setChart(this);

        setLayout(null);
        _presentation = presentation;
        this.add(_presentation);
        _presentation.setLocation((this.getSize().width - _presentation.getPreferredSize().width) / 2, 50);
    }

    public void setTitle(String title) {
        _title = title;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Dialog", Font.PLAIN, 15));
        g.drawString(_title, this.getSize().width / 2, 20);
        g.drawString(_xAxisUnit, this.getSize().width / 2, this.getSize().height - 30);
        g.drawString(_yAxisUnit, 5, this.getSize().height / 2);
    }

    public void setDataSource(AnalysisTool2 dataSource) {
        _dataSource = dataSource;
    }

    public AnalysisTool2 getDataSource() {
        return _dataSource;
    }

    public void setDataProcessor(DataProcessor dProcesser) {
        _dProcesser = dProcesser;
    }

    public DataProcessor getDataProcessor() {
        return _dProcesser;
    }

    public void display() {
        if (getDataSource() != null) {
            getDataSource().cal();
        }
        if (_dProcesser != null) {
            _dProcesser.setChart(this);
            _dProcesser.setAnalysisTool2(getDataSource());
            _dProcesser.processData();
        }

        TmpChartFrame _commonFrame = new TmpChartFrame();
        _commonFrame.addChart(this);
        _commonFrame.setBounds(5000, 5000, 0, 0);
        _commonFrame.setVisible(true);
        _commonFrame.setVisible(false);
        /*
         * this.updateUI(); this.repaint(); _commonFrame.repaint();
         */
    }

    public Chart[] getCharts() {
        Chart[] charts = new Chart[1];
        charts[0] = this;
        return charts;// _commonFrame.getCharts();
    }

    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
}
