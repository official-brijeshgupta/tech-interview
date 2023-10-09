package interviews.tech.algo.greedy;

import org.springframework.data.util.Pair;

import java.util.*;

public class Dijkstra {
    public static void main(String[] arg){
        Map<Integer, Node> temp = new HashMap<>();

        Graph graph = new Graph();
        for(int i=1; i<=6; i++) {
            Node node = new Node(i);
            graph.addNode(node);
            temp.put(i, node);
        }

        //Add Edges
        temp.get(1).addDestination(temp.get(2), 2);
        temp.get(1).addDestination(temp.get(3), 4);
        temp.get(2).addDestination(temp.get(4), 7);
        temp.get(2).addDestination(temp.get(3), 1);
        temp.get(3).addDestination(temp.get(5), 3);
        temp.get(4).addDestination(temp.get(6), 1);
        temp.get(5).addDestination(temp.get(4), 2);
        temp.get(5).addDestination(temp.get(6), 5);

    }

    private static int findShortestPath(Node source, Graph graph) {


        Set<Node> visitedNode = new HashSet<>();
        Set<Node> allNodes = new HashSet<>();

        Map<Node, Pair<Node, Integer>> record = new HashMap<>();
        record.put(source,Pair.of(source,0));

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            Node visited = queue.poll();

//            for(Map.Entry<Node, Integer> entry: visited.getAdjacentNodes().entrySet()){
//                queue.offer(visited.getAdjacentNodes().get())
//            }
        }

        return -1;
    }
}

class Graph {

    private final Set<Node> nodes;

    public Graph(){
        nodes = new HashSet<>();
    }

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    // getters and setters
}

class Node {

    private final int name;

//    private List<Node> shortestPath = new LinkedList<>();

//    private Integer distance = Integer.MAX_VALUE;

    private final Map<Node, Integer> adjacentNodes ;

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(int name) {
        this.name = name;
        this.adjacentNodes = new HashMap<>();
    }

    public int getName() {
        return name;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    // getters and setters
}


//
//class Node {
//    private final int value;
//
//    public Node(int val) {
//        this.value = val;
//    }
//
//    public int getValue() {
//        return value;
//    }
//}
//
//class Edge {
//    private final int distance;
//    private final Node from, to;
//
//    public Edge(Node from, Node to, int distance) {
//        this.distance = distance;
//        this.from = from;
//        this.to = to;
//    }
//
//    public int getDistance() {
//        return distance;
//    }
//
//    public Node getFrom() {
//        return from;
//    }
//
//    public Node getTo() {
//        return to;
//    }
//}
