package diagram;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;

//import javax.swing.JPanel;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class Diagram extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 4288649520152928025L;
    private JToolBar _toolBar;
    private JLayeredPane _contentPane;
    private DiagramControl _diagramControl; // for diagram dynamic behavior
    private ActionListener _buttonControl; // for toolbar buttons
    private JFrame _frame;

    private PropertyTable _jTable;

    public Diagram(JFrame frame) {
        super();
        _frame = frame;
        _diagramControl = new DiagramControl(this);
        _buttonControl = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DiagramButton b = (DiagramButton) e.getSource();
                // System.out.println("Press " + b);
                _diagramControl.setControl(b.getControl());

                if (b.getDiagramElementType().isConnector()) {
                    setGlassPaneVisible(true);
                } else {
                    setGlassPaneVisible(false);
                }
            }
        };

        _toolBar = new JToolBar();
        _toolBar.setPreferredSize(new Dimension(60, 350));
        _toolBar.setLayout(new GridLayout(7, 2));
        JScrollPane _toolBarScrollPane = new JScrollPane(_toolBar, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        _contentPane = new JLayeredPane();
        JScrollPane _contentScrollPane = new JScrollPane(_contentPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        _contentPane.addMouseListener(_diagramControl);
        _contentPane.addMouseMotionListener(_diagramControl);

        _jTable = new PropertyTable();
        JScrollPane _tableScrollPane = new JScrollPane(_jTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        _tableScrollPane.setPreferredSize(new Dimension(30, 100));

        // _contentPane.setPreferredSize(new Dimension(100,100));
        setLayout(new BorderLayout());

        JSplitPane _westSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, _toolBarScrollPane, _tableScrollPane);
        _westSplitPane.setResizeWeight(0.25);
        _westSplitPane.setDividerLocation(0.30);
        _westSplitPane.setOneTouchExpandable(true);
        _westSplitPane.setContinuousLayout(true);
        // combine the scroll pane
        JSplitPane _mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, _westSplitPane, _contentScrollPane);
        _mainSplitPane.setResizeWeight(0.20);
        _mainSplitPane.setDividerLocation(0.25);
        _mainSplitPane.setOneTouchExpandable(true);
        _mainSplitPane.setContinuousLayout(true);

        // _westSplitPane.setSize(new Dimension(100, 60));
        add(_mainSplitPane, BorderLayout.CENTER);
        // add(scrollPane, BorderLayout.WEST);
        // add(new JScrollPane(_contentPane), BorderLayout.CENTER);
        _contentPane.setLayout(null);
    }

    public void setGlassPaneVisible(boolean b) {
        _frame.getGlassPane().setVisible(b);
    }

    public void addButton(String label, String image, DiagramElementType t, String controller) {
        DiagramButton button = null;

        button = new DiagramButton(label, image, t, controller, _diagramControl);
        button.addActionListener(_buttonControl);
        _toolBar.add(button);
    }

    public DiagramElementType addDiagramElementType(String name, String image, String className, String category, String renderer, String controller, String exporterControl, String importerControl) throws java.lang.Exception {

        DiagramElementType type = new DiagramElementType(name, className, category, renderer, exporterControl, importerControl);
        addButton(name, image, type, controller);
        return (type);
    }

    public void addDiagramElement(DiagramElement e) {
        _contentPane.add(e, JLayeredPane.DEFAULT_LAYER, 0);
        e.setVisible(true);
        if (e.getType().isConnector()) {
            ((DiagramConnector) e).setOpaque(true);
        }
        e.repaint();
    }

//added by matthew, testing!!
    public void addDiagramSelection(DiagramElement e) {
        _contentPane.add(e, new Integer(JLayeredPane.DEFAULT_LAYER.intValue() - 1), 0);
        e.setVisible(true);
        e.repaint();
    }

    // Testing!!!
    public void removeDiagramElement(JComponent object) {
        object.setVisible(false);
        _contentPane.remove(object);
    }

    public JComponent getContentPane() {
        return _contentPane;
    }

    public JComponent getToolBar() {
        return _toolBar;
    }

    public JFrame getFrame() {
        return _frame;
    }

    // Testing!!!!
    public DiagramControl getDiagramControl() {
        return _diagramControl;
    }

    public PropertyTable getJTable() {
        return _jTable;
    }

    public JComponent getMainPane() {
        return _contentPane;
    }
}
