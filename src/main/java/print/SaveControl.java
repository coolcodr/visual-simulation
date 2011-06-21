package print;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

public class SaveControl {
    private ReportDocument reportDocument;
    private String currentPath = null;

    public SaveControl() {
        this(null);
    }

    public SaveControl(ReportDocument reportDocument) {
        this.reportDocument = reportDocument;
    }

    public void setReportDocument(ReportDocument reportDocument) {
        this.reportDocument = reportDocument;
    }

    public void saveAs(Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new ReportFileFilter());
        fileChooser.showSaveDialog(parent);
        File file = fileChooser.getSelectedFile();
        String path = "";
        String name;

        if (file != null) {
            if (file.isFile()) {
                path = file.getAbsolutePath();
            } else {
                path = file.getAbsolutePath() + ".vsr";
            }
            currentPath = path;
            save(parent);
        } else {
            return;
        }

    }

    public void save(Component parent) {
        if (reportDocument == null) {
            return;
        }
        if (currentPath == null) {
            saveAs(parent);
        }
        reportDocument.prepareImage();
        // TEMP
        try {
            File outFile = new File(currentPath);
            FileOutputStream outFileStream = new FileOutputStream(outFile);
            ObjectOutputStream outObjectStream = new ObjectOutputStream(outFileStream);

            outObjectStream.writeObject(reportDocument);
            outObjectStream.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        reportDocument.restoreImage();

    }

    public ReportDocument load(Component parent) {
        ReportDocument reportDocument = null;

        JFileChooser fc = new JFileChooser();
        fc.addChoosableFileFilter(new ReportFileFilter());
        fc.showOpenDialog(parent);
        File file = fc.getSelectedFile();

        if (file.isFile()) {
            currentPath = file.getAbsolutePath();
        }

        try {
            File inFile = new File(currentPath);
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
