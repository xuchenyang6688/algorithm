package com.xcy.solutions.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

/**
 * 102. Binary Tree Level Order Traversal
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 *
 * Approach:
 * 1. Use FIFO queue to push a level of nodes, and the poll level nodes to offer next level of nodes, every time poll, need to know how many times to poll to store the result of this level of nodes.
 * Time Complexity: O(N)
 * Space Complexity: O(N)
 */
public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root){
        if (root == null){
            return Collections.emptyList();
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()){

            int queueSize = queue.size();
            List<Integer> levelResult = new ArrayList<>(queueSize);

            for (int i=0; i<queueSize; i++){
                TreeNode node = queue.poll();
                levelResult.add(node.val);
                if (node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            result.add(levelResult);
        }
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
