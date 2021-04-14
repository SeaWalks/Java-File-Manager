package cecs227project;

import javax.swing.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FilePanel extends JPanel {
    private static final long serialVersionUID = 4085105696717794672L;
    private JScrollPane scrollPane = new JScrollPane();
    JList<String> myList = new JList<String>(printDetails());

    public FilePanel() {
        scrollPane.setViewportView(myList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }
    
    private String[] printDetails() { // Eventually take in a string to the selected directory?
        File file = new File("C:\\Users\\Eric\\Documents");/* Send current directory here */
        File[] files = file.listFiles();
        String[] details = new String[files.length];
        for (int i = 0; i < files.length; i++) {

            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy)");
            //DecimalFormat dformat = new DecimalFormat("%,%%%");

            if (files[i].isDirectory()) {
                details[i] = ("Directory" + files[i].getAbsolutePath() + " Date:"
                        + formatter.format(files[i].lastModified()) /*+" Size:" + dformat.format(files[i].length())*/);

            } else {
                details[i] = ("Directory" + files[i].getAbsolutePath() + " Date:"
                        + formatter.format(files[i].lastModified()));
            }
        }
        return details;
    }
}
