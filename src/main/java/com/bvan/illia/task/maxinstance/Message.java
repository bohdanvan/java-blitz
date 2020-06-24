package com.bvan.illia.task.maxinstance;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Message {
    private final String id;
    private final String body;
    private final Instant createdTime;
}
