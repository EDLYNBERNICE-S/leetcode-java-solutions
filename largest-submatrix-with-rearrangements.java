/*
Problem: Largest Submatrix With Rearrangements

Description:
You are given a binary matrix. You can rearrange the columns of the matrix
in any order. After rearranging, find the largest possible submatrix
consisting only of 1's and return its area.

Example:

Input:
matrix = [[0,0,1],
          [1,1,1],
          [1,0,1]]

Output:
4


------------------------------------------------

Approach 1: Height + Sorting (Optimal)

Idea:
1. Treat each row as the base of a histogram.
2. Convert matrix into heights:
      If matrix[i][j] == 1 → height += previous row height
3. For each row:
      - Sort heights
      - Try forming rectangles using sorted heights

Steps:
1. Build height matrix in-place.
2. For each row:
      - Sort the row
      - Calculate area using:
            height * width

Time Complexity:
O(m * n log n)

Space Complexity:
O(1) (in-place modification)
*/

import java.util.Arrays;

class Solution {

    public int largestSubmatrix(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;

        // Step 1: Build heights
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] += matrix[i - 1][j];
                }
            }
        }

        // Step 2: Process each row
        for (int i = 0; i < m; i++) {

            Arrays.sort(matrix[i]);

            for (int j = n - 1; j >= 0; j--) {

                int height = matrix[i][j];
                int width = n - j;

                if (height == 0) break;

                maxArea = Math.max(maxArea, height * width);
            }
        }

        return maxArea;
    }
}

/*
Approach 2: Tracking Heights with Column Indices

Idea:
Instead of sorting the entire row, track column heights dynamically
and compute area based on increasing heights.

Steps:
1. Maintain previous row heights with column indices.
2. Extend heights if current cell is 1.
3. Add new height = 1 where needed.
4. Calculate area incrementally.

Time Complexity:
O(m * n)

Space Complexity:
O(n)
*/

import java.util.*;

class Solution {

    public int largestSubmatrix(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;

        List<int[]> prevHeights = new ArrayList<>();

        for (int i = 0; i < m; i++) {

            List<int[]> currHeights = new ArrayList<>();
            boolean[] seen = new boolean[n];

            // Extend heights
            for (int[] pair : prevHeights) {

                int height = pair[0];
                int col = pair[1];

                if (matrix[i][col] == 1) {
                    currHeights.add(new int[]{height + 1, col});
                    seen[col] = true;
                }
            }

            // Add new heights
            for (int j = 0; j < n; j++) {
                if (!seen[j] && matrix[i][j] == 1) {
                    currHeights.add(new int[]{1, j});
                }
            }

            // Calculate area
            for (int k = 0; k < currHeights.size(); k++) {

                int height = currHeights.get(k)[0];
                int width = k + 1;

                maxArea = Math.max(maxArea, height * width);
            }

            prevHeights = currHeights;
        }

        return maxArea;
    }
}
