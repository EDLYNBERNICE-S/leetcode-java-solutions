/*
Problem: Minimum Distance to the Target Element

Description:
Given an integer array nums, a target value, and a start index,
find the minimum distance between the start index and any index
i such that nums[i] == target.

Distance is defined as:
|i - start|

------------------------------------------------

Approach:

Step 1:
Initialize minDiff = ∞

Step 2:
Traverse the array:
- If nums[i] == target:
    → Calculate distance = |i - start|
    → Update minimum distance

Step 3:
Optimization:
- If distance becomes 0 → return immediately

Step 4:
Return minimum distance

------------------------------------------------

Example:

Input:
nums = [1,2,3,4,5], target = 5, start = 3

Positions of target:
i = 4 → distance = |4 - 3| = 1

Output:
1

------------------------------------------------

Time Complexity:
O(n)

- Single pass through array

------------------------------------------------

Space Complexity:
O(1)

- No extra space used

------------------------------------------------
*/

class Solution {

    public int getMinDistance(int[] nums, int target, int start) {

        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == target) {

                int currentDiff = Math.abs(i - start);

                if (currentDiff < minDiff) {
                    minDiff = currentDiff;
                }

                // Best possible answer
                if (minDiff == 0) return 0;
            }
        }

        return minDiff;
    }
}
