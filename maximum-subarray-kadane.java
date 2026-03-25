/*
Problem: Maximum Subarray Sum (Kadane’s Algorithm)

Description:
Given an integer array nums, find the subarray with the largest sum
and return its sum.

------------------------------------------------

Approach: Kadane’s Algorithm

We use a greedy approach:

1. Maintain two variables:
   - currentSum → maximum sum ending at current index
   - maxSum → overall maximum sum

2. At each step:
   - Either extend the current subarray OR start a new subarray
   - currentSum = max(nums[i], currentSum + nums[i])

3. Update maxSum with the maximum value found so far

------------------------------------------------

Example:

Input:
nums = [-2,1,-3,4,-1,2,1,-5,4]

Output:
6

Explanation:
Subarray [4, -1, 2, 1] gives maximum sum = 6

------------------------------------------------

Time Complexity:
O(n)

We traverse the array only once.

------------------------------------------------

Space Complexity:
O(1)

Only constant extra space is used.

------------------------------------------------
*/

import java.util.*;

public class Main {

    public static int maxSubArray(int[] nums) {

        if (nums.length == 0) return 0;

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        System.out.println(maxSubArray(nums));
    }
}
