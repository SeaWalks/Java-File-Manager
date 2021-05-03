package CECS277_Project;


import javax.swing.*;
import java.awt.event.*;

public class deleteDLG extends JDialog {
 
	private static final long serialVersionUID = -5610037078916689838L;

	private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField fileNameTextField;

    public deleteDLG() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setSize(400, 200);

        buttonOK.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
			public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

    }

    public void setFileNameTextField(String s){
        fileNameTextField.setText(s);
    }


    public boolean onOK() {

        dispose();
        return true;
    }
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
