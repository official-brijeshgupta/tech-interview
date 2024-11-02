package interviews.tech.leethcode.top150;

import interviews.tech.leethcode.AbstractSolution;

/**
 * 88. Merge Sorted Array
 * You are given two integer arrays nums1 and nums2, sorted in non-decreasing order, and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
 *
 * Merge nums1 and nums2 into a single array sorted in non-decreasing order.
 *
 * The final sorted array should not be returned by the function, but instead be stored inside the array nums1. To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged, and the last n elements are set to 0 and should be ignored. nums2 has a length of n.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * Output: [1,2,2,3,5,6]
 * Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 * Example 2:
 *
 * Input: nums1 = [1], m = 1, nums2 = [], n = 0
 * Output: [1]
 * Explanation: The arrays we are merging are [1] and [].
 * The result of the merge is [1].
 * Example 3:
 *
 * Input: nums1 = [0], m = 0, nums2 = [1], n = 1
 * Output: [1]
 * Explanation: The arrays we are merging are [] and [1].
 * The result of the merge is [1].
 * Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
 *
 *
 * Constraints:
 *
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[j] <= 109
 *
 *
 * Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */
public class MergeTwoSortedArray extends AbstractSolution {
    public static void main(String[] args){
        test(solution(new int[]{}, 0, new int[]{}, 0), new int[]{});
        test(solution(new int[]{1,2,3}, 3, new int[]{}, 0), new int[]{1, 2, 3});
        test(solution(new int[]{0}, 0, new int[]{1}, 1), new int[]{1});
        test(solution(new int[]{1, 2, 3, 0, 0, 0}, 3, new int[]{4, 5, 6}, 3), new int[]{1, 2, 3, 4, 5, 6});
        test(solution(new int[]{2, 4, 6, 0, 0, 0}, 3, new int[]{3, 5, 7}, 3), new int[]{2, 3, 4, 5, 6, 7});
        test(solution(new int[]{2, 4, 6, 0, 0, 0}, 3, new int[]{4, 6, 8}, 3), new int[]{2, 4, 4, 6, 6, 8});
    }

    public static int[] solution(int[] nums1, int m, int[] nums2, int n) {
        if(n == 0) return nums1;

        int p1=m-1, p2=n-1, p3=m+n-1;
        while(p1 >= 0 && p2 >=0){
            int n1 = nums1[p1];
            int n2 = nums2[p2];

            if(n1 >= n2){
                nums1[p3] = n1;
                p1--;
                p3--;
            } else{
                nums1[p3] = n2;
                p2--;
                p3--;
            }
        }

        while(p2 >= 0){
            nums1[p3--] = nums2[p2--];
        }

        return nums1;
    }
}
