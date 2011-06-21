import javax.swing.UIManager;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import diagram.*;
import animation.*;
import engine.*;
import print.*;
import java.util.*;
import java.lang.reflect.*;
import java.net.*;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class vSim {

	DiagramElement de;
	Vector deVector;
	Vector as;
	static boolean isCut;
	static boolean isCopy;
	boolean packFrame = false;
	MyGlassPane myGlassPane;
	ClassLoader localLoader;
	LinkedList undoList = new LinkedList();//new
	LinkedList redoList = new LinkedList();//new
	/**Construct the application*/
	public vSim() {

		//JButton jbutton = new JButton("Hello");
		try {

			final Frame1 frame = new Frame1();
			final Diagram diagram = new Diagram(frame);
			JMenuBar menuBar = new JMenuBar();
			JMenu frameMenu = new JMenu("File");
			JMenu runMenu = new JMenu("Deployment");
			JMenuItem exportItem = new JMenuItem("Export");
			JMenuItem importItem = new JMenuItem("Import");
			JMenuItem newItem = new JMenuItem("New");
			JMenuItem exitItem = new JMenuItem("Exit");
//new add print
			JMenuItem printItem = new JMenuItem("Print");
			JToolBar toolbar = new JToolBar();

//Roy copy & paste
			JMenu editMenu = new JMenu("Edit");
			JMenuItem undoItem = new JMenuItem("Undo");//NEW
			JMenuItem redoItem = new JMenuItem("Redo");
			JMenuItem cutItem = new JMenuItem("Cut");
			JMenuItem copyItem = new JMenuItem("Copy");
			JMenuItem pasteItem = new JMenuItem("Paste");
			editMenu.add(undoItem);
			editMenu.add(redoItem);
			editMenu.addSeparator();
			editMenu.add(cutItem);
			editMenu.add(copyItem);
			editMenu.add(pasteItem);

//Animation control
			final JButton selItem = new JButton("Selection");
			JLabel cLabel = new JLabel("Animation Class:");
			cLabel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
			final JTextField cText = new JTextField("animation.Animation");
	        JLabel aLabel = new JLabel("Animation Speed:");
	        aLabel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
	        final JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,1, 100, 10);
	        JLabel rLabel = new JLabel("Ratio:");
	        rLabel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
	        final JTextField rText = new JTextField("1");
			JLabel tLabel = new JLabel("Time unit(millisecond):");
			tLabel.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
			final JTextField tText = new JTextField("1000");
	        final JButton run = new JButton("Run");
	        run.setEnabled(true);
	        final JButton stop = new JButton("Stop");
	        final JButton compil = new JButton("Compile");


			frameMenu.add(newItem);
			frameMenu.add(exportItem);
			frameMenu.add(importItem);
			frameMenu.add(printItem);
			frameMenu.add(exitItem);
			menuBar.add(frameMenu);
			menuBar.add(editMenu);
			JMenuItem compileItem = new JMenuItem("Generate");
			JMenuItem runItem = new JMenuItem("Run....");
			runMenu.add(compileItem);
			runMenu.add(runItem);
			menuBar.add(runMenu);

			frame.setJMenuBar(menuBar);




			//Testing!!!!
			DiagramShape.setDiagram(diagram);
			DiagramConnector.setDiagram(diagram);
			diagram.addDiagramElementType("Source", "diagram/images/iconSource.gif", "diagram.DiagramSource", DiagramElementType.SHAPE, "diagram.SourceRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("Server", "diagram/images/iconServer.gif", "diagram.DiagramServer", DiagramElementType.SHAPE, "diagram.ServerRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("NServer", "diagram/images/iconNServer.gif", "diagram.DiagramNServer", DiagramElementType.SHAPE, "diagram.NServerRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("Sink", "diagram/images/iconSink.gif", "diagram.DiagramSink", DiagramElementType.SHAPE, "diagram.SinkRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("Splitter", "diagram/images/iconSplitter.gif", "diagram.DiagramSplitter", DiagramElementType.SHAPE, "diagram.SplitterRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
//careful, not compelete
//need to modify the export and import control code
			diagram.addDiagramElementType("Merger", "diagram/images/iconMerger.gif", "diagram.DiagramMerger", DiagramElementType.SHAPE, "diagram.SplitterRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("FIFO", "diagram/images/iconMessageQueueFIFO.gif", "diagram.DiagramMessageQueueFIFO", DiagramElementType.SHAPE, "diagram.MessageQueueFIFORenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("LIFO", "diagram/images/iconMessageQueueLIFO.gif", "diagram.DiagramMessageQueueLIFO", DiagramElementType.SHAPE, "diagram.MessageQueueLIFORenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("Priority Queue", "diagram/images/iconPriorityQueue.gif", "diagram.DiagramPriorityQueue", DiagramElementType.SHAPE, "diagram.PriorityQueueRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("Time Out FIFO", "diagram/images/iconTimeOutFIFO.gif", "diagram.DiagramTimeOutFIFO", DiagramElementType.SHAPE, "diagram.TimeOutFIFORenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("Time Out LIFO", "diagram/images/iconTimeOutLIFO.gif", "diagram.DiagramTimeOutLIFO", DiagramElementType.SHAPE, "diagram.TimeOutLIFORenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("Time Out Priority", "diagram/images/iconTimeOutPriority.gif", "diagram.DiagramTimeOutPriorityQueue", DiagramElementType.SHAPE, "diagram.TimeOutPriorityRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("AnalysisTool", "diagram/images/iconAnalysisTool.gif", "diagram.DiagramAnalysisTool", DiagramElementType.SHAPE, "diagram.AnalysisToolRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
			diagram.addDiagramElementType("ArrowLine", "diagram/images/iconArrowLine.gif", "diagram.DiagramArrowLine", DiagramElementType.CONNECTOR, "diagram.SolidArrowLineRenderer", "diagram.AddConnectorControl", "diagram.ConnectorExporterControl", "diagram.ConnectorImporterControl");
			diagram.addDiagramElementType("DotArrowLine", "diagram/images/iconDotArrowLine.gif", "diagram.DiagramDotArrowLine", DiagramElementType.CONNECTOR, "diagram.DotArrowLineRenderer", "diagram.AddConnectorControl", "diagram.ConnectorExporterControl", "diagram.ConnectorImporterControl");
			diagram.addDiagramElementType("Chart", "diagram/images/chart.gif", "diagram.DiagramChart", DiagramElementType.SHAPE, "diagram.ChartRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
//			diagram.addDiagramElementType("Selection", "diagram/images/iconSelection.gif", "diagram.DiagramSelection", DiagramElementType.SELECTION, "diagram.SelectionRenderer", "diagram.SelectionControl", "diagram.ConnectorExporterControl", "diagram.ConnectorImporterControl");
			ResizePoint.setLinkedList(undoList);//new undoredo
			CEditor.setLinkedList(undoList);
			DefaultShapeControl.setLinkedList(undoList);
			AddShapeControl.setLinkedList(undoList);
			AddConnectorControl.setLinkedList(undoList);

			exportItem.addMouseListener(new MouseInputAdapter() {
				public void mousePressed(MouseEvent e) {
					diagram.getDiagramControl().exportDiagram();
				}
			});

			importItem.addMouseListener(new MouseInputAdapter() {
				public void mousePressed(MouseEvent e) {
					diagram.getDiagramControl().importDiagram();
				 }
			});

			newItem.addMouseListener(new MouseInputAdapter() {
			public void mousePressed(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(diagram, "Do you want to save your diagram?", "Save!!!", JOptionPane.YES_NO_OPTION)==0) {
					diagram.getDiagramControl().exportDiagram();
				}
				else {
					diagram.getDiagramControl().clearDiagram();
				}
				 }
			});

			exitItem.addMouseListener(new MouseInputAdapter() {
			public void mousePressed(MouseEvent e) {
					System.exit(0);
				 }
			});

			compileItem.addMouseListener(new MouseInputAdapter() {
				public void mousePressed(MouseEvent e) {
					//Horst compile code
					diagram.getDiagramControl().generateCode();
//					diagram.getDiagramControl().generateCode();
//
//					CompilationThread compilationThread = new CompilationThread(diagram);
//					compilationThread.start();
				}
			});


			runItem.addMouseListener(new MouseInputAdapter() {
				public void mousePressed(MouseEvent e) {
					diagram.getDiagramControl().generateCode();

					RunThread runThread = new RunThread(diagram);
					runThread.start();

				 }
			});

			undoItem.addMouseListener(new MouseInputAdapter() {//new
				public void mousePressed(MouseEvent e) {
					UndoCommand.undoOrRedo = UndoCommand.UNDO;
					UndoCommand undoCommand = (UndoCommand)undoList.removeLast();
					UndoCommand redoCommand = (UndoCommand)undoCommand.createInverseCommand();
					redoList.add(redoCommand);
					undoCommand.restore();
				}
			});
			//frame.getContentPane().setLayout(null);

			//frame.getContentPane().add(diagram);
			//frame.setContentPane(diagram);
			redoItem.addMouseListener(new MouseInputAdapter() {//new
				public void mousePressed(MouseEvent e) {
					UndoCommand.undoOrRedo = UndoCommand.REDO;
//					undoList.add(tempList.removeLast());
					UndoCommand redoCommand = (UndoCommand)redoList.removeLast();
					UndoCommand undoCommand = (UndoCommand)redoCommand.createInverseCommand();
					undoList.add(undoCommand);
					redoCommand.restore();
				}
			});

//Roy copy & paste
			cutItem.addMouseListener(new MouseInputAdapter() {
				public void mousePressed(MouseEvent e) {
					if(DiagramShape.getDiagram().getDiagramControl().getSelectedElements()!=null){
						if(DiagramShape.getDiagram().getDiagramControl().getSelectedElements().size()>0) {
							deVector = DiagramShape.getDiagram().getDiagramControl().getSelectedElements();
						}
					}
					else {
//						de = DiagramShape.getDiagram().getDiagramControl().getSelectedElement();
					}
					DiagramShape.getDiagram().getDiagramControl().setShift(0);
					isCut = true;
				}
				public void mouseReleased(MouseEvent e){
					if(DiagramShape.getDiagram().getDiagramControl().getSelectedElements()!=null){
						if(DiagramShape.getDiagram().getDiagramControl().getSelectedElements().size()>0) {
							as = DiagramShape.getDiagram().getDiagramControl().removeDiagramElements(deVector);
							isCut = true;
						}
					}
					else {
//						DiagramShape.getDiagram().getDiagramControl().removeDiagramElement(de);
					}
				}


			});

			copyItem.addMouseListener(new MouseInputAdapter() {
				public void mousePressed(MouseEvent e) {
					if(DiagramShape.getDiagram().getDiagramControl().getSelectedElements()!=null){
						if(DiagramShape.getDiagram().getDiagramControl().getSelectedElements().size()>0) {
							deVector = DiagramShape.getDiagram().getDiagramControl().getSelectedElements();
							DiagramShape.getDiagram().getDiagramControl().setShift(0);
							isCut=false;
						}
					}
				}
				public void mouseReleased(MouseEvent e){
					if(DiagramShape.getDiagram().getDiagramControl().getSelectedElements()!=null){
						if(DiagramShape.getDiagram().getDiagramControl().getSelectedElements().size()>0) {
							as = DiagramShape.getDiagram().getDiagramControl().getElementAssociations(deVector);
							isCut = false;
						}
					}
				}
			});

			pasteItem.addMouseListener(new MouseInputAdapter() {
				public void mousePressed(MouseEvent e) {
					if(DiagramShape.getDiagram().getDiagramControl().getSelectedElements()!=null){
						if(DiagramShape.getDiagram().getDiagramControl().getSelectedElements().size()>0) {
							DiagramShape.getDiagram().getDiagramControl().cloneElements(isCut,as);
							System.err.println("\nElement in paste(vSim.java):");
							for(int i =1;i<=diagram.getDiagramControl().getMaxElementId();i++) {
								System.err.println((DiagramElement)diagram.getDiagramControl().getDiagramElement(i));

							}
							isCut=false;
						}
					}
//					else {
//						DiagramShape.getDiagram().getDiagramControl().cloneElement(de,isCut);
//					}

//					DiagramElementType _type = de.getType();
//					DiagramElement d = DiagramShape.getDiagram().getDiagramControl().addDiagramElement(_type, 25, 25);
//
//					d.setPane((JLayeredPane) DiagramShape.getDiagram().getContentPane());
//					d.setOpaque(true);
//					DiagramShape.getDiagram().getDiagramControl().restoreControl();
//					((DiagramShape)d).setPort();
//
//					d.requestFocus();


		      	}
			});


			printItem.addMouseListener(new MouseInputAdapter() {
				public void mousePressed(MouseEvent e) {
					PrintEditor printEditor = new PrintEditor (diagram.getMainPane());
					printEditor.setVisible (true);

				}
			});

			selItem.addMouseListener(new MouseInputAdapter() {

				public void mousePressed(MouseEvent e) {
					diagram.getDiagramControl().setControl(new SelectionControl2(diagram));
				}

			});

			frame.setContentPane(diagram);
			frame.getContentPane().addMouseListener(
				new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						//System.out.println("ContentPane " + e);
					}
				}
			);

			toolbar.add(selItem);
	        toolbar.add(cLabel);
	        toolbar.add(cText);
	        toolbar.add(aLabel);
	        toolbar.add(framesPerSecond);
			toolbar.add(rLabel);
			toolbar.add(rText);
			toolbar.add(tLabel);
			toolbar.add(tText);
			toolbar.add(compil);
	        toolbar.add(run);
	        toolbar.add(stop);
	        frame.getContentPane().add(toolbar, BorderLayout.NORTH);
			myGlassPane = new MyGlassPane(frame.getContentPane(),toolbar);
			frame.setGlassPane(myGlassPane);
			myGlassPane.setVisible(false);



//modified by matthew

			run.addActionListener(new ActionListener() {

	        	public void actionPerformed(ActionEvent e) {
	        		SimThread.setStop(false);
	        		SystemThread.setStop(false);
	        		SimThread.clearSystemData();
	        		AnimationLine.setStop(false);

					try{
//		        		Class IM2 = Class.forName("InternalModel");
						// create a custom ClassLoader. You must write the code for LocalClassLoader.
//						URL[] serverURLs = new URL[]{new URL("file:F:/IM/")};
						localLoader = new SimpleClassLoader();
						// create a new MyClass object, can't cast to MyClass!
						Class IM =localLoader.loadClass ("InternalModel");

//		        		Class IM = IM2.getClassLoader().loadClass("InternalModel");

		        		Method IMMethod = IM.getMethod("start",null);

		        		Class[] classArray = new Class[] {JComponent.class, JComponent.class,int.class};
		        		Constructor IMConstructor = IM.getConstructor(classArray);
		        		Object[] objectArray = new Object[] {diagram.getContentPane(),myGlassPane,new Integer(5)};
		        		System.err.println("Constructor:" + IMConstructor);
		        		Object im=IMConstructor.newInstance(objectArray);
//	        			InternalModel im = new InternalModel(diagram.getContentPane(),myGlassPane,5,"animation.Animation");
		        		AnimationLine.setAnimationStatus(true);
		        		FEL.setRatio(Double.parseDouble(rText.getText()));
						FEL.setTimeUnit(Long.parseLong(tText.getText()));

						IMMethod.invoke(im,null);

//		        		im.start();
		        	}
		        	catch(Exception e2) {
		        		System.err.println(e2);
		        		System.err.println(e2.getStackTrace().toString());
		        	};

	        	}
	        });
			compil.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
						diagram.getDiagramControl().generateInternalCode();
						CompilationFileThread compilationFileThread = new CompilationFileThread(frame,run,"InternalModel.java");
						compilationFileThread.start();
	        	}
	        });
			stop.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
//					SimThread.getSimSystemData().addEvent(new engine.Event(0, this, "Termination", null));
		        	SimThread.setStop(true);
		        	SystemThread.setStop(true);
		        	SimThread.clearSystemData();
		        	AnimationLine.setStop(true);
//		        	Animation.setStopAnimation(true);
					myGlassPane.setVisible(false);
					AnimationLine.setAnimationStatus(false);
	        	}
	        });

			//Validate frames that have preset sizes
			//Pack frames that have useful preferred size info, e.g. from their layout
			if (packFrame) {
				frame.pack();
			}
			else {
				frame.validate();
			}

			//Center the window
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension frameSize = frame.getSize();
			if (frameSize.height > screenSize.height) {
				frameSize.height = screenSize.height;
			}
			if (frameSize.width > screenSize.width) {
				frameSize.width = screenSize.width;
			}
			frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
			frame.setVisible(true);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**Main method*/

	public static void main(String[] args) {
		try {

            String lnfName = "light.LightLookAndFeel";
            //String lnfName = "com.incors.plaf.alloy.AlloyLookAndFeel";
            UIManager.setLookAndFeel(lnfName);

			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		new vSim();
	}
}


/**
 * We have to provide our own glass pane so that it can paint.
 */

/**
 * Listen for all events that our check box is likely to be
 * interested in.  Redispatch them to the check box.
 */
class CBListener extends MouseInputAdapter {
	Toolkit toolkit;

	//Component liveButton;
	//JMenuBar menuBar;

	MyGlassPane glassPane;
	Container contentPane;
	JToolBar _toolbar;
	boolean inDrag = false;

	public CBListener(MyGlassPane glassPane, Container contentPane, JToolBar toolbar) {
		toolkit = Toolkit.getDefaultToolkit();
		//this.liveButton = liveButton;
		//this.menuBar = menuBar;
		this.glassPane = glassPane;
		this.contentPane = contentPane;
		_toolbar = toolbar;
	}

	public void mouseMoved(MouseEvent e) {
		redispatchMouseEvent(e);
	}

	/*
	 * We must forward at least the mouse drags that started
	 * with mouse presses over the check box.  Otherwise,
	 * when the user presses the check box then drags off,
	 * the check box isn't disarmed -- it keeps its dark
	 * gray background or whatever its L&F uses to indicate
	 * that the button is currently being pressed.
	 */
	public void mouseDragged(MouseEvent e) {
		redispatchMouseEvent(e);
	}

	public void mouseClicked(MouseEvent e) {
		redispatchMouseEvent(e);
	}

	public void mouseEntered(MouseEvent e) {
		redispatchMouseEvent(e);
	}

	public void mouseExited(MouseEvent e) {
		redispatchMouseEvent(e);
	}

	public void mousePressed(MouseEvent e) {
		redispatchMouseEvent(e);
	}

	public void mouseReleased(MouseEvent e) {
		redispatchMouseEvent(e);
	}

	private void redispatchMouseEvent(MouseEvent e) {
		Point glassPanePoint = e.getPoint();
		Component component = null;
		Container container = contentPane;
		Point containerPoint = SwingUtilities.convertPoint(glassPane, glassPanePoint, contentPane);

		int eventID = e.getID();

		if (containerPoint.y < 0) {
			//container = menuBar;
			//containerPoint = SwingUtilities.convertPoint(glassPane, glassPanePoint, menuBar);
			System.out.println("Mouse event in menu bar");

			return;
		}
		JComponent toolBar = ((Diagram) contentPane).getToolBar();
		JComponent content = ((Diagram) contentPane).getContentPane();


		Point point = SwingUtilities.convertPoint(contentPane, containerPoint, _toolbar);
		if (_toolbar.contains(point.x, point.y)) {
			component = SwingUtilities.getDeepestComponentAt(_toolbar, point.x, point.y);

			if (component == null) {
				_toolbar.dispatchEvent(new MouseEvent(_toolbar,
									eventID,
									e.getWhen(),
									e.getModifiers(),
									point.x,
									point.y,
									e.getClickCount(),
									e.isPopupTrigger())
				);

				return;
			}
			else {
				Point cp;
				cp = SwingUtilities.convertPoint(_toolbar, point, component);
				component.dispatchEvent(new MouseEvent(component,
									eventID,
									e.getWhen(),
									e.getModifiers(),
									cp.x,
									cp.y,
									e.getClickCount(),
									e.isPopupTrigger())
				);

				return;
			}
		}

		point = SwingUtilities.convertPoint(contentPane, containerPoint, toolBar);

		// dispath event to toolBar
		if (toolBar.contains(point.x, point.y)) {
			component = SwingUtilities.getDeepestComponentAt(toolBar, point.x, point.y);

			if (component == null) {
				toolBar.dispatchEvent(new MouseEvent(toolBar,
									eventID,
									e.getWhen(),
									e.getModifiers(),
									point.x,
									point.y,
									e.getClickCount(),
									e.isPopupTrigger())
				);

				return;
			}
			else {
				Point cp;
				cp = SwingUtilities.convertPoint(toolBar, point, component);
				component.dispatchEvent(new MouseEvent(component,
									eventID,
									e.getWhen(),
									e.getModifiers(),
									cp.x,
									cp.y,
									e.getClickCount(),
									e.isPopupTrigger())
				);

				return;
			}
		}

		point = SwingUtilities.convertPoint(contentPane, containerPoint, content);

		// dispath event to content pane of diagram
		if (content.contains(point.x, point.y)) {
			content.dispatchEvent(new MouseEvent(content,
								eventID,
								e.getWhen(),
								e.getModifiers(),
								point.x,
								point.y,
								e.getClickCount(),
								e.isPopupTrigger())
			);

			return;
		}
	}
}