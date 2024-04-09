import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ForumGUI {
    JFrame frame;
    Forum forum;
    Subforum[] subforums;
    Subforum current_subforum;
    JScrollPane old_posts;


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
        current_subforum = subforums[0];
        refreshPostDisplay(current_subforum);
        frame.setVisible(true);
    }

    private void refreshTopPanel( ) {

    }

    private void refreshSubforumList() {
        subforums = forum.getSubforums();
        String[] subforumNames = new String[subforums.length];
        for (int i = 0; i < subforums.length; i++) {
            subforumNames[i] = subforums[i].getTitle();
        }

        var list = new JList<String>(subforumNames);
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                current_subforum = subforums[list.getSelectedIndex()];
                refreshPostDisplay(current_subforum);
            }
        });
        list.setFixedCellWidth(WINDOW_WIDTH / 5);
        list.setVisible(true);
        frame.add(list, BorderLayout.WEST);
    }

    private void refreshPostDisplay(Subforum subforum) {
        var grid = new GridLayout(100, 1, 0, 10);
        var posts = new JPanel(grid);
        var scroll_posts = new JScrollPane(posts);
        
        posts.setAutoscrolls(true);
        
        for (Post p : subforum.getPosts()) {
            System.out.println(p.getBody());
            JTextArea a = new JTextArea(p.getAuthor() + ", " + p.getTitle() + ":\n" + p.getBody() + "\n" + p.getDate());
            a.setLineWrap(true);
            a.setEditable(false);
            a.setVisible(true);
            posts.add(a);
        }
        posts.setVisible(true);
        scroll_posts.setVisible(true);

        if(old_posts != null) {
            frame.remove(old_posts);
        }
        
        frame.add(scroll_posts, BorderLayout.CENTER);
        old_posts = scroll_posts;
        frame.revalidate();
        frame.repaint();
        
    }
}
