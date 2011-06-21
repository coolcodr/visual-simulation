package diagram;

import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class PropertiesTableReader {
	
	String _filePath;
	Vector _propertiesTableData;
	
	public PropertiesTableReader() {
		_filePath = "PropertiesTable.txt";
		
		_propertiesTableData = new Vector();
		
		refresh();
	}
	
	public PropertiesTableReader(String filePath) {
		this();
		
		_filePath = filePath;
	}
	
	public void refresh() {
			
		File file;
		FileInputStream fileInputStream;
		StreamTokenizer inFileTokenizer = null;
		
		int lineCount;
		int lineWordCount;
		String lineWord;
		
		try {
			file = new File(_filePath);
			fileInputStream = new FileInputStream(file);
			inFileTokenizer = new StreamTokenizer(fileInputStream);
		}
		catch (Exception e) {
		}
		
		lineCount = 0;
		
		try {
			inFileTokenizer.nextToken();
		}
		catch(Exception e) {
		}
		
		while (inFileTokenizer.ttype != StreamTokenizer.TT_EOF) {
			PropertiesTableData propertiesTableData = new PropertiesTableData();
				
			lineWordCount = 0;
			lineWord = "";
			
			while (lineWordCount < 7) {
				try {
					lineWord = inFileTokenizer.sval;
					
					inFileTokenizer.nextToken();
				}
				catch(Exception e) {
				}
				
				while (inFileTokenizer.ttype != (int)'`') {
					try {
						lineWord = lineWord + " " + inFileTokenizer.sval;
						inFileTokenizer.nextToken();
					}
					catch(Exception e) {
					}
				}
				
				propertiesTableData.setData(lineWordCount, lineWord);
				lineWordCount++;
				
				try {
					inFileTokenizer.nextToken();
				}
				catch(Exception e) {
				}
			}
			
			_propertiesTableData.add(propertiesTableData);
			lineCount++;
		}
	}
	
	public String[] getType(String parentType) {
		
		String[] result;
		
		Vector temp = new Vector();
		
		for (int i = 0; i < _propertiesTableData.size(); i++) {
			
			if (((PropertiesTableData) _propertiesTableData.elementAt(i)).getParentType().equals(parentType)) {
			
				boolean isCap = false;
				for (int j = 0; j < temp.size(); j++) {

					if (((String) temp.elementAt(j)).equals(((PropertiesTableData) _propertiesTableData.elementAt(i)).getParent())) {
						isCap = true;
						j = temp.size();
					}
				}
				
				if (! isCap) {
					temp.add(((PropertiesTableData) _propertiesTableData.elementAt(i)).getParent());
				}
			}
		}
		
		result = new String[temp.size()];
		
		for (int i = 0; i < temp.size(); i++) {
			result[i] = (String) temp.elementAt(i);
		}
		
		return result;
	}	
		
	public PropertiesTableData[] getPropertiesTableData(String parent) {
		
		PropertiesTableData[] result;
		
		Vector temp = new Vector();
		
		for (int i = 0; i < _propertiesTableData.size(); i++) {										
			if (((PropertiesTableData) _propertiesTableData.elementAt(i)).getParent().equals(parent)) {
				temp.add(_propertiesTableData.elementAt(i));
			}
		}
		
		result = new PropertiesTableData[temp.size()];
		
		for (int i = 0; i < temp.size(); i++) {
			result[i] = (PropertiesTableData) temp.elementAt(i);
		}
		
		return result;
	}
	
	public boolean hasProperties(String parent) {
		PropertiesTableData[] data = getPropertiesTableData(parent);
		
		if (data[0].getName().equals("null")) {
			return false;
		}
		
		return true;
	}
}