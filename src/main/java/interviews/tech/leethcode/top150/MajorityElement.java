package interviews.tech.leethcode.top150;

import interviews.tech.leethcode.AbstractSolution;

import java.util.ArrayList;
import java.util.List;

/** 169. Majority Element
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 *
 */


/** 229. Majority Element II
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: [3]
 * Example 2:
 *
 * Input: nums = [1]
 * Output: [1]
 * Example 3:
 *
 * Input: nums = [1,2]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * Follow up: Could you solve the problem in linear time and in O(1) space?
 */

public class MajorityElement extends AbstractSolution {

    public static void main(String[] args){
        test(majorityElement(new int[]{3,2,3}), 3);
        test(majorityElement(new int[]{2,2,1,1,1,2,2}), 2);
        test(majorityElement(new int[]{2}), 2);
        test(majorityElement(new int[]{}), -1);

        test(majorityElement2(new int[]{-1,-1,-1}).size(), 1);
        test(majorityElement2(new int[]{3,2,3}).size(), 1);
        test(majorityElement2(new int[]{1}).size(), 1);
        test(majorityElement2(new int[]{1, 2}).size(), 2);
    }

    /**
     * Algorithm
     * Boyer-Moore Voting Algorithm
     *
     * The Boyer-Moore Voting Algorithm is a linear time solution with constant space.
     * This algorithm is based on the idea that if an element is the majority,
     * it will eventually "outvote" all other elements.
     *
     * Initialize Variables:
     * count: Set to 0, it represents the number of "votes" for the current candidate.
     * candidate: Keeps track of the current majority element candidate.
     * Iterate Over Array:
     * For each element in the array:
     * If count is 0, assign the current element as the candidate.
     * If the element matches the candidate, increase count by 1.
     * If it doesn't match, decrease count by 1.
     * Return the Candidate:
     * After the loop, the candidate will be the majority element.
     *
     * Why This Works
     * Since the majority element appears more than ⌊n/2⌋ times, it will have more "votes" than any other element in the end.
     */
    private static int majorityElement(int[] nums) {
        if (nums.length ==0) return -1;
        int candidate = nums[0];
        int count = 1;

        for(int i=1; i< nums.length; i++){
            if(candidate == nums[i]){
                count++;
            } else if(count == 0){
                candidate = nums[i];
                count = 1;
            } else{
                count--;
            }
        }

        return candidate;
    }

    /**
     * Solution Approach
     * This problem can be efficiently solved using an extension of the Boyer-Moore Voting Algorithm, which is often called the "Boyer-Moore Majority Vote algorithm for finding elements appearing more than ⌊n/3⌋ times."
     *
     * Key Insights
     * Threshold: For any integer array of size n, there can be at most two elements that appear more than ⌊n/3⌋ times. If there were three or more such elements, they would exceed the array size.
     * Candidate Selection and Validation:
     * We use two potential candidates (candidate1 and candidate2) and two counts (count1 and count2).
     * In the first pass, identify potential candidates by counting appearances.
     * In the second pass, confirm that each candidate appears more than ⌊n/3⌋ times.
     * Steps
     * First Pass (Finding Candidates):
     * Iterate through nums, maintaining two candidates and their counts.
     * If count1 is 0, assign the current element as candidate1. If count2 is 0 and the current element isn’t candidate1, assign it as candidate2.
     * Increment or decrement count1 and count2 based on matches with the current element.
     * Second Pass (Validating Candidates):
     * Confirm that candidate1 and candidate2 each appear more than ⌊n/3⌋ times by counting their occurrences.
     * @param nums
     * @return
     */
    public static List<Integer> majorityElement2(int[] nums) {

        List<Integer> result = new ArrayList<>();
        int candidate1 = 0;
        int candidate2 = 1;
        int count1 = 0, count2 = 0;

        for(int i=0; i< nums.length; i++){
            if(candidate1 == nums[i]){
                count1++;
            } else if(candidate2 == nums[i]){
                count2++;
            } else if(count1 == 0){
                candidate1 = nums[i];
                count1++;
            } else if(count2 == 0){
                candidate2 = nums[i];
                count2++;
            } else {
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        // System.out.println("Candidate 1 " + candidate1);
        // System.out.println("Candidate 2 " + candidate2);

        for(int i=0; i< nums.length; i++){
            if(candidate1 == nums[i]){
                count1++;
            }

            if(candidate2 == nums[i]){
                count2++;
            }
        }

        // System.out.println("count 1 " + count1);
        // System.out.println("count 2 " + count2);

        if(count1 > nums.length / 3){
            result.add(candidate1);
        }

        if(count2 > nums.length / 3){
            result.add(candidate2);
        }

        return result;
    }
}
