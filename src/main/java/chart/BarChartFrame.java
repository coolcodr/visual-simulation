package chart;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BarChartFrame extends JFrame implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = -2082257694835521130L;
    private Chart myChart;
    private JTextField pointText;
    private JTextField pointLabelText;

    private JTextField xUnitText;
    private JTextField yUnitText;

    public BarChartFrame() {

        setTitle("Chart Prototype");
        Container c = getContentPane();
        myChart = new chart.Chart("BarChart");

        // scroll = new JScrollPane(myChart);
        // scroll.setPreferredSize( new Dimension(600, 450) );

        // p.add(myChart);
        c.setLayout(new FlowLayout());
        c.setBackground(Color.lightGray);
        myChart.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        c.add(myChart);

        myChart.setPreferredSize(new Dimension(700, 550));
        myChart.setBounds(0, 0, 700, 550);
        myChart.setLocation(0, 0);

        BarChart barChartPresentation = new BarChart(myChart);
        myChart.setPresentation(barChartPresentation);

        JPanel newPointPanel = new JPanel();
        JButton but3 = new JButton("Add Point");
        but3.addActionListener(this);
        pointText = new JTextField();
        pointText.setColumns(10);
        pointLabelText = new JTextField();
        pointLabelText.setColumns(10);

        newPointPanel.add(but3);
        newPointPanel.add(pointText);
        newPointPanel.add(pointLabelText);
        c.add(newPointPanel);

        JPanel propertyPanel = new JPanel();
        JButton but4 = new JButton("Change property");
        but4.addActionListener(this);
        xUnitText = new JTextField();
        xUnitText.setColumns(10);
        yUnitText = new JTextField();
        yUnitText.setColumns(10);

        propertyPanel.add(but4);
        propertyPanel.add(xUnitText);
        propertyPanel.add(yUnitText);
        c.add(propertyPanel);

        // c.add(myChart);
        // c = new simChart.LineChart();

        // setLayout(new BorderLayout());
        // setContentPane(c);
        // add(c,BorderLayout.CENTER);
        // c.setLocation(100,100);
        setSize(new Dimension(750, 650));
        setBackground(Color.white);

        setVisible(false);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Point")) {
            String newTxtValue = pointText.getText();
            double newValue;
            try {
                newValue = Double.parseDouble(newTxtValue);
            } catch (NumberFormatException err) {
                pointText.setText("");
                return;
            }

            if (newValue >= 0) {
                myChart.addSampleValue(newValue, pointLabelText.getText());
                System.out.println("actionPerformed add point");
            }
        } else if (e.getActionCommand().equals("Change property")) {
            myChart.setXAxisUnit(xUnitText.getText());
            myChart.setYAxisUnit(yUnitText.getText());
            myChart.repaint();
        }
    }

}
