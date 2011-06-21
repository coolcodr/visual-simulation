package statistic;

import javax.swing.*;
import java.awt.*;

public class SummaryReport extends JFrame {
	
	private AnalysisTool2[] _analysisTools;
	private double[] _serviceTimeMean;
	private double[] _utilizations;
	
	private JTabbedPane _contentPane;
	
	private SummaryDataTextPane _dPane;
	private SummaryServiceTimeMeanPane _sPane;
	private SummaryUtilizationsPane _uPane;
	
	public SummaryReport() {
		super();
		
		_contentPane = new JTabbedPane();
		
		_dPane = new SummaryDataTextPane();
		_sPane = new SummaryServiceTimeMeanPane();
		_uPane = new SummaryUtilizationsPane();
		
		setContentPane(_contentPane);
		
		_contentPane.add("Summary of Data", _dPane);
		_contentPane.add("Summary of Service Time Means", _sPane);
		_contentPane.add("Summary of Utilizations", _uPane);
		
		
		this.setSize(new Dimension(900, 800));
	}
	
	public void setData(AnalysisTool2[] analysisTools, double[] serviceTimeMean, double[] utilizations) {
		_analysisTools = analysisTools;
		_serviceTimeMean = serviceTimeMean;
		_utilizations = utilizations;
	}
	
	public void display() {
		_dPane.display(_analysisTools, _serviceTimeMean, _utilizations);
		_sPane.display(_analysisTools, _serviceTimeMean);
		_uPane.display(_analysisTools, _utilizations);
		
		this.setVisible(true);
	}
}