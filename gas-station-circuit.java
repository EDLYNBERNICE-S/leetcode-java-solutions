/*
Problem: Gas Station (Can Complete Circuit)

Description:
There are n gas stations in a circular route.
Each station provides gas[i] amount of gas, and it costs cost[i] gas
to travel to the next station.

Return the starting index of the gas station from where you can travel
around the circuit once in clockwise direction.
If it is not possible, return -1.

------------------------------------------------

Approach:

- Key Idea:
  1. If total gas < total cost → NOT possible → return -1
  2. Otherwise, a solution ALWAYS exists

- Greedy Logic:
  - Traverse all stations
  - Maintain:
      total → overall gas balance
      tank  → current running gas
      start → candidate starting index

- If tank becomes negative:
  → Cannot start from previous stations
  → Reset start to next station (i + 1)
  → Reset tank = 0

------------------------------------------------

Example:

Input:
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output:
3

Explanation:
Start at index 3 → complete full cycle

------------------------------------------------

Time Complexity:
O(n)

- Single pass through the array

------------------------------------------------

Space Complexity:
O(1)

- Constant extra space used

------------------------------------------------
*/

public class Solution {

    public static int canCompleteCircuit(int[] gas, int[] cost) {

        int total = 0;   // Total net gas
        int tank = 0;    // Current tank gas
        int start = 0;   // Candidate start index

        for (int i = 0; i < gas.length; i++) {

            int diff = gas[i] - cost[i];
            total += diff;
            tank += diff;

            // If tank becomes negative, reset start
            if (tank < 0) {
                start = i + 1;
                tank = 0;
            }
        }

        return total >= 0 ? start : -1;
    }
}
