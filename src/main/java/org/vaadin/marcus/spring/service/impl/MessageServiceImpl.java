package org.vaadin.marcus.spring.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.vaadin.marcus.spring.model.InputMessage;
import org.vaadin.marcus.spring.model.Message;
import org.vaadin.marcus.spring.model.MessageStatus;
import org.vaadin.marcus.spring.repository.MessageRepository;
import org.vaadin.marcus.spring.service.MessageService;

import javax.transaction.Transactional;
import java.util.*;
import java.sql.Timestamp;

import java.util.stream.Collectors;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;
    private final PageRequest lastRequest;

    private List<Long> chekedMessages = new ArrayList<>();


    @Autowired
    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
        lastRequest = new PageRequest(0, 10, Sort.Direction.DESC, "id");
    }

    @Override
    public MessageStatus add(@RequestBody  Message message) {
        if(message == null) {
            System.out.println("An empty request to save data came");

        }
        MessageStatus status = new MessageStatus();
        try {
            repository.save(message);
            status.setMessage("Message Saved");
        }
        catch (Exception e) {
            status.setMessage("Message not saved");
        }
        return status;
    }

    @Override
    public List<Message> getLast() {
        return repository.getLastMessages();
    }

    @Override
    public List<Message> getAllMessages() {
        return repository.getAllfromTable();
    }

//    @Override
//    public List<Message> getLast() {
//        List<Message> result = repository.findAll(lastRequest).getContent();
//
//        return result.stream()
//                .sorted(Comparator.comparingLong(Message::getId))
//                .collect(Collectors.toList());
//    }



    @Override
    public List<Message> getUnreadById(InputMessage message) {
         return repository.getUnreadById(message.getMessageId());
    }



    // Тут реализация метода, который проверяет каждое сообщение с ранее выдаными на уникальность

    @Override
    public String getUnreadMessages() {
        List<Message> out = new ArrayList<>();
        List<Message> unchekedMessages = repository.findAll();
        for (Message message: unchekedMessages) {
            if (!chekedMessages.contains(message.getId())) {
                chekedMessages.add(message.getId());
                out.add(message);
            }
        }
        return new Gson().toJson(out);
    }

    @Override
    public void deleteMessages() {
        repository.clearBase();
    }

    @Override
    public void updateMessage(long id, Message message) {
        if (repository.findById(id).isPresent()) {
            message.setId(id);
            repository.save(message);
        }
    }
}
