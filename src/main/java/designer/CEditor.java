package designer;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

public class CEditor extends DefaultCellEditor implements CellEditorListener {
    /**
     * 
     */
    private static final long serialVersionUID = -3426772698769985402L;
    protected Object object;
    protected PropertyCommand property;
    private boolean emt;

    public CEditor(PropertyCommand property) {
        super(new JTextField());

        this.property = property;
    }

    public CEditor(JComboBox jComponent) {
        // super(jComponent);
        super(jComponent);
        addCellEditorListener(this);
        setClickCountToStart(1);
    }

    public CEditor(JTextField jComponent) {
        super(jComponent);
        addCellEditorListener(this);
        jComponent.setBorder(null);
        setClickCountToStart(1);
    }

    public CEditor(JCheckBox jComponent) {
        super(jComponent);
        addCellEditorListener(this);
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void settEmt(boolean b) {
        emt = b;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int r, int c) {
        /*
         * if ( value == null ) return null;
         */
        // if ( emt )
        if (c == 1) {
            if (table.getValueAt(r, 0) instanceof PropertyCommand) {
                PropertyCommand property = (PropertyCommand) table.getValueAt(r, 0);
                JComponent editor = property.getEditor();
                super.editorComponent = editor;
                if (editor instanceof JComboBox) {
                    ((JComboBox) editor).addItemListener(new ComboEditorListener(this, property));
                } else if (editor instanceof JCheckBox) {
                    ((JCheckBox) editor).addActionListener(new CheckEditorListener(this, property));
                }
            }
        }
        // return ;//super.getTableCellEditorComponent(table, value, isSelected,
        // r , c);
        // else
        return super.getTableCellEditorComponent(table, value, isSelected, r, c);
        /*
         * System.out.println("TABLE: "+table);
         * System.out.println("ROW: "+table.getSelectedRow());
         * System.out.println("VALUE: "+value);
         *//*
            * PropertyCommand property; CEditor editor = null; if
            * (table.getSelectedRow() != -1 ) { property = (PropertyCommand)
            * table.getValueAt(table.getSelectedRow(), 0); if
            * (property.getEditor()instanceof JTextField) editor = new CEditor(
            * (JTextField) property.getEditor()); else if
            * (property.getEditor()instanceof JComboBox) editor = new CEditor(
            * (JComboBox) property.getEditor()); else if
            * (property.getEditor()instanceof JCheckBox) editor = new CEditor(
            * (JCheckBox) property.getEditor()); return editor.editorComponent;
            * }
            * 
            * return new JTextField(value.toString());
            */
    }

    public void editingCanceled(ChangeEvent e) {
    }

    public void editingStopped(ChangeEvent e) {
        Object newValue = "";

        if (editorComponent instanceof JTextField) {
            newValue = ((JTextField) editorComponent).getText();
        } else if (editorComponent instanceof JComboBox) {
            newValue = ((JComboBox) editorComponent).getSelectedItem();
        } else if (editorComponent instanceof JCheckBox) {
            newValue = new Boolean((((JCheckBox) editorComponent).isSelected()));
        } else if (editorComponent instanceof ChangeChoiceNameEditor) {
            newValue = ((ChangeChoiceNameEditor) editorComponent).getText();
        } else if (editorComponent instanceof SelectColorEditor) {
            newValue = ((SelectColorEditor) editorComponent).getColor();
            return;
        } else if (editorComponent instanceof SelectFontEditor) {
            newValue = ((SelectFontEditor) editorComponent).getFont();
            return;
        }

        PropertyCommand property = (PropertyCommand) UIDesigner.sourceTable.getValueAt(UIDesigner.sourceTable.getSelectedRow(), 0);
        try {
            PropertyCommand undoCommand = property.createUndoCommand();
            if (undoCommand != null) {
                UIDesigner.getControl().addUndoCommand(undoCommand);
            }
            property.setValue(newValue);
        } catch (InvalidPropertyException ex) {
            JOptionPane.showMessageDialog(UIDesigner.mainWindow, ex.getMessage(), "Invalid Data", JOptionPane.INFORMATION_MESSAGE);
        }
        DesignerControl.currentDesignPane.repaint();
        /*
         * boolean newValue =
         * ((CEditor)e.getSource()).getCellEditorValue().toString
         * ().equalsIgnoreCase("true");
         * //System.out.println(((CEditor)e.getSource
         * ()).getCellEditorValue().toString()); Object test =
         * UIDesigner.sourceTable
         * .getValueAt(UIDesigner.sourceTable.getSelectedRow(),1);
         * ((SimPropertyChoice)test).setDisplay(newValue);
         * 
         * //Refresh combobx cboice
         * ((DiagramComponentSetPanel)UIDesigner.sourceList
         * .selectedObject).refreshComboxBoxChoice();
         * //((TempTestBoolean)object).setValue(newValue); //((Boolean)object).s
         * //System.out.println(object); //root.getUserObjectPath()
         */
    }
}

class ComboEditorListener implements ItemListener {
    private CEditor editor;
    private PropertyCommand property;

    public ComboEditorListener(CEditor editor, PropertyCommand property) {
        this.editor = editor;
        this.property = property;
    }

    public void itemStateChanged(ItemEvent e) {
        System.out.println("ED STOPPED");
        ((JComponent) e.getSource()).setVisible(false);
        try {
            if (!e.getItem().equals(property.getValue())) {
                PropertyCommand undoCommand = property.createUndoCommand();
                UIDesigner.getControl().addUndoCommand(undoCommand);
            }
            property.setValue(e.getItem());
        } catch (InvalidPropertyException ex) {
        }
        property.refresh();
    }
}

class CheckEditorListener implements ActionListener {
    private CEditor editor;
    private PropertyCommand property;

    public CheckEditorListener(CEditor editor, PropertyCommand property) {
        this.editor = editor;
        this.property = property;
    }

    public void actionPerformed(ActionEvent e) {
        JCheckBox chechBox = (JCheckBox) e.getSource();
        chechBox.setVisible(false);
        try {
            PropertyCommand undoCommand = property.createUndoCommand();
            if (undoCommand != null) {
                UIDesigner.getControl().addUndoCommand(undoCommand);
            }
            property.setValue(new Boolean(chechBox.isSelected()));

        } catch (InvalidPropertyException ex) {
        }
        {
            property.refresh();
        }
    }
}
