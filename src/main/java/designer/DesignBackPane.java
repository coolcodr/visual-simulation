package designer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import util.ImageIconHelper;

public class DesignBackPane extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -4247527644675892240L;
    private DesignPane designPane;
    private JPanel backPanel;
    private JPanel bottomPanel;
    private JPanel workPanel;
    private boolean haveParent = false;
    private CardPane cardPane = null;
    private JLabel cardLabel;
    private CardBounds cardBounds;
    private JScrollPane jScrollPane;

    private DesignerComponentButton jToggleButton1 = new DesignerComponentButton(CLabel.class);
    private DesignerComponentButton jToggleButton2 = new DesignerComponentButton(CButton.class);
    private DesignerComponentButton jToggleButton3 = new DesignerComponentButton(TitlePane.class);
    private DesignerComponentButton jToggleButton4 = new DesignerComponentButton(CardPane.class);
    private JToggleButton jButton37 = new JToggleButton(new ImageIconHelper().getImageIcon("/designer/images/Pointer16.gif"));
    private JLabel jLabel6 = new JLabel();
    private JLabel titleLabel = new JLabel();

    public DesignBackPane(DesignPane designPane) {
        this.designPane = designPane;
        setName(designPane.toString());

        jToggleButton1.setFont(new java.awt.Font("Dialog", 0, 12));
        jToggleButton1.setText("Label");
        jToggleButton1.setFocusPainted(false);
        jToggleButton1.setBounds(new Rectangle(135, 3, 72, 24));
        jToggleButton1.setVisible(true);
        jToggleButton2.setFont(new java.awt.Font("Dialog", 0, 12));
        jToggleButton2.setFocusPainted(false);
        jToggleButton2.setText("Button");
        jToggleButton2.setVisible(true);
        jToggleButton2.setBounds(new Rectangle(209, 3, 70, 24));
        jToggleButton3.setFont(new java.awt.Font("Dialog", 0, 12));
        jToggleButton3.setText("Titled Panel");
        jToggleButton3.setBounds(new Rectangle(281, 3, 100, 24));
        jToggleButton3.setFocusPainted(false);
        jToggleButton3.setVisible(true);
        jToggleButton4.setFont(new java.awt.Font("Dialog", 0, 12));
        jToggleButton4.setText("Card Panel");
        jToggleButton4.setFocusPainted(false);
        jToggleButton4.setBounds(new Rectangle(383, 3, 100, 24));
        jToggleButton4.setVisible(true);
        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel6.setText("Add Component:");
        jLabel6.setBounds(new Rectangle(36, 3, 102, 24));
        jLabel6.setVisible(true);
        jButton37.setBounds(new Rectangle(4, 3, 24, 24));
        jButton37.setVisible(true);
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton37_actionPerformed(e);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jToggleButton1_actionPerformed(e);
            }
        });
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jToggleButton2_actionPerformed(e);
            }
        });
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jToggleButton3_actionPerformed(e);
            }
        });
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jToggleButton4_actionPerformed(e);
            }
        });

        backPanel = new JPanel();

        // this.setBorder(BorderFactory.createLoweredBevelBorder());
        setBorder(null);
        // this.setViewportView(backPanel);
        setVisible(true);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setVisible(true);
        topPanel.setPreferredSize(new Dimension(100, 30));
        topPanel.setBorder(null);
        topPanel.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(100, 30));

        JPanel textPanel = new JPanel();
        JButton borderPanel = new JButton();

        borderPanel.setVisible(true);
        borderPanel.setPreferredSize(new Dimension(100, 1));
        borderPanel.setBorder(BorderFactory.createLineBorder(Color.gray));

        textPanel.setVisible(true);
        textPanel.setBorder(null);
        textPanel.setLayout(new BorderLayout());

        titleLabel.setText("  Frame Title: " + designPane.getTitle());
        titleLabel.setFont(new java.awt.Font("Dialog", 0, 12));
        titleLabel.setVisible(true);
        titleLabel.setPreferredSize(new Dimension(100, 24));

        textPanel.add(titleLabel, BorderLayout.CENTER);
        textPanel.add(borderPanel, BorderLayout.SOUTH);
        textPanel.setPreferredSize(new Dimension(100, 25));

        // this.add(titlePanel, BorderLayout.NORTH);
        this.add(topPanel, BorderLayout.NORTH);
        topPanel.add(titlePanel, BorderLayout.NORTH);

        titlePanel.setVisible(true);
        titlePanel.setBorder(null);
        titlePanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setVisible(true);
        buttonPanel.setLayout(null);
        buttonPanel.add(jToggleButton1);
        buttonPanel.add(jToggleButton2);
        buttonPanel.add(jToggleButton3);
        buttonPanel.add(jToggleButton4);
        buttonPanel.add(jLabel6);
        buttonPanel.add(jButton37);

        // TEMP
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(25, 25));
        JButton button = new JButton(new ImageIconHelper().getImageIcon("/designer/images/close.gif"));

        // titlePanel.add(panel, BorderLayout.EAST);
        textPanel.add(panel, BorderLayout.EAST);
        titlePanel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(button, BorderLayout.CENTER);

        button.setPreferredSize(new Dimension(15, 15));
        button.setBounds(5, 5, 15, 15);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_actionPerformed(e);
            }
        });

        // TEMP
        bottomPanel = new JPanel();
        cardLabel = new JLabel();
        cardBounds = new CardBounds();
        cardBounds.setVisible(true);

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        mainPanel.setVisible(true);

        jScrollPane = new JScrollPane(bottomPanel);
        jScrollPane.setVisible(true);
        jScrollPane.setBorder(null);

        // bottomPanel.setPreferredSize(new Dimension(2000,2000));//TEMP

        // this.add(bottomPanel, BorderLayout.CENTER);
        // add( jScrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);
        mainPanel.add(textPanel, BorderLayout.NORTH);
        mainPanel.add(jScrollPane, BorderLayout.CENTER);

        bottomPanel.setVisible(true);
        bottomPanel.setBorder(null);// BorderFactory.createLoweredBevelBorder());
        bottomPanel.setLayout(null);

        bottomPanel.addMouseListener(new PaneControl(designPane));

        // System.out.println("TEST PARENT 1: "+designPane.getParent());
        workPanel = new JPanel();
        workPanel.setVisible(true);
        workPanel.setLayout(null);
        workPanel.setBorder(null);
        workPanel.setBounds(10, 10, 5000, 5000);
        workPanel.setBackground(new JDesktopPane().getBackground());
        bottomPanel.add(workPanel);
        // bottomPanel.add(designPane);
        workPanel.add(designPane);
        workPanel.setOpaque(false);
        // System.out.println("TEST PARENT 2: "+designPane.getParent());

        bottomPanel.add(cardLabel);
        bottomPanel.add(cardBounds);

        designPane.setVisible(true);
        repaint();
        designPane.repaint();

        bottomPanel.setBackground(new JDesktopPane().getBackground());
        bottomPanel.setVisible(true);

        cardLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        // cardLabel.setBackground(Color.gray);
        // cardLabel.setOpaque(true);
        cardLabel.setForeground(Color.gray);
        cardLabel.setVisible(true);

        restoreButtonStatus();
    }

    public DesignPane getDesignPane() {
        return designPane;
    }

    public void refreshTitle() {
        titleLabel.setText("  Frame Title: " + designPane.getTitle());
        titleLabel.repaint();
    }

    public void restoreButtonStatus() {
        jButton37.setSelected(true);
        jToggleButton1.setSelected(false);
        jToggleButton2.setSelected(false);
        jToggleButton3.setSelected(false);
        jToggleButton4.setSelected(false);
    }

    void jButton37_actionPerformed(ActionEvent e) {
        restoreButtonStatus();
        designPane.getUpperPane().resetControl();
    }

    void jToggleButton1_actionPerformed(ActionEvent e) {
        designPane.getUpperPane().resetControl();
        jButton37.setSelected(false);
        jToggleButton1.setSelected(true);
        jToggleButton2.setSelected(false);
        jToggleButton3.setSelected(false);
        jToggleButton4.setSelected(false);
    }

    void jToggleButton2_actionPerformed(ActionEvent e) {
        designPane.getUpperPane().resetControl();
        jButton37.setSelected(false);
        jToggleButton1.setSelected(false);
        jToggleButton2.setSelected(true);
        jToggleButton3.setSelected(false);
        jToggleButton4.setSelected(false);
    }

    void jToggleButton3_actionPerformed(ActionEvent e) {
        designPane.getUpperPane().resetControl();
        jButton37.setSelected(false);
        jToggleButton1.setSelected(false);
        jToggleButton2.setSelected(false);
        jToggleButton3.setSelected(true);
        jToggleButton4.setSelected(false);
    }

    void jToggleButton4_actionPerformed(ActionEvent e) {
        designPane.getUpperPane().resetControl();
        jButton37.setSelected(false);
        jToggleButton1.setSelected(false);
        jToggleButton2.setSelected(false);
        jToggleButton3.setSelected(false);
        jToggleButton4.setSelected(true);
    }

    public void refresh() {
        // cardPane = null;
        // haveParent = false;

        // if ( designPane.getParent() instanceof CardPane )
        // {
        // haveParent = true;
        // cardPane = (CardPane)designPane.getParent();
        // }

        if (designPane.getParent() != null) {
            designPane.getParent().remove(designPane);
        }

        workPanel.add(designPane);
        designPane.setBackPane(workPanel, this);
        workPanel.setSize(designPane.getWidth(), designPane.getHeight());
        designPane.setBounds(designPane.getBounds().x, designPane.getBounds().y, designPane.getBounds().width, designPane.getBounds().height);

        if (designPane.getParentCardPane() != null) {
            CardPane cardPane = designPane.getParentCardPane();
            Dimension d = cardPane.getSize();
            int height = cardPane.getText().equalsIgnoreCase("") ? 0 : 19;
            cardLabel.setText("Card Pane - " + cardPane.toString());
            cardLabel.setBounds(10, 3 + d.height - height, d.width + 1, 16);
            cardLabel.setVisible(true);
        } else {
            cardLabel.setVisible(false);
        }
        repaint();
        // designPane.setLocation(20, 20);
        /*
         * JPanel panel = designPane.upperPane;
         * designPane.setSize(panel.getSize().width, panel.getSize().height);
         * designPane.setVisible(true);
         */
    }

    public void paint(Graphics g) {
        if (designPane.getParentCardPane() != null) {
            CardPane cardPane = designPane.getParentCardPane();
            int height = cardPane.getText().equalsIgnoreCase("") ? 0 : 19;
            cardBounds.setBounds(6, 6, cardPane.getSize().width - 6, cardPane.getSize().height - 6 - height);
            cardBounds.setVisible(true);
        } else {
            cardBounds.setVisible(false);
        }
        super.paint(g);
        // Graphics g2 = bottomPanel.getGraphics();
        /*
         * if ( designPane.getParentCardPane() != null ) { CardPane cardPane =
         * designPane.getParentCardPane(); Dimension d = cardPane.getSize();
         * //g.drawRect(10, 35, d.width, d.height); //Font font = new
         * Font("Dialog", Font.BOLD, 12); //int width =
         * g.getFontMetrics(font).stringWidth
         * ("[Card Pane] "+cardPane.toString()); //g.setFont(font); //int x = 10
         * + d.width - width - 5; //int y = 35 + d.height - 5;
         * //g.setColor(Color.lightGray); //g.fillRect(x - 5, y - 15, width +
         * 10, 20 ); //g.setColor(Color.white);
         * //g.drawString("[Card Pane] "+cardPane.toString(), x, y); }
         */
        // g.drawRect(5, 5, 200, 200);
    }

    void jButton_actionPerformed(ActionEvent e) {
        UIDesigner.workPaneTab.remove(this);
    }
}

