package diagram;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;

public class CodeGeneratorControl
{
	protected Vector _componentInfos;
	protected Vector _nameIdLinks;
	protected Vector _elements;
	protected DiagramControl _diagramControl;
	protected Associations _associations;
	protected File _outFile;
	protected FileOutputStream _fileOutputStream;
	protected int distributionNum = 0;
	protected int objectCreatorNum = 0;
	protected int transformNum = 0;
	protected int analysisToolNum = 0;

	protected PrintWriter _printWriter;
	protected Vector _setInfo;
	protected int virtualQ = 0;
	protected int presentationNum = 0; // add by Kenny
	protected int dataProcessorNum = 0; // add by Kenny	
	protected String[] setMode0 = {};
	protected String[] setMode1 = {"ObjectCreator", "Distribution"};
	protected String[] setMode2 = {"Distribution", "Transform"};
	protected String[] setMode3 = {"Distribution"};
	protected String[] setMode4 = {"SplitterModel"};
	// add by Matthew
	protected String[] setMode5 = {"MergerModel"};
	// add by Kenny
	protected String[] setMode6 = {"Presentation", "DataProcessor"};	


	public CodeGeneratorControl() {};
	public CodeGeneratorControl(DiagramControl dc, Vector elements, Associations associations)
	{
		_componentInfos = new Vector();
		_nameIdLinks = new Vector();
		_setInfo = new Vector();
		this._elements = elements;
		this._associations = associations;
		_diagramControl = dc;
		try
		{
			_outFile = new File("Main.java");
			_fileOutputStream = new FileOutputStream(_outFile);
			_printWriter = new PrintWriter(_fileOutputStream);			
		}
		catch (IOException e)
		{
			System.out.println("IOException"+e); 
		}

		createComponentInfos();
	}
	
	public void createComponentInfos()
	{
		
		_componentInfos.add(new ComponentInfo("diagram.DiagramSource", "Source", "getSourceConstructorCode", setMode1, "Shape"));
		_componentInfos.add(new ComponentInfo("diagram.DiagramNServer", "NServer", "getNServerConstructorCode", setMode2, "Shape"));
		_componentInfos.add(new ComponentInfo("diagram.DiagramServer", "Server", "getServerConstructorCode", setMode2, "Shape"));
		_componentInfos.add(new ComponentInfo("diagram.DiagramSplitter", "Splitter", "getSplitterConstructorCode", setMode4, "Shape"));
		_componentInfos.add(new ComponentInfo("diagram.DiagramMerger", "Merger", "getMergerConstructorCode", setMode5, "Shape"));
		_componentInfos.add(new ComponentInfo("diagram.DiagramMessageQueueFIFO", "MessageQueueFIFO", "getMessageQueueFIFOConstructorCode", setMode0, "Queue"));
		_componentInfos.add(new ComponentInfo("diagram.DiagramMessageQueueLIFO", "MessageQueueLIFO", "getMessageQueueLIFOConstructorCode", setMode0, "Queue"));
		_componentInfos.add(new ComponentInfo("diagram.DiagramTimeOutFIFO", "TimeOutFIFO", "getTimeOutFIFOConstructorCode", setMode3, "Queue"));
		_componentInfos.add(new ComponentInfo("diagram.DiagramTimeOutLIFO", "TimeOutLIFO", "getTimeOutLIFOConstructorCode", setMode3, "Queue"));
		_componentInfos.add(new ComponentInfo("diagram.DiagramSink", "Sink", "getSinkConstructorCode", setMode0, "Shape"));
		_componentInfos.add(new ComponentInfo("diagram.DiagramAnalysisTool", "AnalysisTool2", "getAnalysisToolConstructorCode", setMode0, "AnalysisTool"));
		// add by Kenny
		_componentInfos.add(new ComponentInfo("diagram.DiagramChart", "Chart", "getChartConstructorCode", setMode6, "Chart"));
	}

