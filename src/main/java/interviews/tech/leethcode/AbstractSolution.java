package interviews.tech.leethcode;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiPredicate;

public class AbstractSolution {
    protected static void print(int[] arr){
        for(int i:arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    protected static <T> String test(T arr, T expected, BiPredicate<T, T> passFunction){
        return executeDelegate(passFunction, arr, expected) ? "passed" : "failed";
    }

    protected static void test(int[] arr, int[] expected){
        BiPredicate<int[], int[]> passFunction = Arrays::equals;
        System.out.println("Result - " + (executeDelegate(passFunction, arr, expected) ? "passed" : "failed"));
        print(arr);
    }

    protected static void test(int arr, int expected){
        BiPredicate<Integer, Integer> passFunction = Objects::equals;
        System.out.println("Result - " + (executeDelegate(passFunction, arr, expected) ? "passed" : "failed"));
    }

    protected static <T> boolean executeDelegate(BiPredicate<T, T> delegate, T arg1, T arg2) {
        return delegate.test(arg1, arg2);
    }
}
