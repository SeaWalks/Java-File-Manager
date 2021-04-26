package CECS277_Project;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

class App extends JFrame{
    JPanel panel, topPanel;
    JButton details, simple;
    JMenuBar menuBar, statusBar;
    JDesktopPane desktopPane;

    public App(){
        panel = new JPanel();
        topPanel = new JPanel();
        desktopPane = new JDesktopPane();
        menuBar = new JMenuBar();
        statusBar = new JMenuBar();

        details = new JButton("Details");
        simple = new JButton("Simple");

        panel.setLayout(new BorderLayout());
    }

    public void go() {
        buildMenu();
        buildStatusBar("C:");//change somehow
        buildToolbar();

        this.add(panel);

        FileFrame ff = new FileFrame();

        this.setTitle("277 File Manager Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
        this.setSize(800, 600);
        
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(desktopPane, BorderLayout.CENTER);

        desktopPane.add(ff);
    }

    public void buildMenu(){
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

        menuBar.add(fileMenu);
        menuBar.add(treeMenu);
        menuBar.add(windowMenu);
        menuBar.add(helpMenu);
        this.setJMenuBar(menuBar);
    }

    public void buildToolbar(){
        File[] paths;
        paths = File.listRoots();
        String[] s1 = new String[paths.length];
        int i = 0;
        for(File path: paths){
            s1[i] = path.toString();
            i++;
        }

        JComboBox toolbarBox = new JComboBox(s1);
        topPanel.add(toolbarBox);
        topPanel.add(details);
        topPanel.add(simple);
        details.addActionListener(new DetailsAction());
        simple.addActionListener(new SimpleAction());
    }

    public void buildStatusBar(String s){
        File file = new File(s);
        File[] files = file.listFiles();


        //int usedSpace = (int) file.getUsableSpace();
        //int freeSpace = (int) file.getFreeSpace();
        //int totalSpace = (int) file.getTotalSpace();

        //JLabel status = new JLabel("Current Drive: " + directory + " Free Space: " + freeSpace);
        //statusBar.add(status);
        //panel.add(statusBar, BorderLayout.SOUTH);
    }
    //class OkAction implements ActionListener{
        //@Override
       // public void actionPerformed(ActionEvent e){
           // dispose();
        //}
   // }
    private class SimpleAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("You pressed Simple");
        }
    }

    private class DetailsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("You pressed Details");
        }
    }

    private class RenameAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            DataBack dbdlg = new DataBack();
            //(DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            dbdlg.setFromTextField("This is supposed to be the file name u want to rename");//Chnage to file name
            dbdlg.setVisible(true);
            String toField = dbdlg.getToTextField();
            System.out.println("New File name:" + toField);
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
            System.out.println("You pressed ExpandBranch");
        }
    }

    private class CollpaseBranchAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("You pressed Collapse Branch");
        }
    }

    private class NewAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            FileFrame Frame = new FileFrame();
            desktopPane.add(Frame);// Needs to work for the right directory
        }
    }

    private class CascadeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            setLocationByPlatform(true);
        }
    }

    private class HelpAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.out.println("You pressed Collapse Branch");
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
