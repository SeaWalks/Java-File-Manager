package CECS277_Project;

import javax.swing.*;

public class FilePanel extends JPanel {
    public FilePanel(){
        JTree fileTree = new JTree();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(fileTree);
        add(scrollPane);
    }
}
