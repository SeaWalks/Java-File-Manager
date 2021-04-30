import java.io.File;

import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

public class FileFrame extends JInternalFrame {
    private static final long serialVersionUID = -4223439902442375914L;

    private JSplitPane splitpane;
    private DirPanel LeftPanel;
    private FilePanel RightPanel;
    //Now takes in arguments for the ROOT FOLDER and LOCATION
    public FileFrame(String rootFolder, int xPosition, int yPosition){
        System.out.println("rootFolder in fileframe is "+ rootFolder);
        File base = new File(rootFolder);
        System.out.println("base in fileframe is" + base.getName());
        LeftPanel = new DirPanel(base);
        RightPanel = new FilePanel();
        LeftPanel.setFilePanel(RightPanel);
        LeftPanel.setFileFrame(this);
        setTitle(LeftPanel.getCurrentDirectory().getPath());
        splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LeftPanel, RightPanel);
        System.out.println("File frame's current directory: " + LeftPanel.getCurrentDirectory());
        //System.out.println("FileFrame gets current file: "+ LeftPanel.getCurrentFile().getPath());
        //Every time leftPanel updates, i need to update the right panel
        splitpane.setOneTouchExpandable(true);
        splitpane.setResizeWeight(.4);
        splitpane.getLeftComponent().setSize((int)(splitpane.getWidth()*.5), splitpane.getHeight());
        this.setTitle(LeftPanel.getCurrentDirectory().getPath());
        this.getContentPane().add(splitpane);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        this.setSize(400, 300);
        this.setVisible(true);
        this.setLocation(xPosition,yPosition);
       
    }
  
    
}



	