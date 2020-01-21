package org.vaadin.marcus.spring.config;

public class MessageConfigurator {

    private static MessageConfigurator messageConfigurator = null;
    private final MessagesInfoManager chatMessagesInfoManager = new MessagesInfoManager();

    private MessageConfigurator() {
    }

    public static MessageConfigurator getInstance() {
        if (messageConfigurator == null) {
            messageConfigurator = new MessageConfigurator();
        }
        return messageConfigurator;
    }

    public MessagesInfoManager getChatMessagesInfoManager() {
        return chatMessagesInfoManager;
    }
}
