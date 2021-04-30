import javax.swing.*;
import java.io.File;
import java.awt.*;

public class FilePanel extends JPanel {
    private static final long serialVersionUID = 4085105696717794672L;
    private JScrollPane scrollPane = new JScrollPane();
    DefaultListModel<String> model = new DefaultListModel<>();

  

    JList<String> myList = new JList<>();

    public FilePanel() {

        scrollPane.setViewportView(myList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
        myList.setModel(model);
        add(scrollPane);
    }

    public void FillList(File file){
        File[] fileList = file.listFiles();
        model.clear();
        myList.removeAll();
        for(File subfile : fileList){
            model.addElement(subfile.getName());
        }
    }

    
}