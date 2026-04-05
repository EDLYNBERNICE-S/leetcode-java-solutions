/*
Problem: Robot Return to Origin

Description:
A robot starts at position (0, 0) on a 2D plane.
You are given a string moves consisting of:
'U', 'D', 'L', 'R'.

Determine whether the robot returns to the origin
after performing all the moves.

------------------------------------------------

Approach:

Step 1:
Initialize coordinates:
x = 0 (horizontal)
y = 0 (vertical)

Step 2:
Traverse each move:
- 'U' → y++
- 'D' → y--
- 'L' → x--
- 'R' → x++

Step 3:
After processing all moves:
- If (x == 0 && y == 0) → return true
- Else → return false

------------------------------------------------

Example:

Input:
moves = "UDLR"

Process:
U → (0,1)
D → (0,0)
L → (-1,0)
R → (0,0)

Output:
true

------------------------------------------------

Time Complexity:
O(n)

- Traverse the string once

------------------------------------------------

Space Complexity:
O(1)

- Only two variables used

------------------------------------------------
*/

class Solution {

    public boolean judgeCircle(String moves) {

        int x = 0;
        int y = 0;

        for (char move : moves.toCharArray()) {

            if (move == 'U') {
                y++;
            } 
            else if (move == 'D') {
                y--;
            } 
            else if (move == 'L') {
                x--;
            } 
            else if (move == 'R') {
                x++;
            }
        }

        return x == 0 && y == 0;
    }
}
