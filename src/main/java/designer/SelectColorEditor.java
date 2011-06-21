package designer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

class SelectColorEditor extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -685809104593991345L;
    protected JPanel panel;
    protected JPanel colorPane;
    protected JLabel label;
    protected JButton button;
    protected Color color;
    protected PropertyCommand command;

    public SelectColorEditor(PropertyCommand command) {
        this((Color) command.getValue());
        this.command = command;
    }

    public SelectColorEditor(Color color) {
        this.color = color;
        panel = new JPanel();
        colorPane = new JPanel();
        label = new JLabel(color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
        button = new JButton("...");
        button.setVisible(true);
        button.setBounds(0, 0, 22, 20);
        button.setPreferredSize(new Dimension(22, 20));
        setLayout(new BorderLayout());
        setVisible(true);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 3, 1));
        panel.setVisible(true);

        panel.add(colorPane, BorderLayout.WEST);
        colorPane.setVisible(true);
        colorPane.setBackground(color);
        colorPane.setBorder(BorderFactory.createLineBorder(Color.black));
        colorPane.setBounds(0, 0, 22, 10);
        colorPane.setPreferredSize(new Dimension(22, 10));
        panel.add(label, BorderLayout.EAST);
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        colorPane.setBackground(color);
        label.setText(color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
    }

    void button_actionPerformed(ActionEvent e) {
        JColorChooser ccd = new JColorChooser(getColor());
        Color color = JColorChooser.showDialog(UIDesigner.mainWindow, "Select Color", getColor());
        if (color != null) {
            PropertyCommand undoCommand = command.createUndoCommand();
            UIDesigner.getControl().addUndoCommand(undoCommand);

            setColor(color);
            try {
                command.setValue(color);
            } catch (Exception ex) {
            }
        }
    }
}
