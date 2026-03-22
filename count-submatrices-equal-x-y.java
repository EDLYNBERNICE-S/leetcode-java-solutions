/*
Problem: Count Submatrices With Equal Frequency of X and Y (LeetCode 3212)

Description:
You are given a 2D grid where each cell contains:
- 'X'
- 'Y'
- '.'

Count the number of submatrices that:
1. Include the top-left cell (0,0)
2. Have equal number of 'X' and 'Y'
3. Contain at least one 'X'

------------------------------------------------

Key Observation:

Since every valid submatrix must include (0,0),
each submatrix is uniquely identified by its bottom-right corner (i, j).

So we only need to check submatrices from:
(0,0) → (i,j)

------------------------------------------------

Approach: 2D Prefix Count

We maintain two prefix matrices:
- countX → number of 'X'
- countY → number of 'Y'

For each cell (i, j):
countX[i][j] = total X's from (0,0) to (i,j)
countY[i][j] = total Y's from (0,0) to (i,j)

------------------------------------------------

Prefix Formula:

countX[i+1][j+1] =
    countX[i][j+1]
  + countX[i+1][j]
  - countX[i][j]
  + (grid[i][j] == 'X' ? 1 : 0)

Same for countY.

------------------------------------------------

Condition Check:

For each (i, j):
1. countX > 0   → at least one 'X'
2. countX == countY → equal frequency

If both satisfied → count++

------------------------------------------------

Example:

Input:
grid = [["X","Y","."],
        ["Y",".","."]]

Valid submatrices:
(0,0)
(0,1)
(1,0)

Output:
3

------------------------------------------------

Time Complexity:
O(m * n)

We traverse the grid once.

------------------------------------------------

Space Complexity:
O(m * n)

For storing prefix count arrays.

------------------------------------------------
*/

class Solution {

    public int numberOfSubmatrices(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] countX = new int[m + 1][n + 1];
        int[][] countY = new int[m + 1][n + 1];

        int result = 0;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Build prefix counts
                countX[i + 1][j + 1] =
                        countX[i][j + 1]
                      + countX[i + 1][j]
                      - countX[i][j];

                countY[i + 1][j + 1] =
                        countY[i][j + 1]
                      + countY[i + 1][j]
                      - countY[i][j];

                // Update based on current cell
                if (grid[i][j] == 'X') countX[i + 1][j + 1]++;
                else if (grid[i][j] == 'Y') countY[i + 1][j + 1]++;

                // Check conditions
                int x = countX[i + 1][j + 1];
                int y = countY[i + 1][j + 1];

                if (x > 0 && x == y) {
                    result++;
                }
            }
        }

        return result;
    }
}
