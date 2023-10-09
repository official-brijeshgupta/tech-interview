package interviews.tech.amazon;

import java.util.*;

public class AnalyzePattern {

    public static void main(String[] args){

        String[] username = new  String[]{"joe","joe","joe","james","james","james","james","mary","mary","mary"};
        int[] timestamp = new  int[]{1,2,3,4,5,6,7,8,9,10};
        String[] website = new  String[]{"home","about","career","home","cart","maps","home","home","about","career"};

        List<String> result = mostVisitedPattern(username, timestamp, website);

        System.out.println("Result " + String.join(",", result));
    }

    public static  List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        return approach1(username,timestamp, website);
    }

    private static List<String> approach1(String[] username, int[] timestamp, String[] website) {
        List<String> result = new ArrayList<>();

        Map<String, UserPattern> map = new HashMap<>();

        //create pattern per user
        for(int i=0; i< username.length; i++){
            String uname = username[i];
            String page = website[i];
            int time = timestamp[i];

            UserPattern userPattern = map.getOrDefault(uname, new UserPattern());

            userPattern.pages.add(page);
            userPattern.times.add(time);

            map.put(uname, userPattern);
        }

        // compute frequency
        Map<String, Integer> uniquePatterns = new HashMap<>();


        for(Map.Entry<String, UserPattern> entry: map.entrySet()) {
            String pattern = String.join(",", entry.getValue().pages);

            int frequency = uniquePatterns.getOrDefault(pattern, 0);

            uniquePatterns.put(pattern, frequency + 1);
        }

        List<Map.Entry<String, Integer>> groupedPattern = new ArrayList<>(uniquePatterns.entrySet());
        Collections.sort(groupedPattern, (a, b)->compare(a,b));
        print(groupedPattern);


        return Arrays.stream(groupedPattern.get(0).getKey().split(",")).toList();
    }

    private static int compare(Map.Entry<String, Integer> s1, Map.Entry<String, Integer> s2){
        int compareResult = Integer.compare(s2.getValue(), s1.getValue());
        if(compareResult !=0) return compareResult;

        return s2.getKey().compareTo(s1.getKey()) *-1;
    }

    private static void print(List<Map.Entry<String, Integer>> uniquePatterns){
        for (Map.Entry<String, Integer> entry: uniquePatterns){
            System.out.printf("%s -> %s%n",entry.getKey(), entry.getValue());
        }
    }
}

class UserPattern{
    List<String> pages;
    List<Integer> times;

    public UserPattern(){
        pages = new ArrayList<>();
        times = new ArrayList<>();
    }
}
