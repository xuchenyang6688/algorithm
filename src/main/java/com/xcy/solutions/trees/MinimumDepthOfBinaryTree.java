package com.xcy.solutions.trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 111. Minimum Depth of Binary Tree
 *
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * Note: A leaf is a node with no children.
 *
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 *
 * Constraints:
 * - Number of nodes: [0, 10^5]
 * - -1000 <= Node.val <= 1000
 */
public class MinimumDepthOfBinaryTree {
    /**
     * Returns the minimum depth of a binary tree.
     *
     * Approach: Recursive DFS with proper leaf detection
     * 1. Base case: null node has depth 0
     * 2. If node is leaf (both children null): depth = 1
     * 3. If one child is null: must go through non-null child
     * 4. If both children exist: take minimum of both subtrees
     *
     * Key Insight: The minimum depth is NOT simply min(left, right) + 1
     * because a node with one null child is NOT a leaf. We must traverse
     * through the non-null child to find an actual leaf.
     *
     * Common Mistake: Using Math.min(left, right) when one child is null
     * would incorrectly return 1 for a node like [1,null,2,null,3,null,4]
     *
     * Time Complexity: O(n) where n = number of nodes
     *   - Each node visited once
     *
     * Space Complexity: O(h) where h = height of tree
     *   - Recursion stack uses O(h) space
     *   - Worst case (skewed tree): O(n)
     *   - Best case (balanced tree): O(log n)
     */
    public int minDepth(TreeNode root){
        return minDepthDFS(root);
    }

    /**
     * Recursive DFS implementation.
     */
    private int minDepthDFS(TreeNode root){
        if(root==null){
            return 0;
        }

        // Leaf node: both children are null
        if (root.left == null && root.right == null) {
            return 1;
        }

        // If left subtree is empty, must go through right subtree
        if (root.left == null) {
            return 1 + minDepthDFS(root.right);
        }

        // If right subtree is empty, must go through left subtree
        if (root.right == null) {
            return 1 + minDepthDFS(root.left);
        }

        // Both subtrees exist, take the minimum
        return 1 + Math.min(minDepthDFS(root.left), minDepthDFS(root.right));
    }

    /**
     * Alternative: Iterative BFS (Level Order Traversal)
     * More efficient for finding minimum depth because we stop at first leaf.
     *
     * Approach: Use queue for level-order traversal
     * 1. Track depth while processing each level
     * 2. Return depth when we encounter first leaf node
     *
     * Key Insight: BFS finds the shortest path to a leaf naturally
     * because it explores level by level.
     *
     * Time Complexity: O(n) in worst case
     *   - But often faster than DFS when tree is wide
     *
     * Space Complexity: O(w) where w = maximum width of tree
     *   - Queue stores nodes at each level
     */
    private int minDepthBFS(TreeNode root) {
        if (root == null){
            return 0;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int depth = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i=0; i<size; i++){
                TreeNode node = queue.poll();
                if(node.left == null && node.right == null){
                    return depth;
                }
                if (node.left != null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;

    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }
}
