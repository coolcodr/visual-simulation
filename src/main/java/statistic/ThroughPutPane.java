package statistic;

import javax.swing.*;
import java.awt.*;

public class ThroughPutPane extends JPanel {
	
	private AnalysisTool2 _analysisTool;
	
	private double _startTime;
	private double _analyzeTime;
	
	private int[] _data = new int[0];
	private int _max = 1;
	
	public ThroughPutPane() {
		super();
		
		this.setPreferredSize( new Dimension(600, 200) );
	}
	
	public void display(AnalysisTool2 analysisTool, double startTime, double analyzeTime, int[] data) {
		_analysisTool = analysisTool;
		_startTime = startTime;
		_analyzeTime = analyzeTime;
		
		_data = data;
		
		_max = _data[0];
		for (int i = 1; i < _data.length; i++) {
			if (_max < _data[i]) {
				_max = _data[i];
			}
		}
		repaint();
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		g.drawString(_analysisTool + "", 0, 0);
		
		int xlength = 600/_data.length;
		double ylength = (double)300/_max;
		
		int p1x = 50;
		int p1y = 50;
		int p2x = p1x + 600;
		int p2y = p1y + 300;
		
		g.drawString("Through-put", p1x/2, p1y/2);
		
		g.drawString("" + _max, p1x-10, p1y);
		g.drawString("0", p1x-10, p2y);
		
		
		g.drawString("" + _startTime, p1x-5, p2y+15);
		g.drawString("" + (_startTime+_analyzeTime), p2x-5, p2y+15);
		
		g.drawString("Time", (p1x+p2x)/2, p2y+20);
		
		g.drawLine(p1x, p2y, p2x, p2y);
		g.drawLine(p1x, p1y, p1x, p2y);	
		
		for (int i = 0; i < _data.length; i++) {
			g.fillOval(p1x+xlength*i-1, (int)(p1y+(ylength*(_max-_data[i])))-1, 3, 3);
		}
		
		g.setColor(new Color(255, 0, 0));
		
		for (int i = 0; i < _data.length-1; i++) {
			g.drawLine(p1x+xlength*i, (int)(p1y+(ylength*(_max-_data[i]))), p1x+xlength*(i+1), (int)(p1y+(ylength*(_max-_data[i+1]))) );
		}
		
		setVisible(true);
	}
}