

/*
Problem: Maximize the Distance Between Points on a Square (LeetCode 3464)

Description:
Given a square of side length `side` and boundary points,
select k points such that the minimum Manhattan distance
between any two selected points is maximized.

============================================================

APPROACH: Binary Search + Greedy + Geometry Mapping

Steps:
1. Convert 2D boundary points → 1D perimeter positions
2. Sort points based on position
3. Binary search on answer (minimum distance)
4. Validate using greedy + binary search

Time Complexity: O(n log n + n log side * k log n)
Space Complexity: O(n)

============================================================
*/

import java.util.*;

class Solution {

    public int maxDistance(int side, int[][] points, int k) {

        int n = points.length;

        // Step 1: Convert to 1D positions
        Point[] pts = new Point[n];
        for (int i = 0; i < n; i++) {
            pts[i] = new Point(
                points[i][0],
                points[i][1],
                getPos(points[i][0], points[i][1], side)
            );
        }

        // Step 2: Sort
        Arrays.sort(pts, (a, b) -> Long.compare(a.pos, b.pos));

        // Step 3: Binary Search
        int low = 0, high = 2 * side;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canPlace(mid, k, pts, side)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean canPlace(int d, int k, Point[] pts, int side) {

        int n = pts.length;
        long perimeter = 4L * side;

        // Extend array for circular handling
        Point[] ext = new Point[2 * n];
        for (int i = 0; i < n; i++) {
            ext[i] = pts[i];
            ext[i + n] = new Point(pts[i].x, pts[i].y, pts[i].pos + perimeter);
        }

        // Try each starting point
        for (int i = 0; i < n; i++) {

            int count = 1;
            int last = i;

            for (int pick = 1; pick < k; pick++) {

                int next = findNext(ext, last, i + n - 1, d);

                if (next == -1) break;

                last = next;
                count++;
            }

            // Final circular validation
            if (count == k && dist(ext[last], ext[i]) >= d) {
                return true;
            }
        }

        return false;
    }

    private int findNext(Point[] arr, int start, int end, int d) {

        int left = start + 1;
        int right = end;
        int res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (dist(arr[start], arr[mid]) >= d) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }

    private int dist(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private long getPos(int x, int y, int side) {

        if (y == 0) return x;
        if (x == side) return (long) side + y;
        if (y == side) return (long) 2 * side + (side - x);

        return (long) 3 * side + (side - y);
    }

    static class Point {
        int x, y;
        long pos;

        Point(int x, int y, long pos) {
            this.x = x;
            this.y = y;
            this.pos = pos;
        }
    }
}

============================================================

Summary:
✔ Convert 2D → 1D perimeter
✔ Binary search on answer
✔ Greedy validation with binary search
✔ Handle circular nature using duplication

============================================================
