/*
 Problem: Alternating Bits

 Check whether a number has alternating 0s and 1s
 in its binary representation.

 Example:
 5  -> 101   -> true
 7  -> 111   -> false
*/

class AlternatingBitsComparison {

    /* ---------------------------------------------------------
       ✅ SOLUTION 1: String-Based Approach
       ---------------------------------------------------------
       Approach:
       - Convert number to binary string
       - Compare adjacent characters
       - If two consecutive bits are same → return false
    */
    public static boolean hasAlternatingBits_String(int n) {

        String binary = Integer.toBinaryString(n);
        char[] arr = binary.toCharArray();

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return false;
            }
        }

        return true;
    }


    /* ---------------------------------------------------------
       🚀 SOLUTION 2: Optimized Bitwise Approach
       ---------------------------------------------------------
       Approach:
       - Extract last bit using (n & 1)
       - Right shift number
       - Compare current bit with previous bit
       - Stop immediately if two bits are equal
    */
    public static boolean hasAlternatingBits_Bitwise(int n) {

        int previousBit = 2;  // Initialize with invalid bit (not 0 or 1)

        while (n > 0) {

            int currentBit = n & 1;  // Extract last bit
            n >>= 1;                // Shift right

            if (currentBit == previousBit) {
                return false;
            }

            previousBit = currentBit;
        }

        return true;
    }


    /* ---------------------------------------------------------
       🔎 MAIN METHOD FOR TESTING
       ---------------------------------------------------------
    */
    public static void main(String[] args) {

        int number = 5;

        System.out.println("String Method:  " +
                hasAlternatingBits_String(number));

        System.out.println("Bitwise Method: " +
                hasAlternatingBits_Bitwise(number));
    }
}
