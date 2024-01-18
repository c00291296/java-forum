import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            var c = DriverManager.getConnection("JDBC:sqlite:forum.db");
            var s = c.createStatement();
            System.out.println("Current users:");
            var rs = s.executeQuery("select name from Users;");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}