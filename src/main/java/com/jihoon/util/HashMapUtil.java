package com.jihoon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

public class HashMapUtil
{

    private static final Logger logger = LoggerFactory.getLogger(HashMapUtil.class);

    public static HashMap<String, Integer> sortByOrder(HashMap<String, Integer> unsortMap, final boolean order)
    {

        List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());

        // Sorting the list based on values ( Integer )
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> hashMap1, Entry<String, Integer> hashMap2)
            {
                if (order)
                {
                    return hashMap1.getValue().compareTo(hashMap2.getValue());
                }
                else
                {
                    return hashMap2.getValue().compareTo(hashMap1.getValue());
                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        HashMap<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void printHashMap(HashMap<String, Integer> hashMap)
    {
        for (Entry<String, Integer> entry : hashMap.entrySet())
        {
            logger.info(entry.getKey() + " : "+ entry.getValue());
        }
    }
}