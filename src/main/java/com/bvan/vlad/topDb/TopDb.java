package com.bvan.vlad.topDb;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopDb {

    public List<Entry<String, Long>> findFrequencyOfWords(final Collection<String> words) {

        return setToMap(words).entrySet().stream()
                .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toList());
    }

    private Map<String, Long> setToMap(final Collection<String> words) {
        return words.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); // w -> w
    }
}
