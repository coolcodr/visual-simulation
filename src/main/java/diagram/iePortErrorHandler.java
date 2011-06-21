package diagram;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class iePortErrorHandler implements ErrorHandler {
    public void fatalError(SAXParseException exception) throws SAXException {
        throw exception;
    }

    public void error(SAXParseException e) throws SAXParseException {
        throw e;
    }

    public void warning(SAXParseException err) throws SAXParseException {
        System.err.println("Warning: " + err.getMessage());
    }
}
