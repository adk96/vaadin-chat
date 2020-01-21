package org.vaadin.marcus.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.vaadin.marcus.spring.config.MessagesInfoManager;
import org.vaadin.marcus.spring.config.MessageConfigurator;
import org.vaadin.marcus.spring.model.Message;
import org.vaadin.marcus.spring.model.MessageInfo;
import org.vaadin.marcus.spring.service.RestService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@StyleSheet("frontend://styles/styles.css")
@Route
@PWA(name = "Vaadin MessagesInfoManager", shortName = "Vaadin MessagesInfoManager")
@Push
@StyleSheet("frontend://styles/styles.css")

public class MainView extends VerticalLayout {

    private final MessagesInfoManager messagesInfoManager;
    private final RestService restService;
    private String username;

    private int count = 0;

    @Autowired
    public MainView(RestService restService) {
        this.messagesInfoManager = MessageConfigurator.getInstance().getChatMessagesInfoManager();
        addClassName("main-view");
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);

        H1 header = new H1("Vaadin Chat");
        header.getElement().getThemeList().add("dark");

        add(header);

        askUsername();
        this.restService = restService;
    }

    private void askUsername() {
        HorizontalLayout layout = new HorizontalLayout();
        TextField usernameField = new TextField();
        Button startButton = new Button("Start chat");

        layout.add(usernameField, startButton);

        startButton.addClickListener(click -> {
            username = usernameField.getValue();
            remove(layout);
            showChat(username);
        });

        add(layout);
    }

    MessageList messageList;

    private void showChat(String username) {
        messageList = new MessageList();

        List<Message> lasts = restService.getLast();
        for (Message message : lasts) {
            messageList.add(new Paragraph(message.getFrom() + ": " + message.getMessage()));
        }

        add(messageList, createInputLayout(username, messageList));
        expand(messageList);
    }

    private Component createInputLayout(String username, MessageList messageList) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidth("100%");

        TextField messageField = new TextField();
        messageField.addKeyDownListener(Key.ENTER, keyDownEvent -> sender(messageField, messageList));
        Button sendButton = new Button("Send");
        sendButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        layout.add(messageField, sendButton);
        layout.expand(messageField);

        messageField.addFocusListener(event -> {
            for (Message message : messagesInfoManager.getMessagesByUI(getUI())) {
                if (!message.getFrom().equals(username)) {
                    message.setUnread(false);
                    this.restService.updateMessage(message.getId(), message);
                }
            }
        });

        sendButton.addClickListener(click -> sender(messageField, messageList));
        messageField.focus();

        return layout;
    }

    private void sender(TextField textField, MessageList messageList) {
        Message message = new Message(username, textField.getValue());
        message = restService.saveMessage(message);
        messagesInfoManager.updateMessageUIInfo(new MessageInfo(messageList, message, this));
        textField.clear();
        textField.focus();
    }

    @Scheduled(fixedDelay = 1000)
    public void test() {
        count++;
        System.out.println("Hello" + count);

        this.getUI().get().access(() -> {

            if (messageList != null) {
                messageList.add(new Paragraph("From me" + ": " + "Hello" + count));
            }

        });

    }
}
