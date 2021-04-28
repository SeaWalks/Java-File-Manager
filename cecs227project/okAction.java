import java.awt.event.*;

public class okAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e){
        switch (e.getActionCommand()) {
            case "Okay" -> System.out.println("You pressed Okay");
            case "Details" -> System.out.println("You pressed Details");


            case "Simple" -> System.out.println("You pressed Simple");
            //File tab
            case "Rename" -> System.out.println("You pressed Rename in the file tab");
            case "Copy" -> System.out.println("You pressed Copy in the file tab");
            case "Delete" -> System.out.println("You pressed Delete in the file tab");
            case "Run" -> System.out.println("You pressed Run in the file tab");
            case "Exit" -> System.exit(0);

            //Tree tab
            case "Expand Branch" -> System.out.println("You pressed Expand Branch in the tree tab");
            case "Collapse Branch" -> System.out.println("You pressed Collapse Branch in the tree tab");

            //Window tab
            case "New" -> System.out.println("You pressed New in the window tab");
            case "Cascade" -> System.out.println("You pressed Cascade in the window tab");

            //Help tab
            case "Help" -> System.out.println("You pressed Help in the window tab");
            case "About" -> {
                //Aboutdlg dlg = new Aboutdlg();
                //dlg.setVisible(true);
            }
            case "Debug" -> System.out.println("Debugging the Program");
        }
    }
}