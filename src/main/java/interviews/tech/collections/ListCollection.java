package interviews.tech.collections;

import java.util.*;

public class ListCollection {

    public static void execute(){
        binarySearch();
    }

    /**
     * Returns index of key in sorted list sorted in ascending order.
     *
     * Returns index of key in sorted list sorted in order defined by Comparator c.
     *
     *  If key is not present, then it returns "(-(insertion point) - 1)".
     *  The insertion point is defined as the point at which the key
     *  would be inserted into the list.
     */
    private static void binarySearch(){
        List<Integer> list = new ArrayList<Integer>();

        list.add(1);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(9);

        int num = Collections.binarySearch(list, 3);
        int num2 = Collections.binarySearch(list, 4);
        int num3 = Collections.binarySearch(list, 10);



        if(num >=0){
            System.out.printf("Found %s at index %s%n", 3, num);
        }else{
            System.out.printf("%s is missing at index %s%n", 3, Math.abs(num + 1));
        }

        if(num2 >=0){
            System.out.printf("Found %s at index %s%n", 4, num2);
        }else{
            System.out.printf("%s is missing at index %s%n", 4, Math.abs(num2 + 1));
        }

        if(num >=0){
            System.out.printf("Found %s at index %s%n", 10, num3);
        }else{
            System.out.printf("%s is missing at index %s%n", 10, Math.abs(num3 + 1));
        }
    }



    private static void setExamples(){

        Set<Integer> set = new LinkedHashSet<>();

        set.add(1);
        set.add(3);
        set.add(1);
        set.add(4);


    }


    private static void linkedList(){
        LinkedList<Integer> list = new LinkedList<>();

    }


}
