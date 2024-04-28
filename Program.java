import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.FileOutputStream;

import javax.swing.JTextArea;


import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.io.PrintStream;
import java.io.FileOutputStream;

public class Program {
    public static void main(String[] args) {
        try {
        PrintStream ps = new PrintStream(new FileOutputStream("log.txt"));
        System.setOut(ps);
        System.setErr(ps);
        } catch(Exception e){
            e.printStackTrace();
        }

        ForumDB fdb = new ForumDB();
        Forum f = new Forum(fdb);
        ForumGUI gui = new ForumGUI(f, fdb);
        
    }
}