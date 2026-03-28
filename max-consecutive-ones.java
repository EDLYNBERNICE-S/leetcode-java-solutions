/*
Problem: Max Consecutive Ones

Description:
Given a binary array nums, return the maximum number of consecutive 1's in the array.

------------------------------------------------

Approach:

- Traverse the array once.
- Maintain two variables:
    1. currentCount → counts consecutive 1's
    2. max → stores maximum consecutive 1's found so far

- If element is 1 → increment currentCount
- If element is 0 → update max and reset currentCount

- Finally, return the maximum of max and currentCount
  (to handle case where array ends with 1)

------------------------------------------------

Example:

Input:
nums = [1,1,0,1,1,1]

Output:
3

Explanation:
Longest sequence of consecutive 1's is [1,1,1]

------------------------------------------------

Time Complexity:
O(n)

- Single traversal of the array

------------------------------------------------

Space Complexity:
O(1)

- Constant extra space used

------------------------------------------------
*/

class Solution {

    public int findMaxConsecutiveOnes(int[] nums) {

        int max = 0;
        int currentCount = 0;

        for (int num : nums) {

            if (num == 1) {
                currentCount++;
            } else {
                if (currentCount > max) {
                    max = currentCount;
                }
                currentCount = 0;
            }
        }

        // Final check in case array ends with 1
        return currentCount > max ? currentCount : max;
    }
}
