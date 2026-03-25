/*
Problem: Partition Grid into Two Equal Sum Parts

Description:
Given a 2D grid of integers, determine if it is possible to split
the grid into two parts using a single horizontal or vertical cut
such that the sum of both parts is equal.

------------------------------------------------

Approach:

1. Compute total sum of all elements in the grid.

2. If total sum is odd → return false
   (because it cannot be divided into two equal integers)

3. Calculate:
   - rowSums[] → sum of each row
   - colSums[] → sum of each column

4. Check Horizontal Cuts:
   - Keep adding row sums from top
   - If cumulative sum == totalSum / 2 → valid split

5. Check Vertical Cuts:
   - Keep adding column sums from left
   - If cumulative sum == totalSum / 2 → valid split

------------------------------------------------

Example:

Input:
grid = [[1,2],
        [3,4]]

Total Sum = 10 → Target = 5

No horizontal or vertical cut gives sum = 5

Output:
false

------------------------------------------------

Time Complexity:
O(m * n)

- One pass to calculate sums
- One pass for rows and columns

------------------------------------------------

Space Complexity:
O(m + n)

- rowSums array of size m
- colSums array of size n

------------------------------------------------
*/

class Solution {

    public boolean canPartitionGrid(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        long totalSum = 0;

        long[] rowSums = new long[m];
        long[] colSums = new long[n];

        // Step 1: Calculate total sum, row sums, and column sums
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                long val = grid[i][j];

                totalSum += val;
                rowSums[i] += val;
                colSums[j] += val;
            }
        }

        // Step 2: If total sum is odd, cannot split equally
        if (totalSum % 2 != 0) return false;

        long target = totalSum / 2;

        // Step 3: Check horizontal partition
        long currentRowSum = 0;

        for (int i = 0; i < m - 1; i++) {
            currentRowSum += rowSums[i];

            if (currentRowSum == target) {
                return true;
            }
        }

        // Step 4: Check vertical partition
        long currentColSum = 0;

        for (int j = 0; j < n - 1; j++) {
            currentColSum += colSums[j];

            if (currentColSum == target) {
                return true;
            }
        }

        return false;
    }
}
