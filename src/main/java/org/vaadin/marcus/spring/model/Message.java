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

  @Column(name = "fromV")
  private String fromV;
  @Column(name = "messageV")
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

  public String getFromV() {
    return fromV;
  }

  public void setFromV(String fromV) {
    this.fromV = fromV;
  }

  public String getMessageV() {
    return messageV;
  }

  public void setMessageV(String messageV) {
    this.messageV = messageV;
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