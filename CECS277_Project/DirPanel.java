package CECS277_Project;


import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.File;


public class DirPanel extends JPanel {
    JTree tree = new JTree();
    JScrollPane scrollPane;
    DefaultTreeModel treeModel;

    public DirPanel() {
        buildTree();
        tree.addTreeSelectionListener(new DemoTreeSelectionListener());
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(tree);
        add(scrollPane);
    }

    private void buildTree() {

        tree.setShowsRootHandles(true);
        MyFileNode base = new MyFileNode("C:\\Users\\Iyad\\Documents");
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(base);

        File[] files = base.getFile().listFiles();
        assert files != null;
        for(File file: files){
            MyFileNode currentFile = new MyFileNode(file.getName());
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
                    new DefaultMutableTreeNode(new MyFileNode(file.getName()));
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
           // MyFileNode mfn = (MyFileNode) node.getUserObject();
            System.out.println(node);
        }
    }
}
