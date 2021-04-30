import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;

public class FileFrame extends JInternalFrame {
    private static final long serialVersionUID = -4223439902442375914L;

    private JSplitPane splitpane;
    private DirPanel LeftPanel;
    private FilePanel RightPanel;
    public FileFrame(){
        
        LeftPanel = new DirPanel();
        RightPanel = new FilePanel();
        LeftPanel.setFilePanel(RightPanel);

        splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LeftPanel, RightPanel);
        System.out.println("File frame's current directory: " + LeftPanel.getCurrentDirectory());
        //System.out.println("FileFrame gets current file: "+ LeftPanel.getCurrentFile().getPath());
        //Every time leftPanel updates, i need to update the right panel
        splitpane.setOneTouchExpandable(true);
        splitpane.setResizeWeight(.4);
        splitpane.getLeftComponent().setSize((int)(splitpane.getWidth()*.5), splitpane.getHeight());
        this.setTitle("Current folder location goes here.");
        this.getContentPane().add(splitpane);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        this.setSize(400, 300);
        this.setVisible(true);
    }
    public void RefreshFrame(){
        LeftPanel.revalidate();
        RightPanel.revalidate();
    }
    
}



	