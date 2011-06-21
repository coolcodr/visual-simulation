import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import designer.deployment.MainFrame;

public class RunSim {
    public static void main(String args[]) {
        try {
            String lnfName = "light.LightLookAndFeel";
            UIManager.setLookAndFeel(lnfName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainFrame mainFrame;

        if (args.length > 0) {
            mainFrame = new MainFrame(args[0], true);
        } else {
            mainFrame = new MainFrame(true);
        }

        mainFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }
}
