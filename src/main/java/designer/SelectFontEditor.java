package designer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectFontEditor extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = 175976027777236938L;
    protected JPanel panel;
    protected JLabel label;
    protected JButton button;
    protected Font font;
    protected PropertyCommand command;

    public SelectFontEditor(PropertyCommand command) {
        this((Font) command.getValue());
        this.command = command;
    }

    public SelectFontEditor(Font font) {
        this.font = font;
        panel = new JPanel();

        label = new JLabel(font.getFontName());// +"\" \""+font.getStyle()+"\" \""+font.getSize());
        button = new JButton("...");
        button.setVisible(true);
        button.setBounds(0, 0, 22, 20);
        button.setPreferredSize(new Dimension(22, 20));
        setLayout(new BorderLayout());
        setVisible(true);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 1));
        panel.setVisible(true);

        panel.add(label, BorderLayout.CENTER);
        panel.setBackground(Color.white);
        setBackground(Color.white);
        label.setVisible(true);

        this.add(button, BorderLayout.EAST);
        this.add(panel, BorderLayout.WEST);

        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                button_actionPerformed(e);
            }
        });
    }

    public Font getCustomFont() {
        return font;
    }

    public void setCustomFont(Font font) {
        // if ( font == null ) return;
        System.out.println("LABEL: " + label);
        System.out.println("FONT: " + font);
        this.font = font;
        label.setText(font.getFontName());// +"\" \""+font.getStyle()+"\" \""+font.getSize());
    }

    void button_actionPerformed(ActionEvent e) {
        FontChooser fc = new FontChooser(UIDesigner.mainWindow, getCustomFont());
        Font font = fc.getCustomerFont();

        PropertyCommand undoCommand = command.createUndoCommand();
        UIDesigner.getControl().addUndoCommand(undoCommand);

        setCustomFont(font);
        try {
            command.setValue(font);
        } catch (Exception ex) {
        }
    }

}
