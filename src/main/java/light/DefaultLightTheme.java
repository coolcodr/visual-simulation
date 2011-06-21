package light;

import java.awt.Font;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class DefaultLightTheme extends DefaultMetalTheme {
    public DefaultLightTheme() {
        super();
    }

    private static final ColorUIResource primary1 = new ColorUIResource(144, 127, 172);
    private static final ColorUIResource primary2 = new ColorUIResource(205, 197, 218);
    private static final ColorUIResource primary3 = new ColorUIResource(224, 212, 245);

    private static final ColorUIResource secondary1 = new ColorUIResource(75, 75, 94);
    private static final ColorUIResource secondary2 = new ColorUIResource(185, 185, 198);
    private static final ColorUIResource secondary3 = new ColorUIResource(241, 241, 244);

    protected ColorUIResource getPrimary1() {
        return primary1;
    }

    protected ColorUIResource getPrimary2() {
        return primary2;
    }

    protected ColorUIResource getPrimary3() {
        return primary3;
    }

    protected ColorUIResource getSecondary1() {
        return secondary1;
    }

    protected ColorUIResource getSecondary2() {
        return secondary2;
    }

    protected ColorUIResource getSecondary3() {
        return secondary3;
    }

    public FontUIResource getControlTextFont() {
        return new FontUIResource("Dialog", Font.PLAIN, 12);
    }

    public FontUIResource getSystemTextFont() {
        return new FontUIResource("Dialog", Font.PLAIN, 12);
    }

    public FontUIResource getUserTextFont() {
        return new FontUIResource("Dialog", Font.PLAIN, 12);
    }

    public FontUIResource getMenuTextFont() {
        return new FontUIResource("Dialog", Font.PLAIN, 12);
    }

    public FontUIResource getWindowTitleFont() {
        return new FontUIResource("Dialog", Font.PLAIN, 12);
    }

    public FontUIResource getSubTextFont() {
        return new FontUIResource("Dialog", Font.PLAIN, 12);
    }

}
