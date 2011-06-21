package diagram;

import javax.swing.JComponent;
import java.awt.*;
import java.lang.reflect.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

class MessageQueueFIFORenderer extends DefaultRenderer {
	
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
		
		int[] x = new int[3];
		int[] y = new int[3];
		
		g.setColor(new Color(192, 192, 192));
		g.fillRect((int)(size*0.25), (int)(size*0.25), (int)(size*0.5), (int)(size*0.3));
		
		g.setColor(new Color(255, 0, 0));
		x[0] = (int)(size*0.15); x[1] = (int)(size*0.2); x[2] = (int)(size*0.15);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		x[0] = (int)(size*0.85); x[1] = (int)(size*0.9); x[2] = (int)(size*0.85);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.8), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.75), (int)(size*0.25));
		g.drawLine((int)(size*0.75), (int)(size*0.25), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.25), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.55), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.3), (int)(size*0.25), (int)(size*0.3), (int)(size*0.55));
		g.drawLine((int)(size*0.7), (int)(size*0.25), (int)(size*0.7), (int)(size*0.55));
		g.drawLine((int)(size*0.4), (int)(size*0.3), (int)(size*0.4), (int)(size*0.5));
		g.drawLine((int)(size*0.5), (int)(size*0.3), (int)(size*0.5), (int)(size*0.5));
		g.drawLine((int)(size*0.6), (int)(size*0.3), (int)(size*0.6), (int)(size*0.5));
		
		g.drawLine((int)(size*0.3), (int)(size*0.1), (int)(size*0.35), (int)(size*0.1));
		g.drawLine((int)(size*0.3), (int)(size*0.1), (int)(size*0.3), (int)(size*0.2));
		g.drawLine((int)(size*0.3), (int)(size*0.15), (int)(size*0.35), (int)(size*0.15));
		g.drawLine((int)(size*0.4), (int)(size*0.1), (int)(size*0.4), (int)(size*0.2));
		g.drawLine((int)(size*0.45), (int)(size*0.1), (int)(size*0.5), (int)(size*0.1));
		g.drawLine((int)(size*0.45), (int)(size*0.1), (int)(size*0.45), (int)(size*0.2));
		g.drawLine((int)(size*0.45), (int)(size*0.15), (int)(size*0.5), (int)(size*0.15));
		g.drawLine((int)(size*0.56), (int)(size*0.1), (int)(size*0.59), (int)(size*0.1));
		g.drawLine((int)(size*0.55), (int)(size*0.11), (int)(size*0.55), (int)(size*0.19));
		g.drawLine((int)(size*0.6), (int)(size*0.11), (int)(size*0.6), (int)(size*0.19));
		g.drawLine((int)(size*0.56), (int)(size*0.2), (int)(size*0.59), (int)(size*0.2));
		g.drawLine((int)(size*0.56), (int)(size*0.1), (int)(size*0.55), (int)(size*0.11));
		g.drawLine((int)(size*0.59), (int)(size*0.1), (int)(size*0.6), (int)(size*0.11));
		g.drawLine((int)(size*0.55), (int)(size*0.19), (int)(size*0.56), (int)(size*0.2));
		g.drawLine((int)(size*0.6), (int)(size*0.19), (int)(size*0.59), (int)(size*0.2));
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class MessageQueueLIFORenderer extends DefaultRenderer {
	
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
		
		int[] x = new int[3];
		int[] y = new int[3];
		
		g.setColor(new Color(192, 192, 192));
		g.fillRect((int)(size*0.25), (int)(size*0.25), (int)(size*0.5), (int)(size*0.3));
		
		g.setColor(new Color(255, 0, 0));
		x[0] = (int)(size*0.15); x[1] = (int)(size*0.2); x[2] = (int)(size*0.15);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		x[0] = (int)(size*0.85); x[1] = (int)(size*0.9); x[2] = (int)(size*0.85);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.8), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.75), (int)(size*0.25));
		g.drawLine((int)(size*0.75), (int)(size*0.25), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.25), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.55), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.3), (int)(size*0.25), (int)(size*0.3), (int)(size*0.55));
		g.drawLine((int)(size*0.7), (int)(size*0.25), (int)(size*0.7), (int)(size*0.55));
		g.drawLine((int)(size*0.4), (int)(size*0.3), (int)(size*0.4), (int)(size*0.5));
		g.drawLine((int)(size*0.5), (int)(size*0.3), (int)(size*0.5), (int)(size*0.5));
		g.drawLine((int)(size*0.6), (int)(size*0.3), (int)(size*0.6), (int)(size*0.5));
		
		g.drawString("LIFO", 10, 20);
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class PriorityQueueRenderer extends DefaultRenderer {
	
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
		
		int[] x = new int[3];
		int[] y = new int[3];
		
		g.setColor(new Color(192, 192, 192));
		g.fillRect((int)(size*0.25), (int)(size*0.25), (int)(size*0.5), (int)(size*0.3));
		
		g.setColor(new Color(255, 0, 0));
		x[0] = (int)(size*0.15); x[1] = (int)(size*0.2); x[2] = (int)(size*0.15);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		x[0] = (int)(size*0.85); x[1] = (int)(size*0.9); x[2] = (int)(size*0.85);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.8), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.75), (int)(size*0.25));
		g.drawLine((int)(size*0.75), (int)(size*0.25), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.25), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.55), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.3), (int)(size*0.25), (int)(size*0.3), (int)(size*0.55));
		g.drawLine((int)(size*0.7), (int)(size*0.25), (int)(size*0.7), (int)(size*0.55));
		g.drawLine((int)(size*0.4), (int)(size*0.3), (int)(size*0.4), (int)(size*0.5));
		g.drawLine((int)(size*0.5), (int)(size*0.3), (int)(size*0.5), (int)(size*0.5));
		g.drawLine((int)(size*0.6), (int)(size*0.3), (int)(size*0.6), (int)(size*0.5));
		
		g.drawString("Priority", 10, 20);
		g.drawString("Queue", 10, 50);
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class TimeOutFIFORenderer extends DefaultRenderer {
	
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
		
		int[] x = new int[3];
		int[] y = new int[3];
		
		g.setColor(new Color(192, 192, 192));
		g.fillRect((int)(size*0.25), (int)(size*0.25), (int)(size*0.5), (int)(size*0.3));
		
		g.setColor(new Color(255, 0, 0));
		x[0] = (int)(size*0.15); x[1] = (int)(size*0.2); x[2] = (int)(size*0.15);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		x[0] = (int)(size*0.85); x[1] = (int)(size*0.9); x[2] = (int)(size*0.85);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.8), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.75), (int)(size*0.25));
		g.drawLine((int)(size*0.75), (int)(size*0.25), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.25), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.55), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.3), (int)(size*0.25), (int)(size*0.3), (int)(size*0.55));
		g.drawLine((int)(size*0.7), (int)(size*0.25), (int)(size*0.7), (int)(size*0.55));
		g.drawLine((int)(size*0.4), (int)(size*0.3), (int)(size*0.4), (int)(size*0.5));
		g.drawLine((int)(size*0.5), (int)(size*0.3), (int)(size*0.5), (int)(size*0.5));
		g.drawLine((int)(size*0.6), (int)(size*0.3), (int)(size*0.6), (int)(size*0.5));
		
		g.drawString("Time Out", 10, 20);
		g.drawString("FIFO", 10, 50);
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class TimeOutLIFORenderer extends DefaultRenderer {
	
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
		
		int[] x = new int[3];
		int[] y = new int[3];
		
		g.setColor(new Color(192, 192, 192));
		g.fillRect((int)(size*0.25), (int)(size*0.25), (int)(size*0.5), (int)(size*0.3));
		
		g.setColor(new Color(255, 0, 0));
		x[0] = (int)(size*0.15); x[1] = (int)(size*0.2); x[2] = (int)(size*0.15);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		x[0] = (int)(size*0.85); x[1] = (int)(size*0.9); x[2] = (int)(size*0.85);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.8), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.75), (int)(size*0.25));
		g.drawLine((int)(size*0.75), (int)(size*0.25), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.25), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.55), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.3), (int)(size*0.25), (int)(size*0.3), (int)(size*0.55));
		g.drawLine((int)(size*0.7), (int)(size*0.25), (int)(size*0.7), (int)(size*0.55));
		g.drawLine((int)(size*0.4), (int)(size*0.3), (int)(size*0.4), (int)(size*0.5));
		g.drawLine((int)(size*0.5), (int)(size*0.3), (int)(size*0.5), (int)(size*0.5));
		g.drawLine((int)(size*0.6), (int)(size*0.3), (int)(size*0.6), (int)(size*0.5));
		
		g.drawString("Time Out", 10, 20);
		g.drawString("LIFO", 10, 50);
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class TimeOutPriorityRenderer extends DefaultRenderer {
	
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
		
		int[] x = new int[3];
		int[] y = new int[3];
		
		g.setColor(new Color(192, 192, 192));
		g.fillRect((int)(size*0.25), (int)(size*0.25), (int)(size*0.5), (int)(size*0.3));
		
		g.setColor(new Color(255, 0, 0));
		x[0] = (int)(size*0.15); x[1] = (int)(size*0.2); x[2] = (int)(size*0.15);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.1), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		x[0] = (int)(size*0.85); x[1] = (int)(size*0.9); x[2] = (int)(size*0.85);
		y[0] = (int)(size*0.3)-1; y[1] = (int)(size*0.4); y[2] = (int)(size*0.5);
		g.fillPolygon(x, y, 3);
		g.fillRect((int)(size*0.8), (int)(size*0.35), (int)(size*0.06), (int)(size*0.1));
		
		g.setColor(d.getForeground());
		
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.75), (int)(size*0.25));
		g.drawLine((int)(size*0.75), (int)(size*0.25), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.25), (int)(size*0.25), (int)(size*0.55));
		g.drawLine((int)(size*0.25), (int)(size*0.55), (int)(size*0.75), (int)(size*0.55));
		g.drawLine((int)(size*0.3), (int)(size*0.25), (int)(size*0.3), (int)(size*0.55));
		g.drawLine((int)(size*0.7), (int)(size*0.25), (int)(size*0.7), (int)(size*0.55));
		g.drawLine((int)(size*0.4), (int)(size*0.3), (int)(size*0.4), (int)(size*0.5));
		g.drawLine((int)(size*0.5), (int)(size*0.3), (int)(size*0.5), (int)(size*0.5));
		g.drawLine((int)(size*0.6), (int)(size*0.3), (int)(size*0.6), (int)(size*0.5));
		
		g.drawString("Time Out", 10, 20);
		g.drawString("Priority", 10, 50);
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class AnalysisToolRenderer extends DefaultRenderer {
	
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
		
		g.drawString("Analysis", w/2-20, h/2-20);
		
		
		try {
			String name = (String) ((Property) (((DiagramShape) d).getProperties().elementAt(0))).getValue();
			g.drawString(name, 0, h-1);
		}
		catch (Exception e) {
			System.out.println("Bingo! -- " + e);
		}
	}
}

class SelectionRenderer extends DefaultRenderer {
		
	public void paint(DiagramElement d, Graphics g) {
		int w, h;
		
		w = d.getWidth();
		h = d.getHeight();
		
		g.setColor(Color.gray);
		
		g.drawRect(0,0,w-1,h-1);
	}
}
