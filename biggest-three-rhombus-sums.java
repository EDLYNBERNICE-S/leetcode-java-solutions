/*
Problem: Get Biggest Three Rhombus Sums in a Grid

Description:
You are given an m × n integer grid. A rhombus is defined by selecting
a center cell and extending diagonally in four directions to form a
diamond shape. The border of the rhombus consists of the cells along
these diagonals.

The task is to find the three largest distinct rhombus border sums
in the grid. If there are fewer than three distinct sums, return all
available sums.

Example:
Input:
grid = [[3,4,5,1,3],
        [3,3,4,2,3],
        [20,30,200,40,10],
        [1,5,5,4,1],
        [4,3,2,2,5]]

Output:
[228,216,211]


Approach:

1. Traverse every cell (i, j) in the grid and treat it as the center
   of a possible rhombus.

2. Add the value of the cell itself as a rhombus of size 0.

3. Expand the rhombus with increasing radius s while the borders
   stay inside the grid.

4. For each valid radius:
      - Calculate the rhombus border sum by traversing the four sides:
        • Top → Right
        • Right → Bottom
        • Bottom → Left
        • Left → Top

5. Store the sums in a TreeSet (descending order) to maintain
   distinct values automatically.

6. Extract the largest three values from the set.

Time Complexity:
O(m * n * min(m,n))

Space Complexity:
O(k) where k is the number of distinct rhombus sums stored.

*/

import java.util.*;

class Solution {

    public int[] getBiggestThree(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // TreeSet keeps values unique and sorted in descending order
        TreeSet<Integer> set = new TreeSet<>(Collections.reverseOrder());

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Radius 0 rhombus (single cell)
                set.add(grid[i][j]);

                // Try increasing rhombus radius
                for (int s = 1; i - s >= 0 && i + s < m && j - s >= 0 && j + s < n; s++) {

                    int sum = 0;

                    // Top → Right
                    for (int k = 0; k < s; k++)
                        sum += grid[i - s + k][j + k];

                    // Right → Bottom
                    for (int k = 0; k < s; k++)
                        sum += grid[i + k][j + s - k];

                    // Bottom → Left
                    for (int k = 0; k < s; k++)
                        sum += grid[i + s - k][j - k];

                    // Left → Top
                    for (int k = 0; k < s; k++)
                        sum += grid[i - k][j - s + k];

                    set.add(sum);
                }
            }
        }

        // Extract top 3 distinct values
        int size = Math.min(set.size(), 3);
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = set.pollFirst();
        }

        return result;
    }
}
