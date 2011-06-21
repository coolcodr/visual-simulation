package animation;

public interface AnimationQueueHandler {
    public void setSendAnimationLine(AnimationLine al);

    public void setReceiveAnimationLine(AnimationLine al);

    public void triggerSendAnimation(Object object);

    public void triggerReceiveAnimation(Object object);

}
