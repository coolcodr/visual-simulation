package mcomponent.distribution;

import statistic.*;
import mcomponent.distribution.*;
import engine.eventhandler.*;
import mcomponent.*;
import mcomponent.queue.*;
import engine.*;
import java.util.*;

public class DefaultMergerModel2 extends MergerModel
{	
	int num=0;
	public void start()
	{
//		int num = (int)Math.round(Math.random()*10)+1;
		num++;
		if(num==2)
			num=0;
		if(num%2==0) {
			setInputNo(0);
		}
		else {
			setInputNo(1);
		}
		
		//setOutputNo(0);
	}
}