/*
Problem: Minimum Distance Between Equal Triplets

Description:
Given an integer array nums, find the minimum value of:

    2 * (k - i)

such that:
- nums[i] == nums[j] == nums[k]
- i < j < k

If no such triplet exists, return -1.

------------------------------------------------

Approach:

Step 1:
Store indices of each number using a HashMap:
value → list of indices

Step 2:
For each number:
- If it appears at least 3 times
- Check consecutive triplets of indices

Step 3:
For indices [i, j, k]:
- Minimum distance occurs for consecutive triple
- Compute: 2 * (k - i)

Step 4:
Track the minimum among all values

------------------------------------------------

Key Idea:

Instead of checking all combinations (O(n³)),
we only check consecutive triplets:

indices = [a, b, c, d]
→ check (a,b,c), (b,c,d)

This ensures minimum distance efficiently.

------------------------------------------------

Example:

Input:
nums = [1, 2, 1, 1, 2, 1]

Indices of 1 → [0, 2, 3, 5]

Triplets:
(0,2,3) → distance = 2*(3-0) = 6
(2,3,5) → distance = 2*(5-2) = 6

Output:
6

------------------------------------------------

Time Complexity:
O(n)

- Building map → O(n)
- Traversing lists → O(n)

------------------------------------------------

Space Complexity:
O(n)

- HashMap stores indices

------------------------------------------------
*/

import java.util.*;

class Solution {

    public int minimumDistance(int[] nums) {

        int n = nums.length;

        // Map: value → list of indices
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int minDistance = Integer.MAX_VALUE;
        boolean found = false;

        // Process each number
        for (List<Integer> indices : map.values()) {

            if (indices.size() >= 3) {

                for (int i = 0; i <= indices.size() - 3; i++) {

                    int first = indices.get(i);
                    int third = indices.get(i + 2);

                    int distance = 2 * (third - first);

                    minDistance = Math.min(minDistance, distance);
                    found = true;
                }
            }
        }

        return found ? minDistance : -1;
    }
}
