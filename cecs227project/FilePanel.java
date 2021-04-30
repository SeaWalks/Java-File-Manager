import javax.swing.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.text.SimpleDateFormat;

import java.awt.event.*;

public class FilePanel extends JPanel {
    private static final long serialVersionUID = 4085105696717794672L;
    private JScrollPane scrollPane = new JScrollPane();
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> myList = new JList<>();
    private File[] fileList;

    public FilePanel() {

        scrollPane.setViewportView(myList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        myList.setModel(model);
        // Listeners: is this good practice to put this shit here?
        myList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {
                    File selectedFile = fileList[myList.getSelectedIndex()];
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
        fileList = file.listFiles();
        model.clear();
        myList.removeAll();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        for (File subfile : fileList) {
            model.addElement(subfile.getName()
            +"     File Size:"
            +subfile.length()
            +"     Date Last Modified: "
            + sdf.format(subfile.lastModified())
            );
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
}