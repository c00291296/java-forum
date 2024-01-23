import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        ForumDB fdb = new ForumDB();
        fdb.printUsers();
        fdb.addSubforum("Ostriches");
    }
}