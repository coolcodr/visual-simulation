package statistic;

import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel {
	
	private double[][] _result = null;
	
	public GraphicPanel() {
		super();
		
		this.setPreferredSize( new Dimension(500, 600) );
	}
	
	public void display(double[][] result) {
		_result = result;
		
		repaint();
	}
	
	public void paint(Graphics g) {
		double total;
		
		int p1, p2;
		
		g.drawString("Total service time", 0, 100);
		g.drawString("Throughput", 0, 200);
		g.drawString("Maximum service time", 0, 300);
		g.drawString("Minimum service time", 0, 400);
		g.drawString("Mean service Time", 0, 500);
		for (int i = 0; i < 5; i++) {
			total = _result[0][i] + _result[1][i] + _result[2][i] + _result[3][i] + _result[4][i];
			g.setColor(new Color(50*i, 0, 0));
			
			for (int j = 0; j < 4; j++) {
				p1 = 100+100*i - new Double(_result[j][i]/total*100).intValue();
				p2 = 100+100*i - new Double(_result[j+1][i]/total*100).intValue();
				g.drawLine(200+50*j, p1, 200+50*(j+1), p2);
				g.fillOval(200+50*j-2, p1-2, 4, 4);
				g.fillOval(200+50*(j+1)-2, p2-2, 4, 4);
			}
		}
	}
}