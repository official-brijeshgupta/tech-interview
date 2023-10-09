package interviews.tech.collections;

import java.util.*;

public class QueueExample {

    public static void execute(){
        queueOperation();
    }


    private static void queueOperation(){
        Queue<Integer> queue = new LinkedList<>() ;

        queue.add(1);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.add(9);

        System.out.printf("Peek - %s%n", queue.peek());
        System.out.printf("Poll - %s%n", queue.poll());
        System.out.printf("Peek - %s%n", queue.peek());
        System.out.printf("Contains 1 %s%n", queue.contains(1));
        System.out.printf(12 + "" + 12);
    }

    private static void dequeueOperation(){
        Deque<Integer> queue = new LinkedList<>() ;

        queue.add(1);
        queue.add(4);
        queue.add(5);
        queue.add(6);
        queue.add(9);

        System.out.printf("Peek - %s%n", queue.peek());
        System.out.printf("Poll - %s%n", queue.poll());
        System.out.printf("Peek - %s%n", queue.peek());
        System.out.printf("Contains 1 %s%n", queue.contains(1));
        System.out.printf(12 + "" + 12);
    }

//    private static void queueWithoutDuplicates(){
//        Deque<Integer> queue = new HashSet<>() ;
//
//        queue.add(1);
//        queue.add(4);
//        queue.add(5);
//        queue.add(6);
//        queue.add(9);
//
//        System.out.printf("Peek - %s%n", queue.peek());
//        System.out.printf("Poll - %s%n", queue.poll());
//        System.out.printf("Peek - %s%n", queue.peek());
//        System.out.printf("Contains 1 %s%n", queue.contains(1));
//        System.out.printf(12 + "" + 12);
//    }

//    private static void dequeueExamples(){
//        Deque<Integer> queue = new LinkedList<>();
//        queue.
//    }

}
