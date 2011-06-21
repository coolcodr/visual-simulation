package designer.deployment;

import java.io.*;
import org.w3c.dom.*;
//import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;
import javax.swing.JFileChooser;
import java.awt.Component;

public class ExportDataControl
{
    private Document document;
    private DeployObject deployObject;

    public ExportDataControl(DeployObject deployObject)
    {
        this.deployObject = deployObject;
        createDocumentNode();
    }

    public void saveData ( Component parent )
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new XFileFilter());
        fileChooser.showSaveDialog(parent);
        File file = fileChooser.getSelectedFile();
        String path;

        if ( file != null )
        {
            if (file.isFile())
                path = file.getAbsolutePath();
            else
                path = file.getAbsolutePath() + ".xml";

            createDocumentNode(path);
        }
    }
    public void createDocumentNode ()
    {
        createDocumentNode("simulation_data_tmp.xml");
    }
    public void createDocumentNode( String path )
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.newDocument();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }

        Element e1 = document.createElement("Properties");
        document.appendChild(e1);

        for ( int i = 0 ; i < deployObject.getInputObjectCount() ; i ++ )
        {
            Element e2 = document.createElement("Input");
            String name = ((InputComponent)deployObject.getInputObject(i)).getName();
            String setMode = ((InputComponent)deployObject.getInputObject(i)).getSetMode();
            String value;

            try{
                value = ((InputComponent)deployObject.getInputObject(i)).getInput().toString();
            }
            catch ( InvalidDataException e ){
                value = "";
            }
            Attr elementName = document.createAttribute("Name");
            Attr elementMode = document.createAttribute("Type");
            Attr elementValue = document.createAttribute("Value");
            e2.setAttributeNode(elementName);
            e2.setAttributeNode(elementMode);
            e2.setAttributeNode(elementValue);
            elementName.setValue(name);
            elementMode.setValue(setMode);
            elementValue.setValue(value);
            e1.appendChild(e2);
        }

        for ( int i = 0 ; i < deployObject.getInternalInputObjectCount() ; i ++ )
        {

            String name = ((InputComponent)deployObject.getInternalInputObject(i)).getName();
            String setMode = ((InputComponent)deployObject.getInternalInputObject(i)).getSetMode();
            String value;
            try{
                value = ((InputComponent)deployObject.getInternalInputObject(i)).getInput().toString();
            }
            catch ( InvalidDataException e ){
                value = "";
            }
            Element e2 = document.createElement("InternalInput");
            Attr elementName = document.createAttribute("Name");
            Attr elementMode = document.createAttribute("Mode");
            Attr elementType = document.createAttribute("Type");
            Attr elementValue = document.createAttribute("Value");
            e2.setAttributeNode(elementName);
            e2.setAttributeNode(elementMode);
            e2.setAttributeNode(elementType);
            e2.setAttributeNode(elementValue);
            elementMode.setValue(name.substring(name.lastIndexOf("|")+1));
            elementName.setValue(name.substring(0,name.indexOf("|")));
            elementType.setValue(setMode);
            elementValue.setValue(value);
            e1.appendChild(e2);
        }

        createDocument(path);
    }

    public void createDocument(String path)
    {
        try{
            ((XmlDocument) document).write(new FileOutputStream(path));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}