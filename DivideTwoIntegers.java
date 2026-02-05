/**
 * Problem: Divide Two Integers (LeetCode)
 * Approach: Use bit manipulation (binary doubling) to simulate division
 * Time Complexity: O(log N)
 * Space Complexity: O(1)
 */
class Solution {

    public int divide(int dividend, int divisor) {

        // Handle overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Convert to positive long to avoid overflow
        long positiveDividend = Math.abs((long) dividend);
        long positiveDivisor = Math.abs((long) divisor);

        int quotient = 0;

        // Repeat until dividend is smaller than divisor
        while (positiveDividend >= positiveDivisor) {

            long currentDivisor = positiveDivisor;
            int multiple = 1;

            // Double divisor until it exceeds remaining dividend
            while (positiveDividend >= (currentDivisor << 1)) {
                currentDivisor <<= 1;
                multiple <<= 1;
            }

            positiveDividend -= currentDivisor;
            quotient += multiple;
        }

        // Apply sign
        if ((dividend < 0) ^ (divisor < 0)) {
            quotient = -quotient;
        }

        return quotient;
    }
}
