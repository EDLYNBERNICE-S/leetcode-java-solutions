/*
 Problem: Balanced Binary Tree (LeetCode)

 Description:
 A binary tree is height-balanced if the depth of the two subtrees of every node
 never differs by more than one.

 Approach:
 - Use a bottom-up DFS approach.
 - Compute the height of left and right subtrees.
 - If any subtree is unbalanced, propagate -1 upward.
 - If the current node is unbalanced, return -1.
 - Otherwise, return the height of the current subtree.

 Time Complexity: O(n)
   Each node is visited once.

 Space Complexity: O(h)
   h = height of the tree (recursive call stack).
*/

class BalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {
        return computeHeight(root) != -1;
    }

    private int computeHeight(TreeNode current) {
        if (current == null) {
            return 0;
        }

        int leftHeight = computeHeight(current.left);
        if (leftHeight == -1) {
            return -1;   // Left subtree is unbalanced
        }

        int rightHeight = computeHeight(current.right);
        if (rightHeight == -1) {
            return -1;  // Right subtree is unbalanced
        }

        // Check balance condition at current node
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Return height of the current subtree
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
