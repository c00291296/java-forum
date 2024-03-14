import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import javax.swing.JList;

public class Program {
    final static int WINDOW_WIDTH = 800;
    final static int WINDOW_HEIGHT = 600;
    public static void main(String[] args) {
        var frame = new JFrame("The Forum", null);


        ForumDB fdb = new ForumDB();
        Forum f = new Forum(fdb);
        Subforum[] subforums = f.getSubforums();
        String[] subforumNames = new String[subforums.length];
        for (int i = 0; i < subforums.length; i++) {
            subforumNames[i] = subforums[i].getTitle();
        }
        var list = new JList<String>(subforumNames);
        list.setFixedCellWidth(WINDOW_WIDTH / 5);
        var layout = new BorderLayout(0, 0);
        list.setVisible(true);
        frame.setVisible(true);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setLayout(layout);
        frame.add(list, layout.WEST);
    }
}