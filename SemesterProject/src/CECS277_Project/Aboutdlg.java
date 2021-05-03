package CECS277_Project;

import javax.swing.*;
import java.awt.event.*;

public class Aboutdlg extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1417250597309717826L;
	private JPanel contentPane;
    private JButton buttonOK;

    public Aboutdlg() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setSize(400, 200);
        buttonOK.addActionListener(new okAction() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }
}

