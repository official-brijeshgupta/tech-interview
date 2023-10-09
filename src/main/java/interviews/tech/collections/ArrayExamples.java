package interviews.tech.collections;


import java.util.Arrays;

public class ArrayExamples {

    public static void execute(){
        oneDArrayExamples();

    }

    private static void oneDArrayExamples(){

        System.out.println("simple  Array Demo.....");
         int[] arr = new int[10];

        Arrays.fill(arr, 10);



        for(int element: arr){
            System.out.println(element);
        }


    }



}
