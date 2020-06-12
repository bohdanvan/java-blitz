package illia.task.maxinstance;

import static org.assertj.core.api.Assertions.assertThat;

import com.bvan.illia.task.maxinstance.Message;
import com.bvan.illia.task.maxinstance.MessageService;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


public class MessageServiceTest {
    MessageService service = new MessageService();

    @Test
    public void getMaxInstance_should_returnMaxValueOfInstance() {
        Instant maxInstant = Instant.MAX;
        List<List<Message>> list = generateBatchesWithMessages();

        list.get(0).add(new Message("mySupId", "", maxInstant));

        assertThat(service.getMaxInstant(list)).isEqualTo(maxInstant);

    }

    @Test
    public void getMaxInstance_ifAllBachesAreEmpty_shouldReturnNull() {
        List<List<Message>> listOfEmptyBaches = new ArrayList<>();
        List<Message>list = new ArrayList<>();

        listOfEmptyBaches.add(list);

        assertThat(service.getMaxInstant(listOfEmptyBaches)).isNull();
    }

    @Test
    public void getMaxInstance_ifAllBatchesAreEmpty_happyPath() {
        Instant maxInstant = Instant.MAX;
        List<List<Message>> list = generateBatchesWithMessages();

        list.get(1).add(1,new Message("","", maxInstant));
        list.add(new ArrayList<>());

        assertThat(service.getMaxInstant(list)).isEqualTo(maxInstant);
    }

    @Test
    public void getMaxInstance_ifThereAre2MaxValue_shouldReturnMaxValue() {
        Instant maxInstant = Instant.MAX;
        List<List<Message>> list = generateBatchesWithMessages();

        list.get(0).add(new Message("id", "", maxInstant));
        list.get(5).add(new Message("id", "", maxInstant));

        assertThat(service.getMaxInstant(list)).isEqualTo(maxInstant);
    }

    private List<List<Message>> generateBatchesWithMessages() {
        List<List<Message>> listOfBatchesMessages = new ArrayList<>();
        List<Message> batches = new ArrayList();

        for (int i = 0; i < 10; i++) {
            batches.add(new Message("myId" + i, "", Instant.now()));
        }
        for (int i = 0; i < 100; i++) {
            listOfBatchesMessages.add(batches);
        }

        return listOfBatchesMessages;
    }
}
