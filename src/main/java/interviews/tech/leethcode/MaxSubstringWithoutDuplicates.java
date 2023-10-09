package interviews.tech.leethcode;

import java.util.*;

public class MaxSubstringWithoutDuplicates {
    public static void execute(){

        String[] inputs = new String[]{
                "ABCABAB",
                "ABCDDBAB",
                "ABABCDEF",
                "A",
                ""
        };

        for(String input:inputs){
            int count = getMaxSubStringWithoutDuplicates(input);

            System.out.printf("%s -> %s%n",input,count);
            System.out.println();
        }
    }

    /**
     * Using HashSet
     *  Do not get overwhelmed with removal order from beginning.
     *  We anyway need to remove the element and there is only one instance.
     *  TC - O(N)
     *  SC - O(N)
     *  to improve TC, use an array of 26 characters to maintain Window
     */
    private static int getMaxSubStringWithoutDuplicates(String str){
        Set<Character> window = new HashSet<>();

        int left = 0;
        int max = 0;
        for (int i= 0; i < str.length(); i++) {
            Character ch = str.charAt(i);

            while (window.contains(ch)) {
                window.remove(str.charAt(left++));
            }

            window.add(ch);
            max = Math.max(max, window.size());
        }


        return max;
    }
}
