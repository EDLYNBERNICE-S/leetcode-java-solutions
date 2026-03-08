/*
Problem: Minimum Number of Flips to Make the Binary String Alternating

Description:
Given a binary string s, we can rotate the string and flip characters.
The goal is to make the string alternating (0101... or 1010...) with
minimum flips.

Approach:
1. Double the string to simulate all possible rotations.
2. Maintain a sliding window of size n.
3. Track mismatches with two patterns:
      Pattern1 → 010101...
      Pattern2 → 101010...
4. Update mismatch counts as the window moves.
5. Track the minimum flips needed.

Time Complexity: O(n)
Space Complexity: O(1)
*/

class Solution {

    public int minFlips(String s) {

        int n = s.length();
        String doubled = s + s;

        int diff1 = 0, diff2 = 0;
        int minFlips = Integer.MAX_VALUE;

        for (int i = 0; i < 2 * n; i++) {

            char expected1 = (i % 2 == 0) ? '0' : '1';
            char expected2 = (i % 2 == 0) ? '1' : '0';

            if (doubled.charAt(i) != expected1) diff1++;
            if (doubled.charAt(i) != expected2) diff2++;

            if (i >= n) {

                char prevExpected1 = ((i - n) % 2 == 0) ? '0' : '1';
                char prevExpected2 = ((i - n) % 2 == 0) ? '1' : '0';

                if (doubled.charAt(i - n) != prevExpected1) diff1--;
                if (doubled.charAt(i - n) != prevExpected2) diff2--;
            }

            if (i >= n - 1)
                minFlips = Math.min(minFlips, Math.min(diff1, diff2));
        }

        return minFlips;
    }
}
