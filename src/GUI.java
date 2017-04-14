import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;

public class GUI extends JFrame implements ActionListener, MouseListener {

    Game g = new Game();

    private double ver = 0.09;
    private boolean ingame = false;

    // Buttons
    private JButton bStart, bEditor, bInfo;

    // TextPane
    private JTextPane tpDialog;

    // Labels
    private JLabel lChar1, background;

    // Pane
    private JLayeredPane layeredPane;


    // common GUI
    public GUI(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(960, 720);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.pink);

        // layeredpane for zindex
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(960, 720));
        layeredPane.setBounds(0,0,960,720);
        this.add(layeredPane);

        // create menu buttons
        bStart = new JButton ("Start");
        bStart.setBounds(180, 400, 200, 100);
        bStart.addActionListener(this);

        bEditor = new JButton ("Editor");
        bEditor.setBounds(380, 400, 200, 100);
        bEditor.addActionListener(this);

        bInfo = new JButton ("Info");
        bInfo.setBounds(580, 400, 200, 100);
        bInfo.addActionListener(this);

        // font
        Font f1 = new Font(Font.SANS_SERIF, Font.PLAIN, 25);
        Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 25);

        // create dialogbox
        tpDialog = new JTextPane();
        tpDialog.setBounds(50, 450, 850, 200);
        tpDialog.setForeground(Color.BLACK);
        tpDialog.setBackground(Color.WHITE);
        tpDialog.setEditable(false);
        tpDialog.setFont(f1);
        tpDialog.setMargin(new Insets(15,20,20,20));
        tpDialog.addMouseListener(this);

        // name labels
        lChar1 = new JLabel();
        lChar1.setBounds(60, 383, 200, 100);
        lChar1.setFont(f2);
        lChar1.setForeground(Color.WHITE);

        // background
        background = new JLabel();
        background.setBounds(50, 50, 200, 100);

        ingamegui();
    }

    // switch between ingame and menu GUI
    public void ingamegui() {
        // ingame GUI
        if (ingame){
            // set game name title
            this.setTitle(g.getgamename());

            // remove menu buttons
            layeredPane.remove(bStart);
            layeredPane.remove(bEditor);
            layeredPane.remove(bInfo);

            // add ingame elements
            //background with zindex 0 to be behind the buttons // DOESNT WORK FOR SOME REASON!
            layeredPane.add(background,new Integer(0));
            layeredPane.add(tpDialog,new Integer(1));
            layeredPane.add(lChar1,new Integer(1));
        }
        // menu GUI
        else {
            // remove ingame components
            layeredPane.remove(tpDialog);
            layeredPane.remove(lChar1);
            layeredPane.remove(background);

            // Menu title
            this.setTitle("lonegine");

            // add all the menu buttons
            layeredPane.add(bStart,new Integer(1));
            layeredPane.add(bEditor,new Integer(1));
            layeredPane.add(bInfo,new Integer(1));
        }
        // repaint after changing GUI
        this.repaint();
        // make things visible
        this.setVisible(true);
    }

	// button clicks
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.bStart){
		    ingame = true;
		    next();
            ingamegui();
        }
        else if(e.getSource() == this.bEditor){
            infobox("Sorry", "The Editor is not available yet.");
        }
        else if (e.getSource() == this.bInfo){
            infobox("Info", "lonegine (lonely engine) visual novel engine developed by Jen Stehlik. version: " + ver);
        }
    }

    // when clicked on dialogbox
    public void mouseClicked(MouseEvent e) {
        // if end triggered in game
        if (!g.end()) {
            next();
        }
        else {
            // switch back to menu
            ingame = false;
            ingamegui();
        }
    }

    // load the next "slide"
    public void next() {
        // Background
        background = new JLabel(new ImageIcon(g.combineimg()));

        // dialog
        tpDialog.setText(g.storytext());
        lChar1.setText(g.getcharname());

        this.repaint();
    }

    // Infobox for credits and stuff
    public void infobox(String titleBar, String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.PLAIN_MESSAGE);
    }

    // not used but needed methods for MouseListener
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}
