package com.xcy.solutions.linked_list;

/**
 * 206. Reverse Linked List
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * <p>
 * Follow up: A linked list can be reversed either iteratively or recursively
 */
public class ReversedLinkedList {
    public ListNode reverseListNode(ListNode head) {
        return reverseListNodeRecursively(head);
    }

    /**
     * Approach
     * For 1 -> 2, if we want to change 1.next to null, we must first change 2.next =1, we must make sure the nodes after 2 has reversed, we are safe to change the current and the next nodes' next
     * and we must know what's the reversed result linked nodes' head
     * Recursive end condition: the node doesn't have next node, and it's also the head node of the new linked list
     * The new reversed linked nodes head is the original's linked nodes' last node, if the node doesn't have the next, it's the last, we reverse the node from last to the begin,evertime we reverse
     * @param head
     * @return
     */
    private ListNode reverseListNodeRecursively(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseListNodeRecursively(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * Approach
     * For 1->2->3, when we iterate the node, we want to know the prev, so we can set the current node's next = prev
     * And before we change the next, we must store the original next to avoid we can't iterate
     * @param head
     * @return
     */
    private ListNode reverseListNodeIteratively(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode prev = null;
        ListNode current = head;

        while (current!=null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        ReversedLinkedList solution = new ReversedLinkedList();
        ListNode head = null;
        ListNode tail = buildListNode(head, new int[] {1,2,3,4,5});
        assert tail == solution.reverseListNode(head);
    }

    public static ListNode buildListNode(ListNode head, int[] nums){
        head = new ListNode(nums[0]);
        ListNode prev= head;
        ListNode last = null;
        for (int i =1; i<nums.length; i++){
            ListNode node = new ListNode(nums[i]);
            prev.next = node;
            if (i == nums.length -1){
                last = node;
            }
        }
        return last;
    }

}
