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
            
        }

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

    }
    
	// Infobox for credits and stuff
	// infobox("lonegine: visual novel engine developed by Jen Stehlik. version: " + ver, "Info");
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
