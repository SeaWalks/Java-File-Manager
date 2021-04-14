package cecs227project;

import javax.swing.*;

public class DirPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JScrollPane scrollPane = new JScrollPane();
    private JTree dirTree = new JTree();
    public DirPanel() {
        scrollPane.setViewportView(dirTree);
        add(scrollPane);
    }
}
