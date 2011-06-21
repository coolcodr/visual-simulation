package designer.deployment;

import java.io.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import org.apache.crimson.tree.XmlDocument;
import javax.swing.JFileChooser;
import java.awt.Component;

public class ImportDataControl
{
    private Document document;
    private DeployObject deployObject;

    public ImportDataControl(DeployObject deployObject)
    {
        this.deployObject = deployObject;
    }

    public void loadData(Component parent)
    {
        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new XFileFilter());
        fc.showOpenDialog(parent);
        File file = fc.getSelectedFile();
        String path = null;
        if ( file != null )
        {
            if (file.isFile())
                path = file.getAbsolutePath();
            createDocumentNode(path);
        }
    }

    public Document createDocumentNode()
    {
        return createDocumentNode("simulation_data_tmp.xml"); //TEMP
    }

    public Document createDocumentNode(String path)
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(path));

            loadData();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return document;
    }

    public void loadData() throws InvalidDataException
    {
        Node root = document.getDocumentElement();

        if (root.getNodeType() == Node.ELEMENT_NODE)
        {
            Element properties = (Element) root;
            NodeList inputNodes = properties.getElementsByTagName("Input");

            int inputNodeCount = inputNodes.getLength();

            if (inputNodeCount != deployObject.getInputObjectCount())
                throw new InvalidDataException("Invalid data file for this simultation model");

            for (int i = 0; i < inputNodeCount; i++)
            {
                InputComponent inputComponent = (InputComponent) deployObject.getInputObject(i);

                Node inputNode = inputNodes.item(i);

                NamedNodeMap diagramAttributes = ( (Element) inputNode).getAttributes();

                Attr nameNode = (Attr) diagramAttributes.getNamedItem("Name");
                Attr typeNode = (Attr) diagramAttributes.getNamedItem("Type");
                Attr valueNode = (Attr) diagramAttributes.getNamedItem("Value");

                if (!inputComponent.getName().equalsIgnoreCase(nameNode.getValue()) ||
                    !inputComponent.getSetMode().equalsIgnoreCase(typeNode.getValue()))
                    throw new InvalidDataException("Invalid data file for this simultation model");

                if (inputComponent.isVisible())
                {
                    String value = valueNode.getValue();
                    inputComponent.setInputValue(value);
                }
            }

            NodeList internalInputNodes = properties.getElementsByTagName("InternalInput");

            int internalInputNodeCount = internalInputNodes.getLength();

            if (internalInputNodeCount != deployObject.getInternalInputObjectCount())
                throw new InvalidDataException("Invalid data file for this simultation model");

            for (int i = 0; i < internalInputNodeCount; i++)
            {
                InputComponent inputComponent = (InputComponent) deployObject.getInternalInputObject(i);

                Node inputNode = internalInputNodes.item(i);

                NamedNodeMap diagramAttributes = ( (Element) inputNode).getAttributes();

                Attr nameNode = (Attr) diagramAttributes.getNamedItem("Name");
                Attr modeNode = (Attr) diagramAttributes.getNamedItem("Mode");
                Attr typeNode = (Attr) diagramAttributes.getNamedItem("Type");
                Attr valueNode = (Attr) diagramAttributes.getNamedItem("Value");

                if (!inputComponent.getName().equalsIgnoreCase(nameNode.getValue() + "|" + modeNode.getValue()) ||
                    !inputComponent.getSetMode().equalsIgnoreCase(typeNode.getValue()))
                    throw new InvalidDataException("Invalid data file for this simultation model");

                if (inputComponent.isVisible())
                {
                    String value = valueNode.getValue();
                    inputComponent.setInputValue(value);
                }
            }
        }
    }
}