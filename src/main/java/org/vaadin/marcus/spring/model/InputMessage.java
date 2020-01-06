package org.vaadin.marcus.spring.model;

public class InputMessage {

    private long messageId;

    public InputMessage(long messageId) {
        this.messageId = messageId;
    }

    public InputMessage() {
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
