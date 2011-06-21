package diagram;

import javax.swing.JComponent;
import java.awt.*;
import java.lang.reflect.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class SourceRenderer extends DefaultRenderer {
	
	public void paint(DiagramElement d, Graphics g) {
		
		super.paint(d, g);
		
		int w, h;
		int size;
		
		w = d.getWidth();
		h = d.getHeight();
		
		if (w < h)
			size = w;
		else
			size = h;
		
		if (d.isOpaque()) {
			g.setColor(d.getBackground());
			g.fillRect(0,0,w,h);
		}
		g.setColor(d.getForeground());
		g.drawRect(0, 0, w-1, h-1);
		
		int[] x = new int[4];
		int[] y = new int[4];
		
		g.setColor(new Color(192, 192, 192));
		
		x[0] = (int)(size*0.3); x[1] = (int)(size*0.1); x[2] = (int)(size*0.1); x[3] = (int)(size*0.3);
		y[0] = (int)(size*0.1); y[1] = (int)(size*0.3); y[2] = (int)(size*0.6); y[3] = (int)(size*0.6);
		g.fillPolygon(x, y, 4);
		g.fillRect((int)(size*0.3), (int)(size*0.1), (int)(size*0.3), (int)(size*0.5));
		
		g.setColor(new Color(255, 0, 0));
		
		
		
		x[0] = (int)(size*0.8); x[1] = (int)(size*0.8); x[2] = (int)(size*0.9);
		y[0] = (int)(size*0.35)-1; y[1] = (int)(size*0.55); y[2] = (int)(size*0.45);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.7), (int)(size*0.4), (int)(size*0.15), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.1), (int)(size*0.6), (int)(size*0.1), (int)(size*0.3));
		g.drawLine((int)(size*0.1), (int)(size*0.3), (int)(size*0.3), (int)(size*0.1));
		g.drawLine((int)(size*0.3), (int)(size*0.1), (int)(size*0.6), (int)(size*0.1));
		g.drawLine((int)(size*0.6), (int)(size*0.1), (int)(size*0.6), (int)(size*0.6));
		g.drawLine((int)(size*0.55), (int)(size*0.1), (int)(size*0.55), (int)(size*0.6));
		g.drawLine((int)(size*0.1), (int)(size*0.6), (int)(size*0.7), (int)(size*0.6));
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class ServerRenderer extends DefaultRenderer {
	
	public void paint(DiagramElement d, Graphics g) {
		
		super.paint(d, g);
		
		int w, h;
		int size;
		
		w = d.getWidth();
		h = d.getHeight();
		
		if (w < h)
			size = w;
		else
			size = h;
		
		if (d.isOpaque()) {
			g.setColor(d.getBackground());
			g.fillRect(0,0,w,h);
		}
		g.setColor(d.getForeground());
		g.drawRect(0, 0, w-1, h-1);
		
		int[] x = new int[4];
		int[] y = new int[4];
		
		g.setColor(new Color(192, 192, 192));
		x[0] = (int)(size*0.4); x[1] = (int)(size*0.45); x[2] = (int)(size*0.45); x[3] = (int)(size*0.4);
		y[0] = (int)(size*0.1); y[1] = (int)(size*0.2); y[2] = (int)(size*0.5); y[3] = (int)(size*0.6);
		g.fillPolygon(x, y, 4);
		x[0] = (int)(size*0.55); x[1] = (int)(size*0.6); x[2] = (int)(size*0.6); x[3] = (int)(size*0.55);
		y[0] = (int)(size*0.2); y[1] = (int)(size*0.1); y[2] = (int)(size*0.6); y[3] = (int)(size*0.5);
		g.fillPolygon(x, y, 4);
		g.fillRect((int)(size*0.25), (int)(size*0.1), (int)(size*0.15), (int)(size*0.5));
		g.fillRect((int)(size*0.6), (int)(size*0.1), (int)(size*0.15), (int)(size*0.5));
		g.fillRect((int)(size*0.45), (int)(size*0.2), (int)(size*0.1), (int)(size*0.3));
		
		g.setColor(new Color(255, 0, 0));
		x[0] = (int)(size*0.15); x[1] = (int)(size*0.2); x[2] = (int)(size*0.15);
		y[0] = (int)(size*0.25)-1; y[1] = (int)(size*0.35); y[2] = (int)(size*0.45);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.3), (int)(size*0.06), (int)(size*0.1));
		
		x[0] = (int)(size*0.85); x[1] = (int)(size*0.9); x[2] = (int)(size*0.85);
		y[0] = (int)(size*0.25)-1; y[1] = (int)(size*0.35); y[2] = (int)(size*0.45);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.8), (int)(size*0.3), (int)(size*0.06), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.25), (int)(size*0.1), (int)(size*0.4), (int)(size*0.1));
		g.drawLine((int)(size*0.4), (int)(size*0.1), (int)(size*0.45), (int)(size*0.2));
		g.drawLine((int)(size*0.45), (int)(size*0.2), (int)(size*0.55), (int)(size*0.2));
		g.drawLine((int)(size*0.55), (int)(size*0.2), (int)(size*0.6), (int)(size*0.1));
		g.drawLine((int)(size*0.6), (int)(size*0.1), (int)(size*0.75), (int)(size*0.1));
		g.drawLine((int)(size*0.75), (int)(size*0.1), (int)(size*0.75), (int)(size*0.6));
		g.drawLine((int)(size*0.25), (int)(size*0.1), (int)(size*0.25), (int)(size*0.6));
		g.drawLine((int)(size*0.25), (int)(size*0.6), (int)(size*0.4), (int)(size*0.6));
		g.drawLine((int)(size*0.4), (int)(size*0.6), (int)(size*0.45), (int)(size*0.5));
		g.drawLine((int)(size*0.45), (int)(size*0.5), (int)(size*0.55), (int)(size*0.5));
		g.drawLine((int)(size*0.55), (int)(size*0.5), (int)(size*0.6), (int)(size*0.6));
		g.drawLine((int)(size*0.6), (int)(size*0.6), (int)(size*0.75), (int)(size*0.6));
		g.drawLine((int)(size*0.3), (int)(size*0.1), (int)(size*0.3), (int)(size*0.6));
		g.drawLine((int)(size*0.45), (int)(size*0.2), (int)(size*0.45), (int)(size*0.5));
		g.drawLine((int)(size*0.55), (int)(size*0.2), (int)(size*0.55), (int)(size*0.5));
		g.drawLine((int)(size*0.7), (int)(size*0.1), (int)(size*0.7), (int)(size*0.6));
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class NServerRenderer extends DefaultRenderer {
	
	public void paint(DiagramElement d, Graphics g) {
		
		super.paint(d, g);
		
		int w, h;
		int size;
		
		w = d.getWidth();
		h = d.getHeight();
		
		if (w < h)
			size = w;
		else
			size = h;
		
		if (d.isOpaque()) {
			g.setColor(d.getBackground());
			g.fillRect(0,0,w,h);
		}
		g.setColor(d.getForeground());
		g.drawRect(0, 0, w-1, h-1);
		
		int[] x = new int[4];
		int[] y = new int[4];
		
		g.setColor(new Color(192, 192, 192));
		
		x[0] = (int)(size*0.4); x[1] = (int)(size*0.45); x[2] = (int)(size*0.45); x[3] = (int)(size*0.4);
		y[0] = (int)(size*0.1); y[1] = (int)(size*0.2); y[2] = (int)(size*0.5); y[3] = (int)(size*0.6);
		g.fillPolygon(x, y, 4);
		x[0] = (int)(size*0.55); x[1] = (int)(size*0.6); x[2] = (int)(size*0.6); x[3] = (int)(size*0.55);
		y[0] = (int)(size*0.2); y[1] = (int)(size*0.1); y[2] = (int)(size*0.6); y[3] = (int)(size*0.5);
		g.fillPolygon(x, y, 4);
		g.fillRect((int)(size*0.25), (int)(size*0.1), (int)(size*0.15), (int)(size*0.5));
		g.fillRect((int)(size*0.6), (int)(size*0.1), (int)(size*0.15), (int)(size*0.5));
		g.fillRect((int)(size*0.45), (int)(size*0.2), (int)(size*0.1), (int)(size*0.3));
		
		g.setColor(new Color(255, 0, 0));
		
		x[0] = (int)(size*0.15); x[1] = (int)(size*0.2); x[2] = (int)(size*0.15);
		y[0] = (int)(size*0.25)-1; y[1] = (int)(size*0.35); y[2] = (int)(size*0.45);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.3), (int)(size*0.06), (int)(size*0.1));
		
		x[0] = (int)(size*0.85); x[1] = (int)(size*0.9); x[2] = (int)(size*0.85);
		y[0] = (int)(size*0.25)-1; y[1] = (int)(size*0.35); y[2] = (int)(size*0.45);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.8), (int)(size*0.3), (int)(size*0.06), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.25), (int)(size*0.1), (int)(size*0.4), (int)(size*0.1));
		g.drawLine((int)(size*0.4), (int)(size*0.1), (int)(size*0.45), (int)(size*0.2));
		g.drawLine((int)(size*0.45), (int)(size*0.2), (int)(size*0.55), (int)(size*0.2));
		g.drawLine((int)(size*0.55), (int)(size*0.2), (int)(size*0.6), (int)(size*0.1));
		g.drawLine((int)(size*0.6), (int)(size*0.1), (int)(size*0.75), (int)(size*0.1));
		g.drawLine((int)(size*0.75), (int)(size*0.1), (int)(size*0.75), (int)(size*0.6));
		g.drawLine((int)(size*0.25), (int)(size*0.1), (int)(size*0.25), (int)(size*0.6));
		g.drawLine((int)(size*0.25), (int)(size*0.6), (int)(size*0.4), (int)(size*0.6));
		g.drawLine((int)(size*0.4), (int)(size*0.6), (int)(size*0.45), (int)(size*0.5));
		g.drawLine((int)(size*0.45), (int)(size*0.5), (int)(size*0.55), (int)(size*0.5));
		g.drawLine((int)(size*0.55), (int)(size*0.5), (int)(size*0.6), (int)(size*0.6));
		g.drawLine((int)(size*0.6), (int)(size*0.6), (int)(size*0.75), (int)(size*0.6));
		g.drawLine((int)(size*0.3), (int)(size*0.1), (int)(size*0.3), (int)(size*0.6));
		g.drawLine((int)(size*0.45), (int)(size*0.2), (int)(size*0.45), (int)(size*0.5));
		g.drawLine((int)(size*0.55), (int)(size*0.2), (int)(size*0.55), (int)(size*0.5));
		g.drawLine((int)(size*0.7), (int)(size*0.1), (int)(size*0.7), (int)(size*0.6));
		
		g.setColor(new Color(192, 192, 192));
		
		g.fillRect((int)(size*0.45)-1, (int)(size*0.25)-1, (int)(size*0.1)+2, (int)(size*0.2)+2);
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.45)-1, (int)(size*0.25)+2, (int)(size*0.45)-1, (int)(size*0.45)-4);
		g.drawLine((int)(size*0.45)-1, (int)(size*0.25)+2, (int)(size*0.55)-1, (int)(size*0.45)-4);
		g.drawLine((int)(size*0.55)-1, (int)(size*0.25)+2, (int)(size*0.55)-1, (int)(size*0.45)-4);
		
		g.drawLine((int)(size*0.45), (int)(size*0.25)+3, (int)(size*0.45), (int)(size*0.45)-3);
		g.drawLine((int)(size*0.45), (int)(size*0.25)+3, (int)(size*0.55), (int)(size*0.45)-3);
		g.drawLine((int)(size*0.55), (int)(size*0.25)+3, (int)(size*0.55), (int)(size*0.45)-3);
		
		g.drawLine((int)(size*0.45)+1, (int)(size*0.25)+4, (int)(size*0.45)+1, (int)(size*0.45)-2);
		g.drawLine((int)(size*0.45)+1, (int)(size*0.25)+4, (int)(size*0.55)+1, (int)(size*0.45)-2);
		g.drawLine((int)(size*0.55)+1, (int)(size*0.25)+4, (int)(size*0.55)+1, (int)(size*0.45)-2);
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class SinkRenderer extends DefaultRenderer {
	
	public void paint(DiagramElement d, Graphics g) {
		
		super.paint(d, g);
		
		int w, h;
		int size;
		
		w = d.getWidth();
		h = d.getHeight();
		
		if (w < h)
			size = w;
		else
			size = h;
		
		if (d.isOpaque()) {
			g.setColor(d.getBackground());
			g.fillRect(0,0,w,h);
		}
		g.setColor(d.getForeground());
		g.drawRect(0, 0, w-1, h-1);
		
		int[] x = new int[4];
		int[] y = new int[4];
		
		g.setColor(new Color(192, 192, 192));
		x[0] = (int)(size*0.7); x[1] = (int)(size*0.9); x[2] = (int)(size*0.9); x[3] = (int)(size*0.7);
		y[0] = (int)(size*0.1); y[1] = (int)(size*0.3); y[2] = (int)(size*0.6); y[3] = (int)(size*0.6);
		g.fillPolygon(x, y, 4);
		g.fillRect((int)(size*0.4), (int)(size*0.1), (int)(size*0.3), (int)(size*0.5));
		
		g.setColor(new Color(255, 0, 0));
		x[0] = (int)(size*0.2); x[1] = (int)(size*0.3); x[2] = (int)(size*0.2);
		y[0] = (int)(size*0.35)-1; y[1] = (int)(size*0.45); y[2] = (int)(size*0.55);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.4), (int)(size*0.15), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.4), (int)(size*0.1), (int)(size*0.7), (int)(size*0.1));
		g.drawLine((int)(size*0.7), (int)(size*0.1), (int)(size*0.9), (int)(size*0.3));
		g.drawLine((int)(size*0.9), (int)(size*0.3), (int)(size*0.9), (int)(size*0.6));
		g.drawLine((int)(size*0.4), (int)(size*0.1), (int)(size*0.4), (int)(size*0.6));
		g.drawLine((int)(size*0.3), (int)(size*0.6), (int)(size*0.9), (int)(size*0.6));
		g.drawLine((int)(size*0.45), (int)(size*0.1), (int)(size*0.45), (int)(size*0.6));
		
		
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class SplitterRenderer extends DefaultRenderer {
	
	public void paint(DiagramElement d, Graphics g) {
		
		super.paint(d, g);
		
		int w, h;
		int size;
		
		w = d.getWidth();
		h = d.getHeight();
		
		if (w < h)
			size = w;
		else
			size = h;
		
		if (d.isOpaque()) {
			g.setColor(d.getBackground());
			g.fillRect(0,0,w,h);
		}
		g.setColor(d.getForeground());
		g.drawRect(0, 0, w-1, h-1);
		
		int[] x = new int[4];
		int[] y = new int[4];
		
		g.setColor(new Color(192, 192, 192));
		x[0] = (int)(size*0.4); x[1] = (int)(size*0.45); x[2] = (int)(size*0.45); x[3] = (int)(size*0.4);
		y[0] = (int)(size*0.2); y[1] = (int)(size*0.25); y[2] = (int)(size*0.45); y[3] = (int)(size*0.5);
		g.fillPolygon(x, y, 4);
		x[0] = (int)(size*0.55); x[1] = (int)(size*0.65); x[2] = (int)(size*0.65); x[3] = (int)(size*0.55);
		y[0] = (int)(size*0.25); y[1] = (int)(size*0.15); y[2] = (int)(size*0.55); y[3] = (int)(size*0.45);
		g.fillPolygon(x, y, 4);
		x[0] = (int)(size*0.65); x[1] = (int)(size*0.7); x[2] = (int)(size*0.7); x[3] = (int)(size*0.65);
		y[0] = (int)(size*0.15); y[1] = (int)(size*0.15); y[2] = (int)(size*0.3); y[3] = (int)(size*0.35);
		g.fillPolygon(x, y, 4);
		x[0] = (int)(size*0.65); x[1] = (int)(size*0.7); x[2] = (int)(size*0.7); x[3] = (int)(size*0.65);
		y[0] = (int)(size*0.35); y[1] = (int)(size*0.4); y[2] = (int)(size*0.55); y[3] = (int)(size*0.55);
		g.fillPolygon(x, y, 4);
		g.fillRect((int)(size*0.25), (int)(size*0.2), (int)(size*0.159), (int)(size*0.3));
		g.fillRect((int)(size*0.44), (int)(size*0.25), (int)(size*0.12), (int)(size*0.2));
		g.fillRect((int)(size*0.69), (int)(size*0.15), (int)(size*0.06), (int)(size*0.15));
		g.fillRect((int)(size*0.69), (int)(size*0.4), (int)(size*0.06), (int)(size*0.15));
		
		g.setColor(new Color(255, 0, 0));
		x[0] = (int)(size*0.15); x[1] = (int)(size*0.2); x[2] = (int)(size*0.15);
		y[0] = (int)(size*0.25)-1; y[1] = (int)(size*0.35); y[2] = (int)(size*0.45);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.3), (int)(size*0.06), (int)(size*0.1));
		x[0] = (int)(size*0.85); x[1] = (int)(size*0.9); x[2] = (int)(size*0.85);
		y[0] = (int)(size*0.1)-1; y[1] = (int)(size*0.2); y[2] = (int)(size*0.3);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.8), (int)(size*0.15), (int)(size*0.06), (int)(size*0.1));
		x[0] = (int)(size*0.85); x[1] = (int)(size*0.9); x[2] = (int)(size*0.85);
		y[0] = (int)(size*0.4)-1; y[1] = (int)(size*0.5); y[2] = (int)(size*0.6);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.8), (int)(size*0.45), (int)(size*0.06), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.25), (int)(size*0.2), (int)(size*0.4), (int)(size*0.2));
		g.drawLine((int)(size*0.4), (int)(size*0.2), (int)(size*0.45), (int)(size*0.25));
		g.drawLine((int)(size*0.45), (int)(size*0.25), (int)(size*0.55), (int)(size*0.25));
		g.drawLine((int)(size*0.55), (int)(size*0.25), (int)(size*0.65), (int)(size*0.15));
		g.drawLine((int)(size*0.65), (int)(size*0.15), (int)(size*0.75), (int)(size*0.15));
		g.drawLine((int)(size*0.75), (int)(size*0.15), (int)(size*0.75), (int)(size*0.3));
		g.drawLine((int)(size*0.7), (int)(size*0.3), (int)(size*0.75), (int)(size*0.3));
		g.drawLine((int)(size*0.7), (int)(size*0.3), (int)(size*0.65), (int)(size*0.35));
		g.drawLine((int)(size*0.65), (int)(size*0.35), (int)(size*0.7), (int)(size*0.4));
		g.drawLine((int)(size*0.7), (int)(size*0.4), (int)(size*0.75), (int)(size*0.4));
		g.drawLine((int)(size*0.75), (int)(size*0.4), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.2), (int)(size*0.25), (int)(size*0.5));
		g.drawLine((int)(size*0.25), (int)(size*0.5), (int)(size*0.4), (int)(size*0.5));
		g.drawLine((int)(size*0.4), (int)(size*0.5), (int)(size*0.45), (int)(size*0.45));
		g.drawLine((int)(size*0.45), (int)(size*0.45), (int)(size*0.55), (int)(size*0.45));
		g.drawLine((int)(size*0.55), (int)(size*0.45), (int)(size*0.65), (int)(size*0.55));
		g.drawLine((int)(size*0.65), (int)(size*0.55), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.3), (int)(size*0.2), (int)(size*0.3), (int)(size*0.5));
		g.drawLine((int)(size*0.45), (int)(size*0.25), (int)(size*0.45), (int)(size*0.45));
		g.drawLine((int)(size*0.55), (int)(size*0.25), (int)(size*0.65), (int)(size*0.35));
		g.drawLine((int)(size*0.55), (int)(size*0.45), (int)(size*0.65), (int)(size*0.35));
		g.drawLine((int)(size*0.7), (int)(size*0.15), (int)(size*0.7), (int)(size*0.3));
		g.drawLine((int)(size*0.7), (int)(size*0.4), (int)(size*0.7), (int)(size*0.55));
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

