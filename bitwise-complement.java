/*
Approach 2: Bit Manipulation (Optimized)

Idea:
We create a mask that has all bits set to 1 for the length of n.

Example:
n = 5 → binary = 101

Mask creation:
101
111  ← mask

Now XOR with mask:

101
111
---
010 → 2

Steps:
1. Copy n to mask.
2. Expand mask so that all bits become 1.
3. XOR n with mask to flip bits.

Time Complexity: O(1)
Space Complexity: O(1)
*/

class Solution {

    public int bitwiseComplement(int n) {

        if (n == 0) return 1;

        int mask = n;

        mask |= (mask >> 1);
        mask |= (mask >> 2);
        mask |= (mask >> 4);
        mask |= (mask >> 8);
        mask |= (mask >> 16);

        return n ^ mask;
    }
}
