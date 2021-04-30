import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.io.*;

public class DirPanel extends JPanel {
	private JScrollPane scrollPane;
	private JTree tree;
	private DefaultTreeModel treeModel;
	private String currentDrive;
	private File currentDirectory;
	FileFrame BigFrame;
	FilePanel RightPanel;

	public void setFilePanel(FilePanel fp){
		RightPanel = fp;
	}

	// Getters for active folders. Returns File objects.
	public String getCurrentDrive() {
		return currentDrive;
	}

	public File getCurrentDirectory() {
		return currentDirectory;
	}
	/////////////BETA TESTING SETTERS///////////////
	public void setCurrentDirectory(File file){
		currentDirectory = file;
	}
	////////////////////////////////////////////////
	public JTree getTree() {
		return tree;
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
		tree.addTreeSelectionListener(new DemoTreeSelectionListener()); //FIGURE THIS SHIT OUT
		
		FileNode base = new FileNode("C:\\"); // Base should eventually be a parameter
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(); // Base node in the tree
		
		currentDirectory = base.getFile();
		root.setUserObject(base);
		createChildren(root);
		treeModel = new DefaultTreeModel(root);
		tree.setModel(treeModel); 
	}
	
	void createChildren(DefaultMutableTreeNode node) {

		FileNode parentNode = (FileNode) node.getUserObject();
		File parentFile = new File(parentNode.getFile().getPath());
		File[] parentList = parentFile.listFiles();

		
		if (parentList != null) {
			for (File file : parentList) {
				if (file.isDirectory() && (file.listFiles()!= null)) {
					DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file.getPath()));
					node.add(childNode);
					File childFile = new File(file.getPath());
					File[] childList = childFile.listFiles();
					if (childList != null) {
						for (File file2 : childList) {
							if (file2.isDirectory()) {
								DefaultMutableTreeNode secondChildNode = new DefaultMutableTreeNode(
										new FileNode(file2.getPath()));
								childNode.add(secondChildNode);
							}
						}
					}			
				}
			}
		}
	}

	public class DemoTreeSelectionListener implements TreeSelectionListener {

		@Override
		public void valueChanged(TreeSelectionEvent e) {

			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			FileNode nodeInfo = (FileNode) selectedNode.getUserObject();

			// If nodeinfo.getFile() is a directory load the next set of folders inside.
			// We won't "unload" shit and its probably bad to

			File currentFile = new File(nodeInfo.getFile().getPath());

			RightPanel.FillList(currentFile);

			/*Debugging tools
			System.out.println("The currently selected file name is: " + currentFile.getAbsolutePath());
			System.out.println("The selected file is directory: " + currentFile.isDirectory());
			System.out.println("Number of Children: " + selectedNode.getChildCount());
			*/
			if (currentFile.isDirectory()) {
				currentDirectory = currentFile;
				selectedNode.removeAllChildren();
				createChildren(selectedNode);
				System.out.println("DirPanel's current directory: "+ currentDirectory.getName());
			}
		}
	}
}