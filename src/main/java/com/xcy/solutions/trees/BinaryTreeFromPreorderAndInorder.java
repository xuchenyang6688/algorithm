package com.xcy.solutions.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 *
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and
 * inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 */
public class BinaryTreeFromPreorderAndInorder {

    private final Map<Integer, Integer> indexByVal = new HashMap<>();

    /**
     *
     * @param preorder
     * @param inorder
     * @return
     * Time Complexity: O(N)
     * Space Complexity: O(N)  (Map space, Recursive stack space O(h), Return values O(N))
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        int n = preorder.length;
        buildValIndexMap(inorder);
        return buildTreeHelper(preorder, inorder, 0, n-1, 0, n-1);
    }

    /**
     *
     * @param preorder tree's preorder
     * @param inorder tree's inorder
     * @param preorderStart preorder array's start index (inclusive)
     * @param preorderEnd preorder array's end index (inclusive)
     * @param inorderStart inorder array's start index (inclusive)
     * @param inorderEnd inorder array's end index (inclusive)
     * @return the root of the tree
     *
     */
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preorderStart, int preorderEnd, int inorderStart, int inorderEnd) {
        if (preorderStart>preorderEnd) {
            return null;
        }
        int rootVal = preorder[preorderStart];
        int rootIndex = indexByVal.get(rootVal);
        int leftTreeNodeSize = rootIndex - inorderStart;

        TreeNode root = new TreeNode(rootVal);
        root.left = buildTreeHelper(preorder, inorder, preorderStart+1, preorderStart+leftTreeNodeSize, inorderStart, rootIndex-1);
        root.right = buildTreeHelper(preorder, inorder, preorderStart+1+leftTreeNodeSize, preorderEnd, rootIndex+1, inorderEnd);
        return root;
    }

    private void buildValIndexMap(int[] array){
        for (int i=0; i<array.length; i++){
            indexByVal.put(array[i], i);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static Integer[] levelOrder(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> levelOrderList = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                TreeNode currentNode = queue.poll();
                levelOrderList.add(currentNode.val);
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
        }
        return levelOrderList.toArray(new Integer[0]);
    }

    public static void main(String[] args) {
        BinaryTreeFromPreorderAndInorder solution = new BinaryTreeFromPreorderAndInorder();
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        TreeNode root = solution.buildTree(preorder, inorder);
        Integer[] result = levelOrder(root);
        assert Arrays.equals(new Integer[]{3, 9, 20, 15, 7}, result, (a, b) -> {
            if (a == null && b == null) {
                return 0;
            }
            if (a == null || b == null) {
                return -1;
            }
            return Integer.compare(a, b);

        });

    }
}
