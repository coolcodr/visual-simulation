package designer;

import javax.swing.JComponent;

import designer.deployment.SimPropertyChoice;

public class PropertiesFactory {

    public PropertiesFactory() {
    }

    private static void addBasicProperties(ElementProperties properties, JComponent jComponent) {
        properties.addProperty(new SetXCommand((DesignerComponent) jComponent));
        properties.addProperty(new SetYCommand((DesignerComponent) jComponent));
        properties.addProperty(new SetWidthCommand((DesignerComponent) jComponent));
        properties.addProperty(new SetHeightCommand((DesignerComponent) jComponent));
    }

    private static void addDisplayProperties(ElementProperties properties, JComponent jComponent) {
        properties.addProperty(new SetFontCommand((DesignerComponent) jComponent));
        properties.addProperty(new SetForegroundCommand((DesignerComponent) jComponent));
    }

    public static ElementProperties createDesignPaneProperties(JComponent jComponent) {
        ElementProperties properties = new ElementProperties(jComponent);
        // addBasicProperties(properties, jComponent);
        properties.addProperty(new PaneSetNameCommand((DesignPane) jComponent));
        properties.addProperty(new PaneSetTextCommand((DesignPane) jComponent));
        properties.addProperty(new PaneSetXCommand((DesignPane) jComponent));
        properties.addProperty(new PaneSetYCommand((DesignPane) jComponent));
        properties.addProperty(new PaneSetWidthCommand((DesignPane) jComponent));
        properties.addProperty(new PaneSetHeightCommand((DesignPane) jComponent));
        return properties;
    }

    public static ElementProperties createSetPaneProperties(JComponent jComponent) {
        ElementProperties properties = new ElementProperties(jComponent);
        addBasicProperties(properties, jComponent);
        properties.addProperty(new SetAutoLayoutCommand((DesignerComponent) jComponent));
        // properties.addProperty(new SetBackgroundCommand( (DesignerComponent)
        // jComponent));
        if (!((DiagramComponentSetPanel) jComponent).getTextField().isVisible()) {
            properties.addProperty(new SelectInputMethodCommand((DesignerComponent) jComponent));
            properties.addProperty(new SelectCardPaneCommand((DesignerComponent) jComponent));
        }
        return properties;
    }

    public static ElementProperties createCardPaneProperties(JComponent jComponent) {
        ElementProperties properties = new ElementProperties(jComponent);
        properties.addProperty(new SetTextCommand((DesignerComponent) jComponent, "Border Title", "Display"));
        // addDisplayProperties(properties, jComponent);
        addBasicProperties(properties, jComponent);
        return properties;
    }

    public static ElementProperties createTitlePaneProperties(JComponent jComponent) {
        ElementProperties properties = new ElementProperties(jComponent);
        properties.addProperty(new SetTextCommand((DesignerComponent) jComponent, "Border Title", "Display"));
        // addDisplayProperties(properties, jComponent);
        addBasicProperties(properties, jComponent);
        return properties;
    }

    public static ElementProperties createLabelProperties(JComponent jComponent) {
        ElementProperties properties = new ElementProperties(jComponent);
        properties.addProperty(new SetTextCommand((DesignerComponent) jComponent, "Caption", "Display"));
        addDisplayProperties(properties, jComponent);
        properties.addProperty(new SetBackgroundCommand((DesignerComponent) jComponent));
        addBasicProperties(properties, jComponent);
        return properties;
    }

    public static ElementProperties createTextFieldProperties(JComponent jComponent) {
        ElementProperties properties = new ElementProperties(jComponent);
        properties.addProperty(new SetTextCommand((DesignerComponent) jComponent, "Default Value", "Display"));
        addDisplayProperties(properties, jComponent);
        addBasicProperties(properties, jComponent);
        return properties;
    }

    public static ElementProperties createComboBoxProperties(JComponent jComponent) {
        ElementProperties properties = new ElementProperties(jComponent);
        // properties.addProperty(new SetTextCommand ( (DesignerComponent)
        // jComponent, "Name", "Name"));
        addDisplayProperties(properties, jComponent);
        addBasicProperties(properties, jComponent);
        /*
         * JComboBox comboBox = (JComboBox) jComponent;
         * System.out.println("COMMAND COUNT: "+comboBox.getItemCount()); for (
         * int i = 0 ; i < comboBox.getItemCount() ; i ++ ) { SimPropertyChoice
         * simChoice = (SimPropertyChoice) comboBox.getItemAt(i);
         * properties.addProperty(new ChangeChoiceNameCommand(
         * (DesignerComponent) jComponent, simChoice )); }
         */
        return properties;
    }

    public static void appendChoiceProperties(CComboBox comboBox, SimPropertyChoice[] simChoice) {
        ElementProperties properties = comboBox.getProperties();
        for (int i = 0; i < simChoice.length; i++) {
            properties.addProperty(new ChangeChoiceNameCommand(comboBox, simChoice[i]));
        }
    }

    public static ElementProperties createButtonProperties(JComponent jComponent) {
        ElementProperties properties = new ElementProperties(jComponent);
        properties.addProperty(new SetTextCommand((DesignerComponent) jComponent, "Caption", "Display"));
        addDisplayProperties(properties, jComponent);
        addBasicProperties(properties, jComponent);
        properties.addProperty(new SelectActionCommand((DesignerComponent) jComponent));
        return properties;
    }
}
