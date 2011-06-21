package diagram;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

public class CEditor extends DefaultCellEditor implements CellEditorListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1611163896134783804L;

    PropertiesTableReader _pReader = new PropertiesTableReader();

    private TableTextField _tField;
    private TableComboBox _tComboBox;
    private PropertyTable _pTable;

    final public String TYPE_PRIMITIVE = "Primitive";
    final public String TYPE_TRANSFORM = "Transform";
    final public String TYPE_DISTRIBUTION = "Distribution";
    final public String TYPE_OBJECT_CREATOR = "Object Creator";
    final public String TYPE_ANIMATION = "Animation";
    final public String TYPE_SPLITTERMODEL = "SplitterModel";
    final public String TYPE_MERGERMODEL = "MergerModel";
    final public String TYPE_PRESENTATION = "Presentation";
    final public String TYPE_DATA_PROCESSOR = "DataProcessor";
    final public String TYPE_OTHER = "Other";
    private String _valueType = TYPE_OTHER;

    private Property _p;
    private int _row;
    private int _column;
    private JTable _table;
    private PropertyEditorComponent _editorComponent;

    static public UndoCommand undoCommand;// new undoredo[
    static private LinkedList undoList;
    Vector undovalue, undomethod;
    private Object oldpValue;// new undoredo]

    public CEditor(JTextField j) {
        super(j);
        addCellEditorListener(this);
        setClickCountToStart(1);
    }

    static public void setLinkedList(LinkedList vSimUndoList) {
        undoList = vSimUndoList;
    }// new undoredo

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        _pTable = (PropertyTable) table;
        _row = row;
        _column = column;
        _p = ((Property) _pTable.getProperties().elementAt(row));

        oldpValue = _p.getValue();// new

        PropertiesTableReader _pReader = new PropertiesTableReader();
        String[] primitiveAry = { "java.lang.String", "java.lang.Integer", "java.lang.Double" };
        String type = _p.getType();

        int i = 0;
        while (!(_valueType.equals(TYPE_PRIMITIVE)) && i < primitiveAry.length) {

            if (type.equalsIgnoreCase(primitiveAry[i])) {
                _valueType = TYPE_PRIMITIVE;
            }
            i++;
        }
        if (type.equals("mcomponent.distribution.Transform")) {
            _valueType = TYPE_TRANSFORM;
        } else if (type.equals("mcomponent.distribution.Distribution")) {
            _valueType = TYPE_DISTRIBUTION;
        }
        if (type.equals("mcomponent.distribution.Transform")) {
            _valueType = TYPE_TRANSFORM;
        } else if (type.equals("mcomponent.distribution.ObjectCreator")) {
            _valueType = TYPE_OBJECT_CREATOR;
        } else if (type.equals("animation.Animation")) {
            _valueType = TYPE_ANIMATION;
        } else if (type.equals("mcomponent.distribution.SplitterModel")) {
            _valueType = TYPE_SPLITTERMODEL;
        } else if (type.equals("mcomponent.distribution.MergerModel")) {
            _valueType = TYPE_MERGERMODEL;
        } else if (type.equals("chart.Presentation")) {
            _valueType = TYPE_PRESENTATION;
        } else if (type.equals("chart.DataProcessor")) {
            _valueType = TYPE_DATA_PROCESSOR;
        }
        if (_valueType.equals(TYPE_PRIMITIVE)) {

            _tField = new TableTextField();
            if (_p.getValue() == null) {
                _tField.setText("");
            } else {
                _tField.setText(String.valueOf(_p.getValue()));
            }
            _editorComponent = _tField;
        }

        else if (_valueType.equals(TYPE_OBJECT_CREATOR)) {
            JButton jb = new JButton("...");

            // Here's the code that brings up the dialog.
            jb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.err.println("Property:" + _p);
                    System.err.println("Property(type):" + _p.getType());
                    System.err.println("Property(name):" + _p.getName());
                    System.err.println("Property(value):" + _p.getValue());
                    ObjectCreatorDialog ObjectCreatorDialog = new ObjectCreatorDialog(DiagramShape.getDiagram().getFrame(), _p, _tField);
                    ObjectCreatorDialog.setVisible(true);
                    _p.setValue(ObjectCreatorDialog.getPropertyValue());

                }
            });

            _tField = new TableTextField();
            if (_p.getValue() != null) {
                String className = (_p.getValue().getClass().getName());
                String classNameOnly = className.substring((className.lastIndexOf(".") + 1));
                _tField.setText(classNameOnly);
                _tField.setObject(_p.getValue());
            }
            _tField.setLayout(new BorderLayout());
            _tField.add("East", jb);
            _tField.setEditable(false);
            _editorComponent = _tField;
        } else if (_valueType.equals(TYPE_TRANSFORM)) {
            JButton jb = new JButton("...");

            // Here's the code that brings up the dialog.
            jb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.err.println("Property:" + _p);
                    System.err.println("Property(type):" + _p.getType());
                    System.err.println("Property(name):" + _p.getName());
                    System.err.println("Property(value):" + _p.getValue());
                    TransformDialog transformDialog = new TransformDialog(DiagramShape.getDiagram().getFrame(), _p, _tField);
                    transformDialog.setVisible(true);
                    _p.setValue(transformDialog.getPropertyValue());

                }
            });

            _tField = new TableTextField();
            if (_p.getValue() != null) {
                String className = (_p.getValue().getClass().getName());
                String classNameOnly = className.substring((className.lastIndexOf(".") + 1));
                _tField.setText(classNameOnly);
                _tField.setObject(_p.getValue());
            }
            _tField.setLayout(new BorderLayout());
            _tField.add("East", jb);
            _tField.setEditable(false);
            _editorComponent = _tField;
        } else if (_valueType.equals(TYPE_DISTRIBUTION)) {
            JButton jb = new JButton("...");

            // Here's the code that brings up the dialog.
            jb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DistributionDialog distributionDialog = new DistributionDialog(DiagramShape.getDiagram().getFrame(), _p, _tField);
                    distributionDialog.setVisible(true);
                    _p.setValue(distributionDialog.getPropertyValue());

                }
            });

            _tField = new TableTextField();
            if (_p.getValue() != null) {

                String className = (_p.getValue().getClass().getName());
                String classNameOnly = className.substring((className.lastIndexOf(".") + 1));
                _tField.setText(classNameOnly);
                _tField.setObject(_p.getValue());
            }
            _tField.setLayout(new BorderLayout());
            _tField.add("East", jb);
            _tField.setEditable(false);
            _editorComponent = _tField;
        } else if (_valueType.equals(TYPE_ANIMATION)) {
            JButton jb = new JButton("...");

            // Here's the code that brings up the dialog.
            jb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AnimationTransformDialog animationTransformDialog = new AnimationTransformDialog(DiagramShape.getDiagram().getFrame(), _p, _tField);
                    animationTransformDialog.setVisible(true);
                    _p.setValue(animationTransformDialog.getPropertyValue());

                }
            });

            _tField = new TableTextField();
            if (_p.getValue() != null) {

                String className = (_p.getValue().getClass().getName());
                String classNameOnly = className.substring((className.lastIndexOf(".") + 1));
                _tField.setText(classNameOnly);
                _tField.setObject(_p.getValue());
            }
            _tField.setLayout(new BorderLayout());
            _tField.add("East", jb);
            _tField.setEditable(false);
            _editorComponent = _tField;
        } else if (_valueType.equals(TYPE_SPLITTERMODEL)) {
            JButton jb = new JButton("...");

            // Here's the code that brings up the dialog.
            jb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.err.println("Property:" + _p);
                    System.err.println("Property(type):" + _p.getType());
                    System.err.println("Property(name):" + _p.getName());
                    System.err.println("Property(value):" + _p.getValue());
                    SplitterModelDialog splitterModelDialog = new SplitterModelDialog(DiagramShape.getDiagram().getFrame(), _p, _tField);
                    splitterModelDialog.setVisible(true);
                    _p.setValue(splitterModelDialog.getPropertyValue());

                }
            });

            _tField = new TableTextField();
            if (_p.getValue() != null) {
                String className = (_p.getValue().getClass().getName());
                String classNameOnly = className.substring((className.lastIndexOf(".") + 1));
                _tField.setText(classNameOnly);
                _tField.setObject(_p.getValue());
            }
            _tField.setLayout(new BorderLayout());
            _tField.add("East", jb);
            _tField.setEditable(false);
            _editorComponent = _tField;
        } else if (_valueType.equals(TYPE_MERGERMODEL)) {
            JButton jb = new JButton("...");

            // Here's the code that brings up the dialog.
            jb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.err.println("Property:" + _p);
                    System.err.println("Property(type):" + _p.getType());
                    System.err.println("Property(name):" + _p.getName());
                    System.err.println("Property(value):" + _p.getValue());
                    MergerModelDialog mergerModelDialog = new MergerModelDialog(DiagramShape.getDiagram().getFrame(), _p, _tField);
                    mergerModelDialog.setVisible(true);
                    _p.setValue(mergerModelDialog.getPropertyValue());

                }
            });

            _tField = new TableTextField();
            if (_p.getValue() != null) {
                String className = (_p.getValue().getClass().getName());
                String classNameOnly = className.substring((className.lastIndexOf(".") + 1));
                _tField.setText(classNameOnly);
                _tField.setObject(_p.getValue());
            }
            _tField.setLayout(new BorderLayout());
            _tField.add("East", jb);
            _tField.setEditable(false);
            _editorComponent = _tField;
        } else if (_valueType.equals(TYPE_PRESENTATION)) {
            JButton jb = new JButton("...");

            // Here's the code that brings up the dialog.
            jb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.err.println("Property:" + _p);
                    System.err.println("Property(type):" + _p.getType());
                    System.err.println("Property(name):" + _p.getName());
                    System.err.println("Property(value):" + _p.getValue());
                    DataProcessorDialog dataProcessorDialog = new DataProcessorDialog(DiagramShape.getDiagram().getFrame(), _p, _tField);
                    dataProcessorDialog.setVisible(true);
                    _p.setValue(dataProcessorDialog.getPropertyValue());

                }
            });

            _tField = new TableTextField();
            if (_p.getValue() != null) {
                String className = (_p.getValue().getClass().getName());
                String classNameOnly = className.substring((className.lastIndexOf(".") + 1));
                _tField.setText(classNameOnly);
                _tField.setObject(_p.getValue());
            }
            _tField.setLayout(new BorderLayout());
            _tField.add("East", jb);
            _tField.setEditable(false);
            _editorComponent = _tField;
        } else if (_valueType.equals(TYPE_DATA_PROCESSOR)) {
            JButton jb = new JButton("...");

            // Here's the code that brings up the dialog.
            jb.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.err.println("Property:" + _p);
                    System.err.println("Property(type):" + _p.getType());
                    System.err.println("Property(name):" + _p.getName());
                    System.err.println("Property(value):" + _p.getValue());
                    MergerModelDialog mergerModelDialog = new MergerModelDialog(DiagramShape.getDiagram().getFrame(), _p, _tField);
                    mergerModelDialog.setVisible(true);
                    _p.setValue(mergerModelDialog.getPropertyValue());

                }
            });

            _tField = new TableTextField();
            if (_p.getValue() != null) {
                String className = (_p.getValue().getClass().getName());
                String classNameOnly = className.substring((className.lastIndexOf(".") + 1));
                _tField.setText(classNameOnly);
                _tField.setObject(_p.getValue());
            }
            _tField.setLayout(new BorderLayout());
            _tField.add("East", jb);
            _tField.setEditable(false);
            _editorComponent = _tField;
        } else {
            String[] typeAry = _pReader.getType(type);
            String[] choiceAry = new String[typeAry.length];

            for (int j = 0; j < typeAry.length; j++) {
                StringTokenizer st = new StringTokenizer(typeAry[j], ".");
                int count = st.countTokens();
                while (count != 1) {
                    st.nextToken();
                    count--;
                }

                choiceAry[j] = st.nextToken();
                _tComboBox = new TableComboBox(choiceAry);
            }

            if (_p.getValue() != null) {
                _tComboBox.setObject(_p.getValue());
            }

            _editorComponent = _tComboBox;
        }

        return editorComponent();
    }

    public void editingCanceled(ChangeEvent e) {
        System.out.println("editing Canceled");
    }

    public void editingStopped(ChangeEvent e) {

        System.out.println("_p = " + _p + " Name: " + oldpValue);// new undoredo

        if (_valueType.equals(TYPE_PRIMITIVE)) {
            try {
                String type = _p.getType();
                // The array of parameter of the constructor, here is the class
                // is String
                Class[] para = new Class[1];
                para[0] = Class.forName("java.lang.String");
                // get the constructor of the class "type"
                Constructor c = Class.forName(type).getConstructor(para);
                // get the argument of the constructor
                Object[] argu = new Object[1];
                argu[0] = _tField.getText();
                // use the constructor to new an instance
                Object o = c.newInstance(argu);

                // store the old value
                undoCommand = new UndoCommand(_p, oldpValue, o);// new
                                                                // UndoCommand
                undoList.add(undoCommand);// new
                oldpValue = o;// new

                _p.setValue(o);

                DiagramShape.getDiagram().repaint();// new

            } catch (Exception ex) {
                System.out.println("CEditor 1 -- " + ex);
            }
        } else if (_valueType.equals(TYPE_TRANSFORM)) {
        } else if (_valueType.equals(TYPE_DISTRIBUTION)) {

        } else if (_valueType.equals(TYPE_OBJECT_CREATOR)) {

        } else if (_valueType.equals(TYPE_ANIMATION)) {

        } else if (_valueType.equals(TYPE_SPLITTERMODEL)) {

        } else if (_valueType.equals(TYPE_MERGERMODEL)) {

        } else {
            String className = (_p.getValue().getClass().getName());
            String originalItem = className.substring((className.lastIndexOf(".") + 1));

            boolean found = false;
            String type = _p.getType();
            String fullClassName = null; // This is the String of full class
                                         // name e.g. distribution.Uniformation
            String selectedItem = ((String) _tComboBox.getSelectedItem());
            String[] typeAry = _pReader.getType(type);

            for (int j = 0; j < typeAry.length && !found; j++) {
                StringTokenizer st = new StringTokenizer(typeAry[j], ".");
                int count = st.countTokens();

                while (count != 1) {
                    st.nextToken();
                    count--;
                }

                String s = st.nextToken(); // System.out.println("Next token"+st.nextToken());

                if (selectedItem.equals(s)) {
                    fullClassName = typeAry[j];
                    found = true;
                }

            }
            if (found) {

                try {
                    Object o, o2;// new
                    o2 = _p.getValue();// new

                    _p.setValue(_editorComponent.getObject());

                    if ((_p.getValue() == null) || (_p.getValue().getClass() != Class.forName(fullClassName))) {
                        o = Class.forName(fullClassName).newInstance(); // This
                                                                        // is
                                                                        // the
                                                                        // object
                                                                        // created
                    } else {
                        o = _p.getValue();
                    }

                    PropertiesTableReader pReader = new PropertiesTableReader();

                    if (pReader.hasProperties(fullClassName)) {
                        PropertiesInputForm inputForm = new PropertiesInputForm(pReader);
                        inputForm.dataInput(this, _p, o, fullClassName);
                        inputForm.display();
                    }
                    System.out.println("o2: " + o2.toString());
                    System.out.println("o: " + o);

                    if (o2 != o) {// new
                        // new undocommand(_p, o2);
                        _p.setValue(o);
                    }

//					_p.setValue(o);
                } catch (Exception ex) {
                    System.out.println("CEditor 2 -- " + ex);
                }
            }
        }
    }

//	protected int clickCountToStart() {
//		return 0;
//	}
//	
    protected JComponent editorComponent() {
        return (JComponent) _editorComponent;
    }

    public void setPropertyValue(Object object) {
        _p.setValue(object);
        _pTable.getProperties().setElementAt(_p, _row);
    }

}
