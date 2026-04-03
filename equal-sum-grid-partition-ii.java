/*
Problem: Equal Sum Grid Partition II

Description:
Given an m x n grid of positive integers, determine if it is possible
to make exactly one horizontal OR vertical cut such that:

1. Both sections are non-empty
2. The sums of both sections are equal OR
   can be made equal by removing (discounting) at most ONE cell
3. After removing that cell, the section must remain CONNECTED

------------------------------------------------

Approach:

Step 1: Compute
- Total sum of grid
- Row sums and column sums
- Frequency of all values

Step 2: Try all possible horizontal cuts
- Maintain top section sum
- Bottom sum = total - top
- Check:
    ✔ Direct equality
    ✔ Remove one cell (difference = s1 - s2)
    ✔ Ensure connectivity:
        - If section is more than 1x1 → safe
        - If single row/column → only edge cells removable

Step 3: Try all vertical cuts (same logic)

------------------------------------------------

Key Idea:

Let:
s1 = sum of section A
s2 = sum of section B

If s1 != s2:
We need:
s1 - x = s2   OR   s2 - x = s1

→ x = |s1 - s2|

So we check if a cell with value = difference exists
in the correct section.

------------------------------------------------

Time Complexity:
O(m × n)

- Single traversal
- Efficient frequency usage

------------------------------------------------

Space Complexity:
O(maxValue)

- Frequency arrays

------------------------------------------------
*/

import java.util.*;

class Solution {

    public boolean canPartitionGrid(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        long totalSum = 0;
        long[] rowSums = new long[m];
        long[] colSums = new long[n];

        int maxVal = 0;

        // Step 1: Compute sums + max value
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                totalSum += val;
                rowSums[i] += val;
                colSums[j] += val;
                maxVal = Math.max(maxVal, val);
            }
        }

        // Frequency of all elements
        int[] totalFreq = new int[maxVal + 1];
        for (int[] row : grid) {
            for (int v : row) totalFreq[v]++;
        }

        // -------------------------
        // Horizontal Cuts
        // -------------------------
        int[] topFreq = new int[maxVal + 1];
        long topSum = 0;

        for (int i = 0; i < m - 1; i++) {

            topSum += rowSums[i];
            for (int v : grid[i]) topFreq[v]++;

            long bottomSum = totalSum - topSum;

            // Case 1: Remove from Top
            if (check(topSum, bottomSum, i + 1, n, topFreq,
                    grid[0][0], grid[0][n - 1], grid[i][0], grid[i][n - 1])) {
                return true;
            }

            // Case 2: Remove from Bottom
            if (check(bottomSum, topSum, m - 1 - i, n,
                    totalFreq, topFreq,
                    grid[i + 1][0], grid[i + 1][n - 1],
                    grid[m - 1][0], grid[m - 1][n - 1])) {
                return true;
            }
        }

        // -------------------------
        // Vertical Cuts
        // -------------------------
        int[] leftFreq = new int[maxVal + 1];
        long leftSum = 0;

        for (int j = 0; j < n - 1; j++) {

            leftSum += colSums[j];
            for (int i = 0; i < m; i++) {
                leftFreq[grid[i][j]]++;
            }

            long rightSum = totalSum - leftSum;

            // Case 1: Remove from Left
            if (check(leftSum, rightSum, m, j + 1, leftFreq,
                    grid[0][0], grid[m - 1][0], grid[0][j], grid[m - 1][j])) {
                return true;
            }

            // Case 2: Remove from Right
            if (check(rightSum, leftSum, m, n - 1 - j,
                    totalFreq, leftFreq,
                    grid[0][j + 1], grid[m - 1][j + 1],
                    grid[0][n - 1], grid[m - 1][n - 1])) {
                return true;
            }
        }

        return false;
    }

    // Case 1: Check within same section
    private boolean check(long s1, long s2, int R, int C,
                          int[] freq,
                          int e1, int e2, int e3, int e4) {

        if (s1 == s2) return true;

        long diff = s1 - s2;
        if (diff <= 0 || diff >= freq.length) return false;

        int d = (int) diff;

        // If section is large → any element removable
        if (R > 1 && C > 1) {
            return freq[d] > 0;
        }

        // If 1D → only edge cells allowed
        return d == e1 || d == e2 || d == e3 || d == e4;
    }

    // Case 2: Check remaining section (Total - Current)
    private boolean check(long s1, long s2, int R, int C,
                          int[] total, int[] sub,
                          int e1, int e2, int e3, int e4) {

        if (s1 == s2) return true;

        long diff = s1 - s2;
        if (diff <= 0 || diff >= total.length) return false;

        int d = (int) diff;

        if (R > 1 && C > 1) {
            return (total[d] - sub[d]) > 0;
        }

        return d == e1 || d == e2 || d == e3 || d == e4;
    }
}
