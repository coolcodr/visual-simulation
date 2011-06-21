
package chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.accessibility.*;
import java.util.*;

import chart.*;
import print.*;

public class TmpChartFrame extends JFrame{
	private Vector _charts;
	private JTextField pointText;
	private JTextField pointLabelText;

	private JTextField xUnitText;
	private JTextField yUnitText;
	private JScrollPane _scroll;
	private JPanel _chartsPane;
	private JPanel _buttonPane;
	private JButton _printButton;

	private final int SCROLL_WIDTH = 700;
	private final int SCROLL_HEIGHT = 550;

	public TmpChartFrame() {
		_charts=new Vector();
		_chartsPane=new JPanel();
		_buttonPane = new JPanel();
		_printButton = new JButton("Print");
		_chartsPane.setLayout(new FlowLayout());
		_buttonPane.setLayout(new FlowLayout());

		setTitle("Charts after Simulation");

		//this.setBounds(0,0,750,650);
		this.setSize( new Dimension(750, 650) );

		_scroll = new JScrollPane(_chartsPane);
		_scroll.setBounds((int)((this.getSize().getWidth()/2)-(SCROLL_WIDTH/2)), (int)((this.getSize().getHeight()/2)-(SCROLL_HEIGHT/2)), SCROLL_WIDTH, SCROLL_HEIGHT);

		//p.add(myChart);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(_scroll, BorderLayout.CENTER);
		this.getContentPane().add(_buttonPane, BorderLayout.SOUTH);
		//this.setVisible(true);
		_buttonPane.add(_printButton);
		//this.addWindowListener(new WinAdapter());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _printButton_actionPerformed(e);
            }
        });

	}
	void _printButton_actionPerformed(ActionEvent e) {
        Chart[] chartAry = new Chart[_charts.size()];
        for ( int i = 0  ; i < _charts.size() ; i ++ )
        {
        	chartAry[i] = (Chart)_charts.elementAt(i);
        }
        PrintEditor printEditor = new PrintEditor (chartAry);
		printEditor.setVisible (true);
    }
    public Chart[] getCharts ()
    {
        Chart[] chartAry = new Chart[_charts.size()];
        for ( int i = 0  ; i < _charts.size() ; i ++ )
        {
            chartAry[i] = (Chart)_charts.elementAt(i);
        }
        return chartAry;
    }
	public void addChart(Chart chart){
		int totalWidth=0;
		int totalHeight=0;

		_charts.add(chart);
		for(int i=0; i<_charts.size(); i++){
			totalWidth+=((Chart)_charts.get(i)).getWidth();
		}

		_chartsPane.setBounds(_chartsPane.getLocation().x, _chartsPane.getLocation().y, totalWidth, _chartsPane.getHeight());
		_chartsPane.add(chart);
		_scroll.setViewportView(_chartsPane);
	}

	/*
	protected void paintComponent(Graphics g){
		_scroll.setBounds((int)((this.getSize().getWidth()/2)-(SCROLL_WIDTH/2)), (int)((this.getSize().getHeight()/2)-(SCROLL_HEIGHT/2)), SCROLL_WIDTH, SCROLL_HEIGHT);
		System.out.println("this.getSize().getWidth()"+this.getSize().getWidth()+" this.getSize().getHeight()"+this.getSize().getHeight());
		super.paintComponents(g);
	}

	public void repaint(){
		_scroll.setBounds((int)((this.getSize().getWidth()/2)-(SCROLL_WIDTH/2)), (int)((this.getSize().getHeight()/2)-(SCROLL_HEIGHT/2)), SCROLL_WIDTH, SCROLL_HEIGHT);
		System.out.println("this.getSize().getWidth()"+this.getSize().getWidth()+" this.getSize().getHeight()"+this.getSize().getHeight());
		super.repaint();
	}
	*/

	public static void main(String[] args) {
		new TmpChartFrame();
	}

	static class WinAdapter implements WindowListener {
		public void windowActivated(WindowEvent e){}
		public void windowClosed(WindowEvent e){}

		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
		public void windowDeactivated(WindowEvent e){}
		public void windowDeiconified(WindowEvent e){}
		public void windowIconified(WindowEvent e){}
		public void windowOpened(WindowEvent e){}
	}

}