package burp;

import burp.view.InsertRepeatedStringDialog;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class InsertRepeatedStringActionListener implements ActionListener {
    private final Window window;
    private final Message message;

    InsertRepeatedStringActionListener(Window window, Message message) {
        this.window = window;
        this.message = message;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var repeatedString = new RepeatedString(message.selection());

        var dialog = new InsertRepeatedStringDialog(window, repeatedString);
        dialog.setVisible(true);

        if (!repeatedString.repeatedText().isEmpty()) {
            message.updateMessage(repeatedString.repeatedText());
        }
    }
}
