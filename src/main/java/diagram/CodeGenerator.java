package diagram;

import java.util.Vector;

public class CodeGenerator {
    // add by Kenny
    public String getChartConstructorCode(DiagramShape e) {

        String name = getName(e);
        return "Chart(" + " \"" + name + "\" " + ");";
    }

    // add by Kenny
    public String getPresentationCode(DiagramShape e, String presentationVar, Vector setInfo) {
        PropertiesTableReader _pReader = new PropertiesTableReader();
        PropertiesSetting _pSetting = new PropertiesSetting();
        Vector _ps = e.getProperties();
        Object presentationObj = null;

        // Get the presentation object
        for (int i = 0; i < _ps.size(); i++) {
            Property _p = (Property) _ps.elementAt(i);
            if (_p.getName().equalsIgnoreCase("presentation")) {
                presentationObj = _p.getValue();
            }
        }
        // Use the presentation object to get method name...
        int dotPosition = presentationObj.getClass().getName().lastIndexOf(".");
        String className = presentationObj.getClass().getName().substring(dotPosition + 1);
        String returnCode = className + " " + presentationVar + " = new " + className + "();";
        String getMethod = "";
        String arg = "";
        PropertiesTableData[] _pDataAry = _pReader.getPropertiesTableData(presentationObj.getClass().getName());
        for (int i = 0; i < _pDataAry.length; i++) {
            getMethod = _pDataAry[i].getGetMethod();
            if (getMethod != null && !getMethod.equals("null")) {
                arg = String.valueOf(_pSetting.get(presentationObj, getMethod));
                // System.out.println("getMethod" +
                // getMethod+"          arg "+arg);
                setInfo.add("\t\t_main." + presentationVar + "." + _pDataAry[i].getSetMethod() + "( " + arg + " );\n");
            }
        }
        // System.out.println("Return Code       "+returnCode);
        return returnCode;
    }

    // add by Kenny
    public String getDataProcessorCode(DiagramShape e, String dataProcessorVar, Vector setInfo) {
        PropertiesTableReader _pReader = new PropertiesTableReader();
        PropertiesSetting _pSetting = new PropertiesSetting();
        Vector _ps = e.getProperties();
        Object dataProcessorObj = null;

        // Get the dataProcessor object
        for (int i = 0; i < _ps.size(); i++) {
            Property _p = (Property) _ps.elementAt(i);
            if (_p.getName().equalsIgnoreCase("DataProcessor")) {
                dataProcessorObj = _p.getValue();
            }
        }
        // Use the dataProcessor object to get method name...
        int dotPosition = dataProcessorObj.getClass().getName().lastIndexOf(".");
        String className = dataProcessorObj.getClass().getName().substring(dotPosition + 1);
        String returnCode = className + " " + dataProcessorVar + " = new " + className + "();";
        String getMethod = "";
        String arg = "";
        PropertiesTableData[] _pDataAry = _pReader.getPropertiesTableData(dataProcessorObj.getClass().getName());
        for (int i = 0; i < _pDataAry.length; i++) {
            getMethod = _pDataAry[i].getGetMethod();
            if (getMethod != null && !getMethod.equals("null")) {
                arg = String.valueOf(_pSetting.get(dataProcessorObj, getMethod));
                // System.out.println("getMethod" +
                // getMethod+"          arg "+arg);
                setInfo.add("\t\t_main." + dataProcessorVar + "." + _pDataAry[i].getSetMethod() + "( " + arg + " );\n");
            }
        }
        // System.out.println("Return Code       "+returnCode);
        return returnCode;
    }

    public String getSourceConstructorCode(DiagramShape e) {

        String name = getName(e);
        return "Source(" + " \"" + name + "\" " + ");";
    }

    public String getMessageQueueFIFOConstructorCode(DiagramMessageQueue e) {

        String name = getName((DiagramShape) e);
        String _length = getQLength((DiagramShape) e);

        if (_length.equals("-1")) {
            return "MessageQueueFIFO(" + " \"" + name + "\" " + ");";
        } else {
            return "MessageQueueFIFO(" + " \"" + name + "\", " + _length + ");";
        }
    }

    public String getMessageQueueLIFOConstructorCode(DiagramMessageQueue e) {

        String name = getName((DiagramShape) e);
        String _length = getQLength((DiagramShape) e);

        if (_length.equals("-1")) {
            return "MessageQueueLIFO(" + " \"" + name + "\" " + ");";
        } else {
            return "MessageQueueLIFO(" + " \"" + name + "\", " + _length + ");";
        }
    }

