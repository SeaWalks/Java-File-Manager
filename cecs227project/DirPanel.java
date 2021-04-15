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

    /*
     * What we need to do: 1) List all files in the directory 2) Add each file into
     * the root as a filenode 3) For all directories in the current root, generate
     * children for those dudes (recursion?)
     */
    private void buildTree() {
        tree = new JTree();

        FileNode base = new FileNode("C:\\");
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(base); // Create "root" node with C as the base (change
                                                                        // base eventually, maybe get a list of drives
                                                                        // and build multiple trees?)
        treeModel = new DefaultTreeModel(root);                         // Create a tree, set "root" as the first node in the tree
        for (int i = 0; i <  base.getFile().listFiles().length; i++) { // Loop for every detected file in base directory
            FileNode currentFile = new FileNode(base.getFile().listFiles()[i].getName());
            DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(currentFile);
            /*
             * 1) Declare the fileNode 
             * 2) Declare the treeNode 
             * 3) Add the filenode to the treeNode
             */
            if (currentFile.isDirectory()) {
                root.add(parentNode);
                for (int j = 0; j < currentFile.getFile().listFiles().length; j++) {
                    FileNode nestedFile = new FileNode(currentFile.getFile().listFiles()[i].getName());
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(nestedFile);
                    parentNode.add(childNode);
                }
            } else {
                root.add(parentNode);
            }

        }
        /*
         * DefaultMutableTreeNode node = new DefaultMutableTreeNode("Node1");
         * root.add(node); node = new DefaultMutableTreeNode("Node2"); root.add(node);
         * for (int i = 0; i < 10; i++) { FileNode myfilenode = new
         * FileNode("Subnode"+i); DefaultMutableTreeNode subnode = new
         * DefaultMutableTreeNode(myfilenode); node.add(subnode); }
         */
        tree.setModel(treeModel);
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