	public void generateCode()//createNameIdLinks()
	{
		printImportCode();
		_printWriter.println();
		printClassDeclarationCode();

		for(int i=0; i<_elements.size(); i++)
		{
			//System.out.println("Elements"+_elements);
			DiagramElement e = (DiagramElement)_elements.elementAt(i);
			if((e.getType().getCategory().toLowerCase()).equals("shape"))
			{
				printComponentCreationCode(e);
			}
		}
		
		for(int i=0; i<_elements.size(); i++)
		{
			//System.out.println("printDistribution"+_elements);
			DiagramElement e = (DiagramElement)_elements.elementAt(i);
			//System.out.println("++++++"+e.getClass().getSuperclass().getName());
			if((e.getType().getCategory().toLowerCase()).equals("shape") || e.getClass().getSuperclass().getName().equalsIgnoreCase("diagrammessagequeue"))
			{
				System.out.println("found");
				ComponentInfo cInfo = getComponentInfo(e.getClass().getName());
				String[] sMode = cInfo.getSetMode();
				for(int k=0; k<sMode.length; k++) {
					if(sMode[k].equalsIgnoreCase("distribution")) {
						System.out.println("in distribution");
						printDistributionCode(e);
					}
					else if(sMode[k].equalsIgnoreCase("objectcreator")) {
						printObjectCreatorCode(e);
					}
					else if(sMode[k].equalsIgnoreCase("transform")) {
						printTransformCode(e);
					}
					else if(sMode[k].equalsIgnoreCase("splittermodel")) {
						printSplitterModelCode(e);
					}
					// add by Kenny
					else if(sMode[k].equalsIgnoreCase("presentation")) {
						printPresentationCode(e);
					}
					else if(sMode[k].equalsIgnoreCase("dataprocessor")) {
						printDataProcessorCode(e);
					}										
				}
			}
		}

		_printWriter.println();
		printMainMethodCode();
		
		_printWriter.println();
		printClassCreationCode();

		Vector assos = _associations.getAssociations();
		for(int j=0; j<assos.size(); j++)
		{
			Association a = (Association)assos.elementAt(j);
			if(a.isAssociation())
			{
				DiagramElement fromPort = a.getParent();
				DiagramElement toPort = a.getChild();

				DiagramElement shape1 = _associations.getParent(fromPort);
				DiagramElement shape2 = _associations.getParent(toPort);
				//System.out.println("formport class"+fromPort.getClass().getName());
				if(fromPort.getClass().getName().equalsIgnoreCase("diagram.diagramstartport") ||  fromPort.getClass().getName().equalsIgnoreCase("diagram.diagramendport")) {
					if(fromPort.getClass().getName().equalsIgnoreCase("diagram.diagramstartport")) {
						printAddStartPointCode(shape1, shape2, toPort);
					}
					else if(fromPort.getClass().getName().equalsIgnoreCase("diagram.diagramendport")){
						printAddEndPointCode(shape1, shape2, toPort);
					}

				}
				//Add by kenny
				else if(fromPort.getClass().getName().equalsIgnoreCase("diagram.DiagramDataSourcePort")){
					printSetDataSourcePortCode(shape2, shape1);
				}				
				else {
					if(getComponentType(getComponentInfo(shape1.getClass().getName())).equalsIgnoreCase("queue"))
					{	
						if(fromPort.getClass().getName().equalsIgnoreCase("diagram.diagramexitport")) {
							printSetInputFromExitCode(shape2, shape1);
						}
						else {
							printSetInputCode(shape2, shape1);
						}
					}
					else if(getComponentType(getComponentInfo(shape2.getClass().getName())).equalsIgnoreCase("queue"))
					{
							if(shape1.getClass().getName().equalsIgnoreCase("diagram.DiagramSplitter")) {
								//System.out.println("Splitter");
								String portIndex = String.valueOf(((DiagramPort)fromPort).getPortIndex());
								//System.out.println("portIndex   "+portIndex);
								printSetSplitterOutputCode(shape1, shape2, portIndex);
							}
							else {
								printSetOutputCode(shape1, shape2);
							}
					}
					else if(getComponentType(getComponentInfo(shape1.getClass().getName())).equalsIgnoreCase("shape")&&getComponentType(getComponentInfo(shape2.getClass().getName())).equalsIgnoreCase("shape"))
					{
							System.out.println("no queue////////////////////////////////////////");
							/*
							ComponentInfo c = null;
							int queueNum;
							for(int q=0; q<_componentInfos.size(); q++) {
								c = (ComponentInfo)_componentInfos.elementAt(q);
								if(c.getComponentName().equalsIgnoreCase("messagequeuefifo")) {
									q = _componentInfos.size()+2;
								}
							}
							c.incrementCount();*/
							virtualQ++;
							String queueName = "virtualMessageQ"+virtualQ;
							String createStatement = " MessageQueueFIFO "+ queueName+" = new MessageQueueFIFO(\"virtual queue\");";
							_printWriter.println("\t"+createStatement);

								printSetOutputCode(shape1, queueName);
								printSetInputCode(shape2, queueName);
					}		
				}
			}
		}
		_printWriter.println();
		for(int m=0; m<_setInfo.size(); m++) {
			_printWriter.print((String)_setInfo.elementAt(m));
		}
		
		_printWriter.println();
		printSetAnalysisStartTime();

		_printWriter.println();
		for(int p=0; p<_elements.size(); p++) {
			DiagramElement e = (DiagramElement)_elements.elementAt(p);
			if((e.getType().getCategory().toLowerCase()).equals("shape")) {
				if(getComponentType(getComponentInfo(e.getClass().getName())).equalsIgnoreCase("shape")) {
					printComponentStartCode(e);
				}
			}
		}
		
		_printWriter.println();
		printProcessAddTerminationEvent();

		_printWriter.print("\t");
		printEndQuote();	//end quote for main method

		_printWriter.println();
		printProcessAddTerminationEventMethod();
		
		//
		_printWriter.println();
		printProcessTerminationEvent();

		_printWriter.println();
		printEndQuote();	//end quote for class
		_printWriter.close();
	}
	

