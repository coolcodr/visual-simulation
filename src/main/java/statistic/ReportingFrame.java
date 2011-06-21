package statistic;

import javax.swing.*;
import java.awt.*;

public class ReportingFrame extends JFrame {
	
	private DataPanel _dataPanel;
	private GraphicPanel _gPanel;
	
	public ReportingFrame() {
		super();
		
		_dataPanel = new DataPanel();
		_gPanel = new GraphicPanel();
		setContentPane(_dataPanel);
		setSize( new Dimension(910, 610) );
	}
	
	public void display(String name, double[][] result2, double[] result1) {
		//JOptionPane.showMessageDialog(this, "hello");
		_dataPanel.display(name, result1);
		_dataPanel.setGraphicPanel(_gPanel);
		
		_gPanel.display(result2);
		
		this.setVisible(false);
		this.setVisible(true);
		toFront();
		show();
		repaint();
	}
}