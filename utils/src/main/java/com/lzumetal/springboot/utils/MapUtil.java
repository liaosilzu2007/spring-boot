package com.lzumetal.springboot.utils;

import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liaosi
 * @date 2020-10-15
 */
public class MapUtil {


    private static <K, V extends Comparable> Map<K, V> sortMapByValues(Map<K, V> map) {
        HashMap<K, V> finalOut = new LinkedHashMap<>();
        map.entrySet()
                .stream()
                .sorted((m1, m2) -> m1.getValue().compareTo(m2.getValue())
                )
                .collect(Collectors.toList()).forEach(ele -> finalOut.put(ele.getKey(), ele.getValue()));
        return finalOut;
    }


    private static Map<Integer, Long> groupStat(Map<?, Integer> map) {
        if (CollectionUtils.isEmpty(map)) {
            return Collections.emptyMap();
        }
        Map<Integer, Long> groupMap = new HashMap<>();
        for (Integer value : map.values()) {
            Long count = groupMap.computeIfAbsent(value, v -> 0L);
            count = count + 1;
            groupMap.put(value, count);
        }
        return groupMap;
    }


}
