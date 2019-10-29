package com.example.websocketdemo.repository;

import com.example.websocketdemo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message,Long> {
}
