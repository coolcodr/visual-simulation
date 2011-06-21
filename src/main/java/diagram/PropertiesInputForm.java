package diagram;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Title: Description: Copyright: Copyright (c) 2001 Company:
 * 
 * @author
 * @version 1.0
 */

public class PropertiesInputForm implements ActionListener {

    PropertiesTableReader _pReader;
    PropertiesSetting _pSetting;

    CEditor _cEditor;
    Property _property;
    Object _value;
    String _type;

    private JFrame _formFrame;
    private JPanel _contentPane;
    private JPanel _formPanel;

    private JLabel[] _labels;
    JTextField[] _inputs;

    private JButton _submitButton;
    private JButton _printButton;

    private GridLayout _formLayout;

    public PropertiesInputForm() {
        _pReader = new PropertiesTableReader();
        _pSetting = new PropertiesSetting();

        _formFrame = new JFrame();
        _contentPane = new JPanel();
        _formPanel = new JPanel();

        _formFrame.setContentPane(_contentPane);

        _submitButton = new JButton("OK");
        _printButton = new JButton("Print");

        _formLayout = new GridLayout();
        _formLayout.setColumns(2);

        _formFrame.setSize(new Dimension(400, 400));
        _contentPane.add(_formPanel);
        _formPanel.setLayout(_formLayout);
        _submitButton.addActionListener(new TempActionListener(this));
        _printButton.addActionListener(this);
    }

    public PropertiesInputForm(PropertiesTableReader pReader) {
        this();

        _pReader = pReader;
    }

    public void dataInput(CEditor cEditor, Property property, Object value, String type) {
        PropertiesTableData[] data = _pReader.getPropertiesTableData(type);
        _cEditor = cEditor;
        _property = property;
        _value = value;
        _type = type;

        _labels = new JLabel[data.length];
        _inputs = new JTextField[data.length];

        _formLayout.setRows(data.length + 1);

        _formPanel.setPreferredSize(new Dimension(300, 25 * data.length));

        for (int i = 0; i < data.length; i++) {
            _labels[i] = new JLabel(data[i].getName());

            if (_pSetting.get(value, data[i].getGetMethod()) == null) {
                _inputs[i] = new JTextField(null);
            } else {
                _inputs[i] = new JTextField(_pSetting.get(value, data[i].getGetMethod()).toString());
            }

            _formPanel.add(_labels[i]);
            _formPanel.add(_inputs[i]);
        }

        _formPanel.add(_submitButton);
        _formPanel.add(_printButton);
    }

    public void display() {
        _formFrame.setVisible(false);
        _formFrame.setVisible(true);
    }

    public void hide() {
        _formFrame.hide();
    }

    public void actionPerformed(ActionEvent ae) { // method for printout the
                                                  // object attributes
        PropertiesTableData[] data = _pReader.getPropertiesTableData(_type);

        for (int i = 0; i < data.length; i++) {
            System.out.println(_pSetting.get(_value, data[i].getGetMethod()).toString());
        }
    }
}

class TempActionListener implements ActionListener {

    private PropertiesInputForm _form;

    public TempActionListener(PropertiesInputForm form) {
        _form = form;
    }

    public void actionPerformed(ActionEvent ae) {

        PropertiesTableData[] data = _form._pReader.getPropertiesTableData(_form._type);

        for (int i = 0; i < data.length; i++) {
            try {
                // Class.forName(data[i].getType()).newInstance();

                Class[] para = new Class[1];
                para[0] = _form._inputs[i].getText().getClass();

                Object[] para2 = new Object[1];
                para2[0] = _form._inputs[i].getText();

                _form._pSetting.set(_form._value, data[i].getSetMethod(), Class.forName(data[i].getType()).getConstructor(para).newInstance(para2));
            } catch (Exception e) {
                System.out.println("Event - " + e);
            }
        }

        _form._cEditor.setPropertyValue(_form._value);
        _form.hide();
    }
}
