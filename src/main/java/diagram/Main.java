package diagram;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class Main {
    boolean packFrame = false;

    /** Construct the application */
    public Main() {

        // JButton jbutton = new JButton("Hello");
        try {
            Frame1 frame = new Frame1();
            final Diagram diagram = new Diagram(frame);
            JMenuBar menuBar = new JMenuBar();
            JMenu frameMenu = new JMenu("File");
            JMenu runMenu = new JMenu("Simulation");
            JMenuItem exportItem = new JMenuItem("Export");
            JMenuItem importItem = new JMenuItem("Import");
            JMenuItem newItem = new JMenuItem("New");
            JMenuItem exitItem = new JMenuItem("Exit");
            frameMenu.add(newItem);
            frameMenu.add(exportItem);
            frameMenu.add(importItem);
            frameMenu.add(exitItem);
            menuBar.add(frameMenu);

            JMenuItem compileItem = new JMenuItem("Compile model");
            JMenuItem runItem = new JMenuItem("Run....");
            runMenu.add(compileItem);
            runMenu.add(runItem);
            menuBar.add(runMenu);

            frame.setJMenuBar(menuBar);

            // Testing!!!!
            DiagramShape.setDiagram(diagram);

            diagram.addDiagramElementType("Source", "diagram/images/iconSource.gif", "diagram.DiagramSource", DiagramElementType.SHAPE, "diagram.SourceRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("Server", "diagram/images/iconServer.gif", "diagram.DiagramServer", DiagramElementType.SHAPE, "diagram.ServerRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("NServer", "diagram/images/iconNServer.gif", "diagram.DiagramNServer", DiagramElementType.SHAPE, "diagram.NServerRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("Sink", "diagram/images/iconSink.gif", "diagram.DiagramSink", DiagramElementType.SHAPE, "diagram.SinkRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("Splitter", "diagram/images/iconSplitter.gif", "diagram.DiagramSplitter", DiagramElementType.SHAPE, "diagram.SplitterRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("FIFO", "diagram/images/iconMessageQueueFIFO.gif", "diagram.DiagramMessageQueueFIFO", DiagramElementType.SHAPE, "diagram.MessageQueueFIFORenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("LIFO", "diagram/images/iconMessageQueueLIFO.gif", "diagram.DiagramMessageQueueLIFO", DiagramElementType.SHAPE, "diagram.MessageQueueLIFORenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("Priority Queue", "diagram/images/iconPriorityQueue.gif", "diagram.DiagramPriorityQueue", DiagramElementType.SHAPE, "diagram.PriorityQueueRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("Time Out FIFO", "diagram/images/iconTimeOutFIFO.gif", "diagram.DiagramTimeOutFIFO", DiagramElementType.SHAPE, "diagram.TimeOutFIFORenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("Time Out LIFO", "diagram/images/iconTimeOutLIFO.gif", "diagram.DiagramTimeOutLIFO", DiagramElementType.SHAPE, "diagram.TimeOutLIFORenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("Time Out Priority", "diagram/images/iconTimeOutPriority.gif", "diagram.DiagramTimeOutPriorityQueue", DiagramElementType.SHAPE, "diagram.TimeOutPriorityRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("AnalysisTool", "diagram/images/iconAnalysisTool.gif", "diagram.DiagramAnalysisTool", DiagramElementType.SHAPE, "diagram.AnalysisToolRenderer", "diagram.AddShapeControl", "diagram.ShapeExporterControl", "diagram.ShapeImporterControl");
            diagram.addDiagramElementType("ArrowLine", "diagram/images/iconArrowLine.gif", "diagram.DiagramArrowLine", DiagramElementType.CONNECTOR, "diagram.SolidArrowLineRenderer", "diagram.AddConnectorControl", "diagram.ConnectorExporterControl", "diagram.ConnectorImporterControl");
            diagram.addDiagramElementType("DotArrowLine", "diagram/images/iconDotArrowLine.gif", "diagram.DiagramDotArrowLine", DiagramElementType.CONNECTOR, "diagram.DotArrowLineRenderer", "diagram.AddConnectorControl", "diagram.ConnectorExporterControl", "diagram.ConnectorImporterControl");

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
                    if (JOptionPane.showConfirmDialog(diagram, "Do you want to save your diagram?", "Save!!!", JOptionPane.YES_NO_OPTION) == 0) {
                        diagram.getDiagramControl().exportDiagram();
                    } else {
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
                    diagram.getDiagramControl().generateCode();

                    CompilationThread compilationThread = new CompilationThread(diagram);
                    compilationThread.start();
                }
            });

            runItem.addMouseListener(new MouseInputAdapter() {
                public void mousePressed(MouseEvent e) {
                    diagram.getDiagramControl().generateCode();

                    RunThread runThread = new RunThread(diagram);
                    runThread.start();
                }
            });
            // frame.getContentPane().setLayout(null);

            // frame.getContentPane().add(diagram);
            // frame.setContentPane(diagram);

            frame.setContentPane(diagram);
            frame.getContentPane().addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    // System.out.println("ContentPane " + e);
                }
            });

            MyGlassPane myGlassPane = new MyGlassPane(frame.getContentPane());
            frame.setGlassPane(myGlassPane);
            myGlassPane.setVisible(false);

            // Validate frames that have preset sizes
            // Pack frames that have useful preferred size info, e.g. from their
            // layout
            if (packFrame) {
                frame.pack();
            } else {
                frame.validate();
            }

            // Center the window
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Main method */

    public static void main(String[] args) {
        try {
            // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Main();
    }
}

/**
 * We have to provide our own glass pane so that it can paint.
 */
class MyGlassPane extends JComponent {
    Point point;

    public void paint(Graphics g) {
        if (point != null) {
            g.setColor(Color.red);
            g.fillOval(point.x - 10, point.y - 10, 20, 20);
        }
    }

    public void setPoint(Point p) {
        point = p;
    }

    public MyGlassPane(Container contentPane) {
        CBListener listener = new CBListener(this, contentPane);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }
}

/**
 * Listen for all events that our check box is likely to be interested in.
 * Redispatch them to the check box.
 */
class CBListener extends MouseInputAdapter {
    Toolkit toolkit;

    // Component liveButton;
    // JMenuBar menuBar;

    MyGlassPane glassPane;
    Container contentPane;
    boolean inDrag = false;

    public CBListener(MyGlassPane glassPane, Container contentPane) {
        toolkit = Toolkit.getDefaultToolkit();
        // this.liveButton = liveButton;
        // this.menuBar = menuBar;
        this.glassPane = glassPane;
        this.contentPane = contentPane;
    }

    public void mouseMoved(MouseEvent e) {
        redispatchMouseEvent(e);
    }

    /*
     * We must forward at least the mouse drags that started with mouse presses
     * over the check box. Otherwise, when the user presses the check box then
     * drags off, the check box isn't disarmed -- it keeps its dark gray
     * background or whatever its L&F uses to indicate that the button is
     * currently being pressed.
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
            // container = menuBar;
            // containerPoint = SwingUtilities.convertPoint(glassPane,
            // glassPanePoint, menuBar);
            System.out.println("Mouse event in menu bar");

            return;
        }
        JComponent toolBar = ((Diagram) contentPane).getToolBar();
        JComponent content = ((Diagram) contentPane).getContentPane();

        Point point = SwingUtilities.convertPoint(contentPane, containerPoint, toolBar);

        // dispath event to toolBar
        if (toolBar.contains(point.x, point.y)) {
            component = SwingUtilities.getDeepestComponentAt(toolBar, point.x, point.y);

            if (component == null) {
                toolBar.dispatchEvent(new MouseEvent(toolBar, eventID, e.getWhen(), e.getModifiers(), point.x, point.y, e.getClickCount(), e.isPopupTrigger()));

                return;
            } else {
                Point cp;
                cp = SwingUtilities.convertPoint(toolBar, point, component);
                component.dispatchEvent(new MouseEvent(component, eventID, e.getWhen(), e.getModifiers(), cp.x, cp.y, e.getClickCount(), e.isPopupTrigger()));

                return;
            }
        }

        point = SwingUtilities.convertPoint(contentPane, containerPoint, content);

        // dispath event to content pane of diagram
        if (content.contains(point.x, point.y)) {
            content.dispatchEvent(new MouseEvent(content, eventID, e.getWhen(), e.getModifiers(), point.x, point.y, e.getClickCount(), e.isPopupTrigger()));

            return;
        }
    }
}
