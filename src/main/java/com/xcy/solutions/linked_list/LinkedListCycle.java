package com.xcy.solutions.linked_list;

/**
 * 141. Linked List Cycle
 *
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 *
 * Constraints:
 *
 * The number of the nodes in the list is in the range [0, 104].
 * -105 <= Node.val <= 105
 * pos is -1 or a valid index in the linked-list.
 *
 * Follow up: Can you solve it using O(1) (i.e. constant) memory? (If O(N), use hashmap)
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Check both fast and fast.next for null to avoid NullPointerException
        while (fast != null && fast.next != null) {
            slow = slow.next;          // Move 1 step
            fast = fast.next.next;     // Move 2 steps

            if (slow == fast) {
                return true;           // Cycle detected
            }
        }

        return false;                  // Fast reached null, no cycle
    }

    // ==================== LIST NODE DEFINITION ====================

    /**
     * Definition for singly-linked list node.
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    private static ListNode buildListNodes(int[] nums, int position) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        ListNode positionNode = null;
        ListNode tail = null;
        for (int i = 0; i < nums.length; i++) {
            current.next = new ListNode(nums[i]);
            current = current.next;
            if (i == position) {
                positionNode = current;
            }
            if (i == nums.length - 1) {
                tail = current;
                tail.next = positionNode;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = buildListNodes(new int[]{1, 2, 3, 4}, 1);
        LinkedListCycle solution = new LinkedListCycle();
        assert solution.hasCycle(head);

        head = buildListNodes(new int[]{1, 2, 3, 4}, -1);
        solution = new LinkedListCycle();
        assert !solution.hasCycle(head);
    }
}
