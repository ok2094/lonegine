
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class SQLinteractions {

    private Connection con = null;
    // game name
    public String game = "test" + ".vn";

    // create a connection to the database
    private Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch(ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        // SQLite connection string
        String url = "jdbc:sqlite:" + game;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Connection to database failed: " + e.getMessage());
        }
        return con;
    }

    // select things from the story table from database
    public String selectstory(int nr, String what) {

        String result = "";
        Statement st;
        ResultSet rs;
        String sql = "SELECT charid1, charid2, dialog, bgid, nextnr FROM story WHERE nr = " + nr + ";";

        try {
            if (con == null) {
                connect();
            }
            st = con.createStatement();
            rs = st.executeQuery(sql);

            // what do you need?
            result = rs.getString(what);

        } catch (SQLException e) {
            System.out.println("Selecting story text failed: " + e.getMessage());
        } finally {
            try {
                // close db connections
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                System.out.println("Closing connection failed: " + ex.getMessage());
            }
        }
        return result;
    }

    // select character
    public String selectcharacter(int nr, int id, String what) {

        String result = "";
        Statement st;
        ResultSet rs;
        String sql = "SELECT charid, name FROM story INNER JOIN characters charid WHERE nr = " + nr + " and charid = " + id + ";";

        try {
            if (con == null) {
                connect();
            }
            st = con.createStatement();
            rs = st.executeQuery(sql);

            // what do you need?
            result = rs.getString(what);

        } catch (SQLException e) {
            System.out.println("Selecting character failed: " + e.getMessage());
        } finally {
            try {
                // close db connections
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                System.out.println("Closing connection failed: " + ex.getMessage());
            }
        }
        return result;
    }

    // select things from the story table from database
    public String selectmeta(String what) {

        String result = "";
        Statement st;
        ResultSet rs;
        String sql = "SELECT * FROM meta;";

        try {
            if (con == null) {
                connect();
            }
            st = con.createStatement();
            rs = st.executeQuery(sql);

            // what do you need?
            result = rs.getString(what);

        } catch (SQLException e) {
            System.out.println("Selecting meta things failed: " + e.getMessage());
        } finally {
            try {
                // close db connections
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                System.out.println("Closing connection failed: " + ex.getMessage());
            }
        }
        return result;
    }

    public BufferedImage selectimg(int id, String what) {

        BufferedImage result = null;
        String sql = "";
        Statement st;
        ResultSet rs;

        if (what.equalsIgnoreCase("bg")) {
            sql = "SELECT bg FROM background WHERE bgid = " + id + ";";
        }
        else if (what.equalsIgnoreCase("sprite")) {
            sql = "SELECT sprite FROM characters WHERE charid = " + id + ";";
        }

        try {
            if (con == null) {
                connect();
            }
            st = con.createStatement();
            rs = st.executeQuery(sql);

            // get image and decode the binary stream
                InputStream binaryStream = rs.getBinaryStream(what);
            try {
                result = ImageIO.read(binaryStream);
            } catch (IOException exe) {
                System.out.println("Converting image to BufferedImage failed: " + exe.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Selecting images failed: " + e.getMessage());
        } finally {
            try {
                // close db connections
                if (con != null) {
                    con.close();
                    con = null;
                }
            } catch (SQLException ex) {
                System.out.println("Closing connection failed: " + ex.getMessage());
            }
        }
        return result;
    }
}