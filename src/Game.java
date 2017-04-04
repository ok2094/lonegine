import java.util.regex.*;

public class Game {

    SQLinteractions sql = new SQLinteractions();

    private int nr = 1;
    private boolean stilltalking = false;
	// Matcher variable to access it outside of 'if'
    private Matcher m;

	
		// splits and fills text into story label.
		public String storytext() {

			// if no character is talking, fill the dialog into the textarea and split it if its too long
			if (!stilltalking){
				int maxlength = 100;
				// source: http://stackoverflow.com/questions/25853393/split-a-string-in-java-into-equal-length-substrings-while-maintaining-word-bound
				Pattern p = Pattern.compile("\\G\\s*(.{1,"+maxlength+"})(?=\\s|$)", Pattern.DOTALL);
				m = p.matcher(sql.selectstory(nr, "dialog"));
			}
			// check if character is still talking and set the boolean accordingly
			if (m.find()) {
				stilltalking = true;
			}
			else {
				stilltalking = false;
				// if not talking select the next dialog
				nr = Integer.parseInt(sql.selectstory(nr, "nextnr"));
			}
			return m.group(1);
		}

		// get game name
		public String getgamename() {
			return sql.selectmeta("name");
		}


		// get character name
		public String getcharname(int id) {
			 return sql.selectcharacter(nr, id, "name");
		}
	}
