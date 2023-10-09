package interviews.tech.booking;

import java.util.*;

/**
 * You are given an array of variable pairs equations and an array of real numbers values,
 * where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
 * Each Ai or Bi is a string that represents a single variable.
 *
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must
 * find the answer for Cj / Dj = ?.
 *
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 *
 * Note: The input is always valid. You may assume that evaluating the queries will not result in
 * division by zero and that there is no contradiction.
 *
 * Example 1:
 *
 * Input:
 * equations = [["a","b"],["b","c"]],
 * values = [2.0,3.0],
 * queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * Explanation:
 * Given: a / b = 2.0, b / c = 3.0
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
 *
 * Example 2:
 *
 * Input:
 * equations = [["a","b"],["b","c"],["bc","cd"]],
 * values = [1.5,2.5,5.0],
 * queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * Output: [3.75000,0.40000,5.00000,0.20000]
 *
 * Example 3:
 *
 * Input:
 * equations = [["a","b"]],
 * values = [0.5],
 * queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * Output: [0.50000,2.00000,-1.00000,-1.00000]
 */
public class CurrencyConversion2 {

    public static void main(String[] args){

        List<List<String>> equations = List.of(List.of("a", "b"), List.of("b", "c"), List.of("bc", "cd"));
        double[] values = new double[]{1.5,2.5,5.0};
        List<List<String>> queries = List.of(List.of("a", "c"), List.of("c", "b"), List.of("bc", "cd"), List.of("cd", "bc"));
//        double[] result = calcEquation(equations, values, queries);
        List<Double> calculatedValues = evaluate(equations, values, queries);
    }

    private static List<Double> evaluate(List<List<String>> equations, double[] values, List<List<String>> queries){
        Map<String, Map<String, Double>> graph = createGraph(equations, values);
        List<Double> result = new ArrayList<>();
        for (int i=0; i<queries.size(); i++){
            List<String> query = queries.get(i);
            Double value = search(graph, query, new HashSet<>(), 1d);
            result.add(value);
            System.out.println(query.get(0) + " -> " + query.get(1) + " = " + value);
        }

        return result;
    }

    private static Map<String, Map<String, Double>> createGraph(List<List<String>> equations, double[] values){
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i=0; i<equations.size(); i++){
            List<String> equation = equations.get(i);
            String numerator = equation.get(0);
            String denominator = equation.get(1);
            Double value = values[i];

            Map<String, Double> forwardPath = graph.getOrDefault(numerator, new HashMap<>());
            if(!forwardPath.containsKey(denominator)){
                forwardPath.put(denominator, value);
            }

            graph.put(numerator, forwardPath);


            Map<String, Double> reversePath = graph.getOrDefault(denominator, new HashMap<>());
            if(!reversePath.containsKey(numerator)){
                reversePath.put(numerator, 1d / value);
            }

            graph.put(denominator, reversePath);


        }

        printGraph(graph);
        return graph;
    }

    private static Double search(Map<String, Map<String, Double>> graph, List<String> query, Set<String> visited, Double value){
        String numerator = query.get(0);
        String denominator = query.get(1);

        if(graph.containsKey(numerator) && !visited.contains(numerator)){
            visited.add(numerator);
            Map<String, Double> paths = graph.get(numerator);
            if(paths.containsKey(denominator)){
                return value * paths.get(denominator);
            }else{

                for (Map.Entry<String, Double> path: paths.entrySet()){
                    Double currentValue = value * path.getValue();
                    Double calculatedValue = search(graph, List.of(path.getKey(), denominator), visited, currentValue);

                    if(calculatedValue > 0) return calculatedValue;
                }
            }

        }

        return -1d;
    }


    private static void printGraph(Map<String, Map<String, Double>> graph){
        for(Map.Entry<String, Map<String, Double>> entry: graph.entrySet()){
            String start = entry.getKey();
            Map<String, Double> paths = entry.getValue();

            for(Map.Entry<String, Double> path: paths.entrySet()){
                System.out.printf("%s ---> %s = %s\n", start, path.getKey(), path.getValue());
            }

        }
    }
}
