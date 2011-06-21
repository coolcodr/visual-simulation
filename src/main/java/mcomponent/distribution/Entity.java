package mcomponent.distribution;

public class Entity {
	
	boolean _lock = false;
	Object _object;
	String _animationName;
	
	public Entity(Object object) {
		this(object,"animation.Animation");
	}
	
	public Entity(Object object, String animationName) {
		_object = object;
		_animationName = animationName;
	}
	
	public synchronized void lock() {
		if (!_lock) {
			_lock = true;
		}
		else {
			while (_lock) {
				try {
					wait();
				}
				catch(InterruptedException e) {}
			}
			_lock = true;
		}
		
	}
	
	public synchronized void unlock() {
		_lock = false;
		notify();
	}
	
	public Object getObject() {
		return _object;
	}
	
	public void setObject(Object object) {
		_object = object;
	}	
	
	public String getAnimationName() {
		return _animationName;
	}
	
	public void setAnimationName(String animationName) {
		_animationName = animationName;
	}	
}