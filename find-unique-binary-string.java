/*
Problem: Find Unique Binary String (LeetCode 1980)

Description:
Given an array of n unique binary strings where each string has length n,
return a binary string of length n that does not appear in the array.

The returned string must be different from every string in the input list.

Example:
Input:
nums = ["01","10"]

Output:
"11" or "00"


Approach (Cantor's Diagonalization):

1. Iterate through the array from index 0 to n-1.
2. For each index i:
      - Check the i-th character of nums[i].
      - Flip the bit:
            '0' → '1'
            '1' → '0'
3. Append the flipped bit to the result string.

Why this works:
The constructed string differs from:
- nums[0] at index 0
- nums[1] at index 1
- nums[2] at index 2
...
Therefore, it cannot match any string in the array.

Example Walkthrough:

nums = ["01","10"]

i = 0 → nums[0][0] = '0' → append '1'
i = 1 → nums[1][1] = '0' → append '1'

Result = "11"

Time Complexity:
O(n)

We iterate through the array once.

Space Complexity:
O(n)

The result string of length n is stored.
*/

class Solution {

    public String findDifferentBinaryString(String[] nums) {

        StringBuilder sb = new StringBuilder();
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            char currentChar = nums[i].charAt(i);

            sb.append(currentChar == '0' ? '1' : '0');
        }

        return sb.toString();
    }
}
