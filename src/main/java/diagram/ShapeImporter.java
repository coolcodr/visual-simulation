package diagram;

import java.lang.reflect.Constructor;
import java.util.Vector;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ShapeImporter extends Importer {
    private String[] primitiveAry = { "java.lang.String", "java.lang.Integer", "java.lang.Double" };

    public Vector createProperties(Node _dNode) {
        Vector _properties = new Vector();

        // Get all property Tags
        NodeList _propertyNodes = ((Element) _dNode).getElementsByTagName("Property");
        for (int i = 0; i < _propertyNodes.getLength(); i++) {
            // get the attributes of property tag
            Element _propertyTag = (Element) _propertyNodes.item(i);
            NamedNodeMap _attributes = _propertyTag.getAttributes();
            String _pName = ((Attr) _attributes.item(0)).getValue();
            String _pType = ((Attr) _attributes.item(1)).getValue();
            String _pValue = ((Attr) _attributes.item(2)).getValue();
            String _pSubType = ((Attr) _attributes.item(3)).getValue();

            // check if the type is primitive type
            Object _o = null;
            if (isPrimitive(_pType)) {
                System.out.println("PType  " + _pType + "     PValue    " + _pValue);
                _o = createPrimitiveObject(_pType, _pValue);
                System.out.println("Object" + _o);
            } else {

                PropertiesSetting _pSetting = new PropertiesSetting();
                try {
                    _o = Class.forName(_pSubType).newInstance();
                } catch (Exception e) {
                    System.out.println("exception" + e);
                }

                NodeList _parameterNodes = ((Element) _dNode).getElementsByTagName("Parameter");
                for (int j = 0; j < _parameterNodes.getLength(); j++) {
                    Element _parameterTag = (Element) _parameterNodes.item(j);
                    // Get the attributes of parameter
                    NamedNodeMap _paraAttributes = _parameterTag.getAttributes();
                    String paraType = ((Attr) _paraAttributes.item(1)).getValue();
                    String setMethod = ((Attr) _paraAttributes.item(2)).getValue();
                    String paraValue = ((Attr) _paraAttributes.item(3)).getValue();
                    System.out.println("paraType     " + paraType + "paraValue    " + paraValue);
                    Object _paraValueObject = createPrimitiveObject(paraType, paraValue);
                    System.out.println("Object" + _o);
                    System.out.println("setMehod" + setMethod);
                    System.out.println("_paraValueObject" + _paraValueObject);
                    _pSetting.set(_o, setMethod, _paraValueObject);

                }
            }
            Property _p = new Property(_pType, _pName, _o);
            _properties.add(_p);
        }
        return _properties;
    }

    public boolean isPrimitive(String type) {
        boolean isPrimitiveType = false;
        int i = 0;
        while (!isPrimitiveType && i < primitiveAry.length) {
            if (type.equalsIgnoreCase(primitiveAry[i])) {
                isPrimitiveType = true;
            }
            i++;
        }
        return isPrimitiveType;
    }

    public Object createPrimitiveObject(String type, String value) {
        Object o = null;
        try {
            // The array of parameter of the constructor, here is the class is
            // String
            Class[] para = new Class[1];
            para[0] = Class.forName("java.lang.String");
            // get the constructor of the class "type"
            Constructor c = Class.forName(type).getConstructor(para);
            // get the argument of the constructor
            Object[] argu = new Object[1];
            argu[0] = value;
            // use the constructor to new an instance
            o = c.newInstance(argu);

        } catch (ClassNotFoundException ce) {
            System.out.println(ce);
        } catch (NoSuchMethodException ne) {
            System.out.println(ne);
        } catch (InstantiationException ie) {
            System.out.println(ie);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println("oooooooooooooooooo" + o);
        return o;
    }

}
