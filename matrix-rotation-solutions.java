/*
Problem: Determine Whether Matrix Can Be Obtained By Rotation (LeetCode 1886)

Description:
Given two n × n binary matrices mat and target,
check if mat can be rotated (0°, 90°, 180°, 270°)
to become equal to target.

============================================================

SOLUTION 1: Using Boolean Array

Approach:
- Use an array of size 4 to track all rotations
- Compare all 4 rotations in a single traversal
- At the end, return true if any rotation matches

Time Complexity: O(n^2)
Space Complexity: O(1)

------------------------------------------------------------
*/

import java.util.Arrays;

class Solution1 {

    public boolean findRotation(int[][] mat, int[][] target) {

        int n = mat.length;

        boolean[] check = new boolean[4];
        Arrays.fill(check, true);

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (mat[i][j] != target[i][j]) 
                    check[0] = false;

                if (mat[i][j] != target[j][n - 1 - i]) 
                    check[1] = false;

                if (mat[i][j] != target[n - 1 - i][n - 1 - j]) 
                    check[2] = false;

                if (mat[i][j] != target[n - 1 - j][i]) 
                    check[3] = false;
            }
        }

        return check[0] || check[1] || check[2] || check[3];
    }
}

============================================================

/*
SOLUTION 2: Optimized Version (Early Exit + No Array)

Approach:
- Use 4 boolean variables instead of array
- Track rotations directly
- Exit early if all become false

Why Optimized?
- Avoids array overhead
- Early exit reduces unnecessary checks

Time Complexity: O(n^2)
Space Complexity: O(1)
*/

class Solution2 {

    public boolean findRotation(int[][] mat, int[][] target) {

        int n = mat.length;

        boolean rot0 = true;
        boolean rot90 = true;
        boolean rot180 = true;
        boolean rot270 = true;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                int val = mat[i][j];

                if (val != target[i][j]) 
                    rot0 = false;

                if (val != target[j][n - 1 - i]) 
                    rot90 = false;

                if (val != target[n - 1 - i][n - 1 - j]) 
                    rot180 = false;

                if (val != target[n - 1 - j][i]) 
                    rot270 = false;

                // Early exit optimization
                if (!(rot0 || rot90 || rot180 || rot270)) 
                    return false;
            }
        }

        return rot0 || rot90 || rot180 || rot270;
    }
}

============================================================

Comparison:

Solution 1:
- Uses boolean array
- Simple and clean
- Slightly less efficient

Solution 2:
- Uses variables instead of array
- Includes early exit
- More optimized in practice

============================================================
*/
