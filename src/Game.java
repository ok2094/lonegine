import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.regex.*;

public class Game {

    SQLinteractions sql = new SQLinteractions();

    private int nr = 1;
    private boolean stilltalking = false;
	// Matcher variable to access it outside of 'if'
    private Matcher m;
    private String dialog;
    private int charid;
	private boolean end = false;
	
		// splits and fills text into story label.
		public String storytext() {
			String result = "";

			// check if character is still talking
			if (stilltalking) {
				if (m.find()) {
					result = m.group(1);
				} else {
					stilltalking = false;
					nextnr();
				}
			}
			if (!stilltalking) {
				// select dialog
				dialog = sql.selectstory(nr, "dialog");
				// select talking character
				charid = Integer.parseInt(sql.selectstory(nr, "charid1"));

				// if shorter than 300 do not split
				if (dialog.length() < 300) {
					result = dialog;
					nextnr();
				}
				// if longer than 300 split text to multiple dialog boxes
				else {
					int maxlength = 300;
					// source: http://stackoverflow.com/questions/25853393/split-a-string-in-java-into-equal-length-substrings-while-maintaining-word-bound
					Pattern p = Pattern.compile("\\G\\s*(.{1," + maxlength + "})(?=\\s|$)", Pattern.DOTALL);
					m = p.matcher(dialog);
					m.find();
					stilltalking = true;

					result = m.group(1);
				}
			}

			return result;
		}

		public void nextnr() {
			int nxtnr;
			nxtnr = Integer.parseInt(sql.selectstory(nr, "nextnr"));
			if (nxtnr == 9999) {
				end = true;
			}
			else {
				nr = nxtnr;
			}
		}

		public boolean end() {
			return end;
		}

		// get game name
		public String getgamename() {
			return sql.selectmeta("name");
		}

		// get character name
		public String getcharname() {
			return sql.selectcharacter(charid, "name");
		}

		// not working yet
		// combine sprites and background
		public BufferedImage combineimg(){
			// background
			BufferedImage combined = getimg("bg");
			Graphics g = combined.getGraphics();
			// draw character 1
			g.drawImage(getimg("char1"), 50, 90, null);
			// if there is a second character, draw character 2
			if (sql.selectstory(nr, "charid2") != null) {
				g.drawImage(getimg("char2"), 550 + 350, 90, -350, 630, null);
			}
			g.dispose();

			return combined;
		}

		// get character/background images
		public BufferedImage getimg(String what){
			int id = 0;
			String img = "";
			if (what.equalsIgnoreCase("bg")) {
				id = Integer.parseInt(sql.selectstory(nr, "bgid"));
				img = "bg";
			}
			else if (what.equalsIgnoreCase("char1")) {
				id = Integer.parseInt(sql.selectstory(nr, "charid1"));
				img = "sprite";
			}
			else if (what.equalsIgnoreCase("char2")) {
				id = Integer.parseInt(sql.selectstory(nr, "charid2"));
				img = "sprite";
			}
			return sql.selectimg(id, img);
		}
	}
