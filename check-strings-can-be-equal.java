/*
Problem: Check if Strings Can Be Made Equal

Description:
You are given two strings s1 and s2 of length 4.
You can swap characters only at positions:
- (0 ↔ 2)  → even indices
- (1 ↔ 3)  → odd indices

Return true if s1 can be transformed into s2 using any number of such swaps.

------------------------------------------------

Approach:

Step 1:
Observe that swaps are only allowed within:
- Even indices → {0, 2}
- Odd indices  → {1, 3}

Step 2:
Check if characters at even positions in s1
can match characters at even positions in s2.

Step 3:
Check the same for odd positions.

Step 4:
If both match → return true

------------------------------------------------

Key Idea:

We only need to check if:
- (s1[0], s1[2]) matches (s2[0], s2[2])
- (s1[1], s1[3]) matches (s2[1], s2[3])

Each pair can either:
✔ Match directly
✔ Match after swapping

------------------------------------------------

Example:

Input:
s1 = "abcd"
s2 = "cdab"

Even indices:
(a, c) ↔ (c, a) → valid (swap)

Odd indices:
(b, d) ↔ (d, b) → valid (swap)

Output:
true

------------------------------------------------

Time Complexity:
O(1)

- Only fixed 4 character checks

------------------------------------------------

Space Complexity:
O(1)

- No extra space used

------------------------------------------------
*/

class Solution {

    public boolean canBeEqual(String s1, String s2) {

        // Check even indices (0, 2)
        boolean evenMatch = canMatch(
                s1.charAt(0), s1.charAt(2),
                s2.charAt(0), s2.charAt(2)
        );

        // Check odd indices (1, 3)
        boolean oddMatch = canMatch(
                s1.charAt(1), s1.charAt(3),
                s2.charAt(1), s2.charAt(3)
        );

        return evenMatch && oddMatch;
    }

    // Helper function to check if two pairs can match
    private boolean canMatch(char a1, char a2, char b1, char b2) {

        // Direct match OR swapped match
        return (a1 == b1 && a2 == b2) ||
               (a1 == b2 && a2 == b1);
    }
}
