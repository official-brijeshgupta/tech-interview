package interviews.tech.collections;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapExamples {
    public static void execute(){
//        hashMapExamples();
        LRUExamples();
    }

    private static void hashMapExamples(){
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "One");
        map.put(1, "Uno");
        map.put(2, "two");
        map.put(3, "three");

        for(Map.Entry<Integer, String> entry: map.entrySet()){
            System.out.printf("%s -> %s", entry.getKey(), entry.getValue());
        }
    }

    private static void linkedHashMapExamples(){
        LinkedHashMap<Integer, String> map = new LinkedHashMap<>();

        map.put(1, "One");
        map.put(1, "Uno");
        map.put(2, "two");
        map.put(3, "three");


        for(Map.Entry<Integer, String> entry: map.entrySet()){
            System.out.printf("%s -> %s", entry.getKey(), entry.getValue());
        }
    }

    private static void LRUExamples(){
        System.out.println("LRU Cache");
        Map<Integer, String> map = new LRUCache<>(4);

        map.put(1, "One");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        printMap(map);

        map.get(3);
        printMap(map);

        map.put(5, "five");
        printMap(map);

        map.remove(2);
        printMap(map);

        map.put(4, "One");
        printMap(map);
    }

    private static void printMap(Map<Integer, String> map){
        for(Map.Entry<Integer, String> entry: map.entrySet()){
            System.out.printf("%s -> %s%n", entry.getKey(), entry.getValue());
        }

        System.out.println();
    }
}

class LRUCache<K,V> extends LinkedHashMap<K, V>{
    private final int capacity;

    /**
     * The iteration ordering method for this linked hash map: {@code true}
     * for access-order, {@code false} for insertion-order.
     *
     * @serial
     */
    private static final boolean ACCESS_ORDER = true;
    private static final float LOAD_FACTOR = .75f;

    public LRUCache(int capacity){
        super(capacity, LOAD_FACTOR, ACCESS_ORDER);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }
}

//class CustomLRUCache {
//    private final LinkedList<Integer> list;
//    private final Map<Integer, Integer> cache;
//
//    public CustomLRUCache() {
//        list = new LinkedList<>();
//        cache = new HashMap<>();
//
//        list.remove(1)
//    }
//}
