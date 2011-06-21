package diagram;

import javax.swing.*;
import java.lang.reflect.*;
import java.util.*;

public class UndoCommand {

	final private int RESIZE_MOVE = 1;
	final private int CELLEDIT = 2;
	final private int ADDSHAPE = 3;
	final private int ADDCONNECTOR = 4;
	
	static final public int UNDO = 0;
	static final public int REDO = 1;
	
	static public int undoOrRedo;
	
	private int undoType = 0;

	private Object source; // shape
	private Vector value;
	private Vector oldvalue; 
	private Vector method;
	private Property _p;
	private Object _oldpValue,_newpValue;
	private DiagramElement _de = new DiagramElement();
	private DiagramConnector _dc;
	
	UndoCommand(){}

	UndoCommand(Object src, Vector value, Vector method) {
		undoType = RESIZE_MOVE;
		this.source = src;
		this.method = method;
		this.value = value;
//		this.oldvalue = value;
		oldvalue = new Vector();
		for (int i=0;i<value.size();i++){
			Class _class = src.getClass();

			try { 
				Method m = _class.getMethod((String)value.elementAt(i), null);
				Object o = m.invoke(src,null);
				
				oldvalue.add(i,o);
							
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
			
	}
	
	UndoCommand(Property p, Object oldpValue, Object newpValue){
		undoType = CELLEDIT;
		_p = p;
		_oldpValue = oldpValue;
		_newpValue = newpValue;
				
	}

	UndoCommand(DiagramElement de){
		undoType = ADDSHAPE;
		_de = de;
	}

	UndoCommand(DiagramConnector dc){
		undoType = ADDCONNECTOR;
		_dc = dc;
		
	}
	
	public boolean restore() {
		boolean result = true;
		if (undoType == RESIZE_MOVE){//resize diagram shape undo
		
			for(int i=0;i<oldvalue.size();i++) {
				
				Class _class = source.getClass();
				System.out.println("source class = " + _class);
				
				Class parametersClass[] = new Class[1];
				Object parameters[] = new Object[1];
				
				System.out.println("old value = " + oldvalue.elementAt(i));
				parameters[0] = oldvalue.elementAt(i);;
				parametersClass[0] = oldvalue.elementAt(i).getClass();
				
				try { 
					System.out.println("method = " + method.elementAt(i) + " , parameterClass = " + parametersClass);
					Method m = _class.getMethod((String)method.elementAt(i), parametersClass);
					m.invoke(source, parameters);
				}
				catch (Exception e) {
					e.printStackTrace();
					result = false;
				}
			}
	
			((DiagramShape)source).repaintAssociations();
			((DiagramShape)source).showResizePoints();
		
		}else if (undoType == CELLEDIT){
			_p.setValue(_oldpValue);
			DiagramShape.getDiagram().repaint();
			
		}else if (undoType == ADDSHAPE){
			if (undoOrRedo == REDO){
				System.out.println("TestRedo");
				
				DiagramElement d =DiagramShape.getDiagram().getDiagramControl()
				.addDiagramSelection(_de,_de.getX(),_de.getY());
				
				d.setPane((JLayeredPane)DiagramShape.getDiagram().getContentPane());
				d.setOpaque(true);
				DiagramShape.getDiagram().getDiagramControl().restoreControl();
				((DiagramShape)d).setPort();
				d.requestFocus();
				
			}else {
				System.out.println(" TestUndo");
				DiagramShape.getDiagram().getDiagramControl().removeDiagramElement(_de);
			}
		}else if (undoType == ADDCONNECTOR){
			DiagramShape.getDiagram().getDiagramControl()
			.removeDiagramElement((DiagramElement)_dc);
		}

		return result;

	}

	
	
	public UndoCommand createInverseCommand(){
		UndoCommand cmd = new UndoCommand();
		if (undoType == RESIZE_MOVE){
			cmd = new UndoCommand(source,value,method);
		}else if (undoType == CELLEDIT){
			cmd = new UndoCommand(_p,_newpValue,_oldpValue);//Inverse two value
		}else if (undoType == ADDSHAPE){
			cmd = new UndoCommand(_de);
		}
		
		return cmd;
	}

}