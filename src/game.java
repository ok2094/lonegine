
public class game {

    sqlinteractions sql = new sqlinteractions();
    gui gui = new gui();
    int nr = 1;
    boolean stilltalking = false;

		play() {
			// if character still talking continue his dialog
			if (stilltalking){
				storytext();
			}
			// set all information about current scene and go to dialog
			else {
				gui.setname(sql.selectstory(nr, "name"));
				gui.setbg(sql.selectstory(nr, "bgid"));
				gui.setchar1(sql.selectstory(nr, "charid1"));
				gui.setchar2(sql.selectstory(nr, "charid2"));
				storytext();
			}
		}
	
		// splits and fills text into story label. source: http://stackoverflow.com/questions/25853393/split-a-string-in-java-into-equal-length-substrings-while-maintaining-word-bound
		storytext() {
			// if no character is talking, fill the dialog into the textarea and split it if its too long
			if (!stilltalking){
				int maxlength = 100;
				Pattern p = Pattern.compile("\\G\\s*(.{1,"+maxlength+"})(?=\\s|$)", Pattern.DOTALL);
				Matcher m = p.matcher(sql.selectstory(nr, "dialog");
			}
			gui.setstory(m.group(1));
			// check if character is still talking and set the boolean accordingly
			if (m.find()) {
				stilltalking = true;
			}
			else {
				stilltalking = false;
				// if not talking select the next dialog
				nr = sql.selectstory(nr, "nextnr");
			}
		}
	}
