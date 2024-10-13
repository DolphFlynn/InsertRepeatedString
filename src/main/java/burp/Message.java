package burp;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.core.Range;
import burp.api.montoya.http.message.HttpMessage;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.contextmenu.MessageEditorHttpRequestResponse;
import burp.api.montoya.ui.contextmenu.MessageEditorHttpRequestResponse.SelectionContext;

import static burp.api.montoya.core.ByteArray.byteArray;
import static burp.api.montoya.core.Range.range;
import static burp.api.montoya.http.message.requests.HttpRequest.httpRequest;
import static burp.api.montoya.http.message.responses.HttpResponse.httpResponse;
import static burp.api.montoya.ui.contextmenu.MessageEditorHttpRequestResponse.SelectionContext.REQUEST;

class Message {
    private final MessageEditorHttpRequestResponse message;

    Message(MessageEditorHttpRequestResponse message) {
        this.message = message;
    }

    String selection() {
        return message.selectionOffsets()
                .map(range -> {
                    SelectionContext selectionContext = message.selectionContext();
                    HttpRequestResponse requestResponse = message.requestResponse();
                    HttpMessage httpMessage = selectionContext == REQUEST ? requestResponse.request() : requestResponse.response();
                    ByteArray messageBytes = httpMessage.toByteArray().subArray(range);

                    return messageBytes.toString();
                })
                .orElse("");
    }

    void updateMessage(String text) {
        SelectionContext selectionContext = message.selectionContext();
        HttpRequestResponse requestResponse = message.requestResponse();
        HttpMessage httpMessage = selectionContext == REQUEST ? requestResponse.request() : requestResponse.response();

        Range selection = message.selectionOffsets().orElse(range(message.caretPosition(), message.caretPosition()));

        ByteArray data = httpMessage.toByteArray();

        ByteArray start = selection.startIndexInclusive() > 0 ?
                data.subArray(0, selection.startIndexInclusive())
                : byteArray("");

        ByteArray end = data.length() > selection.endIndexExclusive()
                ? data.subArray(selection.endIndexExclusive(), data.length())
                : byteArray("");

        ByteArray updatedData = start.withAppended(text).withAppended(end);

       if (selectionContext == REQUEST) {
           message.setRequest(httpRequest(updatedData));
       } else {
           message.setResponse(httpResponse(updatedData));
       }
    }
}
