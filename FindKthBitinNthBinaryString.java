/*
Problem: 1545. Find Kth Bit in Nth Binary String

Description:
A binary string Sn is defined recursively.

S1 = "0"

For i > 1
Si = Si-1 + "1" + reverse(invert(Si-1))

Where:
+       → concatenation
reverse → reverses the string
invert  → flips bits (0 → 1, 1 → 0)

Goal:
Return the kth bit in Sn.

Example:
Input:
n = 3, k = 1

S1 = 0
S2 = 011
S3 = 0111001

Output:
0


Key Observation:
Length of Sn = (2^n) - 1

Middle element is always '1'.

Left half  = Sn-1
Right half = reverse(invert(Sn-1))

Instead of building the full string (which becomes huge),
we use recursion to determine where k lies.
*/

class Solution {

    public char findKthBit(int n, int k) {

        // Base case
        if (n == 1) return '0';

        int length = (1 << n) - 1;
        int mid = (length / 2) + 1;

        // Middle element is always 1
        if (k == mid)
            return '1';

        // Left part → same as previous string
        if (k < mid)
            return findKthBit(n - 1, k);

        // Right part → mirrored & inverted
        char bit = findKthBit(n - 1, length - k + 1);

        return bit == '0' ? '1' : '0';
    }
}
