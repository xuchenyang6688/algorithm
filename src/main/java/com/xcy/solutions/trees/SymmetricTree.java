package com.xcy.solutions.trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * LCR.145 Check If SymmetricTree
 */
public class SymmetricTree {
    public boolean checkSymmetricTree(TreeNode root) {
        return checkSymmetricTreeBFS(root, root);
    }

    private boolean checkSymmetricTreeBFS(TreeNode left, TreeNode right) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(left);
        queue.offer(right);
        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            if (node1 == null && node2 == null) {
                continue;
            }
            if ((node1 == null || node2 == null) || (node1.val != node2.val)) {
                return false;
            }
            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);
        }
        return true;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
