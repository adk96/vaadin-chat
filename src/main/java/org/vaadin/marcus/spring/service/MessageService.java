package org.vaadin.marcus.spring.service;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.vaadin.marcus.spring.model.InputMessage;
import org.vaadin.marcus.spring.model.Message;
import org.vaadin.marcus.spring.model.MessageStatus;

import java.util.List;

public interface MessageService {
    MessageStatus add(Message message);

    List<Message> getAllMessages();
    List<Message> getLast();
    List<Message> getUnreadById(InputMessage message);

    String getUnreadMessages();

    void updateMessage(long id, Message message);
}
