package interviews.tech.booking;

import java.util.*;
import java.util.stream.Collectors;

public class GroupAnagram {

    public static void main(String[] args){

        String[] input = new String[]{"eat","tea","tan","ate","nat","bat"};
        List<List<String>> output = groupAnagrams(input);

    }
    private static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> result = new HashMap<>();

        for(int i=0; i< strs.length; i++){
            String pattern = findAnagramPattern(strs[i]);
            List<String> words = result.getOrDefault(pattern, new ArrayList<>());
            words.add(strs[i]);
            result.put(pattern, words);
        }

        return result.values().stream().toList();
    }

    private static  List<List<String>>  formResult( Map<Integer[], List<String>> result ){
        List<List<String>> r = new ArrayList<>();

        for (Map.Entry<Integer[], List<String>> entry: result.entrySet()){
            r.add(entry.getValue());
        }

        return r;
    }

    private static String findAnagramPattern(String s){
        Integer[] alphabets = new Integer[26];
        Arrays.fill(alphabets, 0);
        for(int i=0; i< s.length(); i++){
            alphabets[s.charAt(i) - 'a'] +=1;
        }

        return Arrays.toString(alphabets);
    }
}
