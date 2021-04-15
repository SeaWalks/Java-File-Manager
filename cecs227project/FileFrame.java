package cecs227project;

import javax.swing.*;

public class FileFrame extends JInternalFrame {
    private static final long serialVersionUID = -4223439902442375914L;


    public FileFrame(){
        
        JSplitPane splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new DirPanel(), new FilePanel());
        splitpane.setResizeWeight(0.3);
        splitpane.getLeftComponent().setSize((int)(splitpane.getWidth()*.5), splitpane.getHeight());
        //splitpane.setPreferredSize(preferredSize);
        this.setTitle("Current folder location goes here.");
        this.getContentPane().add(splitpane);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        this.setSize(200, 200);
        this.setVisible(true);
        
    }

}