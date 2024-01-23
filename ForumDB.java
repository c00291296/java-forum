import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.Exchanger;

public class ForumDB {
    private Connection connection;
    private int id = 0;

    private int getid() {
        return id++;
    }

    public ForumDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("JDBC:sqlite:forum.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printUsers() {
        try {
            var s = connection.createStatement();
            System.out.println("Current users:");
            var rs = s.executeQuery("select name from Users;");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSubforum(String name) {
        try {
        var ps = connection.prepareStatement("INSERT INTO Subforum (name) VALUES (?);");
        ps.setString(1, name);
        ps.executeUpdate();
        ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addPostToSubforum(int subforum_id, String post_title, String post_body, String post_date,
                                  int userid)
        {
            try {
                String post_text = post_title.toUpperCase() + "\n\n" + post_body;
                var ps = connection.prepareStatement("INSERT INTO Posts (userid, subforumid, time, text) VALUES (?, ?, ?, ?);");
                ps.setInt(1, userid);
                ps.setInt(2, subforum_id);
                ps.setString(3, post_date);
                ps.setString(4, post_text);
                ps.executeUpdate();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
    public void censorPost (int postID) {
        try {
            var ps = connection.prepareStatement("UPDATE Posts SET text = '[CENSORED]' WHERE id = ?;");
            ps.setInt(1, postID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void appointModerator(int userID) {
        try {
            var ps = connection.prepareStatement("INSERT INTO AdminList VALUES (?);");
            ps.setInt(1, userID);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
