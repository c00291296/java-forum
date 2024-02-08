import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.concurrent.Exchanger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ForumDB {
    private Connection connection;
    private int id = 0;

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

    public String getUserName(int userid) {
        try {
            var ps = connection.prepareStatement("select name from Users where id = ?;");
            ps.setInt(1, userid);
            var rs = ps.executeQuery();
            rs.next();
            System.out.println(rs.getString("name"));
            ps.close();
            return rs.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getStackTrace().toString();
        }
    }

    public void setUserName(int userid, String name) {
        try {
            var ps = connection.prepareStatement("UPDATE Users SET name=? where id = ?;");
            ps.setString(1, name);
            ps.setInt(2, userid);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Post createPost(String title, String body, Date date, int authorId) {
        int postID = -1; //is supposed to be a post containing [ERROR]
        try {
            var ps = connection.prepareStatement("INSERT INTO Posts (title, body, userid, date) OUTPUT INSERTED.id VALUES (?, ?, ?, ?);");
            ps.setString(1, title);
            ps.setString(2, body);
            ps.setInt(3, authorId);
            ps.setDate(4, new java.sql.Date(date.getTime()), Calendar.getInstance());
            ResultSet rs = ps.executeQuery();
            postID = rs.getInt("id");
            ps.close();
            rs.close();
            return new Post(postID, this);
        } catch (Exception e) {
            e.printStackTrace();
            return new Post(postID, this);
        }
    }

    public String getPostTitle(int id) {
        try {
            var ps = connection.prepareStatement("SELECT title FROM Posts WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String title = rs.getString("title");
            ps.close();
            rs.close();
            return title;
        } catch (Exception e) {
            e.printStackTrace();
            return "[ERROR]" + e.getStackTrace();
        }
    }

    public String getPostBody(int id) {
        try {
            var ps = connection.prepareStatement("SELECT body FROM Posts WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String title = rs.getString("body");
            ps.close();
            rs.close();
            return title;
        } catch (Exception e) {
            e.printStackTrace();
            return "[ERROR]" + e.getStackTrace();
        }
    }

    public Date getPostDate(int id) {
        try {
            var ps = connection.prepareStatement("SELECT date FROM Posts WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Date date = rs.getDate("date");
            ps.close();
            rs.close();
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }

    public String getPostAuthor(int postId) {
        try {
            var ps = connection.prepareStatement("SELECT name FROM Users, Posts WHERE userid = Users.id AND Posts.id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String name  = rs.getString("name");
            ps.close();
            rs.close();
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return "[ERROR]";
        }
    }

    public Subforum[] getSubforums() {
        List<Subforum> subforums = new ArrayList<Subforum>();
        try {
            var s = connection.createStatement();
            var rs = s.executeQuery("SELECT id FROM Subforum;");
            while (rs.next()) {
                subforums.add(new Subforum(rs.getInt("id"), this));
            }
            s.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subforums.toArray(new Subforum[0]);
    }

    public String getSubforumTitle(int id) {
        try {
            var ps = connection.prepareStatement("SELECT name FROM Subforum WHERE id = ?;");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String title = rs.getString("name");
            ps.close();
            rs.close();
            return title;
        } catch (Exception e) {
            e.printStackTrace();
            return "[ERROR]" + e.getStackTrace();
        }
    }

}
