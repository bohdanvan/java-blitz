package com.bvan.illia.task.topdb;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/*
*add(List<String>) - добавляет слова
top() - возвращает топ 100 самых популярных слов вместе с количеством повторений.
* если некоторые слова повторяются одинаковое кол-во раз,
*  то они должны возвращаться упорядоченными в алфавитном порядке
доп. инфа:
начальная реализация должна быть однопоточной
top() вызывается в 100 раз чаще, чем add()
 */
@Data
@RequiredArgsConstructor
public class TopDb {

    private Map<String, Integer> mapOfWords = new HashMap<>();

    public void add(List<String> words) {
//        for (String word : words) {
//            Integer count = mapOfWords.get(word);
//            mapOfWords.put(word, (count == null) ? 1 : count + 1);
//        }

        mapOfWords = words.stream()
                .collect(Collectors.toMap(String::toString, string -> 1, Integer::sum));
    }

    public Map<String, Integer> top(Map<String, Integer> map) {
        return map.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()).thenComparing(Map.Entry.<String, Integer>comparingByKey().reversed()))
                .limit(100)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
    }
}


