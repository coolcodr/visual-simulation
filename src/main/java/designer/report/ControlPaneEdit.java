package designer.report;

/**
 * <p>Title: Print Editor</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.FocusManager;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.StyledDocument;

import print.PCDiagram;
import print.PCParagraph;
import print.ReportPage;

public class ControlPaneEdit {
    public static final int NORMAL = 1;
    public static final int ADD_PARAGRAPH = 2;
    public static final int ADDING_PARAGRAPH = 3;
    public static final int PCDIAGRAM_SELECTED = 4;
    public static final int PCDIAGRAM_RESIZE = 5;
    public static final int PCPARAGRAPH_SELECTED = 6;
    public static final int PCPARAGRAPH_RESIZE = 7;
    public static final int EDITING_PARAGRAPH = 8;
    public static final int HEADER_SELECTED = 9;
    public static final int HEADER_EDITING = 10;
    public static final int HEADER_RESIZING = 11;
    public static final int FOOTER_SELECTED = 12;
    public static final int FOOTER_EDITING = 13;
    public static final int FOOTER_RESIZING = 14;

    private int status;
    // Current component
    private PCDiagram selectedDiagram;
    private PCParagraph selectedParagraph;

    // Report page parameter
    private int marginX;
    private int marginY;
    private int imageableWidth;
    private int imageableHeight;

    // This class
    private int x;
    private int y;
    private int width;
    private int height;

    private int dx;
    private int dy;

    private int gridSize = 4;

    private WorkPane workPane;
    private ControlMainEditor controlMainEditor;

    // Swing component
    private JTextField jTextFieldText;
    private JTextPane jTextPaneParagraph;

    private int currentPage = 0;

    public ControlPaneEdit(ControlMainEditor controlMainEditor) {
        status = NORMAL;
        x = 0;
        y = 0;
        width = 0;
        height = 0;

        jTextFieldText = new JTextField();
        jTextPaneParagraph = new JTextPane();

        controlMainEditor.setTextPane(jTextPaneParagraph);

        jTextFieldText.setNextFocusableComponent(jTextPaneParagraph);
        jTextPaneParagraph.setNextFocusableComponent(jTextFieldText);

        this.controlMainEditor = controlMainEditor;

    }

    public void setStatus(int i) {
        status = i;
    }

    public void drawSelected(Graphics2D g2) {
        if (width == 0 && height == 0) {
            return;
        }
        switch (status) {
            case (NORMAL):
                g2.setColor(Color.gray);
                break;
            case (ADD_PARAGRAPH):
                g2.setColor(Color.lightGray);
                break;
            case (HEADER_SELECTED):
            case (FOOTER_SELECTED):
            case (PCDIAGRAM_SELECTED):
            case (PCPARAGRAPH_SELECTED):
            case (EDITING_PARAGRAPH):
            case (HEADER_EDITING):
            case (FOOTER_EDITING):
                g2.setColor(Color.gray);
                break;
        }
        if (workPane.getReportPage().getDiagram()[0].getNumOfPages() > 1 && status == PCDIAGRAM_SELECTED) {
            ;
        } else {
            g2.drawRect(x, y, width, height);
        }

        if (width + height != 0) {
            // g2.fillRect( x - 3 , y - 3, 6, 6 );
            // g2.fillRect( x - 3 , y + height - 2 , 6, 6);
            // g2.fillRect( x + width - 2 , y - 3 , 6, 6);
            if (status != HEADER_SELECTED && status != FOOTER_SELECTED && status != HEADER_EDITING && status != FOOTER_EDITING && status != HEADER_RESIZING && status != FOOTER_RESIZING && status != NORMAL) {
                g2.fillRect(x + width - 3, y + height - 3, 6, 6);
            }
        }
    }

    public void mousePressed(int x, int y) {
        setBoundary();
        jTextPaneParagraph.setVisible(false);
        jTextFieldText.setVisible(false);
        // status = selectObject ( x, y );
        switch (status) {
            case (NORMAL):
                this.x = x;
                this.y = y;
                width = 0;
                height = 0;
            case (ADD_PARAGRAPH):
                this.x = x - x % gridSize;
                this.y = y - y % gridSize;
                width = 0;
                height = 0;
                break;
            case (ADDING_PARAGRAPH):
                addParagraph();
                status = NORMAL;
                status = selectObject(x, y);
                dx = x - this.x;
                dy = y - this.y;
                break;
            case (EDITING_PARAGRAPH):
                editParagraph();
                // status = NORMAL;
                status = PCPARAGRAPH_SELECTED;
                status = selectObject(x, y);// Test added
                dx = x - this.x;// Test added
                dy = y - this.y;// Test added
                break;
            case (HEADER_EDITING):
                editHeader();
                // status = NORMAL;
                status = HEADER_SELECTED;
                status = selectObject(x, y);// Test added
                dx = x - this.x;// Test added
                dy = y - this.y;// Test added
                break;
            case (FOOTER_EDITING):
                editFooter();
                // status = NORMAL;
                status = FOOTER_SELECTED;
                status = selectObject(x, y);// Test added
                dx = x - this.x;// Test added
                dy = y - this.y;// Test added
                break;
            case (HEADER_SELECTED):
            case (FOOTER_SELECTED):
            case (PCPARAGRAPH_SELECTED):
            case (PCDIAGRAM_SELECTED):
                status = selectObject(x, y);
                dx = x - this.x;
                dy = y - this.y;
                break;
        }
        updateToolBar();
        updateEditorInfo();
    }

    public void mouseReleased(int x, int y) {
        jTextFieldText.setVisible(false);
        clearInfo();
        switch (status) {
            // Relase a size of a text box
            case (NORMAL):
                paneRefresh(workPane);
                break;
            case (ADD_PARAGRAPH):
                jTextPaneParagraph = new JTextPane();
                controlMainEditor.setTextPane(jTextPaneParagraph);
                controlMainEditor.setCurrentFontFormat(jTextPaneParagraph);
                jTextPaneParagraph.setVerifyInputWhenFocusTarget(true);
                workPane.setLayer(jTextPaneParagraph, JLayeredPane.PALETTE_LAYER.intValue());
                workPane.add(jTextPaneParagraph);
                jTextPaneParagraph.setBounds(this.x, this.y, width + 1, height + 1);
                jTextPaneParagraph.setVisible(true);
                jTextPaneParagraph.setBorder(BorderFactory.createLineBorder(Color.gray));
                jTextPaneParagraph.setText("");
                status = ADDING_PARAGRAPH;
                FocusManager.getCurrentManager().focusNextComponent(jTextFieldText);
                break;
            case (PCPARAGRAPH_RESIZE):
                status = PCPARAGRAPH_SELECTED;
                break;
            case (PCDIAGRAM_RESIZE):
                status = PCDIAGRAM_SELECTED;
                break;
            case (HEADER_RESIZING):
                status = HEADER_SELECTED;
                break;
            case (FOOTER_RESIZING):
                status = FOOTER_SELECTED;
                break;
        }
        updateToolBar();
        updateEditorInfo();
    }

    public Dimension testSize(String text) {
        /*
         * FontMetrics fm =
         * jTextPaneParagraph.getFontMetrics(jTextPaneParagraph.getFont()); int
         * width = fm.stringWidth(text); int height = fm.getMaxAscent();
         */
        setBoundary();
        int width = imageableWidth - 20;
        int height = 24;
        width = width - width % gridSize;
        height = height - height % gridSize;
        return new Dimension(width, height);
    }

    public void addText(int x, int y, int width, int height, String text) {
        status = NORMAL;
        paneRefresh(workPane);
        if (jTextPaneParagraph.isVisible()) {
            jTextPaneParagraph.setVisible(false);
            editParagraph();
        }

        jTextPaneParagraph = new JTextPane();
        controlMainEditor.setTextPane(jTextPaneParagraph);
        controlMainEditor.setCurrentFontFormat(jTextPaneParagraph);
        // jTextPaneParagraph.setVerifyInputWhenFocusTarget(true);
        workPane.setLayer(jTextPaneParagraph, JLayeredPane.PALETTE_LAYER.intValue());
        workPane.add(jTextPaneParagraph);
        jTextPaneParagraph.setBounds(x, y, width, height);
        jTextPaneParagraph.setVisible(true);
        jTextPaneParagraph.setBorder(BorderFactory.createLineBorder(Color.gray));
        jTextPaneParagraph.setText(text);
        setBoundary();
        jTextPaneParagraph.setVisible(false);
        jTextFieldText.setVisible(false);
        clearInfo();
        addParagraph();
        paneRefresh(workPane);
    }

    public void mouseClicked(int x, int y, boolean doubleClick) {
        setBoundary();
        status = selectObject(x, y);
        if (doubleClick) {
            switch (status) {
                case (PCPARAGRAPH_SELECTED):

                    controlMainEditor.setTextPane(jTextPaneParagraph);
                    jTextPaneParagraph.setStyledDocument(selectedParagraph.getParagraph());
                    jTextPaneParagraph.setCaretPosition(jTextPaneParagraph.getStyledDocument().getLength());
                    jTextPaneParagraph.setBounds(this.x + 3, this.y + 3, width - 6, height - 3);
                    jTextPaneParagraph.setBorder(null);
                    jTextPaneParagraph.setVisible(true);
                    status = EDITING_PARAGRAPH;
                    FocusManager.getCurrentManager().focusNextComponent(jTextFieldText);
                    break;
                case (HEADER_SELECTED):
                    controlMainEditor.setTextPane(jTextPaneParagraph);
                    jTextPaneParagraph.setStyledDocument(workPane.getReportPage().getHeader());
                    jTextPaneParagraph.setCaretPosition(jTextPaneParagraph.getStyledDocument().getLength());
                    jTextPaneParagraph.setBounds(this.x + 3, this.y + 3, width - 6, height - 6 - 5);
                    jTextPaneParagraph.setBorder(null);
                    jTextPaneParagraph.setVisible(true);
                    status = HEADER_EDITING;
                    FocusManager.getCurrentManager().focusNextComponent(jTextFieldText);
                    break;
                case (FOOTER_SELECTED):
                    controlMainEditor.setTextPane(jTextPaneParagraph);
                    jTextPaneParagraph.setStyledDocument(workPane.getReportPage().getFooter());
                    jTextPaneParagraph.setCaretPosition(jTextPaneParagraph.getStyledDocument().getLength());
                    jTextPaneParagraph.setBounds(this.x + 3, this.y + 3 + 5, width - 6, height - 6 - 5);
                    jTextPaneParagraph.setBorder(null);
                    jTextPaneParagraph.setVisible(true);
                    status = FOOTER_EDITING;
                    FocusManager.getCurrentManager().focusNextComponent(jTextFieldText);
                    break;
            }
        }
        updateToolBar();
        updateEditorInfo();
    }

    public void setDraggedSize(int x, int y) {
        setBoundary();
        // x = x - x%gridSize;
        // y = y - y%gridSize;
        int newX, newY;
        switch (status) {
            case (ADD_PARAGRAPH):
                newX = x - x % gridSize - this.x;
                newY = y - y % gridSize - this.y;
                width = newX;// - newX%gridSize;
                height = newY;// - newY%gridSize;
                // displayTextInfo(String.valueOf(this.width)+" x "+String.valueOf(this.height),
                // this.x, this.y + this.height);
                controlMainEditor.setStatus2Text(" Size: " + String.valueOf(width) + " x " + String.valueOf(height));
                break;
            case (PCDIAGRAM_SELECTED):
                newX = x - dx - marginX + 5;
                newY = y - dy - marginY + 5;
                selectedDiagram.setXY(newX - newX % gridSize, newY - newY % gridSize);
                this.x = selectedDiagram.getX() + marginX - 5;
                this.y = selectedDiagram.getY() + marginY - 5;
                // displayTextInfo(String.valueOf(this.x-marginX-5)+", "+String.valueOf(this.y-marginY-5),
                // this.x, this.y-21);
                controlMainEditor.setStatus2Text(" Location: " + String.valueOf(this.x - marginX - 5) + ", " + String.valueOf(this.y - marginY - 5));
                break;
            case (PCDIAGRAM_RESIZE):
                newX = x - this.x;
                newY = y - this.y;
                selectedDiagram.setSize(newX - newX % gridSize, newY - newY % gridSize);
                this.x = selectedDiagram.getX() + marginX - 5;
                this.y = selectedDiagram.getY() + marginY - 5;
                width = selectedDiagram.getWidth() + 10;
                height = selectedDiagram.getHeight() + 10;
                // displayTextInfo(String.valueOf(this.width-10)+" x "+String.valueOf(this.height-10)+" ("+String.valueOf(selectedDiagram.getScale())+"%)",
                // this.x, this.y + this.height);
                controlMainEditor.setStatus2Text(" Size: " + String.valueOf(width - 10) + " x " + String.valueOf(height - 10) + " (" + String.valueOf(selectedDiagram.getScale()) + "%)");
                break;
            case (HEADER_RESIZING):
                newY = y - this.y - 5;
                workPane.getReportPage().setHeaderHeight(newY - newY % gridSize);
                height = workPane.getReportPage().getHeaderHeight() - 5;
                // this.y = workPane.getReportPage().getHeaderY();
                break;
            case (FOOTER_RESIZING):
                workPane.getReportPage().setFooterY(y - y % gridSize);
                newY = (int) workPane.getReportPage().getPageFormat().getImageableHeight() + marginY - y - 5;
                workPane.getReportPage().setFooterHeight(newY - newY % gridSize);
                height = workPane.getReportPage().getFooterHeight() - 5;
                this.y = workPane.getReportPage().getFooterY() + marginY - 5;
                break;
            case (PCPARAGRAPH_SELECTED):
                newX = x - dx - marginX;
                newY = y - dy - marginY;
                selectedParagraph.setXY(newX - newX % gridSize, newY - newY % gridSize);
                this.x = selectedParagraph.getX() + marginX;
                this.y = selectedParagraph.getY() + marginY;
                // displayTextInfo(String.valueOf(this.x-marginX-10)+", "+String.valueOf(this.y-marginY-10),
                // this.x, this.y-21);
                controlMainEditor.setStatus2Text(" Location: " + String.valueOf(this.x - marginX - 10) + ", " + String.valueOf(this.y - marginY - 10));
                break;
            case (PCPARAGRAPH_RESIZE):
                newX = x - this.x;
                newY = y - this.y;
                selectedParagraph.setSize(newX - newX % gridSize, newY - newY % gridSize);
                this.x = selectedParagraph.getX() + marginX;
                this.y = selectedParagraph.getY() + marginY;
                width = selectedParagraph.getWidth();
                height = selectedParagraph.getHeight();
                // displayTextInfo(String.valueOf(this.width)+" x "+String.valueOf(this.height),
                // this.x, this.y + this.height);
                controlMainEditor.setStatus2Text(" Size: " + String.valueOf(width) + " x " + String.valueOf(height));
                break;
        }
        updateEditorInfo();
    }

    private void displayTextInfo(String text, int x, int y) {
        jTextFieldText.setText(text);
        jTextFieldText.setFocusable(false);
        jTextFieldText.setBounds(x, y, jTextFieldText.getFontMetrics(jTextFieldText.getFont()).stringWidth(text) + 10, 22);
        jTextFieldText.setHorizontalAlignment(SwingConstants.CENTER);
        jTextFieldText.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        jTextFieldText.setVisible(true);
    }

    public void setCurrentPane(WorkPane workPane) {
        if (this.workPane != workPane) {
            if (this.workPane != null) {
                paneRefresh(this.workPane);
            }
            paneRefresh(workPane);

            this.workPane = workPane;
            workPane.setLayer(jTextPaneParagraph, JLayeredPane.PALETTE_LAYER.intValue());
            workPane.add(jTextPaneParagraph);
            jTextPaneParagraph.setVisible(false);

            workPane.setLayer(jTextFieldText, JLayeredPane.PALETTE_LAYER.intValue());
            workPane.add(jTextFieldText);
            jTextFieldText.setVisible(false);
        }
        setBoundary();
    }

    public WorkPane getWorkPane() {
        return workPane;
    }

    public void setCursor(WorkPane workPane, int x, int y) {
        switch (status) {
            case NORMAL:
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
            case (ADD_PARAGRAPH):
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                break;
            case (ADDING_PARAGRAPH):
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                break;
            case (PCDIAGRAM_SELECTED):
            case (EDITING_PARAGRAPH):
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            case (PCPARAGRAPH_SELECTED):
                if (x > this.x + width - 3 && x < this.x + width + 6 && y > this.y + height - 3 && y < this.y + height + 6) {
                    workPane.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                } else if (x > this.x && x < this.x + width && y > this.y && y < this.y + height) {
                    workPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                } else {
                    workPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
                break;
            case (HEADER_EDITING):
            case (HEADER_SELECTED):
                if (x > this.x && x < this.x + width && y > this.y + height - 3 && y < this.y + height + 6) {
                    workPane.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                } else {
                    workPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
                break;
            case (FOOTER_EDITING):
            case (FOOTER_SELECTED):
                if (x > this.x && x < this.x + width && y > this.y - 3 && y < this.y + 6) {
                    workPane.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                } else {
                    workPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
                break;
        }
    }

    private void addParagraph() {
        ReportPage reportPage = workPane.getReportPage();
        int pX = (int) jTextPaneParagraph.getBounds().getX();
        int pY = (int) jTextPaneParagraph.getBounds().getY();
        int pWidth = jTextPaneParagraph.getWidth();
        int pHeight = jTextPaneParagraph.getHeight();

        reportPage.addParagraph(jTextPaneParagraph.getStyledDocument(), workPane.getWorkEditPane().getReportPageNumber(), pX, pY, pWidth, pHeight);
    }

    private void editParagraph() {
        selectedParagraph.setParagraph(jTextPaneParagraph.getStyledDocument());
    }

    private void editHeader() {
        workPane.getReportPage().setHeader(jTextPaneParagraph.getStyledDocument());
    }

    private void editFooter() {
        workPane.getReportPage().setFooter(jTextPaneParagraph.getStyledDocument());
    }

    public boolean setDiagramScale(int i) {
        int originPage = selectedDiagram.getNumOfPages();
        selectedDiagram.setScale(i);

        workPane.getWorkEditPane().repaint();

        int diagramX = selectedDiagram.getX() + marginX;
        int diagramY = selectedDiagram.getY() + marginY;
        int diagramWidth = selectedDiagram.getWidth();
        int diagramHeight = selectedDiagram.getHeight();

        x = diagramX - 5;
        y = diagramY - 5;
        width = diagramWidth + 10;
        height = diagramHeight + 10;

        return (originPage != selectedDiagram.getNumOfPages() ? true : false);
    }

    public boolean setDiagramScaleHorizontalFit() {
        if (selectedDiagram != null) {
            int scale;// = 0;
            double originalWidth = selectedDiagram.getWidth() / ((double) selectedDiagram.getScale() / 100);
            scale = (int) (((imageableWidth - 20) / originalWidth) * 100);
            System.out.println(imageableWidth + " / " + originalWidth + " _ " + selectedDiagram.getScale());
            controlMainEditor.setScaleText(scale);
            return setDiagramScale(scale);
        } else if (selectedParagraph != null) {
            selectedParagraph.setSize(imageableWidth - 20, selectedParagraph.getHeight());
            x = selectedParagraph.getX() + marginX;
            y = selectedParagraph.getY() + marginY;
            width = selectedParagraph.getWidth();
            height = selectedParagraph.getHeight();
            controlMainEditor.setStatus2Text(" Size: " + String.valueOf(width) + " x " + String.valueOf(height));
            return false;
        } else {
            return false;
        }
    }

    public boolean setDiagramScaleVerticalFit() {
        if (selectedDiagram != null) {
            int scale;// = 0;
            double originalHeight = selectedDiagram.getHeight() / ((double) selectedDiagram.getScale() / 100);
            scale = (int) (((imageableHeight - workPane.getReportPage().getHeaderHeight() - workPane.getReportPage().getFooterHeight()) / originalHeight) * 100);
            controlMainEditor.setScaleText(scale);
            return setDiagramScale(scale);
        } else if (selectedParagraph != null) {
            selectedParagraph.setSize(selectedParagraph.getWidth(), imageableHeight - workPane.getReportPage().getHeaderHeight() - workPane.getReportPage().getFooterHeight());
            x = selectedParagraph.getX() + marginX;
            y = selectedParagraph.getY() + marginY;
            width = selectedParagraph.getWidth();
            height = selectedParagraph.getHeight();
            controlMainEditor.setStatus2Text(" Size: " + String.valueOf(width) + " x " + String.valueOf(height));
            return false;
        } else {
            return false;
        }
    }

    public void setDiagramPositionCenter() {
        int x = (imageableWidth - selectedDiagram.getWidth()) / 2;
        int y = (imageableHeight - selectedDiagram.getHeight()) / 2;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        selectedDiagram.setXY(x, y);

        this.x = selectedDiagram.getX() + marginX - 5;
        this.y = selectedDiagram.getY() + marginY - 5;
        controlMainEditor.setStatus2Text(" Location: " + String.valueOf(this.x - marginX - 5) + ", " + String.valueOf(this.y - marginY - 5));

    }

    private void paneRefresh(WorkPane workPane) {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
        workPane.repaint();
    }

    private void setBoundary() {
        imageableWidth = (int) workPane.getReportPage().getPageFormat().getImageableWidth();
        imageableHeight = (int) workPane.getReportPage().getPageFormat().getImageableHeight();
        marginX = (int) workPane.getReportPage().getPageFormat().getImageableX();
        marginY = (int) workPane.getReportPage().getPageFormat().getImageableY();
    }

    private int selectObject(int x2, int y2) {
        int calStatus = NORMAL;

        PCParagraph[] pcParagraph2 = workPane.getReportPage().getParagraph();
        paragraph: for (int i = 0; i < pcParagraph2.length; i++) {
            int paragraphX = pcParagraph2[i].getX() + marginX;
            int paragraphY = pcParagraph2[i].getY() + marginY;
            int paragraphWidth = pcParagraph2[i].getWidth();
            int paragraphHeight = pcParagraph2[i].getHeight();
            if (x2 > paragraphX && x2 < paragraphX + paragraphWidth && y2 > paragraphY && y2 < paragraphY + paragraphHeight) {
                x = paragraphX;
                y = paragraphY;
                width = paragraphWidth;
                height = paragraphHeight;
                status = PCPARAGRAPH_SELECTED;
                selectedParagraph = pcParagraph2[i];
                break paragraph;
            }
        }
        if (status == PCDIAGRAM_SELECTED || status == PCPARAGRAPH_SELECTED) {
            if (x2 > x + width - 3 && x2 < x + width + 6 && y2 > y + height - 3 && y2 < y + height + 6) {
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
                if (status == PCDIAGRAM_SELECTED) {
                    calStatus = PCDIAGRAM_RESIZE;
                }
                if (status == PCPARAGRAPH_SELECTED) {
                    calStatus = PCPARAGRAPH_RESIZE;
                }
            } else if (x2 > x && x2 < x + width && y2 > y && y2 < y + height) {
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                if (status == PCDIAGRAM_SELECTED) {
                    calStatus = PCDIAGRAM_SELECTED;
                }
                if (status == PCPARAGRAPH_SELECTED) {
                    calStatus = PCPARAGRAPH_SELECTED;
                }
            } else {
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                calStatus = NORMAL;
            }
        } else if (status == HEADER_SELECTED) {
            if (x2 > x && x2 < x + width && y2 > y + height - 3 && y2 < y + height + 6) {
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
                calStatus = HEADER_RESIZING;
            } else {
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                calStatus = NORMAL;
            }
        } else if (status == FOOTER_SELECTED) {
            if (x2 > x && x2 < x + width && y2 > y - 3 && y2 < y + 6) {
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                calStatus = FOOTER_RESIZING;
            } else {
                workPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                calStatus = NORMAL;
            }
        } else {
            // Header selection
            int headerX = marginX;
            int headerY = marginY;
            int headerWidth = (int) workPane.getReportPage().getPageFormat().getImageableWidth();
            int headerHeight = workPane.getReportPage().getHeaderHeight();
            if (x2 > headerX && x2 < headerWidth + headerX && y2 > headerY && y2 < headerHeight + headerY && workPane.getReportPage().getHeader() != null) {
                x = headerX;
                y = headerY;
                width = headerWidth;
                height = headerHeight - 5;
                calStatus = HEADER_SELECTED;
            }
            int footerX = marginX;
            int footerY = marginY + (int) workPane.getReportPage().getPageFormat().getImageableHeight() - workPane.getReportPage().getFooterHeight();
            int footerWidth = (int) workPane.getReportPage().getPageFormat().getImageableWidth();
            int footerHeight = workPane.getReportPage().getFooterHeight();
            if (x2 > footerX && x2 < footerWidth + footerX && y2 > footerY && y2 < footerHeight + footerY && workPane.getReportPage().getFooter() != null) {
                x = footerX;
                y = footerY + 5;
                width = footerWidth;
                height = footerHeight - 5;
                calStatus = FOOTER_SELECTED;
            }
            PCDiagram[] pcDiagram = workPane.getReportPage().getDiagram();
            diagram: for (int i = 0; i < pcDiagram.length; i++) {
                int diagramX = pcDiagram[i].getX() + marginX;
                int diagramY = pcDiagram[i].getY() + marginY;
                int diagramWidth = pcDiagram[i].getWidth();
                int diagramHeight = pcDiagram[i].getHeight();
                if (x2 > diagramX && x2 < diagramX + diagramWidth && y2 > diagramY && y2 < diagramY + diagramHeight) {
                    x = diagramX - 5;
                    y = diagramY - 5;
                    width = diagramWidth + 10;
                    height = diagramHeight + 10;
                    calStatus = PCDIAGRAM_SELECTED;
                    selectedDiagram = pcDiagram[i];
                    break diagram;
                }
            }
            PCParagraph[] pcParagraph = workPane.getReportPage().getParagraph();
            paragraph: for (int i = 0; i < pcParagraph.length; i++) {
                int paragraphX = pcParagraph[i].getX() + marginX;
                int paragraphY = pcParagraph[i].getY() + marginY;
                int paragraphWidth = pcParagraph[i].getWidth();
                int paragraphHeight = pcParagraph[i].getHeight();
                if (x2 > paragraphX && x2 < paragraphX + paragraphWidth && y2 > paragraphY && y2 < paragraphY + paragraphHeight) {
                    x = paragraphX;
                    y = paragraphY;
                    width = paragraphWidth;
                    height = paragraphHeight;
                    calStatus = PCPARAGRAPH_SELECTED;
                    selectedParagraph = pcParagraph[i];
                    break paragraph;
                }

            }
        }
        return calStatus;
    }

    public void updateToolBar() {
        controlMainEditor.setAddTextSelected(false);
        switch (status) {
            case (NORMAL):
                controlMainEditor.enableDiagramEditButton(false);
                break;
            case (PCPARAGRAPH_SELECTED):
                controlMainEditor.enableDiagramEditButton(false);
                controlMainEditor.enableParagraphEditButton(true);
                break;
            case (PCDIAGRAM_SELECTED):
                controlMainEditor.enableDiagramEditButton(true);
                break;
            case (ADDING_PARAGRAPH):
            case (EDITING_PARAGRAPH):
                controlMainEditor.enableDiagramEditButton(false);
                break;
            case (ADD_PARAGRAPH):
                controlMainEditor.setAddTextSelected(true);

        }
    }

    private void updateEditorInfo() {
        switch (status) {
            case (NORMAL):
                selectedDiagram = null;
                controlMainEditor.setScaleText(100);
                break;
            case (PCDIAGRAM_SELECTED):
            case (PCDIAGRAM_RESIZE):
                controlMainEditor.setScaleText(selectedDiagram.getScale());
                break;
            default:
                selectedDiagram = null;
                controlMainEditor.setScaleText(100);
                break;

        }
    }

    public PCDiagram getSelectedDiagram() {
        return selectedDiagram;
    }

    private void clearInfo() {
        controlMainEditor.setStatus2Text("");
    }

    public void setStatusPage(int currentPage, int totalPage) {
        this.currentPage = currentPage;
        controlMainEditor.setStatus1Text(currentPage, totalPage);
    }

    public void setGridSize(int i) {
        gridSize = i;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void keyTyped(int i) {
        if (i == KeyEvent.VK_DELETE) {
            removeItem();
        }
    }

    public void removeItem() {
        if (selectedParagraph != null) {
            workPane.getReportPage().removeParagraph(selectedParagraph);
        }
        status = NORMAL;
        mousePressed(0, 0);
        workPane.repaint();
    }

    public void addHeader(BackPane backPane) {
        setCurrentPane(backPane.getWorkPane(currentPage));
        StyledDocument sd = null;
        if (workPane.getReportPage().getHeader() == null) {
            sd = new JTextPane().getStyledDocument();
            workPane.getReportPage().setHeader(sd);
        }
        mousePressed(marginX + 1, marginY + 1);
        mouseClicked(marginX + 1, marginY + 1, true);
        /*
         * if ( sd != null ) for ( int i = 0 ; i < backPane.count() ; i ++ ) {
         * backPane.getWorkPane(i).getReportPage().setHeader(sd); }
         */
        backPane.repaint();
    }

    public void addFooter(BackPane backPane) {
        setCurrentPane(backPane.getWorkPane(currentPage));
        StyledDocument sd = null;
        if (workPane.getReportPage().getFooter() == null) {
            sd = new JTextPane().getStyledDocument();
            workPane.getReportPage().setFooter(sd);
        }
        // workPane.getReportPage().setFooter(workPane.getReportPage().getFooter());
        mousePressed(marginX + 1, marginY + imageableHeight - 1);
        mouseClicked(marginX + 1, marginY + imageableHeight - 1, true);
        /*
         * if ( sd != null ) for ( int i = 0 ; i < backPane.count() ; i ++ ) {
         * backPane.getWorkPane(i).getReportPage().setFooter(sd); }
         */
        backPane.repaint();
    }

    public void removeHeader(BackPane backPane) {
        setCurrentPane(backPane.getWorkPane(currentPage));

        for (int i = 0; i < backPane.count(); i++) {
            backPane.getWorkPane(i).getReportPage().setHeader(null);
        }
        status = NORMAL;
        mousePressed(0, 0);
        backPane.repaint();
    }

    public void removeFooter(BackPane backPane) {
        setCurrentPane(backPane.getWorkPane(currentPage));

        for (int i = 0; i < backPane.count(); i++) {
            backPane.getWorkPane(i).getReportPage().setFooter(null);
        }
        status = NORMAL;
        mousePressed(0, 0);
        backPane.repaint();
    }

    public void addHeaderLine(BackPane backPane, boolean b) {
        setCurrentPane(backPane.getWorkPane(currentPage));
        workPane.getReportPage().setHaveHeaderLine(b);
        backPane.repaint();
    }

    public void addFooterLine(BackPane backPane, boolean b) {
        setCurrentPane(backPane.getWorkPane(currentPage));
        workPane.getReportPage().setHaveFooterLine(b);
        backPane.repaint();
    }

    public boolean getHeaderLine(BackPane backPane) {
        return backPane.getWorkPane(currentPage).getReportPage().getHaveHeaderLine();
    }

    public boolean getFooterLine(BackPane backPane) {
        return backPane.getWorkPane(currentPage).getReportPage().getHaveFooterLine();
    }

    public boolean getHaveHeader(BackPane backPane) {
        return (backPane.getWorkPane(currentPage).getReportPage().getHeader() != null);
    }

    public boolean getHaveFooter(BackPane backPane) {
        return (backPane.getWorkPane(currentPage).getReportPage().getFooter() != null);
    }

}
