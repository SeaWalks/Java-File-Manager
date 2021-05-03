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

/******************
 * ToDo: 1.) Figure out how to create an action listener that lets us know what
 * the current frame is 2.) Take the drive of the current frame and pass that
 * into method buildStatusBar so that it can update 3.) Every time we swtich
 * frames so does the status bar 4.) Get right click to work in app somehow 5.)
 * Tie everything together DONE PROJECT BABY!!!
 *******************/

/*******************
 * 5/3/21 PatchNotes
 * -FIXED PASTE BUTTON IN POPUP MENU(Incorrect string was being passed)
 * -Parameterized lots of lists, removed unused imports, etc to get rid of warnings. 
 * -Clicking on a blank FilePanel no longer generates errors
 * -Using menu buttons with no selected item in tree no longer generates errors
 * -Cleaned up FilePanel & DirPanel, too much too list
 * 		Big ones are improving tree search and pulling listeners into their own class
 * 		in FilePanel
 * -I wonder if copied files are consistent between the pop-up and menu buttons
 * There has to be a way to get the active frame. there HAS TO. Need to look into
 * how the Title of FileFrame is updated... 
*/
class App extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3185585690453715039L;
	private JPanel mainPanel, topPanel;
	private JDesktopPane desktopPane;
	private JMenuBar mb, statusBar;
	private JButton details, simple;
	private JComboBox<String> toolbarBox;
	private FileFrame ff;

	public App() {
		mainPanel = new JPanel();
		mainPanel.addMouseListener(new MyMouseListener()); //Triggers when you click on blank space in desktop pane. Why??
		topPanel = new JPanel();
		mb = new JMenuBar();
		new JPanel();
		desktopPane = new JDesktopPane();
		statusBar = new JMenuBar();
		details = new JButton("Details");
		simple = new JButton("Simple");
		ff = new FileFrame("C:\\", 0, 0);
		ff.getLeftPanel().addMouseListener(new MyMouseListener()); //Should trigger on the left panel. Doesn't. WTF?
	}

	public void go() {
		mainPanel.setLayout(new BorderLayout());
		this.setTitle("277 File Manager");
		buildMenuBar();
		buildToolbar();
		buildStatusBar("C:");
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(desktopPane, BorderLayout.CENTER);
		// FILEFRAME TAKES IN ARGUMENTS NOW
		// ff.addAncestorListener(new currentFrameAction());
		// myList = ff.getRightPanel().getmyList();
		// myList.addMouseListener(new MouseAdapter());
		desktopPane.add(ff);
		// ff.addInternalFrameListener(death);
		this.add(mainPanel);
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/*******************************/
	/******BUILD UI ELEMENTS********/
	/*******************************/
	
	private void buildMenuBar() {
		JMenu fileMenu, treeMenu, windowMenu, helpMenu;
		fileMenu = new JMenu("File");
		treeMenu = new JMenu("Tree");
		windowMenu = new JMenu("Window");
		helpMenu = new JMenu("Help");

		// from the File tab
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

		// from the Run tab
		JMenuItem expandBranch = new JMenuItem("Expand Branch");
		expandBranch.addActionListener(new ExpandBranchAction());
		JMenuItem collapseBranch = new JMenuItem("Collapse Branch");
		collapseBranch.addActionListener(new CollpaseBranchAction());

		// from the window tab
		JMenuItem New = new JMenuItem("New");
		New.addActionListener(new NewAction());
		JMenuItem cascade = new JMenuItem("Cascade");
		cascade.addActionListener(new CascadeAction());

		// from the help tab
		JMenuItem help = new JMenuItem("Help");
		// help.addActionListener(new HelpAction());
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
		toolbarBox = new JComboBox<String>(s1);
		topPanel.add(toolbarBox);
		topPanel.add(details);
		topPanel.add(simple);
		// toolbarBox.addActionListener(new toolbarBoxAction());
		details.addActionListener(new DetailsAction());
		simple.addActionListener(new SimpleAction());
	}

	public void buildStatusBar(String currentDrive) {
		statusBar.removeAll();
		File file = new File(currentDrive);
		if(file.exists()) {
		int freeSpace = (int) (file.getUsableSpace() / (1024 * 1024 * 1024));
		int totalSpace = (int) (file.getTotalSpace() / (1024 * 1024 * 1024));
		int usedSpace = totalSpace - freeSpace;
		
		JLabel status = new JLabel("Current Drive: " + currentDrive + " Free Space: " + freeSpace + "GB"
				+ " Used Space: " + usedSpace + "GB" + " Total Space: " + totalSpace + "GB");
		statusBar.add(status);
		mainPanel.add(statusBar, BorderLayout.SOUTH);
		}
		else {
			System.out.println(currentDrive);
		}
	}

	

	// public class statusbarActoimplements AncestorListener{ //COMPLETE
	// @Override
	// public void actionPerformed(ActionEvent e){
	// String s = (String) toolbarBox.getSelectedItem();
	// System.out.println("you selected " + s);
	// buildStatusBar(s);
	// mainPanel.revalidate();
	// }
	// }

	// public void mouseClicked(MouseEvent e) {
	// if (e.getClickCount() == 1 && e.getButton() == 3) {
	// myList.setSelectedIndex(myList.locationToIndex(e.getPoint()));
	// selectedFile = fileList[myList.locationToIndex(e.getPoint())];
	// System.out.println("We right clicked on " + selectedFile.getName());
	// ff.getRightPanel().buildpopMenu();
	// popMenu.show(e.getComponent(), e.getX(), e.getY());
	// }
	// }
	// });
	// }
	// private class rightClickAction

	// private class currentFrame implements M{

	// }
	
	
	/*******************************/
	/***********LISTENERS***********/
	/*******************************/
	
	private class MyMouseListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent me) {
			if (selectedDirectory != null) {
				if (me.getClickCount() == 1 && (me.getButton() == 1) && myList.locationToIndex(me.getPoint()) != -1) {
					selectedFile = fileList[myList.getSelectedIndex()];
					System.out.println("Selected file is: " + selectedFile.getName());
				}
				// SOMEHOW GET THIS SHIT TO WORK IN APP
				if (me.getClickCount() == 1 && (me.getButton() == 3) && myList.locationToIndex(me.getPoint()) != -1) {
					myList.setSelectedIndex(myList.locationToIndex(me.getPoint()));
					selectedFile = fileList[myList.locationToIndex(me.getPoint())];
					System.out.println("We right clicked on " + selectedFile.getName());
					System.out.println("Selected file is: " + selectedFile.getName());
					buildpopMenu();
					popMenu.show(me.getComponent(), me.getX(), me.getY());
				}
				if (me.getClickCount() == 2 && (me.getButton() == 1) && myList.locationToIndex(me.getPoint()) != -1) {
					selectedFile = fileList[myList.getSelectedIndex()];
					runFile(selectedFile);
				}

				System.out.println(
						"Current Drive, based on FilePanel.java " + selectedDirectory.getPath().substring(0, 2));
				/*
				 * Why teh fuck would i think this would work App genesis = new App();
				 * genesis.buildStatusBar(selectedDirectory.getPath().substring(0,2));
				 */
			}
		}
	}
	private class SimpleAction implements ActionListener { // COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ff.getRightPanel().getfileList() != null) {
				ff.getRightPanel().setShowDetails(false);
			}
		}
	}

	private class DetailsAction implements ActionListener { // COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {

			if (ff.getRightPanel().getfileList() != null) {
				ff = (FileFrame) desktopPane.getSelectedFrame();
				ff.getRightPanel().setShowDetails(true);
			}
		}
	}

	private class pasteAction implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ff.getRightPanel().getfileList() != null) {
				// DO ACTION HERE
			}
		}
	}

	private class RenameAction implements ActionListener {// COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ff.getRightPanel().getfileList() != null) {
				ff = (FileFrame) desktopPane.getSelectedFrame();
				DataBack dbdlg = new DataBack();
				dbdlg.setFileNameTextField(ff.getRightPanel().getFile().getPath());// Chnage to file name
				dbdlg.setVisible(true);
				String newFile = dbdlg.getFileNameTextField();
				ff.getRightPanel().renameFile(newFile);
			}
		}
	}

	private class CopyAction implements ActionListener { // ASSUME COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ff.getRightPanel().getfileList() != null) {
				ff = (FileFrame) desktopPane.getSelectedFrame();
				ff.getRightPanel().setCopiedFile(ff.getRightPanel().getFile());
			}
		}
	}

	private class DeleteAction implements ActionListener { // COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ff.getRightPanel().getfileList() != null) {
				ff = (FileFrame) desktopPane.getSelectedFrame();
				System.out.println("Delete: " + ff.getRightPanel().getFile());
				deleteDLG dlg = new deleteDLG();

				dlg.setFileNameTextField(ff.getRightPanel().getFile().getPath());
				dlg.setVisible(true);
				if (dlg.onOK()) {
					ff.getRightPanel().deleteFile();
				}
			}
		}
	}

	private class RunAction implements ActionListener {// COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			if (ff.getRightPanel().getfileList() != null) {
				ff = (FileFrame) desktopPane.getSelectedFrame();
				ff.getRightPanel().runFile(ff.getRightPanel().getFile());
			}
		}
	}

	private class ExitAction implements ActionListener { // COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private class ExpandBranchAction implements ActionListener {// COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			ff.getLeftPanel().expandTree();

		}
	}

	private class CollpaseBranchAction implements ActionListener {// COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			ff.getLeftPanel().collapseTree();
		}
	}

	private class NewAction implements ActionListener {// ALMSOT COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = (String) toolbarBox.getSelectedItem();
			FileFrame ff = new FileFrame(s, 0, 100);
			desktopPane.add(ff);

			// STATUS BAR MIGHT NEED TO UPDATE BASED ON PANEL IN FOCUS
		}
	}

	private class CascadeAction implements ActionListener { // COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			JInternalFrame[] cascade = desktopPane.getAllFrames();
			int i = 10;
			int j = 10;
			for (JInternalFrame k : cascade) {
				k.setLocation(i, j);
				i += 25;
				j += 25;
				k.moveToFront();
			}
		}
	}

	private class AboutAction implements ActionListener {// COMPLETE
		@Override
		public void actionPerformed(ActionEvent e) {
			Aboutdlg dlg = new Aboutdlg();
			dlg.setVisible(true);
		}
	}
}
