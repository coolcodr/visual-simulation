package designer.report;

import java.awt.print.Book;
import java.awt.print.PrinterJob;

import print.ReportDocument;

/**
 * <p>
 * Title: Print Editor
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author unascribed
 * @version 1.0
 */

public class ControlPrint {
    private PrinterJob printJob;
    private Book book;
    private PageFormat pageFormat;
    private PageFormat multiPageFormat;

    public ControlPrint() {
        printJob = PrinterJob.getPrinterJob();
        pageFormat = new PageFormat();

    }

    public void printDocument(ReportDocument reportDocument) {
        book = new Book();

        reportDocument.refresh();

        for (int i = 0; i < reportDocument.getNumOfPage(); i++) {
            book.append(reportDocument.getReportMultiPage(i), reportDocument.getMultiPageFormat(), 1);
        }

        printJob.setPageable(book);

        if (printJob.printDialog()) {
            try {
                printJob.print();
            } catch (Exception PrintException) {
                PrintException.printStackTrace();
            }
        }

    }
}
