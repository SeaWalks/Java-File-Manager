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
    // Ignore the next line, VSCODE throws a fit without it. :(
    private static final long serialVersionUID = 3725860681747915637L;
    //
    JPanel mainPanel;
    JPanel topPanel;
    JPanel topMenu;
    JPanel topToolbar;
    JButton ok;
    JButton cancel; 
    JButton topPanelTop;
    JButton topPanelBottom;
    JButton bottomBar;
    JButton middleBar;

    public App() {
        //Panels created
        mainPanel = new JPanel();
        topPanel = new JPanel();
        topMenu = new JPanel(); 
        topToolbar = new JPanel();

        //Buttons created
        topPanelTop = new JButton("Okay");
        topPanelBottom = new JButton("Okay");
        bottomBar = new JButton("Cancel");
        middleBar = new JButton("Okay");

        //ActionListeners created
        topPanelTop.addActionListener(new okActionListener());
        topPanelBottom.addActionListener(new okActionListener());
        bottomBar.addActionListener(new okActionListener());
    }

    public void go() {
        this.setTitle("File Manager");
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);      
        topPanel.setLayout(new BorderLayout());
            topPanel.add(topPanelTop, BorderLayout.NORTH);
            topPanel.add(topPanelBottom, BorderLayout.SOUTH);
        mainPanel.add(bottomBar, BorderLayout.SOUTH);
        mainPanel.add(middleBar, BorderLayout.CENTER);
        
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