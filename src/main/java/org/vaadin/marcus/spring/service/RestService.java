package org.vaadin.marcus.spring.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.vaadin.marcus.spring.model.Message;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class RestService {
    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Message saveMessage(Message message) {
        String url = "http://localhost:8080/api/save";

        return this.restTemplate.postForObject(url, message, Message.class);
    }

    public void updateMessage(long id, Message message) {
        String url = String.format("http://localhost:8080/api/update/%d", id);

        this.restTemplate.put(url, message);
    }

    public List<LinkedHashMap> getUnreadMessages(long message) {
        String url = "http://localhost:8080/api/unread/byid";
        return (List<LinkedHashMap>) this.restTemplate.postForObject(url, message, List.class);
    }

    public List<Message> getLast() {
        String url = "http://localhost:8080/api/last";

        String json = restTemplate.getForObject(url, String.class);
        return new Gson().fromJson(json, new TypeToken<List<Message>>(){}.getType());
    }
}
