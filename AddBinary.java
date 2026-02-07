/**
 * LeetCode Problem: Add Binary
 * 
 * This class contains a method to add two binary strings and return
 * their sum as a binary string.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class AddBinary {

    // Adds two binary strings and returns the result as a binary string
    public String addBinary(String a, String b) {

        StringBuilder answer = new StringBuilder();   // To build the result efficiently
        int i = a.length() - 1;                       // Pointer for first binary string
        int j = b.length() - 1;                       // Pointer for second binary string
        int carry = 0;                                // Carry for binary addition

        // Loop until both strings are processed and no carry remains
        while (i >= 0 || j >= 0 || carry != 0) {

            int sum = carry;   // Start with carry from previous addition

            if (i >= 0) {
                sum += a.charAt(i) - '0';   // Convert char to int (0 or 1)
                i--;
            }

            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            // Current result bit
            answer.append(sum % 2);

            // Update carry
            carry = sum / 2;
        }

        // Reverse because we added bits from LSB to MSB
        return answer.reverse().toString();
    }
}
