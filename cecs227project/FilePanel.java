import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.dnd.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class FilePanel extends JPanel {
    private static final long serialVersionUID = 4085105696717794672L;
    private JScrollPane scrollPane = new JScrollPane();
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> myList = new JList<>();
    private File[] fileList;
    private File selectedFile;
    private File selectedDirectory;
    private Boolean showDetails;

    public FilePanel() {
        showDetails = false;
        scrollPane.setViewportView(myList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        myList.setModel(model);
        this.setDropTarget(new MyDropTarget());
        myList.setDragEnabled(true);
        // Listeners: is this good practice to put this here?
        myList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 1) {
                    File selectedFile = fileList[myList.getSelectedIndex()];
                    System.out.println("Selected file is: " + selectedFile.getPath());
                }
                if (me.getClickCount() == 2) {
                    System.out.println("You double clicked on " + myList.getSelectedValue());
                    System.out.println("Attempting to open..." + myList.getSelectedIndex());
                    runFile(selectedFile);
                }
            }
        });
        myList.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    runFile(new File(myList.getSelectedValue()));
                }
            }
        });
        add(scrollPane);
    }

    public void FillList(File file) {
        selectedDirectory = file;
        fileList = file.listFiles();
        model.clear();
        myList.removeAll();
        
        // Debugg test: System.out.println("Show details is" + showDetails);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        if (showDetails) {
            for (File subfile : fileList) {
                if (!subfile.isDirectory()) {
                    model.addElement(subfile.getName() + "     File Size:" + subfile.length()
                            + "     Date Last Modified: " + sdf.format(subfile.lastModified()));
                } else {
                    model.addElement(subfile.getName());
                }
            }
        } else {
            for (File subfile : fileList) {
                model.addElement(subfile.getName());
            }
        }
    }

    // Takes in a whole ass pathname to rename the selected file.
    // PATHNAME MUST BE IN THE SAME DIRECTORY OR THINGS R FUK
    public void renameFile(String pathname) {
        // The way he specifies in the PDF:
        File newFile = new File(pathname);
        if (selectedFile.renameTo(newFile)) {
            System.out.println("File succesfully renamed to: " + newFile.getName());
        } else {
            System.out.println("Something went wrong renaming the file.");
        }
        // Slightly better waay, only requires the user to type in only the
        // new file name without the directory path stuff.
        /*
         * File betterFile = new File(selectedFile.getParent()+pathname); if
         * (selectedFile.renameTo(newFile)){
         * System.out.println("File succesfully renamed to: "+ betterFile.getName());
         * }else{ System.out.println("Something went wrong renaming the file."); }
         */
    }

    public File getFile() {
        return selectedFile;
    }

    public void deleteSelectedFile() {
        try {
            System.out.println("Deleting File at: " + selectedFile.getPath());
            selectedFile.delete();
        } catch (Exception e) {
            System.out.println("Something went wrong when deleting the file.");
        }
    }

    public void runFile(File file) {
        Desktop desktop = Desktop.getDesktop();
        if (file.exists())
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void setShowDetails(Boolean toggle) {
        showDetails = toggle;
        FillList(selectedDirectory); // Redraw the list after changing the boolean
    }

    /*
     * Drag and drop bullshit, we are close to finishing this though maybe 80% of
     * the Drag and drop shit is implemented here. just need to fix the professors
     * code and implement the copying methods
     */
    class MyDropTarget extends DropTarget {
        /**************************************************************************
         * 
         * @param evt the event that caused this drop operation to be invoked
         */
        public void drop(DropTargetDropEvent evt) {
            try {
                // types of events accepted
                evt.acceptDrop(DnDConstants.ACTION_COPY);
                // storage to hold the drop data for processing
                // List result = new ArrayList();
                ArrayList<Object> result = new ArrayList<Object>();
                // what is being dropped? First, Strings are processed
                if (evt.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String temp = (String) evt.getTransferable().getTransferData(DataFlavor.stringFlavor);
                    // String events are concatenated if more than one list item
                    // selected in the source. The strings are separated by
                    // newline characters. Use split to break the string into
                    // individual file names and store in String[]
                    String[] next = temp.split("\\n");
                    // add the strings to the listmodel
                    for (int i = 0; i < next.length; i++)
                        model.addElement(next[i]);
                    /*
                     * next[i] *should* be a String that gives a filepath to a new file that needs
                     * to be copied into the current directory, given by selectedDirectory.
                     * Implement copy behavior here.
                     */
                } else { // then if not String, Files are assumed
                    result = (ArrayList) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor); // what
                                                                                                               // the
                                                                                                               // fuck
                                                                                                               // professor
                    // process the input
                    for (Object o : result) {
                        System.out.println(o.toString());
                        model.addElement(o.toString());
                        /*
                         * Object o *should* be a File object that needs to be copied into the current
                         * directory, given by selectedDirectory. Implement copy behavior here. I think.
                         */
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
}