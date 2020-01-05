package org.vaadin.marcus.spring.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vaadin.marcus.spring.service.impl.MessageServiceImpl;

import java.util.Timer;
import java.util.TimerTask;


@Configuration
public class TimerConfig {

// Таймер вызывает каждую секунду метод на загрузку сообщений из БД
    @Autowired
    MessageServiceImpl messageService;

    @Bean
    public TimerTask timer () {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                messageService.getUnreadMessages();
            }

        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);
        return timerTask;
    }
}