class CardBounds extends JComponent {
    /**
     * 
     */
    private static final long serialVersionUID = -6186453490303167998L;

    public void CardBounds() {
    }

    public void paint(Graphics g) {
        Dimension d = getSize();
        g.setColor(Color.gray);

        int size = 8;

        for (int j = 4; j <= 4; j += 4) {
            for (int i = 0; i < d.width - j * 2; i = i + size * 2) {
                g.drawLine(j + i, j - 1, j + i + size < d.width + 0 - j * 2 ? j + i + size : d.width + 0 - j * 2, j - 1);
                g.drawLine(j + i, d.height - 0 - j, j + i + size < d.width + 0 - j * 2 ? j + i + size : d.width + 0 - j * 2, 0 + d.height - 0 - j);
            }

            for (int i = 0; i < d.height - j * 2; i = i + size * 2) {
                g.drawLine(j - 1, j + i, j - 1, j + i + size < d.height + 0 - j * 2 ? j + i + size : d.height + 0 - j * 2);
                g.drawLine(1 + d.width - 1 - j, j + i, 1 + d.width - 1 - j, j + i + size < d.height + 0 - j * 2 ? j + i + size : d.height + 0 - j * 2);
            }
        }
        // g.setColor(Color.lightGray);
        // g.drawRect(4, 4, d.width - 9, d.height - 9);
    }
}

class PaneControl extends MouseAdapter implements MouseListener {
    private DesignPane designPane;

    public PaneControl(DesignPane designPane) {
        this.designPane = designPane;
    }

    public void mousePressed(MouseEvent e) {
        designPane.deselectAll();
        DesignerControl.removePropertiesTable();
    }
}
