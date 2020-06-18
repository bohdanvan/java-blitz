package com.bvan.vlad;

import java.time.Instant;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MessageService {

    // Memory O(1)
    // Speed O(b^2 * m^2)
    public Instant lastMessageDate(List<List<Message>> batches) {
        // batches empty - NoSuchElementException
        // lists messages in batches empty - NoSuchElementException
        // lists null - NoSuchElementException
        // one batch null and other nonNull - should be work fine
        // one message in list null and other nonNull - should be work fine
        // instant in one message null - should be work fine

        Message lastMessage = batches.stream()
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(message -> message != null && message.getCreatedDate() != null)
                .max(Comparator.comparing(Message::getCreatedDate)).orElse(null);

        if (lastMessage == null){
            return null;
        } else {
            return lastMessage.getCreatedDate();
        }

    }

}
