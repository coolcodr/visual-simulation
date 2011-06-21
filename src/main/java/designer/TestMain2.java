package designer;

//=======================================================//

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//=======================================================//
public class TestMain2 {
    public static void main(String[] args) {
        GUI gui = new GUI();
    } // end main
} // end class SampProg138

//=======================================================//

class GUI {

    public GUI() { // constructor

        // Build an active card.

        Label outputLabel = new Label("     ");
        outputLabel.setBackground(Color.green);
        Button setOutputButton = new Button("Display Output");
        Button clearOutputButton = new Button("Clear Output");
        Panel outputPanel = new Panel();
        GridLayout outputLayout = new GridLayout(3, 1);
        outputLayout.setVgap(5);
        outputPanel.setLayout(outputLayout);
        outputPanel.add(setOutputButton);
        outputPanel.add(clearOutputButton);
        outputPanel.add(outputLabel);
        outputPanel.setName("Output");

        // Instantiate ActionListener objects and register them
        // on the set and clear buttons. These event handlers
        // will display the output or clear the display.
        setOutputButton.addActionListener(new SetOutputActionListener());
        clearOutputButton.addActionListener(new ClrOutputActionListener());
        // ===================================================//

        // ===Build a display panel for the deck of cards===

        // Instantiate a layout manager object to be used with
        // a Panel object
        CardLayout myCardLayout = new CardLayout();

        // Instantiate a display Panel object that will be
        // composited onto a Frame object.
        Panel displayPanel = new Panel();

        // Specify a CardLayout manager for the Panel object
        displayPanel.setLayout(myCardLayout);
        // make the display panel visible
        displayPanel.setBackground(Color.red);

        // Add objects to the display panel using the specified
        // CardLayout manager
        displayPanel.add(new Label("First Card"), "first");
        displayPanel.add(outputPanel, "output panel");
        displayPanel.add(new Label("Last Card"), "last");

        // ======== Build the control panel ======//

        // Instantiate button objects that will be used to
        // cycle through the cards in the deck.
        Button firstButton = new Button("Show First Card");
        Button nextButton = new Button("Show Next Card");

        // Instantiate action listener objects and register on
        // the buttons on the control panel
        firstButton.addActionListener(new FirstListener(myCardLayout, displayPanel));
        nextButton.addActionListener(new NextListener(myCardLayout, displayPanel));

        // Instantiate a control Panel object and place the
        // Button objects on it.
        Panel controlPanel = new Panel();
        BorderLayout controlLayout = new BorderLayout();
        controlLayout.setVgap(5);
        controlPanel.setLayout(controlLayout);
        controlPanel.add(firstButton, "North");
        controlPanel.add(nextButton, "South");
        controlPanel.setBackground(Color.yellow);

        // == Build the Top-Level User-Interface Object ==

        // Instantiate a Frame object
        Frame myFrame = new Frame("Copyright 1997, R.G.Baldwin");

        // Add the display panel and the control panel objects
        // to the Frame object to create the composite
        // user-interface object.
        myFrame.add(displayPanel, "North");
        myFrame.add(controlPanel, "South");

        myFrame.setSize(350, 200); // set the size
        myFrame.setVisible(true); // make it visible

        // Instantiate and register a window listener to
        // terminate the program when the Frame is closed.
        myFrame.addWindowListener(new Terminate());
    } // end constructor
} // end class GUI definition

//=======================================================//

//An object of this ActionListener class is registered on
// a Button object on a Panel object.  When an event
// occurs, this handler modifies the text in a Label object
// on the same panel.
class SetOutputActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // Construct an array of the components on the
        // panel that is the parent object of the source
        // of the event. Note the requirement for casting.
        Component[] comps = ((Component) e.getSource()).getParent().getComponents();

        // Identify the Label component in the array and
        // modify its text.
        for (int cnt = 0; cnt < comps.length; cnt++) {
            if (comps[cnt].toString().indexOf("Label") != -1) {
                ((Label) comps[cnt]).setText("Ouch");
            }
        } // end for loop
    } // end actionPerformed()
} // end class SetOutputActionListener

//=======================================================//
//An object of this ActionListener class is registered on
// a Button object on a Panel object.  When an event
// occurs, this handler clears the text in a Label object
// on the same panel. This ActionListener is used to clear
// the names.

class ClrOutputActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // Construct an array of the components on the
        // panel that is the parent object of the source
        // of the event. Note the requirement for casting.
        Component[] comps = ((Component) e.getSource()).getParent().getComponents();

        // Identify the Label component in the array and
        // modify its text.
        for (int cnt = 0; cnt < comps.length; cnt++) {
            if (comps[cnt].toString().indexOf("Label") != -1) {
                ((Label) comps[cnt]).setText("");
            }
        } // end for loop
    } // end actionPerformed()
} // end class ClrOutputActionListener

//=======================================================//

//Objects of the next two ActionListener classes are
// registered on the Button objects on the control panel.
// When an event occurs, the event handler causes a card
// in the deck of cards to be displayed on the display
// panel.  For example, an object of the following class
// causes the first card in the deck to be displayed.  All
// of the classes are very similar, differing only by
// one statement in the overridden ActionPerformed
// method which specifies the action to be taken.

class FirstListener implements ActionListener {
    Panel myPanelObject;
    CardLayout myCardLayoutObject;

    // constructor
    FirstListener(CardLayout inCardLayout, Panel inPanel) {
        myCardLayoutObject = inCardLayout;
        myPanelObject = inPanel;
    } // end constructor

    public void actionPerformed(ActionEvent e) {
        myCardLayoutObject.first(myPanelObject);
    } // end actionPerformed()
} // end class NextListener

//=======================================================//

//See comments above in class FirstListener
class NextListener implements ActionListener {
    Panel myPanelObject;
    CardLayout myCardLayoutObject;

    // constructor
    NextListener(CardLayout inCardLayout, Panel inPanel) {
        myCardLayoutObject = inCardLayout;
        myPanelObject = inPanel;
    } // end constructor

    public void actionPerformed(ActionEvent e) {
        myCardLayoutObject.next(myPanelObject);
    } // end actionPerformed()
} // end class NextListener

//=======================================================//

class Terminate extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        // terminate the program when the window is closed
        System.exit(0);
    } // end windowClosing
} // end class Terminate
