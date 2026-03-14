/*
Problem: Get K-th Happy String

Description:
A happy string is a string consisting only of characters 'a', 'b', and 'c'
such that no two adjacent characters are the same.

Given two integers n and k, return the k-th lexicographical happy string
of length n. If there are fewer than k happy strings, return an empty string.

Example:
Input:
n = 3
k = 9

Output:
"cab"
------------------------------------------------

Approach 1: Mathematical / Combinatorics (Optimized)

Observation:
For length n:
- First character has 3 choices: a, b, c
- Every next character has 2 choices (cannot repeat previous)

Total happy strings:
3 × 2^(n-1)

Idea:
Instead of generating all strings, directly determine the
correct character at each position using block sizes.

Steps:
1. Calculate total possible happy strings.
2. If k > total, return "".
3. Determine the first character using block division.
4. For remaining positions choose among the two valid characters.
5. Reduce k as we move deeper.

Time Complexity:
O(n)

Space Complexity:
O(n)
*/

class Solution {

    public String getHappyString(int n, int k) {

        int total = 3 * (int) Math.pow(2, n - 1);
        if (k > total) return "";

        StringBuilder sb = new StringBuilder();
        char[] chars = {'a', 'b', 'c'};

        k--; // convert to 0-index

        int blockSize = (int) Math.pow(2, n - 1);

        int index = k / blockSize;
        sb.append(chars[index]);

        for (int i = 1; i < n; i++) {

            k %= blockSize;
            blockSize /= 2;

            int nextIndex = k / blockSize;

            char last = sb.charAt(sb.length() - 1);

            if (last == 'a')
                sb.append(nextIndex == 0 ? 'b' : 'c');
            else if (last == 'b')
                sb.append(nextIndex == 0 ? 'a' : 'c');
            else
                sb.append(nextIndex == 0 ? 'a' : 'b');
        }

        return sb.toString();
    }
}


/*
Approach 2: Backtracking

Idea:
Generate all possible happy strings in lexicographical order
using recursion and stop when the k-th string is found.

Steps:
1. Build strings recursively.
2. Ensure the next character is different from the previous one.
3. Increase count whenever a valid string of length n is formed.
4. When count == k, store the result and stop recursion.

Time Complexity:
O(3 × 2^(n-1))

Space Complexity:
O(n) recursion stack
*/

class Solution {

    int count = 0;
    String result = "";

    public String getHappyString(int n, int k) {

        backtrack(n, k, new StringBuilder());
        return result;
    }

    private void backtrack(int n, int k, StringBuilder current) {

        if (!result.equals("")) return;

        if (current.length() == n) {
            count++;
            if (count == k)
                result = current.toString();
            return;
        }

        for (char c : new char[]{'a', 'b', 'c'}) {

            int len = current.length();

            if (len > 0 && current.charAt(len - 1) == c)
                continue;

            current.append(c);
            backtrack(n, k, current);
            current.deleteCharAt(current.length() - 1);
        }
    }
}