    public String getTimeOutFIFOConstructorCode(DiagramMessageQueue e) {

        String name = getName((DiagramShape) e);
        String _length = getQLength((DiagramShape) e);

        if (_length.equals("-1")) {
            return "TimeOutFIFO(" + " \"" + name + "\" " + ");";
        } else {
            return "TimeOutFIFO(" + " \"" + name + "\", " + _length + ");";
        }
    }

    public String getTimeOutLIFOConstructorCode(DiagramMessageQueue e) {

        String name = getName((DiagramShape) e);
        String _length = getQLength((DiagramShape) e);

        if (_length.equals("-1")) {
            return "TimeOutLIFO(" + " \"" + name + "\" " + ");";
        } else {
            return "TimeOutLIFO(" + " \"" + name + "\", " + _length + ");";
        }
    }

    public String getServerConstructorCode(DiagramShape e) {

        String name = getName((DiagramShape) e);
        return "Server(" + " \"" + name + "\" " + ");";
    }

    public String getSplitterConstructorCode(DiagramShape e) {

        String name = getName((DiagramShape) e);
        String outputNum = getOutputNum((DiagramShape) e);

        return "Splitter(" + " \"" + name + "\", " + outputNum + ");";

    }

    public String getMergerConstructorCode(DiagramShape e) {

        String name = getName((DiagramShape) e);
        String inputNum = getInputNum((DiagramShape) e);

        return "Merger(" + " \"" + name + "\", " + inputNum + ");";

    }

    public String getSinkConstructorCode(DiagramShape e) {

        String name = getName((DiagramShape) e);
        return "Sink(" + " \"" + name + "\" " + ");";
    }

    public String getNServerConstructorCode(DiagramShape e) {
        Vector _ps = e.getProperties();
        String serverNum = "";

        for (int i = 0; i < _ps.size(); i++) {
            Property _p = (Property) _ps.elementAt(i);
            if (_p.getName().equalsIgnoreCase("server no")) {
                serverNum = String.valueOf(_p.getValue());
            }
        }
        String name = getName(e);
        return "NServer(" + " \"" + name + "\", " + serverNum + " );";
    }

    public String getAnalysisToolConstructorCode(DiagramShape e) {
        String name = getName(e);
        return "AnalysisTool2(" + " \"" + name + "\" " + ");";
    }

    public String getName(DiagramShape e) {
        Vector _ps = e.getProperties();
        String name = "";

        for (int i = 0; i < _ps.size(); i++) {
            Property _p = (Property) _ps.elementAt(i);
            if (_p.getName().equalsIgnoreCase("Name")) {
                name = String.valueOf(_p.getValue());
            }
        }
        return name;
    }

    public String getOutputNum(DiagramShape e) {
        Vector _ps = e.getProperties();
        String outputNum = "";

        for (int i = 0; i < _ps.size(); i++) {
            Property _p = (Property) _ps.elementAt(i);
            if (_p.getName().equalsIgnoreCase("output no")) {
                outputNum = String.valueOf(_p.getValue());
            }
        }
        return outputNum;
    }

    public String getInputNum(DiagramShape e) {
        Vector _ps = e.getProperties();
        String inputNum = "";

        for (int i = 0; i < _ps.size(); i++) {
            Property _p = (Property) _ps.elementAt(i);
            if (_p.getName().equalsIgnoreCase("input no")) {
                inputNum = String.valueOf(_p.getValue());
            }
        }
        return inputNum;
    }

    public String getQLength(DiagramShape e) {
        Vector _ps = e.getProperties();
        String _length = "";

        for (int i = 0; i < _ps.size(); i++) {
            Property _p = (Property) _ps.elementAt(i);
            if (_p.getName().equalsIgnoreCase("queue limit")) {
                _length = String.valueOf(_p.getValue());
            }
        }
        return _length;
    }

