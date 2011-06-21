package diagram;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class DataProcessorDialog extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = -7351312038110435868L;
    Object _value;
    Property _property;
    private JLabel[] _labels;
    JTextField[] _inputs;
    JPanel[] _panes;
    String _type;

    PropertiesTableData[] data;
    PropertiesTableReader _pReader;
    PropertiesSetting _pSetting;
    JComponent basicPane;
    TableTextField _txtField;

    public DataProcessorDialog(JFrame owner, Property property, TableTextField txtField) {
        super(owner, "DataProcessor Setting", true);

        _type = new String(property.getType());
        _property = property;
        _value = property.getValue();
        _txtField = txtField;

        // create dialog framework
        setResizable(false);
        JPanel mainPane = new JPanel();
//		mainPane.setPreferredSize(new Dimension(400,300));
        JTabbedPane tabbedPane = new JTabbedPane();
        basicPane = new JPanel();
        JComponent advancedPane = new JPanel();
        tabbedPane.addTab("Basic", basicPane);
        tabbedPane.addTab("Advanced", advancedPane);
        tabbedPane.setSelectedIndex(0);
        mainPane.setLayout(new BorderLayout());
        mainPane.add(tabbedPane, BorderLayout.CENTER);
        JPanel buttonPane = new JPanel();
        JButton cancelButton = new JButton("Cancel");
        JButton setButton = new JButton("Set");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
            }
        });
        setButton.addActionListener(new DataProcessorDialogActionListener(this));
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPane.add(setButton);
        mainPane.add(buttonPane, BorderLayout.SOUTH);
        getContentPane().add(mainPane);
        basicPane.setLayout(new BoxLayout(basicPane, BoxLayout.Y_AXIS));

        // create tansform combo box

        _pReader = new PropertiesTableReader();
        _pSetting = new PropertiesSetting();
        String[] typeAry = _pReader.getType(_type);
        String[] choiceAry = new String[typeAry.length];

        for (int j = 0; j < typeAry.length; j++) {
            StringTokenizer st = new StringTokenizer(typeAry[j], ".");
            int count = st.countTokens();
            while (count != 1) {
                st.nextToken();
                count--;
            }

            choiceAry[j] = st.nextToken();
        }
        JComboBox dataProcessorList = new JComboBox(choiceAry);
        dataProcessorList.setEditable(false);

        if (_property.getValue() != null) {
            String className = (_property.getValue().getClass().getName());
            String classNameOnly = className.substring((className.lastIndexOf(".") + 1));
            System.err.println("dataProcessorList.getMaximumRowCount():" + dataProcessorList.getMaximumRowCount());
            for (int i = 0; i < dataProcessorList.getItemCount(); i++) {
                System.err.println("(String)dataProcessorList.getItemAt(i):" + (String) dataProcessorList.getItemAt(i));
                if (((String) dataProcessorList.getItemAt(i)).equals(classNameOnly)) {
                    dataProcessorList.setSelectedIndex(i);
                }
            }
        } else {
            dataProcessorList.setSelectedIndex(0);
        }

        dataProcessorList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String itemName = (String) cb.getSelectedItem();
                refreshPane(itemName);
            }
        });

        JLabel dataProcessorLabel = new JLabel("dataProcessor Model:");

        JPanel topPane = new JPanel();
        topPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        topPane.add(dataProcessorLabel);
        topPane.add(dataProcessorList);
        basicPane.add(topPane);

        refreshPane((String) dataProcessorList.getSelectedItem());

        basicPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLocation(400, 400);
        pack();
        Object o;

        try {
            if (_property.getValue() == null) {
                _value = Class.forName(_type).newInstance(); // This is the
                                                             // object created
            } else {
                _value = _property.getValue();
            }
        } catch (Exception cnf) {
            System.err.println("1(in mergermodel dialog:" + cnf);
        }

    }

    public Object getPropertyValue() {
        return _value;
    }

    private void refreshPane(String selectedItem) {
        _type = _type.substring(0, _type.lastIndexOf(".")) + "." + selectedItem;
        System.err.println("_type" + _type);
        try {
            _value = Class.forName(_type).newInstance(); // This is the object
                                                         // created
        } catch (Exception cnf) {
            System.err.println("2 in mergermodel dialog" + cnf);
        }
    }
}

class DataProcessorDialogActionListener implements ActionListener {

    private DataProcessorDialog _dialog;

    public DataProcessorDialogActionListener(DataProcessorDialog dialog) {
        _dialog = dialog;
    }

    public void actionPerformed(ActionEvent ae) {

        PropertiesTableData[] data = _dialog._pReader.getPropertiesTableData(_dialog._type);
        if (!(data[0].getName().equalsIgnoreCase("null"))) {
            for (int i = 0; i < data.length; i++) {
                try {

                    Class[] para = new Class[1];
                    para[0] = _dialog._inputs[i].getText().getClass();

                    Object[] para2 = new Object[1];
                    para2[0] = _dialog._inputs[i].getText();

                    _dialog._pSetting.set(_dialog._value, data[i].getSetMethod(), Class.forName(data[i].getType()).getConstructor(para).newInstance(para2));
                } catch (Exception e) {
                    System.out.println("Event - " + e);
                }
            }
        }
        _dialog._txtField.setText(_dialog._type.substring(_dialog._type.lastIndexOf(".") + 1));
        _dialog.setVisible(false);
    }
}
