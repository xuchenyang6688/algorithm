package com.xcy.solutions.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 94. Binary Tree Inorder Traversal
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Inorder traversal: left → root → right
 *
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {

    /**
     * Main entry point - uses iterative traversal by default.
     */
    public List<Integer> inorderTraversal(TreeNode root){
        return inorderTraversalIterative(root);
        // return inorderTraversalRecursive(root, result);
    }

    /**
     * Iterative inorder traversal using stack.
     *
     * Approach: Simulate recursion using explicit stack
     * 1. Push all left nodes to stack (going deep left)
     * 2. Pop node, process it (add to result)
     * 3. Move to right subtree and repeat
     *
     * Key Insight: The stack preserves the "call stack" of recursion.
     * We process nodes in LIFO order as we would in recursion.
     *
     * Time Complexity: O(n) - each node visited once
     * Space Complexity: O(n) - stack stores up to height of tree
     *                  (O(h) where h is height, worst case O(n) for skewed tree)
     */
    private List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode current = root;

        while(current!=null|| !stack.isEmpty()){
            // Go as deep left as possible
            while(current!=null){
                stack.push(current);
                current = current.left;
            }

            // Process node
            current = stack.pop();
            result.add(current.val);

            // Move to right subtree
            current = current.right;
        }
        return result;
    }

    /**
     * Recursive inorder traversal.
     *
     * Approach: Depth-first search
     * 1. Traverse left subtree recursively
     * 2. Visit current node
     * 3. Traverse right subtree recursively
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) for recursion stack (O(h) height)
     */
    private List<Integer> inorderTraversalRecursive(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRecursiveHelper(root, result);
        return result;
    }

    private void inorderTraversalRecursiveHelper(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorderTraversalRecursiveHelper(root.left, result);
        result.add(root.val);
        inorderTraversalRecursiveHelper(root.right, result);
    }

    // ==================== TREE NODE DEFINITION ====================
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) {

    }
}
