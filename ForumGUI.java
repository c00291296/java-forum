import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ForumGUI {
    JFrame frame;
    Forum forum;
    Subforum[] subforums;
    Subforum current_subforum;
    JScrollPane old_posts;
    JPanel top_panel;
    User current_user;
    ForumDB fdb;


    final static int WINDOW_WIDTH = 800;
    final static int WINDOW_HEIGHT = 600;

    public ForumGUI(Forum forum, ForumDB fdb) {
        this.forum = forum;
        this.fdb = fdb;
        frame = new JFrame("The Forum", null);

        var layout = new BorderLayout(0, 0);
        
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setLayout(layout);
        refreshTopPanel();
        refreshSubforumList();
        current_subforum = subforums[0];
        refreshPostDisplay(current_subforum);
        frame.setVisible(true);
    }

    private void refreshTopPanel( ) {
        JPanel t = new JPanel(new FlowLayout(FlowLayout.RIGHT), false);
        String username;
        JButton b = new JButton();
        if(current_user!=null) {
            username = current_user.getName();
            b.setText("Log Out");
            b.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    current_user = null;
                    refreshTopPanel();
                }
            });
        } else {
            username = "Guest";
            b.setText("Log In");
            b.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    current_user = fdb.getUser(Integer.parseInt(JOptionPane.showInputDialog("Enter unique user id to log in:")));
                    if(current_user.getName().equals("null")) {
                        current_user = null;
                    }
                    refreshTopPanel();
                }
            });
        }
        String status_symbol = "👤";
        if(current_user instanceof Admin) { //status symbol
            status_symbol = "👑";
        } else if (current_user instanceof Moderator) {
            status_symbol = "🗡️";
        }
        username = status_symbol + " " + username; //looks nice
        var l = new JLabel(username);
        l.setVisible(true);
        t.add(l);
        t.setVisible(true);
        b.setVisible(true);
        t.add(b);
        if(top_panel != null) {
            frame.remove(top_panel);
        }
        t.setSize(WINDOW_WIDTH, -1);
        frame.add(t,BorderLayout.NORTH);
        top_panel = t;
        frame.revalidate();
        frame.repaint();
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
