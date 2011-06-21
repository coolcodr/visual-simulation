package chart;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

public class BarChartMouseAdapter extends MouseAdapter implements MouseMotionListener, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2608999754547459764L;
    private BarChart _target;

    public BarChartMouseAdapter(BarChart target) {
        _target = target;
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered " + e);
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited " + e);
    }

    public void mouseDragged(MouseEvent e) {
        // do nothing
    }

    public void mouseMoved(MouseEvent e) {
        int index = _target.calSampleValuesIndex(e.getX());

        if (index >= 0) {
            // System.out.println("Mouse moved "+ e);
            SampleValue currentSampleValue = ((Presentation) _target).getChart().getSampleValue(index);
            _target.setMouseOnBarLabel(currentSampleValue.getValue() + " " + currentSampleValue.getUnit());
            _target.repaint();
        } else {
            _target.setMouseOnBarLabel("");
            _target.repaint();
        }
    }

}
