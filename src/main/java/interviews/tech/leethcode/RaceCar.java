package interviews.tech.leethcode;


import java.util.*;

public class RaceCar {
    public static void main(String[] args){

        System.out.println(findMinimumCommandToReachTarget(20));
    }


    private static int findMinimumCommandToReachTarget(int target) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 1}); // starts from position 0 with speed 1

        Set<String> visited = new HashSet<>();
        visited.add(0 + " " + 1);

        int level = 0;
        while(!queue.isEmpty()) {

            int totalPathsToExplore = queue.size();

            for (int k = totalPathsToExplore; k > 0; k--) {
                int[] cur = queue.poll();  // cur[0] is position; cur[1] is speed

                if (cur[0] == target) return level;

                // accelerate instruction
                int[] nxt = new int[]{cur[0] + cur[1], cur[1] * 2};
                String key = (nxt[0] + " " + nxt[1]);

                if (!visited.contains(key) && nxt[0] > 0 && nxt[0] < (target * 2)) {
                    queue.offer(nxt);
                    visited.add(key);
                }

                // reverse instruction
                nxt = new int[]{cur[0], cur[1] > 0 ? -1 : 1};
                key = (nxt[0] + " " + nxt[1]);

                if (!visited.contains(key) && nxt[0] > 0 && nxt[0] < (target * 2)) {
                    queue.offer(nxt);
                    visited.add(key);
                }
            }

            level++;
        }


        return -1;
    }


    //Dijkstra ??
    private int findMinimumCommandToReachTarget_Dijkstra(int target) {
        int K = 33 - Integer.numberOfLeadingZeros(target - 1);

        int barrier = 1 << K;
        int[] dist = new int[2 * barrier + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[target] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> a.steps - b.steps);
        pq.offer(new Node(0, target));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int steps = node.steps, targ1 = node.target;
            if (dist[Math.floorMod(targ1, dist.length)] > steps) continue;

            for (int k = 0; k <= K; ++k) {
                int walk = (1 << k) - 1;
                int targ2 = walk - targ1;
                int steps2 = steps + k + (targ2 != 0 ? 1 : 0);

                if (Math.abs(targ2) <= barrier && steps2 < dist[Math.floorMod(targ2, dist.length)]) {
                    pq.offer(new Node(steps2, targ2));
                    dist[Math.floorMod(targ2, dist.length)] = steps2;
                }
            }
        }

        return dist[0];
    }
}


class Node {
    int steps, target;
    Node(int s, int t) {
        steps = s;
        target = t;
    }
}
