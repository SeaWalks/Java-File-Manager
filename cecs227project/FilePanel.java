import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.*;
import java.awt.dnd.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class FilePanel extends JPanel {
    private static final long serialVersionUID = 4085105696717794672L;
    private JScrollPane scrollPane = new JScrollPane();
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> myList = new JList<>();
    private File[] fileList;
    private File selectedFile, selectedDirectory, copiedFile;
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
                    System.out.println("Selected file is: " + selectedFile.getName());
                    // runFile(selectedFile);
                }
                if (me.getClickCount() == 2) {
                    File selectedFile = fileList[myList.getSelectedIndex()];
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
        File[] sorted = new File[fileList.length];
        int counter = 0;
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isDirectory()) {
                sorted[counter] = fileList[i];
                counter++;// Only increment counter when something is added
            }
        }
        for (int i = 0; i < fileList.length; i++) {
            if (!fileList[i].isDirectory()) {
                sorted[counter] = fileList[i];
                counter++;// Only increment counter when something is added
            }
        }
        fileList = sorted;

        model.clear();
        myList.removeAll();
        myList.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
        // Debugg test: System.out.println("Show details is" + showDetails);

        if (showDetails) {
            for (File subfile : sorted) {
                if (!subfile.isDirectory()) {
                    model.addElement(getDetails(subfile));
                } else {
                    model.addElement(subfile.getName());
                }
            }
        } else {
            for (File subfile : sorted) {
                model.addElement(subfile.getName());
            }
        }
    }

    private String getDetails(File file) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return String.format("%-40s %-20s %20s", file.getName(), (file.length() / 1024) + "KB",
                sdf.format(file.lastModified()));
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
        // Slightly better way, only requires the user to type in only the
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

    public void setCopiedFile(File file) {
        copiedFile = file;
    }

    public void pasteFile(String s) throws IOException {
        /*
         * Basically the way this works is that we have a file caleld copiedFile which
         * is the file to be copied. We then pass a file into this method, which will be
         * the "place" this will be created. This will typically be
         * selectedDirectory.getPath()+"\\"+copiedFile.getName() . However, the copy
         * dialogue box requires the user to specify a directory, so thats why this
         * takes in a string.
         */
        File newFile = new File(s);
        try {
            FileInputStream ins = new FileInputStream(copiedFile);
            FileOutputStream outs = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = ins.read(buffer)) > 0) {
                outs.write(buffer, 0, length);
            }
            ins.close();
            outs.close();
            System.out.println("File copied successfully.");
        } catch (FileNotFoundException ioe) {
            ioe.printStackTrace();
        }

    }

    public void setShowDetails(Boolean toggle) {
        showDetails = toggle;
        FillList(selectedDirectory); // Redraw the list after changing the boolean
    }

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
                List result = new ArrayList();
                // what is being dropped? First, Strings are processed
                if (evt.getTransferable().isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    String temp = (String) evt.getTransferable().getTransferData(DataFlavor.stringFlavor);
                    // String events are concatenated if more than one list item
                    // selected in the source. The strings are separated by
                    // newline characters. Use split to break the string into
                    // individual file names and store in String[]
                    String[] next = temp.split("\\n");
                    // add the strings to the listmodel
                    for (int i = 0; i < next.length; i++) {
                        model.addElement(next[i]);
                        setCopiedFile(new File(next[i]));
                        File pastedFile = new File(selectedDirectory.getPath() + "\\" + copiedFile.getName());
                        pasteFile(pastedFile.getPath());
                    }
                } else { // then if not String, Files are assumed
                    result = (List) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    // process the input
                    for (Object o : result) {
                        // System.out.println(o.toString()+"MOM WE DID IT");
                        model.addElement(o.toString());
                        setCopiedFile(new File(o.toString()));
                        File pastedFile = new File(selectedDirectory.getPath() + "\\" + copiedFile.getName());
                        pasteFile(pastedFile.getPath());
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }
        }

    }

}
