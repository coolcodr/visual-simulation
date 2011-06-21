package designer;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

    /**
     * 
     */
    private static final long serialVersionUID = -7511375012107726228L;

    public CheckBoxRenderer() {
        setHorizontalAlignment(SwingConstants.LEFT);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setSelected((value != null && ((Boolean) value).booleanValue()));
        if (value == null) {
            return null;
        }
        return this;
    }
}
