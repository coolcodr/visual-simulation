package designer;

import diagram.*;
import designer.deployment.*;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import javax.swing.tree.*;

public class DiagramElementSource
{
    protected Vector _componentInfos;

    protected DiagramControl _diagramControl;
    protected Vector _elements;
    protected Associations _associations;

    protected String[] setMode0 = {};
    protected String[] setMode1 = {"ObjectCreator", "Distribution"};
    protected String[] setMode2 = {"Distribution", "Transform"};
    protected String[] setMode3 = {"Distribution"};
    protected String[] setMode4 = {"SplitterModel"};
    protected String[] setMode5 = {"MergerModel"};
    // add by Kenny
	protected String[] setMode6 = {"Presentation", "DataProcessor"};

    private PropertiesSetting pSetting;

    private DiagramSourceList diagramSourceList ;//= new DiagramSourceList();
    private DiagramSourceAnalysisList diagramAnalysisList;

    private DiagramControl dc;
    private Vector elements;
    private Associations associations;

    public DiagramElementSource(DiagramControl dc, Vector elements, Associations associations)
    {
        this.dc = dc;
        this.elements = elements;
        this.associations = associations;
        this.generateList ();
    }
    public DiagramSourceList regenerateList ( String message )
    {
        generateList (message);
        return getDiagramSourceList();
    }
    public void generateList ()
    {
        generateList (null);
    }
    private void addAnalysisNode2 ( String name, String middle, DefaultMutableTreeNode node )
    {
        AnalysisSet set;
        set = new AnalysisSet(name, "Average");
        set.setTag(middle);
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(set);
        set = new AnalysisSet(name, "Mean");
        set.setTag(middle);
        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode(set);
        set = new AnalysisSet(name, "Maximum");
        set.setTag(middle);
        DefaultMutableTreeNode node4 = new DefaultMutableTreeNode(set);
        set = new AnalysisSet(name, "Minimum");
        set.setTag(middle);
        DefaultMutableTreeNode node5 = new DefaultMutableTreeNode(set);
        node.add(node2);
        node.add(node3);
        node.add(node4);
        node.add(node5);
    }
    public void addAnalysisNode ( String name, String title )
    {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(title);
        DefaultMutableTreeNode newNode1 = new DefaultMutableTreeNode("Service Time");
        DefaultMutableTreeNode newNode2 = new DefaultMutableTreeNode("Utilization");
        DefaultMutableTreeNode newNode3 = new DefaultMutableTreeNode("Thorughput");


        addAnalysisNode2 (name, "Service Time", newNode1);
        addAnalysisNode2 (name, "Utilization", newNode2);
        addAnalysisNode2 (name, "Thorughput", newNode3);

        newNode.add(newNode1);
        newNode.add(newNode2);
        newNode.add(newNode3);

        addAnalysisElement(newNode);
    }
    public void addChartNode ( String name, String title )
    {
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(title);

        ChartSet set;
        set = new ChartSet(name, "BarChart");
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(set);
        newNode.add(node1);
        addAnalysisElement(newNode);
    }
    public void generateList ( String message )
    {
        diagramSourceList = new DiagramSourceList();
        diagramAnalysisList = new DiagramSourceAnalysisList();

        _componentInfos = new Vector();

        this._diagramControl = dc;
        this._elements = elements;
        this._associations = associations;

        this.pSetting = new PropertiesSetting();

        createComponentInfos();

        ComponentInfo c;

        System.gc();
        GenerateProgress progress = new GenerateProgress();
        if ( message != null )
            progress.setText(message);
        progress.setMaximun( (_elements.size() - 1) * 100 );
        progress.setVisible(true);
        int count = 0;

        for ( int i = 0 ; i < _elements.size() ; i ++ )
        {
            c = getComponentInfo(((DiagramElement)_elements.elementAt(i)).getClass().getName());
            count = i * 100;
            progress.setValue(count);
            if( c != null )
            {
                String className = ((DiagramElement)_elements.elementAt(i)).getClass().getName();

                //Can be use class name also but will so confused in designing. so now commented
                //DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(className.substring(className.lastIndexOf(".")+1));

                //First propertise is Name, redundent in the Table!
                String property = ((Property)(((DiagramShape)_elements.elementAt(i)).getProperties().firstElement())).getValue().toString();
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(property);



                Vector properties = ((DiagramShape)(_elements.elementAt(i))).getProperties();
                //This name is generate such as "server_1" ..."sever_9"... etc.
                String name = getName ( ((DiagramElement)_elements.elementAt(i)) );


                if ( className.equalsIgnoreCase("diagram.DiagramChart") )
                {
                    addChartNode(name, property);
                    //break;
                }
                else
                {
                    if ( className.equalsIgnoreCase("diagram.DiagramAnalysisTool") )
                        addAnalysisNode(name, property);

                    this.addListElement(newNode);

                    for (int j = 0; j < properties.size(); j++)
                    {
                        progress.setValue(count + 1 < i * 100 + 100 ? ++count : count);

                        Property elementProperty = (Property) (properties.elementAt(j));
                        //Each Diagram Component referenced by a Property
                        DiagramComponentSetPanel setPane1 = new DiagramComponentSetPanel(name, elementProperty);
                        setPane1.setCoverControl(CoverControl.createDraggableControl());

                        DefaultMutableTreeNode p1Node = new DefaultMutableTreeNode(setPane1);

                        //Add to list for UI generate //Temp or test or final...!!
                        diagramSourceList.inputComponent.add(setPane1);
                        diagramSourceList.componentTable.put(setPane1.getName() + "|" + setPane1.getSetMode(), setPane1);
                        //System.out.println("INPUT NAME: "+setPane1.getName());
                        //System.out.println("INPIT Set MODE: "+setPane1.getSetMode());

                        newNode.add(p1Node);

                        PropertiesTableReader pReader = new PropertiesTableReader();
                        String type = ( (Property) (properties.elementAt(j))).getType();

                        //Pass a typr will return a list of typeAry
                        //Tt is the list of the combobox, such as "String creator", "Custom creator"...etc
                        String[] typeAry = pReader.getType(type);

                        if (typeAry.length > 0)
                        {
                            //04 FEB 2003, Now add to the set pane, so that when click the pane or the node the table tree will be refreah by get its root.
                            DefaultMutableTreeNode nestedRoot = new DefaultMutableTreeNode(setPane1);

                            String diagramValue = elementProperty.getValue().getClass().getName();
                            String defaultValue = diagramValue.substring(diagramValue.lastIndexOf(".") + 1);

                            for (int k = 0; k < typeAry.length; k++)
                            {
                                progress.setValue(count + 1 < i * 100 + 100 ? ++count : count);
                                //This will get the nested data list.

                                //Use SimPropertyChoice here is TEMP ONPY!
                                String thisOptionType = typeAry[k].toString().substring(typeAry[k].toString().lastIndexOf(".") + 1);
                                SimPropertyChoice choice = new SimPropertyChoice(thisOptionType);
                                if (thisOptionType.equalsIgnoreCase(defaultValue))
                                {
                                    setPane1.setDefaultValue(choice);
                                }

                                DefaultMutableTreeNode nestNode = new DefaultMutableTreeNode(choice);

                                //For a temp propertise only, it will set much more later
                                /////TEMP////
                                Object[] s1 =
                                    {
                                    "Input Frame", new FrameIDandName("-1", "")};
                                choice.getProperties().add(s1);
                                /////TEMP////

                                //04 FEB 2003, Not add to the smae list but in a new TreeTable
                                //p1Node.add(nestNode);
                                //This will re washed
                                nestedRoot.add(nestNode);

                                p1Node.add(nestNode);

                                //This will get the nested data list.
                                PropertiesTableData[] data = pReader.getPropertiesTableData(typeAry[k]);

                                //Check if have nest data input ( such as setName, setUpperLimit)
                                //This checking may be wrong, but it work now, may be neeed change later
                                //!data[0].getName().equalsIgnoreCase("null") <=== duno correct or not
                                if (data.length > 0 && !data[0].getName().equalsIgnoreCase("null"))
                                {
                                    for (int l = 0; l < data.length; l++)
                                    {
                                        progress.setValue(count + 1 < i * 100 + 100 ? ++count : count);
                                        //Nest set pane
                                        String value = null;
                                        //If the default value is same as this combobox option, the nested component default value
                                        //must set to the value set in the diagramming tool
                                        if (setPane1.getDefultValue().toString().equalsIgnoreCase(thisOptionType))
                                        {
                                            //This is the set the defalut value, is the type settED is this
                                            value = pSetting.get( ( (Property) (properties.elementAt(j))).getValue(), data[l].getGetMethod()).toString();
                                        }
                                        else
                                        {
                                            //This is to set the default to the default Class defined is the typ setED is not this
                                            try
                                            {
                                                value = pSetting.get(Class.forName(data[l].getParent()).newInstance(), data[l].getGetMethod()).toString();
                                            }
                                            catch (Exception e)
                                            {
                                                System.out.println(e);
                                            }
                                        }
                                        if (value == null)
                                            value = "";

                                        Property nestProperty = new Property(data[l].getType(), data[l].getName(), value);
                                        DiagramComponentSetPanel setPane1Child = new DiagramComponentSetPanel(name + "|" + thisOptionType, nestProperty);
                                        setPane1Child.setCoverControl(CoverControl.createDraggableControl());
                                        //Temp or test or final for UI generate
                                        diagramSourceList.inputComponentInternal.add(setPane1Child);
                                        diagramSourceList.componentTable.put(setPane1Child.getName() + "|" + setPane1Child.getSetMode(), setPane1Child);
                                        //System.out.println("INTERNAL NAME: " + setPane1Child.getName());
                                        //System.out.println("INTERNAL Set MODE: " + setPane1Child.getSetMode());

                                        //Nest Node (User obejct: the Nest set pane)
                                        DefaultMutableTreeNode nestNode2 = new DefaultMutableTreeNode(setPane1Child);
                                        nestNode.add(nestNode2);
                                    }
                                }
                            }
                            //04 FEB 2003
                            //13 Feb2003 is not use now
                            setPane1.setInternalValueNode(p1Node);

                            //14 FEB 2003
                            //p1Node.setAllowsChildren(false);
                        }
                    }
                }
            }
        }
        //Add Simulation time set Pane, onyl once
        Vector _properties = _diagramControl.getProperties();
        //System.out.println( _properties.size() );
		Property p = null;
		for( int d = 0; d < _properties.size() ; d++)
        {
            progress.setValue(++count);
			p = (Property)_properties.elementAt(d);
			if(p.getName().equalsIgnoreCase("simulation time(s)"))
			{
				DiagramComponentSetPanel simTimePane = new DiagramComponentSetPanel("_simulationTime1", p);
                diagramSourceList.componentTable.put(simTimePane.getName()+"|"+simTimePane.getSetMode(), simTimePane);

                simTimePane.setCoverControl(CoverControl.createDraggableControl());

                diagramSourceList.inputComponent.add(simTimePane);

            	 //Nest Node (User obejct: the Nest set pane)
                 DefaultMutableTreeNode simTimeNode = new DefaultMutableTreeNode(simTimePane);
                 DefaultMutableTreeNode temp = new DefaultMutableTreeNode("Simulation Parameter");
                 this.addListElement(temp);
                 temp.add(simTimeNode);
			}
		}
        progress.setVisible(false);
        progress.dispose();
    }
    private void addAnalysisElement ( DefaultMutableTreeNode node )
    {
        diagramAnalysisList.addNode(node);
    }
    private void addListElement ( DefaultMutableTreeNode node )
    {
        diagramSourceList.addNode(node);
    }
    public DiagramSourceList getDiagramSourceList ()
    {
        return diagramSourceList;
    }
    public DiagramSourceAnalysisList getAnalysisList ()
    {
        return diagramAnalysisList;
    }
    public void createComponentInfos()
    {
        _componentInfos.add(new ComponentInfo("diagram.DiagramSource", "Source", "getSourceConstructorCode", setMode1, "Shape"));
        _componentInfos.add(new ComponentInfo("diagram.DiagramNServer", "NServer", "getNServerConstructorCode", setMode2, "Shape"));
        _componentInfos.add(new ComponentInfo("diagram.DiagramServer", "Server", "getServerConstructorCode", setMode2, "Shape"));
        _componentInfos.add(new ComponentInfo("diagram.DiagramSplitter", "Splitter", "getSplitterConstructorCode", setMode4, "Shape"));
        _componentInfos.add(new ComponentInfo("diagram.DiagramMessageQueueFIFO", "MessageQueueFIFO", "getMessageQueueFIFOConstructorCode", setMode0, "Queue"));
        _componentInfos.add(new ComponentInfo("diagram.DiagramMessageQueueLIFO", "MessageQueueLIFO", "getMessageQueueLIFOConstructorCode", setMode0, "Queue"));
        _componentInfos.add(new ComponentInfo("diagram.DiagramTimeOutFIFO", "TimeOutFIFO", "getTimeOutFIFOConstructorCode", setMode3, "Queue"));
        _componentInfos.add(new ComponentInfo("diagram.DiagramTimeOutLIFO", "TimeOutLIFO", "getTimeOutLIFOConstructorCode", setMode3, "Queue"));
        _componentInfos.add(new ComponentInfo("diagram.DiagramSink", "Sink", "getSinkConstructorCode", setMode0, "Shape"));
        _componentInfos.add(new ComponentInfo("diagram.DiagramAnalysisTool", "AnalysisTool2", "getAnalysisToolConstructorCode", setMode0, "AnalysisTool"));
    	_componentInfos.add(new ComponentInfo("diagram.DiagramAnalysisTool", "AnalysisTool2", "getAnalysisToolConstructorCode", setMode0, "AnalysisTool"));
    	_componentInfos.add(new ComponentInfo("diagram.DiagramMerger", "Merger", "getMergerConstructorCode", setMode5, "Shape"));
    	// add by Kenny
		_componentInfos.add(new ComponentInfo("diagram.DiagramChart", "Chart", "getChartConstructorCode", setMode5, "Chart"));

    }
    public String getName (DiagramElement e)
    {
        ComponentInfo c;
        String className;
        String vName = null;

        c = getComponentInfo(e.getClass().getName());

        if(c!=null)
        {
            c.incrementCount();
            vName = "_" + c.getComponentName() + Integer.toString(c.getCount());
        }

        return vName;

    }
    public ComponentInfo getComponentInfo( String cName )
    {
        //System.out.println(cName);
        boolean found = false;
        ComponentInfo c = null;

        int j = 0;
        while( j < _componentInfos.size() && !found)
        {
            c = (ComponentInfo) _componentInfos.elementAt(j);
            if( c.getShapeName().equalsIgnoreCase(cName))
            {
                //System.out.println("c: " +c);
                found = true;
            }
            j++;
        }
        if(found)
        {
            return c;
        }
        else
        {
            return null;
        }
    }
}