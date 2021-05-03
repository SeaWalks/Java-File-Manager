package CECS277_Project;


import java.io.File;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class FileFrame extends JInternalFrame {
    /**  
	 * 
	 */
	private static final long serialVersionUID = 2865150108091306126L;
	private JSplitPane splitpane;
    private final DirPanel LeftPanel;
    private final FilePanel RightPanel;
    App testApp;
    public DirPanel getLeftPanel(){
        return LeftPanel;
    }

    public FilePanel getRightPanel(){
        return RightPanel;
    }

    //Now takes in arguments for the ROOT FOLDER and LOCATION
    public FileFrame(App app, String rootFolder, int xPosition, int yPosition){
    	this.testApp = app;
        File base = new File(rootFolder);
        LeftPanel = new DirPanel(base);
        RightPanel = new FilePanel();
        LeftPanel.setFilePanel(RightPanel);
        LeftPanel.setFileFrame(this);
        setTitle(LeftPanel.getCurrentDirectory().getPath());
        this.addInternalFrameListener(new focusListener());
        splitpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, LeftPanel, RightPanel);
        //System.out.println("FileFrame gets current file: "+ LeftPanel.getCurrentFile().getPath());
        //Every time leftPanel updates, i need to update the right panel
        splitpane.setOneTouchExpandable(true);
        splitpane.setResizeWeight(.3);
        splitpane.getLeftComponent().setSize((int)(splitpane.getWidth()*.5), splitpane.getHeight());
        this.setTitle(LeftPanel.getCurrentDirectory().getPath());
        this.getContentPane().add(splitpane);
        this.setMaximizable(true);
        this.setClosable(true);
        this.setIconifiable(true);
        this.setResizable(true);
        this.setSize(600, 450);
        this.setVisible(true);
        this.setLocation(xPosition,yPosition);
    }
    
    public class focusListener implements InternalFrameListener{

		@Override
		public void internalFrameActivated(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			System.out.println("new frame has been selected..");
			testApp.buildStatusBar(LeftPanel.getCurrentDrive());
			System.out.println(LeftPanel.getCurrentDrive());
		}

		@Override
		public void internalFrameClosed(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameClosing(InternalFrameEvent arg0) {
			
		}

		@Override
		public void internalFrameDeactivated(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameDeiconified(InternalFrameEvent arg0) {
			
		}

		@Override
		public void internalFrameIconified(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void internalFrameOpened(InternalFrameEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    	
    }
}
