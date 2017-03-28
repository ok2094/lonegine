import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gui extends JFrame implements ActionListener {

    double ver = 0.01;
    boolean ingame = false;

    game g = new game();
    sqlinteractions sql = new sqlinteractions();

    // Buttons
    JButton bStart = new JButton();
    JButton bEditor = new JButton();
    JButton bInfo = new JButton();

    // main gui
    public gui(){
        this.setTitle(sql.selectmeta("name"));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(960, 720);

        if (ingame){

        }
        else {
            bStart.addActionListener(this);
            bEditor.addActionListener(this);
            bInfo.addActionListener(this);
        }

        this.setVisible(true);
    }

	// button clicks
    public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.bStart){
            g.start();
        }
        else if(e.getSource() == this.bEditor){
            infobox("Sorry", "The editor is not available yet.");
        }
        else if (e.getSource() == this.bInfo){
            infobox("Info", "lonegine (lonely engine) visual novel engine developed by Jen Stehlik. version: " + ver);
        }
    }
    
	// Infobox for credits and stuff
    public static void infoBox(String titleBar, String infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