	public void printImportCode()
	{
		_printWriter.println("import statistic.*;");
		_printWriter.println("import mcomponent.distribution.*;");
		_printWriter.println("import engine.eventhandler.*;");
		_printWriter.println("import mcomponent.*;");
		_printWriter.println("import mcomponent.queue.*;");
		_printWriter.println("import engine.*;");
		_printWriter.println("import javax.swing.*;");
		//add by kenny
		_printWriter.println("import chart.*;");
			

	}
	
	public void printClassDeclarationCode()
	{	
		_printWriter.println("class Main implements TerminationEventHandler");
		_printWriter.println("{");
	}

	public void printEndQuote()
	{
		_printWriter.println("}");
	}
	
	public void printClassCreationCode()
	{
		_printWriter.println("\t\tMain _main = new Main();");
	}
	
	public void printMainMethodCode()
	{
		_printWriter.println("\tpublic static void main(String[] args)");
		_printWriter.println("\t{");
	}

	public void printSetInputCode(DiagramElement e, DiagramElement arg)
	{
			String object = findVariableName(e.getId());
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t_main."+object+".setInput(_main."+parameter+");");
	}

	public void printSetOutputCode(DiagramElement e, DiagramElement arg)
	{
			String object = findVariableName(e.getId());
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t_main."+object+".setOutput(_main."+parameter+");");
	}
	
	
	public void printSetInputCode(DiagramElement e, String parameter)
	{
			String object = findVariableName(e.getId());
			//String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t_main."+object+".setInput("+parameter+");");
	}

	public void printSetOutputCode(DiagramElement e, String parameter)
	{
			String object = findVariableName(e.getId());
			//String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t_main."+object+".setOutput("+parameter+");");
	}

	public void printSetInputFromExitCode(DiagramElement e, DiagramElement arg)
	{
			String object = findVariableName(e.getId());
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t_main."+object+".setInput(_main."+parameter+".getExit());");
	}

	public void printSetSplitterOutputCode(DiagramElement e, DiagramElement arg, String portIndex)
	{
			String object = findVariableName(e.getId());
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t_main."+object+".setOutput("+portIndex+", _main."+parameter+");");
	}

	public void printAddStartPointCode(DiagramElement e, DiagramElement arg,  DiagramElement p)
	{
		String object = findVariableName(e.getId());
		String parameter = findVariableName(arg.getId());
		String type = "";
		if(p.getClass().getName().equalsIgnoreCase("diagram.diagraminport")) {
			type = "Input";
		}
		else if(p.getClass().getName().equalsIgnoreCase("diagram.diagramoutport")) {
			type = "Output";
		}
		_printWriter.println("\t\t_main."+object+".addStartPoint(_main."+parameter+", \""+type+"\");");
	}

	public void printAddEndPointCode(DiagramElement e, DiagramElement arg,  DiagramElement p)
	{
		String object = findVariableName(e.getId());
		String parameter = findVariableName(arg.getId());
		String type = "";
		if(p.getClass().getName().equalsIgnoreCase("diagram.diagraminport")) {
			type = "Input";
		}
		else if(p.getClass().getName().equalsIgnoreCase("diagram.diagramoutport")) {
			type = "Output";
		}
		_printWriter.println("\t\t_main."+object+".addEndPoint(_main."+parameter+", \""+type+"\");");
	}

