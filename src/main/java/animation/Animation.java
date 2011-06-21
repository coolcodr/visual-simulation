package animation;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;

import javax.swing.JComponent;

import mcomponent.distribution.Entity;

public class Animation extends JComponent implements Runnable {

    /**
     * 
     */
    private static final long serialVersionUID = 3470106826118076846L;
    static final int REFRESH_TIME = 20;
    int start_x, start_y, stop_x, stop_y;
    int current_x, current_y, speed;
    long lineDistance;
    boolean finish;
    boolean start;
    Vector points;
    Entity _entity;

    public Animation() {
        Vector points = new Vector();
        this.setSize(new Dimension(10, 10));
        setStartStatus(false);
        finish = false;
    }

    public Animation(Entity entity) {
        Vector points = new Vector();
        this.setSize(new Dimension(10, 10));
        setStartStatus(false);
        finish = false;
        setEntity(entity);
    }

    public void setPoints(Vector points) {
        this.points = points;
    }

    public void setPath(int start_x, int start_y, int stop_x, int stop_y) {
        this.start_x = start_x;
        this.start_y = start_y;
        this.stop_x = stop_x;
        this.stop_y = stop_y;
        current_x = start_x;
        current_y = start_y;
        lineDistance = getLineDistance();
    }

    public void run() {
        int i = 0;
        boolean pleaseStop = false;
        int index = 0;
        try {
            setPath(((Point) points.elementAt(index)).x, ((Point) points.elementAt(index)).y, ((Point) points.elementAt(index + 1)).x, ((Point) points.elementAt(index + 1)).y);
        } catch (ArrayIndexOutOfBoundsException e) {
//			System.out.println("Animation path didn't set properly, or animation disabled.");
            return;
        }
        setStartStatus(true);
        _entity.lock();
        while (!pleaseStop) {

            setLocation(current_x - (size().width / 2), current_y - (size().height / 2));
            setVisible(true);
//			repaint();
            i = i + speed;
            current_x = (int) (start_x + ((double) i / (double) lineDistance) * ((double) stop_x - (double) start_x));
            current_y = (int) (start_y + ((double) i / (double) lineDistance) * ((double) stop_y - (double) start_y));
            if (((stop_y > start_y) && (stop_x > start_x) && (current_x > stop_x)) || ((stop_y > start_y) && (stop_x > start_x) && (current_y > stop_y)) || ((stop_y < start_y) && (stop_x < start_x) && (current_x < stop_x)) || ((stop_y < start_y) && (stop_x < start_x) && (current_y < stop_y)) || ((stop_y > start_y) && (stop_x < start_x) && (current_x < stop_x)) || ((stop_y > start_y) && (stop_x < start_x) && (current_y > stop_y)) || ((stop_y < start_y) && (stop_x > start_x) && (current_x > stop_x)) || ((stop_y < start_y) && (stop_x > start_x) && (current_y < stop_y)) || (i >= lineDistance)) {
                if (index < points.size() - 2) {
                    index++;
                    setPath(((Point) points.elementAt(index)).x, ((Point) points.elementAt(index)).y, ((Point) points.elementAt(index + 1)).x, ((Point) points.elementAt(index + 1)).y);
                    i = 0;
                } else {
                    setVisible(false);
                    setStartStatus(false);
                    getParent().remove(this);
                    pleaseStop = true;
                    finish = true;
                    _entity.unlock();
                }
            }
            try {
                Thread.sleep(REFRESH_TIME);
            } catch (InterruptedException e) {
            }
        }
    }

    public void paintComponent(Graphics g) {
        if (getStartStatus()) {
            setVisible(true);
            g.fillOval(0, 0, 10, 10);
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setStartStatus(boolean start) {
        this.start = true;
    }

    public boolean getStartStatus() {
        return start;
    }

    private long getLineDistance() {
        Point p = new Point();
        p.x = start_x;
        p.y = start_y;
        return Math.round(p.distance(stop_x, stop_y));
    }

    public static int getREFRESHTIME() {
        return REFRESH_TIME;
    }

    public void setEntity(Entity entity) {
        _entity = entity;
    }

//added
    static boolean _stop = false;

    public static void setStop(boolean stop) {
        _stop = stop;
    }

    public static boolean getStop() {
        return _stop;
    }
}
