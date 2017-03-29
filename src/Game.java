import java.util.regex.*;

public class Game {

    SQLinteractions sql = new SQLinteractions();
	GUI gui = new GUI();

    private int nr = 1;
    private boolean stilltalking = false;
	// Matcher variable to access it outside of 'if'
    private Matcher m;

		public void game() {
			// if character still talking continue his dialog
			if (stilltalking){
				storytext();
			}
			// set all information about current scene and go to dialog
			else {
				setchar();
			}
		}
	
		// splits and fills text into story label. source: http://stackoverflow.com/questions/25853393/split-a-string-in-java-into-equal-length-substrings-while-maintaining-word-bound
		public void storytext() {

			// if no character is talking, fill the dialog into the textarea and split it if its too long
			if (!stilltalking){
				int maxlength = 100;
				Pattern p = Pattern.compile("\\G\\s*(.{1,"+maxlength+"})(?=\\s|$)", Pattern.DOTALL);
				m = p.matcher(sql.selectstory(nr, "dialog"));
			}
			gui.setstory(m.group(1));
			// check if character is still talking and set the boolean accordingly
			if (m.find()) {
				stilltalking = true;
			}
			else {
				stilltalking = false;
				// if not talking select the next dialog
				nr = Integer.parseInt(sql.selectstory(nr, "nextnr"));
			}
		}

		public void setchar(){

		}
	}
