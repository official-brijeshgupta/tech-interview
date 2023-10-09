package interviews.tech.collections;

import java.util.*;

public class SortedCollection {
    public static void main(String[] arg){
//        treeSetExamples();
        treeMapExamples();
//        priorityQueue();
    }

    /**
     * TreeSet - useful to maintain a collection with incoming stream of data in sorted format
     * ordered i.e. order is guarenteed.
     * No duplicates
     *
     * Constructs a new, empty tree set, sorted according to the
     * natural ordering of its elements.  All elements inserted into
     * the set must implement the {@link Comparable} interface.
     * Furthermore, all such elements must be <i>mutually
     * comparable</i>: {@code e1.compareTo(e2)} must not throw a
     * {@code ClassCastException} for any elements {@code e1} and
     * {@code e2} in the set.  If the user attempts to add an element
     * to the set that violates this constraint (for example, the user
     * attempts to add a string element to a set whose elements are
     * integers), the {@code add} call will throw a
     * {@code ClassCastException}.
     */
    private static void treeSetExamples(){

        System.out.println("Tree set Demo.....");
        Set<Integer> set = new TreeSet<>();

        set.add(10);
        set.add(4);
        set.add(1);
        set.add(4);
        set.add(1);
        set.add(10);
        set.add(3);
        set.add(2);


        for(int element: set){
            System.out.println(element);
        }

        System.out.println("is 4 present? " + set.contains(4));
        System.out.println("is 4 removed? " + set.remove(4));
        System.out.println("is 4 present? " + set.contains(4));
    }

    /**
     * TreeMap - helps when we need a sorted collection with duplicates.
     * Can use value as frequency to manage duplicates
     *
     * Constructs a new, empty tree map, using the natural ordering of its
     * keys.  All keys inserted into the map must implement the {@link
     * Comparable} interface.  Furthermore, all such keys must be
     * <em>mutually comparable</em>: {@code k1.compareTo(k2)} must not throw
     * a {@code ClassCastException} for any keys {@code k1} and
     * {@code k2} in the map.  If the user attempts to put a key into the
     * map that violates this constraint (for example, the user attempts to
     * put a string key into a map whose keys are integers), the
     * {@code put(Object key, Object value)} call will throw a
     * {@code ClassCastException}.
     * */
    private static void treeMapExamples(){

        System.out.println("Tree Map Demo.....");
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // Important use  floorEntry or ceilingEntry to find entry before or after a missing entry

        map.put(10, 1);
        map.put(4,1);
        map.put(1,1);
        map.put(4,1);
        map.put(1,1);
        map.put(10,1);
        map.put(3,1);
        map.put(2,1);

        for(Map.Entry element: map.entrySet()){
            System.out.println(element.getKey());
        }


    }

    /**
     * Use PriorityQueue to maintain sorted order with duplicates.
     */
    private static void priorityQueue(){

        System.out.println("PriorityQueue Demo.....");
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(10);
        queue.add(4);
        queue.add(1);
        queue.add(4);
        queue.add(1);
        queue.add(10);
        queue.add(3);
        queue.add(2);

       while(queue.size()>0){
           System.out.println(queue.poll());
       }

        /**
         * Output  - sorted order
         * 1
         * 1
         * 2
         * 3
         * 4
         * 4
         * 10
         * 10
         */

    }
}
