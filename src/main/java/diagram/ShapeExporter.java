package diagram;

import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;

public class ShapeExporter extends Exporter {
	
	private String [] primitiveAry ={"java.lang.String", "java.lang.Integer", "java.lang.Double"};

	public ShapeExporter(DiagramElement _diagramElement, Document _document) {
		super(_diagramElement, _document);
	}

	public Element createPropertiesTag()
	{
		return _document.createElement("Properties");
	}

	public Vector createPropertyTags() {
		Vector _propertyTags = new Vector();
		Vector _properties = ((DiagramShape)_diagramElement).getProperties();
		for(int i=0; i<_properties.size(); i++) {
			
			Element _propertyTag = _document.createElement("Property");
			Attr _nameAttr = _document.createAttribute("Name");
			Attr _typeAttr = _document.createAttribute("Type");
			Attr _valueAttr = _document.createAttribute("Value");
			Attr _subTypeAttr = _document.createAttribute("subType");

			Property _p = (Property)_properties.elementAt(i);	//get the property

			_nameAttr.setValue(_p.getName());
			_typeAttr.setValue(_p.getType());
			_valueAttr.setValue(String.valueOf(_p.getValue()));
			_subTypeAttr.setValue("");

			_propertyTag.setAttributeNode(_nameAttr);
			_propertyTag.setAttributeNode(_typeAttr);
			_propertyTag.setAttributeNode(_valueAttr);
			_propertyTag.setAttributeNode(_subTypeAttr);
			

			if(!isPrimitive(_p.getType())) {
				System.out.println("is not Primitive");
				PropertiesTableReader _pReader = new PropertiesTableReader();
				PropertiesTableData[] _pDataAry = _pReader.getPropertiesTableData(_p.getValue().getClass().getName());
				System.out.println("pArray"+_pDataAry.length);
				
				_subTypeAttr.setValue(_p.getValue().getClass().getName());
				for(int j=0; j<_pDataAry.length; j++) {
					Element _parameterTag = createParameterTag(_p, _pDataAry[j]);
					_propertyTag.appendChild(_parameterTag);
				}
			}
			else{System.out.println("else");};

			_propertyTags.add(_propertyTag);
		}
		
		return _propertyTags;
	}
	
	public Element createParameterTag(Property _p, PropertiesTableData _pData) {
		System.out.println("in for loop");
	
		PropertiesSetting _pSetting = new PropertiesSetting();
		Element _parameterTag = _document.createElement("Parameter");

		Attr _pNameAttr = _document.createAttribute("pName");
		Attr _pTypeAttr = _document.createAttribute("parameterType");
		Attr _pMethodAttr = _document.createAttribute("setMethod");
		Attr _pValueAttr = _document.createAttribute("pValue");
		
		_pNameAttr.setValue(_pData.getName());
		_pTypeAttr.setValue(_pData.getType());
		_pMethodAttr.setValue(_pData.getSetMethod());
		_pValueAttr.setValue(String.valueOf(_pSetting.get(_p.getValue(), _pData.getGetMethod())));

		_parameterTag.setAttributeNode(_pNameAttr);
		_parameterTag.setAttributeNode(_pTypeAttr);
		_parameterTag.setAttributeNode(_pMethodAttr);
		_parameterTag.setAttributeNode(_pValueAttr);			

		return _parameterTag;
	}

	public boolean isPrimitive(String type) {
		boolean isPrimitiveType = false;
		int i=0;
		while(!isPrimitiveType && i<primitiveAry.length) {
			if(type.equalsIgnoreCase(primitiveAry[i])) {
				isPrimitiveType = true;
			}
			i++;
		}
		return isPrimitiveType;
	}
}

