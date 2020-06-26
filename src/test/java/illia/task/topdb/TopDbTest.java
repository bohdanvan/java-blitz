package illia.task.topdb;

import com.bvan.illia.task.topdb.TopDb;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TopDbTest {

    @Test
    public void top_sholdreturn100sortedelements() {
        Map<String, Integer> espect = new HashMap<>();
        TopDb actual = new TopDb();

        espect.put("A", 250);
        espect.put("B", 250);
        espect.put("C", 500);
        espect.entrySet().stream().sorted((Map.Entry.<String, Integer>comparingByValue().reversed())).collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        actual.add(generate1000words());

        Assertions.assertThat(actual.top(actual.getMapOfWords())).size().isEqualTo(3);
        Assertions.assertThat(actual.top(actual.getMapOfWords())).isEqualTo(espect);

    }

    @Test
    public void top_whenListContainsEqualValue_sholdSortByKey() {
        TopDb actual = new TopDb();
        Map<String, Integer> espect = new HashMap<>();

        espect.put("B", 250);
        espect.put("A", 250);
        espect.put("C", 500);
        espect.entrySet().stream().sorted((Map.Entry.<String, Integer>comparingByValue().reversed())).collect(Collectors
                .toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new)); // for save sorted order

        actual.add(generate1000words());

        Assertions.assertThat(actual.top(actual.getMapOfWords())).isEqualTo(espect);
    }

    @Test
    public void top_whenListIsEmpty_sholdreturnEmptyMap() {
        TopDb actual = new TopDb();
        actual.add(Collections.emptyList());

        Map<String, Integer> actualMap = actual.getMapOfWords();

        Assertions.assertThat(actual.top(actualMap).isEmpty());
    }

    @Test
    public void top_whenTopWillCall100Times_happyPath() {
        TopDb actual = new TopDb();

        actual.add(generate1000words());

        for (int i = 0; i < 100; i++) {
            actual.top(actual.getMapOfWords());
        }
        Assertions.assertThat(actual.getMapOfWords().size()).isEqualTo(3);
    }

    private List<String> generate1000words() {
        List<String> arrayList = new LinkedList<>();
        for (int i = 0; i < 250; i++) {
            arrayList.add("A");
        }

        for (int i = 0; i < 250; i++) {
            arrayList.add("B");
        }
        for (int i = 0; i < 500; i++) {
            arrayList.add("C");
        }

        return arrayList;
    }
}
