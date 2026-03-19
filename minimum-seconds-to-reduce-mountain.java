/*
Problem: Minimum Number of Seconds to Make Mountain Height Zero (LeetCode 3296)

Description:
You are given:
- An integer mountainHeight representing the height of a mountain.
- An array workerTimes where each worker reduces the mountain height.

Each worker works simultaneously. For a worker with time w:
To reduce height by x, time taken = w * (1 + 2 + ... + x)
                               = w * x * (x + 1) / 2

Goal:
Find the minimum time required so that all workers together reduce
the mountain height to 0.

------------------------------------------------

Approach: Binary Search on Time

Idea:
Instead of simulating height reduction, we search for the minimum
time required.

Steps:
1. Use Binary Search on time (low → high).
2. For each mid (time):
      - Check how much height all workers can reduce within this time.
3. Use quadratic formula to compute maximum x such that:
      w * x * (x + 1) / 2 ≤ time
4. Sum all x values.
5. If total height ≥ mountainHeight → possible → try smaller time.
6. Else → increase time.

------------------------------------------------

Key Formula:
w * x * (x + 1) / 2 ≤ time

Solving for x:
x = (-1 + sqrt(1 + 8 * time / w)) / 2

------------------------------------------------

Example:
Input:
mountainHeight = 4
workerTimes = [2,1,1]

Output:
3

------------------------------------------------

Time Complexity:
O(n log T)

n = number of workers  
T = search space (time range)

------------------------------------------------

Space Complexity:
O(1)

------------------------------------------------
*/

class Solution {

    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {

        long low = 0;
        long high = 10_000_000_000_000_000L; // large upper bound
        long ans = high;

        while (low <= high) {

            long mid = low + (high - low) / 2;

            if (canFinish(mid, mountainHeight, workerTimes)) {
                ans = mid;
                high = mid - 1; // try smaller time
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canFinish(long timeLimit, int target, int[] workerTimes) {

        long totalHeight = 0;

        for (int w : workerTimes) {

            // Solve quadratic equation
            double val = (2.0 * timeLimit) / w;

            long x = (long)((-1.0 + Math.sqrt(1.0 + 4.0 * val)) / 2.0);

            totalHeight += x;

            if (totalHeight >= target)
                return true;
        }

        return totalHeight >= target;
    }
}
