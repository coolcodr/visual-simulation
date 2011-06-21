package mcomponent.distribution;

import statistic.*;
import mcomponent.distribution.*;
import engine.eventhandler.*;
import mcomponent.*;
import mcomponent.queue.*;
import engine.*;
import java.util.*;

public class DefaultSplitterModel2 extends SplitterModel
{
	int num =0;
	public void start()
	{
		int num = (int)Math.round(Math.random()*10)+1;
		
		if(num%2==0) {
			setOutputNo(0);
		}
		else {
			setOutputNo(1);
		}
		
		//setOutputNo(0);
	}
}