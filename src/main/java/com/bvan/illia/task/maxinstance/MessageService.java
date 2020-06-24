package com.bvan.illia.task.maxinstance;

import java.time.Instant;
import java.util.List;

public class MessageService {
    public Instant getMaxInstant(List<List<Message>> messages) {

        return messages.stream().flatMap(List::stream)
                .map(Message::getCreatedTime).max(Instant::compareTo)
                .orElse(null);
    }

    public static void main(String[] args) {
    }
}
