/*
Problem: Count Submatrices with Top-Left Element and Sum ≤ k (LeetCode 3070)

Description:
You are given a matrix grid and an integer k.

You need to count the number of submatrices:
- That always include the top-left element (0,0)
- Whose total sum is less than or equal to k

------------------------------------------------

Key Observation:

Since every valid submatrix must include (0,0),
each submatrix is uniquely defined by its bottom-right corner (i, j).

So instead of checking all submatrices,
we only need to compute sum from (0,0) → (i,j).

------------------------------------------------

Approach: 2D Prefix Sum

1. Use a prefix sum matrix where:
   prefix[i][j] = sum of elements from (0,0) to (i,j)

2. Formula:
   prefix[i][j] =
       grid[i][j]
     + prefix[i-1][j]
     + prefix[i][j-1]
     - prefix[i-1][j-1]

3. For each (i, j):
   - If prefix[i][j] ≤ k → valid submatrix → count++
   - If prefix[i][j] > k → break (optimization)

Why break works:
All values are non-negative.
So moving right increases sum → no need to check further.

------------------------------------------------

Example:

Input:
grid = [[7,6,3],
        [6,6,1]]
k = 18

Valid submatrices:
(0,0)
(0,1)
(1,0)
(1,1)

Output:
4

------------------------------------------------

Time Complexity:
O(m * n)

We traverse the matrix once.

------------------------------------------------

Space Complexity:
O(m * n)

For storing prefix sum matrix.

------------------------------------------------
*/

class Solution {

    public int countSubmatrices(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        int count = 0;

        long[][] prefix = new long[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                long sum = grid[i][j];

                if (i > 0) sum += prefix[i - 1][j];
                if (j > 0) sum += prefix[i][j - 1];
                if (i > 0 && j > 0) sum -= prefix[i - 1][j - 1];

                prefix[i][j] = sum;

                if (sum <= k) {
                    count++;
                } else {
                    break; // optimization
                }
            }
        }

        return count;
    }
}
