/*
 Problem: Duplicate Zeros

 Given a fixed-length integer array, duplicate each occurrence
 of zero, shifting the remaining elements to the right.

 Note:
 Elements beyond array length are discarded.
*/


class DuplicateZerosComparison {

    /* ---------------------------------------------------------
       ✅ SOLUTION 1: Brute Force (Shifting)
       ---------------------------------------------------------
       Approach:
       - When a zero is found, shift all elements right
       - Insert extra zero
       - Skip next index

       Time Complexity: O(n²)
       Space Complexity: O(1)
    */
    public static void duplicateZeros_Brute(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {

            if (arr[i] == 0) {

                // Shift elements to the right
                for (int j = n - 1; j > i + 1; j--) {
                    arr[j] = arr[j - 1];
                }

                arr[i + 1] = 0;
                i++;  // Skip duplicated zero
            }
        }
    }


    /* ---------------------------------------------------------
       🚀 SOLUTION 2: Optimized Two-Pass Method
       ---------------------------------------------------------
       Approach:
       1. Count how many zeros can be duplicated.
       2. Work backward and place elements correctly.

       Time Complexity: O(n)
       Space Complexity: O(1)
    */
    public static void duplicateZeros_Optimized(int[] arr) {

        int n = arr.length;
        int zeros = 0;

        // First pass: count duplicable zeros
        for (int i = 0; i < n - zeros; i++) {

            if (arr[i] == 0) {

                // Edge case: zero at boundary
                if (i == n - zeros - 1) {
                    arr[n - 1] = 0;
                    n -= 1;
                    break;
                }

                zeros++;
            }
        }

        // Second pass: fill from back
        int last = n - zeros - 1;

        for (int i = last; i >= 0; i--) {

            if (arr[i] == 0) {
                arr[i + zeros] = 0;
                zeros--;
                arr[i + zeros] = 0;
            } else {
                arr[i + zeros] = arr[i];
            }
        }
    }
}
