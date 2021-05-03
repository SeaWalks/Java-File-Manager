package CECS277_Project;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.*;

public class DirPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 222102463676642957L;
	private JScrollPane scrollPane;
	private JTree tree;
	private DefaultTreeModel treeModel;
	private String currentDrive;
	private File currentDirectory;
	FileFrame BigFrame;
	FilePanel RightPanel;
	App TestApp;

	/*******************************/
	/************ GET&SET **********/
	/*******************************/

	public File getCurrentDirectory() {
		return currentDirectory;
	}

	public JTree getTree() {
		return tree;
	}

	public void setFilePanel(FilePanel fp) {
		RightPanel = fp;
	}

	public void setFileFrame(FileFrame frame) {
		BigFrame = frame;
	}

	// TESTING
	public void setStatusBar(App app) {
		TestApp = app;
	}

	// Getters for active folders. Returns File objects.
	public String getCurrentDrive() {
		return currentDrive;
	}

	public DirPanel(File base) {
		FileNode root = new FileNode(base.getPath());
		buildTree(root);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tree);
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
	}

	/*******************************/
	/******* TREE OPERATION ********/
	/*******************************/

	private void buildTree(FileNode base) {

		tree = new JTree();
		tree.addTreeSelectionListener(new DemoTreeSelectionListener());
		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		currentDirectory = base.getFile();
		root.setUserObject(base);
		createChildren(root);
		treeModel = new DefaultTreeModel(root);
		tree.setModel(treeModel);
	}

	void createChildren(DefaultMutableTreeNode node) {// Loads two nodes deep

		FileNode parentNode = (FileNode) node.getUserObject();
		File[] parentList = parentNode.getFile().listFiles();
		for (File file : parentList) {
			if (file.isDirectory() && (file.listFiles() != null)) {
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file.getPath()));
				node.add(childNode);
				File[] childList = (new File(file.getPath()).listFiles());
				for (File file2 : childList) {
					if (file2.isDirectory()) {
						childNode.add(new DefaultMutableTreeNode(new FileNode(file2.getPath())));
					}
				}
			}
		}
	}

	public void expandTree() {
		if (tree.isCollapsed(tree.getMinSelectionRow())) {
			tree.expandRow(tree.getMinSelectionRow());
		}
	}

	public void collapseTree() {
		if (tree.isExpanded(tree.getMinSelectionRow())) {
			tree.collapseRow(tree.getMinSelectionRow());
		}
	}

	/*******************************/
	/*********** LISTENER **********/
	/*******************************/

	public class DemoTreeSelectionListener implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {

			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (selectedNode != null) {
				FileNode nodeInfo = (FileNode) selectedNode.getUserObject();
				File currentFile = new File(nodeInfo.getFile().getPath());
				// Update RightPanel
				RightPanel.FillList(currentFile);
				RightPanel.setShowDetails(true);
				BigFrame.setTitle(currentFile.getPath());
				// TestApp.buildStatusBar("F:\\");
				if (currentFile.isDirectory()) {
					currentDirectory = currentFile;
					selectedNode.removeAllChildren();
					createChildren(selectedNode);

				}
			}
		}
	}
}
