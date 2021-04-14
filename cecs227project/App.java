package cecs227project;


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;


/* 
******************
Current status: Basic GUI stuff done (menus, toolbars)
*******************
Everything is in here. There are so many declarations
that feel redundant. Maybe we should declare/create 
things in the same line in the App method. We should
look into breaking this up into classes, with app as
the parent class to reduce LOC in any one class. Looks
messy.
Perhaps
->App
    ->Toolbar
    ->File Manager (center window stuff)
    Everything seems to hinge on data from file manager.
*/
class App extends JFrame {
    // Ignore line, VSCODE throws a fit without it. :(
    private static final long serialVersionUID = 3725860681747915637L;

    // Declare Panels
    JPanel mainPanel;
    JPanel topPanel;

    // Declare Menus
    /*
    Potentially replace all these declarations with arraylist? 
    Ex an array of menus, MenuItems, etc. Then you only declare 
    the array and assign as nessecary?
    */
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

    // Declare Menu Buttons
    JButton ok;
    JButton cancel;
    JButton toolbarDetails;
    JButton toolbarSimple;
    JButton bottomBar;
    JButton middleBar;
    JComboBox toolbarBox; //wtf is this warning? Fix it eventually.


    //Declare Labels
    JLabel statusBar, testLabel1, testLabel2, testLabel3;
    

    public App() {
        // Panels created
        mainPanel = new JPanel();
        topPanel = new JPanel();
      

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
        toolbarDetails = new JButton("Details");
        toolbarSimple = new JButton("Simple");
        middleBar = new JButton("Okay");


        //Statusbar
        statusBar = new JLabel("String argument for Drive information should be passed here.");
        

        // ActionListeners created
        toolbarDetails.addActionListener(new okActionListener());
        toolbarSimple.addActionListener(new okActionListener());
        //bottomBar.addActionListener(new okActionListener());
    }

    public void go() {
        this.setTitle("File Manager");

        //Build Panels
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new FlowLayout());
        //Toolbar Combobox; Jcombobox holds type string
        //may need to change later.
        String s1[] = {"Pancakes", "Waffles", "Syrup"};
        toolbarBox = new JComboBox<String>(s1); 

        
       
        topPanel.add(toolbarBox);    
        topPanel.add(toolbarDetails);
        topPanel.add(toolbarSimple);
        mainPanel.add(statusBar, BorderLayout.SOUTH);
        mainPanel.add(middleBar, BorderLayout.CENTER);

        // Add buttons to menus;
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
        this.setJMenuBar(mb);

        //Draw the main panel;
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