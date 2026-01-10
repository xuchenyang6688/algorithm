package com.xcy.solutions.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 94. Binary Tree Inorder Traversal
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example 1:
 *
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 *
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 */
public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root){
        return inorderTraversalIterative(root);
        // List<Integer> result = new ArrayList<>();
        // inorderTraversalRecursive(root, result);
        // return result;
    }

    /**
     * Inorder traversal the tree's nodes iteratively.
     * Time Complexity: O(N)
     * Space Complexity: O(N), Space to store result, the stack space depends on the depth of the tree
     * @param root
     * @return
     */
    private List<Integer> inorderTraversalIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        // if root == null, but stack is empty, it means the right tree is null, need to find the upper level root node and its right tree.
        while(root!=null|| !stack.isEmpty()){
            // DFS current tree's left nodes(the tree is a left tree or a right tree)
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            // Pop the deepest left tree node, add to result, since it doesn't have left node, start to iterative right tree as the same way.
            root = stack.pop();
            result.add(root.val);
            root = root.right;
        }
        return result;
    }

    /**
     * Inorder Traversal a tree's nodes recursively.
     *
     * Time Complexity: O(N), Depending on the count of the tree's nodes,
     * Space Complexity: O(N), Depending on the depth of the recursive call(stack), in the worst case, the tree is a linked list, so is O(N)
     * @param root Tree's root ndoe
     * @param result A list of tree's node val in inorder sequence.
     * @return
     */
    private List<Integer> inorderTraversalRecursive(TreeNode root, List<Integer> result){
        if (root == null){
            return null;
        }
        inorderTraversalRecursive(root.left, result);
        result.add(root.val);
        inorderTraversalRecursive(root.right, result);
        return result;
    }

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
