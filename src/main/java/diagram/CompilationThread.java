package diagram;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class CompilationThread extends Thread {
    Process _process;
    Diagram _diagram;

    public CompilationThread(Diagram diagram) {
        super("Compilation Thread");
        setPriority(MAX_PRIORITY);
        _diagram = diagram;
    }

    public void run() {
        try {
            Runtime _runtime = Runtime.getRuntime();
            _process = _runtime.exec("javac -O Main.java");
            JDialog _progressDialog = new JDialog();
            _progressDialog.setVisible(true);
            _progressDialog.getContentPane().setLayout(new BorderLayout());
            _progressDialog.setSize(450, 130);
            _progressDialog.setLocation(450, 400);
            JLabel _progressLabel = new JLabel("Compilation in progress....", new ImageIcon("diagram/images/bullet.GIF"), SwingConstants.HORIZONTAL);
            _progressDialog.getContentPane().add(_progressLabel, BorderLayout.CENTER);
            // _progressLabel.setVisible(false);
            _progressLabel.repaint();
            _progressLabel.setVisible(true);
            _progressDialog.repaint();
            _diagram.repaint();

            _process.waitFor();
            _progressDialog.hide();
            if (_process.exitValue() == 0) {
                JOptionPane.showMessageDialog(_diagram, "Compilation success", "Compile", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(_diagram, "Invalid model or Missing Properties", "Model error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception eee) {
            System.out.println("eee" + eee);
        }
        setPriority(MIN_PRIORITY);

    }
}
