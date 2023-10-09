package interviews.tech.euler;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class SumOfMultiplesOf3And5 {
    public static void Execute(int n){
      System.out.printf("The sum of all the multiples of 3 or 5 below %s%n = %s", n, bruteForce(n-1));
      System.out.printf("The sum of all the multiples of 3 or 5 below %s%n = %s", n, usingMaths(n-1));
    }

    //Time complexity O(n)
    private static  int bruteForce(int n){
        int sum = 0;
        for(int i= 1; i<=n; i++){
            if(i%3 == 0 || i%5==0)  sum+=i;
        }

        return sum;
    }

    //Time complexity O(1)
    private static int usingMaths(int n){
        int f3 = n/3;
        int f5 = n/5;
        int f15 = n/15;
        return (f3*3*(f3+1)/2) + (f5*5*(f5+1)/2) - (f15*15*(f15+1)/2);
    }
}
