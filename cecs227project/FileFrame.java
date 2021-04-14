package cecs227project;

import javax.swing.JInternalFrame;

public class FileFrame extends JInternalFrame {
    private static final long serialVersionUID = -4223439902442375914L;

    public FileFrame(){
        this.setTitle("Current folder location goes here.");
        this.setMaximizable(true);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        this.setSize(200, 200);
        this.setVisible(true);
    }
}