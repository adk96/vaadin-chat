package org.vaadin.marcus.spring.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import org.vaadin.marcus.spring.model.Message;
import org.vaadin.marcus.spring.service.MessageService;

import javax.annotation.Resource;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Resource
    private final MessageService messageService;

    public RestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/api/save")
    public Message saveMessage(@RequestBody Message chatMessage) {
        return messageService.add(chatMessage);
    }

    @GetMapping("/api/last")
    public String getLasts() {
        return new Gson().toJson(messageService.getLast());
    }

    @GetMapping("/api/unread")
    public List<Message> getUnreadMessages() {
        return messageService.getUnreadMessages();
    }

    @PutMapping("/api/update/{id}")
    public void updateMessage(@PathVariable long id, @RequestBody Message chatMessage) {
        messageService.updateMessage(id, chatMessage);
    }
}
