package designer.report;

import print.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.image.*;

import designer.*;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class ControlMainEditor
{
    //Print Page
    private ReportPage reportPage;
    private ReportDocument reportDocument;

    //Control
    private ControlPrint controlPrint;

    //Panework
    protected ControlPaneEdit controlPaneEdit;

    //Save and load control
    protected SaveControl saveControl;

    //Pane
    private BackPane backPane;
    private PreviewPane previewPane;
    private JList pageList;

    // << and >>
    private JTextField jTextField;
    private JTextPane jTextPane;

    private JScrollPane jScrollPane;
    private JScrollPane jScrollPane1;
    private PrintEditor printEditor;

    private double previewScale;

    private DialogMultiPage dialogMultiPage;

    public ControlMainEditor(  JScrollPane jScrollPane,  JScrollPane jScrollPane1, PrintEditor printEditor )
    {
        this.jScrollPane = jScrollPane;
        this.jScrollPane1 = jScrollPane1;
        this.printEditor = printEditor;
        this.pageList = printEditor.getList();

        jTextField = new JTextField();
        controlPrint = new ControlPrint();
        reportDocument = new ReportDocument();
        controlPaneEdit = new ControlPaneEdit( this );

        controlPaneEdit.setGridSize(DesignerControl.getGridSize());
        saveControl = new SaveControl(reportDocument);

        dialogMultiPage = new DialogMultiPage ( printEditor );

        jTextField.setNextFocusableComponent(jTextPane);

        previewScale = 0.5;

    }
    public void refreshPageList ()
    {

    }
    public ReportDocument getReportDocument ()
    {
        return reportDocument;
    }
    public void close ()
    {
        ((DefaultListModel)pageList.getModel()).removeAllElements();

        jTextField = new JTextField();
        controlPrint = new ControlPrint();
        reportDocument = new ReportDocument();
        controlPaneEdit = new ControlPaneEdit( this );

        saveControl = new SaveControl(reportDocument);

        previewScale = 0.5;

        if ( backPane != null )
        {
            backPane.removeAll();
            backPane.setPreferredSize(new Dimension(0, 0));
        }

        if ( previewPane != null )
        {
            previewPane.removeAll();
            previewPane.setPreferredSize(new Dimension(0, 0));
        }
        jScrollPane.updateUI();
        jScrollPane1.updateUI();
        //this.refreshPreview();
        //this.refreshView();
    }
    public void addNewDiagram ( ObjectComponent component )
    {
        //Create a new page
        /* failed in image
        ImageIcon image = new ImageIcon("E:\\Temp\\simChart-Printing intergrate 001\\Printing\\images\\AlignCenter16.gif");

        Image i1 = image.getImage();
        *
        component.setBounds(1,1,1,1);
        reportPage = new ReportPage();

        ObjectImage objectImage = new ObjectImage((BufferedImage)i1);
        */
        component.setBounds(1,1,1,1);
        reportPage = new ReportPage(reportDocument.getHeaderFooter());
        reportPage.addDiagram(component);

        //Add it to doucment
        reportDocument.addReportPage(reportPage);
    }
    public void saveAs ()
    {
        saveControl.saveAs();
    }
    public void save ()
    {
        saveControl.save();
    }
    public void viewPage ( int i )
    {
        JScrollBar sb = jScrollPane.getVerticalScrollBar();
        int max = backPane.getPreferredSize().height - 10;
        int numOfReport = reportDocument.getNumOfPage();
        int page = (int) ( max / numOfReport );
        int value = page * i;
        sb.setValue(value);
    }
    public void load ()
     {
         reportDocument = saveControl.load();
         load(reportDocument);
     }
     public void load ( ReportDocument reportDocument )
     {
         if ( reportDocument == null ) return;

         this.reportDocument = reportDocument;
         reportDocument.restoreImage();
         reportDocument.setPageFormat(true);

         controlPaneEdit = new ControlPaneEdit( this );

         saveControl = new SaveControl(reportDocument);

         previewScale = 0.5;

         if ( backPane != null )
         {
             backPane.removeAll();
             backPane.setPreferredSize(new Dimension(0, 0));

         }

         if ( previewPane != null )
         {
             previewPane.removeAll();
             previewPane.setPreferredSize(new Dimension(0, 0));
         }

         try
         {
             refreshView();
             refreshPreview();
         }
         catch ( Exception ex )
         {
             System.out.println(ex);
         }

         jScrollPane.updateUI();
         jScrollPane1.updateUI();

         printEditor.enableEditMenuItem(true);
         printEditor.updateFormatingDialog(reportDocument.getPageNumberFormat(), reportDocument.getDateTimeFormat());
     }

    public void addParagraph ()
    {
        controlPaneEdit.setStatus(ControlPaneEdit.ADD_PARAGRAPH);
    }
    public void editHeader ()
    {
        controlPaneEdit.addHeader(backPane);
    }
    public void editFooter ()
    {
        controlPaneEdit.addFooter(backPane);
    }
    public void addHeaderLine (boolean b)
    {
        controlPaneEdit.addHeaderLine(backPane,b);
    }
    public void addFooterLine (boolean b)
    {
        controlPaneEdit.addFooterLine(backPane,b);
    }
    public boolean getHeaderLine ()
    {
        return controlPaneEdit.getHeaderLine(backPane);
    }
    public boolean getFooterLine ()
    {
        return controlPaneEdit.getFooterLine(backPane);
    }
    public boolean getHaveHeader()
    {
        return controlPaneEdit.getHaveHeader(backPane);
    }
    public boolean getHaveFooter()
    {
        return controlPaneEdit.getHaveFooter(backPane);
    }
    public void removeHeader()
    {
        controlPaneEdit.removeHeader(backPane);
    }
    public void removeFooter()
    {
        controlPaneEdit.removeFooter(backPane);
    }
    public void scrollToPageTop ( int i )
    {
    }
    public void refreshView ( )
    {
        reportDocument.updateStartPage(true);
        reportDocument.refresh();

        backPane = new BackPane ( controlPaneEdit, reportDocument, pageList);
        backPane.setPreferredSize(new Dimension ( (int)jScrollPane.getSize().getWidth(), (int)jScrollPane.getSize().getHeight()));
        backPane.addAllPanel();

        jScrollPane.add(backPane);
        jScrollPane.setViewportView(backPane);
        backPane.setVisible(true);

    }
    public void refreshPreview()
    {
        reportDocument.updateStartPage(false);
        reportDocument.refresh();

        previewPane = new PreviewPane(reportDocument, previewScale);
        //previewPane.setPreferredSize(new Dimension ( (int)jScrollPane.getSize().getWidth(), (int)jScrollPane.getSize().getHeight()));
        previewPane.setPreferredSize(new Dimension ( (int)jScrollPane.getSize().getWidth(), (int)jScrollPane.getSize().getHeight()));
        previewPane.addAllPanel();

        jScrollPane1.add(previewPane);
        jScrollPane1.setViewportView(previewPane);
        previewPane.setVisible(true);

    }
    public void setPreviewScale ( double d )
    {
        previewScale = d;
        refreshPreview();
    }
    public void setTwoPagePreview ()
    {
        previewScale = (jScrollPane1.getWidth()/ 2 - 20)/ reportDocument.getMultiPageFormat().getWidth();
        refreshPreview();
    }
    public void setPageWidthPreview ()
    {
        previewScale = (jScrollPane1.getWidth() - 30)/ reportDocument.getMultiPageFormat().getWidth();
        refreshPreview();
    }
    public void setWholePagePreview ()
    {
        previewScale = (jScrollPane1.getHeight() - 15)/ reportDocument.getMultiPageFormat().getHeight();
        refreshPreview();
    }
    public double getPreviewScale ()
    {
        return previewPane.getScale();
    }
    public void setTextPane ( JTextPane jTextPane )
    {
        this.jTextPane = jTextPane;
        jTextPane.addCaretListener( new CaretListener()
        {
            public void caretUpdate ( CaretEvent e )
            {
                updateTextFormat();
            }
        });
    }
    public void setCurrentFontFormat ( JTextPane jTextPane )
    {
        printEditor.setFontFormat();
    }
    public void updateTextFormat ()
    {
        //printEditor.updateTextFormat(jTextPane.getCharacterAttributes());
        //if ( jTextPane.getCaretPosition() != 0 )
            printEditor.updateParagraphFormat(jTextPane.getParagraphAttributes());
        if ( jTextPane.getCaretPosition() != 0 )
            printEditor.updateTextFormat(jTextPane.getStyledDocument().getCharacterElement(jTextPane.getCaretPosition()-1).getAttributes());
    }
    public void pageSetup ()
    {
        reportDocument.setPageFormat();
        //reportDocument.updateStartPage(true);
        if ( jScrollPane1.isShowing() )
            refreshPreview();
        else
            refreshView();
    }
    public void setMultiPageFormat ()
    {
        dialogMultiPage.showDialog(reportDocument.getPageFormat());
        int result[] = dialogMultiPage.getMultiPageFormat();
        reportDocument.setMultiPage(result[0],result[1]);
        reportDocument.setMultiPageOrientation(result[2]);
        reportDocument.setMultiPageDirection(result[3]);
        reportDocument.setMultiPageBoarder(result[4]);
        if ( jScrollPane1.isShowing() )
            refreshPreview();
        else
            refreshView();
    }
    public void print ()
    {
        reportDocument.updateStartPage(false);
        controlPrint.printDocument(reportDocument);
        reportDocument.updateStartPage(true);
    }
    public void setDiagramScale ( int i )
    {
        if ( controlPaneEdit.getSelectedDiagram()!=null && controlPaneEdit.setDiagramScale(i) )
        //if ( controlPaneEdit.setDiagramScale(i) )
            refreshView ();
        printEditor.repaint();
    }
    public void setDiagramScaleHorizontalFit ()
    {
        //if ( controlPaneEdit.getSelectedDiagram()!=null && controlPaneEdit.setDiagramScaleHorizontalFit() )
        if ( controlPaneEdit.setDiagramScaleHorizontalFit() )
            refreshView ();
        printEditor.repaint();
    }
    public void setDiagramScaleVerticalFit ()
    {
        //if ( controlPaneEdit.getSelectedDiagram()!=null && controlPaneEdit.setDiagramScaleVerticalFit() )
        if (  controlPaneEdit.setDiagramScaleVerticalFit() )
            refreshView ();
        printEditor.repaint();
    }
    public void setDiagramPositionCenter()
    {
        controlPaneEdit.setDiagramPositionCenter();
        printEditor.repaint();
    }
    public void setFont ( String s )
    {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setFontFamily( attr, s);
        jTextPane.setCharacterAttributes(attr, false);
    }
    public void setFontSize ( int i )
    {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setFontSize (attr, i);
        jTextPane.setCharacterAttributes(attr, false);
    }
    public void setItatic ( boolean b )
    {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setItalic (attr, b);
        jTextPane.setCharacterAttributes(attr, false);
    }
    public void setBold ( boolean b )
    {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setBold (attr, b);
        jTextPane.setCharacterAttributes(attr, false);
    }
    public void setUnderLine ( boolean b )
    {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setUnderline(attr, b);
        jTextPane.setCharacterAttributes(attr, false);
    }
    public void setLeftAlign( boolean b )
    {
        MutableAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr,StyleConstants.ALIGN_LEFT );
        jTextPane.setParagraphAttributes(attr, false);
        updateTextFormat();
    }
    public void setCenterAlign( boolean b )
    {
        MutableAttributeSet attr = new SimpleAttributeSet();
        if (b)
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_CENTER );
        else
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_LEFT );
        jTextPane.setParagraphAttributes(attr, false);
        updateTextFormat();
    }
    public void setRightAlign( boolean b )
    {
        MutableAttributeSet attr = new SimpleAttributeSet();
        if (b)
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_RIGHT );
        else
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_LEFT );

        jTextPane.setParagraphAttributes(attr, false);
        updateTextFormat();
    }
    public void setJustifyAlign( boolean b )
    {
        MutableAttributeSet attr = new SimpleAttributeSet();
        if (b)
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_JUSTIFIED );
        else
            StyleConstants.setAlignment(attr,StyleConstants.ALIGN_LEFT );
        jTextPane.setParagraphAttributes(attr, false);
        updateTextFormat();
    }
    public void removeItem()
    {
        controlPaneEdit.removeItem();
    }
    public void enableDiagramEditButton ( boolean b )
    {
        printEditor.enableDiagramEditButton(b);
    }
    public void enableParagraphEditButton ( boolean b )
    {
        printEditor.enableParagraphEditButton(b);
    }
    public void setAddTextSelected ( boolean b )
    {
        printEditor.enableAddTextButton(b);
    }
    public void setScaleText ( int i )
    {
        printEditor.setScaleText(i);
    }
    public void setStatus3Text ( String s )
    {
        printEditor.setStatus3Text(s);
    }
    public void setStatus2Text ( String s )
    {
        printEditor.setStatus2Text(s);
    }
    public void setStatus1Text ( int currentPage, int totalPage )
    {
        printEditor.setStatus1Text(currentPage, totalPage);
        pageList.setSelectedIndex(currentPage);
    }
    public void updatePageNumber ( PCPageNumber pcPageNumber )
    {
        reportDocument.setPageNumberFormat(pcPageNumber);
    }
    public void updateDataTime ( PCPageNumber pcPageNumber )
    {
        reportDocument.setDateTimeFormat ( pcPageNumber );
    }

    public static void disableDoubleBuffering(Component c)
	{
    	RepaintManager currentManager = RepaintManager.currentManager(c);
    	currentManager.setDoubleBufferingEnabled(false);
  	}

  	public static void enableDoubleBuffering(Component c)
  	{
    	RepaintManager currentManager = RepaintManager.currentManager(c);
    	currentManager.setDoubleBufferingEnabled(true);
  	}
}