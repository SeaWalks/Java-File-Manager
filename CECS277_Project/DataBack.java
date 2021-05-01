package CECS277_Project;

import javax.swing.*;
import java.awt.event.*;

public class DataBack extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField fromTextField;
    private JTextField toTextField;


    public DataBack() {
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

    public String getToTextField(){
        return toTextField.getText();
    }

    public void setFromTextField(String s){
        fromTextField.setText(s);
    }


    private void onOK() {
        // add your code here
        dispose();
    }



}
