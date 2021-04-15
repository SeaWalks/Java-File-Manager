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
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        treeModel = new DefaultTreeModel(root);
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Node1");
        root.add(node);
        node = new DefaultMutableTreeNode("Node2");
        root.add(node);
        for (int i = 0; i < 10; i++) {
            FileNode myfilenode = new FileNode("Subnode"+i);
            DefaultMutableTreeNode subnode = new DefaultMutableTreeNode(myfilenode);
            node.add(subnode);
        }
        tree.setModel(treeModel);
    }
    public class DemoTreeSelectionListener implements TreeSelectionListener{
        @Override
        public void valueChanged(TreeSelectionEvent e){
            System.out.println("You selected a node in the tree");
            System.out.println(tree.getMinSelectionRow());
            System.out.println(tree.getSelectionPath());
    
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            FileNode mfn = (FileNode) node.getUserObject();
            System.out.println(node);
        }
    }
    
}