
package chart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BarChartMain {

	public static void main(String args[]){
		BarChartFrame f = new BarChartFrame();
		WinAdapter adapter = new WinAdapter();

		f.addWindowListener(adapter);
	}

	static class WinAdapter implements WindowListener {
		public void windowActivated(WindowEvent e){}
		public void windowClosed(WindowEvent e){}

		public void windowClosing(WindowEvent e){
			System.out.println("closing");
			System.exit(0);
		}
		public void windowDeactivated(WindowEvent e){}
		public void windowDeiconified(WindowEvent e){}
		public void windowIconified(WindowEvent e){}
		public void windowOpened(WindowEvent e){}
	}

}