	public void printComponentCreationCode(DiagramElement e)
	{
				ComponentInfo c;
				String className;
				String vName;		//variable name
				String cType;	//component type "Queue, Shape etc."


				//System.out.println("in if1");
				c = getComponentInfo(e.getClass().getName());
				//System.out.println("Class name"+e.getClass().getName());

				if(c!=null)
				{
					//System.out.println("in if2");
					c.incrementCount();	//
					className = c.getComponentName();
					vName = getVariableName(c);
					cType = getComponentType(c);
					CodeGenerator _cGenerator = new CodeGenerator();
					Class[] paraTypes = new Class[1];
					paraTypes[0] = e.getClass().getSuperclass();
					Object [] args = new Object[1];
					args[0] = e;
					//System.out.println("paraTypes    "+paraTypes[0]);
					//System.out.println("generator   "+_cGenerator.getClass().getConstructors()[1].getName());
					//System.out.println("++++++++++++++"+_cGenerator+c.getGetConstructorCodeMethod()+"--"+paraTypes[0]+args[0]);
					String code = String.valueOf(getCode(_cGenerator, c.getGetConstructorCodeMethod(), paraTypes, args));
					_printWriter.println("\t"+className+" "+vName+" = new "+code);
					//_printWriter.println("\t"+className+" "+vName+" = new "+className+"(\""+vName+"\");");		
					_nameIdLinks.add(new NameIDLink(e.getId(), vName, cType)); 
				}
				else
				{ 
					System.out.println("c is null");
				}
			
	}

