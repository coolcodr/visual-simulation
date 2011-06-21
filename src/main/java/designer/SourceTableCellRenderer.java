package designer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import designer.deployment.SimPropertyChoice;

public class SourceTableCellRenderer implements TableCellRenderer {

    public SourceTableCellRenderer() {
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (value == null) {
            return null;
        }
        if (column == 1) {
            if (value instanceof Boolean) {
                // System.out.println("BOOLEAN VLAUE: "+ value);
                Boolean currentValue = (Boolean) value;
                JCheckBox checkBox = new JCheckBox();
                checkBox.setHorizontalAlignment(SwingConstants.CENTER);
                checkBox.setSelected(currentValue.booleanValue());
                checkBox.setBackground(Color.white);
                return checkBox;
            } else if (value instanceof SimPropertyChoice) {
                SimPropertyChoice simChoice = (SimPropertyChoice) value;
                ChangeChoiceNameEditor renderer = new ChangeChoiceNameEditor(simChoice);
                renderer.setSelected(simChoice.getDisplay());
                renderer.setText(simChoice.getDisplayName());
                return renderer;
            } else if (value instanceof Color) {
                Color color = (Color) value;
                SelectColorEditor renderer = new SelectColorEditor(color);
                return renderer;
            } else if (value instanceof Font) {
                Font font = (Font) value;
                SelectFontEditor renderer = new SelectFontEditor(font);
                return renderer;
            } else {
                JTextField textField = new JTextField();
                textField.setBorder(null);
                textField.setText(value.toString());
                return textField;
            }
        }
        return null;
    }
}
