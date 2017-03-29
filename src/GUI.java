import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {

    SQLinteractions sql = new SQLinteractions();

    double ver = 0.01;
    boolean ingame = false;

    // Buttons
    private JButton bStart,
                    bEditor,
                    bInfo,
                    bDialog;

    // TextPane
    private JTextPane tpDialog;

    // Labels
    private JLabel  lChar1,
                    lChar2;

    // common GUI
    public GUI(){
        this.setTitle(sql.selectmeta("name"));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(960, 720);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.pink);

        // create menu buttons
        bStart = new JButton ("Start");
        bStart.setBounds(200, 400, 200, 100);
        bStart.addActionListener(this);

        bEditor = new JButton ("Editor");
        bEditor.setBounds(400, 400, 200, 100);
        bEditor.addActionListener(this);

        bInfo = new JButton ("Info");
        bInfo.setBounds(600, 400, 200, 100);
        bInfo.addActionListener(this);

        // create dialogbox
        tpDialog = new JTextPane();
        tpDialog.setBounds(50, 50, 850, 200);
        tpDialog.setForeground(Color.white);

        bDialog = new JButton ();
        bDialog.setBounds(50, 50, 850, 200);
        bDialog.addActionListener(this);
        bDialog.setOpaque(false);
        bDialog.setContentAreaFilled(false);
        bDialog.setBorderPainted(false);

        // name labels
        lChar1 = new JLabel();
        lChar1.setBounds(50, 200, 200, 100);

        lChar2 = new JLabel();

        ingamegui();
    }

    // switch between ingame and menu GUI
    public void ingamegui() {
        // ingame GUI
        if (ingame){
            // remove menu buttons
            this.remove(bStart);
            this.remove(bEditor);
            this.remove(bInfo);

            // add ingame elements
            this.add(tpDialog);
            this.add(bDialog);
            this.add(lChar1);
        }
        // menu GUI
        else {
            // add all the menu buttons
            this.add(bStart);
            this.add(bEditor);
            this.add(bInfo);
        }
        // repaint after changing GUI
        this.repaint();
        //make things visible
        this.setVisible(true);
    }

	// button clicks
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.bStart){
            //g.Game();
        }
        else if(e.getSource() == this.bEditor){
            infobox("Sorry", "The editor is not available yet.");
        }
        else if (e.getSource() == this.bInfo){
            infobox("Info", "lonegine (lonely engine) visual novel engine developed by Jen Stehlik. version: " + ver);
        }
        else if (e.getSource() == this.bDialog){
            //g.storytext();
        }
    }

    public void setstory(String story) {
        tpDialog.setText(story);
    }

	// Infobox for credits and stuff
    public void infobox(String titleBar, String infoMessage) {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
