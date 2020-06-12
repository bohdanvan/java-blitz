package com.bvan.illia.task.maxinstance;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Message {
    String id;
    String body;
    Instant createdTime;
}
