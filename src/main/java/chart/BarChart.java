package chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.io.Serializable;
import java.util.LinkedList;

public class BarChart extends Presentation implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4867663508259956099L;
    private final Dimension _smallestSize = new Dimension(600, 450);
    private Dimension _immediateSize = new Dimension(600, 450);
    // private BufferedImage bi = new
    // BufferedImage(_immediateSize.width,_immediateSize.height,BufferedImage.TYPE_INT_ARGB);
    private BarChartMouseAdapter _myMouseAdapter;

    private double _gridLineUnit = 100;
    private double _assistantLineUnit = 10;
    private int _numOfDisplayOnXAxis = 30;

    private Insets _border;
    private int _x, _y, _width, _height;

    private LinkedList _sampleValues;

    private boolean _isDrawVerticalLine = true;
    private boolean _isDrawBarLabel = true;
    private boolean _isDrawYGridLine = true;
    private boolean _isDrawSecondaryYGridLine = true;
    private boolean _isDrawValueIndicator = true;

    private String _mouseOnBarLabel = "";
    private int _space;

    public BarChart() {
        this(null);
    }

    public BarChart(Chart chartToShow) {
        super(chartToShow);

        if (getChart() != null) {
            _sampleValues = getChart().getSampleValues();
        }

        setPreferredSize(_immediateSize);
        this.setBounds(0, 0, _immediateSize.width, _immediateSize.height);
        setDrawVerticalLine(false);
        setDrawBarLabel(false);

        _myMouseAdapter = new BarChartMouseAdapter(this);
        addMouseListener(_myMouseAdapter);
        addMouseMotionListener(_myMouseAdapter);

        _border = this.getInsets();
    }

    protected void paintComponent(Graphics g) {
        if (getChart() == null) {
            return;
        }

        _sampleValues = getChart().getSampleValues();
        if (_sampleValues.size() <= 0) {
            super.paintComponent(g);
            return;
        }
        _numOfDisplayOnXAxis = getChart().getNumOfSampleValue();

        updateSize();
        _space = _width / (_numOfDisplayOnXAxis + 1);

        // System.out.println("paintComponent: calculate points");
        double maxPoint = getChart().getMaxSampleValue().getValue();

        double tmp = maxPoint;
        int powCounter = 0;
        if (maxPoint > 1) {
            while (tmp >= 10) {
                powCounter++;
                tmp = tmp / 10;
            }
        } else {
            while (tmp < 1) {
                powCounter--;
                tmp = tmp * 10;
                // System.out.println("powCounter: " +powCounter);
            }
        }

        adjustLineUnit(maxPoint, powCounter);

        _border = this.getInsets();
        int chartHeight;
        chartHeight = getHeight() - ((getHeight() / 10) + _border.top);

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.gray);
        g.drawRect(_x, _y, _width - 1, _height - 1);

        double scaleFactor;
        scaleFactor = calScaleFactor(maxPoint + ((Math.pow(10.0, powCounter)) * 0.5) / 5, chartHeight);
        // scaleFactor = this.calScaleFactor(maxPoint +
        // (double)((Math.pow(10.0,(double)powCounter))*0.5)/5, chartHeight);

        if (_isDrawVerticalLine) {
            drawVerticalLine(g, _space, chartHeight);
        }

        drawBars(g, _space, chartHeight, scaleFactor);

        drawHorizontalLine(g, maxPoint, powCounter, chartHeight, scaleFactor);

        plotLine(g, _space, chartHeight, scaleFactor);

        g.setColor(Color.black);
        drawMouseOnBarLabel(g, _mouseOnBarLabel);
        // this.drawLabels(g);

    }

    private void adjustLineUnit(double maxPoint, int powCounter) {
        int firstSigDigit;

        // powCounter=-1.0;
        if (maxPoint >= 10) {
            firstSigDigit = Integer.parseInt((String.valueOf(maxPoint)).substring(0, 1));
        } else {
            int length = String.valueOf(maxPoint).length();
            int i = 0;
            boolean match = false;

            while (i < length && !match) {
                if (!((String.valueOf(maxPoint)).substring(i, i + 1).equals("0") || (String.valueOf(maxPoint).substring(i, i + 1)).equals("."))) {
                    // terminate the loop
                    length = i;
                    match = true;
                } else {
                    i++;
                }
            }
            firstSigDigit = Integer.parseInt((String.valueOf(maxPoint)).substring(i, i + 1));
        }

        if (firstSigDigit > 7) {
            _gridLineUnit = (Math.pow(10.0, powCounter));
            _assistantLineUnit = (Math.pow(10.0, powCounter)) / 5;
        } else if (firstSigDigit <= 7 && firstSigDigit > 4) {
            _gridLineUnit = (Math.pow(10.0, powCounter));
            _assistantLineUnit = (Math.pow(10.0, powCounter)) / 10;
        } else if (firstSigDigit <= 4 && firstSigDigit > 2) {
            _gridLineUnit = ((Math.pow(10.0, powCounter)) * 0.5);
            _assistantLineUnit = ((Math.pow(10.0, powCounter)) * 0.5) / 5;
        } else {
            _gridLineUnit = ((Math.pow(10.0, powCounter)) * 0.5);
            _assistantLineUnit = ((Math.pow(10.0, powCounter)) * 0.5) / 10;
        }

    }

    private void updateSize() {
        _border = getInsets();

        _width = getWidth() - (_border.left + _border.right);
        _height = getHeight() - (_border.top + _border.bottom);
        _x = _border.left;
        _y = _border.top;
    }

    private double calScaleFactor(double maxPoint, long chartHeight) {
        double factor;

        if (maxPoint != 0.0) {
            factor = chartHeight / maxPoint;
            return factor;
        } else {
            return 0.0; // Avoid divided by zero expection
        }
    }

    private void drawHorizontalLine(Graphics g, double maxPoint, int powCounter, int chartHeight, double scaleFactor) {
        int limit, remainder;
        int counterTmp = 0;
        // draw _SecondaryYLine
        g.setColor(Color.lightGray);
        limit = (int) ((maxPoint + ((long) ((Math.pow(10.0, powCounter)) * 0.5) / 5)) / _assistantLineUnit);
        remainder = (chartHeight) - (int) (limit * _assistantLineUnit * scaleFactor);
        for (int i = limit; 0 - 5 <= i; i--) {
            g.drawLine(_x, (int) (i * _assistantLineUnit * scaleFactor) + remainder, _width - _border.right, (int) (i * _assistantLineUnit * scaleFactor) + remainder);
            counterTmp++;
        }

        // System.out.println("counterTmp: " +counterTmp);

        // draw gridLine
        g.setColor(Color.black);
        limit = (int) ((maxPoint + ((long) ((Math.pow(10.0, powCounter)) * 0.5) / 5)) / _gridLineUnit);
        remainder = (chartHeight) - (int) (limit * _gridLineUnit * scaleFactor);
        g.setFont(new Font("Dialog", Font.PLAIN, 10));
        for (int i = limit; 0 - 5 <= i; i--) {
            g.drawString(String.valueOf((limit * _gridLineUnit) - (i * _gridLineUnit)), _x, (int) (i * _gridLineUnit * scaleFactor) + remainder);
            g.drawLine(_x, (int) (i * _gridLineUnit * scaleFactor) + remainder, _width - _border.right, (int) (i * _gridLineUnit * scaleFactor) + remainder);
        }
    }

    private void drawVerticalLine(Graphics g, int space, int chartHeight) {
        // draw vertical line
        for (int i = 1; i <= _numOfDisplayOnXAxis; i++) {
            g.drawLine(_border.left + space * i, _y, _border.left + space * i, chartHeight);
        }
    }

    private void drawBars(Graphics g, int space, int chartHeight, double scaleFactor) {
        // draw bars
        int barWidth = (int) ((double) _width / _numOfDisplayOnXAxis);
        int i;

        for (i = 0; i < _sampleValues.size(); i++) {
            g.setColor(Color.cyan);
            g.fillRect(_border.left + space * (i + 1) - barWidth / 2, chartHeight - (int) (scaleFactor * ((SampleValue) _sampleValues.get(_sampleValues.size() - i - 1)).getValue()), barWidth, (int) (scaleFactor * ((SampleValue) _sampleValues.get(_sampleValues.size() - i - 1)).getValue()));
            g.setColor(Color.blue);
            g.drawRect(_border.left + space * (i + 1) - barWidth / 2, chartHeight - (int) (scaleFactor * ((SampleValue) _sampleValues.get(_sampleValues.size() - i - 1)).getValue()), barWidth, (int) (scaleFactor * ((SampleValue) _sampleValues.get(_sampleValues.size() - i - 1)).getValue()));
            if (_isDrawBarLabel) {
                g.drawString(((SampleValue) (_sampleValues.get(_sampleValues.size() - (i + 1)))).getLabel(), space * (i + 1) - (getWidth() / 100), chartHeight + (getHeight() / 20));
            }
            if (_isDrawValueIndicator) {
                g.setColor(Color.black);
                g.fillOval(space * (i + 1) - ((getWidth() / 100) / 2), (chartHeight - (int) (scaleFactor * ((SampleValue) _sampleValues.get(_sampleValues.size() - i - 1)).getValue())) - ((getWidth() / 100) / 2), getWidth() / 100, getWidth() / 100);
            }
        }
    }

    private void plotLine(Graphics g, int space, int chartHeight, double scaleFactor) {
        g.setColor(Color.red);
        for (int i = 1; _sampleValues.size() > i; i++) {
            g.drawLine(_border.left + space * (i), chartHeight - (int) (scaleFactor * ((SampleValue) _sampleValues.get(_sampleValues.size() - i)).getValue()), _border.left + space * (i + 1), chartHeight - (int) (scaleFactor * ((SampleValue) _sampleValues.get(_sampleValues.size() - i - 1)).getValue()));
        }
    }

    /*
     * private void drawLabels(Graphics g){
     * g.drawString(_xAxisUnit,(this.getWidth() / 2), this.getHeight() - 5); }
     */

    public void setDrawVerticalLine(boolean value) {
        _isDrawVerticalLine = value;
    }

    public boolean isDrawVerticalLine() {
        return _isDrawVerticalLine;
    }

    public void setDrawYGridLine(boolean value) {
        _isDrawYGridLine = value;
    }

    public boolean isDrawYGridLine() {
        return _isDrawYGridLine;
    }

    public void setDrawSecondaryYGridLine(boolean value) {
        _isDrawSecondaryYGridLine = value;
    }

    public boolean isDrawValueIndicator() {
        return _isDrawValueIndicator;
    }

    public void setDrawValueIndicator(boolean value) {
        _isDrawValueIndicator = value;
    }

    public boolean isDrawSecondaryYGridLine() {
        return _isDrawSecondaryYGridLine;
    }

    public void setDrawBarLabel(boolean value) {
        _isDrawBarLabel = value;
    }

    public boolean isDrawBarLabel() {
        return _isDrawBarLabel;
    }

    public String getMouseOnBarLabel() {
        return _mouseOnBarLabel;
    }

    // override setChart() method in super class Presentation
    public void setChart(Chart chartToShow) {
        super.setChart(chartToShow);

        if (getChart() != null) {
            _sampleValues = getChart().getSampleValues();
        }
    }

    protected void setMouseOnBarLabel(String label) {
        _mouseOnBarLabel = label;
    }

    private void drawMouseOnBarLabel(Graphics g, String label) {
        int strWidth, strHeight;

        FontMetrics fm = g.getFontMetrics(g.getFont());
        strWidth = fm.stringWidth(label);
        strHeight = fm.getHeight();

        g.drawString(label, (getWidth() / 2) - (strWidth / 2), getHeight() - strHeight);
    }

    protected int calSampleValuesIndex(int xOnXAxis) {
        if (_sampleValues.size() == 0) {
            return -1;
        } else {
            int barWidth = (int) ((double) _width / _numOfDisplayOnXAxis);

            for (int i = 0; i < _sampleValues.size(); i++) {
                if ((_border.left + _space * (i + 1) - barWidth / 2 <= xOnXAxis) && (xOnXAxis < _border.left + _space * (i + 2) - barWidth / 2)) {
                    return _sampleValues.size() - 1 - i;
                }
            }
            // not in bar field
            return -1;
        }
    }

}
