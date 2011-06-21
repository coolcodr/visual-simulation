package chart;

import java.awt.Font;
import java.awt.Color;
import java.io.Serializable;

public class SampleValue implements Serializable{
	
	private double _value=0;
	private String _label="";
	private Font _font=new Font("Dialog",Font.PLAIN,12);
	private String _unit="";
	private Color _color= Color.blue;
	
	public SampleValue(){
	}
	
	public SampleValue(double value, String label){
		_value=value;
		_label=label;
	}
	
	public SampleValue(double value, String label, Font font){
		_value=value;
		_label=label;
		_font=font;
	}
	
	public SampleValue(double value, String label, Font font, String unit){
		_value=value;
		_label=label;
		_font=font;
		_unit=unit;
	}
	
	public SampleValue(double value, String label, Font font, String unit, Color color){
		_value=value;
		_label=label;
		_font=font;
		_unit=unit;
		_color = color;
	}
	
	public double getValue(){
		return _value;
	}
	
	public String getLabel(){
		return _label;
	}
	
	public Font getFont(){
		return _font;
	}
	
	public String getUnit(){
		return _unit;
	}
	
	public Color getColor(){
		return _color;
	}
	
	public void setValue(double value){
		_value = value;
	}
	
	public void setLabel(String label){
		_label = label;
	}
	
	public void setFont(Font font){
		_font = font;
	}
	
	public void setUnit(String unit){
		_unit = unit;
	}
	
	public void setColor(Color color){
		_color = color;
	}
}