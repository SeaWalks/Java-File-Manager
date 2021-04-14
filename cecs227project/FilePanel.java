package cecs227project;

import javax.swing.*;

public class FilePanel extends JPanel {
    private static final long serialVersionUID = 4085105696717794672L;
    private JScrollPane scrollPane = new JScrollPane();
    private JTree dirTree = new JTree();
    public FilePanel() {
        scrollPane.setViewportView(dirTree);
        add(scrollPane);
    }
}
