package burp.view;

import burp.RepeatedString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static burp.InsertRepeatedStringExtension.NAME;
import static java.awt.Dialog.ModalityType.APPLICATION_MODAL;

public class InsertRepeatedStringDialog extends JDialog {

    public InsertRepeatedStringDialog(Window owner, RepeatedString repeatedString) {
        super(owner, NAME, APPLICATION_MODAL);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setContentPane(new InsertRepeatedStringPanel(repeatedString, this::dispose));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                repeatedString.setText("");
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(owner);
    }
}
