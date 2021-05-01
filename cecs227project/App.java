import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/******************
ToDo: 
1) FilePanel
	Implement Drag and drop
	Fix formatting
3) Toolbar
	ComboBox -> Just need to link (use FilePanel.setShowDetails)
	Simple   -> Just need to link (use FilePanel.setShowDetails)
	Details  -> Just need to link (use FilePanel.setShowDetails)
4) RightClick Menu
	Rename   -> Just need to test and link. (Use FilePanel.renameFile)
	Copy     -> Just need to test and link. (Use FilePanel.setCopiedFile)
	Paste    -> Just need to test and link. (Use FilePanel.pasteFile)
	Delete   -> Just need to test and lik. (Use FilePanel.deleteFile)

5) Menubar Items
	Rename   -> Just need to test and link. (Use FilePanel.renameFile)
	Copy     -> Just need to test and link. (Use FilePanel.setCopiedFile)
	Delete   -> Just need to test and link. (Use FilePanel.deleteFile)
	Run      -> Just need to link (use FilePanel.runFile)

5) Fix "New" button to spawn at a specific location- (0,100)
	FileFrame edited to take in parameters. When you create a new FileFrame
	in the buttonjust add in the parameters, ex new ff(0,100) or whatever

Then we are done, just organize/clean up code and commenting!!!!

2 warnings to fix, both from professors code. 
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
    private void buildToolbar(){
        toolbarPanel.setLayout(new FlowLayout());
        String s1[] = { "Pancakes", "Waffles", "Syrup" };
        JComboBox<String> toolbarBox = new JComboBox<String>(s1); 
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
        mainPanel.add(statusBar, BorderLayout.SOUTH);
        mainPanel.add(desktopPane, BorderLayout.CENTER);
        //FILEFRAME TAKES IN ARGUMENTS NOW
        FileFrame ff = new FileFrame("C:\\",0,0);
        desktopPane.add(ff);
        this.add(mainPanel);
        this.setSize(1200, 800);
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