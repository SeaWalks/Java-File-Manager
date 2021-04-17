package cecs227project;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import java.io.*;

public class DirPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JScrollPane scrollPane;
    private JTree tree;
    private DefaultTreeModel treeModel;
    private FileNode mfn;

    // private JTree dirTree = new JTree();
    public DirPanel() {
        buildTree();
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(tree);
        // scrollPane.setViewportView();
        add(scrollPane);
    }

    private void buildTree() {
        tree = new JTree();
        tree.setShowsRootHandles(true);
        FileNode base = new FileNode("C:\\Users\\Eric\\Documents");     //Base should eventually be a parameter
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(base); //Base node in the tree
        File [] fileList = base.getFile().listFiles();                  
        for (File file : fileList){                                     //For every file in the base folder:
            FileNode currentFile = new FileNode(file.getName());
            DefaultMutableTreeNode currentBranch = new DefaultMutableTreeNode(currentFile);
            if(file.isDirectory()){
                root.add(currentBranch);
                createChild(file, currentBranch);
            }else{
                root.add(currentBranch);
            }
        }
        treeModel = new DefaultTreeModel(root);   
        tree.setModel(treeModel);
    }
    private void createChild(File fileRoot, DefaultMutableTreeNode node){
        
        File[] files = fileRoot.listFiles();
            //If there are no files (not a directory)
            if (files == null) return;
            //If there are subfiles (is a directory)
            for (File file : files) {
                DefaultMutableTreeNode childNode = 
                        new DefaultMutableTreeNode(new FileNode(file.getName()));
                node.add(childNode);
                if (file.isDirectory()) {
                    createChild(file, childNode); //Recursion
                }
        }
    }

    public class DemoTreeSelectionListener implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            System.out.println("You selected a node in the tree");
            System.out.println(tree.getMinSelectionRow());
            System.out.println(tree.getSelectionPath());

            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            FileNode mfn = (FileNode) node.getUserObject();
            System.out.println(node);
        }
    }
   
}