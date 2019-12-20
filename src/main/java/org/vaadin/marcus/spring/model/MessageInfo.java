package org.vaadin.marcus.spring.model;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.vaadin.marcus.spring.MessageList;

import java.util.Optional;

public class MessageInfo {

    private MessageList messageList;
    private Message message;
    private VerticalLayout mainView;

    public MessageInfo(MessageList messageList, Message message, VerticalLayout mainView) {
        this.messageList = messageList;
        this.message = message;
        this.mainView = mainView;
    }

    public MessageList getMessageList() {
        return messageList;
    }

    public Message getMessage() {
        return message;
    }

    public Optional<UI> getUI() {
        return mainView.getUI();
    }
}
