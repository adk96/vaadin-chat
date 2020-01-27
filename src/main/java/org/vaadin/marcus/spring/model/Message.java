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
  private String fromv;
  @Column(name = "messageV")
  private String messagev;
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
    this.fromv = from;
    this.messagev = message;
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
    return fromv;
  }

  public void setFromV(String fromV) {
    this.fromv = fromV;
  }

  public String getMessageV() {
    return messagev;
  }

  public void setMessageV(String messageV) {
    this.messagev = messageV;
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
            ", from='" + fromv + '\'' +
            ", message='" + messagev + '\'' +
            ", unread=" + unread +
            '}';
  }
}