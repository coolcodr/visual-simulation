package mcomponent.distribution;

public class CustomTransform extends Transform {

    public CustomTransform() {
    }

    public void start() {
        if (getObject() instanceof Entity) {
            if (((Entity) getObject()).getAnimationName().equals("animation.BlueRotatingRect")) {
                ((Entity) getObject()).setAnimationName("animation.CyanRotatingRect");
            } else if (((Entity) getObject()).getAnimationName().equals("animation.RotatingRect")) {
                ((Entity) getObject()).setAnimationName("animation.GreenRotatingRect");
            }
        }
    }
}
