/*
Problem: Climbing Stairs

Description:
You are climbing a staircase with n steps.
Each time you can either climb:
- 1 step
- 2 steps

Find the number of distinct ways to reach the top.

------------------------------------------------

Approach:

Step 1:
Observe pattern:
- To reach step n:
    → From (n-1) using 1 step
    → From (n-2) using 2 steps

So,
ways(n) = ways(n-1) + ways(n-2)

Step 2:
Base cases:
- n = 1 → 1 way
- n = 2 → 2 ways

Step 3:
Use iterative DP (Fibonacci pattern)

------------------------------------------------

Example:

Input:
n = 5

Ways:
1+1+1+1+1
1+1+1+2
1+1+2+1
1+2+1+1
2+1+1+1
1+2+2
2+1+2
2+2+1

Output:
8

------------------------------------------------

Time Complexity:
O(n)

- Single loop from 3 to n

------------------------------------------------

Space Complexity:
O(1)

- Only 2 variables used

------------------------------------------------
*/

class Solution {

    public int climbStairs(int n) {

        if (n <= 2) return n;

        int first = 1;   // ways to reach step 1
        int second = 2;  // ways to reach step 2

        for (int i = 3; i <= n; i++) {

            int third = first + second;

            first = second;
            second = third;
        }

        return second;
    }
}
