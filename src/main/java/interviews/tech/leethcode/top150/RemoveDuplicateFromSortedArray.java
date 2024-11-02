package interviews.tech.leethcode.top150;

import interviews.tech.leethcode.AbstractSolution;

/**
 * 26. Remove Duplicates from Sorted Array
 Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. The relative order of the elements should be kept the same. Then return the number of unique elements in nums.

 Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:

 Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
 Return k.
 Custom Judge:

 The judge will test your solution with the following code:

 int[] nums = [...]; // Input array
 int[] expectedNums = [...]; // The expected answer with correct length

 int k = removeDuplicates(nums); // Calls your implementation

 assert k == expectedNums.length;
 for (int i = 0; i < k; i++) {
 assert nums[i] == expectedNums[i];
 }
 If all assertions pass, then your solution will be accepted.



 Example 1:

 Input: nums = [1,1,2]
 Output: 2, nums = [1,2,_]
 Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
 It does not matter what you leave beyond the returned k (hence they are underscores).
 Example 2:

 Input: nums = [0,0,1,1,1,2,2,3,3,4]
 Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 It does not matter what you leave beyond the returned k (hence they are underscores).


 Constraints:

 1 <= nums.length <= 3 * 104
 -100 <= nums[i] <= 100
 nums is sorted in non-decreasing order.
 */
public class RemoveDuplicateFromSortedArray extends AbstractSolution {
    public static void main(String[] args) {
        test(solution(new int[]{0, 0, 1, 1, 2, 2, 3, 3, 4}), 5);
        test(solution(new int[]{0, 1, 2, 3, 3, 3,}), 4);
        test(solution(new int[]{0}), 1);
        test(solution(new int[]{}), 0);
        test(solution(new int[]{1, 2, 3}), 3);
    }

    public static int solution(int[] nums) {
        if (nums.length == 0 || nums.length == 1) return nums.length;

        int uniqueIndex = 0; // Index to place the next unique element
        for (int i = 1; i < nums.length; i++) {
            int unique = nums[uniqueIndex];
            int current = nums[i];

            if (unique != current) { // Found a new unique element
                uniqueIndex++; // increment the unique index
                nums[uniqueIndex] = current; // Move the unique element to the uniqueIndex
            }
        }

        // The number of unique elements is uniqueIndex + 1
        return uniqueIndex + 1;
    }
}
