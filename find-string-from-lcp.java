/*
Problem: Find The String from LCP Matrix

Description:
Given an LCP (Longest Common Prefix) matrix, construct a string such that:
- lcp[i][j] represents the length of the longest common prefix 
  of the suffixes starting at index i and j.

Return the constructed string, or "" if it is not possible.

------------------------------------------------

Approach:

Step 1: Greedy Construction
- Use a character array `res[]` to build the string.
- Start assigning characters from 'a' onwards.
- For each index i:
    - If not assigned, assign a new character.
    - For all j ≥ i:
        - If lcp[i][j] > 0, assign same character to res[j]

- If characters exceed 'z', return "".

------------------------------------------------

Step 2: Verification
- Validate whether the constructed string satisfies the LCP matrix.
- Traverse from bottom-right:
    - If res[i] == res[j]:
        expected = lcp[i+1][j+1] + 1
    - Else expected = 0
- If mismatch occurs → return "".

------------------------------------------------

Example:

Input:
lcp = [[3,0,1],
       [0,2,0],
       [1,0,1]]

Output:
"aba"

------------------------------------------------

Time Complexity:
O(n²)

- Construction: O(n²)
- Verification: O(n²)

------------------------------------------------

Space Complexity:
O(n)

- Only one result array is used

------------------------------------------------
*/

class Solution {

    public String findTheString(int[][] lcp) {

        int n = lcp.length;
        char[] res = new char[n];
        char currentChar = 'a';

        // Step 1: Greedy Construction
        for (int i = 0; i < n; i++) {

            if (res[i] != 0) continue;

            if (currentChar > 'z') return "";

            for (int j = i; j < n; j++) {
                if (lcp[i][j] > 0) {
                    res[j] = currentChar;
                }
            }

            currentChar++;
        }

        // Step 2: Verification
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                int expected = 0;

                if (res[i] == res[j]) {
                    if (i + 1 < n && j + 1 < n)
                        expected = lcp[i + 1][j + 1] + 1;
                    else
                        expected = 1;
                }

                if (lcp[i][j] != expected) return "";
            }
        }

        return new String(res);
    }
}
