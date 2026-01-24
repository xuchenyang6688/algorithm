package com.xcy.solutions.linked_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 21. Merge Two Sorted Lists
 *
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two
 * lists.

 * Return the head of the merged linked list.
 *
 * Example:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * Constraints:
 *
 *The number of nodes in both lists is in the range [0, 50].
 *-100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedLists {

    /**
     * Merges two sorted linked lists into one sorted list.
     *
     * Approach: Iterative merge with dummy node
     * 1. Create a dummy node to simplify edge cases
     * 2. Compare current nodes of both lists
     * 3. Attach the smaller node to the merged list
     * 4. Advance pointer of the list we took from
     * 5. Continue until one list is exhausted
     * 6. Attach remaining nodes from the non-empty list
     *
     * Key Insight: Since both lists are sorted, we always take the
     * smaller current node. This ensures the merged list stays sorted.
     *
     * Time Complexity: O(n + m) where n = list1 length, m = list2 length
     *   - Each node is visited exactly once
     *
     * Space Complexity: O(1)
     *   - Only uses a few pointers, no extra data structures
     *   - Merges in-place by reusing existing nodes
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (list1!=null && list2!=null){
            if (list1.val <= list2.val){
                current.next = list1;
                list1 = list1.next;
            }else{
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
            
        }
        if (list1!=null){
            current.next = list1;
        }
        if (list2!=null){
            current.next = list2;
        }
        return dummy.next;
    }

    // ==================== LIST NODE DEFINITION ====================

    /**
     * Definition for singly-linked list node.
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode (int val){
            this.val = val;
        }
    }

    // ==================== TESTING UTILITIES ====================

    /**
     * Builds a linked list from array of integers.
     *
     * @param nums array of values (null or empty returns null)
     * @return head of constructed linked list
     */
    private static ListNode buildListNodes(int[] nums){
        if (nums == null || nums.length == 0){
            return null;
        }
        ListNode dummyNode = new ListNode(0);
        ListNode current = dummyNode;
        for (int num: nums) {
           current.next = new ListNode(num);
           current = current.next;
        }
        return dummyNode.next;
    }

    /**
     * Converts linked list to array for easy comparison.
     *
     * @param head head of linked list
     * @return array of values, empty array if head is null
     */
    private static int[] convertListNodesToArray(ListNode head) {
        List<Integer> resultList = new ArrayList<>();
        while(head!=null){
            resultList.add(head.val);
            head = head.next;
        }
        int[] nums = new int[resultList.size()];
        for (int i=0; i< resultList.size(); i++){
            nums[i] = resultList.get(i);
        }
        return nums;
    }

    // ==================== TEST CASES ====================
    public static void main(String[] args) {
        MergeTwoSortedLists solution = new MergeTwoSortedLists();

        // same length
        ListNode list1 = buildListNodes(new int[] {1,2,4});
        ListNode list2 = buildListNodes(new int[] {1,3,4});
        ListNode actual = solution.mergeTwoLists(list1, list2);
        assert Arrays.equals(new int[]{1,1,2,3,4,4}, convertListNodesToArray(actual));

        //different length
        list1 = buildListNodes(new int[] {1,2,4});
        list2 = buildListNodes(new int[] {1,3});
        actual = solution.mergeTwoLists(list1, list2);
        assert Arrays.equals(new int[]{1,1,2,3,4}, convertListNodesToArray(actual));

        //different length
        list1 = buildListNodes(new int[] {1,2,4});
        list2 = buildListNodes(new int[] {1,5});
        actual = solution.mergeTwoLists(list1, list2);
        assert Arrays.equals(new int[]{1,1,2,4,5}, convertListNodesToArray(actual));

    }

}
