import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JList;

public class Program {
    
    public static void main(String[] args) {
        var frame = new JFrame("The Forum", null);
        var list = new JList<String>(new String[] {"aaa aaa aaa", "b bbb of b bb", "ccc ccc and ccc of ccc"});
        var layout = new BorderLayout(0, 0);
        list.setVisible(true);
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLayout(layout);
        frame.add(list, layout.WEST);


        ForumDB fdb = new ForumDB();
        fdb.printUsers();
        fdb.addSubforum("Ostriches");
    }
}