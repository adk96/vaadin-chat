package org.vaadin.marcus.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.vaadin.marcus.spring.model.Message;
import org.vaadin.marcus.spring.repository.MessageRepository;
import org.vaadin.marcus.spring.service.MessageService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.sql.Timestamp;
import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    private final MessageRepository repository;
    private final PageRequest lastRequest;

    @Autowired
    public MessageServiceImpl(MessageRepository repository) {
        this.repository = repository;
        lastRequest = new PageRequest(0, 10, Sort.Direction.DESC, "id");
    }

    @Override
    public Message add(Message message) {
        message.setTime(new Timestamp(new Date().getTime()));
        return repository.saveAndFlush(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return repository.findAll();
    }

    @Override
    public List<Message> getLast() {
        List<Message> result = repository.findAll(lastRequest).getContent();

        return result.stream()
                .sorted(Comparator.comparingLong(Message::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> getUnreadMessages() {
        return repository.findAllByUnread(true);
    }

    @Override
    public void updateMessage(long id, Message message) {
        if (repository.findById(id).isPresent()) {
            message.setId(id);
            repository.save(message);
        }
    }
}
