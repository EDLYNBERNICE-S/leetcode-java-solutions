/*
 Problem: Sliding Window Maximum

 Description:
 Given an integer array nums and a window size k, this program returns
 the maximum value in every sliding window of size k as the window
 moves from left to right across the array.

 Instead of recalculating the maximum for every window, the algorithm
 precomputes two arrays:
 1. leftMax  – stores maximum values from the start of each block.
 2. rightMax – stores maximum values from the end of each block.

 Using these arrays allows efficient retrieval of the maximum
 for each window.

 Approach:
 - Divide the array into blocks of size k.
 - Build leftMax array:
      leftMax[i] stores the maximum from the beginning of the block to i.
 - Build rightMax array:
      rightMax[i] stores the maximum from i to the end of the block.
 - For each window:
      max = max(rightMax[i], leftMax[i + k - 1])

 Time Complexity: O(n)
   - Each element is processed a constant number of times.

 Space Complexity: O(n)
   - Two auxiliary arrays (leftMax and rightMax) are used.

*/

class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {

        int n = nums.length;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Build leftMax array
        for (int i = 0; i < n; i++) {
            if (i % k == 0)
                leftMax[i] = nums[i];
            else
                leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
        }

        // Build rightMax array
        for (int i = n - 1; i >= 0; i--) {
            if (i == n - 1 || (i + 1) % k == 0)
                rightMax[i] = nums[i];
            else
                rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }

        // Compute result for each sliding window
        int[] result = new int[n - k + 1];

        for (int i = 0; i < n - k + 1; i++) {
            result[i] = Math.max(rightMax[i], leftMax[i + k - 1]);
        }

        return result;
    }
}
