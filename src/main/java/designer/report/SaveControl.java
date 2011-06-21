package designer.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import print.ReportDocument;

public class SaveControl {
    private ReportDocument reportDocument;

    public SaveControl() {
        this(null);
    }

    public SaveControl(ReportDocument reportDocument) {
        this.reportDocument = reportDocument;
    }

    public void setReportDocument(ReportDocument reportDocument) {
        this.reportDocument = reportDocument;
    }

    public void saveAs() {

    }

    public void save() {
        if (reportDocument == null) {
            return;
        }

        reportDocument.prepareImage();
        // TEMP
        try {
            File outFile = new File("C:/temp_report.vsr");
            FileOutputStream outFileStream = new FileOutputStream(outFile);
            ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);

            outObjectStream.writeObject(reportDocument);
            outObjectStream.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        reportDocument.restoreImage();

    }

    public ReportDocument load() {
        ReportDocument reportDocument = null;
        // TEMP
        try {
            File inFile = new File("C:/temp_report.vsr");
            FileInputStream inFileStream = new FileInputStream(inFile);
            ObjectInputStream inObjectStream = new ObjectInputStream(inFileStream);

            reportDocument = (ReportDocument) inObjectStream.readObject();
            inObjectStream.close();
        }

        catch (Exception e) {
            System.out.println(e);
        }

        return reportDocument;

    }
}