    public String getDistributionCode(DiagramShape e, String distributionVar, Vector setInfo) {
        PropertiesTableReader _pReader = new PropertiesTableReader();
        PropertiesSetting _pSetting = new PropertiesSetting();
        Vector _ps = e.getProperties();
        Object distributionObj = null;

        // Get the distribution object
        for (int i = 0; i < _ps.size(); i++) {
            Property _p = (Property) _ps.elementAt(i);
            if (_p.getName().equalsIgnoreCase("Distribution")) {
                distributionObj = _p.getValue();
            }
        }
        // Use the distribution object to get method name...
        int dotPosition = distributionObj.getClass().getName().lastIndexOf(".");
        String className = distributionObj.getClass().getName().substring(dotPosition + 1);

        String returnCode = className + " " + distributionVar + " = new " + className + "();";
        String getMethod = "";
        String arg = "";
        PropertiesTableData[] _pDataAry = _pReader.getPropertiesTableData(distributionObj.getClass().getName());
        for (int i = 0; i < _pDataAry.length; i++) {
            getMethod = _pDataAry[i].getGetMethod();
            if (getMethod != null && !getMethod.equals("null")) {
                arg = String.valueOf(_pSetting.get(distributionObj, getMethod));
                // System.out.println("getMethod" +
                // getMethod+"          arg "+arg);
                setInfo.add("\t\t_main." + distributionVar + "." + _pDataAry[i].getSetMethod() + "( " + arg + " );\n");
            }
        }
        // System.out.println("Return Code       "+returnCode);
        return returnCode;
    }

    public String getObjectCreatorCode(DiagramShape e, String objectCreatorVar, Vector setInfo) {
        PropertiesTableReader _pReader = new PropertiesTableReader();
        PropertiesSetting _pSetting = new PropertiesSetting();
        Vector _ps = e.getProperties();
        Object objectCreatorObj = null;

        // Get the distribution object
        for (int i = 0; i < _ps.size(); i++) {
            Property _p = (Property) _ps.elementAt(i);
            if (_p.getName().equalsIgnoreCase("Object Creator")) {
                objectCreatorObj = _p.getValue();
            }
        }
        // Use the distribution object to get method name...
        int dotPosition = objectCreatorObj.getClass().getName().lastIndexOf(".");
        String className = objectCreatorObj.getClass().getName().substring(dotPosition + 1);

        String returnCode = className + " " + objectCreatorVar + " = new " + className + "();";
        String getMethod = "";
        String arg = "";
        PropertiesTableData[] _pDataAry = _pReader.getPropertiesTableData(objectCreatorObj.getClass().getName());
        for (int i = 0; i < _pDataAry.length; i++) {
            getMethod = _pDataAry[i].getGetMethod();
            if (getMethod != null && !getMethod.equals("null")) {
                arg = String.valueOf(_pSetting.get(objectCreatorObj, getMethod));
                // System.out.println("getMethod" +
                // getMethod+"          arg "+arg);
                setInfo.add("\t\t_main." + objectCreatorVar + "." + _pDataAry[i].getSetMethod() + "( " + arg + " );\n");
            }
        }
        // System.out.println("Return Code       "+returnCode);
        return returnCode;
    }

    public String getTransformCode(DiagramShape e, String transformVar, Vector setInfo) {
        PropertiesTableReader _pReader = new PropertiesTableReader();
        PropertiesSetting _pSetting = new PropertiesSetting();
        Vector _ps = e.getProperties();
        Object transformObj = null;

        // Get the distribution object
        for (int i = 0; i < _ps.size(); i++) {
            Property _p = (Property) _ps.elementAt(i);
            if (_p.getName().equalsIgnoreCase("transform")) {
                transformObj = _p.getValue();
            }
        }
        // Use the distribution object to get method name...
        int dotPosition = transformObj.getClass().getName().lastIndexOf(".");
        String className = transformObj.getClass().getName().substring(dotPosition + 1);
        String returnCode = className + " " + transformVar + " = new " + className + "();";
        String getMethod = "";
        String arg = "";
        PropertiesTableData[] _pDataAry = _pReader.getPropertiesTableData(transformObj.getClass().getName());
        for (int i = 0; i < _pDataAry.length; i++) {
            getMethod = _pDataAry[i].getGetMethod();
            if (getMethod != null && !getMethod.equals("null")) {
                arg = String.valueOf(_pSetting.get(transformObj, getMethod));
                // System.out.println("getMethod" +
                // getMethod+"          arg "+arg);
                setInfo.add("\t\t_main." + transformVar + "." + _pDataAry[i].getSetMethod() + "( " + arg + " );\n");
            }
        }
        // System.out.println("Return Code       "+returnCode);
        return returnCode;
    }

}
