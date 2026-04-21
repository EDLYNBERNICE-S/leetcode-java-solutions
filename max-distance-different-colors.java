/*
Problem: Two Furthest Houses With Different Colors

Description:
You are given an array colors where colors[i] represents
the color of the i-th house.

Find the maximum distance between two houses such that:
colors[i] != colors[j]

Distance = |i - j|

------------------------------------------------

Approach:

Step 1:
Let n = length of array

Step 2:
Check distance from first house:
- Compare colors[0] with elements from end
- First mismatch gives maximum possible distance from start

Step 3:
Check distance from last house:
- Compare colors[n-1] with elements from start
- First mismatch gives maximum possible distance from end

Step 4:
Return maximum of both distances

------------------------------------------------

Key Insight:

Maximum distance will always involve:
✔ First element OR last element

So we don't need to check all pairs → optimize to O(n)

------------------------------------------------

Example:

Input:
colors = [1,1,1,6,1,1,1]

From start:
colors[0] vs colors[3] → different → distance = 3

From end:
colors[6] vs colors[3] → different → distance = 3

Output:
3

------------------------------------------------

Time Complexity:
O(n)

- At most two linear scans

------------------------------------------------

Space Complexity:
O(1)

- No extra space used

------------------------------------------------
*/

class Solution {

    public int maxDistance(int[] colors) {

        int n = colors.length;
        int maxDist = 0;

        // Compare first house with houses from the end
        for (int i = n - 1; i >= 0; i--) {

            if (colors[i] != colors[0]) {
                maxDist = i;
                break;
            }
        }

        // Compare last house with houses from the beginning
        for (int i = 0; i < n; i++) {

            if (colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, (n - 1) - i);
                break;
            }
        }

        return maxDist;
    }
}
