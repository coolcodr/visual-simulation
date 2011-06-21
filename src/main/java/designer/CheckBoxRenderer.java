package designer;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer
{

    public CheckBoxRenderer()
    {
        setHorizontalAlignment(JLabel.LEFT);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus,
        int row, int column)
    {
        if (isSelected)
        {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        }
        else
        {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        setSelected( (value != null && ( (Boolean) value).booleanValue()));
        if ( value == null )
            return null;
        return this;
    }
}