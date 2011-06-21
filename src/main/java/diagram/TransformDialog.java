package diagram;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class TransformDialog extends JDialog{
	
	
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
	
	public TransformDialog (JFrame owner, Property property, TableTextField txtField) {
		super(owner,"Transform Setting",true);
		
		_type = new String(property.getType());
		_property = property;
		_value = property.getValue();
		_txtField=txtField;


		//create dialog framework
		this.setResizable(false);
		JPanel mainPane = new JPanel();
//		mainPane.setPreferredSize(new Dimension(400,300));
		JTabbedPane tabbedPane = new JTabbedPane();
        basicPane = new JPanel();
        JComponent advancedPane = new JPanel();
        tabbedPane.addTab("Basic", basicPane);
        tabbedPane.addTab("Advanced", advancedPane);
		tabbedPane.setSelectedIndex(0);
		mainPane.setLayout(new BorderLayout());
		mainPane.add(tabbedPane,BorderLayout.CENTER);
		JPanel buttonPane = new JPanel();
		JButton cancelButton = new JButton("Cancel");
		JButton setButton = new JButton("Set");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setVisible(false);
			}
		});
		setButton.addActionListener(new TransformDialogActionListener(this));
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(cancelButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(setButton);
		mainPane.add(buttonPane,BorderLayout.SOUTH);
        getContentPane().add(mainPane);
        basicPane.setLayout(new BoxLayout(basicPane, BoxLayout.Y_AXIS));
        
        
		//create tansform combo box

		_pReader = new PropertiesTableReader();
		_pSetting = new PropertiesSetting();
		String [] typeAry = _pReader.getType(_type);
		String [] choiceAry = new String[typeAry.length];
			
		for(int j = 0; j < typeAry.length; j++) {
			StringTokenizer st = new StringTokenizer(typeAry[j], "."); 
			int count = st.countTokens();
			while(count != 1) {
				st.nextToken();
				count--;
			}
			
			choiceAry[j] = st.nextToken();
		}
		JComboBox transformList = new JComboBox(choiceAry);
		transformList.setEditable(false);
		
		if(_property.getValue()!=null) {
			String className = ((String)_property.getValue().getClass().getName());
			String classNameOnly = className.substring((className.lastIndexOf(".")+1));
			System.err.println("transformList.getMaximumRowCount():"+transformList.getMaximumRowCount());
			for(int i=0;i<transformList.getItemCount() ;i++) {
				System.err.println("(String)transformList.getItemAt(i):"+(String)transformList.getItemAt(i));
				if(((String)transformList.getItemAt(i)).equals(classNameOnly)) {
					transformList.setSelectedIndex(i);
				}
			}
		}
		else {
			transformList.setSelectedIndex(0);
		}
		
		transformList.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
        		JComboBox cb = (JComboBox)e.getSource();
        		String itemName = (String)cb.getSelectedItem();
        		refreshPane(itemName);
    		}
		});

		JLabel transformLabel = new JLabel("Transform Model:");
		
		JPanel topPane = new JPanel();
		topPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPane.add(transformLabel);
		topPane.add(transformList);
		basicPane.add(topPane);

		refreshPane((String)transformList.getSelectedItem());
		
		

        basicPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLocation(400,400);
        this.pack();
        Object o;
		
		try {
			if(_property.getValue() == null){
				_value = Class.forName(_type).newInstance();	//This is the object created
			}
			else {
				_value = _property.getValue();
			}
		}
		catch(Exception cnf) {
			System.err.println("In DistributionDialog:"+cnf);
		}
        
    }
    
    public Object getPropertyValue() {
    	return _value;
    }
    
    private void refreshPane(String selectedItem) {
    	_type = _type.substring(0,_type.lastIndexOf("."))+"."+selectedItem;
		try {
			_value = Class.forName(_type).newInstance();	//This is the object created
		}
		catch(Exception cnf) {
			System.err.println("In TransformDialog:"+cnf);
		}				
    }
}

class TransformDialogActionListener implements ActionListener {
	
	private TransformDialog _dialog;
	
	public TransformDialogActionListener(TransformDialog dialog) {
		_dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		PropertiesTableData[] data = _dialog._pReader.getPropertiesTableData(_dialog._type);
		if(!(data[0].getName().equalsIgnoreCase("null"))) {
			for (int i = 0; i < data.length; i++) {
				try {
					
					Class[] para = new Class[1];
					para[0] = _dialog._inputs[i].getText().getClass();
					
					Object[] para2 = new Object[1];
					para2[0] = _dialog._inputs[i].getText();
					
					_dialog._pSetting.set(_dialog._value, data[i].getSetMethod(), Class.forName(data[i].getType()).getConstructor(para).newInstance(para2));
				}
				catch (Exception e) {
					System.out.println("Event - " + e);
				}
			}
		}
		_dialog._txtField.setText(_dialog._type.substring(_dialog._type.lastIndexOf(".")+1));
		_dialog.setVisible(false);
	}
}