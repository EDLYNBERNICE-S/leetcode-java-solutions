/*
Problem: Walking Robot Simulation

Description:
A robot starts at (0,0) facing North.
You are given:
- commands → movement instructions
- obstacles → positions the robot cannot enter

Commands:
- -1 → turn right (90°)
- -2 → turn left (90°)
- x  → move forward x steps

Return the maximum Euclidean distance squared from origin.

------------------------------------------------

Approach:

Step 1:
Define 4 directions:
North, East, South, West

Step 2:
Store obstacles in a HashSet for O(1) lookup
→ Encode (x, y) into a single long value

Step 3:
Process each command:
- If turn → update direction
- If move:
    → Move step by step
    → Stop if obstacle encountered

Step 4:
Track max distance:
max(x² + y²)

------------------------------------------------

Key Idea:

Instead of checking obstacles using nested loops,
we use hashing:

(x, y) → unique key

This makes lookup O(1)

------------------------------------------------

Example:

Input:
commands = [4, -1, 3]
obstacles = []

Process:
(0,0) → (0,4)
turn right → East
(0,4) → (3,4)

Max distance = 3² + 4² = 25

------------------------------------------------

Time Complexity:
O(N + K)

- N = commands
- K = total movement steps

------------------------------------------------

Space Complexity:
O(O)

- O = number of obstacles

------------------------------------------------
*/

import java.util.*;

class Solution {

    public int robotSim(int[] commands, int[][] obstacles) {

        // Directions: North, East, South, West
        int[][] dirs = {
            {0, 1},   // North
            {1, 0},   // East
            {0, -1},  // South
            {-1, 0}   // West
        };

        // Store obstacles in a set
        Set<Long> obstacleSet = new HashSet<>();

        for (int[] obs : obstacles) {

            long ox = obs[0] + 30000;
            long oy = obs[1] + 30000;

            long key = (ox << 16) + oy;
            obstacleSet.add(key);
        }

        int x = 0, y = 0;
        int direction = 0; // Start facing North

        int maxDistSq = 0;

        for (int cmd : commands) {

            if (cmd == -1) {
                // Turn Right
                direction = (direction + 1) % 4;
            }
            else if (cmd == -2) {
                // Turn Left
                direction = (direction + 3) % 4;
            }
            else {

                // Move forward step by step
                for (int step = 0; step < cmd; step++) {

                    int nextX = x + dirs[direction][0];
                    int nextY = y + dirs[direction][1];

                    long key = (((long) nextX + 30000) << 16)
                             + ((long) nextY + 30000);

                    // If not blocked → move
                    if (!obstacleSet.contains(key)) {

                        x = nextX;
                        y = nextY;

                        maxDistSq = Math.max(maxDistSq,
                                             x * x + y * y);
                    }
                    else {
                        // Hit obstacle → stop current command
                        break;
                    }
                }
            }
        }

        return maxDistSq;
    }
}
