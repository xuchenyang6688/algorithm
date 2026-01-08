package com.xcy.solutions.trees;

/**
 * 235. Lowest Common Ancestor of a Binary Search Tree
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * <p>
 * The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as
 * descendants (where we allow a node to be a descendant of itself).”
 */
public class LCA_BinaryTree {
    /**
     * Definition for a binary tree node.
     * (Static inner class: more efficient since it will be frequently used in object creation and outer class can be GC)
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) {
            this.val = val;
        }

    }


    /**
     * Finds the lowest common ancestor of two nodes in a binary tree.
     *
     * Approach: Recursive DFS
     * 1. Base cases:
     *    - If current node is null, return null
     *    - If current node is either p or q, return current node
     * 2. Recursively search in left and right subtrees
     * 3. At each node:
     *    - If both left and right return non-null, current node is the LCA
     *    - If only one side returns non-null, return that result (propagate upwards)
     *    - If both return null, return null
     *
     * Time Complexity: O(N) where N is the number of nodes
     *   - In worst case, we visit every node once
     *   - It's only O(logN) for a balanced BST.
     *
     * Space Complexity: O(H) where H is the height of the tree
     *   - Recursion stack uses O(H) space
     *   - In worst case (skewed tree), O(N)
     *   - In balanced tree, O(log N)
     *
     * Tips: Compare node itself, instead of its value  root == p || root == q
     *
     * @param root the root of the binary tree
     * @param p first node
     * @param q second node
     * @return the lowest common ancestor of p and q, or null if not found
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case 1: reached leaf node or null
        if (root == null) {
            return null;
        }
        // Base case 2: current node is either p or q
        // This handles the case where one node is ancestor of the other
        if (root == p || root == q) {
            return root;
        }
        // Recursively search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // Case 1: p and q are in different subtrees -> current node is LCA
        if (left != null && right != null) {
            return root;
        }
        // Case 2: Both p and q are in the same subtree
        // Return whichever side has a non-null result (propagate the found node up)
        return left != null ? left : right;

    }

    public static void main(String[] args) {
        LCA_BinaryTree solution = new LCA_BinaryTree();
        //   6
        //  / \
        // 3   8
        // /\  /\
        //2 5  7 9
        TreeNode root = new TreeNode(6);
        TreeNode left = new TreeNode(3);
        TreeNode right = new TreeNode(8);
        root.left = left;
        root.right = right;
        left.left = new TreeNode(2);
        left.right = new TreeNode(5);
        right.left = new TreeNode(7);
        right.right = new TreeNode(8);
        // q is p's root
        assert left == solution.lowestCommonAncestor(root, left.left, left);
        // p, q is the root 3's left and right, and it's only root 6's left, finally return left;
        assert left == solution.lowestCommonAncestor(root, left.left, left.right);
        assert root == solution.lowestCommonAncestor(root, left.left, right);
    }
}
