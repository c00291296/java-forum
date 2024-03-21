import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ForumGUI {
    JFrame frame;
    Forum forum;
    Subforum[] subforums;

    final static int WINDOW_WIDTH = 800;
    final static int WINDOW_HEIGHT = 600;

    public ForumGUI(Forum forum) {
        this.forum = forum;
        frame = new JFrame("The Forum", null);

        
        
        
        var layout = new BorderLayout(0, 0);
        
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setLayout(layout);
        refreshSubforumList();
        refreshPostDisplay();
        frame.setVisible(true);
    }

    private void refreshSubforumList() {
        subforums = forum.getSubforums();
        String[] subforumNames = new String[subforums.length];
        for (int i = 0; i < subforums.length; i++) {
            subforumNames[i] = subforums[i].getTitle();
        }

        var list = new JList<String>(subforumNames);
        list.setFixedCellWidth(WINDOW_WIDTH / 5);
        list.setVisible(true);
        frame.add(list, BorderLayout.WEST);
    }

    private void refreshPostDisplay() {
        var grid = new GridLayout(100, 1, 0, 10);
        var posts = new JPanel(grid);
        var scroll_posts = new JScrollPane(posts);
        scroll_posts.setVisible(true);
        posts.setAutoscrolls(true);
        posts.setVisible(true);
        for (Post p : subforums[0].getPosts()) {
            System.out.println(p.getBody());
            JTextArea a = new JTextArea(p.getAuthor() + ", " + p.getTitle() + ":\n" + p.getBody() + "\n" + p.getDate());
            a.setLineWrap(true);
            a.setEditable(false);
            a.setVisible(true);
            posts.add(a);
        }

        frame.add(scroll_posts, BorderLayout.CENTER);
    }
}
