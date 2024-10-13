package burp;

import burp.api.montoya.ui.contextmenu.ContextMenuEvent;
import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;
import burp.api.montoya.ui.contextmenu.MessageEditorHttpRequestResponse;
import burp.api.montoya.ui.swing.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

import static burp.InsertRepeatedStringExtension.NAME;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

class InsertRepeatedStringContextMenuItemsProvider implements ContextMenuItemsProvider {
    private final SwingUtils swingUtils;

    InsertRepeatedStringContextMenuItemsProvider(SwingUtils swingUtils) {
        this.swingUtils = swingUtils;
    }

    @Override
    public List<Component> provideMenuItems(ContextMenuEvent event) {
        Optional<MessageEditorHttpRequestResponse> messageEditor = event.messageEditorRequestResponse();

        if (messageEditor.isEmpty()) {
            return emptyList();
        }

        Message message = new Message(messageEditor.get());
        Window window = swingUtils.windowForComponent(event.inputEvent().getComponent());

        var menuItem = new JMenuItem(NAME);
        menuItem.addActionListener(new InsertRepeatedStringActionListener(window, message));

        return singletonList(menuItem);
    }
}
