import java.sql.*;

public class sqlinteractions {

    // create a connection to the database
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:vncontent.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Connection to database failed: " + e.getMessage());
        }
        return conn;
    }

    // select from database
    public void select() {
        String sql = "SELECT " + " FROM " ;

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("name") + "\t" +
                        rs.getDouble("capacity"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // insert into database
    public void insert() {

    }

    // delete from database
    public void delete() {
        String sql = "DELETE FROM " + " WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

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