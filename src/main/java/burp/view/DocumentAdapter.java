package burp.view;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class DocumentAdapter implements DocumentListener {
    private final Runnable action;

    DocumentAdapter(Runnable action) {
        this.action = action;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        action.run();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        action.run();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        action.run();
    }
}
