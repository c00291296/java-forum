import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextArea;


import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Program {
    public static void main(String[] args) {


        ForumDB fdb = new ForumDB();
        Forum f = new Forum(fdb);
        ForumGUI gui = new ForumGUI(f, fdb);
        
    }
}