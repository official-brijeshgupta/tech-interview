package interviews.tech.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExamples {

    public static void execute(){
        hashSetExamples();
    }

    /**
     * Unordered i.e. order is not guarenteed.
     * No duplicates
     */
    private static void hashSetExamples(){

        System.out.println("Hash set Demo.....");
        Set<Integer> set = new HashSet<>();

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
        System.out.println("is 4 removed? " + set.remove(4));
        System.out.println("is 4 present? " + set.contains(4));
    }




}
