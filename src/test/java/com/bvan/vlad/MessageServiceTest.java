package com.bvan.vlad;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class MessageServiceTest {

    MessageService messageService = new MessageService();

    @Test
    public void lastMessageDate_happyPath_withBigData() {
        // given
        List<List<Message>> batches = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            batches.add(getRandomBatchForBigData());
        }
        Message lastMessage = new Message("id", "last", Instant.now().plusSeconds(1_000_001));
        batches.get(0).add(lastMessage);
        // when
        Instant instant = messageService.lastMessageDate(batches);
        // then
        assertThat(lastMessage.getCreatedDate()).isEqualTo(instant);
    }

    @Test
    public void lastMessageDate_happyPath() {
        // given
        List<List<Message>> batches = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            batches.add(getRandomBatch());
        }
        Message lastMessage = new Message("id", "last", Instant.now().plusSeconds(86400));
        batches.get(0).add(lastMessage);
        batches.add(new ArrayList<>());
        // when
        Instant instant = messageService.lastMessageDate(batches);
        // then
        assertThat(lastMessage.getCreatedDate()).isEqualTo(instant);
    }

    @Test
    public void lastMessageDate_whenBatchesIsEmpty_thenCannotReturnInstant() {
        // given
        List<List<Message>> batches = new ArrayList<>();
        // when
        Instant instant = messageService.lastMessageDate(batches);
        // then
        assertNull(instant);

    }

    @Test
    public void lastMessageDate_whenListsMessagesInBatchesIsEmpty_thenCannotReturnInstant() {
        // given
        List<List<Message>> batches = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        batches.add(messages);
        // when
        Instant instant = messageService.lastMessageDate(batches);
        // then
        assertNull(instant);

    }

    @Test
    public void lastMessageDate_whenListsIsNull_thenCannotReturnInstant() {
        // given
        List<List<Message>> batches = new ArrayList<>();
        batches.add(null);
        // when
        Instant instant = messageService.lastMessageDate(batches);
        // then
        assertNull(instant);

    }

    @Test
    public void lastMessageDate_whenOneBatchNullAndOtherNonNull_thenReturnInstant() {
        // given
        List<List<Message>> batches = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            batches.add(getRandomBatch());
        }
        Message lastMessage = new Message("id", "last", Instant.now().plusSeconds(86400));
        batches.get(0).add(lastMessage);
        batches.add(null);
        // when
        Instant instant = messageService.lastMessageDate(batches);
        // then
        assertThat(lastMessage.getCreatedDate()).isEqualTo(instant);

    }

    @Test
    public void lastMessageDate_whenOneMessageInListNullAndOtherNonNull_thenReturnInstant() {
        // given
        List<List<Message>> batches = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            batches.add(getRandomBatch());
        }
        Message lastMessage = new Message("id", "last", Instant.now().plusSeconds(86400));
        batches.get(0).add(lastMessage);
        batches.get(1).add(null);
        // when
        Instant instant = messageService.lastMessageDate(batches);
        // then
        assertThat(lastMessage.getCreatedDate()).isEqualTo(instant);

    }

    @Test
    public void lastMessageDate_whenInstantInOneMessageNull_thenReturnInstant() {
        // given
        List<List<Message>> batches = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            batches.add(getRandomBatch());
        }
        Message lastMessage = new Message("id", "last", Instant.now().plusSeconds(86400));
        batches.get(0).add(lastMessage);
        batches.get(1).add(new Message("id", "body", null));
        // when
        Instant instant = messageService.lastMessageDate(batches);
        // then
        assertThat(lastMessage.getCreatedDate()).isEqualTo(instant);

    }


    private List<Message> getRandomBatch() {
        List<Message> messages = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {

            messages.add(new Message(Integer.toString(i), "body", Instant.now().plusSeconds(i * 10)));
        }
        return messages;
    }

    private List<Message> getRandomBatchForBigData() {
        List<Message> messages = new ArrayList<>();
        for (int i = 1; i <= 100_000; i++) {

            messages.add(new Message(Integer.toString(i), "body", Instant.now().plusSeconds(i)));
        }
        return messages;
    }

}