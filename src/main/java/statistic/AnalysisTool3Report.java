package statistic;

import javax.swing.*;
import java.awt.*;

public class AnalysisTool3Report extends JFrame {
	
	private AnalysisTool2 _analysisTool;
	private double _startTime;
	private double _analyzeTime;
	
	private double[] _serviceTimes;
	private int[] _throughPut;
	private int[] _objectInComponent;
	private double[] _utilizations;
	
	private JTabbedPane _contentPane;
	
	private DataTextPane _dPane;
	private GraphicPane _gPane;
	private ThroughPutPane _tPane;
	private ServiceTimeMeanPane _sPane;
	
	public AnalysisTool3Report() {
		super();
		
		_contentPane = new JTabbedPane();
		_dPane = new DataTextPane();
		_gPane = new GraphicPane();
		_tPane = new ThroughPutPane();
		_sPane = new ServiceTimeMeanPane();
		
		setContentPane(_contentPane);
		
		_contentPane.add("Data", _dPane);
		_contentPane.add("Utilization", _gPane);
		_contentPane.add("Through-put", _tPane);
		_contentPane.add("Service Time Mean", _sPane);
		
		this.setSize(new Dimension(900, 800));
	}
	
	public void setData(AnalysisTool2 analysisTool, double startTime, double analyzeTime, double[] serviceTimes, int[] throughPut, int[] objectInComponent, double[] utilizations) {
		_analysisTool = analysisTool;
		
		_startTime = startTime;
		_analyzeTime = analyzeTime;
		
		_serviceTimes = serviceTimes;
		_throughPut = throughPut;
		_objectInComponent = objectInComponent;
		_utilizations = utilizations;
	}
	
	public void display() {
		_dPane.display(_analysisTool, _startTime, _analyzeTime, _serviceTimes, _throughPut, _objectInComponent, _utilizations);
		_gPane.display(_analysisTool, _startTime, _analyzeTime, _utilizations);
		_tPane.display(_analysisTool, _startTime, _analyzeTime, _throughPut);
		_sPane.display(_analysisTool, _startTime, _analyzeTime, _serviceTimes, _throughPut, _objectInComponent);
		
		this.setVisible(true);
	}
}