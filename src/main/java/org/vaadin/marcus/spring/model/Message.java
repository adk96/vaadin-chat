package org.vaadin.marcus.spring.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;


@Entity
@Table(name = "chatMessages")
public class Message {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private Timestamp time;
  private String fromV;
  private String messageV;
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
    this.fromV = from;
    this.messageV = message;
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
    return fromV;
  }

  public void setFrom(String from) {
    this.fromV = from;
  }

  public String getMessage() {
    return messageV;
  }

  public void setMessage(String message) {
    this.messageV = message;
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
            ", fromV='" + fromV + '\'' +
            ", messageV='" + messageV + '\'' +
            ", unread=" + unread +
            '}';
  }
}