package diagram;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class RunThread extends Thread {
    private Diagram _diagram;

    public RunThread(Diagram diagram) {
        super("Run Thread");
        setPriority(MAX_PRIORITY);
        _diagram = diagram;
    }

    public void run() {
        try {
            Runtime _runtime = Runtime.getRuntime();
            Process _process = _runtime.exec("java Main");

            JDialog _progressDialog = new JDialog();
            _progressDialog.setVisible(true);
            _progressDialog.getContentPane().setLayout(new BorderLayout());
            _progressDialog.setSize(450, 130);
            _progressDialog.setLocation(450, 400);
            JLabel _progressLabel = new JLabel("Simulation in progress....", new ImageIcon("diagram/images/arrow15.GIF"), SwingConstants.HORIZONTAL);
            JLabel _progressLabel2 = new JLabel("Simulation Complete");
            _progressDialog.getContentPane().add(_progressLabel, BorderLayout.CENTER);
            // _progressLabel.setVisible(false);
            _progressLabel.repaint();
            _progressLabel.setVisible(true);
            _diagram.repaint();
            _progressDialog.repaint();

            BufferedReader reader;
            String string = "";
            while (!string.equalsIgnoreCase("end of simulation")) {
                reader = new BufferedReader(new InputStreamReader(_process.getInputStream()));
                string = reader.readLine();
            }
            _progressDialog.hide();
            _progressLabel.setVisible(false);
            _progressDialog.getContentPane().add(_progressLabel2, BorderLayout.CENTER);
            _progressDialog.show();
            _progressDialog.hide();
            // JOptionPane.showMessageDialog(_diagram, "simulation completed",
            // "run", JOptionPane.PLAIN_MESSAGE );
        } catch (Exception eee) {
            System.out.println("eee" + eee);
        }

        System.out.println("run complete");
        setPriority(MIN_PRIORITY);

    }
}
