/*
Problem: Fancy Sequence (LeetCode 1622)

Description:
Design a data structure that supports the following operations:

1. append(val): Add a value to the sequence.
2. addAll(inc): Add 'inc' to all elements.
3. multAll(m): Multiply all elements by 'm'.
4. getIndex(idx): Return value at index idx (mod 1e9+7), else -1.

Constraint:
Total operations ≤ 10^5 → Need O(1) per operation.

------------------------------------------------

Key Idea: Lazy Transformation using Linear Equation

Instead of updating all elements for every operation,
we maintain a transformation of the form:

value = (original * a + b) % MOD

Where:
- a = global multiplier
- b = global increment

------------------------------------------------

Operations:

1. append(val):
   Store value in reverse-transformed form:
   x = (val - b) * modular_inverse(a)

2. addAll(inc):
   b = b + inc

3. multAll(m):
   a = a * m
   b = b * m

4. getIndex(idx):
   return (stored_value * a + b) % MOD

------------------------------------------------

Why this works:
We avoid updating all elements by keeping track of
global transformations and applying them only when needed.

------------------------------------------------

Time Complexity:
append → O(log MOD) (due to modular inverse)
addAll → O(1)
multAll → O(1)
getIndex → O(1)

Overall: Efficient for large constraints

------------------------------------------------

Space Complexity:
O(n) for storing elements

------------------------------------------------
*/

import java.util.*;

class Fancy {

    private List<Long> list;
    private long a = 1; // multiplier
    private long b = 0; // increment
    private final int MOD = 1_000_000_007;

    public Fancy() {
        list = new ArrayList<>();
    }

    public void append(int val) {

        // Reverse transformation:
        // val = x * a + b
        // x = (val - b) / a  (using modular inverse)

        long x = (val - b + MOD) % MOD;
        x = (x * power(a, MOD - 2)) % MOD;

        list.add(x);
    }

    public void addAll(int inc) {
        b = (b + inc) % MOD;
    }

    public void multAll(int m) {
        a = (a * m) % MOD;
        b = (b * m) % MOD;
    }

    public int getIndex(int idx) {

        if (idx >= list.size())
            return -1;

        long res = (list.get(idx) * a) % MOD;
        res = (res + b) % MOD;

        return (int) res;
    }

    // Fast exponentiation for modular inverse
    private long power(long x, long y) {

        long res = 1;
        x %= MOD;

        while (y > 0) {

            if ((y & 1) == 1)
                res = (res * x) % MOD;

            x = (x * x) % MOD;
            y >>= 1;
        }

        return res;
    }
}
