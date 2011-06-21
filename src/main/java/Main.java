import statistic.*;
import mcomponent.distribution.*;
import engine.eventhandler.*;
import mcomponent.*;
import mcomponent.queue.*;
import engine.*;
import javax.swing.*;

class Main implements TerminationEventHandler
{
	Source _Source1 = new Source( "Source" );
	Splitter _Splitter1 = new Splitter( "Splitter", 2);
	MessageQueueFIFO _MessageQueueFIFO1 = new MessageQueueFIFO( "FIFO Queue 1" );
	MessageQueueFIFO _MessageQueueFIFO2 = new MessageQueueFIFO( "FIFO Queue 2" );
	MessageQueueFIFO _MessageQueueFIFO3 = new MessageQueueFIFO( "FIFO Queue 3" );
	Sink _Sink1 = new Sink( "Sink" );
	Sink _Sink2 = new Sink( "Sink" );
	DefaultCreator _objectCreator1 = new DefaultCreator();
	UniformDistribution _distribution1 = new UniformDistribution();

	public static void main(String[] args)
	{

		Main _main = new Main();
		_main._Source1.setOutput(_main._MessageQueueFIFO1);
		_main._Splitter1.setInput(_main._MessageQueueFIFO1);
		_main._Splitter1.setOutput(1, _main._MessageQueueFIFO2);
		_main._Splitter1.setOutput(0, _main._MessageQueueFIFO3);
		_main._Sink1.setInput(_main._MessageQueueFIFO2);
		_main._Sink2.setInput(_main._MessageQueueFIFO3);

		_main._Source1.setObjectCreator(_main._objectCreator1);
		_main._distribution1.setUpperLimit( 1.0 );
		_main._distribution1.setLowerLimit( 1.0 );
		_main._Source1.setDistribution(_main._distribution1);
		_main._Splitter1.setSplitterModel(new DefaultSplitterModel2());


		_main._Source1.start();
		_main._Splitter1.start();
		_main._Sink1.start();
		_main._Sink2.start();

		_main.processAddTerminationEvent();
	}

	public void processAddTerminationEvent() {

		SimThread.getSimSystemData().addEvent(new engine.Event(1000, this, "Termination", null));
	}

	public void processTerminationEvent(engine.Event _event) {

AnalysisTool2.displayAll();
	}

}
