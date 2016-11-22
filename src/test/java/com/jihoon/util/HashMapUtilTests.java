package com.jihoon.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class HashMapUtilTests {

    @Test
    public void hashMapUtilSortingTest(){

        HashMap<String,Integer> hashMap  = new HashMap<String,Integer>();
        hashMap.put("a",1);
        hashMap.put("b",5);
        hashMap.put("c",3);
        hashMap.put("d",2);
        hashMap.put("e",4);

        HashMap<String,Integer> sortedHashMapASC  = HashMapUtil.sortByOrder(hashMap, true);
        HashMap<String,Integer> sortedHashMapDESC  = HashMapUtil.sortByOrder(hashMap, false);

        HashMapUtil.printHashMap(sortedHashMapASC);

        Integer valueASC = 1;
        for (Map.Entry<String, Integer> entry : sortedHashMapASC.entrySet())
        {
            assertThat(entry.getValue()).isEqualTo(valueASC);
            valueASC++;
        }

        HashMapUtil.printHashMap(sortedHashMapDESC);

        Integer valueDESC = 5;
        for (Map.Entry<String, Integer> entry : sortedHashMapDESC.entrySet())
        {
            assertThat(entry.getValue()).isEqualTo(valueDESC);
            valueDESC--;
        }
    }
}
