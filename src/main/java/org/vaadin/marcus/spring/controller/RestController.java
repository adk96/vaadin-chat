package org.vaadin.marcus.spring.controller;

import com.google.gson.Gson;
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
    TimerTask timerTask;

    @Resource
    private final MessageService messageService;

    public RestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/api/save")
    public MessageStatus saveMessage(@RequestBody Message chatMessage) {
        return messageService.add(chatMessage);
    }

    @GetMapping("/api/last")
    public String getLasts() {
        return new Gson().toJson(messageService.getLast());
    }

    @GetMapping("/api/unread")
    public void getUnreadMessages() {
        // тут мы вызываем сам таймер
        timerTask.run();
    }

    @GetMapping("/api/unread/byid")
    public List<Message> getUnreadById(@RequestBody InputMessage message) {
        return messageService.getUnreadById(message);
    }

//    @PutMapping("/api/update/{id}")
//    public void updateMessage(@PathVariable long id, @RequestBody Message chatMessage) {
//        messageService.updateMessage(id, chatMessage);
//    }
}
