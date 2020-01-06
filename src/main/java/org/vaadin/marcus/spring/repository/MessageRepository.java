package org.vaadin.marcus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vaadin.marcus.spring.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByUnread(boolean unread);

    @Query(value = "SELECT * FROM chatMessages WHERE id > :id", nativeQuery = true)
    List<Message> getUnreadById(@Param("id") long id);
}