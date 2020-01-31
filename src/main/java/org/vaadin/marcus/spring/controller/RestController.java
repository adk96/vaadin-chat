package org.vaadin.marcus.spring.controller;

import com.google.gson.Gson;
import org.atmosphere.config.service.Delete;
import org.atmosphere.config.service.Get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vaadin.marcus.spring.model.InputMessage;
import org.vaadin.marcus.spring.model.Message;
import org.vaadin.marcus.spring.model.MessageStatus;
import org.vaadin.marcus.spring.service.MessageService;

import javax.annotation.Resource;
import java.util.List;
import java.util.TimerTask;

@org.springframework.web.bind.annotation.RestController
public class RestController {


    @Resource
    private final MessageService messageService;

    public RestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/api/getall")
    public List<Message> getAll () {
        return messageService.getAllMessages();
    }

    @PostMapping("/api/save")
    public MessageStatus saveMessage(@RequestBody Message chatMessage) {
        return messageService.add(chatMessage);
    }

    @GetMapping("/api/last")
    public List<Message> getLasts() {
        return (messageService.getLast());
    }

    @GetMapping("/api/unread")
    public void getUnreadMessages() {
        
        
    }

    @PostMapping("/api/unread/byid")
    public List<Message> getUnreadById(@RequestBody long message) {
        List<Message> list = messageService.getUnreadById(message);
        return list;
    }

    @DeleteMapping("/api/delete")
    public String clearBase() {
        messageService.deleteMessages();
        return "All messages delete";
    }

    @GetMapping("/get/test")
    public List<Message> getTest() {
        return messageService.getLast();
    }
}
