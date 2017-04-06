import java.awt.image.BufferedImage;
import java.util.regex.*;

public class Game {

    SQLinteractions sql = new SQLinteractions();

    private int nr = 1;
    private boolean stilltalking = false;
	// Matcher variable to access it outside of 'if'
    private Matcher m;
    private String dialog;

	
		// splits and fills text into story label.
		public String storytext() {
			String result = "";

			// check if character is still talking
			if (stilltalking) {
				if (m.find()) {
					result = m.group(1);
				} else {
					stilltalking = false;
					// if not talking select the next dialog
					nr = Integer.parseInt(sql.selectstory(nr, "nextnr"));
				}
			}

			if (!stilltalking) {
				dialog = sql.selectstory(nr, "dialog");

				// if shorter than 300 do not split
				if (dialog.length() < 300){
					result = dialog;
					nr = Integer.parseInt(sql.selectstory(nr, "nextnr"));
				}
				// if longer than 300 split text to multiple dialog boxes
				else {
					int maxlength = 300;
					// source: http://stackoverflow.com/questions/25853393/split-a-string-in-java-into-equal-length-substrings-while-maintaining-word-bound
					Pattern p = Pattern.compile("\\G\\s*(.{1,"+maxlength+"})(?=\\s|$)", Pattern.DOTALL);
					m = p.matcher(dialog);
					m.find();
					stilltalking = true;

					result = m.group(1);
				}
			}
			return result;
		}

		// get game name
		public String getgamename() {
			return sql.selectmeta("name");
		}

		// get character name
		public String getcharname() {
			int id = Integer.parseInt(sql.selectstory(nr, "charid1"));
			return sql.selectcharacter(nr, id, "name");
		}
		public String getcharname(int id) {
			return sql.selectcharacter(nr, id, "name");
		}

		// get character/background images
		public BufferedImage getimg(String what){
			int id = 0;
			String img = "";
			if (what.equalsIgnoreCase("bg")) {
				id = Integer.parseInt(sql.selectstory(nr, "bgid"));
				img = "bg";
			}
			else if (what.equalsIgnoreCase("charid1")) {
				id = Integer.parseInt(sql.selectstory(nr, "charid1"));
				img = "sprite";
			}
			else if (what.equalsIgnoreCase("charid2")) {
				id = Integer.parseInt(sql.selectstory(nr, "charid2"));
				img = "sprite";
			}
			return sql.selectimg(id, img);
		}
	}
