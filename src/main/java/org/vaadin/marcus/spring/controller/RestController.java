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

    @Autowired
    

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
        // тут мы вызываем сам таймер
       
    }
    

    @PostMapping("/api/unread/byid")
    public List<Message> getUnreadById(@RequestBody InputMessage message) {
        return messageService.getUnreadById(message);
    }

    @DeleteMapping("/api/delete")
    public String clearBase() {
        messageService.deleteMessages();
        return "Все сообщения были удалены";
    }
}
