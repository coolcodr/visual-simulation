package statistic;

import javax.swing.*;
import java.awt.*;

public class DataPanel extends JPanel {
	
	private String _name = null;
	private double[] _result = null;
	
	private JPanel _jPanel = new JPanel();
	
	public DataPanel() {
		super();
		
		this.setPreferredSize( new Dimension(400, 600) );
		
		this.setLayout(new BorderLayout());
	}
	
	public void display(String name, double[] result) {
		_name = name;
		_result = result;
		
		_jPanel = new JPanel();
		
		JLabel nameLabel = new JLabel(_name);
		
		JLabel[] termLabels = new JLabel[5];
		
		JLabel[] valueLabels = new JLabel[5];
		
		termLabels[0] = new JLabel("Throughput");
		termLabels[1] = new JLabel("Total Service Time");
		termLabels[2] = new JLabel("Mean Service Time");
		termLabels[3] = new JLabel("Maximum Service Time");
		termLabels[4] = new JLabel("Minimum Service Time");
		
		valueLabels[0] = new JLabel(" :     " + _result[0]);
		valueLabels[1] = new JLabel(" :     " + _result[1]);
		valueLabels[2] = new JLabel(" :     " + _result[2]);
		valueLabels[3] = new JLabel(" :     " + _result[3]);
		valueLabels[4] = new JLabel(" :     " + _result[4]);
		
		
		_jPanel.setLayout(new GridLayout(6, 2));
		
		_jPanel.add(nameLabel);
		_jPanel.add(new JLabel());
		_jPanel.add(termLabels[0]);
		_jPanel.add(valueLabels[0]);
		_jPanel.add(termLabels[1]);
		_jPanel.add(valueLabels[1]);
		_jPanel.add(termLabels[2]);
		_jPanel.add(valueLabels[2]);
		_jPanel.add(termLabels[3]);
		_jPanel.add(valueLabels[3]);
		_jPanel.add(termLabels[4]);
		_jPanel.add(valueLabels[4]);
		
		this.add(BorderLayout.WEST, _jPanel);
	}
	
	public void setGraphicPanel(GraphicPanel gPanel) {
		this.add(BorderLayout.EAST, gPanel);
	}
}