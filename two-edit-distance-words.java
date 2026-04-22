/*
Problem: Words Within Two Edits of Dictionary

Description:
You are given:
- queries → list of words
- dictionary → list of words of same length

A query word is valid if it differs from ANY dictionary word
by at most 2 characters (same index comparison).

Return all such valid query words.

------------------------------------------------

Approach:

Step 1:
Iterate through each query word

Step 2:
For each query, compare with every dictionary word

Step 3:
Count character differences:
- If differences > 2 → stop early (optimization)

Step 4:
If any dictionary word has ≤ 2 differences:
→ add query to result

Step 5:
Move to next query

------------------------------------------------

Key Idea:

We only care about:
✔ Character-by-character mismatch count
✔ Early stopping when edits > 2

This avoids unnecessary comparisons

------------------------------------------------

Example:

Input:
queries = ["word","note","ants","wood"]
dictionary = ["wood","joke","moat"]

Check:
"word" vs "wood" → 1 difference → valid
"note" vs "joke" → 2 differences → valid
"ants" → no match within 2 edits → invalid
"wood" → exact match → valid

Output:
["word","note","wood"]

------------------------------------------------

Time Complexity:
O(Q * D * L)

- Q = number of queries
- D = dictionary size
- L = word length

------------------------------------------------

Space Complexity:
O(1) (excluding output list)

------------------------------------------------
*/

import java.util.*;

class Solution {

    public List<String> twoEditWords(String[] queries, String[] dictionary) {

        List<String> result = new ArrayList<>();

        for (String q : queries) {

            for (String d : dictionary) {

                int edits = 0;

                // Compare characters
                for (int i = 0; i < q.length(); i++) {

                    if (q.charAt(i) != d.charAt(i)) {
                        edits++;
                    }

                    // Early stop
                    if (edits > 2) break;
                }

                // Valid word found
                if (edits <= 2) {
                    result.add(q);
                    break;
                }
            }
        }

        return result;
    }
}
