/*
Problem: Generate String with T/F Constraints

Description:
You are given:
- str1 → a string of 'T' and 'F'
- str2 → a pattern string

Construct a string such that:
1. If str1[i] == 'T' → substring starting at i must MATCH str2
2. If str1[i] == 'F' → substring starting at i must NOT match str2

Return the lexicographically smallest valid string.
If impossible, return "".

------------------------------------------------

Approach:

Step 1: Initialize result array
- Length = n + m - 1
- Fill with '?' initially

Step 2: Apply 'T' constraints
- For every 'T' in str1:
    → Force match with str2
    → If conflict occurs → return ""

Step 3: Fill remaining positions
- Replace all '?' with 'a' (smallest character)

Step 4: Handle 'F' constraints
- For each 'F':
    → If substring matches str2
    → Modify rightmost non-fixed character to break match
    → If not possible → return ""

Step 5: Final verification
- Ensure all 'T' and 'F' conditions are satisfied

------------------------------------------------

Example:

Input:
str1 = "TFT"
str2 = "ab"

Output:
"ababa"

Explanation:
- Index 0 → "ab" matches (T)
- Index 1 → "ba" does NOT match (F)
- Index 2 → "ab" matches (T)

------------------------------------------------

Time Complexity:
O(n * m)

- T constraints → O(n * m)
- F validation → O(n * m)

------------------------------------------------

Space Complexity:
O(n + m)

- Result array and fixed tracking

------------------------------------------------
*/

import java.util.Arrays;

class Solution {

    public String generateString(String str1, String str2) {

        int n = str1.length();
        int m = str2.length();

        int totalLen = n + m - 1;

        char[] res = new char[totalLen];
        boolean[] fixed = new boolean[totalLen];

        Arrays.fill(res, '?');

        // Step 1: Apply 'T' constraints
        for (int i = 0; i < n; i++) {

            if (str1.charAt(i) == 'T') {

                for (int j = 0; j < m; j++) {

                    int pos = i + j;

                    // Conflict check
                    if (res[pos] != '?' && res[pos] != str2.charAt(j)) {
                        return "";
                    }

                    res[pos] = str2.charAt(j);
                    fixed[pos] = true;
                }
            }
        }

        // Step 2: Fill remaining with 'a'
        for (int i = 0; i < totalLen; i++) {
            if (res[i] == '?') res[i] = 'a';
        }

        // Step 3: Handle 'F' constraints
        for (int i = 0; i < n; i++) {

            if (str1.charAt(i) == 'F') {

                while (isMatch(res, i, str2)) {

                    boolean adjusted = false;

                    // Modify from right to minimize lexicographic impact
                    for (int j = m - 1; j >= 0; j--) {

                        int pos = i + j;

                        if (!fixed[pos]) {

                            if (res[pos] < 'z') {
                                res[pos]++;
                                adjusted = true;
                                break;
                            }
                        }
                    }

                    // Cannot break match → invalid
                    if (!adjusted) return "";
                }
            }
        }

        // Step 4: Final validation
        for (int i = 0; i < n; i++) {

            boolean match = isMatch(res, i, str2);

            if (str1.charAt(i) == 'T' && !match) return "";
            if (str1.charAt(i) == 'F' && match) return "";
        }

        return new String(res);
    }

    // Helper: Check substring match
    private boolean isMatch(char[] res, int start, String str2) {

        for (int j = 0; j < str2.length(); j++) {

            if (res[start + j] != str2.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}
