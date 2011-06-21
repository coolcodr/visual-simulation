package diagram;

import javax.swing.*;
import java.io.*;
import java.awt.*;

public class CompilationFileThread extends Thread
{
    Process _process;
    JButton _play;
    String _fileName;
    Frame _frame;
    public CompilationFileThread(Frame frame, JButton play, String fileName)
    {
        super("Compilation Thread");
        setPriority(MAX_PRIORITY);
        _play = play;
        _fileName = fileName;
        _frame = frame;
	}

	public void run()
	{
		try {
			JDialog _progressDialog = new JDialog();
			_progressDialog.getContentPane().setLayout(new BorderLayout());
			_progressDialog.setSize(450,130);
			_progressDialog.setLocation(400,300);
			JLabel _progressLabel = new JLabel( "Compilation in progress....", SwingConstants.HORIZONTAL);
			_progressDialog.getContentPane().add(_progressLabel, BorderLayout.CENTER);
			_progressLabel.repaint();
			_progressDialog.repaint();
			_progressLabel.setVisible(true);
			_progressDialog.setVisible(true);
			Runtime _runtime = Runtime.getRuntime();
			_process = _runtime.exec("C:/j2sdk1.4.1_01/bin/javac -classpath E:/IVE 2003/vSim/classes;C:/j2sdk1.4.1_01/jre/lib/rt.jar;C:/j2sdk1.4.1_01/lib/dt.jar;C:/j2sdk1.4.1_01/lib/tools.jar;C:/j2sdk1.4.1_01/jre/lib/ext/dnsns.jar;C:/j2sdk1.4.1_01/jre/lib/ext/ldapsec.jar;C:/j2sdk1.4.1_01/jre/lib/ext/localedata.jar;C:/j2sdk1.4.1_01/jre/lib/ext/sunjce_provider.jar;C:/j2sdk1.4.1_01/jre/lib/ext/jmf.jar;C:/j2sdk1.4.1_01/jre/lib/ext/sound.jar -O " + _fileName);
			_process.waitFor();

			if(_process.exitValue()==0) {
				JOptionPane.showMessageDialog(_frame, "Compilation success", "Compile", JOptionPane.PLAIN_MESSAGE );
				_play.setEnabled(true);
			}
			else {
				JOptionPane.showMessageDialog(_frame, "Invalid model or Missing Properties", "Model error", JOptionPane.ERROR_MESSAGE);
				_play.setEnabled(false);
			}

		}
		catch(Exception eee){System.out.println("eee"+eee);}
		setPriority(MIN_PRIORITY);

	}
}