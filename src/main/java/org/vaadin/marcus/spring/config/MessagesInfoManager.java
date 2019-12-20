package org.vaadin.marcus.spring.config;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Paragraph;
import org.vaadin.marcus.spring.MessageList;
import org.vaadin.marcus.spring.model.Message;
import org.vaadin.marcus.spring.model.MessageInfo;

import java.util.*;
import java.util.stream.Collectors;

public class MessagesInfoManager {

    private final List<MessageInfo> messagesInfoQueue = new ArrayList<>();
    
    private final Map<Optional<UI>, MessageList> messageListMap = new HashMap<>();

    public void updateMessageUIInfo(MessageInfo messageInfo) {
    
        addMessageInfo(messageInfo);
        Message message = messageInfo.getMessage();

        for (Map.Entry<Optional<UI>, MessageList> entry : this.messageListMap.entrySet()) {
            final Optional<UI> uiOptional = entry.getKey();
            final MessageList messageList = entry.getValue();
            uiOptional.ifPresent(ui ->
                    
                    ui.access(() ->
                            messageList.add(new Paragraph(message.getFrom() + ": " + message.getMessage()))
                    ));
        }
    }

    private void addMessageInfo(MessageInfo messageInfo) {
        if (!messagesInfoQueue.contains(messageInfo)) {
            this.messagesInfoQueue
                    .add(messageInfo);
            this.messageListMap.putIfAbsent(messageInfo.getUI(), messageInfo.getMessageList());
        }
    }

    public List<Message> getMessagesByUI(Optional<UI> uiOptional) {
        return messagesInfoQueue
                .stream()
                .filter(messageInfo -> !messageInfo.getUI().equals(uiOptional))
                .map(MessageInfo::getMessage)
                .collect(Collectors.toList());
    }
}
