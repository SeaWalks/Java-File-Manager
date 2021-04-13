package cecs227project;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/* mainPanel is split into a top, middle, and bottom section.
topPanel is placed into the top section of mainPanel.
topPanel is split again into a top and bottom section.
*/
class App extends JFrame {
    // Ignore the next line, VSCODE throws a fit without it. :(
    private static final long serialVersionUID = 3725860681747915637L;

    // Declare Panels
    JPanel mainPanel;
    JPanel topPanel;
    JPanel topMenu;
    JPanel topToolbar;

    // Declare Menu
    JMenuBar mb;
    JMenu fileMenu, treeMenu, windowMenu, helpMenu;
    JMenuItem
    // File
    rename, copy, delete, run, exit,
            // Tree
            expandBranch, collapseBranch,
            // Window
            newItem, cascade, // Just new is not a valid variable declaration
            // Help
            help, about;
    // Declare Buttons
    JButton ok;
    JButton cancel;
    JButton topPanelTop;
    JButton topPanelBottom;
    JButton bottomBar;
    JButton middleBar;

    public App() {
        // Panels created
        mainPanel = new JPanel();
        topPanel = new JPanel();
        topMenu = new JPanel();
        topToolbar = new JPanel();

        // Menubar created
        mb = new JMenuBar();
        fileMenu = new JMenu("File");
        treeMenu = new JMenu("Tree");
        windowMenu = new JMenu("Window");
        helpMenu = new JMenu("Help");

        // Menu Items added
        rename = new JMenuItem("Rename");
        copy = new JMenuItem("Copy");
        delete = new JMenuItem("Delete");
        run = new JMenuItem("Run");
        exit = new JMenuItem("Exit");
        expandBranch = new JMenuItem("Expand Branch");
        collapseBranch = new JMenuItem("Collapse Branch");
        newItem = new JMenuItem("New");
        cascade = new JMenuItem("Cascade");
        help = new JMenuItem("Help");
        about = new JMenuItem("About");

        // Buttons created
        topPanelTop = new JButton("Okay");
        topPanelBottom = new JButton("Okay");
        bottomBar = new JButton("Cancel");
        middleBar = new JButton("Okay");

        // ActionListeners created
        topPanelTop.addActionListener(new okActionListener());
        topPanelBottom.addActionListener(new okActionListener());
        bottomBar.addActionListener(new okActionListener());
    }

    public void go() {
        this.setTitle("File Manager");
        // BUILD PANELS
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BorderLayout());
        topPanel.add(topPanelTop, BorderLayout.NORTH);
        topPanel.add(topPanelBottom, BorderLayout.SOUTH);
        mainPanel.add(bottomBar, BorderLayout.SOUTH);
        mainPanel.add(middleBar, BorderLayout.CENTER);
        //

        // Add buttons to menus
        fileMenu.add(rename);
        fileMenu.add(copy);
        fileMenu.add(delete);
        fileMenu.add(run);
        fileMenu.add(exit);
        treeMenu.add(expandBranch);
        treeMenu.add(collapseBranch);
        windowMenu.add(newItem);
        windowMenu.add(cascade);
        helpMenu.add(help);
        helpMenu.add(about);
        // Add menus to menubar
        mb.add(fileMenu);
        mb.add(treeMenu);
        mb.add(windowMenu);
        mb.add(helpMenu);

        //
        this.setJMenuBar(mb);
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