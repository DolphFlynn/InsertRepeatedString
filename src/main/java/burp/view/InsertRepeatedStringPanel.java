package burp.view;

import burp.RepeatedString;

import javax.swing.*;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.text.DefaultFormatter;

import static java.awt.event.KeyEvent.VK_ENTER;
import static java.awt.event.KeyEvent.VK_ESCAPE;

class InsertRepeatedStringPanel extends JPanel {
    private final RepeatedString repeatedString;
    private final Runnable closeAction;

    @SuppressWarnings("unused") // NetBeans
    InsertRepeatedStringPanel() {
        initComponents();
        this.repeatedString = null;
        this.closeAction = () -> {};
    }

    InsertRepeatedStringPanel(RepeatedString repeatedString, Runnable closeAction) {
        this.repeatedString = repeatedString;
        this.closeAction = closeAction;

        initComponents();

        txtText.setText(repeatedString.getText());
        txtText.getDocument().addDocumentListener(new DocumentAdapter(this::validatePanel));
        validatePanel();

        spnRepeat.setModel(new SpinnerNumberModel(repeatedString.getCount(), 1, 1000000, 1));

        var editor = new NumberEditor(spnRepeat, "#");
        var formatter = editor.getTextField().getFormatter();

        if (formatter instanceof DefaultFormatter defaultFormatter) {
            defaultFormatter.setAllowsInvalid(false);
            defaultFormatter.setCommitsOnValidEdit(true);
        }

        spnRepeat.setEditor(editor);

        registerKeyboardAction(this::btnOKActionPerformed, KeyStroke.getKeyStroke(VK_ENTER, 0), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        registerKeyboardAction(this::btnCancelActionPerformed, KeyStroke.getKeyStroke(VK_ESCAPE, 0), WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void validatePanel() {
        btnOK.setEnabled(!txtText.getText().isEmpty());
    }

    @SuppressWarnings({"override", "Convert2Lambda", "Anonymous2MethodRef"})
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblText = new javax.swing.JLabel();
        txtText = new javax.swing.JTextField();
        lblRepeat = new javax.swing.JLabel();
        spnRepeat = new javax.swing.JSpinner();
        buttonPanel = new javax.swing.JPanel();
        btnCancel = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 20, 0};
        layout.rowHeights = new int[] {0, 10, 0, 10, 0};
        setLayout(layout);

        lblText.setText("Text");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(lblText, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        add(txtText, gridBagConstraints);

        lblRepeat.setText("Repeat");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(lblRepeat, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        add(spnRepeat, gridBagConstraints);

        buttonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        java.awt.GridBagLayout buttonPanelLayout = new java.awt.GridBagLayout();
        buttonPanelLayout.columnWidths = new int[] {0, 5, 0};
        buttonPanelLayout.rowHeights = new int[] {0};
        buttonPanel.setLayout(buttonPanelLayout);

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        buttonPanel.add(btnCancel, gridBagConstraints);

        btnOK.setText("OK");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        buttonPanel.add(btnOK, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        add(buttonPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        repeatedString.setText(txtText.getText());
        repeatedString.setCount((int) spnRepeat.getValue());
        closeAction.run();

    }//GEN-LAST:event_btnOKActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        repeatedString.setText("");
        closeAction.run();
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JLabel lblRepeat;
    private javax.swing.JLabel lblText;
    private javax.swing.JSpinner spnRepeat;
    private javax.swing.JTextField txtText;
    // End of variables declaration//GEN-END:variables
}
