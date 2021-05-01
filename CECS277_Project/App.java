package CECS277_Project;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import javax.swing.*;

/******************
 ToDo:
 1) FilePanel
 Maybe add icons if I feel like it
 2) Toolbar
 ComboBox -> Just need to link (use FilePanel.setShowDetails)
 Simple   -> Just need to link (use FilePanel.setShowDetails)
 Details  -> Just need to link (use FilePanel.setShowDetails)
 3) RightClick Menu
 Rename   -> Just need to test and link. (Use FilePanel.renameFile)
 Copy     -> Just need to link. (Use FilePanel.setCopiedFile)
 Paste    -> Just need to link. (Use FilePanel.pasteFile)
 Delete   -> Just need to test and link. (Use FilePanel.deleteFile)
 4) Menubar Items
 Rename   -> Just need to test and link. (Use FilePanel.renameFile)
 Copy     -> Just need to link. (Use FilePanel.setCopiedFile)
 Delete   -> Just need to test and link. (Use FilePanel.deleteFile)
 Run      -> Just need to link (use FilePanel.runFile)
 5) Fix "New" button to spawn at a specific location- (0,100)
 FileFrame edited to take in parameters. When you create a new FileFrame
 in the buttonjust add in the parameters, ex new ff(0,100) or whatever
 Then we are done, just organize/clean up code and commenting!!!!
 3 warnings to fix from professors code. Arraylists need to be parameterized
 *******************/

class App extends JFrame {
    JPanel mainPanel, topPanel;
    JPanel toolbarPanel;
    JDesktopPane desktopPane;
    JMenuBar mb, statusBar;
    JButton details, simple;
    JComboBox toolbarBox;

    FileFrame ff;

    private void buildMenuBar() {
        JMenu fileMenu, treeMenu, windowMenu, helpMenu;
        fileMenu = new JMenu("File");
        treeMenu = new JMenu("Tree");
        windowMenu = new JMenu("Window");
        helpMenu = new JMenu("Help");

        //from the File tab
        JMenuItem rename = new JMenuItem("Rename");
        rename.addActionListener(new RenameAction());
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(new CopyAction());
        JMenuItem delete = new JMenuItem("Delete");
        delete.addActionListener(new DeleteAction());
        JMenuItem run = new JMenuItem("Run");
        run.addActionListener(new RunAction());
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(new ExitAction());

        //from the Run tab
        JMenuItem expandBranch = new JMenuItem("Expand Branch");
        expandBranch.addActionListener(new ExpandBranchAction());
        JMenuItem collapseBranch = new JMenuItem("Collapse Branch");
        collapseBranch.addActionListener(new CollpaseBranchAction());

        //from the window tab
        JMenuItem New = new JMenuItem("New");
        New.addActionListener(new NewAction());
        JMenuItem cascade = new JMenuItem("Cascade");
        cascade.addActionListener(new CascadeAction());

        //from the help tab
        JMenuItem help = new JMenuItem("Help");
        help.addActionListener(new HelpAction());
        JMenuItem about = new JMenuItem("About");
        about.addActionListener(new AboutAction());

        fileMenu.add(rename);
        fileMenu.add(copy);
        fileMenu.add(delete);
        fileMenu.add(run);
        fileMenu.add(exit);
        helpMenu.add(help);
        helpMenu.add(about);
        windowMenu.add(New);
        windowMenu.add(cascade);
        treeMenu.add(expandBranch);
        treeMenu.add(collapseBranch);

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
        toolbarBox = new JComboBox(s1);
        topPanel.add(toolbarBox);
        topPanel.add(details);
        topPanel.add(simple);
        toolbarBox.addActionListener(new toolbarBoxAction());
        details.addActionListener(new DetailsAction());
        simple.addActionListener(new SimpleAction());
    }

    private void buildStatusBar(String currentDrive){
        statusBar.removeAll();
        File file = new File(currentDrive);
        int usedSpace = (int) (file.getUsableSpace()/(1024 * 1024 * 1024));
        int totalSpace = (int) (file.getTotalSpace()/(1024 * 1024 * 1024));
        int freeSpace = totalSpace - usedSpace;
        JLabel status = new JLabel("Current Drive: " + currentDrive + " Free Space: " + freeSpace + "GB" + " Used Space: " + usedSpace + "GB" + " Total Space: " + totalSpace + "GB");
        statusBar.add(status);
        mainPanel.add(statusBar, BorderLayout.SOUTH);
    }

    public App() {
        //IDk if we're allowed to assign a string to the statusBar JLabel in the constructor
        mainPanel = new JPanel();
        topPanel = new JPanel();
        mb = new JMenuBar();
        toolbarPanel = new JPanel();
        desktopPane = new JDesktopPane();
        statusBar = new JMenuBar();
        details = new JButton("Details");
        simple = new JButton("Simple");
    }

    public void go() {

        mainPanel.setLayout(new BorderLayout());
        this.setTitle("277 File Manager");
        buildMenuBar();
        buildToolbar();
        buildStatusBar("C:");



        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(desktopPane, BorderLayout.CENTER);
        //FILEFRAME TAKES IN ARGUMENTS NOW
        FileFrame ff = new FileFrame("C:\\",0,0);
        desktopPane.add(ff);
        this.add(mainPanel);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public class toolbarBoxAction implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            String s = (String) toolbarBox.getSelectedItem();
            System.out.println("you selected " + s);
            buildStatusBar(s);
            mainPanel.revalidate();
        }
    }

    private class SimpleAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("You pressed Simple");
        }
    }

    private class DetailsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            //FilePanel
        }
    }

    private class RenameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){

           // DataBack dbdlg = new DataBack();
            //(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            //dbdlg.setFromTextField("This is supposed to be the file name u want to rename");//Chnage to file name
           // dbdlg.setVisible(true);
           // String toField = dbdlg.getToTextField();

        }
    }

    private class CopyAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("You pressed Copy");
        }
    }

    private class DeleteAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("You pressed Delete");
        }
    }

    private class RunAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("You pressed Run");
        }
    }

    private class ExitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }

    private class ExpandBranchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            //ff.getLeftPanel().expandTree();
        }
    }

    private class CollpaseBranchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            // ff.getLeftPanel().collapseTree();
        }
    }

    private class NewAction implements ActionListener {//NEEDS TO BE FIXED
        @Override
        public void actionPerformed(ActionEvent e){
            // FileFrame ff = new FileFrame();
            // desktopPane.add(ff);
        }
    }

    private class CascadeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            JInternalFrame[] cascade = desktopPane.getAllFrames();
            int i = 10;
            int j = 10;
            for (JInternalFrame k : cascade){
                k.setLocation(i, j);
                i += 25;
                j += 25;
                k.moveToFront();
            }
        }
    }

    private class HelpAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            creativeHelpDLG dlg = new creativeHelpDLG();
            dlg.setVisible(true);
        }
    }

    private class AboutAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            Aboutdlg dlg = new Aboutdlg();
            dlg.setVisible(true);
        }
    }
}
