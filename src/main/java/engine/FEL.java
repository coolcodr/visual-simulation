package engine;

public class FEL
{
	
	private Node _root;
	private double _previousTime;
	static private long _timeUnit;
	static private double _ratio;
	
	public FEL()
	{
		_root = null;
		_timeUnit = 0;
		_ratio = 1.0;
		_previousTime = 0.0;
	}
	
	public boolean isEmpty()
	{
		return( _root == null);
	}
	
	public void enqueue(Event _event)
	{
		Node _node = new Node( _event);
		
		if (_root == null)
		{
			_root = _node;
		}
		else
		{
			insertNode( _node, _root);
		}
	}
	
	public void insertNode(Node _data, Node _parent)
	{
		if (_data.getData().getTime() < _parent.getData().getTime())
		{
			if ( _parent.getLeft() != null)
			{
				insertNode( _data, _parent.getLeft());
			}
			else
				_parent.setLeft( _data);
		}
		else
		{
			if ( _parent.getRight() != null)
			{
				insertNode( _data, _parent.getRight());
			}
			else
				_parent.setRight( _data);
		}
	}
	
	public Node searchMinium()
	{
		return searchMin( _root);
	}
	
	public Node searchMin(Node _node)
	{
		if (_node == null)
			return null;
		else if ( _node.getLeft() == null)
			return _node;
		else
			return searchMin(_node.getLeft());
	}
	
	public Event dequeue()
	{
		Node _leftMost = searchMinium();		
		
		if (isEmpty())
			return null;
		else
		{
			if ( _leftMost == _root)
				_root = _root.getRight();
			else
			{
				Node _leftMostParent = searchLeftMostParent( _leftMost);
				_leftMostParent.setLeft( _leftMost.getRight());
			}
//modified by matthew
//simulation scale feature			
			try {
//				System.err.println("Event time: " + _leftMost.getData().getTime() + " Previous Time:" + _previousTime);
				System.err.println("Current time: " + SimThread.getSimSystemData().getCurrentTime());
				if(_ratio<=0||_timeUnit<1){
//					System.out.println("Simulation scale out of limit, scale function off");
				}
				else {
					if((_leftMost.getData().getTime() - _previousTime) == 0.0) {}
					else {
						Thread.sleep(Math.round(_ratio*((_leftMost.getData().getTime()-_previousTime)*_timeUnit)));
					}
					_previousTime = _leftMost.getData().getTime();
				}
			}
			catch(InterruptedException e) {}
			return _leftMost.getData();
		}
	}
	
	public Node searchLeftMostParent(Node _leftMost)
	{
		return searchLMP(_root,  _leftMost);
	}
	
	public Node searchLMP(Node _node, Node _leftMost)
	{
		if ( _node.getLeft() != _leftMost)
			return searchLMP( _node.getLeft(), _leftMost);
		
		return _node;
	}
		
	public Event searchEvent(Object _source, Object _object)
	{
		return searchEvent2(_source, _object, _root).getData();
	}
	
	public Node searchEvent2(Object _source, Object _object, Node _node)
	{
		if ((_node.getData().getSource() == _source) && (_node.getData().getObject() == _object))
		{
			return _node;
		}
		else
		{
			Node _temp = null;
			
			if (_node.getLeft() != null)
			{
				_temp = searchEvent2(_source, _object, _node.getLeft());
			}
			
			if (_temp != null)
			{
				return _temp;
			}
			
			if (_node.getRight() != null)
			{
				_temp = searchEvent2(_source, _object, _node.getRight());
			}
			if (_temp != null)
			{
				return _temp;
			}
			
			return null;
		}
	}
	
	public void display()
	{
		display(-1);
	}
	
	public void display(double _currentTime)
	{
		System.out.println();
		System.out.println("****** FEL *****************");
		order(_root, _currentTime);
		System.out.println("****************************");
		System.out.println();
	}
	
	public void order(Node _node, double _currentTime)
	{
		if (_node != null)
		{
			System.out.println(_node.getData().getSource() + "	,	" + _node.getData().getObject() + "	,	" + _currentTime + "	,	" + _node.getData().getTime());
			order(_node.getLeft(), _currentTime);
			order(_node.getRight(), _currentTime);
		}
	}
	
	public static void setTimeUnit(long _timeUnit) {
		FEL._timeUnit = _timeUnit;
	}
	
	public static long getTimeUnit() {
		return _timeUnit;
	}
	
	public static void setRatio(double _ratio) {
		FEL._ratio = _ratio;
	}	
	
	public static double getRatio() {
		return _ratio;
	}	
}