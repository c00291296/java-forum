import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;

import javax.swing.JTextArea;

import org.w3c.dom.Text;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
        var grid = new GridLayout(100, 1, 0, 10);
        var posts = new JPanel(grid);
        var scroll_posts = new JScrollPane(posts);
        scroll_posts.setVisible(true);
        posts.setAutoscrolls(true);
        posts.setVisible(true);
        /*
        Admin ad = new Admin(0, fdb);
        var potatoes = ad.createPost("hello world!", "HELLO guys i really like potatoes!!!");
        subforums[0].addPost(potatoes)*/
        for (Post p : subforums[0].getPosts()) {
            System.out.println(p.getBody());
            JTextArea a = new JTextArea(p.getAuthor() + ", " + p.getTitle() + ":\n" + p.getBody() + "\n" + p.getDate());
            a.setLineWrap(true);
            a.setEditable(false);
            a.setVisible(true);
            posts.add(a);
        }
        list.setFixedCellWidth(WINDOW_WIDTH / 5);
        var layout = new BorderLayout(0, 0);
        list.setVisible(true);
        frame.setVisible(true);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setLayout(layout);
        frame.add(list, layout.WEST);
        frame.add(scroll_posts, layout.CENTER);
    }
}