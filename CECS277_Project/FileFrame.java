package CECS277_Project;

import javax.swing.*;

public class FileFrame extends JInternalFrame {
    JSplitPane splitPane;
    public FileFrame(){
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new DirPanel(), new FilePanel());
        this.setTitle("Drive that is currently selected");
        this.getContentPane().add(splitPane);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setClosable(true);
        this.setSize(300, 300);
        this.setVisible(true);
    }
}
