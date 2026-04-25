/*
Problem: Set Matrix Zeroes (LeetCode 73)

Description:
Given an m × n matrix, if an element is 0,
set its entire row and column to 0.

The modification must be done IN-PLACE.

============================================================

SOLUTION 1: Using Extra Space (Row & Column Arrays)

Approach:
- Use two arrays:
  row[] → track rows containing 0
  col[] → track columns containing 0
- First pass: mark rows & columns
- Second pass: update matrix

Time Complexity: O(m × n)
Space Complexity: O(m + n)

------------------------------------------------------------
*/

class Solution1 {

    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int[] row = new int[m];
        int[] col = new int[n];

        // Step 1: Mark rows and columns
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        // Step 2: Set zeroes
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

============================================================

/*
SOLUTION 2: Optimal In-Place (Constant Space)

Approach:
- Use first row and first column as markers
- If matrix[i][j] == 0:
    mark matrix[i][0] = 0 and matrix[0][j] = 0
- Use a variable 'col0' to track first column separately
- Traverse from bottom-right to avoid overwriting markers

Why Optimized?
- No extra arrays used
- Constant space solution
- Preferred in interviews

Time Complexity: O(m × n)
Space Complexity: O(1)
*/

class Solution2 {

    public void setZeroes(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        int col0 = 1;

        // Step 1: Mark first row and column
        for (int i = 0; i < m; i++) {

            if (matrix[i][0] == 0) col0 = 0;

            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // Step 2: Apply markings in reverse
        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }

            if (col0 == 0) matrix[i][0] = 0;
        }
    }
}

============================================================

Comparison:

Solution 1:
- Uses extra arrays
- Easy to understand
- Uses more space

Solution 2:
- In-place modification
- No extra space
- More efficient and interview preferred

============================================================
