package interviews.tech.popular;

import interviews.tech.leethcode.AbstractSolution;

import java.util.*;

/**
 *  2. Longest Substring with At Most k Distinct Characters
 * Problem: Given a string and an integer k, find the length of the longest substring
 * that contains at most k distinct characters.
 *
 * Example:
 *
 * Input: s = "araaci", k = 2
 * Output: 4 (substring "araa")
 * Approach:
 *
 * Similar to the previous variation, use a sliding window with a HashMap to keep track of character frequencies.
 * Adjust the window size by moving left if there are more than k distinct characters in the window.
 * Each time the window is valid (with k or fewer distinct characters), update the maximum length.
 */
public class LongestSubstring  extends AbstractSolution {
    public static void main(String[] args){

        test(getLongestSubstringLength("a"), 1);
        test(getLongestSubstringLength("aaa"), 1);
        test(getLongestSubstringLength("abcabc"), 3);
        test(getLongestSubstringLength("abca"), 3);
        test(getLongestSubstringLength("abcabcd"), 4);

        test(getLongestSubstringLengthOptimized("a"), 1);
        test(getLongestSubstringLengthOptimized("aaa"), 1);
        test(getLongestSubstringLengthOptimized("abcabc"), 3);
        test(getLongestSubstringLengthOptimized("abca"), 3);
        test(getLongestSubstringLengthOptimized("abcabcd"), 4);
//
        test(getLongestSubstringLengthOptimized("a", 1), 1);
        test(getLongestSubstringLengthOptimized("aaa", 1), 1);
        test(getLongestSubstringLengthOptimized("abcabc", 1), 3);
        test(getLongestSubstringLengthOptimized("abca", 1), 3);
        test(getLongestSubstringLengthOptimized("abcabcd", 1), 4);


        test(getLongestSubstringLengthOptimized("aaa", 2), 2);
        test(getLongestSubstringLengthOptimized("abcabc", 2), 6);
        test(getLongestSubstringLengthOptimized("abcabca", 2), 6);
        test(getLongestSubstringLengthOptimized("abcaaabbcd", 3), 9);
        test(getLongestSubstringLengthOptimized("abcdabcdabcdaaaaaa", 2), 8);
    }

    // First part : just return the length of the longsest substring with unique characters.
    private static int getLongestSubstringLength(String str){
        int maxLength = 0;
        int start = 0;
        int end = 0;
        Set<Character> window = new HashSet<>();
        for(int i=0; i< str.length(); i++){
            Character curr = str.charAt(i);
            while(window.contains(curr)){
                window.remove(str.charAt(start));
                start++;
            }

            window.add(curr);
            end = i;

            maxLength = Math.max(end-start + 1, maxLength);
        }

        return maxLength;
    }

    /**
     * Second part : just return the length of the longsest substring with unique characters.
     * avoid the inner loop using HashMap
     * @param str
     * @return
     */
    private static int getLongestSubstringLengthOptimized(String str){
        int maxLength = 0;
        int start = 0;
        int end = 0;
        Map<Character, Integer> window = new HashMap<>();
        for(int i=0; i< str.length(); i++){
            Character curr = str.charAt(i);
            if(window.containsKey(curr)){
                start = window.get(curr) + 1;
            }

            window.put(curr, i);
            end = i;

            maxLength = Math.max(end-start + 1, maxLength);
        }

        return maxLength;
    }

    /**
     * Second part : return longest substring with max k distinct characters
     *
     * @param str
     * @return
     */
    private static int getLongestSubstringLengthOptimized(String str, int k){
        int maxLength = 0;
        int start = 0;
        int end = 0;
        int first = 0;
        Map<Character, List<Integer>> window = new HashMap<>();
        for(int i=0; i< str.length(); i++){
            Character curr = str.charAt(i);
            List<Integer> allPosition = window.getOrDefault(curr, new ArrayList<>());
            if(allPosition.size() == k) {
                int removedIndex = allPosition.remove(0);
                start = removedIndex + 1;
            }

            allPosition.add(i);
            window.put(curr, allPosition);
            end = i;
            if (end-start + 1 > maxLength){
                first = start;
                maxLength = end-start + 1;
            }
        }

        System.out.println(str.substring(first, first + maxLength));
        System.out.println(maxLength);

        return maxLength;
    }
}