// add by Kenny
class ChartRenderer extends DefaultRenderer {
	
	public void paint(DiagramElement d, Graphics g) {
		
		super.paint(d, g);
		
		int w, h;
		int size;
		
		w = d.getWidth();
		h = d.getHeight();
		
		if (w < h)
			size = w;
		else
			size = h;
		
		if (d.isOpaque()) {
			g.setColor(d.getBackground());
			g.fillRect(0,0,w,h);
		}
		g.setColor(d.getForeground());
		g.drawRect(0, 0, w-1, h-1);
		
		int[] x = new int[4];
		int[] y = new int[4];
		
		g.setColor(new Color(192, 192, 192));
		x[0] = (int)(size*0.7); x[1] = (int)(size*0.9); x[2] = (int)(size*0.9); x[3] = (int)(size*0.7);
		y[0] = (int)(size*0.1); y[1] = (int)(size*0.3); y[2] = (int)(size*0.6); y[3] = (int)(size*0.6);
		g.fillPolygon(x, y, 4);
		g.fillRect((int)(size*0.4), (int)(size*0.1), (int)(size*0.3), (int)(size*0.5));
		
		g.setColor(new Color(255, 0, 0));
		x[0] = (int)(size*0.2); x[1] = (int)(size*0.3); x[2] = (int)(size*0.2);
		y[0] = (int)(size*0.35)-1; y[1] = (int)(size*0.45); y[2] = (int)(size*0.55);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.4), (int)(size*0.15), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.4), (int)(size*0.1), (int)(size*0.7), (int)(size*0.1));
		g.drawLine((int)(size*0.7), (int)(size*0.1), (int)(size*0.9), (int)(size*0.3));
		g.drawLine((int)(size*0.9), (int)(size*0.3), (int)(size*0.9), (int)(size*0.6));
		g.drawLine((int)(size*0.4), (int)(size*0.1), (int)(size*0.4), (int)(size*0.6));
		g.drawLine((int)(size*0.3), (int)(size*0.6), (int)(size*0.9), (int)(size*0.6));
		g.drawLine((int)(size*0.45), (int)(size*0.1), (int)(size*0.45), (int)(size*0.6));
		
		
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}