package com.example.websocketdemo.service;


import com.example.websocketdemo.model.Message;
import com.example.websocketdemo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Message save(Message message)
    {
        return messageRepository.save(message);
    }



}
