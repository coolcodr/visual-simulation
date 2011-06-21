package statistic;

import javax.swing.*;
import java.awt.*;

public class ServiceTimeMeanPane extends JPanel {
	
	private AnalysisTool2 _analysisTool;
	
	private double _startTime;
	private double _analyzeTime;
	
	private double[] _serviceTimes = new double[0];
	private int[] _throughPut = new int[0];
	private int[] _objectInComponent = new int[0];
	
	private double _data[];
	
	private double _max = 1;
	
	public ServiceTimeMeanPane() {
		super();
		
		this.setPreferredSize( new Dimension(600, 200) );
	}
	
	public void display(AnalysisTool2 analysisTool, double startTime, double analyzeTime, double[] serviceTimes, int[] throughPut, int[] objectInComponent) {
		_analysisTool = analysisTool;
		_startTime = startTime;
		_analyzeTime = analyzeTime;
		
		_serviceTimes = serviceTimes;
		_throughPut = throughPut;
		_objectInComponent = objectInComponent;
		
		_data = new double[_serviceTimes.length];
		for (int i = 0; i < _serviceTimes.length; i++) {
			_data[i] = _serviceTimes[i]/(_throughPut[i]);
		}
		
		_max = _data[0];
		for (int i = 1; i < _data.length; i++) {
			if (_max < _data[i]) {
				_max = _data[i];
			}
		}
		
		double no = 0;
		for (int i = 0; i < _data.length; i++) {
			no += _data[i];
		}
		System.out.println(no/_data.length);
		
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
		
		g.drawString("Mean of Service Time", p1x/2, p1y/2);
		
		g.drawString("" + _max, p1x-10, p1y);
		g.drawString("0", p1x-10, p2y);
		
		
		g.drawString("" + _startTime, p1x-5, p2y+15);
		g.drawString("" + (_startTime+_analyzeTime), p2x-5, p2y+15);
		
		g.drawString("Time", (p1x+p2x)/2, p2y+20);
		
		g.drawLine(p1x, p2y, p2x, p2y);
		g.drawLine(p1x, p1y, p1x, p2y);	
		
		double temp = ylength;
		for(int i = 0; i < _max; i++) {
			g.drawLine(p1x, (int)(p1y-temp), p1x, (int)(p2y-temp));	
			temp= temp+ylength;
		}

		for (int i = 0; i < _data.length; i++) {
			g.fillOval(p1x+xlength*i-1, (int)(p1y+(ylength*(_max-_data[i]))), 3, 3);
			
		}
		
		g.setColor(new Color(255, 0, 0));
		
		for (int i = 0; i < _data.length-1; i++) {
			g.drawLine(p1x+xlength*i, (int)(p1y+(ylength*(_max-_data[i]))), p1x+xlength*(i+1), (int)(p1y+(ylength*(_max-_data[i+1]))) );
		}
		
		setVisible(true);
	}
}