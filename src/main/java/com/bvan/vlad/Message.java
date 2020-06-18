package com.bvan.vlad;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {

    private final String id;
    private final String body;
    private final Instant createdDate;

    public Message(String id, String body, Instant createdDate) {
        this.id = id;
        this.body = body;
        this.createdDate = createdDate;
    }
}
