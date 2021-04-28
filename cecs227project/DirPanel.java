import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.*;

public class DirPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JScrollPane scrollPane;
	private JTree tree;
	private DefaultTreeModel treeModel;
	private String currentDrive;
	private File currentFile;

	// Getters for active folders. Returns File objects.
	public String getCurrentDrive() {
		return currentDrive;
	}

	public File getCurrentFolder() {
		return currentFile;
	}

	public DirPanel() {
		buildTree();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tree);
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
	}

	private void buildTree() {

		tree = new JTree();
		tree.addTreeSelectionListener(new DemoTreeSelectionListener());
		FileNode base = new FileNode("C:\\"); // Base should eventually be a parameter
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(); // Base node in the tree
		root.setUserObject(base);
		createChildren(root);
		treeModel = new DefaultTreeModel(root);
		tree.setModel(treeModel);
	}

	// ONLY CALL THIS ON DIRECTORIES
	private void createChildren(DefaultMutableTreeNode node) {

		// Take in a parent node from the tree. Get the fileNode,
		// get the Folder in the fileNode, list the files in the folder.
		FileNode parentNode = (FileNode) node.getUserObject();
		File parentFile = parentNode.getFile();
		File[] files = parentFile.listFiles();

		// If there are no files (not a directory). This shouldn't happen
		// because createChild is only called on Directories.
		if (files == null)
			return;

		// If there are subfiles (is a directory)
		for (File file : files) {
			DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file.getPath()));
			node.add(childNode); // Add childNode to original tree.
			File childFile = new File(file.getPath());

			if (childFile.isDirectory()) {
				System.out.println("Directory detected: " + childFile.getPath()); // This works
				//FileNode secondaryFileNode = new FileNode(childFile.getPath());
				
				//gchildNode.setUserObject(secondaryFileNode);
				File[] nextChild = childFile.listFiles();
				if (nextChild != null) {
					for (File nextFile : nextChild) {
						System.out.println("Grandchildfile is: " + nextFile.getPath());
				
						DefaultMutableTreeNode gchildNode = new DefaultMutableTreeNode(new FileNode(nextFile.getPath()));
					
						childNode.add(gchildNode);
					}
				}
			}
		}
	}

	public class DemoTreeSelectionListener implements TreeSelectionListener {
		// Can we have a filepath string outside with a getter for the currently
		// selected path?
		// getCurrentFile
		// getCurrentDrive

		@Override
		public void valueChanged(TreeSelectionEvent e) {
			System.out.println("You selected a node in the tree");
			System.out.println(tree.getMinSelectionRow());
			System.out.println(tree.getSelectionPath());
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			FileNode nodeInfo = (FileNode) selectedNode.getUserObject();

			// If nodeinfo.getFile() is a directory load the next set of folders inside.
			// We won't "unload" shit and its probably bad to

			currentDrive = (nodeInfo.getFile().getAbsolutePath()).substring(0, 3);
			currentFile = (nodeInfo.getFile());
			// Debugging console log
			System.out.println("Filepath is: " + nodeInfo.getFile().getAbsolutePath());
			System.out.println("The file name is: " + nodeInfo.getFile().getName());
			System.out.println("Does this file Exist? " + nodeInfo.getFile().exists());
			System.out.println("Can we read this? " + nodeInfo.getFile().canRead());
			System.out.println("Is this a directory? " + nodeInfo.getFile().isDirectory());
			System.out.println("The current drive is: " + currentDrive);
			createChildren(selectedNode);

		}
	}
}
