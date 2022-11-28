package Experiment.E4;

import java.util.*;

/**
 * @author 翟俊华
 */
public class MapAndTreeTest {
    public static void main(String[] args) {
        MapAndTreeTest mapAndTree_test = new MapAndTreeTest();
        mapAndTree_test.hashMap_Test();
        mapAndTree_test.treeSet_Test();
        mapAndTree_test.hashSet_Test();
        mapAndTree_test.treeMap_Test();
    }
    public void hashMap_Test(){
        System.out.println("------HashMap Test Start------");

        HashMap<String, Integer> hashMap = new HashMap<>();

        System.out.println("Putting key-value into HashMap...");
        hashMap.put("Test_1", 1);
        hashMap.put("Test_2", 2);
        hashMap.put("Test_3", 3);
        hashMap.put("Test_4", 4);

        System.out.println("Removing from HashMap where key = Test_3...");
        hashMap.remove("Test_3");

        System.out.print("HashMap after operations: ");
        System.out.println(hashMap);
        System.out.println();
    }

    public void hashSet_Test(){
        System.out.println("------HashSet Test Start------");

        System.out.println("Putting value into HashSet...");
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.add(3);
        hashSet.add(4);

        System.out.println("Removing from HashSet where value = 3...");
        hashSet.remove(3);

        System.out.print("HashSet after operations: ");
        System.out.println(hashSet);
        System.out.println();
    }

    public void treeMap_Test(){
        System.out.println("------TreeMap Test Start------");

        TreeMap<String, Integer> treeMap = new TreeMap<>();

        System.out.println("Putting key-value into TreeMap...");
        treeMap.put("Test_1", 1);
        treeMap.put("Test_2", 2);
        treeMap.put("Test_3", 3);
        treeMap.put("Test_4", 4);

        System.out.println("Removing from TreeMap where key = Test_3...");
        treeMap.remove("Test_3");

        System.out.print("TreeMap after operations: ");
        System.out.println(treeMap);
        System.out.println();
    }

    public void treeSet_Test(){
        System.out.println("------TreeSet Test Start------");

        TreeSet<Integer> treeSet = new TreeSet<>();

        System.out.println("Putting value into TreeSet...");
        treeSet.add(1);
        treeSet.add(2);
        treeSet.add(3);
        treeSet.add(4);

        System.out.println("Removing from TreeSet where value = 3...");
        treeSet.remove(3);

        System.out.print("TreeSet after operations: ");
        System.out.println(treeSet);
        System.out.println();
    }
}
