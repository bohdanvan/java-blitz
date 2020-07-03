package com.bvan.illia.task.topdb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
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
        for (String word : words) {
            Integer count = mapOfWords.get(word);
            mapOfWords.put(word, (count == null) ? 1 : count + 1);
        }

    }

//    public Set<Entry<String, Integer>> top() {
//
//        List<Entry<String, Integer>> list = new LinkedList<>(mapOfWords.entrySet());
//
//        Set<Entry<String, Integer>> s = new TreeSet<>(new ValueThenKeyComparator());
//
//        s.addAll(mapOfWords.entrySet());
//
//        return s;
//    }
//}

    public List<Entry<String, Integer>> top() {

        List<Entry<String, Integer>> list = new ArrayList<>();

        Queue<Entry<String, Integer>> queue = new PriorityQueue<Entry<String, Integer>>(new ValueThenKeyComparator());

        queue.addAll(mapOfWords.entrySet());

        for (int i = 0; i < 100 ; i++) {
            if (!queue.isEmpty()) {
                list.add(queue.poll());
            }
        }
        return list;
    }
}


class ValueThenKeyComparator<K extends Comparable<? super K>,
            V extends Comparable<? super V>>
            implements Comparator<Map.Entry<K, V>> {

        public int compare(Map.Entry<K, V> a, Map.Entry<K, V> b) {
            int cmp1 = a.getValue().compareTo(b.getValue());
            if (cmp1 != 0) {
                return cmp1;
            } else {
                return a.getKey().compareTo(b.getKey());
            }
        }

    }

    // nlogn
//    public Map<String, Integer> top(Map<String, Integer> map) {
//        return map.entrySet()
//                .stream()
//                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()).thenComparing(Map.Entry.<String, Integer>comparingByKey().reversed()))
//                .limit(100)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
//                        LinkedHashMap::new));
//    }


