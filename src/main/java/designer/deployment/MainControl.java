package designer.deployment;

import statistic.*;
import mcomponent.distribution.*;
import engine.eventhandler.*;
import mcomponent.*;
import mcomponent.queue.*;
import engine.*;
import chart.*;

import java.util.Hashtable;

public class MainControl implements TerminationEventHandler, MainControlHandler
{
    Source _Source1 = new Source( "Door 1" );
    Source _Source2 = new Source( "Door 2" );
    Merger _Merger1 = new Merger( "Choose Queue", 2);
    Splitter _Splitter1 = new Splitter( "Choose Queue", 2);
    Server _Server1 = new Server( "Cashier 1" );
    Server _Server2 = new Server( "Cashier 2" );
    Merger _Merger2 = new Merger( "Merger", 2);
    Splitter _Splitter2 = new Splitter( "Splitter", 2);
    Server _Server3 = new Server( "Server" );
    Server _Server4 = new Server( "Server" );
    Merger _Merger3 = new Merger( "Merger", 2);
    Sink _Sink1 = new Sink( "Exit" );
    NServer _NServer1 = new NServer( "Tables", 2 );
    AnalysisTool2 _AnalysisTool21 = new AnalysisTool2( "Analysis Tool 1" );
    Chart _Chart1 = new Chart( "Chart" );
    AnalysisTool2 _AnalysisTool22 = new AnalysisTool2( "Analysis Tool 2" );
    Chart _Chart2 = new Chart( "Chart2" );
    ObjectCreator _objectCreator1 = new CustomerCreator();
    Distribution _distribution1 = new DoorDistribution();
    ObjectCreator _objectCreator2 = new CustomerCreator();
    Distribution _distribution2 = new DoorDistribution();
    MergerModel _mergerModel1 = new EnterQueueMergerModel();
    SplitterModel _splitterModel1 = new ChoosingQueueSplitterModel();
    Distribution _distribution3 = new UniformDistribution();
    Transform _transform1 = new CashierTransform();
    Distribution _distribution4 = new UniformDistribution();
    Transform _transform2 = new CashierTransform();
    MergerModel _mergerModel2 = new EnterQueueMergerModel();
    SplitterModel _splitterModel2 = new DefaultSplitterModel2();
    Distribution _distribution5 = new UniformDistribution();
    Transform _transform3 = new DefaultTransform();
    Distribution _distribution6 = new UniformDistribution();
    Transform _transform4 = new DefaultTransform();
    MergerModel _mergerModel3 = new DefaultMergerModel2();
    Distribution _distribution7 = new UniformDistribution();
    Transform _transform5 = new DefaultTransform();
    BarChart _presentation1 = new BarChart();
    UtilizationProcessor _dataProcessor1 = new UtilizationProcessor();
    BarChart _presentation2 = new BarChart();
    UtilizationProcessor _dataProcessor2 = new UtilizationProcessor();

    DeployObjectFactory deployObjectFactory;
    Hashtable dataTable;

    public MainControl( DeployObjectFactory deployObjectFactory )
    {
        this.deployObjectFactory = deployObjectFactory;
    }

