package interviews.tech.companies.amazon;

import java.util.PriorityQueue;

public class KClosestPoints {

    public static  void main(String[] args){

    }
    private static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Integer[]> minHeap = new PriorityQueue<Integer[]>(k, (a, b)-> comparator(a,b));


        for(int i=0; i< points.length; i++){
            int[] point = points[i];
            int distance = point[0] * point[0] + point[1] * point[1];
            minHeap.add(new Integer[]{distance, point[0], point[1]});
        }

        int[][]  result = new int[k][2];

        for(int i=0; i< minHeap.size(); i++){
            Integer[] point = minHeap.poll();
            result[i] = new int[]{point[1], point[2]};
        }

        return result;


    }

    private static int comparator(Integer[] a, Integer[] b){
        return a[0] - b[0];
    }
}
