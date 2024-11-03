package interviews.tech.leethcode.top150;

import interviews.tech.leethcode.AbstractSolution;

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
public class MajorityElement extends AbstractSolution {

    public static void main(String[] args){
        test(majorityElement(new int[]{3,2,3}), 3);
        test(majorityElement(new int[]{2,2,1,1,1,2,2}), 2);
        test(majorityElement(new int[]{2}), 2);
        test(majorityElement(new int[]{}), -1);
    }

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
}
