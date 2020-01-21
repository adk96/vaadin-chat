package org.vaadin.marcus.spring.service;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.vaadin.marcus.spring.model.InputMessage;
import org.vaadin.marcus.spring.model.Message;
import org.vaadin.marcus.spring.model.MessageStatus;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessages();
    MessageStatus add(Message message);
    List<Message> getLast();
    List<Message> getUnreadById(InputMessage message);
    String getUnreadMessages();
    void deleteMessages();
    void updateMessage(long id, Message message);
}
