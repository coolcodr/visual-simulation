package animation;

public class AnimationTransform{
	
	String _fromAnimation;
	String _toAnimation;
	
	public AnimationTransform(String fromAnimation, String toAnimation) {
		_fromAnimation = fromAnimation;
		_toAnimation = toAnimation;
	}
	
	public String getFromAnimation() {
		return _fromAnimation;
	}
	
	public String getToAnimation() {
		return _toAnimation;
	}
	
	public void setFromAnimation(String fromAnimation) {
		_fromAnimation=fromAnimation;
	}
	
	public void setToAnimation(String toAnimation) {
		_toAnimation = toAnimation;
	}	
}