

import java.awt.event.*;
import java.io.File;
import java.awt.*;
import javax.swing.*;

/******************
Current status: 
> Basic GUI stuff done (menus, toolbars)
> Internal windows added
>SplitPane added to internal windows
*******************/

class App extends JFrame {
    //Neded for VSCode
    private static final long serialVersionUID = 3725860681747915637L;
    
    JPanel mainPanel;
    JPanel toolbarPanel;
    JDesktopPane desktopPane;
    JMenuBar mb;
    JButton ok;
    JButton cancel;
    JLabel statusBar;


    private void buildMenuBar() {
        mb = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu treeMenu = new JMenu("Tree");
        JMenu windowMenu = new JMenu("Window");
        JMenu  helpMenu = new JMenu("Help");
        JMenuItem rename = new JMenuItem("Rename");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem delete = new JMenuItem("Delete");
        JMenuItem run = new JMenuItem("Run");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem expandBranch = new JMenuItem("Expand Branch");
        JMenuItem collapseBranch = new JMenuItem("Collapse Branch");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem cascade = new JMenuItem("Cascade");
        JMenuItem help = new JMenuItem("Help");
        JMenuItem about = new JMenuItem("About");
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
        mb.add(fileMenu);
        mb.add(treeMenu);
        mb.add(windowMenu);
        mb.add(helpMenu);
        this.setJMenuBar(mb);
    }
    private void buildToolbar() {
        File[] paths;
        paths = File.listRoots();
        String[] s1 = new String[paths.length];
        int i = 0;
        for (File path : paths) {
            s1[i] = path.toString();
            i++;
        }
            JComboBox toolbarBox = new JComboBox<String>(s1); // wtf is this warning? Fix it eventually.
            JButton toolbarDetails = new JButton("Details");
            JButton toolbarSimple = new JButton("Simple");
            toolbarDetails.addActionListener(new okActionListener());
            toolbarSimple.addActionListener(new okActionListener());
            toolbarPanel.add(toolbarBox);
            toolbarPanel.add(toolbarDetails);
            toolbarPanel.add(toolbarSimple);
        
    }
    
    public App() {
        //IDk if we're allowed to assign a string to the statusBar JLabel in the constructor
        mainPanel = new JPanel();
        toolbarPanel = new JPanel();
        desktopPane = new JDesktopPane();
        
        statusBar = new JLabel("String argument for Drive information should be passed here.");
    }

    public void go() {
        this.setTitle("File Manager");
        buildMenuBar();
        buildToolbar();
        // Build Panels
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(toolbarPanel, BorderLayout.NORTH);
        toolbarPanel.setLayout(new FlowLayout());
        mainPanel.add(statusBar, BorderLayout.SOUTH);
        mainPanel.add(desktopPane, BorderLayout.CENTER);
        FileFrame ff = new FileFrame();
        
        desktopPane.add(ff);
        // Draw the main panel;
        this.add(mainPanel);
        this.setSize(690, 420);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class okActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Details")) {
                System.out.println("You pressed Details!");
            } else {
                System.out.println("You pressed Simple!");
            }
        }
    }
}