    public void init() throws InvalidDataException
    {

                this._Chart1.setPresentation(this._presentation1);
                this._Chart1.setDataProcessor(this._dataProcessor1);
                this._Chart2.setPresentation(this._presentation2);
                this._Chart2.setDataProcessor(this._dataProcessor2);

        this.overrideData();

     MessageQueueFIFO virtualMessageQ1 = new MessageQueueFIFO("virtual queue");
        this._Source2.setOutput(virtualMessageQ1);
        _Merger1.setInput(1, virtualMessageQ1);
     MessageQueueFIFO virtualMessageQ2 = new MessageQueueFIFO("virtual queue");
        this._Source1.setOutput(virtualMessageQ2);
        _Merger1.setInput(0, virtualMessageQ2);
     MessageQueueFIFO virtualMessageQ3 = new MessageQueueFIFO("virtual queue");
        this._Merger1.setOutput(virtualMessageQ3);
        _Splitter1.setInput(virtualMessageQ3);
     MessageQueueFIFO virtualMessageQ4 = new MessageQueueFIFO("virtual queue");
        _Splitter1.setOutput(1, virtualMessageQ4);
        _Server1.setInput(virtualMessageQ4);
     MessageQueueFIFO virtualMessageQ5 = new MessageQueueFIFO("virtual queue");
        _Splitter1.setOutput(0, virtualMessageQ5);
        _Server2.setInput(virtualMessageQ5);
     MessageQueueFIFO virtualMessageQ6 = new MessageQueueFIFO("virtual queue");
        this._Server2.setOutput(virtualMessageQ6);
        _Merger2.setInput(0, virtualMessageQ6);
     MessageQueueFIFO virtualMessageQ7 = new MessageQueueFIFO("virtual queue");
        this._Server1.setOutput(virtualMessageQ7);
        _Merger2.setInput(1, virtualMessageQ7);
     MessageQueueFIFO virtualMessageQ8 = new MessageQueueFIFO("virtual queue");
        this._Merger2.setOutput(virtualMessageQ8);
        _Splitter2.setInput(virtualMessageQ8);
     MessageQueueFIFO virtualMessageQ9 = new MessageQueueFIFO("virtual queue");
        _Splitter2.setOutput(1, virtualMessageQ9);
        _Server3.setInput(virtualMessageQ9);
     MessageQueueFIFO virtualMessageQ10 = new MessageQueueFIFO("virtual queue");
        _Splitter2.setOutput(0, virtualMessageQ10);
        _Server4.setInput(virtualMessageQ10);
     MessageQueueFIFO virtualMessageQ11 = new MessageQueueFIFO("virtual queue");
        this._Server3.setOutput(virtualMessageQ11);
        _Merger3.setInput(0, virtualMessageQ11);
     MessageQueueFIFO virtualMessageQ12 = new MessageQueueFIFO("virtual queue");
        this._Server4.setOutput(virtualMessageQ12);
        _Merger3.setInput(1, virtualMessageQ12);
     MessageQueueFIFO virtualMessageQ13 = new MessageQueueFIFO("virtual queue");
        this._Merger3.setOutput(virtualMessageQ13);
        _NServer1.setInput(virtualMessageQ13);
     MessageQueueFIFO virtualMessageQ14 = new MessageQueueFIFO("virtual queue");
        this._NServer1.setOutput(virtualMessageQ14);
        _Sink1.setInput(virtualMessageQ14);
        this._AnalysisTool21.addStartPoint(this._Server1, "Input");
        this._AnalysisTool21.addEndPoint(this._Server1, "Output");
        this._Chart1.setDataSource(this._AnalysisTool21);
        this._AnalysisTool22.addStartPoint(this._Server2, "Input");
        this._AnalysisTool22.addEndPoint(this._Server2, "Output");
        this._Chart2.setDataSource(this._AnalysisTool22);

        this._Source1.setObjectCreator(this._objectCreator1);
        this._Source1.setDistribution(this._distribution1);
        this._Source2.setObjectCreator(this._objectCreator2);
        this._Source2.setDistribution(this._distribution2);
        this._Merger1.setMergerModel(this._mergerModel1);
        this._Splitter1.setSplitterModel(this._splitterModel1);
        this._Server1.setDistribution(this._distribution3);
        this._Server1.setTransform(this._transform1);
        this._Server2.setDistribution(this._distribution4);
        this._Server2.setTransform(this._transform2);
        this._Merger2.setMergerModel(this._mergerModel2);
        this._Splitter2.setSplitterModel(this._splitterModel2);
        this._Server3.setDistribution(this._distribution5);
        this._Server3.setTransform(this._transform3);
        this._Server4.setDistribution(this._distribution6);
        this._Server4.setTransform(this._transform4);
        this._Merger3.setMergerModel(this._mergerModel3);
        this._NServer1.setDistribution(this._distribution7);
        this._NServer1.setTransform(this._transform5);

        this._AnalysisTool21.setStartTime(0);
        this._AnalysisTool21.setAnalyzeTime(-1);
        this._AnalysisTool22.setStartTime(0);
        this._AnalysisTool22.setAnalyzeTime(-1);
    }

