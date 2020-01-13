package org.vaadin.marcus.spring.service;

import org.vaadin.marcus.spring.model.Message;

import java.util.List;
import org.vaadin.marcus.spring.model.InputMessage;
import org.vaadin.marcus.spring.model.MessageStatus;

public interface MessageService {
    

    

    List<Message> getAllMessages();
    MessageStatus add(Message message);
    List<Message> getLast();
    List<Message> getUnreadById(InputMessage message);
    String getUnreadMessages();
    void deleteMessages();
    
}
