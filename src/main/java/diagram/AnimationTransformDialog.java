package diagram;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import animation.*;

public class AnimationTransformDialog extends JDialog{
	
	
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
	JComboBox animationTransformList;
	JTable table;
	int _selectedIndex = -1;
	public AnimationTransformDialog (JFrame owner, Property property, TableTextField txtField) {
		super(owner,"Animation Transform Setting",true);
		
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
		setButton.addActionListener(new AnimationTransformDialogActionListener(this));
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(cancelButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(setButton);
		mainPane.add(buttonPane,BorderLayout.SOUTH);
        getContentPane().add(mainPane);
        basicPane.setLayout(new BoxLayout(basicPane, BoxLayout.Y_AXIS));
        
        
        table = new JTable();
        AnimationTableModel animationTableModel = new AnimationTableModel(table,_value);
        table.setModel(animationTableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
	        	ListSelectionModel lsm = (ListSelectionModel)e.getSource();
	        	if (lsm.isSelectionEmpty()) {
					_selectedIndex=-1;
	        	} else {
	            	_selectedIndex = lsm.getMinSelectionIndex();

	        	}
	    	}
		});

		
		JScrollPane scrollPane = new JScrollPane(table);
		setUpColumn(table.getColumnModel().getColumn(0));
		setUpColumn(table.getColumnModel().getColumn(1));
		

		basicPane.add(scrollPane);
		JButton addButton = new JButton("Add Entry");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(_value==null) {
					if(animationTransformList.getItemCount()>0) {
						_value = new Vector();
						((Vector)_value).add(new AnimationTransform((String)animationTransformList.getItemAt(0),(String)animationTransformList.getItemAt(0)));
						table.setModel(new AnimationTableModel(table,_value));
						setUpColumn(table.getColumnModel().getColumn(0));
						setUpColumn(table.getColumnModel().getColumn(1));

					}
				}
				else {
					((Vector)_value).add(new AnimationTransform((String)animationTransformList.getItemAt(0),(String)animationTransformList.getItemAt(0)));
					table.setModel(new AnimationTableModel(table,_value));
					setUpColumn(table.getColumnModel().getColumn(0));
					setUpColumn(table.getColumnModel().getColumn(1));										
				}
			}
		});
		JButton deleteButton = new JButton("Delete Entry");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(_selectedIndex!=-1) {
					((Vector)_value).removeElementAt(_selectedIndex);
					table.setModel(new AnimationTableModel(table,_value));
					setUpColumn(table.getColumnModel().getColumn(0));
					setUpColumn(table.getColumnModel().getColumn(1));
				}
			}
		});
		JPanel buttomPane = new JPanel();
		buttomPane.setLayout(new FlowLayout(FlowLayout.LEFT));
		buttomPane.add(addButton);
		buttomPane.add(deleteButton);
		basicPane.add(buttomPane);

        basicPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.setLocation(400,400);
        this.pack();
        
    }
    
    public Object getPropertyValue() {
    	return _value;
    }
    
    public void setUpColumn(TableColumn column) {
		
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
		animationTransformList = new JComboBox(choiceAry);
		animationTransformList.setEditable(false);
        column.setCellEditor(new DefaultCellEditor(animationTransformList));
    }   
    
}

class AnimationTransformDialogActionListener implements ActionListener {
	
	private AnimationTransformDialog _dialog;
	
	public AnimationTransformDialogActionListener(AnimationTransformDialog dialog) {
		_dialog = dialog;
	}
	
	public void actionPerformed(ActionEvent ae) {
		_dialog._txtField.setText("Defined");
		_dialog.setVisible(false);
	}
}

class AnimationTableModel extends AbstractTableModel {
	
	Vector _animationTransforms;
	JTable _table;
	
	public AnimationTableModel(JTable table, Object o) {
		_table = table;
		if(o!=null) {
			_animationTransforms = (Vector)o;
		}
		else {
			_animationTransforms = new Vector();
			o=_animationTransforms;
		}
	}

	final String[] columnNames = {"From Animation", 
                                   "To Animation"};

    public int getColumnCount() {
        return columnNames.length;
    }
        
    public int getRowCount() {
        return _animationTransforms.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public boolean isCellEditable(int row, int col) {
		return true;
	}
	
    public Object getValueAt(int row, int col) {
        if(col==0)
        	return ((AnimationTransform)_animationTransforms.elementAt(row)).getFromAnimation();
        else
        	return ((AnimationTransform)_animationTransforms.elementAt(row)).getToAnimation();

    }

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(columnIndex==0)
			((AnimationTransform)_animationTransforms.elementAt(rowIndex)).setFromAnimation((String)aValue);
		else if(columnIndex==1)
			((AnimationTransform)_animationTransforms.elementAt(rowIndex)).setToAnimation((String)aValue);
			
	}

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
          
}