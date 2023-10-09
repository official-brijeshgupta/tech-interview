## Greedy Method
The Greedy method is a simple and intuitive approach that makes locally optimal choices at each step, with the hope that these choices will lead to a globally optimal solution. The Greedy method is called "greedy" because it always chooses the option that appears to be the best at the current moment, without considering the future consequences.

Few famous problems based on Greedy Method
1. **Knapsack Problem**: In the Fractional Knapsack Problem, you are given a set of items, each with a weight and a value, and a knapsack with a maximum weight capacity. The goal is to select items to maximize the total value while keeping the total weight within the knapsack's capacity. The Greedy approach in this problem involves selecting items based on their value-to-weight ratio, choosing the items with the highest ratio first.
2. Dijkstra's Algorithm
3. **Interval Scheduling:** In the Interval Scheduling Problem, you are given a set of intervals, each representing a task or an event with a start time and an end time. The goal is to find the maximum number of non-overlapping intervals that can be scheduled. The Greedy approach here involves sorting the intervals by their end times and selecting the non-overlapping intervals with the earliest end times.
4. **Huffman Coding:** Huffman Coding is a compression algorithm used for lossless data compression. It assigns variable-length codes to different characters based on their frequency of occurrence in the input. The Greedy approach in Huffman Coding involves building a binary tree by repeatedly merging the two characters with the lowest frequencies until all characters are combined into a single tree.
5. **Activity Selection:** In the Activity Selection Problem, you are given a set of activities, each with a start time and a finish time, and you want to select the maximum number of non-overlapping activities. The Greedy approach here involves sorting the activities by their finish times and selecting the activities with the earliest finish times that do not conflict with previously selected activities.



## Dijkstra's Algorithm
Dijkstra's Algorithm is used to find the shortest path between two nodes in a graph with non-negative edge weights. It starts from a source node and iteratively selects the node with the smallest distance from the source, adding it to the visited nodes. The Greedy aspect of this algorithm lies in always selecting the node with the currently smallest distance, assuming that it will lead to the shortest path.





Reference - https://www.youtube.com/watch?v=XB4MIexjvY0

