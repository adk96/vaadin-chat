package org.vaadin.marcus.spring.service;

import org.vaadin.marcus.spring.model.Message;

import java.util.List;

public interface MessageService {
    Message add(Message message);

    List<Message> getAllMessages();
    List<Message> getLast();

    List<Message> getUnreadMessages();

    void updateMessage(long id, Message message);
}
