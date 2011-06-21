package designer;

import designer.deployment.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Dimension;

public interface DesignerComponent
{
    public String getText();
    public void setText( String text );

    public CoverComponent getCover();
    public void setCover ( CoverComponent cover );

    public CoverControl getCoverControl();
    public void setCoverControl( CoverControl coverControl);

	public void setProperties ( ElementProperties properties );
	public ElementProperties getProperties ();
}

class CButton extends JButton implements DesignerComponent
{
    private String text;

    private CoverComponent cover;
    private CoverControl coverControl;

    private ElementProperties properties;

    private DAction action;

    public CButton()
    {
        super();
        setCoverControl(CoverControl.createDraggableEditTextControl());
        setText("Untitled Button");
        setProperties( PropertiesFactory.createButtonProperties(this) );
        //setOpaque(false);
    }
    public void setProperties ( ElementProperties properties ){
        this.properties = properties;
    }
    public ElementProperties getProperties (){
        return properties;
    }
    public void setDAction ( DAction action )
    {
        this.action = action;
    }
    public DAction getDAction ()
    {
        return action;
    }
    public CoverControl getCoverControl ()
    {
        return coverControl;
    }
    public void setCoverControl ( CoverControl coverControl )
    {
        this.coverControl = coverControl;
    }
    public CoverComponent getCover ()
    {
        return cover;
    }
    public void setCoverLink ( CoverComponent cover )
    {
        this.cover = cover;
    }
    public void setCover ( CoverComponent cover )
    {
        this.cover = cover;
        this.coverControl.setCover(cover);
        this.cover.setImageIcon("designer/images/button16.gif");
    }
    public String getText()
    {
        return super.getText();
    }
    public void setText(String s)
    {
        super.setText(s);
    }
    public String toString ()
    {
        return getText();
    }
}
class CLabel extends JLabel implements DesignerComponent
{
    private String text;

    private CoverComponent cover;
    private CoverControl coverControl;
    private ElementProperties properties;
    public CLabel()
    {
        super();
        setCoverControl(CoverControl.createDraggableEditTextControl());
        setText("Untitled Label");
        setProperties( PropertiesFactory.createLabelProperties(this) );
        setOpaque(true);
    }
    public void setProperties ( ElementProperties properties ){
        this.properties = properties;
    }
    public ElementProperties getProperties (){
        return properties;
    }
    public CoverControl getCoverControl ()
    {
        return coverControl;
    }
    public void setCoverControl ( CoverControl coverControl )
    {
        this.coverControl = coverControl;
    }
    public CoverComponent getCover ()
    {
        return cover;
    }
    public void setCover ( CoverComponent cover )
    {
        this.cover = cover;
        this.coverControl.setCover(cover);
        this.cover.setImageIcon("designer/images/label16.gif");
    }
    public void setCoverLink ( CoverComponent cover )
    {
        this.cover = cover;
    }
    public String getText()
    {
        return super.getText();
    }
    public void setText(String s)
    {
        super.setText(s);
    }
    public String toString()
    {
        return getText();
    }
}
class CTextField extends JTextField implements DesignerComponent
{
    private String text;

    private CoverComponent cover;
    private CoverControl coverControl;
    private ElementProperties properties;
    public CTextField()
    {
        super();
        setCoverControl(CoverControl.createDraggableEditTextControl());
        setText("Untitled TextField");
        setProperties( PropertiesFactory.createTextFieldProperties(this) );
        //setOpaque(false);
    }
    public void setProperties ( ElementProperties properties ){
        this.properties = properties;
    }
    public ElementProperties getProperties (){
        return properties;
    }
    public CoverControl getCoverControl ()
    {
        return coverControl;
    }
    public void setCoverControl ( CoverControl coverControl )
    {
        this.coverControl = coverControl;
    }
    public CoverComponent getCover ()
    {
        return cover;
    }
    public void setCover ( CoverComponent cover )
    {
        this.cover = cover;
        this.coverControl.setCover(cover);
        this.cover.setImageIcon("designer/images/text16.gif");
    }
    public void setCoverLink ( CoverComponent cover )
    {
        this.cover = cover;
    }
    public String getText()
    {
        return super.getText();
    }
    public void setText(String s)
    {
        super.setText(s);
    }
    public String toString()
    {
        return getText();
    }
}
class CComboBox extends JComboBox implements DesignerComponent
{
    private String text;

    private CoverComponent cover;
    private CoverControl coverControl;
    private ElementProperties properties;

    public CComboBox()
    {
        super();
        setCoverControl(CoverControl.createDraggableEditTextControl());
        setText("Untitled ComboBox");
        setProperties( PropertiesFactory.createComboBoxProperties(this) );
        //setOpaque(false);
    }
    public void setProperties ( ElementProperties properties ){
        this.properties = properties;
    }
    public ElementProperties getProperties (){
        return properties;
    }
    public CoverControl getCoverControl ()
    {
        return coverControl;
    }
    public void setCoverControl ( CoverControl coverControl )
    {
        this.coverControl = coverControl;
    }
    public CoverComponent getCover ()
    {
        return cover;
    }
    public void setCover ( CoverComponent cover )
    {
        this.cover = cover;
        this.coverControl.setCover(cover);
        this.cover.setImageIcon("designer/images/combo16.gif");
    }
    public void setCoverLink ( CoverComponent cover )
    {
        this.cover = cover;
    }
    public String getText()
    {
        return text;
    }
    public void setText(String s)
    {
        this.text = s;
    }
    public String toString()
    {
        return getText();
    }
    public void setSize ( Dimension d )
    {
        super.setSize(d);
        //updateUI();
    }
    public void setSize ( int w, int h )
    {
        super.setSize(w, h);
        //updateUI();
    }
}
