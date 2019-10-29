package com.example.websocketdemo.controller;

import com.example.websocketdemo.model.ChatMessage;
import com.example.websocketdemo.model.Message;
import com.example.websocketdemo.model.User;
import com.example.websocketdemo.service.MessageService;
import com.example.websocketdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Date;


@Controller
public class ChatController {

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/sendToUser/{username}")
    @SendTo("/queue/{username}")
    public ChatMessage sendToUser(@Payload ChatMessage chatMessage,
                               Principal principal,
                               @DestinationVariable String username) {
        System.out.println(principal.getName());
        User from = userService.findUser(principal.getName());
        User to = userService.findUser(username);
        Message message = new Message(from,to,chatMessage.getContent(),false,new Date());
        messageService.save(message);
        return chatMessage;
    }

//    @SubscribeMapping("/secured/app/queue/{username}")
//    public void subscribeOwn(Principal principal,
//                             @DestinationVariable String username) {
//        if(username != principal.getName())
//            //DO NOT SUBSCRIBE
//        else
//            //SUBSCRIBE TO THE QUEUE
//    }

}
