/*
 Problem: First Missing Positive

 Description:
 Given an unsorted integer array, find the smallest missing
 positive integer.

 The algorithm rearranges the array so that each number x
 is placed at index (x - 1) if possible. After this placement,
 the first index that does not contain the correct value
 indicates the missing positive number.

 Example:
 Input:  [3, 4, -1, 1]
 Output: 2

 Approach:
 - Iterate through the array.
 - For each element nums[i], place it in its correct position
   (index = nums[i] - 1) using swapping.
 - Continue swapping until the current number is either:
      • Out of range
      • Already in the correct position
      • Duplicate
 - After rearranging, scan the array.
 - The first index i where nums[i] != i + 1 gives the answer.

 Time Complexity: O(n)
   - Each element is moved at most once.

 Space Complexity: O(1)
   - The algorithm modifies the array in place and uses
     constant extra memory.
*/

class Solution {

    public int firstMissingPositive(int[] nums) {

        int n = nums.length;

        // Place numbers in their correct positions
        for (int i = 0; i < n; i++) {

            while (nums[i] > 0 &&
                   nums[i] <= n &&
                   nums[i] != nums[nums[i] - 1]) {

                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        // Find the first missing positive
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }

        return n + 1;
    }
}
