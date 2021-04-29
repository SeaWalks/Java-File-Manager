import javax.swing.*;
import java.io.File;

import java.text.SimpleDateFormat;

public class FilePanel extends JPanel {
    private static final long serialVersionUID = 4085105696717794672L;
    private JScrollPane scrollPane = new JScrollPane();
    JList<String> myList;

    public FilePanel() {
        //Initially won't work because myList is null
        scrollPane.setViewportView(myList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
    }

    private void stringBuilder(String[] s) {
        myList = new JList<String>(s);
    }

}