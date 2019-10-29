package com.example.websocketdemo.model;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.EnumType.STRING;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "fromuser",nullable = false)
    @ManyToOne
    private User from;

    @JoinColumn(name = "touser",nullable = false)
    @ManyToOne
    private User to;

    @Column(length=65535, columnDefinition = "text",nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean isgroup;

    @Column(nullable = false)
    private Date timestamp = new Date();

    public Message(){}

    public Message(User from, User to, String message, boolean isgroup, Date timestamp) {
        this.from = from;
        this.to = to;
        this.message = message;
        this.isgroup = isgroup;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public User getFrom() {
        return from;
    }

    public User getTo() {
        return to;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public void setGroup(boolean group) {
        isgroup = group;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTo(User to) {
        this.to = to;
    }
}