package com.xcy.solutions.linked_list;

/**
 * 206. Reverse Linked List
 *
 * Given the head of a singly linked list, reverse the list,
 * and return the reversed list.
 *
 * Example:
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * Constraints:
 * - The number of nodes in the list is in the range [0, 5000]
 * - -5000 <= Node.val <= 5000
 *
 * Follow up: A linked list can be reversed either iteratively or recursively.
 * Recursive: Space Complexity: O(N)
 * Iterative: Space Complexity: O(1)
 */
public class ReversedLinkedList {
    /**
     * Reverses a linked list (entry point).
     * Defaults to recursive implementation.
     */
    public ListNode reverseList(ListNode head) {
        return reverseListRecursive(head); // or reverseListIterative(head)
    }

    /**
     * Recursive approach to reverse linked list.
     *
     * Approach: Divide and Conquer
     * 1. Recursively reverse the rest of the list (head.next)
     * 2. Make current node the new tail (head.next.next = head)
     * 3. Terminate new tail (head.next = null)
     *
     * Key Insight: The last node becomes the new head, and
     * we reconnect nodes while unwinding the recursion.
     *
     * Visualization for 1→2→3→null:
     * 1. reverse(1): reverse(2) → gets 3 as newHead
     * 2. reverse(2): reverse(3) → returns 3 (base case)
     * 3. At reverse(2): 2.next.next = 2 (3→2), 2.next = null
     * 4. At reverse(1): 1.next.next = 1 (2→1), 1.next = null
     *
     * Time Complexity: O(n) - each node processed once
     * Space Complexity: O(n) - recursion stack depth
     */
    private ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // Reverse rest of the list
        ListNode newHead = reverseListRecursive(head.next);

        // Reconnect current node as new tail
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * Iterative approach to reverse linked list.
     *
     * Approach: Three-pointer technique(Reverse links one by one using prev/current/next pointers)
     * 1. Maintain prev, current, and next pointers
     * 2. For each node: save next, reverse link, advance pointers
     * 3. Continue until all nodes processed
     *
     * Key Insight: We only need to reverse the "next" pointers
     * one by one while preserving access to remaining list.
     *
     * Time Complexity: O(n) - single pass through list
     * Space Complexity: O(1) - constant extra space
     */
    private ListNode reverseListIterative(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode current = head;

        while (current != null) {
            // Save next node before reversing link
            ListNode next = current.next;
            // Reverse the link
            current.next = prev;

            // Move pointers forward
            prev = current;
            current = next;
        }

        // prev is now the new head
        return prev;
    }

    /**
     * Definition for singly-linked list node.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // ==================== TESTING UTILITIES ====================

    /**
     * Builds a linked list from array values.
     * Returns the head of the constructed list.
     */
    public static ListNode buildListNode(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int num: nums){
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummy.next;
    }

    /**
     * Compares two linked lists by values.
     */
    public static boolean compareLists(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1 == null && l2 == null;
    }

    public static void main(String[] args) {
        ReversedLinkedList solution = new ReversedLinkedList();

        // Test Case 1: Normal case
        ListNode result = solution.reverseList(buildListNode(new int[]{1,2,3,4,5}));
        assert compareLists(buildListNode(new int[]{5,4,3,2,1}), result);

        // Test Case 2: Edge case - Single Node
        result = solution.reverseList(buildListNode(new int[]{1}));
        assert compareLists(buildListNode(new int[]{1}), result);

        // Test Case 3: Edge case - Empty
        result = solution.reverseList(null);
        assert compareLists(null, result);

        System.out.println("All tests passed!");
    }

}
