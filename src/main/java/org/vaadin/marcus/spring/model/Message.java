package org.vaadin.marcus.spring.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "chatMessages")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private Timestamp time;
  private String from;
  private String message;
  private boolean unread;

  public Timestamp getTime() {
    return time;
  }

  public void setTime(Timestamp time) {
    this.time = time;
  }

  public Message() {
    this(null, null);
  }

  public Message(String from, String message) {
    this.from = from;
    this.message = message;
    unread = true;
    time = new Timestamp(System.currentTimeMillis());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean isUnread() {
    return unread;
  }

  public void setUnread(boolean unread) {
    this.unread = unread;
  }

  @Override
  public String toString() {
    return "Message{" +
            "id=" + id +
            ", time=" + time +
            ", from='" + from + '\'' +
            ", message='" + message + '\'' +
            ", unread=" + unread +
            '}';
  }
}