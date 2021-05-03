package CECS277_Project;


import javax.swing.*;
import java.awt.event.*;

public class DataBack extends JDialog {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7370073082092212851L;
	private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;

    public DataBack() {
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

        // call onCancel() when cross is clicked

    }

    public void setFileNameTextField(String s){
       textField1.setText(s);
    }

    public String getFileNameTextField(){
        return textField2.getText();
    }
    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
