package com.bvan.vlad;

import java.time.Instant;

public class Message {

    String id;
    String body;
    Instant createdDate;

    public Message(String id, String body, Instant createdDate) {
        this.id = id;
        this.body = body;
        this.createdDate = createdDate;
    }
    public Instant getCreatedDate() {
        return createdDate;
    }
}
