package diagram;

import java.awt.*;
import java.util.*;
import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;

public class ImportDocumentControl {
	
	protected Document _document = null;

	public Document createDocumentNode(String path) {
		try {
			DocumentBuilderFactory _factory = DocumentBuilderFactory.newInstance();
			
			//_factory.setValidating(true);
			
			DocumentBuilder _builder = _factory.newDocumentBuilder();

			_builder.setErrorHandler(new iePortErrorHandler());
			
			_document = _builder.parse(new File(path));
		}
		catch (SAXParseException spe) {
			System.err.println("Parse error"  + spe.getMessage());
			System.exit(1);
		}
		catch (SAXException se){
			se.printStackTrace();
		}
		catch (FileNotFoundException fne) {
			System.out.println("File not found");
			System.exit(1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	
		return _document; 
	}

	public void inport(Diagram _diagram, String path) {
		
		try {	
			_document = createDocumentNode(path);
		
			
			Node _root = _document.getDocumentElement();
			if(_root.getNodeType() == Node.ELEMENT_NODE) {
				Element _project = (Element) _root;
				NodeList _diagramNodes = _project.getElementsByTagName("Diagram");
				//Get the no. of elements 
				Node _diagramNode = _diagramNodes.item(0); //Actually there is only one diagram node
				NamedNodeMap _diagramAttributes = ((Element)_diagramNode).getAttributes();
				Attr _maxElementIdAttr = (Attr)_diagramAttributes.item(0);
				int _maxElementId = Integer.parseInt(_maxElementIdAttr.getValue());
				
				Attr _simulationTimeAttr = (Attr)_diagramAttributes.item(1);
				Integer _simulaitonTime = new Integer(_simulationTimeAttr.getValue());
				((Property)_diagram.getDiagramControl().getProperties().elementAt(0)).setValue(_simulaitonTime);
				NodeList _diagramElementNodes = _project.getElementsByTagName("DiagramElement");
				
				
				if(_diagramElementNodes.getLength() != 0) {

					for(int j=0; j<_diagramElementNodes.getLength(); j++) {
						Node _diagramElementNode = _diagramElementNodes.item(j);
						//get attributes list of the _diagramElement;
						NamedNodeMap _attributes = ((Element)_diagramElementNode).getAttributes();	
						//getElementType attribute and its value
						Attr _typeAttr = (Attr)_attributes.item(1);
						String _type = _typeAttr.getValue();
						//get the buttons in toobar for getting their type
						Component[] buttons = _diagram.getToolBar().getComponents();
						DiagramElementType _dType = null;
						//check if the diagram element is outport
						if(_type.toLowerCase().equals("outport")) {
							_dType = new DiagramElementType("Outport", "diagram.DiagramOutPort", DiagramElementType.PORT, "diagram.OutPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
						}
						else if(_type.toLowerCase().equals("inport")) {
							_dType = new DiagramElementType("Inport", "diagram.DiagramInPort", DiagramElementType.PORT, "diagram.InPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
						}
						//check if the type name of the button is same as the DiagramElement in document
						else if(_type.toLowerCase().equals("exitport")) {
							_dType = new DiagramElementType("Exitport", "diagram.DiagramExitPort", DiagramElementType.PORT, "diagram.ExitPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
						}
						else if(_type.toLowerCase().equals("startport")) {
							_dType = new DiagramElementType("Startport", "diagram.DiagramStartPort", DiagramElementType.PORT, "diagram.StartPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
						}
						else if(_type.toLowerCase().equals("endport")) {
							_dType = new DiagramElementType("Endport", "diagram.DiagramEndPort", DiagramElementType.PORT, "diagram.EndPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
						}
						//add by Kenny
						else if(_type.toLowerCase().equals("chartport")) {
							_dType = new DiagramElementType("ChartPort", "diagram.DiagramChartPort", DiagramElementType.PORT, "diagram.ChartPortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
						}
						// add by Kenny
						else if(_type.toLowerCase().equals("datasourceport")) {
							_dType = new DiagramElementType("DataSourcePort", "diagram.DiagramDataSourcePort", DiagramElementType.PORT, "diagram.DataSourcePortRenderer","diagram.PortExporterControl","diagram.PortImporterControl");
						}						
						else {

							for(int i= 0; i<buttons.length; i++) {
								_dType = ((DiagramButton)buttons[i]).getDiagramElementType();
								if(_dType.getName().compareTo(_type) == 0) {
									i=(buttons.length)+1;
								}
							}
						}
						_dType.getImporterControl().setDiagram(_diagram);
						_dType.getImporterControl().importElement(_diagramElementNode, _dType);
					}
				}
				//set MaxElementId of diagramControl
				_diagram.getDiagramControl().setMaxElementId(_maxElementId);

				NodeList _associationNodes = _project.getElementsByTagName("Association");
				if(_associationNodes.getLength() != 0) {
					for(int m=0; m<_associationNodes.getLength(); m++) {
						Node _aNode = _associationNodes.item(m);
						AssociationImporterControl _associationImporterControl = new AssociationImporterControl();
						_associationImporterControl.setDiagram(_diagram);
						_associationImporterControl.importAssociation(_aNode);
					}
				}
			}
		}
		catch (SAXParseException spe) {
			System.err.println("Parse error"  + spe.getMessage());
			System.exit(1);
		}
		catch (SAXException se){
			se.printStackTrace();
		}
		catch (FileNotFoundException fne) {
			System.out.println("File not found");
			System.exit(1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}