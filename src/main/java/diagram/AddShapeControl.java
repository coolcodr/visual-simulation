package diagram;

import java.awt.event.*;
import javax.swing.*;
import java.util.*;//new

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class AddShapeControl extends Control {


	static public UndoCommand undoCommand;//new undoredo[
	static private LinkedList undoList;

	public AddShapeControl() {
		super();
	}
	static public void setLinkedList(LinkedList vSimUndoList){
		undoList = vSimUndoList;
	}//new undoredo
	
	public void mousePressed(MouseEvent e) {
		// add a shape
		DiagramElement d = _diagramControl.addDiagramElement(_type, e.getX(), e.getY());
		
		d.setPane((JLayeredPane) e.getSource());
		d.setOpaque(true);
		//System.out.println(e);
		_diagramControl.restoreControl();
		((DiagramShape)d).setPort();
		
		//Testing!!!!!
		//System.out.println("TEsting!!!!!!       111111111111");
		d.requestFocus();
		//System.out.println("TEsting!!!!!!       222222222222");
		undoCommand = new UndoCommand(d);//new undoredo
		undoList.add(undoCommand);//new

	}
}