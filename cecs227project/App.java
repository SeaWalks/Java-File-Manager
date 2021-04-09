package cecs227project;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/* mainPanel is split into a top, middle, and bottom section.
topPanel is placed into the top section of mainPanel.
topPanel is split again into a top and bottom section.
*/
class App extends JFrame {
    // Ignore the next line, VSCODE throws a fit without it
    private static final long serialVersionUID = 3725860681747915637L;
    //
    JPanel mainPanel;
    JPanel topPanel;
    JPanel topMenu;
    JPanel topToolbar;
    JButton ok;
    JButton cancel; 

    public App() {
        mainPanel = new JPanel();
        topMenu = new JPanel();
        topToolbar = new JPanel();
        topPanel = new JPanel();
        ok = new JButton("Okay");
        cancel = new JButton("Cancel");
        ok.addActionListener(new okActionListener());
        cancel.addActionListener(new okActionListener());
    }

    public void go() {
        this.setTitle("File Manager");
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(cancel, BorderLayout.SOUTH);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(topMenu, BorderLayout.NORTH);
        topPanel.add(topToolbar, BorderLayout.SOUTH);
       
        this.add(mainPanel);

        this.setSize(420, 420);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class okActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Okay")) {
                System.out.println("You pressed Okay!");
            } else {
                System.out.println("You pressed Cancel!");
            }
        }
    }
}