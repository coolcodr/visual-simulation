package statistic;

import javax.swing.*;
import java.awt.*;

public class SummaryDataTextPane extends JPanel {
	
	private AnalysisTool2[] _analysisTool;
	private double[] _serviceTimeMean;
	private double[] _utilizations;
	
	private JLabel[][] _labels;
	
	private JPanel _textPanel;
	private JPanel _dataPanel;
	
	public SummaryDataTextPane() {
		super();
		
		_textPanel = new JPanel();
		_dataPanel = new JPanel();
		
		this.setLayout(new BorderLayout());
		
		add(BorderLayout.WEST, _textPanel);
		add(BorderLayout.EAST, _dataPanel);
		
		_textPanel.setPreferredSize( new Dimension(150, 200) );
		_dataPanel.setPreferredSize( new Dimension(700, 200) );
		
		this.setPreferredSize( new Dimension(850, 200) );
	}
	
	public void display(AnalysisTool2[] analysisTool, double[] serviceTimeMean, double[] utilizations) {
		
		_analysisTool = analysisTool;
		_serviceTimeMean = serviceTimeMean;
		_utilizations = utilizations;
		
		int xlength = 3;
		int ylength = _analysisTool.length;
		
		_labels = new JLabel[xlength][ylength+1];
		_dataPanel.setLayout(new GridLayout(xlength, ylength + 1));
		
		for (int i = 1; i < ylength+1; i++) {
			_labels[0][i] = new JLabel("" + analysisTool[i-1]);
			_labels[1][i] = new JLabel("" + Math.round(_serviceTimeMean[i-1]));
			_labels[2][i] = new JLabel("" + Math.round(_utilizations[i-1]*100));
		}
		
		_labels[0][0] = new JLabel("Analysis Tool Name :");
		_labels[1][0] = new JLabel("Summary of Service Time Mean :");
		_labels[2][0] = new JLabel("Summary of Utilization :");
	
		for (int i = 0; i < xlength; i++) {
			for (int j = 0; j < ylength+1; j++) {
				_dataPanel.add(_labels[i][j]);
			}
		}
		
		_dataPanel.setVisible(true);
	}
}