package light;

import javax.swing.UIDefaults;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class LightLookAndFeel extends MetalLookAndFeel {
    /**
     * 
     */
    private static final long serialVersionUID = -9164220975693107707L;

    public String getID() {
        return "Light";
    }

    public String getName() {
        return "Light Look and Feel";
    }

    public String getDescription() {
        return "The Light Look and Feel";
    }

    public boolean isNativeLookAndFeel() {
        return false;
    }

    public boolean isSupportedLookAndFeel() {
        return true;
    }

    protected void initClassDefaults(UIDefaults table) {
        super.initClassDefaults(table);
        table.put("ButtonUI", "light.LightButtonUI");
        table.put("ToolBarUI", "light.LightToolBarUI");
        table.put("MenuBarUI", "light.LightMenuBarUI");
        table.put("MenuUI", "light.LightMenuUI");
        // table.put("LabelUI", "light.LightLabelUI");
        table.put("ToolBarSeparatorUI", "light.LightToolBarSeparatorUI");
        table.put("SliderUI", "light.LightSliderUI");
        table.put("SplitPaneUI", "light.LightSplitPaneUI");
        table.put("ComboBoxUI", "light.LightComboBoxUI");
        table.put("TextFieldUI", "light.LightTextFieldUI");
        table.put("RootPaneUI", "light.LightRootPaneUI");
        table.put("MenuItemUI", "light.LightMenuItemUI");
        table.put("CheckBoxMenuItemUI", "light.LightCheckBoxMenuItemUI");
        table.put("ToggleButtonUI", "light.LightToggleButtonUI");
        table.put("TabbedPaneUI", "light.LightTabbedPaneUI");
        table.put("ScrollBarUI", "light.LightScrollBarUI");
    }

    protected void initSystemColorDefaults(UIDefaults table) {
        super.setCurrentTheme(new DefaultLightTheme());
        super.initComponentDefaults(table);
        super.initSystemColorDefaults(table);

    }
    /*
     * protected void initComponentDefaults(UIDefaults uidefaults) {
     * super.initComponentDefaults(uidefaults); } public static FontUIResource
     * getControlTextFont() { System.out.println("Test"); return new
     * FontUIResource("Verdana", Font.ITALIC, 18); }
     * 
     * public static FontUIResource getSystemTextFont() {
     * System.out.println("Test"); return new FontUIResource("Verdana",
     * Font.ITALIC, 18); }
     * 
     * public static FontUIResource getUserTextFont() {
     * System.out.println("Test"); return new FontUIResource("Verdana",
     * Font.ITALIC, 18); }
     * 
     * public static FontUIResource getMenuTextFont() {
     * System.out.println("Test"); return new FontUIResource("Verdana",
     * Font.ITALIC, 18); }
     * 
     * public static FontUIResource getWindowTitleFont() {
     * System.out.println("Test"); return new FontUIResource("Verdana",
     * Font.ITALIC, 18); }
     * 
     * public static FontUIResource getSubTextFont() {
     * System.out.println("Test"); return new FontUIResource("Verdana",
     * Font.ITALIC, 18); }
     * 
     * /* } ColorUIResource uir = new ColorUIResource(0, 0, 0); ColorUIResource
     * uir2 = new ColorUIResource(0, 0, 255);
     * 
     * Object[] systemColors = { "desktop", uir, /* Color of the desktop
     * background "activeCaption", uir, /* Color for captions (title bars) when
     * they are active. "activeCaptionText", uir, /* Text color for text in
     * captions (title bars). "activeCaptionBorder", uir, /* Border color for
     * caption (title bar) window borders. "inactiveCaption", uir, /* Color for
     * captions (title bars) when not active. "inactiveCaptionText", uir, /*
     * Text color for text in inactive captions (title bars).
     * "inactiveCaptionBorder", uir, /* Border color for inactive caption (title
     * bar) window borders. * "window", uir, /* Default color for the interior
     * of windows * "windowBorder", uir, /* ??? * "windowText", uir, /* ??? *
     * "menu", uir2, /* Background color for menus * "menuText", uir, /* Text
     * color for menus * "text", uir, /* Text background color * "textText",
     * uir, /* Text foreground color * "textHighlight", uir, /* Text background
     * color when selected * "textHighlightText", uir, /* Text color when
     * selected * "textInactiveText", uir, /* Text color when disabled *
     * "control", uir2, /* Default color for controls (buttons, sliders, etc) *
     * "controlText", uir, /* Default color for text in controls *
     * "controlHighlight", uir, /* Specular highlight (opposite of the shadow) *
     * "controlLtHighlight", uir, /* Highlight color for controls *
     * "controlShadow", uir, /* Shadow color for controls * "controlDkShadow",
     * uir, /* Dark shadow color for controls * "scrollbar", uir, /* Scrollbar
     * background (usually the "track") * "info", uir, /* ToolTip Background *
     * "infoText", uir /* ToolTip Text * };
     * 
     * for(int i = 0; i < systemColors.length; i += 2) {
     * table.put((String)systemColors[i], systemColors[i + 1]); } }
     */
}
