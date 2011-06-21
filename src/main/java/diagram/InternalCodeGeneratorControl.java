package diagram;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import java.awt.*;

public class InternalCodeGeneratorControl extends CodeGeneratorControl {

	public InternalCodeGeneratorControl(DiagramControl dc, Vector elements, Associations associations)
	{
		
		_componentInfos = new Vector();
		_nameIdLinks = new Vector();
		_setInfo = new Vector();
		_elements = elements;
		_associations = associations;
		_diagramControl = dc;
		try
		{
			_outFile = new File("InternalModel.java");
			_fileOutputStream = new FileOutputStream(_outFile);
			_printWriter = new PrintWriter(_fileOutputStream);			
		}
		catch (IOException e)
		{
			System.out.println("IOException"+e); 
		}

		createComponentInfos();
	}
		
	public void generateCode()//createNameIdLinks()
	{
		printImportCode();
		_printWriter.println("import animation.*;");
		_printWriter.println("import diagram.*;");				
		_printWriter.println("import java.util.*;");
		_printWriter.println("import java.awt.*;");
		_printWriter.println();
		_printWriter.println("public class InternalModel implements TerminationEventHandler");
		_printWriter.println("{");

		for(int i=0; i<_elements.size(); i++)
		{
			//System.out.println("Elements"+_elements);
			DiagramElement e = (DiagramElement)_elements.elementAt(i);
			if((e.getType().getCategory().toLowerCase()).equals("shape"))
			{
				printComponentCreationCode(e);
			}
		}
		_printWriter.println("\tAnimationLine _animationLine;");
		_printWriter.println("\tString _animation;");		
		_printWriter.println("\tPoint _point;");
		_printWriter.println("\tVector _vPoint;");
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
					else if(sMode[k].equalsIgnoreCase("mergermodel")) {
						printMergerModelCode(e);
					}					
//					else if(sMode[k].equalsIgnoreCase("animation")) {
//						printAnimationCode(e);
//					}					
				}
			}
		}

		_printWriter.println();
		_printWriter.println("\tpublic InternalModel(JComponent _sourcePane, JComponent _targetPane, int _speed)");
		_printWriter.println("\t{");
		

		Vector assos = _associations.getAssociations();
		for(int j=0; j<assos.size(); j++)
		{
			Association a = (Association)assos.elementAt(j);
			if(a.isAssociation())
			{
				DiagramElement fromPort = a.getParent();
				DiagramElement toPort = a.getChild();
				DiagramConnector connector  = a.getConnector();

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
				else {
					if(getComponentType(getComponentInfo(shape1.getClass().getName())).equalsIgnoreCase("queue"))
					{	
						//added by matthew
						//for merger usage	
						if(shape2.getClass().getName().equalsIgnoreCase("diagram.DiagramMerger")) {
							String portIndex = String.valueOf(((DiagramPort)toPort).getPortIndex());
							printSetMergerInputCode(shape2, shape1, portIndex, connector);
						}
						else if(fromPort.getClass().getName().equalsIgnoreCase("diagram.diagramexitport")) {
							printSetInputFromExitCode(shape2, shape1);
						}					
						else {
							printSetInputCode(shape2, shape1, connector);
						}
					}
					else if(getComponentType(getComponentInfo(shape2.getClass().getName())).equalsIgnoreCase("queue"))
					{
							if(shape1.getClass().getName().equalsIgnoreCase("diagram.DiagramSplitter")) {
								//System.out.println("Splitter");
								String portIndex = String.valueOf(((DiagramPort)fromPort).getPortIndex());
								//System.out.println("portIndex   "+portIndex);
								//modified by matthew								
								//original: printSetSplitterOutputCode(shape1, shape2, portIndex);
								printSetSplitterOutputCode(shape1, shape2, portIndex, connector);
							}
							else {
								printSetOutputCode(shape1, shape2, connector);
							}
					}
					//Virtual queue section				
					else if(getComponentType(getComponentInfo(shape1.getClass().getName())).equalsIgnoreCase("shape")&&getComponentType(getComponentInfo(shape2.getClass().getName())).equalsIgnoreCase("shape"))
					{
							if(shape2.getClass().getName().equalsIgnoreCase("diagram.DiagramMerger")) {
								String portIndex = String.valueOf(((DiagramPort)toPort).getPortIndex());
								virtualQ++;
								String queueName = "virtualMessageQ"+virtualQ;
								String createStatement = " MessageQueueFIFO "+ queueName+" = new MessageQueueFIFO(\"virtual queue\");";
								_printWriter.println("\t"+createStatement);
	
								printSetOutputCode(shape1, queueName, connector);
								printSetMergerInputCode(shape2, queueName, portIndex, connector);

							}
							else if(shape1.getClass().getName().equalsIgnoreCase("diagram.DiagramSplitter")){
								String portIndex = String.valueOf(((DiagramPort)fromPort).getPortIndex());
								virtualQ++;
								String queueName = "virtualMessageQ"+virtualQ;
								String createStatement = " MessageQueueFIFO "+ queueName+" = new MessageQueueFIFO(\"virtual queue\");";
								_printWriter.println("\t"+createStatement);
	
								printSetSplitterOutputCode(shape1, queueName, portIndex, connector);
								printSetInputCode(shape2, queueName, connector);
								
							}
							else{
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
	
								printSetOutputCode(shape1, queueName, connector);
								printSetInputCode(shape2, queueName, connector);
							}
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
		printEndQuote();
		_printWriter.println("\tpublic void start(){");
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

	public void printSetInputCode(DiagramElement e, DiagramElement arg, DiagramConnector dc)
	{
			String object = findVariableName(e.getId());
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t"+object+".setInput("+parameter+");");
			_printWriter.println("\t\t_animationLine = new AnimationLine();");
			_printWriter.println("\t\t_animationLine.setSourcePane(_sourcePane);");
	        _printWriter.println("\t\t_animationLine.setTargetPane(_targetPane);");
	        _printWriter.println("\t\t_vPoint = new Vector();");
	        Vector _points = dc.getPoints();
	        Point p;
	        for(int i =0;i<_points.size();i++) {
	        	p = (Point)_points.elementAt(i);
	        	_printWriter.println("\t\t_point = new Point(" + p.x+ ","+p.y+");");
	        	_printWriter.println("\t\t_vPoint.add(_point);");
	        }
	        _printWriter.println("\t\t_animationLine.setPath(_vPoint);");
	        _printWriter.println("\t\t_animationLine.setSpeed(_speed);");
			_printWriter.println("\t\t"+parameter+".setSendAnimationLine(_animationLine);");
	}

	public void printSetOutputCode(DiagramElement e, DiagramElement arg, DiagramConnector dc)
	{
			String object = findVariableName(e.getId());
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t"+object+".setOutput("+parameter+");");
			_printWriter.println("\t\t_animationLine = new AnimationLine();");
			_printWriter.println("\t\t_animationLine.setSourcePane(_sourcePane);");
	        _printWriter.println("\t\t_animationLine.setTargetPane(_targetPane);");
	        _printWriter.println("\t\t_vPoint = new Vector();");
	        Vector _points = dc.getPoints();
	        Point p;
	        for(int i =0;i<_points.size();i++) {
	        	p = (Point)_points.elementAt(i);
	        	_printWriter.println("\t\t_point = new Point(" + p.x+ ","+p.y+");");
	        	_printWriter.println("\t\t_vPoint.add(_point);");
	        }
	        _printWriter.println("\t\t_animationLine.setPath(_vPoint);");
	        _printWriter.println("\t\t_animationLine.setSpeed(_speed);");
			_printWriter.println("\t\t"+parameter+".setReceiveAnimationLine(_animationLine);");			
	}
	
	
	public void printSetInputCode(DiagramElement e, String parameter, DiagramConnector dc)
	{
			String object = findVariableName(e.getId());
			//String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t"+object+".setInput("+parameter+");");
			_printWriter.println("\t\t_animationLine = new AnimationLine();");
			_printWriter.println("\t\t_animationLine.setSourcePane(_sourcePane);");
	        _printWriter.println("\t\t_animationLine.setTargetPane(_targetPane);");
	        _printWriter.println("\t\t_vPoint = new Vector();");

//aware start and stop at the same point!!
//	        Vector _points = dc.getPoints();
//	        Point p;
//	        for(int i =0;i<_points.size();i++) {
//	        	p = (Point)_points.elementAt(i);
//	        	_printWriter.println("_point = new Point(" + p.x+ ","+p.y+");");
//	        	_printWriter.println("_vPoint.add(_point);");
//	        }
	        _printWriter.println("\t\t_animationLine.setPath(_vPoint);");
	        _printWriter.println("\t\t_animationLine.setSpeed(_speed);");
			_printWriter.println("\t\t"+parameter+".setSendAnimationLine(_animationLine);");				
	}

	public void printSetOutputCode(DiagramElement e, String parameter, DiagramConnector dc)
	{
			String object = findVariableName(e.getId());
			//String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t"+object+".setOutput("+parameter+");");
			_printWriter.println("\t\t_animationLine = new AnimationLine();");
			_printWriter.println("\t\t_animationLine.setSourcePane(_sourcePane);");
	        _printWriter.println("\t\t_animationLine.setTargetPane(_targetPane);");
	        _printWriter.println("\t\t_vPoint = new Vector();");
	        Vector _points = dc.getPoints();
	        Point p;
	        for(int i =0;i<_points.size();i++) {
	        	p = (Point)_points.elementAt(i);
	        	_printWriter.println("\t\t_point = new Point(" + p.x+ ","+p.y+");");
	        	_printWriter.println("\t\t_vPoint.add(_point);");
	        }
	        _printWriter.println("\t\t_animationLine.setPath(_vPoint);");
	        _printWriter.println("\t\t_animationLine.setSpeed(_speed);");
			_printWriter.println("\t\t"+parameter+".setReceiveAnimationLine(_animationLine);");				
	}

	public void printSetInputFromExitCode(DiagramElement e, DiagramElement arg)
	{
			String object = findVariableName(e.getId());
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t"+object+".setInput("+parameter+".getExit());");
	}
	
//added by matthew, in order to print correct splitter code with animation
//add this method, add connector in the argument.
	public void printSetSplitterOutputCode(DiagramElement e, String parameter, String portIndex, DiagramConnector dc)
	{
			printSetSplitterOutputCode(e,parameter,portIndex);
			_printWriter.println("\t\t_animationLine = new AnimationLine();");
			_printWriter.println("\t\t_animationLine.setSourcePane(_sourcePane);");
	        _printWriter.println("\t\t_animationLine.setTargetPane(_targetPane);");
	        _printWriter.println("\t\t_vPoint = new Vector();");
	        Vector _points = dc.getPoints();
	        Point p;
	        for(int i =0;i<_points.size();i++) {
	        	p = (Point)_points.elementAt(i);
	        	_printWriter.println("\t\t_point = new Point(" + p.x+ ","+p.y+");");
	        	_printWriter.println("\t\t_vPoint.add(_point);");
	        }
	        _printWriter.println("\t\t_animationLine.setPath(_vPoint);");
	        _printWriter.println("\t\t_animationLine.setSpeed(_speed);");
			_printWriter.println("\t\t"+parameter+".setReceiveAnimationLine(_animationLine);");				
			
	}

	public void printSetSplitterOutputCode(DiagramElement e, DiagramElement arg, String portIndex, DiagramConnector dc)
	{
			this.printSetSplitterOutputCode(e,arg,portIndex);
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t_animationLine = new AnimationLine();");
			_printWriter.println("\t\t_animationLine.setSourcePane(_sourcePane);");
	        _printWriter.println("\t\t_animationLine.setTargetPane(_targetPane);");
	        _printWriter.println("\t\t_vPoint = new Vector();");
	        Vector _points = dc.getPoints();
	        Point p;
	        for(int i =0;i<_points.size();i++) {
	        	p = (Point)_points.elementAt(i);
	        	_printWriter.println("\t\t_point = new Point(" + p.x+ ","+p.y+");");
	        	_printWriter.println("\t\t_vPoint.add(_point);");
	        }
	        _printWriter.println("\t\t_animationLine.setPath(_vPoint);");
	        _printWriter.println("\t\t_animationLine.setSpeed(_speed);");

			_printWriter.println("\t\t"+parameter+".setReceiveAnimationLine(_animationLine);");				
			
	}

	public void printSetSplitterOutputCode(DiagramElement e, DiagramElement arg, String portIndex)
	{
			String object = findVariableName(e.getId());
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t"+object+".setOutput("+portIndex+", "+parameter+");");
	}

	public void printSetSplitterOutputCode(DiagramElement e, String arg, String portIndex)
	{
			String object = findVariableName(e.getId());
			_printWriter.println("\t\t"+object+".setOutput("+portIndex+", "+arg+");");
	}

//added by matthew, in order to print correct splitter code with animation
//add this method, add connector in the argument.
	public void printSetMergerInputCode(DiagramElement e, String parameter, String portIndex, DiagramConnector dc)
	{
			printSetMergerInputCode(e,parameter,portIndex);
			_printWriter.println("\t\t_animationLine = new AnimationLine();");
			_printWriter.println("\t\t_animationLine.setSourcePane(_sourcePane);");
	        _printWriter.println("\t\t_animationLine.setTargetPane(_targetPane);");
	        _printWriter.println("\t\t_vPoint = new Vector();");
//	        Vector _points = dc.getPoints();
//	        Point p;
//	        for(int i =0;i<_points.size();i++) {
//	        	p = (Point)_points.elementAt(i);
//	        	_printWriter.println("\t\t_point = new Point(" + p.x+ ","+p.y+");");
//	        	_printWriter.println("\t\t_vPoint.add(_point);");
//	        }
	        _printWriter.println("\t\t_animationLine.setPath(_vPoint);");
	        _printWriter.println("\t\t_animationLine.setSpeed(_speed);");
			_printWriter.println("\t\t"+parameter+".setSendAnimationLine(_animationLine);");				
			
	}
		
	public void printSetMergerInputCode(DiagramElement e, DiagramElement arg, String portIndex, DiagramConnector dc)
	{
			printSetMergerInputCode(e,arg,portIndex);
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t_animationLine = new AnimationLine();");
			_printWriter.println("\t\t_animationLine.setSourcePane(_sourcePane);");
	        _printWriter.println("\t\t_animationLine.setTargetPane(_targetPane);");
	        _printWriter.println("\t\t_vPoint = new Vector();");
	        Vector _points = dc.getPoints();
	        Point p;
	        for(int i =0;i<_points.size();i++) {
	        	p = (Point)_points.elementAt(i);
	        	_printWriter.println("\t\t_point = new Point(" + p.x+ ","+p.y+");");
	        	_printWriter.println("\t\t_vPoint.add(_point);");
	        }
	        _printWriter.println("\t\t_animationLine.setPath(_vPoint);");
	        _printWriter.println("\t\t_animationLine.setSpeed(_speed);");
			_printWriter.println("\t\t"+parameter+".setSendAnimationLine(_animationLine);");				
			
	}

	public void printSetMergerInputCode(DiagramElement e, DiagramElement arg, String portIndex)
	{
			String object = findVariableName(e.getId());
			String parameter = findVariableName(arg.getId());
			_printWriter.println("\t\t"+object+".setInput("+portIndex+", "+parameter+");");
	}
  
	public void printSetMergerInputCode(DiagramElement e, String parameter, String portIndex)
	{
			String object = findVariableName(e.getId());
			_printWriter.println("\t\t"+object+".setInput("+portIndex+", "+parameter+");");
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
		_printWriter.println("\t\t"+object+".addStartPoint("+parameter+", \""+type+"\");");
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
		_printWriter.println("\t\t"+object+".addEndPoint("+parameter+", \""+type+"\");");
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
							_printWriter.println("\t\t"+findVariableName(s.getId())+".setStartTime("+startTime+");");
						}
						else if(((Property)properties.elementAt(a)).getName().equalsIgnoreCase("analyze time")) {
							analyzeTime = String.valueOf(((Property)properties.elementAt(a)).getValue());
							_printWriter.println("\t\t"+findVariableName(s.getId())+".setAnalyzeTime("+analyzeTime+");");
						}
					}
					
				}
			}
		}
	}
	
	public void printDistributionCode(DiagramElement e) {
		
		InternalCodeGenerator _cGenerator = new InternalCodeGenerator();
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
		_setInfo.add("\t\t"+object+".setDistribution("+distributionVar+");\n");
		//_printWriter.println("\t"+object+".setDistribution("+distributionVar+")");


	}		
	public void printObjectCreatorCode(DiagramElement e) {
		
		InternalCodeGenerator _cGenerator = new InternalCodeGenerator();
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
		_setInfo.add("\t\t"+object+".setObjectCreator("+objectCreatorVar+");\n");
		//_printWriter.println("\t"+object+".setDistribution("+distributionVar+")");


	}	
	public void printTransformCode(DiagramElement e) {
		
		InternalCodeGenerator _cGenerator = new InternalCodeGenerator();
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
		_setInfo.add("\t\t"+object+".setTransform("+transformVar+");\n");
		//_printWriter.println("\t"+object+".setDistribution("+distributionVar+")");


	}	
	
	public String getAnimationCode(DiagramShape e) {
		
		PropertiesTableReader _pReader = new PropertiesTableReader();
		PropertiesSetting _pSetting = new PropertiesSetting();
		Vector _ps = e.getProperties();
		Object animationObj = null;
		for(int i=0; i<_ps.size(); i++) {
			Property _p = (Property)_ps.elementAt(i);
			if(_p.getName().equalsIgnoreCase("Animation")) {
				animationObj = _p.getValue();
			}
		}
		if(animationObj!=null)
			return animationObj.getClass().getName();
		else
			return "animation.Animation";
	}	
		
	public void printSplitterModelCode(DiagramElement e) {
		String object = findVariableName(e.getId());
		_setInfo.add("\t\t"+object+".setSplitterModel(new DefaultSplitterModel2());\n");
	}
	
	public void printMergerModelCode(DiagramElement e) {
		String object = findVariableName(e.getId());
		_setInfo.add("\t\t"+object+".setMergerModel(new DefaultMergerModel2());\n");
	}
			
	public void printComponentStartCode(DiagramElement e) {
		String object = findVariableName(e.getId());
		_printWriter.println("\t\t"+object+".start();");
	}
	
	public void printComponentDestoryCode(DiagramElement e) {
		String object = findVariableName(e.getId());
		_printWriter.println("\t\t"+object+".destroy();");
	}

	public void printProcessAddTerminationEvent() {
		_printWriter.println("\t\tprocessAddTerminationEvent();");
	}	
}