    public void overrideData() throws InvalidDataException
    {
        dataTable = deployObjectFactory.initTable();

        _objectCreator1 = ( ObjectCreator ) dataTable.get ( "_Source1|Object Creator" );
        _distribution1 = ( Distribution ) dataTable.get ( "_Source1|Distribution" );
        _objectCreator2 = ( ObjectCreator ) dataTable.get ( "_Source2|Object Creator" );
        _distribution2 = ( Distribution ) dataTable.get ( "_Source2|Distribution" );
        _mergerModel1 = ( MergerModel ) dataTable.get ( "_Merger1|MergerModel" );
        _splitterModel1 = ( SplitterModel ) dataTable.get ( "_Splitter1|SplitterModel" );
        _distribution3 = ( Distribution ) dataTable.get ( "_Server1|Distribution" );
        _transform1 = ( Transform ) dataTable.get ( "_Server1|Transform" );
        _distribution4 = ( Distribution ) dataTable.get ( "_Server2|Distribution" );
        _transform2 = ( Transform ) dataTable.get ( "_Server2|Transform" );
        _mergerModel2 = ( MergerModel ) dataTable.get ( "_Merger2|MergerModel" );
        _splitterModel2 = ( SplitterModel ) dataTable.get ( "_Splitter2|SplitterModel" );
        _distribution5 = ( Distribution ) dataTable.get ( "_Server3|Distribution" );
        _transform3 = ( Transform ) dataTable.get ( "_Server3|Transform" );
        _distribution6 = ( Distribution ) dataTable.get ( "_Server4|Distribution" );
        _transform4 = ( Transform ) dataTable.get ( "_Server4|Transform" );
        _mergerModel3 = ( MergerModel ) dataTable.get ( "_Merger3|MergerModel" );
        _distribution7 = ( Distribution ) dataTable.get ( "_NServer1|Distribution" );
        _transform5 = ( Transform ) dataTable.get ( "_NServer1|Transform" );
        _presentation1 = ( BarChart ) dataTable.get ( "_Chart1|Presentation" );
        _dataProcessor1 = ( UtilizationProcessor ) dataTable.get ( "_Chart1|DataProcessor" );
        _presentation2 = ( BarChart ) dataTable.get ( "_Chart2|Presentation" );
        _dataProcessor2 = ( UtilizationProcessor ) dataTable.get ( "_Chart2|DataProcessor" );
    }

    public void processAddTerminationEvent()
    {
        int i = ((Integer)dataTable.get("_simulationTime1|Simulation time(s)")).intValue();
        SimThread.getSimSystemData().addEvent(new engine.Event( i, this, "Termination", null));
    }

    public void processTerminationEvent(engine.Event _event)
    {
        Hashtable analysisTable = new Hashtable();
        Hashtable chartsTable = new Hashtable();
        analysisTable.put("_AnalysisTool21", _AnalysisTool21);
        _Chart1.display();
        chartsTable.put("_Chart1", _Chart1);
        analysisTable.put("_AnalysisTool22", _AnalysisTool22);
        _Chart2.display();
        chartsTable.put("_Chart2", _Chart2);
        deployObjectFactory.showReports(analysisTable, chartsTable);
    }

    public void startRun() throws InvalidDataException
    {
        this.init();
        this.processAddTerminationEvent();
        this._Source1.start();
        this._Source2.start();
        this._Merger1.start();
        this._Splitter1.start();
        this._Server1.start();
        this._Server2.start();
        this._Merger2.start();
        this._Splitter2.start();
        this._Server3.start();
        this._Server4.start();
        this._Merger3.start();
        this._Sink1.start();
        this._NServer1.start();
    }

}
