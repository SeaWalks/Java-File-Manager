package cecs227project;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class DirPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private JScrollPane scrollPane = new JScrollPane();
    private JTree tree;
    private DefaultTreeModel treemodel;
   // private JTree dirTree = new JTree();
    public DirPanel() {
        buildTree();
        scrollPane.setViewportView(tree);
       // scrollPane.setViewportView();
        add(scrollPane);
    }
    private void buildTree(){
        tree = new JTree();
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        treemodel = new DefaultTreeModel(root);
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Node1");
        root.add(node);
        node = new DefaultMutableTreeNode("Node2");
        DefaultMutableTreeNode subnode = new DefaultMutableTreeNode("Subnode2");
        node.add(subnode);
        root.add(node);
        tree.setModel(treemodel);
    }
}