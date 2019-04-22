package com.ddw.leetcode;

public class SwapNodesinPairs {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 24. Swap Nodes in Pairs
     * Given a linked list, swap every two adjacent nodes and return its head.
     * Example:
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     * time: O(n)
     * space: O(1)
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode l1 = dummy;
        ListNode l2 = head;
        while (l2 != null && l2.next != null){
            ListNode nextTemp = l2.next.next;
            l1.next = l2.next;
            l2.next.next= l2;
            l2.next = nextTemp;
            l1 = l2;
            l2 = l2.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l1_2 = new ListNode(3);
        ListNode l1_3 = new ListNode(4);
        l1.next = l1_2;
        l1_2.next = l1_3;

        ListNode result = swapPairs(l1);
        while (result != null ){
            System.out.println(result.val);
            result = result.next;
        }

    }


}


