package diagram;

import javax.swing.JComponent;
import java.awt.*;
import java.lang.reflect.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class DiagramElementType {
  protected String _name;
  protected String _category;
  protected Renderer _renderer;
  protected ExporterControl _exporterControl;
  protected ImporterControl _importerControl;
  protected String _ElementClass;

  static public final String SHAPE = "shape";
  static public final String CONNECTOR = "connector";
  static public final String PORT = "port";
//added by matthew  
  static public final String SELECTION = "selection";

  public DiagramElementType(String name, String className, String category, String renderer, String exporterControl, String importerControl) throws java.lang.Exception {
    _name = name;
    _category = category;
    _renderer = (Renderer) (Class.forName(renderer).newInstance());
    _exporterControl = (ExporterControl) (Class.forName(exporterControl).newInstance());
	if(_exporterControl != null)
	//System.out.println(_exporterControl);
	_importerControl = (ImporterControl) (Class.forName(importerControl).newInstance());
    _ElementClass = className;
  }


  public String getElementClass() {
    return(_ElementClass);
  }

  public Renderer getRenderer() {
    return(_renderer);
  }

  public String getCategory() {
    return _category;
  }


  public boolean isShape() {
    return (_category.equals(SHAPE));
  }

  public boolean isConnector() {
    return (_category.equals(CONNECTOR));
  }
  
  public boolean isPort() {
    return (_category.equals(PORT));
  }  

//modified by matthew
  public boolean isSelection() {
    return (_category.equals(SELECTION));
  }

  public String getName() {
	return _name;
  }

  
  public ExporterControl getExporterControl() {
    return _exporterControl;
  }

  public ImporterControl getImporterControl() {
    return _importerControl;
  }
}