	public ComponentInfo getComponentInfo(String cName)
	{
		System.out.println("Class Name"+ cName);
		boolean found = false;
		ComponentInfo c = null;
	
		int j=0;
		while(j<_componentInfos.size() && !found)
		{
			c = (ComponentInfo)_componentInfos.elementAt(j); 
			if(c.getShapeName().equalsIgnoreCase(cName))
			{
				System.out.println("c" +c);
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

	// find the variable name of existing component using the id
	public String findVariableName(int id)
	{
		String vName = null;
		NameIDLink n = null;
		int k =0;
		boolean found = false;

		while(!found  && k<_nameIdLinks.size()){
			n =(NameIDLink)_nameIdLinks.elementAt(k);
			if(n.getId() == id){
				vName = n.getComponentName();
			}
			k++;
		}
		return vName;
	}

	//Generate a new variable name for the component
	public String getVariableName(ComponentInfo c)
	{
		//System.out.println("c"+ c);
		String variableName = "_";
		
		variableName = variableName.concat(c.getComponentName().concat(Integer.toString(c.getCount())));
		//System.out.println("Variable Name:"+ variableName);

		return variableName;
	}

	// Get the type shape/queue of a component
	public String getComponentType(ComponentInfo c)
	{
		//System.out.println("c"+ c);

		return c.getComponentType();
	}

	public void printAll()
	{
		ComponentInfo  c = null;
		NameIDLink n = null;

		for(int k=0; k<_componentInfos.size(); k++){
			c =(ComponentInfo) _componentInfos.elementAt(k);
			//System.out.println("ComponentInfo          "+c.getShapeName()+"  "+c.getComponentName()+"  "+c.getComponentType()+"  "+c.getCount());
		}

		for(int k=0; k<_nameIdLinks.size(); k++){
			n =(NameIDLink)_nameIdLinks.elementAt(k);
			//System.out.println("Links          "+n.getId()+"  "+n.getComponentName()+"  "+n.getComponentType());
		}
	}

	public Object getCode(Object parent, String methodName, Class[] paraTypes, Object[] args) {
		try {
			Class parentType = parent.getClass();
			Class[] para = new Class[1];
			Method getMethod = parentType.getDeclaredMethod(methodName, paraTypes);
			return getMethod.invoke(parent, args);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

	public String getDistributionVar() {

		return "_distribution"+distributionNum;
	}
	
	public String getObjectCreatorVar() {
		return "_objectCreator"+objectCreatorNum;
	}
	
	public String getTransformVar() {
		return "_transform"+transformNum;
	}

	
	public String getAnalysisToolVar() {
		return "_analysisTool"+analysisToolNum;
	}

	public void printDistributionCode(DiagramElement e) {
		
		CodeGenerator _cGenerator = new CodeGenerator();
		distributionNum++;
		String object = findVariableName(e.getId());
		String distributionVar = getDistributionVar();
		Class[] paraTypes = new Class[3];
		//System.out.println("***************"+e.getClass().getSuperclass().getName());
		if((e.getClass().getSuperclass().getName()).equalsIgnoreCase("diagram.diagrammessagequeue")) {
			//System.out.println("------------"+e.getClass().getSuperclass().getName());
			paraTypes[0] = e.getClass().getSuperclass().getSuperclass();
		}
		else {
		paraTypes[0] = e.getClass().getSuperclass();
		}
		paraTypes[1] = distributionVar.getClass();
		paraTypes[2] = _setInfo.getClass();

		Object[] args = new Object[3];
		args[0] = e;
		args[1] = distributionVar;
		args[2] = _setInfo;

		Object code = getCode(_cGenerator, "getDistributionCode", paraTypes, args);
		
		_printWriter.println("\t"+String.valueOf(code));
		_setInfo.add("\t\t_main."+object+".setDistribution(_main."+distributionVar+");\n");
		//_printWriter.println("\t"+object+".setDistribution("+distributionVar+")");


	}

	public void printObjectCreatorCode(DiagramElement e) {
		
		CodeGenerator _cGenerator = new CodeGenerator();
		objectCreatorNum++;
		String object = findVariableName(e.getId());
		String objectCreatorVar = getObjectCreatorVar();
		Class[] paraTypes = new Class[3];
		
		paraTypes[0] = e.getClass().getSuperclass();
		paraTypes[1] = objectCreatorVar.getClass();
		paraTypes[2] = _setInfo.getClass();

		Object[] args = new Object[3];
		args[0] = e;
		args[1] = objectCreatorVar;
		args[2] = _setInfo;

		Object code = getCode(_cGenerator, "getObjectCreatorCode", paraTypes, args);
		
		_printWriter.println("\t"+String.valueOf(code));
		_setInfo.add("\t\t_main."+object+".setObjectCreator(_main."+objectCreatorVar+");\n");
		//_printWriter.println("\t"+object+".setDistribution("+distributionVar+")");


	}

	public void printTransformCode(DiagramElement e) {
		
		CodeGenerator _cGenerator = new CodeGenerator();
		transformNum++;
		String object = findVariableName(e.getId());
		String transformVar = getTransformVar();
		Class[] paraTypes = new Class[3];
		paraTypes[0] = e.getClass().getSuperclass();
		paraTypes[1] = transformVar.getClass();
		paraTypes[2] = _setInfo.getClass();

		Object[] args = new Object[3];
		args[0] = e;
		args[1] = transformVar;
		args[2] = _setInfo;

		Object code = getCode(_cGenerator, "getTransformCode", paraTypes, args);
		_printWriter.println("\t"+String.valueOf(code));
		_setInfo.add("\t\t_main."+object+".setTransform(_main."+transformVar+");\n");
		//_printWriter.println("\t"+object+".setDistribution("+distributionVar+")");


	}

	public void printSplitterModelCode(DiagramElement e) {
		String object = findVariableName(e.getId());
		_setInfo.add("\t\t_main."+object+".setSplitterModel(new DefaultSplitterModel2());\n");
	}

	public void printComponentStartCode(DiagramElement e) {
		String object = findVariableName(e.getId());
		_printWriter.println("\t\t_main."+object+".start();");
	}

	public void printProcessAddTerminationEvent() {
		_printWriter.println("\t\t_main.processAddTerminationEvent();");
	}
	
	public void printProcessAddTerminationEventMethod() {
		String _simulationTime = "";
		Vector _properties = _diagramControl.getProperties();
		Property p = null;
		for(int d=0; d<_properties.size(); d++) {
			p = (Property)_properties.elementAt(d);
			if(p.getName().equalsIgnoreCase("simulation time(s)")) {
				_simulationTime = String.valueOf(p.getValue());
			}
		}
		_printWriter.println("\tpublic void processAddTerminationEvent() {\n");
		_printWriter.println("\t\tSimThread.getSimSystemData().addEvent(new engine.Event("+_simulationTime+", this, \"Termination\", null));");
		_printWriter.println("\t}");

	}

	public void printProcessTerminationEvent() {
		_printWriter.println("\tpublic void processTerminationEvent(engine.Event _event) {\n");
		DiagramElement s = null;
		//_printWriter.println("JOptionPane.showMessageDialog(\"Begin compilation\");");
		for(int c=0; c<_elements.size(); c++) {
			s = (DiagramElement)_elements.elementAt(c);
			if((s.getType().getCategory().toLowerCase()).equals("shape")) {
				//System.out.println("sssssssssssssssss"+s.getClass().getName());
				if(getComponentType(getComponentInfo(s.getClass().getName())).equalsIgnoreCase("analysistool")) {
					_printWriter.println("\t\t"+findVariableName(s.getId())+".display();");
				}
			}
		}
		//_printWriter.println("JOptionPane.showMessageDialog(\"After compilation\");");
		_printWriter.println("AnalysisTool2.displayAll();");
		_printWriter.println("\t}");
		
	}

	public void printSetAnalysisStartTime() {
		DiagramElement s = null;
		String startTime = "";
		String analyzeTime = "";
		Vector properties = null;
		for(int c=0; c<_elements.size(); c++) {
			s = (DiagramElement)_elements.elementAt(c);
			if((s.getType().getCategory().toLowerCase()).equals("shape")) {
				//System.out.println("sssssssssssssssss"+s.getClass().getName());
				if(getComponentType(getComponentInfo(s.getClass().getName())).equalsIgnoreCase("analysistool")) {
					properties = ((DiagramShape)s).getProperties();
					for(int a=0; a<properties.size(); a++) {
						if(((Property)properties.elementAt(a)).getName().equalsIgnoreCase("start time")) {
							startTime = String.valueOf(((Property)properties.elementAt(a)).getValue());
							_printWriter.println("\t\t_main."+findVariableName(s.getId())+".setStartTime("+startTime+");");
						}
						else if(((Property)properties.elementAt(a)).getName().equalsIgnoreCase("analyze time")) {
							analyzeTime = String.valueOf(((Property)properties.elementAt(a)).getValue());
							_printWriter.println("\t\t_main."+findVariableName(s.getId())+".setAnalyzeTime("+analyzeTime+");");
						}
					}
					
				}
			}
		}
	}
	// add by Kenny
	public void printSetDataSourcePortCode(DiagramElement e, DiagramElement arg)
	{
		String object = findVariableName(e.getId());
		String parameter = findVariableName(arg.getId());
		System.out.println("ObjName: "+object+" ParName: "+parameter);
		_printWriter.println("\t\t_main."+object+".setDataSource(_main."+parameter+");");
	}
	
	// add by Kenny
	public String getPresentationVar() {
		return "_presentation"+presentationNum;
	}
	
	// add by Kenny
	public String getDataProcessorVar() {
		return "_dataProcessor"+dataProcessorNum;
	}
	
	// add by Kenny
	public void printDataProcessorCode(DiagramElement e) {
		
		CodeGenerator _cGenerator = new CodeGenerator();
		dataProcessorNum++;
		String object = findVariableName(e.getId());
		String dataProcessorVar = getDataProcessorVar();
		Class[] paraTypes = new Class[3];
		paraTypes[0] = e.getClass().getSuperclass();
		paraTypes[1] = dataProcessorVar.getClass();
		paraTypes[2] = _setInfo.getClass();

		Object[] args = new Object[3];
		args[0] = e;
		args[1] = dataProcessorVar;
		args[2] = _setInfo;

		Object code = getCode(_cGenerator, "getDataProcessorCode", paraTypes, args);
		_printWriter.println("\t"+String.valueOf(code));
		_setInfo.add("\t\t_main."+object+".setDataProcessor(_main."+dataProcessorVar+");\n");
		//_printWriter.println("\t"+object+".setDistribution("+distributionVar+")");
		
	}
	
	// add by Kenny
	public void printPresentationCode(DiagramElement e) {
		
		CodeGenerator _cGenerator = new CodeGenerator();
		presentationNum++;
		String object = findVariableName(e.getId());
		String presentationVar = getPresentationVar();
		Class[] paraTypes = new Class[3];
		paraTypes[0] = e.getClass().getSuperclass();
		paraTypes[1] = presentationVar.getClass();
		paraTypes[2] = _setInfo.getClass();

		Object[] args = new Object[3];
		args[0] = e;
		args[1] = presentationVar;
		args[2] = _setInfo;

		Object code = getCode(_cGenerator, "getPresentationCode", paraTypes, args);
		_printWriter.println("\t"+String.valueOf(code));
		_setInfo.add("\t\t_main."+object+".setPresentation(_main."+presentationVar+");\n");
		//_printWriter.println("\t"+object+".setDistribution("+distributionVar+")");
		
	}
	
}
