import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ForumDB {
    private Connection connection;

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

    private void addSubforum(String name) {
        try {
        var ps = connection.prepareStatement("INSERT INTO Subforum (name) VALUES (?)");
        ps.setString(0, name);
        ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
