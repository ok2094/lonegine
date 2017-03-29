import java.sql.*;

public class SQLinteractions {

    private Connection con = null;
    public String game = "test.vn";

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
        String sql = "SELECT charid1, charid2, dialog, bgid, nextnr FROM story WHERE nr = " + nr;

        try {
            if (con == null) {
                connect();
            }
            st = con.createStatement();
            rs = st.executeQuery(sql);

            // what do you need?
            if (what.equalsIgnoreCase("dialog")){
                result = rs.getString("dialog");
            }
            else if (what.equalsIgnoreCase("nextnr")){
                result = rs.getString("nextnr");
            }
            else if (what.equalsIgnoreCase("charid1")){
                result = rs.getString("charid1");
            }
            else if (what.equalsIgnoreCase("charid2")){
                result = rs.getString("charid2");
            }
            else if (what.equalsIgnoreCase("bgid")){
                result = rs.getString("bgid");
            }

        } catch (SQLException e) {
            System.out.println("Selecting story text failed: " + e.getMessage());
        } finally {
            try {
                // close all db connections
                if (con != null) {
                    con.close();
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
        String sql = "SELECT * FROM meta";

        try {
            if (con == null) {
                connect();
            }
            st = con.createStatement();
            rs = st.executeQuery(sql);

            // what do you need?
            if (what.equalsIgnoreCase("name")){
                result = rs.getString("name");
            }

        } catch (SQLException e) {
            System.out.println("Selecting meta things failed: " + e.getMessage());
        } finally {
            try {
                // close all db connections
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("Closing connection failed: " + ex.getMessage());
            }
        }
        return result;
    }

    // delete from database
    public void delete(String table, int id) {
        String sql = "DELETE FROM " + table + " WHERE id = " + id;

        try (Connection con = this.connect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // select image from database
    public void selectimg() {

    }

    // insert image into database
    public void insertimg() {

    }

    // delete image from database
    public void deleteimg() {

    }

    // select audio from database
    public void selectaudio() {

    }

    // insert audio into database
    public void insertaudio() {

    }

    // delete audio from database
    public void deleteaudio() {

    }
}