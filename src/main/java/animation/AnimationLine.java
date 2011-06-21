package animation;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import mcomponent.distribution.*;

public class AnimationLine{
	
	static ThreadPool pool= new ThreadPool(3000);
	static boolean _animationStatus = false;
	JComponent sourcePane, targetPane;
	int speed;
    Vector points = new Vector();
    String animationClass;
    Animation a;
    Vector _animationTransforms;
    
	public AnimationLine(){
		points = new Vector();
		_animationTransforms = new Vector();
	}
	
	public void setPath(Vector points){	
		for(int i = 0; i< points.size();i++) {
			Point p = new Point();
			Point p2;
			p.x = ((Point)points.elementAt(i)).x;
			p.y = ((Point)points.elementAt(i)).y;
			p2 = SwingUtilities.convertPoint(sourcePane, p, targetPane);
			this.points.add(p2);
			
		}
		this.speed = speed;
	}
	
	public void setSourcePane(JComponent pane) {
		this.sourcePane = pane;
	}
	
	public void setTargetPane(JComponent pane) {
		this.targetPane = pane;
	}	
	
	public void setAnimation(String animationClass) {
		this.animationClass = animationClass;
	}
	
	public void start(Entity entity){
		
		Point start = new Point();
		Point end = new Point();
		if(!getStop()) {
			try {
//				if(_animationTransforms.size()>0) {
//					for(int i=0;i<_animationTransforms.size();i++) {
//						if(entity.getAnimationName().equals(((AnimationTransform)_animationTransforms.elementAt(i)).getFromAnimation())){
//							entity.setAnimationName(((AnimationTransform)_animationTransforms.elementAt(i)).getToAnimation());
//						}
//					}
//				}
				a = (Animation)(Class.forName(entity.getAnimationName()).newInstance());
				a.setEntity(entity);
				a.setSpeed(getSpeed());
				a.setPoints(points);
				a.setVisible(false);
				targetPane.add(a);
				targetPane.setVisible(true);
				pool.execute(a);
			}
			catch(Exception e) {};
		}
		else {
			targetPane.setVisible(false);
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getFinishTime() {
		Point start,end;
		Animation temp = new AnimationPicture();
		long sumDistance=0;
		for(int i = 0;i<points.size()-1;i++) {
			start = (Point)points.elementAt(i);
			end = (Point)points.elementAt(i+1);
			sumDistance = sumDistance + Math.round(start.distance(end));
			System.out.println("Distance" + sumDistance);
		}
		return (int)(sumDistance/this.getSpeed()*Animation.getREFRESHTIME());
	}
	
//added
	static boolean _stop=false;
	public static void setStop(boolean stop) {
		_stop=stop;
	}	
	
	public static boolean getStop() {
		return _stop;
	}
	
	public void setAnimationTransforms(Vector fromAnimation, Vector toAnimation){
		for(int i=0;i<fromAnimation.size();i++) {
			_animationTransforms.add(new AnimationTransform((String)fromAnimation.elementAt(i),(String)toAnimation.elementAt(i)));
		}
	}

	public static void setAnimationStatus(boolean animationStatus) {
		_animationStatus = animationStatus;
	}	
	
	public static boolean getAnimationStatus() {
		return _animationStatus;
	}
	
}