package org.vaadin.marcus.spring.model;


public class MessageStatus {

    private String status;

    public String getMessage() {
        return status;
    }

    public void setMessage(String status) {
        this.status = status;
    }

    public MessageStatus(String status) {
        this.status = status;
    }

    public MessageStatus() {
    }
}
