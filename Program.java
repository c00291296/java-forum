import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.LayoutStyle;

public class Program {
    
    public static void main(String[] args) {
        var frame = new JFrame("The Forum", null);
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);

        ForumDB fdb = new ForumDB();
        fdb.printUsers();
        fdb.addSubforum("Ostriches");
    }
}